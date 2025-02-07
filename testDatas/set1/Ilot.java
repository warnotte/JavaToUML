package org.dnt.fswf.model;

public class Ilot extends MapModelItem {

	//@XStreamAsAttribute
	//Axe axePanneauSolaire = Axe.Y;

	// On avait dit qu'on supprimer les differente classe de flotteur mais le probleme c'est que Prism et Triangle sont les même ils ont L, l, h mais pas le circulaire qui est une L et un rayon mais pas un l implicite = au rayon
	//@XStreamAsAttribute
	//TypeFlotteur typeFlotteur = TypeFlotteur.PRISMATIQUE;
	
	public Ilot clone() throws CloneNotSupportedException 
    { 
		Ilot t = (Ilot)super.clone(); 
		return t; 
    } 
	
	public Ilot(int id, int nbrItemX, int nbrItemY) {
		super(id, nbrItemX, nbrItemY);
		name = "Ilot_"+id;
	}
	
	public void duplicateParameters(Ilot ii) {
		super.duplicateParameters(ii);
	//	this.axePanneauSolaire = ii.axePanneauSolaire;
		//this.typeFlotteur = ii.typeFlotteur;
	}
	

	@Override
	public String toString() {
		return id+") "+name;
	}
	/*
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, gui_type=gui_type.COMBO,  orderDisplay = 20)
	public Axe getAxePanneauSolaire() {
		return axePanneauSolaire;
	}

	public void setAxePanneauSolaire(Axe axePanneauSolaire) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setAxePanneauSolaire", this.axePanneauSolaire, axePanneauSolaire));
		this.axePanneauSolaire = axePanneauSolaire;
	}
	*/
	/*
 
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, gui_type=gui_type.COMBO,  orderDisplay = 30)
	public TypeFlotteur getTypeFlotteur() {
		return typeFlotteur;
	}

	public void setTypeFlotteur(TypeFlotteur typeFlotteur) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.typeFlotteur, typeFlotteur));
		this.typeFlotteur = typeFlotteur;
	}
	*/

}
