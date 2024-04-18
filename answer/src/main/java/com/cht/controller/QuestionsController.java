package com.cht.controller;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cht.mapper.QuestionsMapper;
import com.cht.mapper.UserMapper;
import com.cht.pojo.Questions;
import com.cht.pojo.User;
import com.cht.service.QuestionsService;
import com.cht.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
public class QuestionsController {


    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("questionList")
    @ResponseBody
    public List<Questions> list(){
        List<Questions> questions = questionsService.getRandomQuestions(10);
        return questions;
    }


    @RequestMapping("addData")
    @ResponseBody
    public String addData(@RequestBody JSONObject requestData){
        JSONObject data = requestData.getJSONObject("data");
        // 从 JSON 数据中提取字段
        String avatarUrl = data.getString("avatarUrl");
        String nickName = data.getString("nickName");
        Integer successNum = data.getInteger("successNum");
        Long endTime = data.getLong("endTime");
        Float howLong = data.getFloat("howLong");
        String openId = data.getString("openId");
        float score = (float) calculateScore(successNum, (10-successNum), howLong);


        // 创建实体对象并设置字段值
        User user = new User();
        user.setAvatarUrl(avatarUrl);
        user.setNickName(nickName);
        user.setSuccessNum(successNum);
        user.setEndTime(endTime.toString()); // 将时间戳转换为日期对象
        user.setHowLong(howLong);
        user.setOpenId(openId);
        user.setScore(score);
        return userService.addData(user);
    }

    @GetMapping("/getSuccessNum")
    @ResponseBody
    public User getSuccessNum(@RequestParam String open_id) {
        // 构建查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id", open_id);

        // 执行查询
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);

        // 如果查询结果不为空，则返回 success_num；否则返回 -1（或其他默认值）
        //return user != null ? user.getSuccessNum() : -1;
        return user;
    }

    @GetMapping("/updateSuccessNum")
    @ResponseBody
    public int updateSuccessNum(@RequestParam String open_id, @RequestParam int newSuccessNum,
    @RequestParam String newEndTime, @RequestParam float howLong) {
//        System.out.println(open_id);
//        System.out.println(newSuccessNum);
        // 构建更新条件
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("open_id", open_id);
        // 构建更新内容
        User updateUser = new User();
        updateUser.setSuccessNum(newSuccessNum);
        updateUser.setEndTime(newEndTime);
        updateUser.setHowLong(howLong);
        float score = (float) calculateScore(newSuccessNum, (10-newSuccessNum), howLong);
        updateUser.setScore(score);
        // 执行更新操作
        boolean success = userMapper.update(updateUser, updateWrapper) > 0;
        // 返回更新结果
        if (success) {
            return newSuccessNum;
        } else {
            return -1; // 或者其他表示更新失败的值
        }
    }

    @RequestMapping("rankList")
    @ResponseBody
    public List<User> rankList(){
        // 创建查询条件对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 指定按照score字段降序排序
        queryWrapper.orderByDesc("score");
        // 查询结果并返回
        List<User> userScores = userMapper.selectList(queryWrapper);
        return userScores;
    }


    public static double calculateScore(int correctAnswers, int wrongAnswers, float totalTimeInSeconds) {
        // 计算基础分数
        double baseScore = correctAnswers * 10;
        // 计算答错扣分
        double wrongScore = wrongAnswers * 5;
        // 计算时间扣分
        double timePenalty = totalTimeInSeconds * 0.1;
        // 计算最终得分
        double finalScore = baseScore - wrongScore - timePenalty;
        // 确保最终得分不低于0
        if (finalScore < 0) {
            finalScore = 0;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedScore = df.format(finalScore);
        return Double.parseDouble(formattedScore);
    }

}
