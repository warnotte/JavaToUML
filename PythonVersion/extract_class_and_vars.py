import re

import javalang

def extract_classes_and_relations(file_paths, excluded_classes = {}):
    classes = {}

    # Étape 1 : Identifier toutes les classes et enums avant d'extraire les relations
    class_names = set()
    enum_names = set()

    for file_path in file_paths:
        with open(file_path, "r", encoding="utf-8") as file:
            content = file.read()
        try:
            tree = javalang.parse.parse(content)

            # Trouver les classes
            for _, node in tree.filter(javalang.tree.ClassDeclaration):
                class_names.add(node.name)

            # Trouver les enums
            for _, node in tree.filter(javalang.tree.EnumDeclaration):
                enum_names.add(node.name)

        except javalang.parser.JavaSyntaxError:
            print(f"Erreur de syntaxe dans {file_path}, ignoré.")

    # Étape 2 : Extraire les relations et variables
    for file_path in file_paths:
        with open(file_path, "r", encoding="utf-8") as file:
            content = file.read()

        try:
            tree = javalang.parse.parse(content)

            # Traitement des classes normales
            for _, node in tree.filter(javalang.tree.ClassDeclaration):
                class_name = node.name
                classes[class_name] = {
                    "type": "class",
                    "variables": [],
                    "extends": node.extends.name if node.extends and node.extends.name not in excluded_classes else None,
                    "implements": [impl.name for impl in (node.implements or []) if impl.name not in excluded_classes],
                    "associations": set()
                }

                # Extraction des variables classiques
                for field in node.fields:
                    for declarator in field.declarators:
                        var_name = declarator.name
                        var_type = field.type.name if hasattr(field.type, 'name') else str(field.type)
                        
                        # Vérifier si le type est à exclure
                        if var_type in excluded_classes:
                            continue  # Ignorer cette variable

                        if (len(field.type.dimensions)==1):
                            var_type = var_type+"[]"
                        if (len(field.type.dimensions)==2):
                            var_type = var_type+"[][]"
                            
                        # Vérifier si c'est une référence directe à une autre classe ou un enum
                        if var_type in class_names or var_type in enum_names:
                            classes[class_name]["associations"].add((var_type, var_name))

                        # Ajouter la variable à la classe
                        classes[class_name]["variables"].append((var_type, var_name))

                # Détection améliorée des `List<T>` avec un set pour éviter les doublons
                list_matches = re.findall(r"(?:private|protected|public)?\s*List\s*<\s*(\w+)\s*>\s+(\w+)", content)
                for contained_type, list_var_name in list_matches:
                    if contained_type in class_names and contained_type not in excluded_classes:  
                        classes[class_name]["associations"].add((contained_type, f"List<{contained_type}>"))

            # Détection des enums et extraction manuelle des valeurs
            for _, node in tree.filter(javalang.tree.EnumDeclaration):
                enum_name = node.name

                # Trouver les constantes d'enum via une regex
                enum_match = re.search(rf"enum\s+{enum_name}\s*\{{([^}}]+)\}}", content, re.MULTILINE)
                if enum_match:
                    constants = [const.strip() for const in enum_match.group(1).split(",") if const.strip()]
                else:
                    constants = []

                classes[enum_name] = {
                    "type": "enum",
                    "variables": [("EnumValue", constant) for constant in constants],  # Ajouter les valeurs de l'enum
                    "extends": None,
                    "implements": [],
                    "associations": set()
                }

            # Convertir les sets en listes pour la compatibilité JSON
            for class_name in classes:
                classes[class_name]["associations"] = list(classes[class_name]["associations"])

        except javalang.parser.JavaSyntaxError:
            print(f"Erreur de syntaxe dans {file_path}, ignoré.")

    return classes

