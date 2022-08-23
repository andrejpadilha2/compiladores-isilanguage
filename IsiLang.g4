grammar IsiLang;

@header{
	import isilanguage.datastructures.IsiSymbol;
	import isilanguage.datastructures.IsiVariable;
	import isilanguage.datastructures.IsiSymbolTable;
	import isilanguage.exceptions.IsiSemanticException;
	import isilanguage.ast.IsiProgram;
	import isilanguage.ast.AbstractCommand;
	import isilanguage.ast.CommandLeitura;
	import isilanguage.ast.CommandEscrita;
	import isilanguage.ast.CommandAtribuicao;
	import isilanguage.ast.CommandDecisao;
	import isilanguage.ast.CommandWhile;
	import java.util.ArrayList;
	import java.util.Stack;
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private IsiVariable _varAttrib;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> curThread;
	private Stack<ArrayList<AbstractCommand>> stackThreads = new Stack<ArrayList<AbstractCommand>>();
	private Stack<String> stackExprDecision = new Stack<String>();
	private Stack<String> stackExprWhile = new Stack<String>();
	private String _term, _op;
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprAtribuicao, _exprComparacao;
	private ArrayList<AbstractCommand> listaTrue, listaFalse, listaWhile;
	
	public void verificaIDNaoDeclarado(String id){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" not declared.");
		}
	}
	
	public void verificaIDJaDeclarado(String id){
		if (symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" already declared.");
		}
	}
	
	public void exibeDeclaracoes(){
		for (IsiSymbol symbol: symbolTable.getAll()) {
			System.out.println(symbol);
		}
	}
	
	public void exibeComandos(){
		for (AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}
	
	public void generateCode(){
		program.generateJavaTarget();
	}
	
	public void neverUsedVariableWarnings(){
		for (IsiSymbol symbol: symbolTable.getAll()) {
			if (symbol instanceof IsiVariable) {
				if (!((IsiVariable)symbol).isUsed()) {
					System.out.println("[WARNING]: Variable '" + symbol.getName() + "' declared and never used.");
				}
			}
		}
	}
	
	public void verificaTipoIncompativel(IsiVariable var, int tipo, String term){
		int tipoVariavel = var.getType();
		
		String atrib = "";
		if (tipo == IsiVariable.INTEGER) {
			atrib = "INTEGER";
		}
		else if (tipo == IsiVariable.DOUBLE) {
			atrib = "DOUBLE";
		}
		else if (tipo == IsiVariable.STRING) {
			atrib = "STRING";
		}
		
		if (tipoVariavel == IsiVariable.INTEGER) {
			if (tipo != IsiVariable.INTEGER) {
				throw new IsiSemanticException("Incompatible types at line "+((TokenStream) _input).LT(-1).getLine()+": variable '"+var.getName()+"' is of type INTEGER and term '"+term+"' is of type "+atrib);
			}
		}
		else if (tipoVariavel == IsiVariable.DOUBLE) {
			if (tipo != IsiVariable.INTEGER && tipo != IsiVariable.DOUBLE) {
				throw new IsiSemanticException("Incompatible types at line "+((TokenStream) _input).LT(-1).getLine()+": variable '"+var.getName()+"' is of type DOUBLE and term '"+term+"' is of type "+atrib);
			}
		}
		else if (tipoVariavel == IsiVariable.STRING) {
			if (tipo != IsiVariable.STRING) {
				throw new IsiSemanticException("Incompatible types at line "+((TokenStream) _input).LT(-1).getLine()+": variable '"+var.getName()+"' is of type STRING and term '"+term+"' is of type "+atrib);
			}
		}
	}
}

prog:	'programa' decl bloco  'fimprog;' {  
			program.setSymbolTable(symbolTable);
			program.setComandos(stackThreads.pop());
           	 
		} 
		;
		
decl:	(declaravar)+
		;
        
        
declaravar:	'declare'
			tipo 
			ID  {
				_varName = _input.LT(-1).getText();
				_varValue = null;
				verificaIDJaDeclarado(_varName);
				symbol = new IsiVariable(_varName, _tipo, _varValue);
				symbolTable.add(symbol);	
			} 
            (
            	VIR 
				ID	{
					_varName = _input.LT(-1).getText();
					_varValue = null;
					verificaIDJaDeclarado(_varName);
					symbol = new IsiVariable(_varName, _tipo, _varValue);
					symbolTable.add(symbol);
				}
			)* 
			SC
			;
           
tipo:	'int' { _tipo = IsiVariable.INTEGER;  }
		|
		'double' { _tipo = IsiVariable.DOUBLE;  }
		|
		'string'  { _tipo = IsiVariable.STRING;  }
		;
        
bloco: 	{ 
			curThread = new ArrayList<AbstractCommand>(); 
			stackThreads.push(curThread);  
		}
		(cmd)+
		;
		

cmd		:	cmdleitura | cmdescrita | cmdattrib | cmddecisao | cmdwhile
		;
		
cmdleitura:	'leia' 
			AP
			ID { 
				verificaIDNaoDeclarado(_input.LT(-1).getText());
				_readID = _input.LT(-1).getText();
			} 
			FP 
			SC {
				IsiVariable var = (IsiVariable)symbolTable.get(_readID);
				var.setUsed();
				CommandLeitura cmd = new CommandLeitura(_readID, var);
				stackThreads.peek().add(cmd);
			}   
			;
			
cmdescrita:	'escreva' 
			AP 
			(
				ID { verificaIDNaoDeclarado(_input.LT(-1).getText()); } 
				|
				STRING 
			) {
				_writeID = _input.LT(-1).getText();
				if (symbolTable.exists(_writeID))
				{
					IsiVariable var = (IsiVariable)symbolTable.get(_writeID);
					var.setUsed();
				}
				CommandEscrita cmd = new CommandEscrita(_writeID);
			}
			FP 
			SC {
				stackThreads.peek().add(cmd);
			}
			;

// checagem de tipos -> atribuição -> lado esquerdo deve ser igual ao resultado da expressão do lado direito
// deve ser possível somar double e int, mas o resultado sempre será double
cmdattrib:	ID { 
				verificaIDNaoDeclarado(_input.LT(-1).getText());
				_exprID = _input.LT(-1).getText();
				_varAttrib = (IsiVariable)symbolTable.get(_exprID);
				_varAttrib.setUsed();
			} 
			ATTR { 
					_exprAtribuicao = "";
			} 
			expr
			SC
			{
				CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprAtribuicao);
				stackThreads.peek().add(cmd);
			}
			;
			
expr:	termo 
			(
			OP  {
					_op = _input.LT(-1).getText();
					if (!_op.equals("+") && _varAttrib.getType() == IsiVariable.STRING) {
						throw new IsiSemanticException("Only the '+' operator (concatenation) is supported for strings, but identified '" + _op + "' operator at line " + _input.LT(-1).getLine() +".");
					}
					_exprAtribuicao += _op;
			}
			termo
			)*
		;
			
termo: ID { 
			verificaIDNaoDeclarado(_input.LT(-1).getText());
			_exprAtribuicao += _input.LT(-1).getText();
			IsiVariable var = (IsiVariable)symbolTable.get(_input.LT(-1).getText());
			verificaTipoIncompativel(_varAttrib, var.getType(), var.getName());
			var.setUsed();
			} 
		| 
		INT {
			_term = _input.LT(-1).getText();
			_exprAtribuicao += _term;
			verificaTipoIncompativel(_varAttrib, IsiVariable.INTEGER, _term);
			}
		|
		DOUBLE {
			_term = _input.LT(-1).getText();
			_exprAtribuicao += _term;
			verificaTipoIncompativel(_varAttrib, IsiVariable.DOUBLE, _term);
			}
		|
		STRING {
			_term = _input.LT(-1).getText();
			_exprAtribuicao += _term;
			verificaTipoIncompativel(_varAttrib, IsiVariable.STRING, _term);
			}
		|
		AP {
			_term = _input.LT(-1).getText();
			_exprAtribuicao += _term;
			}
		expr 
		FP {
			_term = _input.LT(-1).getText();
			_exprAtribuicao += _term;
			}
		;
			
			
cmddecisao:	'se' 
			AP { _exprComparacao = "";} 
			exprComparacao
			OPREL { _exprComparacao += _input.LT(-1).getText(); }
			exprComparacao { stackExprDecision.push(_exprComparacao);}
			FP 
			'entao'
			ACH 
			{ 
				curThread = new ArrayList<AbstractCommand>(); 
				stackThreads.push(curThread);
			}
			(cmd)+ 
			FCH 
			{
				listaTrue = stackThreads.pop();	
				listaFalse = new ArrayList<AbstractCommand>(); 
			} 
			(
				'senao' 
				ACH
				{
					curThread = new ArrayList<AbstractCommand>();
					stackThreads.push(curThread);
				} 
				(cmd+) 
				FCH
				{
					listaFalse = stackThreads.pop();
				}
			)? {
				_exprComparacao = stackExprDecision.pop();
				CommandDecisao cmd = new CommandDecisao(_exprComparacao, listaTrue, listaFalse);
				stackThreads.peek().add(cmd);
			}
            ;
            
cmdwhile:	'enquanto'
			AP { _exprComparacao = "";} 
			exprComparacao
    		OPREL { _exprComparacao += _input.LT(-1).getText(); }
			exprComparacao { stackExprWhile.push(_exprComparacao);}
        	FP
			ACH 
        	{ 
        		curThread = new ArrayList<AbstractCommand>(); 
          		stackThreads.push(curThread);
        	}
        	(cmd)+ 
			FCH {
				_exprComparacao = stackExprWhile.pop();
				listaWhile = stackThreads.pop();
				CommandWhile cmd = new CommandWhile(_exprComparacao, listaWhile);
				stackThreads.peek().add(cmd);
			}
			
		;
		          		
exprComparacao:	termoComparacao 
				(
				OP  {
						_exprComparacao += _input.LT(-1).getText();
				}
				termoComparacao
				)*
		;
			
termoComparacao: ID { 
					verificaIDNaoDeclarado(_input.LT(-1).getText());
					_exprComparacao += _input.LT(-1).getText();
					IsiVariable var = (IsiVariable)symbolTable.get(_input.LT(-1).getText());
					var.setUsed();
					} 
				| 
				INT {
					_term = _input.LT(-1).getText();
					_exprComparacao += _term;
					}
				|
				DOUBLE {
					_term = _input.LT(-1).getText();
					_exprComparacao += _term;
					}
				|
				AP {
					_term = _input.LT(-1).getText();
					_exprComparacao += _term;
					}
				exprComparacao 
				FP {
					_term = _input.LT(-1).getText();
					_exprComparacao += _term;
					}
				;
							
// TOKENS	
AP	: '('
	;
	
FP	: ')'
	;
	
SC	: ';'
	;

OP	: '+' | '-' | '*' | '/' | '%'
	;

	
ATTR : ':='
	 ;
	 
VIR  : ','
     ;
     
ACH  : '{'
     ;
     
FCH  : '}'
     ;
	 
QUOTATION	: '"'
			;
	 
OPREL : '>' | '<' | '>=' | '<=' | '==' | '!='
      ;
      
ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;

INT	: [0-9]+
		;
	
DOUBLE	: [0-9]+ ('.' [0-9]+)?
		;
	
STRING	: QUOTATION ~["\r\n]* QUOTATION
		;
		
WS	: (' ' | '\t' | '\n' | '\r') -> skip;

