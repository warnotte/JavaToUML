package org.dnt.fswf.model;

import java.beans.PropertyChangeSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dnt.fswf.manager.Manager;
import org.dnt.fswf.manager.SuperPropertyChangeEvent;
import org.dnt.fswf.manager.SuperPropertyChangeEvent.TypeOfChangeEvent;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface.gui_type;

public class WindSettings {

	protected static final Logger logger = LogManager.getLogger(WindSettings.class);

	@XStreamOmitField
	private PropertyChangeSupport pcs = null;

	public PropertyChangeSupport getPcs() {
		if (pcs == null) {
			pcs = new PropertyChangeSupport(this);
			pcs.addPropertyChangeListener(Manager.dataChangedObserver);
		}
		return pcs;
	}

	public static void main(String args[]) {
		WindSettings f = new WindSettings();
		System.err.println("" + f.getQp(1.0));
		System.err.println("" + f.getQp(0.7));
		System.err.println("" + f.getQp(1.15));
	}

	public enum typeCalculVent {
		EuroCode, Abaques, ASCE7
	}

	@XStreamAsAttribute
	typeCalculVent typeCalcul = typeCalculVent.EuroCode;
	// Vitesse base initalle (m/s)
	@XStreamAsAttribute
	double Vb_init = 22;
	// Coefficient de direction
	@XStreamAsAttribute
	double Cdir = 1;
	// Coefficient de saison
	@XStreamAsAttribute
	double Cseason = 1;

	// Longueur de rugosité de terrain (Voir tableau 5)
	@XStreamAsAttribute
	double Zo = 0.01;
	// Categorie de térrain II (Valeur fixée par défaut)
	@XStreamAsAttribute
	double Zo2 = 0.05;

	// Hauteur
	@XStreamAsAttribute
	double Z = 1;
	// Hauteur minimal (Voir Tableau 5)
	@XStreamAsAttribute
	double Zmin = 2;
	// Valeur fixée par défaut
	@XStreamAsAttribute
	double Zmax = 200;

	// Coefficient rugosité
	// @XStreamAsAttribute
	// double Cr = 0.701;
	// Coefficient orographique
	@XStreamAsAttribute
	double Co = 1.0;
	//// Facteur de terrain
	// @XStreamAsAttribute
	// double Kr = 0.19;
	// Coefficient de turbulence
	@XStreamAsAttribute
	double Kl = 1.00;
	// Densité de l'air
	@XStreamAsAttribute
	double P = 1.225;

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

	/**
	 * 
	 * @return ecart-type de la turbulence
	 */
	/*
	 * public double getAv() { return 0;//Kr * getVb(1.0) * Kl; }
	 */

	/**
	 * 
	 * @param anneFacteur
	 * @return Vitesse moyenne (m/s)
	 */
	public double getVm(double anneFacteur) {
		return getCr() * Co * getVb(anneFacteur);
	}


	/**
	 * 
	 * @param anneFacteur (1.0 = 50 ans, 0.7 = 2 ans, 1.15 = 500 ans)
	 * @return Pression d'air dynamique
	 */
	public double getQp(double anneFacteur) {
		return (1.0 + 7.0 * getIv()) * (1.0 / 2.0) * P * (getVm(anneFacteur) * getVm(anneFacteur));
	}

	// TODO : Je ne suis pas sure que ceci doivent aller ici (mais au dessus, parent) ... mauricio n'as pas été très claire.
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, gui_type = gui_type.COMBO, orderDisplay = 10)
	public typeCalculVent getTypeCalcul() {
		return typeCalcul;
	}

	public void setTypeCalcul(typeCalculVent typeCalcul) {
		this.typeCalcul = typeCalcul;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 20)
	public double getVb_init() {
		return Vb_init;
	}

	public void setVb_init(double vb_init) {
		// getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this,
		// "setVb_init",this.Vb_init, vb_init));
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Vb_init, vb_init));
		Vb_init = vb_init;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 30)
	public double getCdir() {
		return Cdir;
	}

	public void setCdir(double cdir) {
		// getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this,
		// "setCdir",this.Cdir, cdir));
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Cdir, cdir));
		Cdir = cdir;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 40)
	public double getCseason() {
		return Cseason;
	}

	public void setCseason(double cseason) {
		// getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this,
		// "setCseason",this.Cseason, cseason));
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Cseason, cseason));
		Cseason = cseason;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 50)
	public double getZ() {
		return Z;
	}

	public void setZ(double z) {
		if (z > Zmax)
			z = Zmax;
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Z, z));
		Z = z;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 60)
	public double getZmin() {
		return Zmin;
	}

	public void setZmin(double zmin) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Zmin, zmin));
		Zmin = zmin;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 70)
	public double getZo() {
		return Zo;
	}

	public void setZo(double zo) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Zo, zo));
		Zo = zo;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 80)
	public double getZmax() {
		return Zmax;
	}

	public void setZmax(double zmax) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Zmax, zmax));
		Zmax = zmax;
		if (Z > Zmax)
			Z = Zmax;

	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 90)
	public double getZo2() {
		return Zo2;
	}

	public void setZo2(double zo2) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Zo2, zo2));
		Zo2 = zo2;
	}

	/**
	 * Facteur de terrain calculé
	 * 
	 * @return le facteur de terrain
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 100)
	public double getKr() {
		return 0.19 * Math.pow((Zo / Zo2), 0.07);
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 110)
	public double getCr() {
		if (Z < Zmin) {
			return getKr() * Math.log(Zmin / Zo);
		} else if ((Z >= Zmin) && (Z <= Zmax)) {
			return getKr() * Math.log(Z / Zo);
		} else {
			logger.fatal("This should not happens in getCr()");
			return -1;
		}
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 130)
	public double getCo() {
		return Co;
	}

	public void setCo(double co) {
		// getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setCo",this.Co,
		// co));
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Co, co));
		Co = co;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 140)
	public double getKl() {
		return Kl;
	}

	public void setKl(double kl) {
		// getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setKi",this.Ki,
		// ki));
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.Kl, kl));
		Kl = kl;
	}


	/**
	 * 
	 * @return Intesité de la turbulence
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 150)
	public double getIv() {

		// B42/(B35*LN(B44/B43))
		return getKl() / (getCo() * Math.log(getZ() / Zo));
	}
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 160)
	public double getP() {
		return P;
	}

	public void setP(double p) {
//		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setP",this.P, p));
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.P, p));
		P = p;
	}

	// TODO : Ceci devrait retster ici ou aller dans paramatres de simulation ??
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 170)
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.angle, angle));
		this.angle = angle;
	}

}
