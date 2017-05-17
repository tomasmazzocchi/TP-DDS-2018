package ar.edu.utn.dds.ExceptionHandler;

public class ManejadorIndicadoresException extends RuntimeException {
	
	public ManejadorIndicadoresException() {
		super();
	}

	public ManejadorIndicadoresException(String s) {
		super(s);
	}

	public ManejadorIndicadoresException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public ManejadorIndicadoresException(Throwable throwable) {
		super(throwable);
	}

}
