package com.yan.antlr.demo.boolexpr;

import com.yan.antlr.demo.dto.UserDto;
import com.yan.antlr.demo.service.UserTrackerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserTrackerServiceTest extends BaseTest {

    @Autowired
    private UserTrackerService userTrackerService;

    private static final String INPUT_STR1 = "(\"applications\" or \"concurrency\")";
    @Test
    public void test1() {
        List<UserDto> userDtos = userTrackerService.listUserByTracker(INPUT_STR1);
        Assertions.assertNotNull(userDtos);
    }

    private static final String INPUT_STR2 = "(\"applications\" and \"simple\")";
    @Test
    public void test2() {
        List<UserDto> userDtos = userTrackerService.listUserByTracker(INPUT_STR2);
        Assertions.assertNotNull(userDtos);
    }

    private static final String INPUT_STR3 = "(\"applications\" and \"simple\" and \"style\")";
    @Test
    public void test3() {
        List<UserDto> userDtos = userTrackerService.listUserByTracker(INPUT_STR3);
        Assertions.assertNotNull(userDtos);
    }

    private static final String INPUT_STR4 = "(\"virtual\" or \"server\" or \"language\") and (\"parallel\" or \"change\")";
    @Test
    public void test4() {
        List<UserDto> userDtos = userTrackerService.listUserByTracker(INPUT_STR4);
        Assertions.assertNotNull(userDtos);
    }

    private static final String INPUT_STR5 = "(\"virtual\" and \"lightweight\" and \"concurrent\") or (\"applications\" and \"utilization\")";
    @Test
    public void test5() {
        List<UserDto> userDtos = userTrackerService.listUserByTracker(INPUT_STR5);
        Assertions.assertNotNull(userDtos);
    }

    private static final String INPUT_STR6 = "(\"virtual\" or \"applications\" or \"concurrent\") or (\"understand\" and \"profile\") and not (\"maintaining\" or \"observing\")";
    @Test
    public void test6() {
        List<UserDto> userDtos = userTrackerService.listUserByTracker(INPUT_STR6);
        Assertions.assertNotNull(userDtos);
    }

    private static final String INPUT_STR7 = "(\"virtual\" or \"applications\" or \"concurrent\") or (\"understand\" and \"profile\") and not (\"maintaining\" or \"observing\" or \"independent\")";
    @Test
    public void test7() {
        List<UserDto> userDtos = userTrackerService.listUserByTracker(INPUT_STR7);
        Assertions.assertNotNull(userDtos);
    }

    private static final String INPUT_STR8 = "(\"maintaining\") or (\"written\" or \"adopt\") or (\"parallelism\" and \"process\") and not (\"concurrent\" or \"dedicating\")";
    @Test
    public void test8() {
        List<UserDto> userDtos = userTrackerService.listUserByTracker(INPUT_STR8);
        Assertions.assertNotNull(userDtos);
    }

    /**
     * keyword的匹配，是以maintaining这个条件匹配的，但是把applications这个单词也highLight了
     */
    private static final String INPUT_STR9 = "(\"applications\" and \"simple\" and \"style\") or (\"dedicating\") or (\"maintaining\")";
    @Test
    public void test9() {
        List<UserDto> userDtos = userTrackerService.listUserByTracker(INPUT_STR9);
        Assertions.assertNotNull(userDtos);
    }

    private static final String INPUT_STR10 = "(\"dedicating\") or (\"maintaining\") and (\"applications\" and \"simple\" and \"style\")";
    @Test
    public void test10() {
        List<UserDto> userDtos = userTrackerService.listUserByTracker(INPUT_STR10);
        Assertions.assertNotNull(userDtos);
    }
}
