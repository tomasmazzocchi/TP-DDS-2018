package ar.edu.utn.dds.rest;

public interface ResponseTransformer {
	
		String render(Object model) throws Exception;

		}

