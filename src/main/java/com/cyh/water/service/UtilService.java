package com.cyh.water.service;

import com.cyh.water.pojo.ConfigureInfo;
import com.cyh.water.pojo.TransitionModel;
import org.apache.ibatis.annotations.Param;

public interface UtilService {
    // 修改excel的存放路径
    int excelSrcSetting(ConfigureInfo configureInfo);
    // 根据name 获取 info
    ConfigureInfo getConfigureInfoByName(String name);
    // 检查表存不存在
    String checkedTableExist(String table, String year);
    //创建表
    void createTab(String tableStatement);
    //导入数据
    int importDateByTableName(String oldTable, String newTable);
    //轮询记录
    int insertConfigureInfoLogs(String log, String ipAndPort, String byWay);
    //初始化数据表
    int updateOldTabForZero(String sql);
    //修改到下一年的年份
    int updateYear(String table, int year);
    //记录到综合表
    int replaceTransitionModel(TransitionModel tm);
    //查询今天开票数量  不区分系统登录的用户
    int getOperateCutomerCountByToDate(TransitionModel tm);
    //查询当前任务有没有被执行
    int checkStateForTask(@Param(value = "taskName") String taskName, @Param(value = "thisDate") String thisDate, @Param(value = "nextDate") String nextDate, @Param(value = "execute_ip") String execute_ip);
    //任务执行完毕 修改数据库中任务状态
    int updateTaskState(@Param(value = "taskName") String taskName);
}
