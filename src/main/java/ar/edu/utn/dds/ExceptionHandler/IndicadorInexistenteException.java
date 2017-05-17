package ar.edu.utn.dds.ExceptionHandler;

public class IndicadorInexistenteException extends RuntimeException {

	public IndicadorInexistenteException() {
		super();
	}

	public IndicadorInexistenteException(String s) {
		super(s);
	}

	public IndicadorInexistenteException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public IndicadorInexistenteException(Throwable throwable) {
		super(throwable);
	}

}