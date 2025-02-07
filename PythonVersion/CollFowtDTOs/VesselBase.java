/**
 * 
 */
package org.anast.fowt.DTOs;

import org.anast.fowt.main.Manager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;

/**
 * @author Warnotte Renaud
 *
 */
public abstract class VesselBase
{
	
	protected static final Logger Logger = LogManager.getLogger("VesselBase");
	
	private double Height = 23.0; 		// Height hb (m)
	private double StemRadiusQ = 20.0; 	// q (m)
	private double StemRadiusP = 8.0; 	// p (m)
	private double StemAngle = 75.0; 	// phi (deg)
	private double SideAngle = 80.0; 	// psi (deg)
	private double Mass = 5.0E7; 		// Mass (tons)
	private double Velocity = 0.5;		// Velocity (m/s)
	private double distanceFromBowToCOG_X = 35;
	private double distanceFromBowToCOG_Z = 15;
	
	private double draft = 18.0;
	
	// Constructeur sans arguments
	public VesselBase()
	{
		
	}
	
	/**
	 * @param height
	 * @param stemRadius1
	 * @param stemRadius2
	 * @param stemAngle
	 * @param sideAngle
	 * @param mass
	 * @param velocity
	 */
	
	// Constructeur avec arguments
	public VesselBase(double height, double stemRadius1, double stemRadius2, double stemAngle, double sideAngle, double mass, double velocity)
	{
		super();
		Height = height;
		StemRadiusQ = stemRadius1;
		StemRadiusP = stemRadius2;
		StemAngle = stemAngle;
		SideAngle = sideAngle;
		Mass = mass;
		Velocity = velocity;
	}

	/**
	 * @return the height
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=3)
	public synchronized double getHeight()
	{
		return Height;
	}

	/**
	 * @param height the height to set
	 */
	public synchronized void setHeight(double height)
	{
		if (height<=0)
		{
			Logger.error("Ship height cannot be <= 0");
			return;
		}
		Height = height;
	
	}

	/**
	 * @return the steamRadius1 -> q
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=4)
	public synchronized double getStemRadiusQ()
	{
		return StemRadiusQ;
	}

	/**
	 * @param steamRadius1 the steamRadius1 to set -> q
	 */
	public synchronized void setStemRadiusQ(double stemRadius1)
	{
		StemRadiusQ = stemRadius1;
	}

	/**
	 * @return the steamRadius2 -> p
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=5)
	public synchronized double getStemRadiusP()
	{
		return StemRadiusP;
	}

	/**
	 * @param steamRadius2 the steamRadius2 to set -> p
	 */
	public synchronized void setStemRadiusP(double stemRadius2)
	{
		StemRadiusP = stemRadius2;
	}

	/**
	 * @return the stemAngle phi
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=9)
	public synchronized double getStemAngle()
	{
		return StemAngle;
	}

	/**
	 * @param stemAngle the stemAngle to set phi
	 */
	public synchronized void setStemAngle(double stemAngle)
	{
		if (stemAngle<10) stemAngle = 10;
		if (stemAngle>90) stemAngle = 90;
		StemAngle = stemAngle;
	
	}

	/**
	 * @return the sideAngle psi
	 */
	//@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=10)
	public synchronized double getSideAngle()
	{
		return SideAngle;
	}

	/**
	 * @param sideAngle the sideAngle to set psi
	 */
	public synchronized void setSideAngle(double sideAngle)
	{
		if (sideAngle<10) sideAngle = 10;
		if (sideAngle>90) sideAngle = 90;
		SideAngle = sideAngle;
	}
	
	/**
	 * @return the mass
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=1)
	public synchronized double getMass()
	{
		return Mass;
	}

	/**
	 * @param mass the mass to set Mass
	 */
	public synchronized void setMass(double mass)
	{
		Mass = mass;
	}
	
	/**
	 * @return the velocity
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=2)
	public synchronized double getVelocity()
	{
		return Velocity;
	}

	/**
	 * @param velocity the velocity to set Velocity
	 */
	public synchronized void setVelocity(double velocity)
	{
		Velocity = velocity;
	}

	/**
	 * @return the draft
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=2)
	public double getDraft()
	{
		return draft;
	}

	/**
	 * @param draft the draft to set
	 */
	public void setDraft(double draft)
	{
		this.draft = draft;
	
	}

	/**
	 * @return the distanceFromBowToCOG_X
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=20)
	public double getDistanceFromBowToCOG_X()
	{
		return distanceFromBowToCOG_X;
	}

	/**
	 * @param distanceFromBowToCOG_X the distanceFromBowToCOG_X to set
	 */
	public void setDistanceFromBowToCOG_X(double distanceFromBowToCOG_X)
	{
		this.distanceFromBowToCOG_X = distanceFromBowToCOG_X;
	
	}

	/**
	 * @return the distanceFromBowToCOG_Z
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=25)
	public double getDistanceFromBowToCOG_Z()
	{
		return distanceFromBowToCOG_Z;
	}

	/**
	 * @param distanceFromBowToCOG_Z the distanceFromBowToCOG_Z to set
	 */
	public void setDistanceFromBowToCOG_Z(double distanceFromBowToCOG_Z)
	{
		this.distanceFromBowToCOG_Z = distanceFromBowToCOG_Z;
	
	}

	/**
	 * @return the parabolaBow
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=30, readOnly=true)
	public double getParabolaBow()
	{
		return Manager.getAns(StemRadiusP, StemRadiusQ);
	}

	
	
	
	
	
	
}
