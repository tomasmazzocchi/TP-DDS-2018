package ar.edu.utn.dds.ExceptionHandler;

public class CondicionExistenteException extends RuntimeException {

	public CondicionExistenteException() {
		super();
	}

	public CondicionExistenteException(String s) {
		super(s);
	}

	public CondicionExistenteException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public CondicionExistenteException(Throwable throwable) {
		super(throwable);
	}

}
