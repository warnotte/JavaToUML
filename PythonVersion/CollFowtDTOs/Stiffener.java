package org.anast.fowt.DTOs;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface.gui_type;

public class Stiffener
{
	/** Attributes */
	/**
	 * 
	 */
	public double wh = 0.2;
	/**
	 * 
	 */
	public double wt = 0.05;
	/**
	 * 
	 */
	public double fh = 0.2;
	/**
	 * 
	 */
	public double ft = 0.05;
	
	//@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=10, displayFormat="0.0000 (m)", editFormat="0.0000",gui_type = gui_type.DECIMALFORMATTEDTEXTFIELD)
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=10)
	public double getWh() {
		return wh;
	}
	public void setWh(double wh) {
		this.wh = wh;
	}
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=20)
	public double getWt() {
		return wt;
	}
	
	public void setWt(double wt) {
		this.wt = wt;
	}
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=30)
	public double getFh() {
		return fh;
	}
	
	public void setFh(double fh) {
		this.fh = fh;
	}
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=40)
	public double getFt() {
		return ft;
	}
	
	public void setFt(double ft) {
		this.ft = ft;
	}
	
	
	
}
