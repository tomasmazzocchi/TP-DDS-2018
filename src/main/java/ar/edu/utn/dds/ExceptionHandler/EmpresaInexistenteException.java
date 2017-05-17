package ar.edu.utn.dds.ExceptionHandler;

public class EmpresaInexistenteException extends RuntimeException {

	public EmpresaInexistenteException() {
		super();
	}

	public EmpresaInexistenteException(String s) {
		super(s);
	}

	public EmpresaInexistenteException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public EmpresaInexistenteException(Throwable throwable) {
		super(throwable);
	}

}
