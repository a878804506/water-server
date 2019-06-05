package com.cyh.water.pojo;

import lombok.Data;

@Data
public class ConfigureInfo implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String path; // name = excel 时  path是指服务器端excel存放路径    name =  updateTables 时   path是指需要创建表的年份
    private String updateTables; //name =  updateTables 时 有用 ， 需要创建的表，以逗号隔开
    private String time;  // name = excel 时  time是指修改时间    name =  updateTables 时   time是指创建及初始化表的时机
    private String update_user;
}
