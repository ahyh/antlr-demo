package com.yan.antlr.demo.tracker;

import com.google.common.collect.Sets;
import com.yan.antlr.demo.dto.Phrases;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MyTrackerVisitor extends TrackerBaseVisitor<Phrases> {

    private List<String> keywords;
    private boolean hasDuplicateKeyword;
    private boolean differentLogicInOneParens;

    @Override
    public Phrases visitParens(TrackerParser.ParensContext ctx) {
        TerminalNode leftBracket = ctx.LeftBracket();
        TerminalNode rightBracket = ctx.RightBracket();
        if (leftBracket == null || rightBracket == null) {
            throw new RuntimeException("bracket error");
        }
        Phrases parensPhrases = visit(ctx.expr());
        if (parensPhrases != null) {
            if (parensPhrases.getType() == 0) {
                // here means () only contains one keyword
                Phrases parent = new Phrases();
                parent.setType(1);
                parent.addChild(parensPhrases);
                return parent;
            }
            List<String> logics = parensPhrases.getLogics();
            if (logics != null) {
                Set<String> logicSet = Sets.newHashSet(logics);
                if (!this.differentLogicInOneParens) {
                    this.differentLogicInOneParens = CollectionUtils.size(logics) != CollectionUtils.size(logicSet);
                }
            }
        }
        return parensPhrases;
    }

    @Override
    public Phrases visitKeyword(TrackerParser.KeywordContext ctx) {
        if (this.keywords == null) {
            this.keywords = new ArrayList<>();
        }
        if (ctx.Keyword() == null || ctx.Keyword().getText() == null) {
            return null;
        }
        String text = ctx.Keyword().getText();
        String k = StringUtils.substring(text, 1, text.length() - 1);
        if (!this.hasDuplicateKeyword) {
            this.hasDuplicateKeyword = this.keywords.contains(k);
        }
        this.keywords.add(k);

        Phrases keyword = new Phrases();
        keyword.setType(0);
        keyword.setKeyword(k);
        return keyword;
    }

    @Override
    public Phrases visitLogic(TrackerParser.LogicContext ctx) {
        Phrases left = visit(ctx.expr(0));
        Phrases right = visit(ctx.expr(1));
        if (left == null || right == null) {
            throw new RuntimeException("error");
        }
        String logic = ctx.Logic().getText();
        return handleLeftAndRight(left, right, logic);
    }

    public Phrases handleLeftAndRight(Phrases left, Phrases right, String logic) {
        if (left.getType() == right.getType() ||
                (left.getType() == 0 && right.getType() == 1)) {
            Phrases parent = new Phrases();
            parent.setType(1);
            parent.addLogic(logic);
            parent.addChild(left);
            parent.addChild(right);
            return parent;
        } else if (left.getType() == 1 && right.getType() == 0) {
            left.addLogic(logic);
            left.addChild(right);
            return left;
        }
        return null;
    }

    @Override
    public Phrases visitErrorNode(ErrorNode node) {
        if (node != null) {
            super.visitErrorNode(node);
            throw new RuntimeException("error");
        }
        return null;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public boolean isHasDuplicateKeyword() {
        return hasDuplicateKeyword;
    }

    public boolean isDifferentLogicInOneParens() {
        return differentLogicInOneParens;
    }
}
