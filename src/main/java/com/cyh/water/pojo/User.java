package com.cyh.water.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String password;
    private String time;
    private String all_time;
    private String last_time;
    private String ip; // 用户上网ip
    private String region; //用户ip所在省
    private String city; // 用户所在城市
    private String struts;//1:账号可用，0：账号不可用
    private String yzm; //用户登录验证码
    private String nickName;//昵称
    private String img;//头像
    private String grayImg;//头像

}
