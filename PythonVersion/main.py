import extract_class_and_vars
import generate_diagram

import zipfile
import os
import json

def list_files(extract_path):
    # Définition du chemin du fichier ZIP et du répertoire d'extraction
    # zip_path = "/mnt/data/Diag.zip"
    #extract_path = "/mnt/data/Diag_extracted"

    # Extraction des fichiers
    #with zipfile.ZipFile(zip_path, 'r') as zip_ref:
    #    zip_ref.extractall(extract_path)

    # Liste des fichiers extraits
    extracted_files = []
    for root, _, files in os.walk(extract_path):
        for file in files:
            if file.endswith(".java"):
                extracted_files.append(os.path.join(root, file))

    # Afficher la liste des fichiers Java extraits
    return extracted_files

# List les fichiers java du repertoire et sous repertoire
files = list_files("C:\\tmp\\Diag\\javaDTOs")

# Extraction des classes et variables
#class_data = extract_class_and_vars.extract_classes_and_variables_with_javalang(files)
excluded_classes = {"Logger", "PropertyChangeSupport", "HashMap"}  # Liste des classes à exclure

class_data = extract_class_and_vars.extract_classes_and_relations(files, excluded_classes)

# Sauvegarde des résultats dans un fichier JSON
with open("extracted_class_data.json", "w", encoding="utf-8") as json_file:
    json.dump(class_data, json_file, indent=4, ensure_ascii=False)

print("Extraction terminée ! Le fichier 'extracted_class_data.json' a été généré.")

# print(class_data)
print(json.dumps(class_data, indent=2))

generate_diagram.generateUML_javalang(class_data, "C:\\tmp\\Diag\\out")
