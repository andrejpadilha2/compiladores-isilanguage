package isilanguage.datastructures;

public abstract class IsiSymbol {
	
	protected String name;
	
	public abstract String generateJavaCode(String identacao);
	
	public IsiSymbol(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "IsiSymbol [name=" + name + "]";
	}
	
}