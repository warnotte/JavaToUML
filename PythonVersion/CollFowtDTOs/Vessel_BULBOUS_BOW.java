/**
 * 
 */
package org.anast.fowt.DTOs;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;

/**
 * @author Warnotte Renaud
 *
 */
public class Vessel_BULBOUS_BOW extends VesselBase
{

	double BulbRadiusX = 0.0 ; // X 
	double BulbRadiusY = 0.0 ; // Y
	double BulbRadiusZ = 0.0 ; // Z
	
	// Constructeur sans argument
	public Vessel_BULBOUS_BOW()
	{
		
	}
	
	/**
	 * @param height
	 * @param steamRadius1
	 * @param steamRadius2
	 * @param stemAngle
	 * @param sideAngle
	 * @param mass
	 * @param velocity
	 */
	
	// Constructeur avec arguments
	public Vessel_BULBOUS_BOW(double height, double stemRadius1, double stemRadius2, double stemAngle, double sideAngle, double mass, double velocity, double bulbRadiusX, double bulbRadiusY, double bulbRadiusZ)
	{
		super(height, stemRadius1, stemRadius2, stemAngle, sideAngle, mass, velocity);
		BulbRadiusX = bulbRadiusX;
		BulbRadiusY = bulbRadiusY;
		BulbRadiusZ = bulbRadiusZ;
	}

	/**
	 * @return the bulbRadius1 en X
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=6)
	public synchronized double getBulbRadiusX()
	{
		return BulbRadiusX;
	}

	/**
	 * @param bulbRadius1 the bulbRadius1 en X to set
	 */
	public synchronized void setBulbRadiusX(double bulbRadiusX)
	{
		BulbRadiusX = bulbRadiusX;
	
	}

	/**
	 * @return the bulbRadius2 en Y
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=7)
	public synchronized double getBulbRadiusY()
	{
		return BulbRadiusY;
	}

	/**
	 * @param bulbRadius2 the bulbRadius2 en Y to set
	 */
	public synchronized void setBulbRadiusY(double bulbRadiusY)
	{
		BulbRadiusY = bulbRadiusY;
	
	}

	/**
	 * @return the bulbRadius3 en Z
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=8)
	public synchronized double getBulbRadiusZ()
	{
		return BulbRadiusZ;
	}

	/**
	 * @param bulbRadius3 the bulbRadius3 en Z to set
	 */
	public synchronized void setBulbRadiusZ(double bulbRadiusZ)
	{
		BulbRadiusZ = bulbRadiusZ;
	
	}

	
}
