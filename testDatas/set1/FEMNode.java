package org.dnt.fswf.model;

public class FEMNode extends Vertex2D {

	int id;
	
	public FEMNode(int id, double xx, double yy) {
		super(xx, yy);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		//return id+";"+x+";"+y;
		return "v "+x+" "+y;
	}

	
}
