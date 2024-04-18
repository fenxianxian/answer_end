package com.cht.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cht.mapper.QuestionsMapper;
import com.cht.pojo.Questions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface QuestionsService extends IService<Questions>
{

     List<Questions> getRandomQuestions(int limit);

}
