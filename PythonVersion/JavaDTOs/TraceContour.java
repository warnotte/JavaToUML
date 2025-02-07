package org.dnt.fswf.model;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class TraceContour {

	@XStreamAsAttribute
	float minX = 0;
	@XStreamAsAttribute
	float minY = 0;
	@XStreamAsAttribute
	float maxX = 0;
	@XStreamAsAttribute
	float maxY = 0;
	
	List<Vertex2D> listPoints = new ArrayList<>();
	// Liste des point remis a l'origine 0, 0
	transient List<Vertex2D> listPointsAtZero = null;//new ArrayList<>();
	
	transient Path2D shp_trace = null;

	public TraceContour(List<Vertex2D> listPoints)
	{
		setListPoints(listPoints);
	}
	
	private void computeBounds() {
		Comparator<Vertex2D>	compX	= Comparator.comparing(Vertex2D::getX);
		Comparator<Vertex2D>	compY	= Comparator.comparing(Vertex2D::getY);

		// Get Min or Max Object
		//double minX = listPoints.stream().min(compX).get().x();
		//double minY = listPoints.stream().max(compY).get().y();
		minX = (float) listPoints.stream().min(compX).get().x;
		minY = (float)listPoints.stream().min(compY).get().y;
		maxX = (float)listPoints.stream().max(compX).get().x;
		maxY = (float)listPoints.stream().max(compY).get().y;
		
		
		

		
	}

	public List<Vertex2D> getListPoints() {
		return listPoints;
	}

	public void setListPoints(List<Vertex2D> listPoints) {
		this.listPoints = listPoints;
		computeBounds();
		computeListPointAtZero();
		shp_trace = null;
	}
	
	public List<Vertex2D> getListPointsAtZero() {
		// Lazy loading
		if (listPointsAtZero==null)
		{
			computeListPointAtZero();
		}
		
		return listPointsAtZero;
	}

	private void computeListPointAtZero() {
		listPointsAtZero = new ArrayList<>();
		//listPointsAtZero.clear();
		for (Iterator<Vertex2D> iterator = listPoints.iterator(); iterator.hasNext();) {
			Vertex2D vertex2d = iterator.next();
			listPointsAtZero.add(new Vertex2D(vertex2d.x - minX, vertex2d.y- minY));
		}
	}

	public float getMinX() {
		return minX;
	}

	public float getMinY() {
		return minY;
	}

	public float getMaxX() {
		return maxX;
	}

	public float getMaxY() {
		return maxY;
	}

	public Path2D getShp_trace() {
		// Lazy loading ;)
		if (shp_trace==null)
		{
			// TODO : Put that in a precomputed method of TC ???
			shp_trace = new Path2D.Double();
					
			List<Vertex2D> verts = getListPointsAtZero();
			shp_trace.moveTo(verts.get(0).x, verts.get(0).y);
			for (int i = 1; i < verts.size(); i++) {
				shp_trace.lineTo(verts.get(i).x, verts.get(i).y);
			}
			shp_trace.closePath();
		}
		return shp_trace;
	}

	@Override
	public String toString() {
		return "TraceContour [minX=" + minX + ", minY=" + minY + ", maxX=" + maxX + ", maxY=" + maxY + "]";
	}
	
	
	
}
