// Generated from IsiLang.g4 by ANTLR 4.4

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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__11=1, T__10=2, T__9=3, T__8=4, T__7=5, T__6=6, T__5=7, T__4=8, T__3=9, 
		T__2=10, T__1=11, T__0=12, AP=13, FP=14, SC=15, OP=16, ATTR=17, VIR=18, 
		ACH=19, FCH=20, QUOTATION=21, OPREL=22, ID=23, INT=24, DOUBLE=25, STRING=26, 
		WS=27;
	public static final String[] tokenNames = {
		"<INVALID>", "'se'", "'escreva'", "'string'", "'double'", "'int'", "'fimprog;'", 
		"'enquanto'", "'senao'", "'entao'", "'declare'", "'programa'", "'leia'", 
		"'('", "')'", "';'", "OP", "':='", "','", "'{'", "'}'", "'\"'", "OPREL", 
		"ID", "INT", "DOUBLE", "STRING", "WS"
	};
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_expr = 9, RULE_termo = 10, RULE_cmddecisao = 11, RULE_cmdwhile = 12, 
		RULE_exprComparacao = 13, RULE_termoComparacao = 14;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
		"cmdattrib", "expr", "termo", "cmddecisao", "cmdwhile", "exprComparacao", 
		"termoComparacao"
	};

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30); match(T__1);
			setState(31); decl();
			setState(32); bloco();
			setState(33); match(T__6);
			  
						program.setSymbolTable(symbolTable);
						program.setComandos(stackThreads.pop());
			           	 
					
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(36); declaravar();
				}
				}
				setState(39); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaravarContext extends ParserRuleContext {
		public TerminalNode VIR(int i) {
			return getToken(IsiLangParser.VIR, i);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public List<TerminalNode> VIR() { return getTokens(IsiLangParser.VIR); }
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); match(T__2);
			setState(42); tipo();
			setState(43); match(ID);

							_varName = _input.LT(-1).getText();
							_varValue = null;
							verificaIDJaDeclarado(_varName);
							symbol = new IsiVariable(_varName, _tipo, _varValue);
							symbolTable.add(symbol);	
						
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(45); match(VIR);
				setState(46); match(ID);

									_varName = _input.LT(-1).getText();
									_varValue = null;
									verificaIDJaDeclarado(_varName);
									symbol = new IsiVariable(_varName, _tipo, _varValue);
									symbolTable.add(symbol);
								
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53); match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(61);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(55); match(T__7);
				 _tipo = IsiVariable.INTEGER;  
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(57); match(T__8);
				 _tipo = IsiVariable.DOUBLE;  
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 3);
				{
				setState(59); match(T__9);
				 _tipo = IsiVariable.STRING;  
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 
						curThread = new ArrayList<AbstractCommand>(); 
						stackThreads.push(curThread);  
					
			setState(65); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(64); cmd();
				}
				}
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__10) | (1L << T__5) | (1L << T__0) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdwhileContext cmdwhile() {
			return getRuleContext(CmdwhileContext.class,0);
		}
		public CmddecisaoContext cmddecisao() {
			return getRuleContext(CmddecisaoContext.class,0);
		}
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(74);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(69); cmdleitura();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(70); cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(71); cmdattrib();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 4);
				{
				setState(72); cmddecisao();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 5);
				{
				setState(73); cmdwhile();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdleitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdleitura(this);
		}
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); match(T__0);
			setState(77); match(AP);
			setState(78); match(ID);
			 
							verificaIDNaoDeclarado(_input.LT(-1).getText());
							_readID = _input.LT(-1).getText();
						
			setState(80); match(FP);
			setState(81); match(SC);

							IsiVariable var = (IsiVariable)symbolTable.get(_readID);
							var.setUsed();
							CommandLeitura cmd = new CommandLeitura(_readID, var);
							stackThreads.peek().add(cmd);
						
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode STRING() { return getToken(IsiLangParser.STRING, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdescrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdescrita(this);
		}
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84); match(T__10);
			setState(85); match(AP);
			setState(89);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(86); match(ID);
				 verificaIDNaoDeclarado(_input.LT(-1).getText()); 
				}
				break;
			case STRING:
				{
				setState(88); match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

							_writeID = _input.LT(-1).getText();
							if (symbolTable.exists(_writeID))
							{
								IsiVariable var = (IsiVariable)symbolTable.get(_writeID);
								var.setUsed();
							}
							CommandEscrita cmd = new CommandEscrita(_writeID);
						
			setState(92); match(FP);
			setState(93); match(SC);

							stackThreads.peek().add(cmd);
						
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdattribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdattribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdattrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdattrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdattrib(this);
		}
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdattrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); match(ID);
			 
							verificaIDNaoDeclarado(_input.LT(-1).getText());
							_exprID = _input.LT(-1).getText();
							_varAttrib = (IsiVariable)symbolTable.get(_exprID);
							_varAttrib.setUsed();
						
			setState(98); match(ATTR);
			 
								_exprAtribuicao = "";
						
			setState(100); expr();
			setState(101); match(SC);

							CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprAtribuicao);
							stackThreads.peek().add(cmd);
						
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<TerminalNode> OP() { return getTokens(IsiLangParser.OP); }
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public TerminalNode OP(int i) {
			return getToken(IsiLangParser.OP, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104); termo();
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(105); match(OP);

									_op = _input.LT(-1).getText();
									if (!_op.equals("+") && _varAttrib.getType() == IsiVariable.STRING) {
										throw new IsiSemanticException("Only the '+' operator (concatenation) is supported for strings, but identified '" + _op + "' operator at line " + _input.LT(-1).getLine() +".");
									}
									_exprAtribuicao += _op;
							
				setState(107); termo();
				}
				}
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode STRING() { return getToken(IsiLangParser.STRING, 0); }
		public TerminalNode INT() { return getToken(IsiLangParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(IsiLangParser.DOUBLE, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_termo);
		try {
			setState(127);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(113); match(ID);
				 
							verificaIDNaoDeclarado(_input.LT(-1).getText());
							_exprAtribuicao += _input.LT(-1).getText();
							IsiVariable var = (IsiVariable)symbolTable.get(_input.LT(-1).getText());
							verificaTipoIncompativel(_varAttrib, var.getType(), var.getName());
							var.setUsed();
							
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(115); match(INT);

							_term = _input.LT(-1).getText();
							_exprAtribuicao += _term;
							verificaTipoIncompativel(_varAttrib, IsiVariable.INTEGER, _term);
							
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 3);
				{
				setState(117); match(DOUBLE);

							_term = _input.LT(-1).getText();
							_exprAtribuicao += _term;
							verificaTipoIncompativel(_varAttrib, IsiVariable.DOUBLE, _term);
							
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(119); match(STRING);

							_term = _input.LT(-1).getText();
							_exprAtribuicao += _term;
							verificaTipoIncompativel(_varAttrib, IsiVariable.STRING, _term);
							
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 5);
				{
				setState(121); match(AP);

							_term = _input.LT(-1).getText();
							_exprAtribuicao += _term;
							
				setState(123); expr();
				setState(124); match(FP);

							_term = _input.LT(-1).getText();
							_exprAtribuicao += _term;
							
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmddecisaoContext extends ParserRuleContext {
		public List<ExprComparacaoContext> exprComparacao() {
			return getRuleContexts(ExprComparacaoContext.class);
		}
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public ExprComparacaoContext exprComparacao(int i) {
			return getRuleContext(ExprComparacaoContext.class,i);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmddecisaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmddecisao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmddecisao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmddecisao(this);
		}
	}

	public final CmddecisaoContext cmddecisao() throws RecognitionException {
		CmddecisaoContext _localctx = new CmddecisaoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmddecisao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129); match(T__11);
			setState(130); match(AP);
			 _exprComparacao = "";
			setState(132); exprComparacao();
			setState(133); match(OPREL);
			 _exprComparacao += _input.LT(-1).getText(); 
			setState(135); exprComparacao();
			 stackExprDecision.push(_exprComparacao);
			setState(137); match(FP);
			setState(138); match(T__3);
			setState(139); match(ACH);
			 
							curThread = new ArrayList<AbstractCommand>(); 
							stackThreads.push(curThread);
						
			setState(142); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(141); cmd();
				}
				}
				setState(144); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__10) | (1L << T__5) | (1L << T__0) | (1L << ID))) != 0) );
			setState(146); match(FCH);

							listaTrue = stackThreads.pop();	
							listaFalse = new ArrayList<AbstractCommand>(); 
						
			setState(159);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(148); match(T__4);
				setState(149); match(ACH);

									curThread = new ArrayList<AbstractCommand>();
									stackThreads.push(curThread);
								
				{
				setState(152); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(151); cmd();
					}
					}
					setState(154); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__10) | (1L << T__5) | (1L << T__0) | (1L << ID))) != 0) );
				}
				setState(156); match(FCH);

									listaFalse = stackThreads.pop();
								
				}
			}


							_exprComparacao = stackExprDecision.pop();
							CommandDecisao cmd = new CommandDecisao(_exprComparacao, listaTrue, listaFalse);
							stackThreads.peek().add(cmd);
						
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdwhileContext extends ParserRuleContext {
		public List<ExprComparacaoContext> exprComparacao() {
			return getRuleContexts(ExprComparacaoContext.class);
		}
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public ExprComparacaoContext exprComparacao(int i) {
			return getRuleContext(ExprComparacaoContext.class,i);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdwhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdwhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdwhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdwhile(this);
		}
	}

	public final CmdwhileContext cmdwhile() throws RecognitionException {
		CmdwhileContext _localctx = new CmdwhileContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_cmdwhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163); match(T__5);
			setState(164); match(AP);
			 _exprComparacao = "";
			setState(166); exprComparacao();
			setState(167); match(OPREL);
			 _exprComparacao += _input.LT(-1).getText(); 
			setState(169); exprComparacao();
			 stackExprWhile.push(_exprComparacao);
			setState(171); match(FP);
			setState(172); match(ACH);
			 
			        		curThread = new ArrayList<AbstractCommand>(); 
			          		stackThreads.push(curThread);
			        	
			setState(175); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(174); cmd();
				}
				}
				setState(177); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__10) | (1L << T__5) | (1L << T__0) | (1L << ID))) != 0) );
			setState(179); match(FCH);

							_exprComparacao = stackExprWhile.pop();
							listaWhile = stackThreads.pop();
							CommandWhile cmd = new CommandWhile(_exprComparacao, listaWhile);
							stackThreads.peek().add(cmd);
						
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprComparacaoContext extends ParserRuleContext {
		public List<TermoComparacaoContext> termoComparacao() {
			return getRuleContexts(TermoComparacaoContext.class);
		}
		public List<TerminalNode> OP() { return getTokens(IsiLangParser.OP); }
		public TermoComparacaoContext termoComparacao(int i) {
			return getRuleContext(TermoComparacaoContext.class,i);
		}
		public TerminalNode OP(int i) {
			return getToken(IsiLangParser.OP, i);
		}
		public ExprComparacaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprComparacao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExprComparacao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExprComparacao(this);
		}
	}

	public final ExprComparacaoContext exprComparacao() throws RecognitionException {
		ExprComparacaoContext _localctx = new ExprComparacaoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_exprComparacao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182); termoComparacao();
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(183); match(OP);

										_exprComparacao += _input.LT(-1).getText();
								
				setState(185); termoComparacao();
				}
				}
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoComparacaoContext extends ParserRuleContext {
		public ExprComparacaoContext exprComparacao() {
			return getRuleContext(ExprComparacaoContext.class,0);
		}
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode INT() { return getToken(IsiLangParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(IsiLangParser.DOUBLE, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TermoComparacaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termoComparacao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermoComparacao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermoComparacao(this);
		}
	}

	public final TermoComparacaoContext termoComparacao() throws RecognitionException {
		TermoComparacaoContext _localctx = new TermoComparacaoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_termoComparacao);
		try {
			setState(203);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(191); match(ID);
				 
									verificaIDNaoDeclarado(_input.LT(-1).getText());
									_exprComparacao += _input.LT(-1).getText();
									IsiVariable var = (IsiVariable)symbolTable.get(_input.LT(-1).getText());
									var.setUsed();
									
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(193); match(INT);

									_term = _input.LT(-1).getText();
									_exprComparacao += _term;
									
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 3);
				{
				setState(195); match(DOUBLE);

									_term = _input.LT(-1).getText();
									_exprComparacao += _term;
									
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 4);
				{
				setState(197); match(AP);

									_term = _input.LT(-1).getText();
									_exprComparacao += _term;
									
				setState(199); exprComparacao();
				setState(200); match(FP);

									_term = _input.LT(-1).getText();
									_exprComparacao += _term;
									
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\35\u00d0\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\6\3(\n\3\r\3\16\3)\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\63\n\4\f"+
		"\4\16\4\66\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5@\n\5\3\6\3\6\6\6D"+
		"\n\6\r\6\16\6E\3\7\3\7\3\7\3\7\3\7\5\7M\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\5\t\\\n\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\7\13o\n\13\f\13\16\13r\13\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0082\n\f\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u0091\n\r\r\r\16"+
		"\r\u0092\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u009b\n\r\r\r\16\r\u009c\3\r\3\r"+
		"\3\r\5\r\u00a2\n\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\6\16\u00b2\n\16\r\16\16\16\u00b3\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\17\7\17\u00bd\n\17\f\17\16\17\u00c0\13\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00ce\n\20\3\20\2\2\21"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\2\u00d7\2 \3\2\2\2\4\'\3\2\2"+
		"\2\6+\3\2\2\2\b?\3\2\2\2\nA\3\2\2\2\fL\3\2\2\2\16N\3\2\2\2\20V\3\2\2\2"+
		"\22b\3\2\2\2\24j\3\2\2\2\26\u0081\3\2\2\2\30\u0083\3\2\2\2\32\u00a5\3"+
		"\2\2\2\34\u00b8\3\2\2\2\36\u00cd\3\2\2\2 !\7\r\2\2!\"\5\4\3\2\"#\5\n\6"+
		"\2#$\7\b\2\2$%\b\2\1\2%\3\3\2\2\2&(\5\6\4\2\'&\3\2\2\2()\3\2\2\2)\'\3"+
		"\2\2\2)*\3\2\2\2*\5\3\2\2\2+,\7\f\2\2,-\5\b\5\2-.\7\31\2\2.\64\b\4\1\2"+
		"/\60\7\24\2\2\60\61\7\31\2\2\61\63\b\4\1\2\62/\3\2\2\2\63\66\3\2\2\2\64"+
		"\62\3\2\2\2\64\65\3\2\2\2\65\67\3\2\2\2\66\64\3\2\2\2\678\7\21\2\28\7"+
		"\3\2\2\29:\7\7\2\2:@\b\5\1\2;<\7\6\2\2<@\b\5\1\2=>\7\5\2\2>@\b\5\1\2?"+
		"9\3\2\2\2?;\3\2\2\2?=\3\2\2\2@\t\3\2\2\2AC\b\6\1\2BD\5\f\7\2CB\3\2\2\2"+
		"DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\13\3\2\2\2GM\5\16\b\2HM\5\20\t\2IM\5\22"+
		"\n\2JM\5\30\r\2KM\5\32\16\2LG\3\2\2\2LH\3\2\2\2LI\3\2\2\2LJ\3\2\2\2LK"+
		"\3\2\2\2M\r\3\2\2\2NO\7\16\2\2OP\7\17\2\2PQ\7\31\2\2QR\b\b\1\2RS\7\20"+
		"\2\2ST\7\21\2\2TU\b\b\1\2U\17\3\2\2\2VW\7\4\2\2W[\7\17\2\2XY\7\31\2\2"+
		"Y\\\b\t\1\2Z\\\7\34\2\2[X\3\2\2\2[Z\3\2\2\2\\]\3\2\2\2]^\b\t\1\2^_\7\20"+
		"\2\2_`\7\21\2\2`a\b\t\1\2a\21\3\2\2\2bc\7\31\2\2cd\b\n\1\2de\7\23\2\2"+
		"ef\b\n\1\2fg\5\24\13\2gh\7\21\2\2hi\b\n\1\2i\23\3\2\2\2jp\5\26\f\2kl\7"+
		"\22\2\2lm\b\13\1\2mo\5\26\f\2nk\3\2\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2"+
		"q\25\3\2\2\2rp\3\2\2\2st\7\31\2\2t\u0082\b\f\1\2uv\7\32\2\2v\u0082\b\f"+
		"\1\2wx\7\33\2\2x\u0082\b\f\1\2yz\7\34\2\2z\u0082\b\f\1\2{|\7\17\2\2|}"+
		"\b\f\1\2}~\5\24\13\2~\177\7\20\2\2\177\u0080\b\f\1\2\u0080\u0082\3\2\2"+
		"\2\u0081s\3\2\2\2\u0081u\3\2\2\2\u0081w\3\2\2\2\u0081y\3\2\2\2\u0081{"+
		"\3\2\2\2\u0082\27\3\2\2\2\u0083\u0084\7\3\2\2\u0084\u0085\7\17\2\2\u0085"+
		"\u0086\b\r\1\2\u0086\u0087\5\34\17\2\u0087\u0088\7\30\2\2\u0088\u0089"+
		"\b\r\1\2\u0089\u008a\5\34\17\2\u008a\u008b\b\r\1\2\u008b\u008c\7\20\2"+
		"\2\u008c\u008d\7\13\2\2\u008d\u008e\7\25\2\2\u008e\u0090\b\r\1\2\u008f"+
		"\u0091\5\f\7\2\u0090\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0090\3\2"+
		"\2\2\u0092\u0093\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\7\26\2\2\u0095"+
		"\u00a1\b\r\1\2\u0096\u0097\7\n\2\2\u0097\u0098\7\25\2\2\u0098\u009a\b"+
		"\r\1\2\u0099\u009b\5\f\7\2\u009a\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c"+
		"\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\7\26"+
		"\2\2\u009f\u00a0\b\r\1\2\u00a0\u00a2\3\2\2\2\u00a1\u0096\3\2\2\2\u00a1"+
		"\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\b\r\1\2\u00a4\31\3\2\2"+
		"\2\u00a5\u00a6\7\t\2\2\u00a6\u00a7\7\17\2\2\u00a7\u00a8\b\16\1\2\u00a8"+
		"\u00a9\5\34\17\2\u00a9\u00aa\7\30\2\2\u00aa\u00ab\b\16\1\2\u00ab\u00ac"+
		"\5\34\17\2\u00ac\u00ad\b\16\1\2\u00ad\u00ae\7\20\2\2\u00ae\u00af\7\25"+
		"\2\2\u00af\u00b1\b\16\1\2\u00b0\u00b2\5\f\7\2\u00b1\u00b0\3\2\2\2\u00b2"+
		"\u00b3\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2"+
		"\2\2\u00b5\u00b6\7\26\2\2\u00b6\u00b7\b\16\1\2\u00b7\33\3\2\2\2\u00b8"+
		"\u00be\5\36\20\2\u00b9\u00ba\7\22\2\2\u00ba\u00bb\b\17\1\2\u00bb\u00bd"+
		"\5\36\20\2\u00bc\u00b9\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2"+
		"\u00be\u00bf\3\2\2\2\u00bf\35\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c2"+
		"\7\31\2\2\u00c2\u00ce\b\20\1\2\u00c3\u00c4\7\32\2\2\u00c4\u00ce\b\20\1"+
		"\2\u00c5\u00c6\7\33\2\2\u00c6\u00ce\b\20\1\2\u00c7\u00c8\7\17\2\2\u00c8"+
		"\u00c9\b\20\1\2\u00c9\u00ca\5\34\17\2\u00ca\u00cb\7\20\2\2\u00cb\u00cc"+
		"\b\20\1\2\u00cc\u00ce\3\2\2\2\u00cd\u00c1\3\2\2\2\u00cd\u00c3\3\2\2\2"+
		"\u00cd\u00c5\3\2\2\2\u00cd\u00c7\3\2\2\2\u00ce\37\3\2\2\2\20)\64?EL[p"+
		"\u0081\u0092\u009c\u00a1\u00b3\u00be\u00cd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}