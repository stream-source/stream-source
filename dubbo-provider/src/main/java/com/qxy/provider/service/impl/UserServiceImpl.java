package com.qxy.provider.service.impl;

import com.qxy.common.model.User;
import com.qxy.common.service.UserService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUserAddressList(String userId) {
        List<User> userList = new LinkedList<>();
        for(int i = 0; i < 2; i++) {
            User user = new User();
            user.setUserAddress("杭州市西湖区" + i);
            userList.add(user);
        }
        return userList;
    }
}
