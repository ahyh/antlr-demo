grammar BoolExpr;

expr : expr NEWLINE           # printExpr
      | expr ANDLOGIC expr    # ANDLOGIC
      | expr ORLOGIC expr     # ORLOGIG
      | '(' expr ')'          # parens
      | BOOL                  # assign
      ;

ANDLOGIC : 'and' ;
ORLOGIC : 'or' ;
BOOL : 'true'|'false' ;
NEWLINE : '\r'? '\n' ;
WS  :   [ \t\n\r]+ -> skip ;