package isilanguage.exceptions;

public class IsiParseException extends RuntimeException{
	public IsiParseException(String msg, int line, int column) {
		super(msg+ " at line " + line + ", column " + column + ".");
	}

}