package com.yan.antlr.demo.tracker;// Generated from Tracker.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TrackerParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TrackerVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link TrackerParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(TrackerParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code keyword}
	 * labeled alternative in {@link TrackerParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword(TrackerParser.KeywordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Logic}
	 * labeled alternative in {@link TrackerParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogic(TrackerParser.LogicContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link TrackerParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintExpr(TrackerParser.PrintExprContext ctx);
}