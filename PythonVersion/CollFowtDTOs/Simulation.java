package org.anast.fowt.DTOs;

import java.beans.Transient;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import io.github.warnotte.obj2gui2.PROPERTY_MIGLAYOUT;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_FIELD_XXXABLE;

@PROPERTY_MIGLAYOUT(LayoutConstraints = "", ColumnConstraints = "fill,grow", RowConstraints = "fill,grow")
public class Simulation

{
	
	@PROPERTY_FIELD_XXXABLE
	public Settings settings = new Settings();
	
	/** Attributes */
	/**
	 * 
	 */
	@PROPERTY_FIELD_XXXABLE
	public VesselBase ship = new Vessel_BULBOUS_BOW();

	/**
	 * 
	 */
	@PROPERTY_FIELD_XXXABLE
	public Fowt fowt = new Fowt();

	/**
	 * 
	 */
	@PROPERTY_FIELD_XXXABLE
	public Material material = new Material();

	@XStreamOmitField
	@PROPERTY_FIELD_XXXABLE
	public StatisticsVolumeCOGandCo stats = new StatisticsVolumeCOGandCo();
	
	public VesselBase getShip() {
		return ship;
	}

	public void setShip(VesselBase ship) {
		this.ship = ship;
	}

	public Fowt getFowt() {
		return fowt;
	}

	public void setFowt(Fowt fowt) {
		this.fowt = fowt;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	/**
	 * @return the stats
	 */
	public StatisticsVolumeCOGandCo getStats()
	{
		return stats;
	}

	/**
	 * @param stats the stats to set
	 */
	public void setStats(StatisticsVolumeCOGandCo stats)
	{
		this.stats = stats;
	}

	
	
}
