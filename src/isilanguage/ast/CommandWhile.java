package isilanguage.ast;

import java.util.ArrayList;

public class CommandWhile extends AbstractCommand {
	private String condition;
	private ArrayList<AbstractCommand> listaWhile;
	
	public CommandWhile(String condition, ArrayList<AbstractCommand> lw) {
		super();
		this.condition = condition;
		this.listaWhile = lw;
	}
	
	@Override
	public String generateJavaCode(String identacao) {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		String prevIdentacao;
		
		str.append("\n" + identacao + "while ("+condition+") {\n");
		
		prevIdentacao = identacao;
		identacao = identacao + "    ";
		
		for (AbstractCommand cmd: listaWhile) {
			str.append(cmd.generateJavaCode(identacao) + "\n");
		}
		
		identacao = prevIdentacao;
		str.append(identacao + "}");
		
		str.append("\n");
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandWhile [condition=" + condition + ", listaWhile=" + listaWhile
				+ "]";
	}
	
	
}
