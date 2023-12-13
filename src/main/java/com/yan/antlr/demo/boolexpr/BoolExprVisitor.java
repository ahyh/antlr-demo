package com.yan.antlr.demo.boolexpr;// Generated from BoolExpr.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BoolExprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BoolExprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link BoolExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(BoolExprParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ORLOGIG}
	 * labeled alternative in {@link BoolExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitORLOGIG(BoolExprParser.ORLOGIGContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ANDLOGIC}
	 * labeled alternative in {@link BoolExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitANDLOGIC(BoolExprParser.ANDLOGICContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link BoolExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(BoolExprParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link BoolExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintExpr(BoolExprParser.PrintExprContext ctx);
}