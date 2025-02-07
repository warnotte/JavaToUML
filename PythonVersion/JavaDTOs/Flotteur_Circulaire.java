package org.dnt.fswf.model;

import org.dnt.fswf.manager.AccelerationNeededEvent;
import org.dnt.fswf.manager.SuperPropertyChangeEvent;
import org.dnt.fswf.manager.SuperPropertyChangeEvent.TypeOfChangeEvent;
import org.dnt.fswf.model.enums.TypeFlotteur;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;


//@PROPERTY_MIGLAYOUT(LayoutConstraints = "", ColumnConstraints = "[grow,left][][]", RowConstraints = "[grow][grow][grow][grow][grow]")
public class Flotteur_Circulaire extends Flotteur implements Cloneable {

	@XStreamAsAttribute
	private double rayon = 2f;
	@XStreamAsAttribute
	private double largeur = 5f;
	
	public Flotteur_Circulaire clone() throws CloneNotSupportedException 
    { 
		Flotteur_Circulaire t = (Flotteur_Circulaire)super.clone(); 
		return t; 
    } 	
	
	public Flotteur_Circulaire(int id) {
		super(id);
	}
	
	public void duplicateParameters(Flotteur ii) {
		super.duplicateParameters(ii);
		this.rayon = ((Flotteur_Circulaire)ii).rayon;
		this.largeur = ((Flotteur_Circulaire)ii).largeur;
	}
	

	@Override
	public TypeFlotteur getTypeFlotteur() {
		return TypeFlotteur.CIRCULAIRE;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 200)
	public double getRayon() {
		return rayon;
	}

	public void setRayon(double rayon) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setRayon", this.rayon, rayon));
		getPcs().firePropertyChange(new AccelerationNeededEvent(this));

		this.rayon = rayon;
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
	

	public double getVolume() {
		return largeur * Math.PI * (rayon * rayon);
	}

	@Override
	/**
	 * Retourne la Longueur calculée vu du dessus. Attention avec cette méthode, c'est pour le visuel.
	 */
	public double getLongueur() {
		return rayon*2;
	}
	
}
