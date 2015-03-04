grammar AQL;

//import AQLMain;

// 
// cd ...ariel/etc
// java -cp "/home/sergep/dev/antlr-4.1-complete.jar" org.antlr.v4.Tool -o ../src/com/q1labs/ariel/ql/parser/antlr -encoding UTF-8 -listener -visitor -package com.q1labs.ariel.ql.parser.antlr AQL.g4

criteria
    : 
     (
        FTS StringLiteral |
        (FTS StringLiteral AND)? booleanExpression
     ) SEMI?
    ;
        

statement
        :( 
           query 
//         | sortCursor 
//         | getCursor
//         | removeCursor
//         | describe
         //| listAllCursors
         //| getServerData
         ) SEMI?
        ;


query    : SELECT displayList 
           
           FROM  databases+=database (COMMA databases+=database)* 
           
           (INTO cursorName=Identifier)?

           whereClause?
           
           groupBy?
           orderBy?
           
           (LIMIT limit=IntegerLiteral)?
           
           queryTime?
           queryParams?
         ;

queryTime
    : START startTime=StringLiteral (STOP stopTime=StringLiteral)?
    | LAST minutes=IntegerLiteral (MINUTES|HOURS|DAYS)
    ;         

queryParams
    : PARAMETERS params+=parameter (COMMA params+=parameter)*
    ;

parameter
    : id=Identifier EQ val=literal
    ;

database
    : name=Identifier (AS? alias=(Identifier | StringLiteral))?
    ;

displayList
    : columns+=displayColumn (COMMA columns+=displayColumn)*
    ;

displayColumn
//    | db=Identifier DOT ASTERISK                                  #DisplayColumnDBAll
    : expression (AS? alias=(Identifier | StringLiteral))?          #DisplayColumnExpression
    //| aggregateFunction (AS? alias=(Identifier | StringLiteral))? #DisplayColumnCounter
    | ASTERISK							    #DisplayColumnAll
    ;

whereClause 
    : WHERE booleanExpression
    | WHERE FTS fts=StringLiteral
    | WHERE FTS fts=StringLiteral AND booleanExpression
    ;

       
orderBy
    : ORDER_BY expressionList
      order=(ASC|DESC)?
      (COLLATE lang=StringLiteral)? //IETF BCP 47 language tag string
    ;

subquery : SELECT;


groupBy: GROUP_BY expressionList (HAVING booleanExpression)?;

booleanExpression
 :  (NOT)? scalarFunction
 |  (NOT)? LPAREN be=booleanExpression RPAREN
 |  left=booleanExpression bop=( AND | OR ) right=booleanExpression
 |  (ALL)? expression booleanOperator
 ;
 
booleanOperator
    :comparisonOperator | betweenOperator | likeOperator | nullOperator;

comparisonOperator  : cop=(EQ | NEQ | LT | LE | GT |GE) expression ;

betweenOperator     : (NOT)? BETWEEN begin=expression AND end=expression ;

likeOperator        : op=(LIKE|ILIKE|REGEX|IREGEX) pattern=StringLiteral ;

nullOperator        : ISNULL | NOTNULL ;

//inOperator     : (NOT)? IN LPAREN (subquery| expressionList) RPAREN;

expressionList
    :   expr+=expression (COMMA expr+=expression)*
    ;

expression
    :   scalarFunction
    |   LPAREN expression arithmeticOperator RPAREN
    |   expression arithmeticOperator
    |   column
    |   literal
    ;


arithmeticOperator
    :   op=POWER_OP expression
    |   op=ASTERISK expression
    |   op = Divide expression
    |   op = Modulo expression
    |   op=Add expression
    |   op=Subtract expression
    ;


column:(db=Identifier DOT)? name=Identifier;

scalarFunction
    :   (db=Identifier DOT)? name=Identifier LPAREN arguments? RPAREN
    |   agg=COUNT LPAREN (ASTERISK?|expression) RPAREN
    |   agg=(FIRST | SUM |AVG | MIN | MAX | UCOUNT) LPAREN expression RPAREN
    ;   

arguments
    :   args+=expression (COMMA args+=expression)*
    //:   (MINUS)?expression (COMMA (MINUS)?expression)*
    ;

aggregateFunction
    : agg=COUNT LPAREN (ASTERISK?|expression) RPAREN
    | agg=(FIRST | SUM |AVG | MIN | MAX | UCOUNT) LPAREN expression RPAREN
    ;


literal
    :   Subtract?IntegerLiteral         #LiteralInteger
    |   Subtract?FloatingPointLiteral   #LiteralFloat
    |   StringLiteral                   #LiteralString
    |   BooleanLiteral                  #LiteralBoolean
    |   NullLiteral                     #LiteralNull
    ;


//////////////////////////////////////////////

// Keywords


ISNULL  :   I S N U L L | I S WS N U L L;
NOTNULL :   N O T N U L L  | I S WS N O T WS N U L L;
AND	:   A N D;
OR	:   O R;
NOT	:   N O T;
BETWEEN	:   B E T W E E N ;
LIKE    :   L I K E ;
ILIKE   :   I L I K E ;
REGEX   :   M A T C H E S ;
IREGEX  :   I M A T C H E S ;

LIMIT   :       L I M I T ;
GROUP_BY:	G R O U P WS B Y ;
HAVING  :       H A V I N G ;
ORDER_BY:       O R D E R WS B Y ;
FROM	:	F R O M;
WHERE	:	W H E R E;
ASC     :       A S C E N D I N G | A S C ;
DESC    :       D E S C E N D I N G | D E S C ;
COLLATE :       C O L L A T E ;
INTO    :       I N T O ;
IN    :       I N;
START   :       S T A R T ;
STOP    :       S T O P ;
LAST    :       L A S T ;
MINUTES :       M I N U T E S;
HOURS :       H O U R S;
DAYS :       D A Y S;
ALL     :       A L L;
AS      :       A S;
//DISTINCT:   D I S T I N C T;

FTS     :       T E X T WS S E A R C H;


COUNT	:	C O U N T;// ('(' ASTERISK? ')')?;
FIRST   :       F I R S T;
SUM     :       S U M;
AVG     :       A V G;
MIN     :       M I N;
MAX     :       M A X;
UCOUNT  :       U N I Q U E C O U N T;

// STATEMENTS
SELECT  :       S E L E C T ;
SORTCURSOR:     S O R T WS C U R S O R;
GETCURSOR:      G E T WS C U R S O R;
REMOVECURSOR:   R E M O V E WS C U R S O R;
DESCRIBE:       D E S C R I B E ;

PARAMETERS  :  P A R A M E T E R S | P A R A M S;

TIMEINTERVAL:  T I M E I N T E R V A L;

IntegerLiteral
    :   DecimalNumeral
//    |   HexIntegerLiteral
//    |   OctalIntegerLiteral
//    |   BinaryIntegerLiteral
    ;

//SignedIntegerLiteral : Sign Digits;

FloatingPointLiteral
:
    DecimalFloatingPointLiteral
;

fragment
DecimalFloatingPointLiteral
    :   Digits DOT Digits? ExponentPart?
    |   DOT Digits ExponentPart?
    |   Digits ExponentPart
    |   Digits 
    ;

fragment
DecimalNumeral
    :   '0'
    |   NonZeroDigit (Digits)?
    ;

fragment
Digits
    :   Digit+
    ;


fragment
Digit
    :   '0'
    |   NonZeroDigit
    ;

fragment
NonZeroDigit
    :   [1-9]
    ;

fragment
ExponentPart
    :   ExponentIndicator SignedInteger
    ;

fragment
ExponentIndicator
    :   [eE]
    ;

fragment
SignedInteger
    :   Sign? Digits
    ;

fragment
Sign
    :   [+-]
    ;

Identifier
	:	( Letter | ['_'] ) LetterOrDigit*
	;

NullLiteral
    :   N U L L
    ;

BooleanLiteral
    :   'true'
    |   'false'
    ;

StringLiteral
    :   QUOTE StringCharacters? QUOTE
        { String s=getText(); setText(s.substring(1, s.length()-1));}
    ;

fragment
StringCharacters
    :   StringCharacter+
    ;

fragment
StringCharacter
    :   ~['\\]
//    |   EscapeSequence
    ;

fragment
Letter
	:	[a-zA-Z$_]
	;

fragment
LetterOrDigit
	:	[a-zA-Z0-9$_]
	;

ASTERISK  : '*';

//Arithmetic Operators
Add     : PLUS;
Subtract: MINUS;
Multiply: ASTERISK;
Divide  : SLASH;
Modulo  : '%';

//ASTERISK  : '*';



EQ	:   '=' | '==';
NEQ	:   '!=' | '<>';
LT    	:   '<';
LE  	:   '<=';
GT    	:   '>';
GE  	:   '>=';


QUOTE     : '\'';
SEMI      : ';' ;
COLON     : ':' ;
DOT       : '.' ;
COMMA     : ',' ;
RPAREN    : ')' ;
LPAREN    : '(' ;
RBRACK    : ']' ;
LBRACK    : '[' ;
PLUS      : '+' ;
MINUS     : '-' ;
NEGATION  : '~' ;
VERTBAR   : '|' ;
BITAND    : '&' ;
POWER_OP  : '^' ;
SLASH     : '/' ;

fragment A: [aA];
fragment B: [bB];
fragment C: [cC];
fragment D: [dD];
fragment E: [eE];
fragment F: [fF];
fragment G: [gG];
fragment H: [hH];
fragment I: [iI];
fragment J: [jJ];
fragment K: [kK];
fragment L: [lL];
fragment M: [mM];
fragment N: [nN];
fragment O: [oO];
fragment P: [pP];
fragment Q: [qQ];
fragment R: [rR];
fragment S: [sS];
fragment T: [tT];
fragment U: [uU];
fragment V: [vV];
fragment W: [wW];
fragment X: [xX];
fragment Y: [yY];
fragment Z: [zZ];

// Whitespace and comments
//
WS  :  [ \t\r\n\u000C]+ -> skip
    ;

COMMENT
    :   '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> skip
    ;

