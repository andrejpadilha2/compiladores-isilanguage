package isilanguage.ast;

import isilanguage.datastructures.IsiVariable;

public class CommandLeitura extends AbstractCommand {

	private String id;
	private IsiVariable var;
	
	public CommandLeitura (String id, IsiVariable var) {
		this.id = id;
		this.var = var;
	}
	
	@Override
	public String generateJavaCode(String identacao) {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append(identacao + id + " = _key.");
		
		if (var.getType()==IsiVariable.INTEGER) {
			str.append("nextInt();");
			return str.toString();
		} else if (var.getType()==IsiVariable.DOUBLE) {
			str.append("nextDouble();");
			return str.toString();
		} else {
			str.append("nextLine();");
			return str.toString();
		}
	}
	
	@Override
	public String toString() {
		return "CommandLeitura [id=" + id + "]";
	}

}