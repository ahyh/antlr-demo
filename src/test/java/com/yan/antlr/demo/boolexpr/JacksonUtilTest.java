package com.yan.antlr.demo.boolexpr;

import com.yan.antlr.demo.dto.User;
import com.yan.antlr.demo.utils.JacksonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JacksonUtilTest {

    @Test
    public void toJson() {
        User user = new User();
        user.setId(10);
        user.setName("xiao an");
        user.setEmail("xiao.an@163.com");
        user.setUserType(List.of(1,2,3));
        user.setDesc("test test");
        String json = JacksonUtil.toJson(user);
        Assertions.assertNotNull(json);
    }

    @Test
    public void testFromJson() {
        String json = "{\"id\":10,\"name\":\"xiao an\",\"email\":\"xiao.an@163.com\",\"user_type\":[1,2,3],\"desc\":\"test test\"}";
        User user = JacksonUtil.fromJson(json, User.class);
        Assertions.assertNotNull(user);
    }

}
