package org.dnt.fswf.model;

import org.dnt.fswf.manager.SuperPropertyChangeEvent;
import org.dnt.fswf.manager.SuperPropertyChangeEvent.TypeOfChangeEvent;
import org.dnt.fswf.model.enums.Direction;
import org.dnt.fswf.model.enums.TypeObjetSurFlotteur;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface.gui_type;

public class PanneauSolaire extends ObjetSurFlotteur implements Cloneable {
	
	@XStreamAsAttribute
	double longueur = 4.5;
	@XStreamAsAttribute
	double largeur = 4;

	/*
	 * Angle 0° = panneau plat, 90° panneau vertical.
	 */
	@XStreamAsAttribute
	double angle = 30;
	@XStreamAsAttribute
	double offsetX = 0;
	@XStreamAsAttribute
	double offsetY = 0;
	@XStreamAsAttribute
	double offsetZ = 0;
	@XStreamAsAttribute
	Direction direction = Direction.AVANT;

	@XStreamAsAttribute
	double puissance = 0; // en Watt
	
	
	public PanneauSolaire clone() throws CloneNotSupportedException 
    { 
		PanneauSolaire t = (PanneauSolaire)super.clone(); 
		return t; 
    } 	
	
	public PanneauSolaire(int id) {
		super(id);
		name = "Panneau Solaire " + id;
	}

	public void duplicateParameters(ObjetSurFlotteur iii) {
		super.duplicateParameters(iii);
		PanneauSolaire ii = (PanneauSolaire) iii;
		longueur = ii.longueur;
		largeur = ii.largeur;
		angle = ii.angle;
		offsetX = ii.offsetX;
		offsetY = ii.offsetY;
		offsetZ = ii.offsetZ;
		direction = ii.direction;
		puissance = ii.puissance;
	}
		
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 100)
	public double getLongueur() {
		return longueur;
	}

	public void setLongueur(double longeur) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setLongueur",this.longueur, longeur));
		this.longueur = longeur;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 110)
	public double getLargeur() {
		return largeur;
	}

	public void setLargeur(double largeur) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setLargeur",this.largeur, largeur));
		this.largeur = largeur;
	}
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 120)
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		if (angle>90) angle=90;
		if (angle<0) angle=0;
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setAngle",this.angle, angle));
		this.angle = angle;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 130)
	public double getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(double offsetX) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setOffsetX",this.offsetX, offsetX));
		this.offsetX = offsetX;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 140)
	public double getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(double offsetY) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setOffsetY",this.offsetY, offsetY));
		this.offsetY = offsetY;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 150)
	public double getOffsetZ() {
		return offsetZ;
	}

	public void setOffsetZ(double offsetZ) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setOffsetZ",this.offsetZ, offsetZ));
		this.offsetZ = offsetZ;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, gui_type=gui_type.COMBO, orderDisplay = 160)
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setDirection",this.direction, direction));
		this.direction = direction;
	}

	@Override
	public TypeObjetSurFlotteur getTypeObjetSurFlotteur() {
		return TypeObjetSurFlotteur.PANNEAU_SOLAIRE;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 170)
	public double getPuissance() {
		return puissance;
	}

	public void setPuissance(double puissance) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.puissance, puissance));
		this.puissance = puissance;
	}

	/**
	 * Retourne la valeur de la largeur si on fait la projection sur le sol en fonctionne de l'angle.
	 */
	public double getLargeurEffective() {
		return getLargeur() * Math.cos(Math.toRadians(getAngle()));
	}
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 180)
	public double getHauteur() {
		return getLargeur() * Math.sin(Math.toRadians(getAngle()));
		//return getLargeurEffective() * Math.tan(Math.toRadians(getAngle()));		
	}

	
	
}
