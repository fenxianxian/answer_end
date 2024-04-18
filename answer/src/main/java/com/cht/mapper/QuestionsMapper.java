package com.cht.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cht.pojo.Questions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;



@Mapper
public interface QuestionsMapper extends BaseMapper<Questions> {

    @Select("SELECT * FROM questions ORDER BY RAND() LIMIT #{limit}")
    List<Questions> selectRandomQuestions(int limit);
}
