package org.dnt.fswf.model;

import org.dnt.fswf.manager.AccelerationNeededEvent;
import org.dnt.fswf.manager.SuperPropertyChangeEvent;
import org.dnt.fswf.manager.SuperPropertyChangeEvent.TypeOfChangeEvent;
import org.dnt.fswf.model.enums.TypeFlotteur;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;


//@PROPERTY_MIGLAYOUT(LayoutConstraints = "", ColumnConstraints = "[grow,left][][]", RowConstraints = "[grow][grow][grow][grow][grow]")
public class Flotteur_Triangle extends Flotteur implements Cloneable {

	@XStreamAsAttribute
	private double longueur = 10f;
	@XStreamAsAttribute
	private double largeur = 5f;
	@XStreamAsAttribute
	private double hauteur = 1;
	
	public Flotteur_Triangle clone() throws CloneNotSupportedException 
    { 
		Flotteur_Triangle t = (Flotteur_Triangle)super.clone(); 
		return t; 
    } 	
	
	public Flotteur_Triangle(int id) {
		super(id);
	}
	
	public void duplicateParameters(Flotteur ii) {
		super.duplicateParameters(ii);
		this.longueur = ((Flotteur_Triangle)ii).longueur;
		this.largeur = ((Flotteur_Triangle)ii).largeur;
		this.hauteur = ((Flotteur_Triangle)ii).hauteur;
	}
	
	@Override
	public TypeFlotteur getTypeFlotteur() {
		return TypeFlotteur.TRIANGLE;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 200)
	public double getLongueur() {
		return longueur;
	}

	public void setLongueur(double longueur) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.longueur, longueur));
		getPcs().firePropertyChange(new AccelerationNeededEvent(this));
		this.longueur = longueur;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 210)
	public double getLargeur() {
		return largeur;
	}

	public void setLargeur(double largeur) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setLargeur", this.largeur, largeur));
		getPcs().firePropertyChange(new AccelerationNeededEvent(this));
		
		this.largeur = largeur;
	}
	
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 220)
	public double getHauteur() {
		return hauteur;
	}

	public void setHauteur(double hauteur) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.hauteur, hauteur));
		this.hauteur = hauteur;
	}

	public double getVolume() {
		return (longueur * largeur * hauteur / 2.0);
	}

}
