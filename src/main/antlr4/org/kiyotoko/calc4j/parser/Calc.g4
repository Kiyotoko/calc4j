grammar Calc;

@header {

}

root: (definition|expr) EOF;

definition: NAME DEF expr
    | NAME '(' parameters? ')' DEF expr;
parameters: (NAME (',' NAME)*);

expr: NAME
    | '(' expr ')'
    | call
    | expr (MUL|DIV) expr
    | expr (ADD|SUB) expr
    | expr COM expr
    | number;
call: NAME '(' arguments? ')';
arguments: expr (',' expr)*;
number: (ADD|SUB)? NUMBER;

// Key Symbols
DEF: ':=';

// Operators
ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';

// Equations
COM: EQ|NEQ|LOW|GRE|LEQ|GEQ;
EQ: '=';
NEQ: '!=';
LOW: '<';
GRE:  '>';
LEQ: '<=';
GEQ: '>=';

// Values
NAME: '_'*[a-zA-Z][a-zA-Z0-9_]*;
NUMBER: (INT|FLOAT);
INT: [0-9]+;
FLOAT: [0-9]+'.'[0-9]+;

WS: [ \n\t] -> skip;