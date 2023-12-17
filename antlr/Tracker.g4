grammar Tracker;

expr : expr NEWLINE                                   # printExpr
      | LeftBracket expr RightBracket                 # parens
      | expr Logic expr                               # Logic
      | Keyword                                       # keyword
      ;


Logic : AndNotLogic | OrLogic | AndLogic ;
Keyword : '"' [A-Za-z0-9 ]+ '"' ;

AndLogic : A N D ;
OrLogic : O R ;
AndNotLogic : A N D ' ' N O T;

LeftBracket : '(' ;
RightBracket : ')' ;

A : [aA];
N : [nN];
D : [dD];
O : [oO];
R : [rR];
T : [tT];

NEWLINE : '\r'? '\n' ;
WS  :   [ \t\n\r]+ -> skip ;