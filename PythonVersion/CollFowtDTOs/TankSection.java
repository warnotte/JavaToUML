package org.anast.fowt.DTOs;

import io.github.warnotte.obj2gui2.PROPERTY_FIELD_LISTABLE;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_FIELD_XXXABLE;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;

public abstract class TankSection

{
	/** Attributes */
	/**
	 * 
	 */
	public double	Diameter	= 10;
	/**
	 * 
	 */
	public double	Height		= 8;
	/**
	 * 
	 */
	public double	Thickness	= 1;

	@PROPERTY_FIELD_XXXABLE
	private HorizontalStiffening horizontalStiffeners = new HorizontalStiffening();

	@PROPERTY_FIELD_XXXABLE
	private VerticalStiffening verticalStiffeners = new VerticalStiffening();

	@PROPERTY_FIELD_LISTABLE
	private Decks decks = new Decks();


	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=10)
	public double getDiameter()
	{
		return Diameter;
	}

	public void setDiameter(double diameter)
	{
		Diameter = diameter;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=20)
	public double getHeight()
	{
		return Height;
	}

	public void setHeight(double height)
	{
		Height = height;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=30)
	public double getThickness()
	{
		return Thickness;
	}

	public void setThickness(double thickness)
	{
		Thickness = thickness;
	}
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=40)
	public double getHorizStiffSpace()
	{
		Stiffening hstiff = getHorizontalStiffeners();
		double lts_height = getHeight();
		double espacement = lts_height/(hstiff.getQuantity()+1);
		return espacement;
	}
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=50)
	public double getVertStiffSpace()
	{
		Stiffening hstiff = getVerticalStiffeners();
		double lts_height = getDiameter() * Math.PI;
		double espacement = lts_height/(hstiff.getQuantity()+1);
		return espacement;
	}

	/**
	 * @return the ringStiffeners
	 */
	public HorizontalStiffening getHorizontalStiffeners()
	{
		return horizontalStiffeners;
	}

	/**
	 * @param ringStiffeners
	 *            the ringStiffeners to set
	 */
	public void setHorizontalStiffeners(HorizontalStiffening ringStiffeners)
	{
		this.horizontalStiffeners = ringStiffeners;

	}

	/**
	 * @return the verticalStiffeners
	 */
	public VerticalStiffening getVerticalStiffeners()
	{
		return verticalStiffeners;
	}

	/**
	 * @param verticalStiffeners
	 *            the verticalStiffeners to set
	 */
	public void setVerticalStiffeners(VerticalStiffening verticalStiffeners)
	{
		this.verticalStiffeners = verticalStiffeners;

	}


	/**
	 * @return the decks
	 */
	public Decks getDecks()
	{
		if (decks==null)
			decks = new Decks();
		return decks;
	}

	/**
	 * @param decks
	 *            the decks to set
	 */
	public void setDecks(Decks decks)
	{
		this.decks = decks;

	}
	
	@Override
	public String toString()
	{
		return "TankSection [Diameter=" + Diameter + ", Height=" + Height + ", Thickness=" + Thickness + ", frameStiffeners=" + horizontalStiffeners + ", girderStiffeners=" + verticalStiffeners + "]";
	}


}
