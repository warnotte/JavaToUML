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

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ParserConfiguration;
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
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.metamodel.PropertyMetaModel;

public class JavaClassInspector {

    public static void main(String[] args) {
    	// TODO : SVG
    	// TODO : N'exclut pas Logger ??
    	String dir = "testDatas\\set1";
        Set<String> excluded = new HashSet<>(List.of("PropertyChangeSupport", "Logger", "EnumExclu"));
        String jsonOutfile = "classes.json";

        process(dir, excluded, jsonOutfile);
    }

    
    /*
    ParserConfiguration config = new ParserConfiguration()
            .setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_8)
            .setAttributeComments(false)
            .setDoNotAssignCommentsPrecedingEmptyLines(true)
            .setPreprocessUnicodeEscapes(false)
            .setLexicalPreservationEnabled(false);

    JavaParser parser = new JavaParser(config);
    CompilationUnit cu = parser.parse(file).getResult()
            .orElseThrow(() -> new IOException("Erreur de parsing dans " + file.getName()));
    */
    
    
    static void process(String dir, Set<String> excluded, String jsonoutfile) {
    	
    	
    	System.out.println("Fiel"
    			+ "ds of PropertyMetaModel: ");
    	for (java.lang.reflect.Field field : PropertyMetaModel.class.getDeclaredFields()) {
    	    System.out.println(field.getName());
    	}
    	
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
		Set<String> classNames = new HashSet<>();
		Set<String> enumNames = new HashSet<>();

/*
    	ParserConfiguration config = new ParserConfiguration()
                .setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_8)
                .setAttributeComments(false)
                .setDoNotAssignCommentsPrecedingEmptyLines(true)
                .setPreprocessUnicodeEscapes(false)
                .setLexicalPreservationEnabled(false);
    	
    	JavaParser parser = new JavaParser(config);
 */   	
    	
		
		// Étape 1 : Identifier toutes les classes et enums pour les associations
		for (File file : javaFiles) {
			try {
				System.err.println("File : "+file);
				
				

				CompilationUnit cu = StaticJavaParser.parse(file);
				
				//CompilationUnit cu = parser.parse(file).getResult()
			    //        .orElseThrow(() -> new IOException("Erreur de parsing dans " + file.getName()));
				
				cu.findAll(ClassOrInterfaceDeclaration.class).forEach(cls -> {
					if (!excludedClasses.contains(cls.getNameAsString())) {
						classNames.add(cls.getNameAsString());
					}
				});
				cu.findAll(EnumDeclaration.class).forEach(enumDecl -> {
					if (!excludedClasses.contains(enumDecl.getNameAsString())) {
						enumNames.add(enumDecl.getNameAsString());
					}
				});
			} catch (IOException | ParseProblemException e) {
				System.out.println("Erreur de parsing dans " + file.getName());
			}
		}

		// Étape 2 : Extraction des relations et variables
        for (File file : javaFiles) {
            try {
            	System.out.println("Analysing : "+file);
                CompilationUnit cu = StaticJavaParser.parse(file);
                
               // CompilationUnit cu = parser.parse(file).getResult()
              //          .orElseThrow(() -> new IOException("Erreur de parsing dans " + file.getName()));
                
                cu.findAll(ClassOrInterfaceDeclaration.class).forEach(cls -> {
                    String className = cls.getNameAsString();
                    
                    if (excludedClasses.contains(className)) return;

                    System.out.println("Class detected : "+className);
                    
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
                    
                    
                    Set<String> associations0toNavoidDupe = new HashSet<String>();

                    System.out.println("\tFields : "+cls.getFields().size());
                    
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

                            // Vérifier si le type est une collection générique
							if (varType instanceof ClassOrInterfaceType) {
								ClassOrInterfaceType classType = (ClassOrInterfaceType) varType;
								String rawType = classType.getNameAsString();

								if (List.of("List", "Set", "Map", "Collection").contains(rawType)) {
									if (classType.getTypeArguments().isPresent() && !classType.getTypeArguments().get().isEmpty()) {
										String genericType = classType.getTypeArguments().get().get(0).toString();
										if (!excludedClasses.contains(genericType)) {
											varInfo.put("generic_type", genericType);
											
											if (classNames.contains(genericType) || enumNames.contains(genericType)) {
												if (associations0toNavoidDupe.contains(genericType)==false)
												{
													associations0toNavoidDupe.add(genericType);
													
														JSONObject relInfo = new JSONObject();
														relInfo.put("name", genericType);
														relInfo.put("relationType", relType._0N.toString()/*"0-N"*/);
														associations.put(relInfo);
													
												}
											}
										}
									}
								}
							}

							if (classNames.contains(typeName) || enumNames.contains(typeName)) {
								JSONObject relInfo = new JSONObject();
								relInfo.put("name", typeName);
								relInfo.put("relationType", relType._11.toString()/*"1-1"*/);
								associations.put(relInfo);
							}

                            
                            classInfo.getJSONArray("variables").put(varInfo);
                        }
                    }

                    System.out.println("\tMethods : "+cls.getMethods().size());

                    for (MethodDeclaration method : cls.getMethods()) {
                    	String returnType = method.getType().toString();
                        if (excludedClasses.contains(returnType)) continue;
                        MethodToJSON(classInfo, method);
                    }

                    classInfo.put("associations", associations);
                    classes.put(className, classInfo);
                });
                

				// Enums
				cu.findAll(EnumDeclaration.class).forEach(enumDecl -> {

					String enumName = enumDecl.getNameAsString();

					if (excludedClasses.contains(enumName))
						return; // Ignorer l'enum si exclue
					System.out.println("\tEnum : "+enumName);

					JSONObject enumInfo = new JSONObject();
					enumInfo.put("type", "enum");
					enumInfo.put("variables", new JSONArray());
					enumInfo.put("methods", new JSONArray());

					JSONArray enumValues = new JSONArray();
					enumDecl.getEntries().forEach(entry -> enumValues.put(entry.getNameAsString()));
					enumInfo.put("values", enumValues);

					for (MethodDeclaration method : enumDecl.getMethods()) {
						MethodToJSON(enumInfo, method);
                    }
					
					
					classes.put(enumName, enumInfo);
					
					
				});
            } catch (IOException | ParseProblemException e) {
                System.out.println("Erreur de parsing dans " + file.getName());
            }
        }
        return classes;
    }

	private static void MethodToJSON(JSONObject enumInfo, MethodDeclaration method) {
		// DUNNO WHY I HAVE DUPLICATED STUFF ... should do that i made a regression somewhere
		if (true)
		{
		  String methodSignature = method.getNameAsString() + method.getType().toString();
		    
		    // Vérifier si la méthode est déjà ajoutée
		    JSONArray methodsArray = enumInfo.getJSONArray("methods");
		    for (int i = 0; i < methodsArray.length(); i++) {
		        JSONObject existingMethod = methodsArray.getJSONObject(i);
		        String existingSignature = existingMethod.getString("name") + existingMethod.getString("returnType");
		        if (existingSignature.equals(methodSignature)) {
		            return; // La méthode existe déjà, on évite de l'ajouter
		        }
		    }
		}
		
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
