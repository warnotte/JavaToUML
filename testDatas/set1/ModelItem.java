package org.dnt.fswf.model;

import java.beans.PropertyChangeSupport;

import org.dnt.fswf.manager.Manager;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import io.github.warnotte.obj2gui2.Identifiable;

public class ModelItem implements Identifiable, Cloneable {
	
	@XStreamAsAttribute
	int id;
	
	@XStreamOmitField
	private PropertyChangeSupport pcs;
	
	private ModelItem() {
	}
	
	public ModelItem(int id) {
		this.id=id;
	}
	
	public PropertyChangeSupport getPcs() {
		if (pcs == null)
		{
			pcs = new PropertyChangeSupport(this);
			pcs.addPropertyChangeListener(Manager.dataChangedObserver);
		}
		return pcs;
	}

	@Override
	public int getId() {
		return id;
	}
	@Override
	public void setId(int id) {
		this.id=id;
		
	}

	public Object clone() throws CloneNotSupportedException 
    { 
        ModelItem t = (ModelItem)super.clone(); 
		return t; 
    } 
}
