package org.anast.fowt.DTOs;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_FIELD_XXXABLE;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;

public abstract class Tank 
{
	/** Attributes */
	/**
	 * 
	 */
	@PROPERTY_FIELD_XXXABLE
	public HeaveBoxes	heaveBoxes	= new HeaveBoxes();
	/**
	 * 
	 */
	@PROPERTY_FIELD_XXXABLE
	public Columns		columns		= new Columns();
	/**
	 * 
	 */
	//public double		BallastMass;

	/**
	 * @return the heaveBoxes
	 */
	public HeaveBoxes getHeaveBoxes()
	{
		return heaveBoxes;
	}

	/**
	 * @param heaveBoxes
	 *            the heaveBoxes to set
	 */
	public void setHeaveBoxes(HeaveBoxes heaveBoxes)
	{
		this.heaveBoxes = heaveBoxes;

	}

	/**
	 * @return the columns
	 */
	public Columns getColumns()
	{
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(Columns columns)
	{
		this.columns = columns;

	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=10, readOnly = true)
	public double getHeight()
	{
		return heaveBoxes.getHeight() + columns.getHeight();
	}

	@Override
	public String toString()
	{
		return "Tank";
	}
	

}
