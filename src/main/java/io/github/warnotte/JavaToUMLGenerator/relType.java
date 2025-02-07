package io.github.warnotte.JavaToUMLGenerator;

public enum relType {
	_11("1-1"),
	_0N("0-N");
	
	String label;
	
	relType(String str){
		this.label=str;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String toString()
	{
		return label;
	}
	
	
	
}
