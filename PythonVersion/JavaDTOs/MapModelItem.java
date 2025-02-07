package org.dnt.fswf.model;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dnt.fswf.manager.AccelerationNeededEvent;
import org.dnt.fswf.manager.SuperPropertyChangeEvent;
import org.dnt.fswf.manager.SuperPropertyChangeEvent.TypeOfChangeEvent;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;

public class MapModelItem extends ModelItem {

	protected static final Logger				logger					= LogManager.getLogger(MapModelItem.class);
	
	// Taille maximale physique
	//final public static int MAXNUMBEROFX = 400;  
	//final public static int MAXNUMBEROFY = 400;  
	
	@XStreamAsAttribute
	protected String name;
	protected Integer[][] map;
	//protected float [] longueurs;
	//protected float [] largeurs;

	// Taille maximale logique
	//int nbrItemX;
	//int nbrItemY;
	
	//final private float defaultLongueur = 10;
	//final private float defaultLargeur = 6;

	public MapModelItem(int id, int nbrItemX, int nbrItemY) {
		super(id);
		map = new Integer[nbrItemX][nbrItemY];
		//this.nbrItemX = nbrItemX;
		//this.nbrItemY = nbrItemY;
		/*longueurs = new float[nbrItemX];
		largeurs = new float[nbrItemY];
		for (int i = 0; i < longueurs.length; i++) {
			longueurs[i]=defaultLongueur;
		}
		for (int i = 0; i < largeurs.length; i++) {
			largeurs[i]=defaultLargeur;
		}*/
	}
	
	public void duplicateParameters(MapModelItem ii) {
		this.name = ii.name + " DUP";
		for (int x = 0 ; x  < ii.getNbrItemX(); x++)
			for (int y = 0 ; y  < ii.getNbrItemY(); y++)
				this.map[x][y] = ii.map[x][y];
		/*for (int x = 0 ; x  < ii.getNbrItemX(); x++)
			this.longueurs[x]= ii.longueurs[x];
		for (int y = 0 ; y  < ii.getNbrItemY(); y++)
			this.largeurs[y]= ii.largeurs[y];
		*/
		
	}
	

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setName", this.name, name));
		this.name = name;
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 20)
	public int getNbrItemX() {
		if (map==null)
			return 0;
		return map.length;
	}

	public void setNbrItemX(int nbrItemX) {
		resizeMap(nbrItemX, getNbrItemY());
	}

	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 30)
	public int getNbrItemY() {
		return map[0].length;
	}

	public void setNbrItemY(int nbrItemY) {
		resizeMap(getNbrItemX(), nbrItemY);
	}

	public Integer getValue(int columnIndex, int rowIndex) {
		return map[columnIndex][rowIndex];
	}
	
	/**
	 * PROGRAMMEUR - DO NOT CALL ME DIRECTLY - USE MODEL.setValue(Ilot ilot, ...) that check consistency
	 * @param integer Identifiant du flotteur
	 * @param x position x dans la grille
	 * @param y position y dans la grille 
	 */
	public void setValue(Integer integer, int x, int y) {
		// TODO : Ouch ... soit on doit copier la map carrément soit j'sais pas ...
		//getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setName", this.name, name));
		getPcs().firePropertyChange(new AccelerationNeededEvent(this));
		
		map[x][y] = integer;
	}

	
	private void resizeMap(int nbrItemX, int nbrItemY) {
		//float[] resizedLong = new float[nbrItemX];
		//float[] resizedLarg = new float[nbrItemY];
		Integer[][] resizedMap = new Integer[nbrItemX][nbrItemY];

		//Arrays.fill(resizedLong, defaultLongueur);
		//Arrays.fill(resizedLarg, defaultLargeur);
		//System.arraycopy(longueurs, 0, resizedLong, 0, Math.min(longueurs.length, resizedLong.length));
		//System.arraycopy(largeurs, 0, resizedLarg, 0, Math.min(largeurs.length, resizedLarg.length));
		
		 // Copier les éléments existants
        for (int i = 0; i < Math.min(map.length, nbrItemX); i++) {
            System.arraycopy(map[i], 0, resizedMap[i], 0, Math.min(map[i].length, nbrItemY));
        }
        
        // Afficher les résultats
        // System.out.println("Tableau original : " + Arrays.toString(this.longueurs));
        // System.out.println("Tableau redimensionné : " + Arrays.toString(resizedLong));
        System.out.println("Tableau original      : " + Arrays.deepToString(this.map));
        System.out.println("Tableau redimensionné : " + Arrays.deepToString(resizedMap));
        
        //this.longueurs = resizedLong;
		//this.largeurs = resizedLarg;
		this.map = resizedMap;
		getPcs().firePropertyChange(new AccelerationNeededEvent(this));
		
	}



}