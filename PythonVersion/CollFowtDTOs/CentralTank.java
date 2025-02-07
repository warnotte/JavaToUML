package org.anast.fowt.DTOs;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;

public class CentralTank extends TankSection
{
	public double		BallastMass;
	
	public CentralTank()
	{
		setDiameter(8);
		setHeight(27);
	}

	/**
	 * @return the ballastMass
	 */
	//@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=60)
	public double getBallastMass()
	{
		return BallastMass;
	}

	/**
	 * @param ballastMass the ballastMass to set
	 */
	public void setBallastMass(double ballastMass)
	{
		BallastMass = ballastMass;
	
	}
	
	
}
