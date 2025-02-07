/**
 * 
 */
package org.anast.fowt.DTOs;

import java.util.ArrayList;
import java.util.List;

import io.github.warnotte.obj2gui2.PROPERTY_FIELD_LISTABLE;

/**
 * @author Warnotte Renaud
 *
 */
@Deprecated
public class Braces extends ArrayList<Brace>
{
	// ARTIFICE POUR VOIRE APPARAITRE DANS OBJ2GUI2
	//@PROPERTY_FIELD_LISTABLE
    //public List<Brace> braces = this;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8665425564967254559L;

	public Braces() {
		
	}

	@Override
	public String toString()
	{
		return "Braces ["+this.size()+"]";
	}
	
	
}

