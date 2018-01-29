package ar.edu.utn.dds.grupo5;

import java.util.BitSet;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import ar.edu.utn.dds.ExceptionHandler.ArgumentoIlegalException;
import ar.edu.utn.dds.grupo5.SimpleLexer;
import ar.edu.utn.dds.grupo5.SimpleParser;
import ar.edu.utn.dds.grupo5.SimpleParser.ExprContext;

public class ExpressionParser {
	private final ANTLRErrorListener _listener = createErrorListener();

	public int resolverFormula(final String expression,Empresa empresa) {

		final SimpleLexer lexer = new SimpleLexer(new ANTLRInputStream(expression));

		lexer.removeErrorListeners();
		lexer.addErrorListener(_listener);

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final SimpleParser parser = new SimpleParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(_listener);

		final ExprContext context = parser.expr();

		return visit(context, empresa);
	}

	private int visit(final ExprContext context, Empresa empresa) {
		Cuenta cuenta;
		Indicador indicador;
		String id;
		String nombre;
		String cadena;
		ExpressionParser parser;

		if (context.number() != null) {
			return Integer.parseInt(context.number().getText());
		} else if (context.string() != null) {
			cadena = context.string().getText();
			id = cadena.substring(0, 2);
			nombre = cadena.substring(3, cadena.length());
			if (id.equalsIgnoreCase("cu")) {
				cuenta = empresa.buscarCuenta(nombre);
				return (int) (cuenta.getValor());
			} else {
				parser = new ExpressionParser();
				indicador = RepoIndicadores.buscarIndicador(nombre, empresa.getListaIndicadores());
				return (parser.resolverFormula(indicador.getFormula(), empresa));
			}
		} else if (context.BR_CLOSE() != null) {
			return visit(context.expr(0), empresa);
		} else if (context.MULTIPLICAR() != null) {
			return visit(context.expr(0), empresa) * visit(context.expr(1), empresa);
		} else if (context.DIVIDIR() != null) {
			return visit(context.expr(0), empresa) / visit(context.expr(1), empresa);
		} else if (context.SUMAR() != null) {
			return visit(context.expr(0), empresa) + visit(context.expr(1), empresa);
		} else if (context.RESTAR() != null) {
			return visit(context.expr(0), empresa) - visit(context.expr(1), empresa);
		} else {
			throw new ArgumentoIlegalException();
		}
	}

	public boolean validarFormula(final String expression) {
		final SimpleLexer lexer = new SimpleLexer(new ANTLRInputStream(expression));

		lexer.removeErrorListeners();
		lexer.addErrorListener(_listener);

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final SimpleParser parser = new SimpleParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(_listener);

		final ExprContext context = parser.expr();

		return visitValidador(context);
	}

	private boolean visitValidador(final ExprContext context) {

		if (context.number() != null) {
			return true;
		} else if (context.string() != null) {
			return true;
		} else if (context.BR_CLOSE() != null) {
			return visitValidador(context.expr(0));
		} else if (context.MULTIPLICAR() != null) {
			return visitValidador(context.expr(0)) && visitValidador(context.expr(1));
		} else if (context.DIVIDIR() != null) {
			return visitValidador(context.expr(0)) && visitValidador(context.expr(1));
		} else if (context.SUMAR() != null) {
			return visitValidador(context.expr(0)) && visitValidador(context.expr(1));
		} else if (context.RESTAR() != null) {
			return visitValidador(context.expr(0)) && visitValidador(context.expr(1));
		} else {
			throw new ArgumentoIlegalException();
		}
	}

	private static ANTLRErrorListener createErrorListener() {
		return new ANTLRErrorListener() {
			public void syntaxError(final Recognizer<?, ?> arg0, final Object obj, final int line, final int position,
					final String message, final RecognitionException ex) {
				throw new ArgumentoIlegalException("Formula no Valida");
			}

			public void reportContextSensitivity(final Parser arg0, final DFA arg1, final int arg2, final int arg3,
					final int arg4, final ATNConfigSet arg5) {
			}

			public void reportAttemptingFullContext(final Parser arg0, final DFA arg1, final int arg2, final int arg3,
					final BitSet arg4, final ATNConfigSet arg5) {
			}

			public void reportAmbiguity(final Parser arg0, final DFA arg1, final int arg2, final int arg3,
					final boolean arg4, final BitSet arg5, final ATNConfigSet arg6) {
			}
		};
	}

}
