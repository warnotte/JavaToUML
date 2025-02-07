package org.anast.fowt.DTOs;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;

public class Material

{
    /** Attributes */
    /**
     * 
     */
    public double YoungModulus = 210000; // MPa
    /**
     * 
     */
    public double PoissonRatio = 0.8;
    /**
     * 
     */
    public double YieldStress = 350; // MPa
    public double UltimateStress = 450; // MPa
    /**
     * 
     */
    public String Name = "Steel";
    @PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 40)
	public double getYoungModulus() {
		return YoungModulus;
	}
	public void setYoungModulus(double youngModulus) {
		YoungModulus = youngModulus;
	}
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 50)
	public double getPoissonRatio() {
		return PoissonRatio;
	}
	public void setPoissonRatio(double poissonRatio) {
		PoissonRatio = poissonRatio;
	}
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 20)
	public double getYieldStress() {
		return YieldStress;
	}
	public void setYieldStress(double yieldStress) {
		YieldStress = yieldStress;
	}
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10)
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 30)
	public double getUltimateStress() {
		return UltimateStress;
	}
	public void setUltimateStress(double ultimateStress) {
		UltimateStress = ultimateStress;
	}
	@Override
	public String toString() {
		return "Material [Name=" + Name + "]";
	}
    
    
}

