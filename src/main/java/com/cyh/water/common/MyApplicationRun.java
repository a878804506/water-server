package com.cyh.water.common;

import com.cyh.water.service.UserService;
import com.cyh.water.utils.CommonUtil;
import com.cyh.water.utils.SpringContextUtil;
import com.cyh.water.utils.timerTaskUtil.TimerRun;
import org.apache.logging.log4j.core.config.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *  WaterServerApplication  初始化完成后执行此代码
 */
@Component
@Order(value=2)
public class MyApplicationRun implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();

        UserService userService = applicationContext.getBean(UserService.class);

        System.out.println("调用init方法");
        //项目启动加载用户信息到redis中
        CommonUtil.getAllStrutsUsersToRedis(userService);

        if(Properties.timedTaskSwitch){ //定时任务总开关
            //进行任务调度
            new TimerRun().run();
        }
    }
}
