package org.dnt.fswf.model;

import java.beans.PropertyChangeSupport;

import org.dnt.fswf.manager.Manager;
import org.dnt.fswf.manager.SuperPropertyChangeEvent;
import org.dnt.fswf.manager.SuperPropertyChangeEvent.TypeOfChangeEvent;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_FIELD_XXXABLE;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;

public class Settings {
	
	@XStreamAsAttribute
	double sunAngle = 30;
	
	@XStreamAsAttribute
	double waterDensity = 1000; // Kg/m³
	
	//@PROPERTY_FIELD_XXXABLE
	WindSettings windSettings = new WindSettings();
	/*@PROPERTY_FIELD_XXXABLE
	WaveSettings			waveSettings	= new WaveSettings();
	@PROPERTY_FIELD_XXXABLE
	CurrentSettings			currentSettings	= new CurrentSettings();
	*/
	@XStreamOmitField
	private PropertyChangeSupport pcs;
	
	public PropertyChangeSupport getPcs() {
		if (pcs == null)
		{
			pcs = new PropertyChangeSupport(this);
			pcs.addPropertyChangeListener(Manager.dataChangedObserver);
		}
		return pcs;
	}
	
	public WindSettings getWindSettings() {
		return windSettings;
	}

	public void setWindSettings(WindSettings windSettings) {
		this.windSettings = windSettings;
	}
/*
	public WaveSettings getWaveSettings() {
		return waveSettings;
	}

	public void setWaveSettings(WaveSettings waveSettings) {
		this.waveSettings = waveSettings;
	}

	public CurrentSettings getCurrentSettings() {
		return currentSettings;
	}

	public void setCurrentSettings(CurrentSettings currentSettings) {
		this.currentSettings = currentSettings;
	}
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10)
	public double getSunAngle() {
		return sunAngle;
	}

	public void setSunAngle(double sunAngle) {
		//getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setSunAngle", this.sunAngle, sunAngle));
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.sunAngle, sunAngle));
		this.sunAngle = sunAngle;
	}
	
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 20)
	public double getWaterDensity() {
		return waterDensity;
	}

	public void setWaterDensity(double waterDensity) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, this.waterDensity, waterDensity));
		this.waterDensity = waterDensity;
	}

	@Override
	public String toString() {
		return "Settings [sunAngle=" + sunAngle + "]";
	}

	
}
