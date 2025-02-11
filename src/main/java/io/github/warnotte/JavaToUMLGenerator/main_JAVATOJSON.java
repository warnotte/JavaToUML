package io.github.warnotte.JavaToUMLGenerator;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class main_JAVATOJSON {

	public static void main(String[] args) throws IOException {
		String javaSourceFolder = "testDatas\\set1";
		String jsonFilePath = "classes.json";
		Set<String> excluded = new HashSet<>(List.of("PropertyChangeSupport", "Logger", "EnumExclu"));
		JavaClassInspector.process(javaSourceFolder, excluded, jsonFilePath);
	}

}
