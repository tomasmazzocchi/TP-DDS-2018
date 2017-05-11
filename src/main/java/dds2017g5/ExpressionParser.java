package dds2017g5;

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

import dds2017g5.SimpleParser.ExprContext;

public class ExpressionParser {
    private final ANTLRErrorListener _listener = createErrorListener();

    /**
     * Parses an expression into an integer result.
     * @param expression the expression to part
     * @return and integer result
     */
    public int parse(final String expression,List<Cuenta> listaCuentas) {
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
        return visit(context,listaCuentas);
    }

    /*
     * Visits the branches in the expression tree recursively until we hit a
     * leaf.
     */
    private int visit(final ExprContext context, List<Cuenta> listaCuentas) {
    	Cuenta cuenta;
    	String id;
    	String nombre;
    	String cadena;
        if (context.number() != null) { //Just a number
            return Integer.parseInt(context.number().getText());
        }
        else if (context.string() != null){
        	//System.out.println(context.string().getText());
           	cadena = context.string().getText();
        	id=cadena.substring(0,2);  //    cu o in
           	nombre=cadena.substring(3,cadena.length()); // Nombre cuenta o indicador
           	//System.out.println("id: "+ id + "  nombre: " + nombre);
			if (id.equalsIgnoreCase("cu")){
				cuenta = Cuenta.BuscaCuenta(nombre,listaCuentas);
				//System.out.println("valor " + cuenta.getValor());
				return (int) (cuenta.getValor()); // convert Double to Integer
			}
			else{return(999);}// en esta parte va el indicador
		}
        else if (context.BR_CLOSE() != null) { //Expression between brackets
            return visit(context.expr(0), listaCuentas);
        }
        else if (context.MULTIPLICAR() != null) { //Expression * expression
            return visit(context.expr(0), listaCuentas) * visit(context.expr(1), listaCuentas);
        }
        else if (context.DIVIDIR() != null) { //Expression / expression
            return visit(context.expr(0),listaCuentas) / visit(context.expr(1),listaCuentas);
        }
        else if (context.SUMAR() != null) { //Expression + expression
            return visit(context.expr(0),listaCuentas) + visit(context.expr(1),listaCuentas);
        }
        else if (context.RESTAR() != null) { //Expression - expression
            return visit(context.expr(0),listaCuentas) - visit(context.expr(1),listaCuentas);
        }
        else {
            throw new IllegalStateException();
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
