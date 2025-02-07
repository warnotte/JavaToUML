package io.github.warnotte.JavaToUMLGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.nidi.graphviz.attribute.*;
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
				StringBuilder label = new StringBuilder("{" + sanitizeString(className) + "| ");

				// Ajouter les variables des classes
				if (classNode.has("variables")) {
					for (JsonNode variable : classNode.get("variables")) {
						String varName = sanitizeString(variable.get("name").asText());
						String varType = sanitizeString(variable.get("type").asText());
						label.append(varType).append(": ").append(varName).append("\\l"); // \l pour aligner à gauche
					}
				}

				// Ajouter les valeurs des enums
				if (type.equals("enum") && classNode.has("values")) {
					// label.append("| ");
					for (JsonNode enumValue : classNode.get("values")) {
						label.append(sanitizeString(enumValue.asText())).append("\\l");
					}
				}

				label.append("}");

				// Appliquer un style différent pour les classes, interfaces et enums
				Node classNodeGraph;
				if (type.equals("interface")) {
					classNodeGraph = node(className).with(Label.raw(label.toString()), Shape.RECORD, Style.SOLID, Color.ORANGE);
				} else if (type.equals("enum")) {
					classNodeGraph = node(className).with(Label.raw(label.toString()), Shape.RECORD, Style.SOLID, Color.GRAY);
				} else {
					classNodeGraph = node(className).with(Label.raw(label.toString()), Shape.RECORD, Style.SOLID, Color.BLACK);
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
				if (classNode.has("variables")) {
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
				}

				g = g.with(classNodeGraph);
			}

			// Générer l'image du diagramme UML
			Graphviz.fromGraph(g).render(Format.PNG).toFile(new File(outputImagePath));
			System.out.println("Diagramme UML généré : " + outputImagePath);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String sanitizeString(String text) {
		return text.replace("<", "&lt;").replace(">", "&gt;");
	}
}
