package com.cht.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("questions")
public class Questions {

  @TableId(value="id",type= IdType.UUID)
  private long id;
  private String title;
  private String option;
  private String answer;

}
