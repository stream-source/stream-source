package com.qxy.common.service;

import com.qxy.common.model.User;

import java.util.List;

public interface UserService {
    List<User> getUserAddressList(String userId);
}
