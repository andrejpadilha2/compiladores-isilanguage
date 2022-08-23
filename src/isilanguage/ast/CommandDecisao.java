package isilanguage.ast;

import java.util.ArrayList;

public class CommandDecisao extends AbstractCommand {
 
	private String condition;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	
	public CommandDecisao(String condition, ArrayList<AbstractCommand> lt, ArrayList<AbstractCommand> lf) {
		this.condition = condition;
		this.listaTrue = lt;
		this.listaFalse = lf;
	}
	
	@Override
	public String generateJavaCode(String identacao) {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		String prevIdentacao;
		
		str.append("\n" + identacao + "if ("+condition+") {\n");
		
		prevIdentacao = identacao;
		identacao = identacao + "    ";
		
		for (AbstractCommand cmd: listaTrue) {
			str.append(cmd.generateJavaCode(identacao) + "\n");
		}
		
		identacao = prevIdentacao;
		str.append(identacao + "}");
		
		if (listaFalse.size() > 0) {
			str.append("\n" + identacao + "else {\n");
			
			prevIdentacao = identacao;
			identacao = identacao + "    ";
			
			for (AbstractCommand cmd: listaFalse) {
				str.append(cmd.generateJavaCode(identacao) + "\n");
			}
			
			identacao = prevIdentacao;
			str.append(identacao + "}");
		
		}
		str.append("\n");
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandDecisao [condition=" + condition + ", listaTrue=" + listaTrue + ", listaFalse=" + listaFalse
				+ "]";
	}

}