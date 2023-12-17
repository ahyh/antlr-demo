package com.yan.antlr.demo.boolexpr;

import com.yan.antlr.demo.dto.User;
import com.yan.antlr.demo.dto.UserDto;
import com.yan.antlr.demo.service.UserService;
import com.yan.antlr.demo.utils.JacksonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceCrudTest extends BaseTest {

    private static final List<String> JSON_LIST = List.of(
            "{\"id\":1,\"name\":\"yan yan\",\"email\":\"yan.yan@sina.com\",\"user_type\":[1,2,3],\"desc\":\"Introduce virtual threads to the Java Platform. Virtual threads are lightweight threads that dramatically reduce the effort of writing, maintaining, and observing high-throughput concurrent applications. \"}",
            "{\"id\":2,\"name\":\"tao tao\",\"email\":\"tao.tao@sina.com\",\"user_type\":[1,4],\"desc\":\"Enable server applications written in the simple thread-per-request style to scale with near-optimal hardware utilization.\"}",
            "{\"id\":3,\"name\":\"cao cao\",\"email\":\"cao.cao@163.com\",\"user_type\":[2,4],\"desc\":\"Enable existing code that uses the java.lang.Thread API to adopt virtual threads with minimal change.\"}",
            "{\"id\":4,\"name\":\"wen wen\",\"email\":\"wen.wen@163.com\",\"user_type\":[0],\"desc\":\"It is not a goal to offer a new data parallelism construct in either the Java language or the Java libraries. The Stream API remains the preferred way to process large data sets in parallel.\"}",
            "{\"id\":5,\"name\":\"gao gao\",\"email\":\"gao.gao@gmail.com\",\"user_type\":[6,7],\"desc\":\"Server applications generally handle concurrent user requests that are independent of each other, so it makes sense for an application to handle a request by dedicating a thread to that request for its entire duration.\"}",
            "{\"id\":6,\"name\":\"yin yin\",\"email\":\"yin.yin@gmail.com\",\"user_type\":[2,5],\"desc\":\"This thread-per-request style is easy to understand, easy to program, and easy to debug and profile because it uses the platform's unit of concurrency to represent the application's unit of concurrency.\"}",
            "{\"id\":7,\"name\":\"yang yang\",\"email\":\"yang.yang@sina.com\",\"user_type\":[3,4,5],\"desc\":\"Unfortunately, the number of available threads is limited because the JDK implements threads as wrappers around operating system (OS) threads. OS threads are costly, so we cannot have too many of them, which makes the implementation ill-suited to the thread-per-request style.\"}"
    );


    @Autowired
    private UserService userService;

    @Test
    public void testInsert() {
        User user = new User();
        user.setId(10);
        user.setName("xiao an");
        user.setEmail("xiao.an@163.com");
        user.setUserType(List.of(1, 2, 3));
        user.setDesc("test test");
        boolean insert = userService.insert(user);
        Assertions.assertNotNull(insert);
    }

    @Test
    public void testGetById() {
        User user = userService.getUserById(1);
        Assertions.assertNotNull(user);
    }

    @Test
    public void testDelete() {
        boolean delete = userService.delete(10);
        Assertions.assertTrue(delete);
    }

    @Test
    public void testListAll() {
        List<User> users = userService.listAll();
        for (User user : users) {
            System.out.println(JacksonUtil.toJson(user));
        }
        Assertions.assertNotNull(users);
    }

    @Test
    public void testFromJackson() {
        for (String json : JSON_LIST) {
            User user = JacksonUtil.fromJson(json, User.class);
            System.out.println(user);
            Assertions.assertNotNull(user);
        }
    }

    @Test
    public void testListByKeyword() {
        List<UserDto> list = userService.listByKeyword("virtual");
        System.out.println(list);
    }
}
