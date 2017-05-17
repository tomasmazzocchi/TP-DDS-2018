package ar.edu.utn.dds.ExceptionHandler;

public class EmpresaExistenteException extends RuntimeException {
	
	public EmpresaExistenteException() {
		super();
	}

	public EmpresaExistenteException(String s) {
		super(s);
	}

	public EmpresaExistenteException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public EmpresaExistenteException(Throwable throwable) {
		super(throwable);
	}

}
