package org.anast.fowt.DTOs;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;

public abstract class Stiffening extends Stiffener {
	
	int quantity = 10;
	double offset;

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=50)
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the offset
	 */
	//@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=60)
	public double getOffset()
	{
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(double offset)
	{
		this.offset = offset;
	
	}

	
	
}
