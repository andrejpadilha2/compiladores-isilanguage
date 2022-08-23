package isilanguage.ast;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import isilanguage.datastructures.IsiSymbol;
import isilanguage.datastructures.IsiSymbolTable;

public class IsiProgram {
	private IsiSymbolTable symbolTable;
	private ArrayList<AbstractCommand> comandos;
	private String programName;

	public void generateJavaTarget() {
		
		StringBuilder str = new StringBuilder();
		String identacao = "";
		
		// imports e inicio do programa em Java
		str.append("import java.util.Scanner;\n\n");
		str.append("public class MainClass { \n\n");
		
		identacao = "    ";
		str.append(identacao + "public static void main(String args[]) {\n");
		
		identacao = "        ";
		str.append(identacao + "Scanner _key = new Scanner(System.in);\n\n");
		
		// declaracao de variaveis que estao armazenadas na "IsiSymbolTable symbolTable"
		for (IsiSymbol symbol: symbolTable.getAll()) {
			str.append(symbol.generateJavaCode(identacao) + "\n");
		}
		
		str.append("\n");
		
		// comandos que estao armazenados no "ArrayList<AbstractCommand> comandos"
		for (AbstractCommand command: comandos) {
			str.append(command.generateJavaCode(identacao) + "\n");
		}
		
		str.append("\n");
		
		str.append("    }\n");
		str.append("}");
		
		try {
			FileWriter fr = new FileWriter(new File("MainClass.java"));
			fr.write(str.toString());
			fr.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

	}

	public IsiSymbolTable getVarTable() {
		return symbolTable;
	}

	public void setSymbolTable(IsiSymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	public ArrayList<AbstractCommand> getComandos() {
		return comandos;
	}

	public void setComandos(ArrayList<AbstractCommand> comandos) {
		this.comandos = comandos;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

}