package isilanguage.main;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import isilanguage.exceptions.IsiParseException;
import isilanguage.exceptions.IsiSemanticException;
import isilanguage.parser.IsiLangLexer;
import isilanguage.parser.IsiLangParser;


/* esta é a classe que é responsável por criar a interação com o usuário
 * instanciando nosso parser e apontando para o arquivo fonte
 * 
 * Arquivo fonte: extensao .isi
 * 
 */
public class MainClass {
	public static void main(String[] args) {
		try {
			IsiLangLexer lexer;
			IsiLangParser parser;
			
			
			// leio o arquivo "input.isi" e isso é entrada para o Analisador Lexico
			lexer = new IsiLangLexer(CharStreams.fromFileName("input2.isi"));
			
			// crio um "fluxo de tokens" para passar para o PARSER
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			// crio meu parser a partir desse tokenStream
			parser = new IsiLangParser(tokenStream);
			//parser.setErrorHandler(new BailErrorStrategy());
			
			parser.removeErrorListeners();

			//final List<String> errorMessages = new ArrayList<>();

			parser.addErrorListener(new BaseErrorListener(){
			    @Override
			    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
			    	throw new IsiParseException(msg, line, charPositionInLine);
			        //errorMessages.add(msg+ " at line " + line + ", column " + charPositionInLine + ".");
			    }
			});
			
			parser.prog(); // comeca a compilacao em si
			

			parser.generateCode();
				
			//parser.exibeDeclaracoes(); // descomentar para mostrar todas as declaracoes identificadas
			//parser.exibeComandos(); // descomentar para mostrar todos os comandos identificados
				
			parser.neverUsedVariableWarnings();
				
			System.out.println("Compilation Successful");

		}
		catch(IsiSemanticException ex) {
			System.err.println("Semantic error - "+ex.getMessage());
		}
		catch(IsiParseException ex) {
			System.err.println("Parsing error - "+ex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.err.println("ERROR "+ex.getMessage());
		}
	}
}