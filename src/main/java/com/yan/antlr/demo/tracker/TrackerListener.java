package com.yan.antlr.demo.tracker;// Generated from Tracker.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TrackerParser}.
 */
public interface TrackerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code parens}
	 * labeled alternative in {@link TrackerParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParens(TrackerParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link TrackerParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParens(TrackerParser.ParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code keyword}
	 * labeled alternative in {@link TrackerParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterKeyword(TrackerParser.KeywordContext ctx);
	/**
	 * Exit a parse tree produced by the {@code keyword}
	 * labeled alternative in {@link TrackerParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitKeyword(TrackerParser.KeywordContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Logic}
	 * labeled alternative in {@link TrackerParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLogic(TrackerParser.LogicContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Logic}
	 * labeled alternative in {@link TrackerParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLogic(TrackerParser.LogicContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link TrackerParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpr(TrackerParser.PrintExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link TrackerParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpr(TrackerParser.PrintExprContext ctx);
}