package com.qxy.consumer.service.impl;


import com.qxy.common.model.User;
import com.qxy.common.service.OrderService;
import com.qxy.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 远程调用步骤：
 * 1,容器启动时，服务提供者注册到注册中心（暴露服务）；
 * 2，服务消费者从注册中心订阅；
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserService userService;

    /**
     * 初始化订单，查询用户的所有地址并返回
     * @param userId
     * @return
     */
    @Override
    public void initOrder(String userId){
        List<User> userAddressList = userService.getUserAddressList(userId);
        for (User user : userAddressList) {
            System.out.println(user.getUserAddress());
        }
    }
}
