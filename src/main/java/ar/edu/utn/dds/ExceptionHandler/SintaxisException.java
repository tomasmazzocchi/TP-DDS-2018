package ar.edu.utn.dds.ExceptionHandler;

public class SintaxisException extends RuntimeException {
	
	public SintaxisException() {
		super();
	}

	public SintaxisException(String s) {
		super(s);
	}

	public SintaxisException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public SintaxisException(Throwable throwable) {
		super(throwable);
	}

}
