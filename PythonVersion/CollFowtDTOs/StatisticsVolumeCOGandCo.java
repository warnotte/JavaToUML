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
public class StatisticsVolumeCOGandCo
{
	
	public double volumeBraces;
	public double cogZBraces;

	/**
	 * Calcul volume Colonnes Exterieur Heave
	 */
	public double volumeHeave;
	public double cogZHeave;
	/**
	 * Calcul volume Colonnes Exterieur Column
	 */
	public double volumeColumn;
	public double cogZColumn;
	
	/**
	 * Calcul volume Colonnes Centrale
	 */
	public double volumeCentralColumn;
	public double cogZCentralColumn;
	
	/**
	 * Calcul volume du mats;
	 */
	public double volumeMast;
	public double cogZMast;
	
	/**
	 * Calcul du volumes des decks;
	 */
	public double volumeCentralColumDecks;
	public double volumeExternalColumDecks;
	public double volumeExternalHeaveBoxDecks;
	
	public double cogZCentralColumDecks;
	public double cogZExternalColumDecks;
	public double cogZExternalHeaveBoxDecks;
	
	public double VolumeTotal;
	public double cogZGlobal;

	

	/**
	 * @return the cogZGlobal
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getCogZGlobal()
	{
		return cogZGlobal;
	}


	/**
	 * @param cogZGlobal the cogZGlobal to set
	 */
	public void setCogZGlobal(double cogZGlobal)
	{
		this.cogZGlobal = cogZGlobal;
	
	}


	/**
	 * @return the volumeBraces
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getVolumeBraces()
	{
		return volumeBraces;
	}


	/**
	 * @param volumeBraces the volumeBraces to set
	 */
	public void setVolumeBraces(double volumeBraces)
	{
		this.volumeBraces = volumeBraces;
	
	}


	/**
	 * @return the cogZBraces
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getCogZBraces()
	{
		return cogZBraces;
	}


	/**
	 * @param cogZBraces the cogZBraces to set
	 */
	public void setCogZBraces(double cogZBraces)
	{
		this.cogZBraces = cogZBraces;
	
	}


	/**
	 * @return the volumeHeave
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getVolumeHeave()
	{
		return volumeHeave;
	}


	/**
	 * @param volumeHeave the volumeHeave to set
	 */
	public void setVolumeHeave(double volumeHeave)
	{
		this.volumeHeave = volumeHeave;
	
	}


	/**
	 * @return the cogZHeave
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getCogZHeave()
	{
		return cogZHeave;
	}


	/**
	 * @param cogZHeave the cogZHeave to set
	 */
	public void setCogZHeave(double cogZHeave)
	{
		this.cogZHeave = cogZHeave;
	
	}


	/**
	 * @return the volumeColumn
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getVolumeColumn()
	{
		return volumeColumn;
	}


	/**
	 * @param volumeColumn the volumeColumn to set
	 */
	public void setVolumeColumn(double volumeColumn)
	{
		this.volumeColumn = volumeColumn;
	
	}


	/**
	 * @return the cogZColumn
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getCogZColumn()
	{
		return cogZColumn;
	}


	/**
	 * @param cogZColumn the cogZColumn to set
	 */
	public void setCogZColumn(double cogZColumn)
	{
		this.cogZColumn = cogZColumn;
	
	}


	/**
	 * @return the volumeCentralColumn
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getVolumeCentralColumn()
	{
		return volumeCentralColumn;
	}


	/**
	 * @param volumeCentralColumn the volumeCentralColumn to set
	 */
	public void setVolumeCentralColumn(double volumeCentralColumn)
	{
		this.volumeCentralColumn = volumeCentralColumn;
	
	}


	/**
	 * @return the cogZCentralColumn
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getCogZCentralColumn()
	{
		return cogZCentralColumn;
	}


	/**
	 * @param cogZCentralColumn the cogZCentralColumn to set
	 */
	public void setCogZCentralColumn(double cogZCentralColumn)
	{
		this.cogZCentralColumn = cogZCentralColumn;
	
	}


	/**
	 * @return the volumeMast
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getVolumeMast()
	{
		return volumeMast;
	}


	/**
	 * @param volumeMast the volumeMast to set
	 */
	public void setVolumeMast(double volumeMast)
	{
		this.volumeMast = volumeMast;
	
	}


	/**
	 * @return the cogZMast
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getCogZMast()
	{
		return cogZMast;
	}


	/**
	 * @param cogZMast the cogZMast to set
	 */
	public void setCogZMast(double cogZMast)
	{
		this.cogZMast = cogZMast;
	
	}


	/**
	 * @return the volumeCentralColumDecks
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getVolumeCentralColumDecks()
	{
		return volumeCentralColumDecks;
	}


	/**
	 * @param volumeCentralColumDecks the volumeCentralColumDecks to set
	 */
	public void setVolumeCentralColumDecks(double volumeCentralColumDecks)
	{
		this.volumeCentralColumDecks = volumeCentralColumDecks;
	
	}


	/**
	 * @return the volumeExternalColumDecks
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getVolumeExternalColumDecks()
	{
		return volumeExternalColumDecks;
	}


	/**
	 * @param volumeExternalColumDecks the volumeExternalColumDecks to set
	 */
	public void setVolumeExternalColumDecks(double volumeExternalColumDecks)
	{
		this.volumeExternalColumDecks = volumeExternalColumDecks;
	
	}


	/**
	 * @return the volumeExternalHeaveBoxDecks
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getVolumeExternalHeaveBoxDecks()
	{
		return volumeExternalHeaveBoxDecks;
	}


	/**
	 * @param volumeExternalHeaveBoxDecks the volumeExternalHeaveBoxDecks to set
	 */
	public void setVolumeExternalHeaveBoxDecks(double volumeExternalHeaveBoxDecks)
	{
		this.volumeExternalHeaveBoxDecks = volumeExternalHeaveBoxDecks;
	
	}


	/**
	 * @return the cogZCentralColumDecks
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getCogZCentralColumDecks()
	{
		return cogZCentralColumDecks;
	}


	/**
	 * @param cogZCentralColumDecks the cogZCentralColumDecks to set
	 */
	public void setCogZCentralColumDecks(double cogZCentralColumDecks)
	{
		this.cogZCentralColumDecks = cogZCentralColumDecks;
	
	}


	/**
	 * @return the cogZExternalColumDecks
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getCogZExternalColumDecks()
	{
		return cogZExternalColumDecks;
	}


	/**
	 * @param cogZExternalColumDecks the cogZExternalColumDecks to set
	 */
	public void setCogZExternalColumDecks(double cogZExternalColumDecks)
	{
		this.cogZExternalColumDecks = cogZExternalColumDecks;
	
	}


	/**
	 * @return the cogZExternalHeaveBoxDeck
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getCogZExternalHeaveBoxDecks()
	{
		return cogZExternalHeaveBoxDecks;
	}


	/**
	 * @param cogZExternalHeaveBoxDeck the cogZExternalHeaveBoxDeck to set
	 */
	public void setCogZExternalHeaveBoxDecks(double cogZExternalHeaveBoxDeck)
	{
		this.cogZExternalHeaveBoxDecks = cogZExternalHeaveBoxDeck;
	
	}


	/**
	 * @return the volumeTotal
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	public double getVolumeTotal()
	{
		return VolumeTotal;
	}


	/**
	 * @param volumeTotal the volumeTotal to set
	 */
	public void setVolumeTotal(double volumeTotal)
	{
		VolumeTotal = volumeTotal;
	
	}


	@Override
	public String toString()
	{
		return "StatisticsVolumeCOGandCo";
	}

	
	
	
//	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10, readOnly = true)
	
	
}
