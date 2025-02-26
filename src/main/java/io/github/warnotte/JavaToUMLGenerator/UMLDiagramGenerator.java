package io.github.warnotte.JavaToUMLGenerator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.attribute.Arrow.DirType;
import guru.nidi.graphviz.attribute.Rank.RankDir;
import guru.nidi.graphviz.engine.*;
import guru.nidi.graphviz.model.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static guru.nidi.graphviz.model.Factory.*;

public class UMLDiagramGenerator {
	
	
	public static void main(String[] args) {
		String jsonFilePath = "classes.json"; // Le fichier JSON généré précédemment
		String outputImagePath = "uml_diagram.png"; // Fichier de sortie

		process(jsonFilePath, outputImagePath);
	}

	static void process(String jsonFilePath, String outputImagePath) {
		UMLDiagramGeneratorConfig config = new UMLDiagramGeneratorConfig();
		config.writeMethods=true;
		config.writeVariables=true;
		config.writeEnum=true;
		
		process(jsonFilePath, outputImagePath, config);
	}
	
	static void process(String jsonFilePath, String outputImagePath, UMLDiagramGeneratorConfig config) {

		try {
			// Charger le JSON
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(new File(jsonFilePath));

			// Construire le graphe UML
			Graph g = graph("UML").directed().graphAttr().with(Rank.dir(RankDir.TOP_TO_BOTTOM)).nodeAttr().with(Shape.RECORD);

			// Ajouter les classes et leurs relations
			for (Iterator<String> it = rootNode.fieldNames(); it.hasNext();) {
				String className = it.next();
				JsonNode classNode = rootNode.get(className);
				String type = classNode.get("type").asText();

				// Construire le label avec le style "record"
				StringBuilder label = new StringBuilder("{" + sanitizeString(className));
				
				
				// Ajouter les variables des classes
				if (config.writeVariables) {
					if (classNode.has("variables")) {
						if (classNode.get("variables").size() != 0) {
							label.append("|");
							for (JsonNode variable : classNode.get("variables")) {
								String varName = sanitizeString(variable.get("name").asText());
								String varType = sanitizeString(variable.get("type").asText());
								label.append(varType).append(": ").append(varName).append("\\l"); // \l pour aligner à gauche
							}
						}
					}
				}

				if (config.writeMethods) {
					if (classNode.has("methods")) {
						if (classNode.get("methods").size() != 0) {
							label.append("|");
							for (JsonNode method : classNode.get("methods")) {
								if (method.get("access").asText().equals("public")) {
									String methodName = sanitizeString(method.get("name").asText());
									String returnType = sanitizeString(method.get("returnType").asText());
									label.append(returnType).append(" ").append(methodName).append("()\\l");
								}
							}
						}
					}
				}

				// Ajouter les valeurs des enums
				if (config.writeEnum) {
					if (type.equals("enum") && classNode.has("values")) {
						if (classNode.get("values").size() != 0) {
							label.append("|");
							for (JsonNode enumValue : classNode.get("values")) {
								label.append(sanitizeString(enumValue.asText())).append("\\l");
							}
						}
					}
				}

				label.append("}");

				// Appliquer un style différent pour les classes, interfaces et enums
				Node classNodeGraph;
				if (type.equals("interface")) {
					classNodeGraph = node(className).with(Label.raw(label.toString()), Shape.RECORD, Style.ROUNDED, Color.ORANGE);
				} else if (type.equals("enum")) {
					classNodeGraph = node(className).with(Label.raw(label.toString()), Shape.RECORD, Style.ROUNDED, Color.GRAY);
				} else {
					classNodeGraph = node(className).with(Label.raw(label.toString()), Shape.RECORD, Style.FILLED, Color.rgba("#00008040")/*Color.BLUE*/);
				}
				
				// Héritage (extends)
				if (classNode.has("extends") && !classNode.get("extends").isNull()) {
					String parentClass = sanitizeString(classNode.get("extends").asText());
					g = g.with(node(parentClass).link(to(classNodeGraph).with(Arrow.VEE, Style.DASHED)));
				}

				// Interfaces (implements)
				if (classNode.has("implements")) {
					for (JsonNode implementedInterface : classNode.get("implements")) {
						String interfaceName = sanitizeString(implementedInterface.asText());
						g = g.with(node(interfaceName).with(Shape.RECORD, Color.ORANGE).link(to(classNodeGraph).with(Arrow.VEE, Style.DASHED)));
					}
				}

				// Variables et associations
				/*if (classNode.has("variables")) {
					for (JsonNode variable : classNode.get("variables")) {
						String varType = sanitizeString(variable.get("type").asText());
						String genericType = variable.has("generic_type") ? sanitizeString(variable.get("generic_type").asText()) : null;

						// Si c'est une classe associée, on crée un lien
						if (rootNode.has(varType)) {
							g = g.with(classNodeGraph.link(to(node(varType)).with(Arrow.NORMAL, Style.SOLID)));
						} else if (genericType != null && rootNode.has(genericType)) {
							g = g.with(classNodeGraph.link(to(node(genericType)).with(Arrow.NORMAL, Style.SOLID)));
						}
					}
				}*/
				
			/*	if (classNode.has("associations1to1")) {
					for (JsonNode implementedInterface : classNode.get("associations1to1")) {
						String interfaceName = sanitizeString(implementedInterface.asText());
						g = g.with(classNodeGraph.link(to(node(interfaceName)).with(Arrow.NONE, Style.SOLID)));
						
					}
				}
				if (classNode.has("associations0toN")) {
					for (JsonNode implementedInterface : classNode.get("associations0toN")) {
						String interfaceName = sanitizeString(implementedInterface.asText());
						g = g.with(classNodeGraph.link(to(node(interfaceName)).with(Arrow.DIAMOND, Style.SOLID)));
						//g = g.with(classNodeGraph.link(to(node(interfaceName)).with(Arrow.DIAMOND, Style.SOLID, Style.tapered(1, DirType.BOTH))));
					}
				}*/
				
				if (classNode.has("associations")) {
					for (JsonNode implementedInterface : classNode.get("associations")) {
						String interfaceName = sanitizeString(implementedInterface.get("name").asText());
						String relationType = sanitizeString(implementedInterface.get("relationType").asText());
						if (relationType.equals(relType._0N.toString()))
							g = g.with(classNodeGraph.link(to(node(interfaceName)).with(Arrow.DIAMOND, Style.SOLID, Label.of(relationType))));
						else
							g = g.with(classNodeGraph.link(to(node(interfaceName)).with(Arrow.NONE, Style.SOLID, Label.of(relationType))));
						
					//	g = g.with(node(interfaceName).with(Shape.RECORD, Color.ORANGE).link(to(classNodeGraph).with(Arrow.VEE, Style.DASHED)));
						
					
					}
				}
				
				g = g.with(classNodeGraph);
			}

			// Générer l'image du diagramme UML
			Graphviz.fromGraph(g).render(Format.PNG).toFile(new File(outputImagePath));
			Graphviz.fromGraph(g).render(Format.SVG).toFile(new File(outputImagePath+".svg"));
			Graphviz.fromGraph(g).render(Format.DOT).toFile(new File(outputImagePath+".dot"));
			System.out.println("Diagramme UML généré : " + outputImagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String sanitizeString(String text) {
		return text.replace("<", "&lt;").replace(">", "&gt;");
	}
}
