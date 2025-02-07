package org.anast.fowt.DTOs;
import java.util.ArrayList;
import java.util.List;

import io.github.warnotte.obj2gui2.PROPERTY_FIELD_LISTABLE;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;

public class Turbine

{
    /** Attributes */
    /**
     * 
     */
	@PROPERTY_FIELD_LISTABLE
    public List<TurbineSection> sections = new ArrayList<>();
	
    /**
     * 
     */
    public double rotorandblademass;
    
	public List<TurbineSection> getSections() {
		return sections;
	}
	public void setSections(List<TurbineSection> sections) {
		this.sections = sections;
	}
	
	
	/**
	 * @return the rotorandblademass
	 */
	//@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=10)
	public double getRotorandblademass()
	{
		return rotorandblademass;
	}
	/**
	 * @param rotorandblademass the rotorandblademass to set
	 */
	public void setRotorandblademass(double rotorandblademass)
	{
		this.rotorandblademass = rotorandblademass;
	
	}
	@Override
	public String toString() {
		return "Turbine";
	}
    
    
}

