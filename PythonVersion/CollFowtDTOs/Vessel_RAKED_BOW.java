/**
 * 
 */
package org.anast.fowt.DTOs;

/**
 * @author Warnotte Renaud
 */
public class Vessel_RAKED_BOW extends VesselBase
{
	// Constructeur sans argument
	public Vessel_RAKED_BOW()
	{
		super();
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
	public Vessel_RAKED_BOW(double height, double stemRadius1, double stemRadius2, double stemAngle, double sideAngle, double mass, double velocity)
	{
		super(height, stemRadius1, stemRadius2, stemAngle, sideAngle, mass, velocity);
	}

}
