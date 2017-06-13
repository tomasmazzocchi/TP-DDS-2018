package ar.edu.utn.dds.ExceptionHandler;

public class CondicionInexistenteException extends RuntimeException {

	public CondicionInexistenteException() {
		super();
	}

	public CondicionInexistenteException(String s) {
		super(s);
	}

	public CondicionInexistenteException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public CondicionInexistenteException(Throwable throwable) {
		super(throwable);
	}

}
