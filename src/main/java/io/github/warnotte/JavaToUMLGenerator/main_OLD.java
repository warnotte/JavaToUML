package io.github.warnotte.JavaToUMLGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class main_OLD {

	enum Mode {
		JAVATOJSON, JSONTOUML
	};

	public static void main(String[] args) {
		
		System.out.println("Welcome to JAVA Source code to UML generator by Wax78");
		
		Mode mode = Mode.JAVATOJSON; // Must be a parameter of the command line

		switch (mode) {
		case JAVATOJSON:
			String javaSourceFolder = "testDatas\\set1"; // Must be a parameter of the command line
			String outputjsonFile = "classes.json"; // Must be a parameter of the command line
			Set<String> excluded = new HashSet<>(List.of("PropertyChangeSupport", "Logger", "EnumExclu" /*, ... etc .... */));  // // Must be a optionnal parameter of the command line with multiple strings
			JavaClassInspector.process(javaSourceFolder, excluded, outputjsonFile);
			break;
		case JSONTOUML:
			String inputjsonFile = "classes.json";
			String outputImage = "uml_diagram.png"; // Fichier de sortie
			UMLDiagramGeneratorConfig umlConfig = new UMLDiagramGeneratorConfig();
			umlConfig.writeVariables = true; // Must be a optionnal parameter of the command line otherwise it's true by default 
			umlConfig.writeMethods = true; // Must be a optionnal parameter of the command line otherwise it's true by default
			umlConfig.writeEnum = true; // Must be a optionnal parameter of the command line otherwise it's true by default
			UMLDiagramGenerator.process(inputjsonFile, outputImage, umlConfig);
			break;
		}

	}

}
