/**
 * 
 */
package org.anast.fowt.DTOs;

/**
 * @author Warnotte Renaud
 *
 */
public class HeaveBoxes extends TankSection
{
	public HeaveBoxes()
	{
		setDiameter(20);
		setHeight(4);
		getVerticalStiffeners().quantity = 20;
	}
}
