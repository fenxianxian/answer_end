package com.cht.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cht.mapper.QuestionsMapper;
import com.cht.pojo.Questions;
import com.cht.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions> implements QuestionsService {

    @Autowired
    private QuestionsMapper questionsMapper; //QuestionsMapper类上方必须加@Mapper

    @Override
    public List<Questions> getRandomQuestions(int limit) {
        return questionsMapper.selectRandomQuestions(limit);
    }
}
