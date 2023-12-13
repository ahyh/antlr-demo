package com.yan.antlr.demo.boolexpr;

import com.yan.antlr.demo.helper.BoolComputeHelper;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BoolComputeHelperTest {

    private static final String BOOL_STR1 = "true";
    private static final String BOOL_STR2 = "true and false";
    private static final String BOOL_STR3 = "false and true";
    private static final String BOOL_STR4 = "true or false";
    private static final String BOOL_STR5 = "(true and false)";
    private static final String BOOL_STR6 = "(true and false) or (false or true)";
    private static final String BOOL_STR7 = "(true and false or (false or true))";
    private static final String BOOL_STR8 = "(true or false) and (false or false)";

    private static final List<String> list = List.of(
            BOOL_STR1, BOOL_STR2, BOOL_STR3, BOOL_STR4, BOOL_STR5, BOOL_STR6, BOOL_STR7, BOOL_STR8
    );

    private static final List<String> wrong_list = List.of(
            "true and fal"
    );

    @Test
    public void testCorrect() {
        try {
            for (String input:list) {
                System.out.println(input);
                boolean b = BoolComputeHelper.boolCompute(input);
                System.out.println(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWrong() {
        try {
            for (String input : wrong_list) {
                System.out.println(input);
                boolean b = BoolComputeHelper.boolCompute(input);
                System.out.println(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
