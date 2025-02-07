package org.dnt.fswf.model;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dnt.fswf.manager.Manager;
import org.dnt.fswf.manager.SuperPropertyChangeEvent;
import org.dnt.fswf.manager.SuperPropertyChangeEvent.TypeOfChangeEvent;
import org.dnt.fswf.manager.exceptions.NoIlotExistException;
import org.dnt.fswf.model.enums.TypeFlotteur;
import org.dnt.fswf.model.enums.TypeObjetSurFlotteur;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class Model {

	protected static final Logger				logger	= LogManager.getLogger(Model.class);
	
	@XStreamAsAttribute
	String					fileXMLVersion	= "1";

	List<Flotteur>			flotteurs		= new ArrayList<>();
	List<Ilot>				ilots			= new ArrayList<>();
	List<IlotInstance>		ilotsinstances	= new ArrayList<>();
	List<ObjetSurFlotteur>	objetssurflotteur	= new ArrayList<>();
	
	
	// Map pour accelerer certaines fonctions
	@XStreamOmitField
	HashMap<Integer, Flotteur> map_flotteur;
	
	
	List<TraceContour>		traces			= new ArrayList<>();
	
	Settings 				settings 		= new Settings();
	
	@XStreamOmitField
	PropertyChangeSupport	pcs;

	@XStreamAsAttribute
	String					name			= "Model";

	public Model() {
		map_flotteur = new HashMap<>();
		/*PanneauSolaire ps1 = addPanneauSolaire();
		PanneauSolaire ps2 = addPanneauSolaire();
		PanneauSolaire ps3 = addPanneauSolaire();
		PanneauSolaire ps4 = addPanneauSolaire();
		ps1.setName("Panneau NORD");
		ps2.setName("Panneau SUD");
		ps3.setName("Panneau OUEST");
		ps4.setName("Panneau EST");
		ps1.setDirection(Direction.NORD);
		ps2.setDirection(Direction.SUD);
		ps3.setDirection(Direction.OUEST);
		ps4.setDirection(Direction.EST);*/
	}

	public PropertyChangeSupport getPcs() {
		if (pcs == null)
		{
			pcs = new PropertyChangeSupport(this);
			pcs.addPropertyChangeListener(Manager.dataChangedObserver);
		}
		return pcs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.MODIFYATTRIBUTE, this, "setName", this.name, name));
		this.name = name;
	}

	public <T extends ModelItem> int getNextId(List<T> items) {
		int maxId = -1;
		for (T item : items) {
			maxId = Math.max(maxId, item.getId());
		}
		return maxId + 1;
	}

	/**
	 * Récupère la liste des flotteurs du modèles.
	 * @return
	 */
	public List<Flotteur> getFlotteurs() {
		return flotteurs;
	}
	
	/**
	 * Récupere tout les flotteurs qui sont dans l'ilot.
	 * @param ilot
	 * @return
	 */
	public List<Flotteur> getFlotteurs(Ilot ilot) {
		// Récupere la liste des flotteurs qui sont dans l'ilot
		List<Flotteur> list = new ArrayList<>();
		for (int i = 0; i < ilot.getNbrItemY(); i++)
		{
			for (int j = 0 ; j < ilot.getNbrItemX(); j++) {
				Integer id_ilot = ilot.getValue(j, i);
				if (id_ilot!=null)
				{
					Flotteur f = getFlotteurById(id_ilot);
					list.add(f);
				}
			}
		}
		return list;
	}
	
	/**
	 * Retourne pour une ilot la liste des objets sur flotteur contenus dans l'ilot.
	 * 
	 * @param ilot
	 * @return
	 */
	public List<ObjetSurFlotteur> getObjetSurFlotteur(Ilot ilot) {
		List<ObjetSurFlotteur> objetssurflot = new ArrayList<>();
		List<Flotteur> flotteurs = getFlotteurs(ilot);
		for (Iterator<Flotteur> iterator = flotteurs.iterator(); iterator.hasNext();) {
			Flotteur flotteur = (Flotteur) iterator.next();
			int idps = flotteur.getID_ObjetSurFlotteur();
			if (idps != -1)
			{
				ObjetSurFlotteur osf = getObjetSurFlotteurById(idps);
				objetssurflot.add(osf);
			}
		}
		return objetssurflot;
		
	}
	

	// public void setFlotteurs(ArrayList<Flotteur> flotteurs) { // -> Test pour
	// UNDO/REDO
	private void setFlotteurs(List<Flotteur> flotteurs) {
		this.flotteurs = flotteurs;
		System.err.println("FLotteurs = " + flotteurs.size());
		refreshAccelerationMaps();
	}
	
	public List<ObjetSurFlotteur> getObjetSurFlotteur() {
		if (objetssurflotteur==null)
			objetssurflotteur = new ArrayList<>();
		return objetssurflotteur;
	}

	private void setObjetSurFlotteur(List<ObjetSurFlotteur> panneauxSolaires) {
		this.objetssurflotteur = panneauxSolaires;
	}
/*
	public List<Block> getBlocks() {
		return blocks;
	}

	private void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}
*/
	public List<Ilot> getIlots() {
		return ilots;
	}
/*
	private void setIlots(List<Ilot> ilots) {
		this.ilots = ilots;
	}
*/	
	public List<IlotInstance> getIlotsInstances() {
		if (ilotsinstances==null)
			ilotsinstances = new ArrayList<>();
		return ilotsinstances;
	}
/*
	private void setIlotsInstances(List<IlotInstance> ilotsinstances) {
		this.ilotsinstances = ilotsinstances;
	}
*/
	
	public Settings getSettings() {
		if (settings==null)
			settings = new Settings();
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public List<TraceContour> getTraces() {
		if (traces==null)
			traces = new ArrayList<>();
		return traces;
	}

	public void setTraces(List<TraceContour> traces) {
		this.traces = traces;
	}

	public static void main(String args[]) {
		/*
		 * Model model = new Model(); Method [] meths = model.getClass().metho for (int
		 * i = 0; i < meths.length; i++) { Sytem.err.println("Meths["+i+"] "+meths[i]);
		 * }
		 */
	}

	public Flotteur addFlotteur(TypeFlotteur typeflot) {
		// En esperant que ca marchera ...
		List<Flotteur>	clonedList	= new ArrayList<Flotteur>(flotteurs);
		Flotteur		item;
		
		switch (typeflot) {
			case PRISMATIQUE : 
				item		= new Flotteur_Prismatique(getNextId(getFlotteurs()));
				break;
			case TRIANGLE : 
				item		= new Flotteur_Triangle(getNextId(getFlotteurs()));
				break;
			case CIRCULAIRE : 
				item		= new Flotteur_Circulaire(getNextId(getFlotteurs()));
				break;
			default:
				item		= new Flotteur_Prismatique(getNextId(getFlotteurs()));
				break;		
		}
		
		getFlotteurs().add(item);
		refreshAccelerationMaps();
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.ADDORREMOVE, this, "setFlotteurs", clonedList, flotteurs));
		return item;
	}
		
	public ObjetSurFlotteur addObjetSurFlotteur(TypeObjetSurFlotteur typeObjetSurFlotteur) {
		// En esperant que ca marchera ...
		List<ObjetSurFlotteur>	clonedList	= new ArrayList<ObjetSurFlotteur>(objetssurflotteur);
		ObjetSurFlotteur		item;
		switch (typeObjetSurFlotteur) {
		case TypeObjetSurFlotteur.PANNEAU_SOLAIRE:
			item = new PanneauSolaire(getNextId(getObjetSurFlotteur()));
			break;
		case TypeObjetSurFlotteur.AUTRES:
			item = new AutreObjetSurFlotteur(getNextId(getObjetSurFlotteur()));
			break;
		default:
			logger.fatal("Objet not implemented : creating solar panel");
			item = new PanneauSolaire(getNextId(getObjetSurFlotteur()));
			break;
		}
		getObjetSurFlotteur().add(item);
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.ADDORREMOVE, this, "setObjetSurFlotteur", clonedList, objetssurflotteur));
		return item;
	}
	
	public ObjetSurFlotteur addObjetSurFlotteur(ObjetSurFlotteur iii) {
		return addObjetSurFlotteur(iii.getTypeObjetSurFlotteur());
	}

	public Ilot addIlot(int w, int h) {
		// En esperant que ca marchera ...
		List<Ilot>	clonedList	= new ArrayList<Ilot>(ilots);
		Ilot		item		= new Ilot(getNextId(getIlots()), w, h);
		getIlots().add(item);
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.ADDORREMOVE, this, "setIlots", clonedList, ilots));
		return item;
	}
	
	public IlotInstance addIlotInstance() throws NoIlotExistException {
		if (ilots.size()==0)
			throw new NoIlotExistException();
		return addIlotInstance(ilots.get(0));
	}
	
	public IlotInstance addIlotInstance(Ilot ilot) {
		List<IlotInstance>	clonedList	= new ArrayList<IlotInstance>(ilotsinstances);
		IlotInstance		item		= new IlotInstance(getNextId(getIlotsInstances()), ilot.getId());
		getIlotsInstances().add(item);
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.ADDORREMOVE, this, "setIlotsInstance", clonedList, ilotsinstances));
		return item;
	}
	
	public boolean removeObjetSurFlotteur(ObjetSurFlotteur item) {
	
		// En esperant que ca marchera ...
		List<ObjetSurFlotteur>	clonedList	= new ArrayList<>(objetssurflotteur);
		boolean			ret			= getObjetSurFlotteur().remove(item);
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.ADDORREMOVE, this, "setPanneauxSolaires", clonedList, objetssurflotteur));
		return ret;
	}
		
	public boolean removeFlotteur(Flotteur item) {
		// En esperant que ca marchera ...
		List<Flotteur>	clonedList	= new ArrayList<>(flotteurs);
		boolean			ret			= getFlotteurs().remove(item);
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.ADDORREMOVE, this, "setFlotteurs", clonedList, flotteurs));
		refreshAccelerationMaps();
		return ret;
	}

	public boolean removeIlot(Ilot item) {
		// En esperant que ca marchera ...
		List<Ilot>	clonedList	= new ArrayList<>(ilots);
		boolean		ret			= getIlots().remove(item);
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.ADDORREMOVE, this, "setIlots", clonedList, ilots));
		return ret;
	}
	
	public boolean removeIlotInstance(IlotInstance item) {
		// En esperant que ca marchera ...
		List<IlotInstance>	clonedList	= new ArrayList<>(ilotsinstances);
		boolean		ret			= getIlotsInstances().remove(item);
		getPcs().firePropertyChange(new SuperPropertyChangeEvent(TypeOfChangeEvent.ADDORREMOVE, this, "setIlotsInstances", clonedList, ilotsinstances));
		return ret;
	}

	public ObjetSurFlotteur duplicateObjetSurFlotteur(ObjetSurFlotteur ii) {
		ObjetSurFlotteur newItem = addObjetSurFlotteur(ii);
		newItem.duplicateParameters(ii);
		return newItem;
	}

	public Flotteur duplicateFlotteur(Flotteur ii) {
		Flotteur newItem = addFlotteur(ii.getTypeFlotteur());
		newItem.duplicateParameters(ii);
		return newItem;
	}

	public Ilot duplicateIlot(Ilot ii) {
		Ilot newItem = addIlot(ii.getNbrItemX(), ii.getNbrItemY());
		newItem.duplicateParameters(ii);
		return newItem;
	}
	
	
	public IlotInstance duplicateIlotInstance(IlotInstance ii) {
		Ilot newItem = getIlotById(ii.getID_IlotRef());
		IlotInstance iinew = addIlotInstance(newItem);
		iinew.duplicateParameters(ii);
		return iinew;
	}
	
	protected <T extends ModelItem> T getModelItemById(int id, List<T> list) {
		List<T> items = list.parallelStream().filter(i -> i.id == id).collect(Collectors.toList());
		if (items.size() != 0)
			return items.get(0);
		return null;
	}

	public Flotteur getFlotteurById(int id) {
		return map_flotteur.get(id);
		//return getModelItemById(id, flotteurs);
	}
	
	public ObjetSurFlotteur getObjetSurFlotteurById(int id) {
		return getModelItemById(id, objetssurflotteur);
	}

	public Ilot getIlotById(int id) {
		return getModelItemById(id, ilots);
		}
	
	public IlotInstance getIlotInstanceById(int id) {
		return getModelItemById(id, ilotsinstances);
	
	}

	/**
	 * 
	 * @param ilot
	 * @param aValue
	 * @param x
	 * @param y
	 */
	public void setIlotValue(Ilot ilot, Integer aValue, int x, int y) {
		if (aValue!=null)
		{
			//Block b = getBlockById(aValue.intValue());
			Flotteur b = getFlotteurById(aValue.intValue());
			if (b!=null) {
				// Verifier si dans la colonne ou la ligne, les autres flotteur on la meme longueur ou largeur que celui qu'on veut mettre.
				boolean ret = checkIfWeCanPlaceFlotteurInIlotAccordingToSize(ilot, b, x, y);
				if (ret==true)
					ilot.setValue(aValue, x, y);
				else
					logger.info("Ce flotteur ne peut être assigné dans cette rangée ou colonne car il ne respecte pas la taille des autres flotteur dans cette rangée ou collonne");
			}
			else
				logger.info("Cannot assign that value in ilot, block id("+aValue+") doesn't exists");
		}
		else
			ilot.setValue(null, x, y);
	}
/*
	public void setBlockValue(Block block, Integer aValue, int x, int y) {
		if (aValue!=null)
		{
			Flotteur b = getFlotteurById(aValue.intValue());
			if (b!=null)
				block.setValue(aValue, x, y);
			else
				logger.info("Cannot assign that value in ilot, block id("+aValue+") doesn't exists");
		}
		else
			block.setValue(null, x, y);
	}
*/

	/**
	 * Vérifie que le flotteur que l'on veut placer a la meme longeur et largeur que les autres flotteurs de cette rangée et colonne.
	 * @param ilot l'ilot ou l'on veux placer le flotteur
	 * @param flotteur le flotteur que l'on veux placer
	 * @param x la position 
	 * @param y la 
	 * @return
	 */
	public boolean checkIfWeCanPlaceFlotteurInIlotAccordingToSize(Ilot ilot, Flotteur flotteur, int x, int y) {
		double L = flotteur.getLongueur();
		double l = flotteur.getLargeur();
		for (int i = 0; i < ilot.getNbrItemX(); i++) {
			Integer id_f = ilot.getValue(i, y);
			if (id_f!=null)
			{
				Flotteur f = getFlotteurById(id_f);
				if (f.getLargeur()!=l)
					return false;
			}
		}
		
		for (int i = 0; i < ilot.getNbrItemY(); i++) {
			Integer id_f = ilot.getValue(x, i);
			if (id_f!=null)
			{
				Flotteur f = getFlotteurById(id_f);
				if (f.getLongueur()!=L)
					return false;
			}
		}
		
		return true;
	}


	public void refreshAccelerationMaps()
	{
		logger.info("Building acceleration map");
		map_flotteur = new HashMap<>();
		for (int i = 0 ; i < flotteurs.size(); i++)
		{
			Flotteur f = flotteurs.get(i);
			map_flotteur.put(f.getId(), f);
		}
	}

	

}
