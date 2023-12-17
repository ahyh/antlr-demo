package com.yan.antlr.demo.boolexpr;

import com.yan.antlr.demo.dto.Phrases;
import com.yan.antlr.demo.utils.TrackerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrackerUtilTest {

    private static final String INPUT_STR1 = "(\"a\" or \"b\" or \"c\") and (\"d\" or \"e\") and not (\"f\" or \"g\")";

    private static final String INPUT_STR2 = "(\"a\" and (\"b\" or \"c\"))";

    private static final String INPUT_STR3 = "(\"a\" or \"b\") and (\"c\")";

    private static final String INPUT_STR4 = "(\"a\") or (\"b\" and \"c\")";

    @Test
    public void testParse1() {
        Phrases phrases = TrackerUtil.parse(INPUT_STR1);
        System.out.println(phrases);
    }

    @Test
    public void testParse2() {
        Phrases phrases = TrackerUtil.parse(INPUT_STR2);
        System.out.println(phrases);
    }

    @Test
    public void testParse4() {
        Phrases phrases = TrackerUtil.parse(INPUT_STR4);
        System.out.println(phrases);
    }

    @Test
    public void testParse3() {
        Phrases phrases = TrackerUtil.parse(INPUT_STR3);
        System.out.println(phrases);
    }


    private static final String INPUT_STR11 = "(\"a\" or \"b\") and (\"c\")";
    private static final String INPUT_STR22 = "(\"a\" or \"b\") and (\"c\") and not (\"d\" or \"e\")";
    private static final String INPUT_STR33 = "(\"applications\" and \"simple\")";
    private static final String INPUT_STR44 = "(\"virtual\" and \"lightweight\" and \"concurrent\") or (\"applications\" and \"utilization\")";

    @Test
    public void testParse11() {
        Phrases parse = TrackerUtil.parse(INPUT_STR11);
        TrackerUtil.parsePhrase(parse);
        TrackerUtil.parseQueryBuilder(parse);
        System.out.println(parse.getBoolQueryBuilder());
    }

    @Test
    public void testParse22() {
        Phrases parse = TrackerUtil.parse(INPUT_STR22);
        TrackerUtil.parsePhrase(parse);
        TrackerUtil.parseQueryBuilder(parse);
        System.out.println(parse.getBoolQueryBuilder());
    }

    @Test
    public void testParse33() {
        Phrases parse = TrackerUtil.parse(INPUT_STR33);
        TrackerUtil.parsePhrase(parse);
        TrackerUtil.parseQueryBuilder(parse);
        System.out.println(parse.getBoolQueryBuilder());
    }

    @Test
    public void testParse44() {
        Phrases parse = TrackerUtil.parse(INPUT_STR44);
        TrackerUtil.parsePhrase(parse);
        TrackerUtil.parseQueryBuilder(parse);
        System.out.println(parse.getBoolQueryBuilder());
    }

    private static final String STR_INPUT5 = "(\"applications\" and \"virtual\") or (\"simple\")";

    @Test
    public void trimHighLight() {
        Phrases parse = TrackerUtil.parse(STR_INPUT5);
        String original = "Enable server <em>applications</em> written in the <em>simple</em> thread-per-request style to scale with near-optimal";
        TrackerUtil.trimHighLight(parse, original);
        Assertions.assertNotNull(parse);
    }
}
