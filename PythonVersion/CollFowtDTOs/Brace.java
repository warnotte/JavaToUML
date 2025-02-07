package org.anast.fowt.DTOs;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_FIELD_XXXABLE;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;

public class Brace
{
	/** Attributes */
	/**
	 * 
	 */
	//public boolean TankTankOrTankTurbine;
	/**
	 * 
	 */
	//public boolean HorizontalOrVertical;
	/**
	 * 
	 */
	public double Diameter = 2.5;
	/**
	 * 
	 */
	public double Thickness = 0.1;
	
	public double z = 25; // position depart
	//public double z1; // position arrivée
	
	
	//@PROPERTY_FIELD_XXXABLE
    //public HorizontalStiffening frameStiffeners = new HorizontalStiffening();
    
    //@PROPERTY_FIELD_XXXABLE
    //public VerticalStiffening girderStiffeners = new VerticalStiffening();
    
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE)
	public double getDiameter() {
		return Diameter;
	}

	public void setDiameter(double diameter) {
		Diameter = diameter;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE)
	public double getThickness() {
		return Thickness;
	}

	public void setThickness(double thickness) {
		Thickness = thickness;
	}

	
	/**
	 * @return the z
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE)
	public double getZ()
	{
		return z;
	}


	/**
	 * @param z0 the z0 to set
	 */
	public void setZ(double z)
	{
		this.z = z;
	
	}

	

	@Override
	public String toString() {
		return "Brace";
				
		//return "Brace [TankTankOrTankTurbine=" + TankTankOrTankTurbine + ", Diameter=" + Diameter + ", Thickness="
		//+ Thickness + ", z0=" + z0 + ", z1=" + z1 + "]";
	}
	
	

}
