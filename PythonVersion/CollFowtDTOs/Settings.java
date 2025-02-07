/**
 * 
 */
package org.anast.fowt.DTOs;

import java.io.File;

import io.github.warnotte.obj2gui2.PROPERTY_button;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface.gui_type;
import io.github.warnotte.waxlib3.waxlibswingcomponents.Dialog.DialogDivers;

/**
 * @author Warnotte Renaud
 *
 */
public class Settings
{
	public enum CollisionType {TANK{
        @Override
        public String toString() {
            return "COLUMN";
        }
    }, BRACE};
	
	CollisionType collisionType = CollisionType.TANK;

	public double collisionAngle = 0;
	
	public String MCOFileFlotteur = "";
	
	/**
	 * @return the collisionType
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=10, gui_type = gui_type.COMBO)
	public CollisionType getCollisionType()
	{
		return collisionType;
	}

	/**
	 * @param collisionType the collisionType to set
	 */
	public void setCollisionType(CollisionType collisionType)
	{
		this.collisionType = collisionType;
	
	}

	/**
	 * @return the collisionAngle
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=20, readOnly= true)
	public double getCollisionAngle()
	{
		return collisionAngle;
	}

	/**
	 * @param collisionAngle the collisionAngle to set
	 */
	public void setCollisionAngle(double collisionAngle)
	{
		this.collisionAngle = collisionAngle;
	
	}

	
	/**
	 * @return the mCOFileFlotteur
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay=30, readOnly=true)
	public String getMCOFileFlotteur()
	{
		return MCOFileFlotteur;
	}

	/**
	 * @param mCOFileFlotteur the mCOFileFlotteur to set
	 */
	public void setMCOFileFlotteur(String mCOFileFlotteur)
	{
		MCOFileFlotteur = mCOFileFlotteur;
	
	}

	
}
