package ar.edu.utn.dds.ExceptionHandler;

public class RepoIndicadoresException extends RuntimeException {
	
	public RepoIndicadoresException() {
		super();
	}

	public RepoIndicadoresException(String s) {
		super(s);
	}

	public RepoIndicadoresException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public RepoIndicadoresException(Throwable throwable) {
		super(throwable);
	}

}
