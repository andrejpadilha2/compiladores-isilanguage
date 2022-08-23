package isilanguage.datastructures;

public class IsiVariable extends IsiSymbol {
	
	public static final int INTEGER	=0;
	public static final int DOUBLE	=1;
	public static final int STRING  =2;
	
	private int type;
	private String value;
	protected boolean used;
	
	public IsiVariable(String name, int type, String value) {
		super(name);
		this.type = type;
		this.value = value;
		this.used = false;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean isUsed() {
		return used;
	}

	public void setUsed() {
		this.used = true;
	}

	@Override
	public String toString() {
		return "IsiVariable [name=" + name + ", type=" + type + ", value=" + value + "]";
	}
	
	public String generateJavaCode(String identacao) {
       String str;
       if (type == INTEGER) {
    	   str = "int ";
       }
       else if (type == DOUBLE) {
    	   str = "double ";
       }
       else {
    	   str = "String ";
       }
       return identacao + str + super.name + ";";
	}
	
	

}