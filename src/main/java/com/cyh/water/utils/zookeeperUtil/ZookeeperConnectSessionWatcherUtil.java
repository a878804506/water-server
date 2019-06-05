package com.cyh.water.utils.zookeeperUtil;


import com.cyh.water.common.Constants;
import com.cyh.water.common.Properties;
import com.cyh.water.utils.timerTaskUtil.job.backupAndResetTables_JOB;

public class ZookeeperConnectSessionWatcherUtil implements Runnable {

    @Override
    public void run() {
        try {
            ZookeeperUtil.zkConnect();
            if(!ZookeeperUtil.zkExists(Constants.ZK_USER_ONLINE_PARENT_PATH)){
                ZookeeperUtil.zkCreate("once create this node for user state.".getBytes());
            }
            if(!ZookeeperUtil.zkExists(backupAndResetTables_JOB.BACKUPANDRESETTABLESPATH)){
                ZookeeperUtil.zkCreate("once create this node for back.".getBytes());
            }
            ZookeeperUtil.zkNodeCache();
            ZookeeperUtil.zkPathChildrenCache(Constants.ZK_USER_ONLINE_PARENT_PATH);
            ZookeeperUtil.zkPathChildrenCache(backupAndResetTables_JOB.BACKUPANDRESETTABLESPATH);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("初次连接zookeeper服务失败！请注意！！");
            return;
        }
        /*while (true){
            try {
                switch (ZookeeperUtil.zoo.getState().toString()){
                    case CONNECTING:
                        System.out.println("["+CommonUtil.DateToString(new Date(),"yyyy-MM-dd HH:mm:ss")+"] 正在连接!"+ZookeeperUtil.zoo.getState());
                        break;
                    case RECONNECTING:
                        System.out.println("["+CommonUtil.DateToString(new Date(),"yyyy-MM-dd HH:mm:ss")+"] 正在重新连接!"+ZookeeperUtil.zoo.getState());
                        break;
                    case CONNECTED:
                        System.out.println("["+CommonUtil.DateToString(new Date(),"yyyy-MM-dd HH:mm:ss")+"] 已经连接!"+ZookeeperUtil.zoo.getState());
                        break;
                    case RECONNECTED:
                        System.out.println("["+CommonUtil.DateToString(new Date(),"yyyy-MM-dd HH:mm:ss")+"] 已经重新连接!"+ZookeeperUtil.zoo.getState());
                        break;
                    case CLOSED:
                        System.out.println("["+CommonUtil.DateToString(new Date(),"yyyy-MM-dd HH:mm:ss")+"] 开始会话重连。。。");
                        ZookeeperUtil.zkConnect();
                        if(!ZookeeperUtil.zkExists()){
                            ZookeeperUtil.zkCreate("once create this node.".getBytes());
                        }
                        break;
                }
                Thread.sleep(30000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}