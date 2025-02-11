# JAVA UML Diagram Generator

## Description

This project aim to generated Basic UML Class diagramm out of java code by using JaveParser and GraphViz

This project contains 3 main classes:
1. **JavaClassInspector**: Inspects Java source files to extract information about classes, their attributes, inheritance relationships, and object associations.
2. **UMLDiagramGenerator**: Generates a UML diagram from the extracted data and produces an image of the diagram using Graphviz.
3. **mainUMLGenerator**: Call the 2 other classes to process a folder recursively containing java source code of a model according to parameters

## Features
- Automatic analysis of classes and enums in a set of `.java` files. Using com.github.javaparser
- Extraction of attributes, types, inheritance (`extends`), interfaces (`implements`), and associations between classes.
- Generation of a JSON file containing structured class information.
- Production of a UML diagram in image format (`.png`), DOT and SVG using Graphviz.

## Prerequisites
- **Java 21+**
- **Graphviz installed** (accessible via the `dot` command) (Possibility maybe to use JS V8 engine)
- **Required Maven dependencies**:

```xml
  <dependencies>
		<dependency>
			<groupId>com.github.javaparser</groupId>
			<artifactId>javaparser-symbol-solver-core</artifactId>
			<version>3.26.3</version>
		</dependency>
		<dependency>
			<groupId>org.json</groupId>
			<artifactId>json</artifactId>
			<version>20250107</version>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.15.0</version>
		</dependency>
		<dependency>
			<groupId>guru.nidi</groupId>
			<artifactId>graphviz-java</artifactId>
			<version>0.18.1</version>
		</dependency>
		<dependency>
			<groupId>info.picocli</groupId>
			<artifactId>picocli</artifactId>
			<version>4.7.5</version>
		</dependency>
  </dependencies>
```

## Usage
### 1️ Running `JavaClassInspector`
```sh
java JavaClassInspector file1.java file2.java directory/
```
📌 **Output**: Generates a `classes.json` file containing the extracted data.

### 2️ Running `UMLDiagramGenerator`
```sh
java UMLDiagramGenerator
```
📌 **Output**: Produces an `uml_diagram.png` image representing the UML diagram.

### 3 Full application

mainUMLGenerator is the main class that can do everything in one place

## Examples
### Example of Generated JSON
```json
{
    "Person": {
        "type": "class",
        "variables": [
            { "name": "name", "type": "String" },
            { "name": "age", "type": "int" }
        ],
        "extends": null,
        "implements": ["Serializable"],
        "associations": []
    }
}
```

### 🖼 Example of Generated UML Diagram
```
+-----------+
|  Person   |
|-----------|
| name: String |
| age: int  |
+-----------+
```

## Usage from command line with jar release

```
Usage: umlgen [-hV] [--writeEnum] [--writeMethods] [--writeVariables]
              [-i=<input>] -m=<mode> -o=<output> [-e=<excluded>]...
Converts Java source code to JSON or JSON to UML diagrams.
  -e, --exclude=<excluded>   [JAVATOJSON] Classes to exclude (comma separated)
  -h, --help                 Show this help message and exit.
  -i, --input=<input>        Input file (json if JSONTOUML) or directory
                               (javasources if JAVATOJSON)
  -m, --mode=<mode>          Mode: JAVATOJSON or JSONTOUML
  -o, --output=<output>      Output file (png if JSONTOUML or json if
                               JAVATOJSON)
  -V, --version              Print version information and exit.
      --writeEnum            [JSONTOUML] Write enums in UML diagram (default:
                               true)
      --writeMethods         [JSONTOUML] Write methods in UML diagram (default:
                               true)
      --writeVariables       [JSONTOUML] Write variables in UML diagram
                               (default: true)
```

### Exemple 

```
java -jar monprogramme.jar -m JAVATOJSON -i testDatas/set1 -o classes.json --exclude Logger,PropertyChangeSupport
java -jar monprogramme.jar -m JSONTOUML -i classes.json -o uml_diagram.png --writeVariables false --writeMethods true --writeEnum false
```

## Authors
Developed by Warnotte Renaud.

---
✏️ **Feel free to contribute or suggest improvements!** 🚀

