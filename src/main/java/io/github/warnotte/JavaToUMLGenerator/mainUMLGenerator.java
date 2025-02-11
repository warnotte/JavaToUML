package io.github.warnotte.JavaToUMLGenerator;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

@Command(name = "umlgen", mixinStandardHelpOptions = true, version = "1.0",
        description = "Converts Java source code to JSON or JSON to UML diagrams.")
public class mainUMLGenerator implements Callable<Integer> {

	// java -jar monprogramme.jar -m JAVATOJSON -i testDatas/set1 -o classes.json --exclude Logger,PropertyChangeSupport
	// java -jar monprogramme.jar -m JSONTOUML -i classes.json -o uml_diagram.png --writeVariables false --writeMethods true --writeEnum false

    @Option(names = {"-m", "--mode"}, description = "Mode: JAVATOJSON or JSONTOUML", required = true)
    private Mode mode;

    @Option(names = {"-i", "--input"}, description = "Input file (json if JSONTOUML) or directory (javasources if JAVATOJSON)")
    private String input;

    @Option(names = {"-o", "--output"}, description = "Output file (png if JSONTOUML or json if JAVATOJSON)", required = true)
    private String output;

    @Option(names = {"-e", "--exclude"}, description = "[JAVATOJSON] Classes to exclude (comma separated)")
    private Set<String> excluded = new HashSet<>();

    @Option(names = {"--writeVariables"}, description = "[JSONTOUML] Write variables in UML diagram (default: true)")
    private Boolean writeVariables = true;

    @Option(names = {"--writeMethods"}, description = "[JSONTOUML] Write methods in UML diagram (default: true)")
    private Boolean writeMethods = true;

    @Option(names = {"--writeEnum"}, description = "[JSONTOUML] Write enums in UML diagram (default: true)")
    private Boolean writeEnum = true;

    enum Mode {
        JAVATOJSON, JSONTOUML
    }

    @Override
    public Integer call() {
        System.out.println("Welcome to JAVA Source code to UML generator by Wax78");

        switch (mode) {
            case JAVATOJSON:
                if (input == null) {
                    System.err.println("Error: Java source folder is required for JAVATOJSON mode.");
                    return 1;
                }
                JavaClassInspector.process(input, excluded, output);
                break;
            case JSONTOUML:
                if (input == null) {
                    System.err.println("Error: JSON input file is required for JSONTOUML mode.");
                    return 1;
                }
                UMLDiagramGeneratorConfig umlConfig = new UMLDiagramGeneratorConfig();
                umlConfig.writeVariables = writeVariables;
                umlConfig.writeMethods = writeMethods;
                umlConfig.writeEnum = writeEnum;
                UMLDiagramGenerator.process(input, output, umlConfig);
                break;
        }
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new mainUMLGenerator()).execute(args);
        System.exit(exitCode);
    }
}
