package ar.edu.utn.dds.Server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		// aca iniciar la bd / repos
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
		Routes.configure();
	}

}
