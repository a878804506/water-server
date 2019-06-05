package com.cyh.water.pojo;

import lombok.Data;

import java.util.List;

@Data
public class MenuPermission implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String type;//menu/permission
    private String url;
    private int parentId;//父id
    private int orderList;//排序
    private String iconCls;//根节点图标
    private int status;//菜单、权限状态   0：停用    1：启用
    private String remark;
    private String updateUser;
    private String updateTime;
    private List<MenuPermission> childMenus;// 子菜单

}
