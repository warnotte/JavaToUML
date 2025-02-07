package org.dnt.fswf.model;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class Vertex2D {

	@XStreamAsAttribute
	public double x,y;
	
	public Vertex2D(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
}
