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
public class Deck
{
	double position;
	
	double thickness = 0.1;

	
	public Deck(double position, double thickness) {
		super();
		this.position = position;
		this.thickness = thickness;
	}

	public Deck() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the position
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=10)
	public double getPosition()
	{
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(double position)
	{
		this.position = position;
	
	}

	/**
	 * @return the thickness
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=20)
	public double getThickness()
	{
		return thickness;
	}

	/**
	 * @param thickness the thickness to set
	 */
	public void setThickness(double thickness)
	{
		this.thickness = thickness;
	
	}

	@Override
	public String toString()
	{
		return "Deck "+position;
	}
	
	
}
