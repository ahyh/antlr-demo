package com.yan.antlr.demo.service;


import com.yan.antlr.demo.dto.UserDto;

import java.util.List;

public interface UserTrackerService {

    List<UserDto> listUserByTracker(String input);
}
