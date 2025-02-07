# UML Diagram Generator

## Description

This project contains 3 main classes:
1. **JavaClassInspector**: Inspects Java source files to extract information about classes, their attributes, inheritance relationships, and object associations.
2. **UMLDiagramGenerator**: Generates a UML diagram from the extracted data and produces an image of the diagram using Graphviz.
3. **Main**: Call the 2 other classes to process a folder recursively containing java source code of a model.


## Features
- Automatic analysis of classes and enums in a set of `.java` files.
- Extraction of attributes, types, inheritance (`extends`), interfaces (`implements`), and associations between classes.
- Generation of a JSON file containing structured class information.
- Production of a UML diagram in image format (`.png`) using Graphviz.

## Prerequisites
- **Java 11+**
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
  </dependencies>
```

## Usage
### 1️⃣ Running `JavaClassInspector`
```sh
java JavaClassInspector file1.java file2.java directory/
```
📌 **Output**: Generates a `classes.json` file containing the extracted data.

### 2️⃣ Running `UMLDiagramGenerator`
```sh
java UMLDiagramGenerator
```
📌 **Output**: Produces an `uml_diagram.png` image representing the UML diagram.

## Examples
### 📄 Example of Generated JSON
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

## Possible Improvements
- Add support for **methods** in classes.
- Add support for **access modifiers** (`public`, `private`, etc.).
- Generate **SVG diagrams** for better quality rendering.

## Authors
Developed by Warnotte Renaud.

---
✏️ **Feel free to contribute or suggest improvements!** 🚀

