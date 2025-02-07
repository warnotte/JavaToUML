package io.github.warnotte.JavaToUMLGenerator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.type.Type;

public class JavaClassInspector {

    public static void main(String[] args) {
        String dir = "testDatas\\set1";
        Set<String> excluded = new HashSet<>(List.of("PropertyChangeSupport", "ClasseBExclure", "EnumExclu"));
        String jsonOutfile = "classes.json";

        process(dir, excluded, jsonOutfile);
    }

    static void process(String dir, Set<String> excluded, String jsonoutfile) {
        List<File> javaFiles = new ArrayList<>();
        collectJavaFiles(new File(dir), javaFiles);
        JSONObject result = extractClassesAndRelations(javaFiles, excluded);

        try (FileWriter file = new FileWriter(jsonoutfile)) {
            file.write(result.toString(4));
            System.out.println("Fichier JSON généré : " + jsonoutfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void collectJavaFiles(File file, List<File> javaFiles) {
        if (file.isDirectory()) {
            for (File child : Objects.requireNonNull(file.listFiles())) {
                collectJavaFiles(child, javaFiles);
            }
        } else if (file.getName().endsWith(".java")) {
            javaFiles.add(file);
        }
    }

    private static JSONObject extractClassesAndRelations(List<File> javaFiles, Set<String> excludedClasses) {
        JSONObject classes = new JSONObject();

        for (File file : javaFiles) {
            try {
                CompilationUnit cu = StaticJavaParser.parse(file);
                cu.findAll(ClassOrInterfaceDeclaration.class).forEach(cls -> {
                    String className = cls.getNameAsString();
                    if (excludedClasses.contains(className)) return;

                    JSONObject classInfo = new JSONObject();
					classInfo.put("type", "class");
					classInfo.put("variables", new JSONArray());
					classInfo.put("methods", new JSONArray());

					// Vérifier et exclure les classes parentes
					classInfo.put("extends", cls.getExtendedTypes().isNonEmpty() && !excludedClasses.contains(cls.getExtendedTypes().get(0).getNameAsString()) ? cls.getExtendedTypes().get(0)
							.getNameAsString() : JSONObject.NULL);

					JSONArray implementedTypes = new JSONArray();
					cls.getImplementedTypes().forEach(it -> {
						if (!excludedClasses.contains(it.getNameAsString())) {
							implementedTypes.put(it.getNameAsString());
						}
					});
                    classInfo.put("implements", implementedTypes);

                    JSONArray associations = new JSONArray();

                    for (FieldDeclaration field : cls.getFields()) {
                        for (VariableDeclarator var : field.getVariables()) {
                            JSONObject varInfo = new JSONObject();
                            Type varType = var.getType();
                            String typeName = varType.toString();

                            if (excludedClasses.contains(typeName)) continue;

                            String accessModifier = field.getModifiers().stream()
                                    .map(m -> m.getKeyword().asString())
                                    .findFirst()
                                    .orElse("default");

                            JSONArray annotations = new JSONArray();
                            field.getAnnotations().forEach(ann -> annotations.put(parseAnnotation(ann)));

                            varInfo.put("name", var.getNameAsString());
                            varInfo.put("type", typeName);
                            varInfo.put("access", accessModifier);
                            varInfo.put("annotations", annotations);

                            classInfo.getJSONArray("variables").put(varInfo);
                        }
                    }

                    for (MethodDeclaration method : cls.getMethods()) {
                        JSONObject methodInfo = new JSONObject();
                        String returnType = method.getType().toString();
                        String accessModifier = method.getModifiers().stream()
                                .map(m -> m.getKeyword().asString())
                                .findFirst()
                                .orElse("default");
                        boolean isAbstract = method.isAbstract();
                        
                        JSONArray annotations = new JSONArray();
                        method.getAnnotations().forEach(ann -> annotations.put(parseAnnotation(ann)));

                        methodInfo.put("name", method.getNameAsString());
                        methodInfo.put("returnType", returnType);
                        methodInfo.put("access", accessModifier);
                        methodInfo.put("annotations", annotations);
                        methodInfo.put("isAbstract", isAbstract);
                        
                        classInfo.getJSONArray("methods").put(methodInfo);
                    }

                    classInfo.put("associations", associations);
                    classes.put(className, classInfo);
                });
                

				// Enums
				cu.findAll(EnumDeclaration.class).forEach(enumDecl -> {
					String enumName = enumDecl.getNameAsString();
					if (excludedClasses.contains(enumName))
						return; // Ignorer l'enum si exclue

					JSONObject enumInfo = new JSONObject();
					enumInfo.put("type", "enum");
					enumInfo.put("variables", new JSONArray());
					enumInfo.put("methods", new JSONArray());

					JSONArray enumValues = new JSONArray();
					enumDecl.getEntries().forEach(entry -> enumValues.put(entry.getNameAsString()));
					enumInfo.put("values", enumValues);

					// TODO : Duplicata du dessus ... grrr
					for (MethodDeclaration method : enumDecl.getMethods()) {
                        JSONObject methodInfo = new JSONObject();
                        String returnType = method.getType().toString();
                        String accessModifier = method.getModifiers().stream()
                                .map(m -> m.getKeyword().asString())
                                .findFirst()
                                .orElse("default");
                        boolean isAbstract = method.isAbstract();
                        
                        JSONArray annotations = new JSONArray();
                        method.getAnnotations().forEach(ann -> annotations.put(parseAnnotation(ann)));

                        methodInfo.put("name", method.getNameAsString());
                        methodInfo.put("returnType", returnType);
                        methodInfo.put("access", accessModifier);
                        methodInfo.put("annotations", annotations);
                        methodInfo.put("isAbstract", isAbstract);
                        
                        enumInfo.getJSONArray("methods").put(methodInfo);
                    }
					
					
					classes.put(enumName, enumInfo);
					
					
				});
            } catch (IOException | ParseProblemException e) {
                System.out.println("Erreur de parsing dans " + file.getName());
            }
        }
        return classes;
    }
    
    private static JSONObject parseAnnotation(AnnotationExpr annotation) {
        JSONObject annotationInfo = new JSONObject();
        annotationInfo.put("name", annotation.getNameAsString());

        if (annotation instanceof NormalAnnotationExpr normalAnn) {
            JSONObject parameters = new JSONObject();
            for (MemberValuePair pair : normalAnn.getPairs()) {
                parameters.put(pair.getNameAsString(), pair.getValue().toString());
            }
            annotationInfo.put("parameters", parameters);
        }
        return annotationInfo;
    }
}
