/**
 * 
 */
package org.anast.fowt.DTOs;

import java.util.ArrayList;

/**
 * @author Warnotte Renaud
 *
 */
public class Moorings extends ArrayList<Mooring>
{
	//ARTIFICE POUR VOIRE APPARAITRE DANS OBJ2GUI2
	//@PROPERTY_FIELD_LISTABLE
    //public List<Mooring> moorings = this;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2015449692960497381L;

	public Moorings() {
		
	}

	@Override
	public String toString()
	{
		return "Moorings []";
	}
	
	
	
}
