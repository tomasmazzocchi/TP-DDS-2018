package ar.edu.utn.dds.ExceptionHandler;

public class ExpressionParserException extends RuntimeException {

	public ExpressionParserException() {
		super();
	}

	public ExpressionParserException(String s) {
		super(s);
	}

	public ExpressionParserException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public ExpressionParserException(Throwable throwable) {
		super(throwable);
	}
	
}
