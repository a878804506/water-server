package com.cyh.water.pojo;

import lombok.Data;

@Data
public class Role implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String role_name;
    private int is_admin;
    private String remark;
    private int status;
    private String update_time;

}
