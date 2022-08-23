package isilanguage.ast;

public class CommandEscrita extends AbstractCommand {

	private String id;
	
	public CommandEscrita(String id) {
		this.id = id;
	}
	
	@Override
	public String generateJavaCode(String identacao) {
		// TODO Auto-generated method stub
		return identacao + "System.out.println("+id+");";
	}
	
	@Override
	public String toString() {
		return "CommandEscrita [id=" + id + "]";
	}
	
}