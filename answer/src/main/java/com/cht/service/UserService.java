package com.cht.service;


import com.baomidou.mybatisplus.extension.service.IService;

import com.cht.pojo.User;

public interface UserService extends IService<User> {

     String addData(User user);
}
