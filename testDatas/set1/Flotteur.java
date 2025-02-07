package org.dnt.fswf.model;

import org.dnt.fswf.manager.SuperPropertyChangeEvent;
import org.dnt.fswf.manager.SuperPropertyChangeEvent.TypeOfChangeEvent;
import org.dnt.fswf.model.enums.TypeFlotteur;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface.gui_type;


//@PROPERTY_MIGLAYOUT(LayoutConstraints = "", ColumnConstraints = "[grow,left][][]", RowConstraints = "[grow][grow][grow][grow][grow]")
public abstract class Flotteur extends ModelItem implements Cloneable {

	
	@XStreamAsAttribute
	private String name = "Unnamed_" + getId();
	@XStreamAsAttribute
	private String fabricant = "Fabricant_" + getId();
	@XStreamAsAttribute
	private String famille = "Famille_" + getId();
	@XStreamAsAttribute
	private int serie = 1;
	
	@XStreamAsAttribute
	private double poids = 10; // En Kg
	@XStreamAsAttribute
	private double poidsAdditionel = 10; // En Kg
	
	@XStreamAsAttribute
	private int ID_ObjetSurFlotteur = -1;
		
	@XStreamAsAttribute
	private float rayonConnecteur = 0.5f;
	
	public Flotteur clone() throws CloneNotSupportedException 
    { 
		Flotteur t = (Flotteur)super.clone(); 
		return t; 
    } 	
	
	public Flotteur(int id) {
		super(id);
		name = "Flotteur " + id;
	}
	
	public void duplicateParameters(Flotteur ii) {
		this.name = ii.name + " DUP";
		this.fabricant = ii.fabricant;
		this.famille = ii.famille;
		this.serie = ii.serie;
		this.ID_ObjetSurFlotteur = ii.ID_ObjetSurFlotteur;
		this.poids = ii.poids;
		this.poidsAdditionel = ii.poidsAdditionel;
		this.rayonConnecteur = ii.rayonConnecteur;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.name, name));
		this.name = name;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 20)
	public String getFabricant() {
		return fabricant;
	}

	public void setFabricant(String fabricant) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setFabricant",this.fabricant, fabricant));
		this.fabricant = fabricant;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 30)
	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setFamille",this.famille, famille));
		this.famille = famille;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 40)
	public int getSerie() {
		return serie;
	}

	public void setSerie(int serie) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setSerie",this.serie, serie));
		this.serie = serie;
	}


	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 50)
	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.poids, poids));
		this.poids = poids;
	}
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 60)
	public double getPoidsAdditionel() {
		return poidsAdditionel;
	}

	public void setPoidsAdditionel(double poidsAdditionel) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.poidsAdditionel, poidsAdditionel));
		this.poidsAdditionel = poidsAdditionel;
	}
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE,orderDisplay = 70)
	public double getPoidsTotal() {
		return poids+poidsAdditionel;
	}	

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, gui_type=gui_type.COMBO,  orderDisplay = 80)
	public int getID_ObjetSurFlotteur() {
		return ID_ObjetSurFlotteur;
	}

	public void setID_ObjetSurFlotteur(int panneauSolaire2) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.ID_ObjetSurFlotteur, ID_ObjetSurFlotteur));
		this.ID_ObjetSurFlotteur = panneauSolaire2;
	}


	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 90)
	public float getRayonConnecteur() {
		return rayonConnecteur;
	}

	public void setRayonConnecteur(float rayonConnecteur) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.rayonConnecteur, rayonConnecteur));
		this.rayonConnecteur = rayonConnecteur;
	}
	
	@Override
	public String toString() {
		return "Flotteur [name=" + name + ", fabricant=" + fabricant + ", famille=" + famille + ", serie=" + serie + ", ID_PanneauSolaire=" + ID_ObjetSurFlotteur + "]";
	}

	public void unassigneObjetSurFlotteur()
	{
		setID_ObjetSurFlotteur(-1);
	}

	public boolean isObjetSurFlotteur() {
		if (ID_ObjetSurFlotteur==-1)
			return false;
		return true;
	}

	/**
	 * Methode qui permet de retourner le volume du flotteur
	 */
	public abstract double getVolume();
	
	/**
	 * Retourne la largeur (cad, en vue du dessus, la dimension en Y)
	 * @return
	 */
	public abstract double getLargeur();
	
	/**
	 * Retourne la largeur (cad, en vue du dessus, la dimension en X)
	 * @return
	 */
	public abstract double getLongueur();
	
	public abstract TypeFlotteur getTypeFlotteur();
}
