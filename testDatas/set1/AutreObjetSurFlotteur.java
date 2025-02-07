package org.dnt.fswf.model;

import org.dnt.fswf.model.enums.TypeObjetSurFlotteur;

public class AutreObjetSurFlotteur extends ObjetSurFlotteur {

	
	public AutreObjetSurFlotteur clone() throws CloneNotSupportedException 
    { 
		AutreObjetSurFlotteur t = (AutreObjetSurFlotteur)super.clone(); 
		return t; 
    } 	
	
	public AutreObjetSurFlotteur(int id) {
		super(id);
		name = "Autre objet " + id;
	}

	public void duplicateParameters(ObjetSurFlotteur iii) {
		super.duplicateParameters(iii);
		AutreObjetSurFlotteur ii = (AutreObjetSurFlotteur) iii;
	}
	
	@Override
	public TypeObjetSurFlotteur getTypeObjetSurFlotteur() {
		return TypeObjetSurFlotteur.AUTRES;
	}

}
