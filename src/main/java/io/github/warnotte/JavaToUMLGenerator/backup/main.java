package io.github.warnotte.JavaToUMLGenerator.backup;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

public class main {

	public static void main(String[] args) throws IOException {
		
		String dir = "testDatas\\set2";
		Set<String> excluded = new HashSet<>(List.of("PropertyChangeSupport", "ClasseBExclure", "EnumExclu"));
		String jsonFilePath = "classes.json";
		
		JavaClassInspector.process(dir, excluded, jsonFilePath);
		
		String outputImagePath = "uml_diagram.png"; // Fichier de sortie

		UMLDiagramGenerator.process(jsonFilePath, outputImagePath);

		Desktop.getDesktop().open(new File(outputImagePath));
	}

}
