package ar.edu.utn.dds.ExceptionHandler;

public class IndicadorExistenteException extends RuntimeException {
	public IndicadorExistenteException() {
        super();
    }
    public IndicadorExistenteException(String s) {
        super(s);
    }
    public IndicadorExistenteException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public IndicadorExistenteException(Throwable throwable) {
        super(throwable);
    }
	
}
