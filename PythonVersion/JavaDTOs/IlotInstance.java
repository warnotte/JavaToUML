package org.dnt.fswf.model;

import org.dnt.fswf.manager.SuperPropertyChangeEvent;
import org.dnt.fswf.manager.SuperPropertyChangeEvent.TypeOfChangeEvent;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface.gui_type;

public class IlotInstance extends ModelItem {
	
	@XStreamAsAttribute
	String name;
	@XStreamAsAttribute
	float x,y;
	@XStreamAsAttribute
	float angle;
	@XStreamAsAttribute
	int ID_IlotRef;

	public IlotInstance(int id, int ID_Ilotreference) {
		super(id);
		name = "IlotInstance_"+id;
		this.ID_IlotRef = ID_Ilotreference;
	}
	
	public void duplicateParameters(IlotInstance ii) {
		this.angle=ii.angle;
		this.x=ii.x;
		this.y=ii.y;
		this.ID_IlotRef = ii.ID_IlotRef;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setName", this.name, name));
		this.name = name;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 30)
	public float getX() {
		return x;
	}

	public void setX(float x) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setX", this.x, x));
		this.x = x;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 40)
	public float getY() {
		return y;
	}

	public void setY(float y) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setY", this.y, y));
		this.y = y;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 50)
	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setAngle", this.angle, angle));
		this.angle = angle;
	}

	//@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 50)
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 20, gui_type=gui_type.COMBO)
	public int getID_IlotRef() {
		return ID_IlotRef;
	}

	public void setID_IlotRef(int iD_IlotRef) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setID_IlotRef", this.ID_IlotRef, iD_IlotRef));
		ID_IlotRef = iD_IlotRef;
	}

	
	
}
