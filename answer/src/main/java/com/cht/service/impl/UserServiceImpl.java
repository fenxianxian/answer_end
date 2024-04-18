package com.cht.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cht.mapper.QuestionsMapper;
import com.cht.mapper.UserMapper;
import com.cht.pojo.Questions;
import com.cht.pojo.User;
import com.cht.service.QuestionsService;
import com.cht.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    public String addData(User user) {
        int result = userMapper.insert(user);
        if (result > 0) {
            return "Data added successfully";
        } else {
            return "Failed to add data";
        }
    }
}
