package io.github.warnotte.JavaToUMLGenerator;

import java.io.IOException;

public class main_JSONTOUML {

	public static void main(String[] args) throws IOException {
		String jsonFilePath = "classes.json";
		String outputImagePath = "uml_diagram.png"; // Fichier de sortie
		UMLDiagramGeneratorConfig umlConfig = new UMLDiagramGeneratorConfig();
		umlConfig.writeVariables=true;
		umlConfig.writeMethods=true;
		umlConfig.writeEnum=true;	
		UMLDiagramGenerator.process(jsonFilePath, outputImagePath, umlConfig);
	}

}
