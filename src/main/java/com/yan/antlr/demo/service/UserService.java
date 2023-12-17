package com.yan.antlr.demo.service;

import com.yan.antlr.demo.dto.User;
import com.yan.antlr.demo.dto.UserDto;

import java.util.List;

public interface UserService {

    boolean insert(User user);

    boolean delete(int id);

    User getUserById(int id);

    List<User> listAll();

    List<UserDto> listByKeyword(String keyword);

    List<User> listByOr(List<String> keywords, List<String> ignoreKeywords);

    List<User> listByAnd(List<String> keywords, List<String> ignoreKeywords);

    List<User> listByInput(String input);
}
