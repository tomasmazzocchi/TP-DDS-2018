package ar.edu.utn.dds.grupo5;

import java.util.BitSet;
import java.util.List;
import java.util.Locale;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import ar.edu.utn.dds.ExceptionHandler.SintaxisException;
import ar.edu.utn.dds.grupo5.SimpleLexer;
import ar.edu.utn.dds.grupo5.SimpleParser;
import ar.edu.utn.dds.grupo5.SimpleParser.ExprContext;

public class ExpressionParser {
    private final ANTLRErrorListener _listener = createErrorListener();
    

    /**
     * Parses an expression into an integer result.
     * @param expression the expression to part
     * @return and integer result
     */
    public int parse(final String expression,List<Cuenta> listaCuentas,List<Indicador> listaIndicadores) {
        /*
         * Create a lexer that reads from our expression string
         */
        final SimpleLexer lexer = new SimpleLexer(new ANTLRInputStream(expression));

        /*
         * By default Antlr4 lexers / parsers have an attached error listener
         * that prints errors to stderr. I prefer them to throw an exception
         * instead so I implemented my own error listener which is attached
         * here. I also remove any existing error listeners.
         */
        lexer.removeErrorListeners();
        lexer.addErrorListener(_listener);

        /*
         * The lexer reads characters and lexes them into token stream. The
         * tokens are consumed by the parser that then builds an Abstract
         * Syntax Tree.
         */
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final SimpleParser parser = new SimpleParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(_listener);

        /*
         * The ExprContext is the root of our Abstract Syntax Tree
         */
        final ExprContext context = parser.expr();

        /*
         * 'Visit' all the branches of the tree to get our expression result.
         */
        return visit(context,listaCuentas,listaIndicadores);
    }

    /*
     * Visits the branches in the expression tree recursively until we hit a
     * leaf.
     */
    private int visit(final ExprContext context, List<Cuenta> listaCuentas, List<Indicador> listaIndicadores) {
    	Cuenta cuenta;
    	Indicador indicador;
    	String id;
    	String nombre;
    	String cadena;
    	ExpressionParser _parser;

        if (context.number() != null) { //Just a number
            return Integer.parseInt(context.number().getText());
        }
        else if (context.string() != null){
           	cadena = context.string().getText();
        	id=cadena.substring(0,2);  //    cu o in
           	nombre=cadena.substring(3,cadena.length()); // Nombre cuenta o indicador
			if (id.equalsIgnoreCase("cu")){
				cuenta = Cuenta.BuscaCuenta(nombre,listaCuentas);
				if  (cuenta != null) {
					return (int) (cuenta.getValor()); // convert Double to Integer
			    }
				else{
					throw new SintaxisException("No existe el nombre de Cuenta");
				}
				
				
			}
			else{// en esta parte va el indicador
				_parser = new ExpressionParser();
				indicador = Indicador.buscaIndicador(nombre,listaIndicadores);
				if  (indicador != null) {
					return (_parser.parse(indicador.getFormula(),listaCuentas,listaIndicadores));
			    }
				else{
					throw new SintaxisException("No existe el nombre del Indicador");
				}
				
			}
		}
        else if (context.BR_CLOSE() != null) { //Expression between brackets
            return visit(context.expr(0), listaCuentas, listaIndicadores);
        }
        else if (context.MULTIPLICAR() != null) { //Expression * expression
            return visit(context.expr(0), listaCuentas, listaIndicadores) * visit(context.expr(1), listaCuentas, listaIndicadores);
        }
        else if (context.DIVIDIR() != null) { //Expression / expression
            return visit(context.expr(0),listaCuentas, listaIndicadores) / visit(context.expr(1),listaCuentas, listaIndicadores);
        }
        else if (context.SUMAR() != null) { //Expression + expression
            return visit(context.expr(0),listaCuentas, listaIndicadores) + visit(context.expr(1),listaCuentas, listaIndicadores);
        }
        else if (context.RESTAR() != null) { //Expression - expression
            return visit(context.expr(0),listaCuentas, listaIndicadores) - visit(context.expr(1),listaCuentas, listaIndicadores);
        }
        else {
        	throw new IllegalArgumentException();
        }
    }

    /*
     * Helper method to create an ANTLRErrorListener. We're only interested in
     * the syntaxError method.
     */
    private static ANTLRErrorListener createErrorListener() {
        return new ANTLRErrorListener() {
            public void syntaxError(final Recognizer<?, ?> arg0, final Object obj, final int line, final int position, final String message, final RecognitionException ex) {
                throw new IllegalArgumentException(String.format(Locale.ROOT, "Exception parsing expression: '%s' on line %s, position %s", message, line, position));
            }

            public void reportContextSensitivity(final Parser arg0, final DFA arg1, final int arg2, final int arg3, final int arg4, final ATNConfigSet arg5) {
            }

            public void reportAttemptingFullContext(final Parser arg0, final DFA arg1, final int arg2, final int arg3, final BitSet arg4, final ATNConfigSet arg5) {
            }

            public void reportAmbiguity(final Parser arg0, final DFA arg1, final int arg2, final int arg3, final boolean arg4, final BitSet arg5, final ATNConfigSet arg6) {
            }
        };
    }

}
