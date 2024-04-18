package com.cht.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {


  @TableId(value="id",type= IdType.UUID)
  private int id;
  private String avatarUrl;
  private String nickName;
  private int successNum;
  private String endTime;
  private float howLong;
  private String openId;
  private float score;





}
