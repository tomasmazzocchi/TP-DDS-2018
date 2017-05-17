package ar.edu.utn.dds.ExceptionHandler;

public class ArgumentoIlegalException extends RuntimeException {

	public ArgumentoIlegalException() {
		super();
	}

	public ArgumentoIlegalException(String s) {
		super(s);
	}

	public ArgumentoIlegalException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public ArgumentoIlegalException(Throwable throwable) {
		super(throwable);
	}
}
