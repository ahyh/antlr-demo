package com.yan.antlr.demo.boolexpr;

import org.antlr.v4.runtime.tree.ErrorNode;

public class MyBoolExprVisitor extends BoolExprBaseVisitor<Boolean> {

    @Override
    public Boolean visitPrintExpr(BoolExprParser.PrintExprContext ctx) {
        Boolean visit = visit(ctx.expr());
        return visit;
    }

    @Override
    public Boolean visitANDLOGIC(BoolExprParser.ANDLOGICContext ctx) {
        boolean left = visit(ctx.expr(0));
        boolean right = visit(ctx.expr(1));
        return left && right;
    }

    @Override
    public Boolean visitORLOGIG(BoolExprParser.ORLOGIGContext ctx) {
        boolean left = visit(ctx.expr(0));
        boolean right = visit(ctx.expr(1));
        return left || right;
    }

    @Override
    public Boolean visitAssign(BoolExprParser.AssignContext ctx) {
        return Boolean.valueOf(ctx.BOOL().getText());
    }

    @Override
    public Boolean visitParens(BoolExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Boolean visitErrorNode(ErrorNode node) {
        if (node != null) {
            System.err.println("visitErrorNode node" + node);
        }
        return super.visitErrorNode(node);
    }

}
