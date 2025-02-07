/**
 * 
 */
package org.anast.fowt.DTOs;

import java.util.ArrayList;

/**
 * @author Warnotte Renaud
 *
 */
public class Decks extends ArrayList<Deck>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2275565376570219294L;

	
	@Override
	public String toString()
	{
		return "Decks ["+this.size()+"]";
	}

}
