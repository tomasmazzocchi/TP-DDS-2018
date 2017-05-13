package ar.edu.utn.dds.ExceptionHandler;

public class ArchivoException extends RuntimeException {

	public ArchivoException() {
        super();
    }
    public ArchivoException(String s) {
        super(s);
    }
    public ArchivoException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public ArchivoException(Throwable throwable) {
        super(throwable);
    }
}
