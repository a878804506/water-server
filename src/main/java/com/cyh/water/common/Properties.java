package com.cyh.water.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 解析配置文件的属性放置到此类
 */
@Component
public class Properties {

    public static Integer serverPort;

    public static Boolean timedTaskSwitch;

    public static Boolean backupAndResetTables_JOB_Switch;

    public static Boolean webSocketChatSwitch;

    public static String webSocketChatAddress;

    public static Boolean useZookeeperLock;

    public static String ftpHostname;

    public static Integer ftpPort;

    public static String ftpUsername;

    public static String ftpPassword;

    public static String ftpStaticPictureHost;

    public static String redisHostName;

    public static Integer redisPort;

    public static String redisPassword;

    public static Integer redisMaxTotal;

    public static Integer redisMinIdle;

    public static Integer redisMaxIdle;

    public static Integer redisMaxWaitMillis;

    public static Boolean redisTestOnBorrow;

    public static Boolean redisTestOnIdle;

    public static Integer redisTimeBetweenEvictionRunsMillis;

    public static Integer redisNumTestsPerEvictionRun;

    public static Integer redisMinEvictableIdleTimeMillis;

    public static Integer redisTimeout;

    public static Integer redisDbSelectedForHistoryMessage;

    public static Integer redisDbSelectedForSystem;

    public static String redisSystemUsers;

    public static Integer redisCacheTime;

    @Value("${server.port}")
    public void setServerPort(int serverPort) {
        Properties.serverPort = serverPort;
    }

    @Value("${system.timedTaskSwitch}")
    public void setTimedTaskSwitch(Boolean timedTaskSwitch) {
        Properties.timedTaskSwitch = timedTaskSwitch;
    }

    @Value("${system.backupAndResetTables_JOB_Switch}")
    public void setBackupAndResetTables_JOB_Switch(Boolean backupAndResetTables_JOB_Switch) {
        Properties.backupAndResetTables_JOB_Switch = backupAndResetTables_JOB_Switch;
    }

    @Value("${system.webSocketChatSwitch}")
    public void setWebSocketChatSwitch(Boolean webSocketChatSwitch) {
        Properties.webSocketChatSwitch = webSocketChatSwitch;
    }

    @Value("${system.webSocketChatAddress}")
    public void setWebSocketChatAddress(String webSocketChatAddress) {
        Properties.webSocketChatAddress = webSocketChatAddress;
    }

    @Value("${system.useZookeeperLock}")
    public void setUseZookeeperLock(Boolean useZookeeperLock) {
        Properties.useZookeeperLock = useZookeeperLock;
    }

    @Value("${ftp.hostname}")
    public void setFtpHostname(String ftpHostname) {
        Properties.ftpHostname = ftpHostname;
    }

    @Value("${ftp.port}")
    public void setFtpPort(int ftpPort) {
        Properties.ftpPort = ftpPort;
    }

    @Value("${ftp.username}")
    public void setFtpUsername(String ftpUsername) {
        Properties.ftpUsername = ftpUsername;
    }

    @Value("${ftp.password}")
    public void setFtpPassword(String ftpPassword) {
        Properties.ftpPassword = ftpPassword;
    }

    @Value("${ftp.staticPictureHost}")
    public void setFtpStaticPictureHost(String ftpStaticPictureHost) {
        Properties.ftpStaticPictureHost = ftpStaticPictureHost;
    }

    @Value("${redis.hostName}")
    public void setRedisHostName(String redisHostName) {
        Properties.redisHostName = redisHostName;
    }

    @Value("${redis.port}")
    public void setRedisPort(int port) {
        Properties.redisPort = port;
    }

    @Value("${redis.password}")
    public void setRedisPassword(String redisPassword) {
        Properties.redisPassword = redisPassword;
    }

    @Value("${redis.maxTotal}")
    public void setRedisMaxTotal(int redisMaxTotal) {
        Properties.redisMaxTotal = redisMaxTotal;
    }

    @Value("${redis.minIdle}")
    public void setRedisMinIdle(int redisMinIdle) {
        Properties.redisMinIdle = redisMinIdle;
    }

    @Value("${redis.maxIdle}")
    public void setRedisMaxIdle(int redisMaxIdle) {
        Properties.redisMaxIdle = redisMaxIdle;
    }

    @Value("${redis.maxWaitMillis}")
    public void setRedisMaxWaitMillis(int redisMaxWaitMillis) {
        Properties.redisMaxWaitMillis = redisMaxWaitMillis;
    }

    @Value("${redis.testOnBorrow}")
    public void setRedisTestOnBorrow(Boolean redisTestOnBorrow) {
        Properties.redisTestOnBorrow = redisTestOnBorrow;
    }

    @Value("${redis.testOnIdle}")
    public void setRedisTestOnIdle(Boolean redisTestOnIdle) {
        Properties.redisTestOnIdle = redisTestOnIdle;
    }

    @Value("${redis.timeBetweenEvictionRunsMillis}")
    public void setRedisTimeBetweenEvictionRunsMillis(int redisTimeBetweenEvictionRunsMillis) {
        Properties.redisTimeBetweenEvictionRunsMillis = redisTimeBetweenEvictionRunsMillis;
    }

    @Value("${redis.numTestsPerEvictionRun}")
    public void setRedisNumTestsPerEvictionRun(int redisNumTestsPerEvictionRun) {
        Properties.redisNumTestsPerEvictionRun = redisNumTestsPerEvictionRun;
    }

    @Value("${redis.minEvictableIdleTimeMillis}")
    public void setRedisMinEvictableIdleTimeMillis(Integer redisMinEvictableIdleTimeMillis) {
        Properties.redisMinEvictableIdleTimeMillis = redisMinEvictableIdleTimeMillis;
    }

    @Value("${redis.timeout}")
    public void setRedisTimeout(int redisTimeout) {
        Properties.redisTimeout = redisTimeout;
    }

    @Value("${redis.dbSelectedForHistoryMessage}")
    public void setRedisDbSelectedForHistoryMessage(int redisDbSelectedForHistoryMessage) {
        Properties.redisDbSelectedForHistoryMessage = redisDbSelectedForHistoryMessage;
    }

    @Value("${redis.dbSelectedForSystem}")
    public void setRedisDbSelectedForSystem(int redisDbSelectedForSystem) {
        Properties.redisDbSelectedForSystem = redisDbSelectedForSystem;
    }

    @Value("${redis.systemUsers}")
    public void setRedisSystemUsers(String redisSystemUsers) {
        Properties.redisSystemUsers = redisSystemUsers;
    }

    @Value("${redis.cacheTime}")
    public void setRedisCacheTime(int redisCacheTime) {
        Properties.redisCacheTime = redisCacheTime;
    }


    // get 方法
    public Integer getServerPort() {
        return serverPort;
    }

    public Boolean getTimedTaskSwitch() {
        return timedTaskSwitch;
    }

    public Boolean getBackupAndResetTables_JOB_Switch() {
        return backupAndResetTables_JOB_Switch;
    }

    public Boolean getWebSocketChatSwitch() {
        return webSocketChatSwitch;
    }

    public String getWebSocketChatAddress() {
        return webSocketChatAddress;
    }

    public Boolean getUseZookeeperLock() {
        return useZookeeperLock;
    }

    public String getFtpHostname() {
        return ftpHostname;
    }

    public Integer getFtpPort() {
        return ftpPort;
    }

    public String getFtpUsername() {
        return ftpUsername;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public String getFtpStaticPictureHost() {
        return ftpStaticPictureHost;
    }

    public String getRedisHostName() {
        return redisHostName;
    }

    public Integer getRedisPort() {
        return redisPort;
    }

    public String getRedisPassword() {
        return redisPassword;
    }

    public Integer getRedisMaxTotal() {
        return redisMaxTotal;
    }

    public Integer getRedisMinIdle() {
        return redisMinIdle;
    }

    public Integer getRedisMaxIdle() {
        return redisMaxIdle;
    }

    public Integer getRedisMaxWaitMillis() {
        return redisMaxWaitMillis;
    }

    public Boolean getRedisTestOnBorrow() {
        return redisTestOnBorrow;
    }

    public Boolean getRedisTestOnIdle() {
        return redisTestOnIdle;
    }

    public Integer getRedisTimeBetweenEvictionRunsMillis() {
        return redisTimeBetweenEvictionRunsMillis;
    }

    public Integer getRedisNumTestsPerEvictionRun() {
        return redisNumTestsPerEvictionRun;
    }

    public Integer getRedisMinEvictableIdleTimeMillis() {
        return redisMinEvictableIdleTimeMillis;
    }

    public Integer getRedisTimeout() {
        return redisTimeout;
    }

    public Integer getRedisDbSelectedForHistoryMessage() {
        return redisDbSelectedForHistoryMessage;
    }

    public Integer getRedisDbSelectedForSystem() {
        return redisDbSelectedForSystem;
    }

    public String getRedisSystemUsers() {
        return redisSystemUsers;
    }

    public Integer getRedisCacheTime() {
        return redisCacheTime;
    }
}
