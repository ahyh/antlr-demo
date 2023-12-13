package com.yan.antlr.demo.helper;


import com.yan.antlr.demo.boolexpr.BoolExprLexer;
import com.yan.antlr.demo.boolexpr.BoolExprParser;
import com.yan.antlr.demo.boolexpr.MyBoolExprVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.stereotype.Component;

@Component
public class BoolComputeHelper {

    public Boolean compute(String input) {
        try {
            return boolCompute(input);
        } catch (Exception e) {
            return null;
        }
    }

    public static Boolean boolCompute(String input) throws Exception {
        CodePointCharStream stream = CharStreams.fromString(input);

        BoolExprLexer lexer = new BoolExprLexer(stream);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        BoolExprParser parser = new BoolExprParser(tokens);

        ParseTree expr = parser.expr();

        MyBoolExprVisitor myBoolExprVisitor = new MyBoolExprVisitor();

        Boolean visit = myBoolExprVisitor.visit(expr);

        return visit;
    }
}
