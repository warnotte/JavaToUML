package org.anast.fowt.DTOs;

import io.github.warnotte.obj2gui2.PROPERTY_FIELD_LISTABLE;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_FIELD_XXXABLE;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;

public class Fowt

{
	/** Attributes */
	/**
	 * 
	 */
	public double DistanceBetweenTanks = 63;
	/**
	 * 
	 */
	public double Draft = 19.27;
	
	/**
	 * 
	 */
	@PROPERTY_FIELD_XXXABLE
	public Turbine turbine = new Turbine();
	
	/**
	 * 
	 */
	@PROPERTY_FIELD_XXXABLE
	public ExternalTank externalTank = new ExternalTank();
	/**
	 * 
	 */
	@PROPERTY_FIELD_XXXABLE
	public CentralTank centralTank = new CentralTank();

	public double Mass = 0.0;
	
//	public boolean computeMass = false;
	
	public double COGZ = 0.0;
	
//	public boolean computeCOGZ = false;
	
	/**
	 * 
	 */
//	@PROPERTY_FIELD_LISTABLE
	//public List<Brace> braces = new ArrayList<>();
	/**
	 * 
	 */
//	@PROPERTY_FIELD_LISTABLE
//	public List<Mooring> moorings = new ArrayList<>();

	//@XStreamImplicit        //define list as an implicit collection
	//@PROPERTY_FIELD_LISTABLE
	//public Braces braces = new Braces();
	
	@PROPERTY_FIELD_XXXABLE
	public Brace brace = new Brace();
	
	//@XStreamImplicit        //define list as an implicit collection
	//@PROPERTY_FIELD_LISTABLE
	//public Moorings moorings = new Moorings();
	@PROPERTY_FIELD_XXXABLE
	public Mooring mooring = new Mooring();
	
	
	public Fowt()
	{
		// DUmmy code for tree testing
		turbine.getSections().add(new TurbineSection(8, 7, 20, 0.2));
		turbine.getSections().add(new TurbineSection(7, 6, 20, 0.2));
		turbine.getSections().add(new TurbineSection(6, 5, 20, 0.15));
		turbine.getSections().add(new TurbineSection(5, 4.5, 25, 0.1));
		turbine.getSections().add(new TurbineSection(4.5, 4, 25, 0.1));
		
		
		//braces.add(new Brace());	
		//braces.add(new Brace());
		
		//moorings.add(new Mooring());
		//moorings.add(new Mooring());
		//moorings.add(new Mooring());
		
		//externalTank.getHeaveBoxes().setHeight(4);
		externalTank.getHeaveBoxes().getDecks().add(new Deck(0, 0.1));
		externalTank.getHeaveBoxes().getDecks().add(new Deck(4, 0.1));
		
		//externalTank.getColumns().setHeight(16);
		externalTank.getColumns().getDecks().add(new Deck(0, 0.1));
		externalTank.getColumns().getDecks().add(new Deck(4, 0.1));
		externalTank.getColumns().getDecks().add(new Deck(8, 0.1));
		externalTank.getColumns().getDecks().add(new Deck(12, 0.1));
		externalTank.getColumns().getDecks().add(new Deck(16, 0.1));
		
		
		
	}
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 5)
	public double getDistanceBetweenTanks() {
		return DistanceBetweenTanks;
	}

	public void setDistanceBetweenTanks(double distanceBetweenTanks) {
		DistanceBetweenTanks = distanceBetweenTanks;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10)
	public double getDraft() {
		return Draft;
	}

	public void setDraft(double meanWaterLevel) {
		Draft = meanWaterLevel;
	}

	
	public ExternalTank getExternalTank() {
		return externalTank;
	}

	public void setExternalTank(ExternalTank externalTank) {
		this.externalTank = externalTank;
	}

	
	public CentralTank getCentralTank() {
		return centralTank;
	}

	public void setCentralTank(CentralTank centralTank) {
		this.centralTank = centralTank;
	}

	
	public Brace getBrace() {
		return brace;
	}

	public void setBraces(Brace brace) {
		this.brace = brace;
	}
	
	
/**
	 * @return the mooring
	 */
	public Mooring getMooring()
	{
		return mooring;
	}

	/**
	 * @param mooring the mooring to set
	 */
	public void setMooring(Mooring mooring)
	{
		this.mooring = mooring;
	
	}

	/*
	public Moorings getMoorings() {
		return moorings;
	}

	public void setMoorings(Moorings moorings) {
		this.moorings = moorings;
	}
*/
	public Turbine getTurbine() {
		return turbine;
	}

	public void setTurbine(Turbine turbine) {
		this.turbine = turbine;
	}
	
	
	
	/**
	 * @return the mass
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 20)
	public double getMass()
	{
		return Mass;
	}

	/**
	 * @param mass the mass to set
	 */
	public void setMass(double mass)
	{
		Mass = mass;
	
	}

	
	

	/**
	 * @return the cOGZ
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 40)
	public double getCOGZ()
	{
		return COGZ;
	}

	/**
	 * @param cOGZ the cOGZ to set
	 */
	public void setCOGZ(double cOGZ)
	{
		COGZ = cOGZ;
	
	}

	

	@Override
	public String toString() {
		return "Fowt";
	}

	/**
	 * @return
	 */
	public boolean isMoorinLineAttachedinColumnsOrHeaveBoxes()
	{
		Tank tank = getExternalTank();
		double z = mooring.getZf();
		double fowt_draft = getDraft();
		
		double Zbottom =  (-fowt_draft + tank.getHeaveBoxes().getHeight());
		
		if (z >= Zbottom)
			return true;
		return false;
	}

	
}
