package org.anast.fowt.DTOs;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;

public class TurbineSection

{
	/** Attributes */
    /**
     * 
     */
    public double LowerDiameter;
    /**
     * 
     */
    public double UpperDiameter;
    /**
     * 
     */
    public double Length;
    /**
     * 
     */
    public double Thickness;
    
    
    
    /**
	 * @param lowerDiameter
	 * @param upperDiameter
	 * @param length
	 * @param thickness
	 */
	public TurbineSection(double lowerDiameter, double upperDiameter, double length, double thickness)
	{
		super();
		LowerDiameter = lowerDiameter;
		UpperDiameter = upperDiameter;
		Length = length;
		Thickness = thickness;
	}
	/**
	 * 
	 */
	public TurbineSection()
	{
		LowerDiameter = 8;
		UpperDiameter = 8;
		Length = 4;
		Thickness = 0.1;
	}
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE)
	public double getLowerDiameter() {
		return LowerDiameter;
	}
	public void setLowerDiameter(double lowerDiameter) {
		LowerDiameter = lowerDiameter;
	}
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE)
	public double getUpperDiameter() {
		return UpperDiameter;
	}
	public void setUpperDiameter(double upperDiameter) {
		UpperDiameter = upperDiameter;
	}
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE)
	public double getLength() {
		return Length;
	}
	public void setLength(double length) {
		Length = length;
	}
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE)
	public double getThickness() {
		return Thickness;
	}
	public void setThickness(double thickness) {
		Thickness = thickness;
	}

    

    @Override
	public String toString()
	{
    	//return String.format("TurbineSection [LowerDiameter=%s, UpperDiameter=%s, Length=%s, Thickness=%s]", LowerDiameter, UpperDiameter, Length, Thickness);
    	return String.format("TurbineSection");
	}
}

