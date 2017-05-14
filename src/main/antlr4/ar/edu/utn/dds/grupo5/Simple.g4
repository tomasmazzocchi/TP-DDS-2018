grammar Simple;

calc: expr EOF;

expr
:BR_OPEN expr BR_CLOSE
|expr MULTIPLICAR expr
|expr DIVIDIR expr 
|expr SUMAR expr
|expr RESTAR expr
|number
|string
; 

number: NUMBER;
string: (IDCU|IDIN)STRING;

SUMAR:   '+';
RESTAR:  '-';
MULTIPLICAR:  '*';
DIVIDIR:    '/';

NUMBER: '-'? [0-9]+;
STRING: [a-zA-Z]+;
IDCU:'cu.';   //identificar de cuenta
IDIN:'in.';    //idendificador de indicador
BR_OPEN: '(';
BR_CLOSE: ')';

WS: [ \t\r\n]+ -> skip;
