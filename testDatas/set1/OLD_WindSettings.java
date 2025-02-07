package org.dnt.fswf.model;

import java.beans.PropertyChangeSupport;

import org.dnt.fswf.manager.Manager;
import org.dnt.fswf.manager.SuperPropertyChangeEvent;
import org.dnt.fswf.manager.SuperPropertyChangeEvent.TypeOfChangeEvent;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface.gui_type;

public class OLD_WindSettings {

	@XStreamOmitField
	private PropertyChangeSupport pcs = null;

	public PropertyChangeSupport getPcs() {
		if (pcs == null)
		{
			pcs = new PropertyChangeSupport(this);
			pcs.addPropertyChangeListener(Manager.dataChangedObserver);
		}
		return pcs;
	}
	
	public static void main(String args[]) {
		OLD_WindSettings f = new OLD_WindSettings();
		System.err.println("" + f.getQp(1.0));
		System.err.println("" + f.getQp(0.7));
		System.err.println("" + f.getQp(1.15));
	}

	public enum typeCalculVent {
		EuroCode,
		Abaques,
		ASCE7
	}
	
	@XStreamAsAttribute
	typeCalculVent typeCalcul = typeCalculVent.EuroCode;
	// Vitesse base initalle (m/s)
	@XStreamAsAttribute
	double	Vb_init	= 22;
	// Coefficient de direction
	@XStreamAsAttribute
	double	Cdir	= 1;
	// Coefficient de saison
	@XStreamAsAttribute
	double	Cseason	= 1;

	@XStreamAsAttribute
	double angle = 0; // °
	
	/**
	 * 
	 * @param anneFacteur
	 * @return Vitesse de base (m/s)
	 */
	public double getVb(double anneFacteur) {
		return Vb_init * Cdir * Cseason * anneFacteur;
	}

	// Coefficient rugosité
	@XStreamAsAttribute
	double	Cr	= 0.701;
	// Coefficient orographique
	@XStreamAsAttribute
	double	Co	= 1.0;

	/**
	 * 
	 * @param anneFacteur
	 * @return Vitesse moyenne (m/s)
	 */
	public double getVm(double anneFacteur) {
		return Cr * Co * getVb(anneFacteur);
	}

	// Facteur de terrain
	@XStreamAsAttribute
	double	Kr	= 0.19;
	// Coefficient de turbulence
	@XStreamAsAttribute
	double	Ki	= 0.995;

	/**
	 * 
	 * @return ecart-type de la turbulence
	 */
	public double getAv() {
		return Kr * getVb(1.0) * Ki;
	}

	/**
	 * 
	 * @return Intesité de la turbulence
	 */
	public double getIv() {
		return getAv() / getVm(1.0);
	}

	// Densité de l'air
	@XStreamAsAttribute
	double P = 1.225;

	/**
	 * 
	 * @param anneFacteur (1.0 = 50 ans, 0.7 = 2 ans, 1.15 = 500 ans)
	 * @return Pression d'air dynamique
	 */
	public double getQp(double anneFacteur) {
		return (1.0 + 7.0 * getIv()) * (1.0 / 2.0) * P * (getVm(anneFacteur) * getVm(anneFacteur));
	}
	
	// TODO : Je ne suis pas sure que ceci doivent aller ici (mais au dessus, parent) ... mauricio n'as pas été très claire.
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, gui_type = gui_type.COMBO, orderDisplay = 5)
	public typeCalculVent getTypeCalcul() {
		return typeCalcul;
	}

	public void setTypeCalcul(typeCalculVent typeCalcul) {
		this.typeCalcul = typeCalcul;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10)
	public double getVb_init() {
		return Vb_init;
	}

	public void setVb_init(double vb_init) {
		//getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setVb_init",this.Vb_init, vb_init));
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Vb_init, vb_init));
		Vb_init = vb_init;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 20)
	public double getCdir() {
		return Cdir;
	}

	public void setCdir(double cdir) {
		//getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setCdir",this.Cdir, cdir));
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Cdir, cdir));
		Cdir = cdir;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 30)
	public double getCseason() {
		return Cseason;
	}

	public void setCseason(double cseason) {
		//getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setCseason",this.Cseason, cseason));
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Cseason, cseason));
		Cseason = cseason;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 40)
	public double getCr() {
		return Cr;
	}

	public void setCr(double cr) {
		//getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setCr",this.Cr, cr));
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Cr, cr));
		Cr = cr;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 50)
	public double getCo() {
		return Co;
	}

	public void setCo(double co) {
		//getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setCo",this.Co, co));
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Co, co));
		Co = co;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 60)
	public double getKr() {
		return Kr;
	}

	public void setKr(double kr) {
		//getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setKr",this.Kr, kr));
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Kr, kr));
		Kr = kr;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 70)
	public double getKi() {
		return Ki;
	}

	public void setKi(double ki) {
		//getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setKi",this.Ki, ki));
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Ki, ki));
		Ki = ki;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 80)
	public double getP() {
		return P;
	}

	public void setP(double p) {
//		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setP",this.P, p));
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.P, p));
		P = p;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 90)
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.angle, angle));
		this.angle = angle;
	}

	
}
