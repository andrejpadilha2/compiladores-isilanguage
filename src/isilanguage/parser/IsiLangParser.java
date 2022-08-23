// Generated from IsiLang.g4 by ANTLR 4.10.1
package isilanguage.parser;

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
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, AP=13, FP=14, SC=15, OP=16, ATTR=17, VIR=18, 
		ACH=19, FCH=20, QUOTATION=21, OPREL=22, ID=23, INT=24, DOUBLE=25, STRING=26, 
		WS=27;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_expr = 9, RULE_termo = 10, RULE_cmddecisao = 11, RULE_cmdwhile = 12, 
		RULE_exprComparacao = 13, RULE_termoComparacao = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
			"cmdattrib", "expr", "termo", "cmddecisao", "cmdwhile", "exprComparacao", 
			"termoComparacao"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog;'", "'declare'", "'int'", "'double'", "'string'", 
			"'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'enquanto'", "'('", 
			"')'", "';'", null, "':='", "','", "'{'", "'}'", "'\"'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", "QUOTATION", 
			"OPREL", "ID", "INT", "DOUBLE", "STRING", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

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
			setState(30);
			match(T__0);
			setState(31);
			decl();
			setState(32);
			bloco();
			setState(33);
			match(T__1);
			  
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
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
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
				setState(36);
				declaravar();
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
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<TerminalNode> VIR() { return getTokens(IsiLangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(IsiLangParser.VIR, i);
		}
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
			setState(41);
			match(T__2);
			setState(42);
			tipo();
			setState(43);
			match(ID);

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
				setState(45);
				match(VIR);
				setState(46);
				match(ID);

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
			setState(53);
			match(SC);
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
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				match(T__3);
				 _tipo = IsiVariable.INTEGER;  
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				match(T__4);
				 _tipo = IsiVariable.DOUBLE;  
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(59);
				match(T__5);
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
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
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
				setState(64);
				cmd();
				}
				}
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << ID))) != 0) );
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
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmddecisaoContext cmddecisao() {
			return getRuleContext(CmddecisaoContext.class,0);
		}
		public CmdwhileContext cmdwhile() {
			return getRuleContext(CmdwhileContext.class,0);
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
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				cmdleitura();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				cmdattrib();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 4);
				{
				setState(72);
				cmddecisao();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 5);
				{
				setState(73);
				cmdwhile();
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
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
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
			setState(76);
			match(T__6);
			setState(77);
			match(AP);
			setState(78);
			match(ID);
			 
							verificaIDNaoDeclarado(_input.LT(-1).getText());
							_readID = _input.LT(-1).getText();
						
			setState(80);
			match(FP);
			setState(81);
			match(SC);

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
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode STRING() { return getToken(IsiLangParser.STRING, 0); }
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
			setState(84);
			match(T__7);
			setState(85);
			match(AP);
			setState(89);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(86);
				match(ID);
				 verificaIDNaoDeclarado(_input.LT(-1).getText()); 
				}
				break;
			case STRING:
				{
				setState(88);
				match(STRING);
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
						
			setState(92);
			match(FP);
			setState(93);
			match(SC);

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
			setState(96);
			match(ID);
			 
							verificaIDNaoDeclarado(_input.LT(-1).getText());
							_exprID = _input.LT(-1).getText();
							_varAttrib = (IsiVariable)symbolTable.get(_exprID);
							_varAttrib.setUsed();
						
			setState(98);
			match(ATTR);
			 
								_exprAtribuicao = "";
						
			setState(100);
			expr();
			setState(101);
			match(SC);

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
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> OP() { return getTokens(IsiLangParser.OP); }
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
			setState(104);
			termo();
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(105);
				match(OP);

									_op = _input.LT(-1).getText();
									if (!_op.equals("+") && _varAttrib.getType() == IsiVariable.STRING) {
										throw new IsiSemanticException("Only the '+' operator (concatenation) is supported for strings, but identified '" + _op + "' operator at line " + _input.LT(-1).getLine() +".");
									}
									_exprAtribuicao += _op;
							
				setState(107);
				termo();
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
		public TerminalNode INT() { return getToken(IsiLangParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(IsiLangParser.DOUBLE, 0); }
		public TerminalNode STRING() { return getToken(IsiLangParser.STRING, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
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
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				match(ID);
				 
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
				setState(115);
				match(INT);

							_term = _input.LT(-1).getText();
							_exprAtribuicao += _term;
							verificaTipoIncompativel(_varAttrib, IsiVariable.INTEGER, _term);
							
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 3);
				{
				setState(117);
				match(DOUBLE);

							_term = _input.LT(-1).getText();
							_exprAtribuicao += _term;
							verificaTipoIncompativel(_varAttrib, IsiVariable.DOUBLE, _term);
							
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(119);
				match(STRING);

							_term = _input.LT(-1).getText();
							_exprAtribuicao += _term;
							verificaTipoIncompativel(_varAttrib, IsiVariable.STRING, _term);
							
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 5);
				{
				setState(121);
				match(AP);

							_term = _input.LT(-1).getText();
							_exprAtribuicao += _term;
							
				setState(123);
				expr();
				setState(124);
				match(FP);

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
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<ExprComparacaoContext> exprComparacao() {
			return getRuleContexts(ExprComparacaoContext.class);
		}
		public ExprComparacaoContext exprComparacao(int i) {
			return getRuleContext(ExprComparacaoContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
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
			setState(129);
			match(T__8);
			setState(130);
			match(AP);
			 _exprComparacao = "";
			setState(132);
			exprComparacao();
			setState(133);
			match(OPREL);
			 _exprComparacao += _input.LT(-1).getText(); 
			setState(135);
			exprComparacao();
			 stackExprDecision.push(_exprComparacao);
			setState(137);
			match(FP);
			setState(138);
			match(T__9);
			setState(139);
			match(ACH);
			 
							curThread = new ArrayList<AbstractCommand>(); 
							stackThreads.push(curThread);
						
			setState(142); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(141);
				cmd();
				}
				}
				setState(144); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << ID))) != 0) );
			setState(146);
			match(FCH);

							listaTrue = stackThreads.pop();	
							listaFalse = new ArrayList<AbstractCommand>(); 
						
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(148);
				match(T__10);
				setState(149);
				match(ACH);

									curThread = new ArrayList<AbstractCommand>();
									stackThreads.push(curThread);
								
				{
				setState(152); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(151);
					cmd();
					}
					}
					setState(154); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << ID))) != 0) );
				}
				setState(156);
				match(FCH);

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
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<ExprComparacaoContext> exprComparacao() {
			return getRuleContexts(ExprComparacaoContext.class);
		}
		public ExprComparacaoContext exprComparacao(int i) {
			return getRuleContext(ExprComparacaoContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
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
			setState(163);
			match(T__11);
			setState(164);
			match(AP);
			 _exprComparacao = "";
			setState(166);
			exprComparacao();
			setState(167);
			match(OPREL);
			 _exprComparacao += _input.LT(-1).getText(); 
			setState(169);
			exprComparacao();
			 stackExprWhile.push(_exprComparacao);
			setState(171);
			match(FP);
			setState(172);
			match(ACH);
			 
			        		curThread = new ArrayList<AbstractCommand>(); 
			          		stackThreads.push(curThread);
			        	
			setState(175); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(174);
				cmd();
				}
				}
				setState(177); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << ID))) != 0) );
			setState(179);
			match(FCH);

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
		public TermoComparacaoContext termoComparacao(int i) {
			return getRuleContext(TermoComparacaoContext.class,i);
		}
		public List<TerminalNode> OP() { return getTokens(IsiLangParser.OP); }
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
			setState(182);
			termoComparacao();
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(183);
				match(OP);

										_exprComparacao += _input.LT(-1).getText();
								
				setState(185);
				termoComparacao();
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
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode INT() { return getToken(IsiLangParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(IsiLangParser.DOUBLE, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public ExprComparacaoContext exprComparacao() {
			return getRuleContext(ExprComparacaoContext.class,0);
		}
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
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(191);
				match(ID);
				 
									verificaIDNaoDeclarado(_input.LT(-1).getText());
									_exprComparacao += _input.LT(-1).getText();
									IsiVariable var = (IsiVariable)symbolTable.get(_input.LT(-1).getText());
									var.setUsed();
									
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(193);
				match(INT);

									_term = _input.LT(-1).getText();
									_exprComparacao += _term;
									
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 3);
				{
				setState(195);
				match(DOUBLE);

									_term = _input.LT(-1).getText();
									_exprComparacao += _term;
									
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 4);
				{
				setState(197);
				match(AP);

									_term = _input.LT(-1).getText();
									_exprComparacao += _term;
									
				setState(199);
				exprComparacao();
				setState(200);
				match(FP);

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
		"\u0004\u0001\u001b\u00ce\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0004"+
		"\u0001&\b\u0001\u000b\u0001\f\u0001\'\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u00021\b\u0002"+
		"\n\u0002\f\u00024\t\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003>\b\u0003"+
		"\u0001\u0004\u0001\u0004\u0004\u0004B\b\u0004\u000b\u0004\f\u0004C\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005K\b"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007Z\b\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0005\tm\b\t"+
		"\n\t\f\tp\t\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0080"+
		"\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0004\u000b\u008f\b\u000b\u000b\u000b\f\u000b\u0090"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0004\u000b\u0099\b\u000b\u000b\u000b\f\u000b\u009a\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u00a0\b\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0004\f\u00b0\b\f\u000b\f\f\f\u00b1\u0001\f\u0001\f"+
		"\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0005\r\u00bb\b\r\n\r\f\r\u00be"+
		"\t\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u00cc\b\u000e\u0001\u000e\u0000\u0000\u000f\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u0000"+
		"\u0000\u00d5\u0000\u001e\u0001\u0000\u0000\u0000\u0002%\u0001\u0000\u0000"+
		"\u0000\u0004)\u0001\u0000\u0000\u0000\u0006=\u0001\u0000\u0000\u0000\b"+
		"?\u0001\u0000\u0000\u0000\nJ\u0001\u0000\u0000\u0000\fL\u0001\u0000\u0000"+
		"\u0000\u000eT\u0001\u0000\u0000\u0000\u0010`\u0001\u0000\u0000\u0000\u0012"+
		"h\u0001\u0000\u0000\u0000\u0014\u007f\u0001\u0000\u0000\u0000\u0016\u0081"+
		"\u0001\u0000\u0000\u0000\u0018\u00a3\u0001\u0000\u0000\u0000\u001a\u00b6"+
		"\u0001\u0000\u0000\u0000\u001c\u00cb\u0001\u0000\u0000\u0000\u001e\u001f"+
		"\u0005\u0001\u0000\u0000\u001f \u0003\u0002\u0001\u0000 !\u0003\b\u0004"+
		"\u0000!\"\u0005\u0002\u0000\u0000\"#\u0006\u0000\uffff\uffff\u0000#\u0001"+
		"\u0001\u0000\u0000\u0000$&\u0003\u0004\u0002\u0000%$\u0001\u0000\u0000"+
		"\u0000&\'\u0001\u0000\u0000\u0000\'%\u0001\u0000\u0000\u0000\'(\u0001"+
		"\u0000\u0000\u0000(\u0003\u0001\u0000\u0000\u0000)*\u0005\u0003\u0000"+
		"\u0000*+\u0003\u0006\u0003\u0000+,\u0005\u0017\u0000\u0000,2\u0006\u0002"+
		"\uffff\uffff\u0000-.\u0005\u0012\u0000\u0000./\u0005\u0017\u0000\u0000"+
		"/1\u0006\u0002\uffff\uffff\u00000-\u0001\u0000\u0000\u000014\u0001\u0000"+
		"\u0000\u000020\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u000035\u0001"+
		"\u0000\u0000\u000042\u0001\u0000\u0000\u000056\u0005\u000f\u0000\u0000"+
		"6\u0005\u0001\u0000\u0000\u000078\u0005\u0004\u0000\u00008>\u0006\u0003"+
		"\uffff\uffff\u00009:\u0005\u0005\u0000\u0000:>\u0006\u0003\uffff\uffff"+
		"\u0000;<\u0005\u0006\u0000\u0000<>\u0006\u0003\uffff\uffff\u0000=7\u0001"+
		"\u0000\u0000\u0000=9\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000"+
		">\u0007\u0001\u0000\u0000\u0000?A\u0006\u0004\uffff\uffff\u0000@B\u0003"+
		"\n\u0005\u0000A@\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000CA\u0001"+
		"\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000D\t\u0001\u0000\u0000\u0000"+
		"EK\u0003\f\u0006\u0000FK\u0003\u000e\u0007\u0000GK\u0003\u0010\b\u0000"+
		"HK\u0003\u0016\u000b\u0000IK\u0003\u0018\f\u0000JE\u0001\u0000\u0000\u0000"+
		"JF\u0001\u0000\u0000\u0000JG\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000"+
		"\u0000JI\u0001\u0000\u0000\u0000K\u000b\u0001\u0000\u0000\u0000LM\u0005"+
		"\u0007\u0000\u0000MN\u0005\r\u0000\u0000NO\u0005\u0017\u0000\u0000OP\u0006"+
		"\u0006\uffff\uffff\u0000PQ\u0005\u000e\u0000\u0000QR\u0005\u000f\u0000"+
		"\u0000RS\u0006\u0006\uffff\uffff\u0000S\r\u0001\u0000\u0000\u0000TU\u0005"+
		"\b\u0000\u0000UY\u0005\r\u0000\u0000VW\u0005\u0017\u0000\u0000WZ\u0006"+
		"\u0007\uffff\uffff\u0000XZ\u0005\u001a\u0000\u0000YV\u0001\u0000\u0000"+
		"\u0000YX\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[\\\u0006\u0007"+
		"\uffff\uffff\u0000\\]\u0005\u000e\u0000\u0000]^\u0005\u000f\u0000\u0000"+
		"^_\u0006\u0007\uffff\uffff\u0000_\u000f\u0001\u0000\u0000\u0000`a\u0005"+
		"\u0017\u0000\u0000ab\u0006\b\uffff\uffff\u0000bc\u0005\u0011\u0000\u0000"+
		"cd\u0006\b\uffff\uffff\u0000de\u0003\u0012\t\u0000ef\u0005\u000f\u0000"+
		"\u0000fg\u0006\b\uffff\uffff\u0000g\u0011\u0001\u0000\u0000\u0000hn\u0003"+
		"\u0014\n\u0000ij\u0005\u0010\u0000\u0000jk\u0006\t\uffff\uffff\u0000k"+
		"m\u0003\u0014\n\u0000li\u0001\u0000\u0000\u0000mp\u0001\u0000\u0000\u0000"+
		"nl\u0001\u0000\u0000\u0000no\u0001\u0000\u0000\u0000o\u0013\u0001\u0000"+
		"\u0000\u0000pn\u0001\u0000\u0000\u0000qr\u0005\u0017\u0000\u0000r\u0080"+
		"\u0006\n\uffff\uffff\u0000st\u0005\u0018\u0000\u0000t\u0080\u0006\n\uffff"+
		"\uffff\u0000uv\u0005\u0019\u0000\u0000v\u0080\u0006\n\uffff\uffff\u0000"+
		"wx\u0005\u001a\u0000\u0000x\u0080\u0006\n\uffff\uffff\u0000yz\u0005\r"+
		"\u0000\u0000z{\u0006\n\uffff\uffff\u0000{|\u0003\u0012\t\u0000|}\u0005"+
		"\u000e\u0000\u0000}~\u0006\n\uffff\uffff\u0000~\u0080\u0001\u0000\u0000"+
		"\u0000\u007fq\u0001\u0000\u0000\u0000\u007fs\u0001\u0000\u0000\u0000\u007f"+
		"u\u0001\u0000\u0000\u0000\u007fw\u0001\u0000\u0000\u0000\u007fy\u0001"+
		"\u0000\u0000\u0000\u0080\u0015\u0001\u0000\u0000\u0000\u0081\u0082\u0005"+
		"\t\u0000\u0000\u0082\u0083\u0005\r\u0000\u0000\u0083\u0084\u0006\u000b"+
		"\uffff\uffff\u0000\u0084\u0085\u0003\u001a\r\u0000\u0085\u0086\u0005\u0016"+
		"\u0000\u0000\u0086\u0087\u0006\u000b\uffff\uffff\u0000\u0087\u0088\u0003"+
		"\u001a\r\u0000\u0088\u0089\u0006\u000b\uffff\uffff\u0000\u0089\u008a\u0005"+
		"\u000e\u0000\u0000\u008a\u008b\u0005\n\u0000\u0000\u008b\u008c\u0005\u0013"+
		"\u0000\u0000\u008c\u008e\u0006\u000b\uffff\uffff\u0000\u008d\u008f\u0003"+
		"\n\u0005\u0000\u008e\u008d\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000"+
		"\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000"+
		"\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0093\u0005\u0014"+
		"\u0000\u0000\u0093\u009f\u0006\u000b\uffff\uffff\u0000\u0094\u0095\u0005"+
		"\u000b\u0000\u0000\u0095\u0096\u0005\u0013\u0000\u0000\u0096\u0098\u0006"+
		"\u000b\uffff\uffff\u0000\u0097\u0099\u0003\n\u0005\u0000\u0098\u0097\u0001"+
		"\u0000\u0000\u0000\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u0098\u0001"+
		"\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u009c\u0001"+
		"\u0000\u0000\u0000\u009c\u009d\u0005\u0014\u0000\u0000\u009d\u009e\u0006"+
		"\u000b\uffff\uffff\u0000\u009e\u00a0\u0001\u0000\u0000\u0000\u009f\u0094"+
		"\u0001\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000\u0000\u00a0\u00a1"+
		"\u0001\u0000\u0000\u0000\u00a1\u00a2\u0006\u000b\uffff\uffff\u0000\u00a2"+
		"\u0017\u0001\u0000\u0000\u0000\u00a3\u00a4\u0005\f\u0000\u0000\u00a4\u00a5"+
		"\u0005\r\u0000\u0000\u00a5\u00a6\u0006\f\uffff\uffff\u0000\u00a6\u00a7"+
		"\u0003\u001a\r\u0000\u00a7\u00a8\u0005\u0016\u0000\u0000\u00a8\u00a9\u0006"+
		"\f\uffff\uffff\u0000\u00a9\u00aa\u0003\u001a\r\u0000\u00aa\u00ab\u0006"+
		"\f\uffff\uffff\u0000\u00ab\u00ac\u0005\u000e\u0000\u0000\u00ac\u00ad\u0005"+
		"\u0013\u0000\u0000\u00ad\u00af\u0006\f\uffff\uffff\u0000\u00ae\u00b0\u0003"+
		"\n\u0005\u0000\u00af\u00ae\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000"+
		"\u0000\u0000\u00b1\u00af\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000"+
		"\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005\u0014"+
		"\u0000\u0000\u00b4\u00b5\u0006\f\uffff\uffff\u0000\u00b5\u0019\u0001\u0000"+
		"\u0000\u0000\u00b6\u00bc\u0003\u001c\u000e\u0000\u00b7\u00b8\u0005\u0010"+
		"\u0000\u0000\u00b8\u00b9\u0006\r\uffff\uffff\u0000\u00b9\u00bb\u0003\u001c"+
		"\u000e\u0000\u00ba\u00b7\u0001\u0000\u0000\u0000\u00bb\u00be\u0001\u0000"+
		"\u0000\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001\u0000"+
		"\u0000\u0000\u00bd\u001b\u0001\u0000\u0000\u0000\u00be\u00bc\u0001\u0000"+
		"\u0000\u0000\u00bf\u00c0\u0005\u0017\u0000\u0000\u00c0\u00cc\u0006\u000e"+
		"\uffff\uffff\u0000\u00c1\u00c2\u0005\u0018\u0000\u0000\u00c2\u00cc\u0006"+
		"\u000e\uffff\uffff\u0000\u00c3\u00c4\u0005\u0019\u0000\u0000\u00c4\u00cc"+
		"\u0006\u000e\uffff\uffff\u0000\u00c5\u00c6\u0005\r\u0000\u0000\u00c6\u00c7"+
		"\u0006\u000e\uffff\uffff\u0000\u00c7\u00c8\u0003\u001a\r\u0000\u00c8\u00c9"+
		"\u0005\u000e\u0000\u0000\u00c9\u00ca\u0006\u000e\uffff\uffff\u0000\u00ca"+
		"\u00cc\u0001\u0000\u0000\u0000\u00cb\u00bf\u0001\u0000\u0000\u0000\u00cb"+
		"\u00c1\u0001\u0000\u0000\u0000\u00cb\u00c3\u0001\u0000\u0000\u0000\u00cb"+
		"\u00c5\u0001\u0000\u0000\u0000\u00cc\u001d\u0001\u0000\u0000\u0000\u000e"+
		"\'2=CJYn\u007f\u0090\u009a\u009f\u00b1\u00bc\u00cb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}