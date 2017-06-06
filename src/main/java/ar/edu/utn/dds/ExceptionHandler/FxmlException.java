package ar.edu.utn.dds.ExceptionHandler;

import java.io.IOException;

public class FxmlException extends IOException {

		public FxmlException() {
			super();
		}

		public FxmlException(String s) {
			super(s);
		}

		public FxmlException(String s, Throwable throwable) {
			super(s, throwable);
		}

		public FxmlException(Throwable throwable) {
			super(throwable);
		}
}
