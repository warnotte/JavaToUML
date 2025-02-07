import graphviz

# Correction des labels pour éviter les erreurs de formatage
def sanitize_label(text):
    """Remplace les caractères problématiques pour Graphviz."""
    return text.replace("<", "&lt;").replace(">", "&gt;")

def generateUML_javalang(class_data_final, outputfile):
    # Création du diagramme UML basé sur les données de javalang
    uml_final = graphviz.Digraph(format="png")
    uml_final.attr(rankdir="TB")

    # Ajout des classes et enums avec leurs variables
    for class_name, details in class_data_final.items():
        label = f"{class_name}|"

        # Vérification si c'est un enum (pas d'héritage, pas d'implémentation et pas d'association)
        is_enum = False #details["extends"] is None and not details["implements"] and not details["associations"]

        if (details["type"]=="enum"):
            is_enum = True

        for var_type, var_name in details["variables"]:
            if is_enum:
                label += f"{sanitize_label(var_name)}\\l"  # Juste les valeurs pour un enum
            else:
                label += f"{sanitize_label(var_name)}: {sanitize_label(var_type)}\\l"

        #uml_final.node(class_name, shape="record", label="{" + label + "}", color="red")
        uml_final.node(class_name, shape="record", label="{" + label + "}")

    # Ajout des relations d'héritage (extends)
    for class_name, details in class_data_final.items():
        if details["extends"]:
            uml_final.edge(details["extends"], class_name, arrowhead="empty")

    # Ajout des relations d'implémentation (implements)
    for class_name, details in class_data_final.items():
        for interface in details["implements"]:
            uml_final.edge(interface, class_name, style="dashed")

    # Ajout des relations d'association (listes et objets)
    for class_name, details in class_data_final.items():
        for related_class, var_name in details["associations"]:
            uml_final.edge(class_name, related_class, arrowhead="vee")


    # Sauvegarde et affichage du diagramme UML mis à jour
    uml_javalang_path = outputfile;#"/mnt/data/class_diagram"
    uml_final.render(uml_javalang_path)

