grammar Tracker;

expr : expr NEWLINE                                   # printExpr
      | expr AndLogic expr                            # AndLogic
      | expr OrLogic expr                             # OrLogic
      | expr AndNotLogic expr                         # AndNotLogic
      | LeftBracket expr RightBracket                 # parens
      | Keyword                                       # keyword
      ;


Keyword : '"' [A-Za-z0-9 ]+ '"' ;

AndLogic : 'and' ;
OrLogic : 'or' ;
AndNotLogic : 'and not';

LeftBracket : '(' ;
RightBracket : ')' ;

NEWLINE : '\r'? '\n' ;
WS  :   [ \t\n\r]+ -> skip ;