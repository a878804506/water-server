package com.cyh.water.common;

/**
 * 常量类
 */

public class Constants {
    // 响应成功
    public static final Integer SUCCESS = 200;
    // 响应失败（服务器内部错误）
    public static final Integer ERROR = 500;
    // 服务器验证不通过（用在登陆，鉴权,数据提供不完整被拒绝服务）
    public static final Integer REFUSE = 101;

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    //zookeeper 配置
    public static final String ZK_CONNECTION_IP_PORT = "148.70.60.231:2181";
    public static final Integer ZK_SESSION_TIMEOUT = 60000;
    public static final Integer ZK_CONNECTION_TIMEOUT = 60000;
    //命名空间 根节点
    public static final String  ZK_NAMESPACE = "cyh";
    //用户登陆,创建用户节点时，会以此节点为父节点
    public static final String ZK_USER_ONLINE_PARENT_PATH = "/waterUserForOnline";
    //用户登陆,创建用户节点时，节点名称前缀
    public static final String ZK_USER_ONLINE_NODE_PREFIX = "/onLine_";
    //拥有所有的权限
    public static final String ZK_ALL_AUTH_INTO = "admin:cyh19930807@!";
    //只拥有读和写的权限
    public static final String ZK_RW_AUTH_INFO = "test:cyh19930807";
}
