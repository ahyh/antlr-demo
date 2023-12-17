package com.yan.antlr.demo.utils;

import com.yan.antlr.demo.dto.Phrases;
import com.yan.antlr.demo.tracker.MyTrackerVisitor;
import com.yan.antlr.demo.tracker.TrackerLexer;
import com.yan.antlr.demo.tracker.TrackerParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrackerUtil {

    /**
     * ("applications" and "virtual") or ("simple")
     */
    public static final String trimHighLight(Phrases phrases, String originHighLightDesc) {
        if (phrases == null || StringUtils.isBlank(originHighLightDesc)) {
            return StringUtils.EMPTY;
        }
        List<String> highLightKeywords = getHighLightKeywords(originHighLightDesc);
        // keyword level set match or not
        setMatchValueForKeyword(phrases, highLightKeywords);

        Set<String> removeKeywords = new HashSet<>();
        Set<String> remainKeywords = new HashSet<>();
        setMatchValueForPhrase(phrases, highLightKeywords, removeKeywords, remainKeywords);

        // 保留词优先
        removeKeywords.removeAll(remainKeywords);
        for (String keyword:removeKeywords) {
            originHighLightDesc = StringUtils.replace(originHighLightDesc, "<em>"+keyword+"</em>", keyword);
        }
        System.out.println(originHighLightDesc);
        return originHighLightDesc;
    }

    public static void setMatchValueForKeyword(Phrases phrases, List<String> highLightKeywords) {
        if (phrases == null || CollectionUtils.isEmpty(highLightKeywords)) {
            return;
        }
        if (phrases.getType() == 0) {
            String keyword = phrases.getKeyword();
            phrases.setMatch(StringUtils.isNotBlank(keyword) && highLightKeywords.contains(keyword));
            return;
        }
        List<String> logics = phrases.getLogics();
        List<Phrases> children = phrases.getChildren();
        Boolean flag = null;
        for (Phrases child : children) {
            setMatchValueForKeyword(child, highLightKeywords);
            if (flag == null) {
                flag = child.isMatch();
            } else {
                if (isAnd(logics)) {
                    flag = flag && child.isMatch();
                } else if (isOr(logics)) {
                    flag = flag || child.isMatch();
                }
            }
        }
        phrases.setMatch(flag);
    }

    public static void setMatchValueForPhrase(Phrases phrases, List<String> highLightKeywords, Set<String> removeKeywords, Set<String> remainKeywords) {
        if (phrases == null) {
            return;
        }
        if (phrases.getType() == 0) {
            return;
        }

        List<String> logics = phrases.getLogics();
        List<Phrases> children = phrases.getChildren();
        if (isKeywordLevelNext(children)) {
            // 如果下个level都是词
            if (CollectionUtils.isEmpty(logics)) {
                List<String> andKeywords = children.stream().map(Phrases::getKeyword).toList();
                remainKeywords.addAll(andKeywords);
                return;
            }
            if (isAnd(logics)) {
                // 如果是and连接的词，都出现在highLightKeywords中，就都保留，否则都移除
                List<String> andKeywords = children.stream().map(Phrases::getKeyword).toList();
                if (CollectionUtils.containsAll(highLightKeywords, andKeywords)) {
                    remainKeywords.addAll(andKeywords);
                } else {
                    removeKeywords.addAll(andKeywords);
                }
            } else if (isOr(logics)) {
                // 如果是or连接的词，保留任何出现在highLightKeywords中的词
                List<String> orKeywords = children.stream().map(Phrases::getKeyword).toList();
                remainKeywords.addAll(orKeywords);
            }
        } else {
            if (CollectionUtils.isEmpty(logics)) {
                setMatchValueForPhrase(children.get(0), highLightKeywords, removeKeywords, remainKeywords);
            }
            Phrases left = children.get(0);
            Phrases right = children.get(1);
            if (isAndNot(logics)) {
                setMatchValueForPhrase(left, highLightKeywords, removeKeywords, remainKeywords);
            } else {
                setMatchValueForPhrase(left, highLightKeywords, removeKeywords, remainKeywords);
                setMatchValueForPhrase(right, highLightKeywords, removeKeywords, remainKeywords);
            }
        }
    }

    public static List<String> getHighLightKeywords(String originHighLightDesc) {
        List<String> keywords = new ArrayList<>();
        int pos = 0;

        while (true) {
            int prefix = StringUtils.indexOf(originHighLightDesc, "<em>", pos);
            int suffix = StringUtils.indexOf(originHighLightDesc, "</em>", pos);
            if (prefix == -1 || suffix == -1 || prefix >= suffix) {
                break;
            }
            keywords.add(StringUtils.substring(originHighLightDesc, prefix + 4, suffix));
            pos = suffix + 4;
        }
        return keywords;
    }

    public static final Phrases parse(String input) {
        if (StringUtils.isBlank(input) || !StringUtils.startsWith(input, "(") || !StringUtils.endsWith(input, ")")) {
            return null;
        }
        if (StringUtils.containsAny(input, "(((", ")))")) {
            return null;
        }
        CodePointCharStream stream = CharStreams.fromString(input);
        TrackerLexer lexer = new TrackerLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TrackerParser parser = new TrackerParser(tokens);
        ParseTree expr = parser.expr();
        MyTrackerVisitor visitor = new MyTrackerVisitor();
        Phrases phrases = visitor.visit(expr);
        System.out.println("Keywords : " + visitor.getKeywords());
        System.out.println("Same keyword : " + visitor.isHasDuplicateKeyword());
        System.out.println("different logic : " + visitor.isDifferentLogicInOneParens());
        return phrases;
    }

    public static void parseQueryBuilder(Phrases phrases) {
        if (phrases == null || phrases.getBoolQueryBuilder() == null) {
            return;
        }
        List<String> logics = phrases.getLogics();
        List<Phrases> children = phrases.getChildren();
        if (CollectionUtils.isEmpty(logics)) {
            return;
        }
        Phrases left = children.get(0);
        Phrases right = children.get(1);
        if (left.getBoolQueryBuilder() == null || right.getBoolQueryBuilder() == null) {
            return;
        }
        parseQueryBuilder(left);
        parseQueryBuilder(right);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (isAndNot(logics)) {
            // right is and not
            boolQueryBuilder.should(left.getBoolQueryBuilder());
            boolQueryBuilder.mustNot(right.getBoolQueryBuilder());
        } else if (isAnd(logics)) {
            boolQueryBuilder.should(left.getBoolQueryBuilder());
            boolQueryBuilder.should(right.getBoolQueryBuilder());
            boolQueryBuilder.minimumShouldMatch(2);
        } else if (isOr(logics)) {
            boolQueryBuilder.should(left.getBoolQueryBuilder());
            boolQueryBuilder.should(right.getBoolQueryBuilder());
            boolQueryBuilder.minimumShouldMatch(1);
        }
        phrases.setBoolQueryBuilder(boolQueryBuilder);
    }

    public static QueryBuilder parsePhrase(Phrases phrases) {
        if (phrases == null) {
            return null;
        }
        if (phrases.getType() == 0) {
            return QueryBuilders.matchPhraseQuery("desc", phrases.getKeyword());
        }
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        List<String> logics = phrases.getLogics();
        List<Phrases> children = phrases.getChildren();
        if (CollectionUtils.isEmpty(logics) && CollectionUtils.isEmpty(children)) {
            return null;
        }
        for (Phrases child : children) {
            if (child == null) {
                continue;
            }
            boolQueryBuilder.should(parsePhrase(child));
        }

        if (CollectionUtils.isEmpty(logics)) {
            boolQueryBuilder.minimumShouldMatch(1);
        } else {
            if (isAnd(logics)) {
                boolQueryBuilder.minimumShouldMatch(logics.size() + 1);
            } else if (isOr(logics)) {
                boolQueryBuilder.minimumShouldMatch(1);
            }
        }

        phrases.setBoolQueryBuilder(boolQueryBuilder);

        return boolQueryBuilder;
    }

    public static boolean isAnd(List<String> logics) {
        return "and".equals(logics.get(0));
    }

    public static boolean isOr(List<String> logics) {
        return "or".equals(logics.get(0));
    }

    public static boolean isAndNot(List<String> logics) {
        return "and not".equals(logics.get(0));
    }

    public static boolean isKeywordLevelNext(List<Phrases> children) {
        if (CollectionUtils.isEmpty(children)) {
            return false;
        }
        return children.get(0).getType() == 0;
    }
}
