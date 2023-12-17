package com.yan.antlr.demo.boolexpr;

import com.yan.antlr.demo.dto.User;
import com.yan.antlr.demo.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceTest extends BaseTest {

    private static final String INPUT_STR1 = "(\"applications\" or \"concurrency\")";
    private static final String INPUT_STR2 = "(\"applications\" and \"simple\")";
    private static final String INPUT_STR3 = "(\"applications\" and \"simple\" and \"style\")";
    private static final String INPUT_STR4 = "(\"virtual\" or \"server\" or \"language\") and (\"parallel\" or \"change\")";
    private static final String INPUT_STR5 = "(\"virtual\" and \"lightweight\" and \"concurrent\") or (\"applications\" and \"utilization\")";
    private static final String INPUT_STR6 = "(\"virtual\" or \"applications\" or \"concurrent\") or (\"understand\" and \"profile\") and not (\"maintaining\" or \"observing\")";
    private static final String INPUT_STR7 = "(\"virtual\" or \"applications\" or \"concurrent\") or (\"understand\" and \"profile\") and not (\"maintaining\" or \"observing\" or \"independent\")";
    private static final String INPUT_STR8 = "(\"maintaining\") or (\"written\" or \"adopt\") or (\"parallelism\" and \"process\") and not (\"concurrent\" or \"dedicating\")";
    private static final String INPUT_STR9 = "(\"applications\" and \"simple\" and \"style\") or (\"dedicating\") or (\"maintaining\")";
    private static final String INPUT_STR10 = "(\"dedicating\") or (\"maintaining\") and (\"applications\" and \"simple\" and \"style\")";
    @Autowired
    private UserService userService;


    @Test
    public void testGetById() {
        User user = userService.getUserById(6);
        Assertions.assertNotNull(user);
    }

    @Test
    public void testListByInput1() {
        List<User> users = userService.listByInput(INPUT_STR1);
        Assertions.assertNotNull(users);
    }

    @Test
    public void testListByInput2() {
        List<User> users = userService.listByInput(INPUT_STR2);
        Assertions.assertNotNull(users);
    }

    @Test
    public void testListByInput3() {
        List<User> users = userService.listByInput(INPUT_STR3);
        Assertions.assertNotNull(users);
    }

    @Test
    public void testListByInput4() {
        List<User> users = userService.listByInput(INPUT_STR4);
        Assertions.assertNotNull(users);
    }

    @Test
    public void testListByInput5() {
        List<User> users = userService.listByInput(INPUT_STR5);
        Assertions.assertNotNull(users);
    }

    @Test
    public void testListByInput6() {
        List<User> users = userService.listByInput(INPUT_STR6);
        Assertions.assertNotNull(users);
    }

    @Test
    public void testListByInput7() {
        List<User> users = userService.listByInput(INPUT_STR7);
        Assertions.assertNotNull(users);
    }

    @Test
    public void testListByInput8() {
        List<User> users = userService.listByInput(INPUT_STR8);
        for (User user:users) {
            System.out.println(user.getDesc());
        }
        Assertions.assertNotNull(users);
    }

    @Test
    public void testListByInput9() {
        List<User> users = userService.listByInput(INPUT_STR9);
        for (User user:users) {
            System.out.println(user.getDesc());
        }
        Assertions.assertNotNull(users);
    }

    @Test
    public void testListByInput10() {
        List<User> users = userService.listByInput(INPUT_STR10);
        for (User user:users) {
            System.out.println(user.getDesc());
        }
        Assertions.assertNotNull(users);
    }
}
