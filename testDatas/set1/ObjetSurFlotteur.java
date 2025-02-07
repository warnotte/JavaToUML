package org.dnt.fswf.model;

import java.beans.PropertyChangeSupport;

import org.dnt.fswf.manager.Manager;
import org.dnt.fswf.manager.SuperPropertyChangeEvent;
import org.dnt.fswf.manager.SuperPropertyChangeEvent.TypeOfChangeEvent;
import org.dnt.fswf.model.enums.TypeObjetSurFlotteur;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;

public abstract class ObjetSurFlotteur extends ModelItem implements Cloneable {
	
	
	@XStreamAsAttribute
	String name;
	@XStreamAsAttribute
	double poids = 1;

	@XStreamOmitField
	private PropertyChangeSupport pcs;
	
	public ObjetSurFlotteur(int id) {
		super(id);
		this.name = "ObjetSurFlotteur "+id;
	}
	
	public ObjetSurFlotteur clone() throws CloneNotSupportedException 
    { 
		ObjetSurFlotteur t = (ObjetSurFlotteur)super.clone(); 
		return t; 
    } 	
	
	public PropertyChangeSupport getPcs() {
		if (pcs == null)
		{
			pcs = new PropertyChangeSupport(this);
			pcs.addPropertyChangeListener(Manager.dataChangedObserver);
		}
		return pcs;
	}
	
	public void duplicateParameters(ObjetSurFlotteur ii) {
		name = ii.name + " DUP";
		poids = ii.poids;
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
	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.poids, poids));
		this.poids = poids;
	}

	
	public abstract TypeObjetSurFlotteur getTypeObjetSurFlotteur();

	
}
