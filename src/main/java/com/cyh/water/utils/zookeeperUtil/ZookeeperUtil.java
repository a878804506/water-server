package com.cyh.water.utils.zookeeperUtil;

import com.cyh.water.common.Constants;
import com.cyh.water.common.Properties;
import com.cyh.water.utils.CommonUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZookeeperUtil {

    // 自定义权限列表
    private static final List<ACL> acls = new ArrayList<>();
    static {
        try {
            Id user1 = new Id("digest", DigestAuthenticationProvider.generateDigest(Constants.ZK_ALL_AUTH_INTO));
            Id user2 = new Id("digest", DigestAuthenticationProvider.generateDigest(Constants.ZK_RW_AUTH_INFO));
            acls.add(new ACL(ZooDefs.Perms.ALL, user1));
            acls.add(new ACL(ZooDefs.Perms.READ, user2));
//            acls.add(new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE , user2)); // 多个权限的给予方式，使用 | 位运算符
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    //CuratorFramework工厂，从工厂中创建zk对象
    private static CuratorFramework client = CuratorFrameworkFactory.builder()
        .connectString(Constants.ZK_CONNECTION_IP_PORT)
        .authorization("digest", Constants.ZK_ALL_AUTH_INTO.getBytes()) //使用用户名/密码进行连接
        .sessionTimeoutMs(Constants.ZK_SESSION_TIMEOUT)  //session 超时时间
        .connectionTimeoutMs(Constants.ZK_CONNECTION_TIMEOUT)  // 客户端连接服务器超时时间
        .namespace(Constants.ZK_NAMESPACE) //命名空间
        .retryPolicy(new ExponentialBackoffRetry(1000, 3))
        .build();

     /*  zookeeper的会话状态有以下几种：:(是跟客户端实例相关的)
     *  Disconneced     //连接失败
     *  SyncConnected	//连接成功
     * 	AuthFailed      //认证失败
     * 	Expired         //会话过期
     */
    // 连接zookeeper.一般不用手动调用，直接用zoo对象就好
    static void zkConnect() throws Exception {
        client.start();
    }

    /**
     * 创建节点
     * createMode：是否为临时节点,可取值为：PERSISTENT(持久无序)PERSISTENT_SEQUENTIAL(持久有序) EPHEMERAL(临时无序) EPHEMERAL_SEQUENTIAL(临时有序)
     * inTransaction() 创建事务并开始
     *  and().commit()  事务提交
     */
    public static void zkCreate(byte[] data) {
        try {
            client.inTransaction().create().withMode(CreateMode.PERSISTENT).withACL(acls).forPath(Constants.ZK_USER_ONLINE_PARENT_PATH,data).and().commit();
        } catch (Exception e) {
            System.out.println("["+ CommonUtil.DateToString(new Date(),"yyyy-MM-dd HH:mm:ss")+"] 节点创建失败!");
            e.printStackTrace();
        }
    }

    /**
     * 创建子节点
     */
    public static void zkCreateSon(int userId) {
        String thisPath = Constants.ZK_USER_ONLINE_PARENT_PATH+Constants.ZK_USER_ONLINE_NODE_PREFIX+userId;
        try {
            if (zkExists(thisPath))
                return ; //存在
            client.inTransaction().create().withMode(CreateMode.PERSISTENT).withACL(acls).forPath(thisPath,(""+userId).getBytes()).and().commit();
        } catch (Exception e) {
            System.out.println("["+CommonUtil.DateToString(new Date(),"yyyy-MM-dd HH:mm:ss")+"] 子节点创建失败!");
            e.printStackTrace();
        }
    }

    /**
     * 创建路径下的节点
     */
    public static Boolean zkCreateToPath(String path,String ipAndPort) {
        try {
            if (zkExists(path))
                return false; //存在
            client.inTransaction().create().withMode(CreateMode.EPHEMERAL).withACL(acls).forPath(path,ipAndPort.getBytes()).and().commit();
        } catch (Exception e) {
            System.out.println("["+CommonUtil.DateToString(new Date(),"yyyy-MM-dd HH:mm:ss")+"] 子节点创建失败!");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // 读取数据节点数据
    /*public static String zkGetData() {
        String result = "";
        try {
            byte [] data = client.getData().forPath(PATH);
            result = new String(data,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }*/

    // 修改节点中的数据
    /*public static Boolean zkUpdate(byte[] data) {
        //判断节点是否存在
        if (!zkExists())
            return false;
        try {
            Stat stat = client.setData().forPath(PATH);
            client.inTransaction().setData().withVersion(stat.getVersion()).forPath(PATH, data).and().commit();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }*/

    // 删除相应的子节点
    public static void zkDeleteSon(int userId) {
        String thisPath = Constants.ZK_USER_ONLINE_PARENT_PATH+Constants.ZK_USER_ONLINE_NODE_PREFIX+userId;
        try {
            if (!zkExists(thisPath))
                return ; //不存在
            client.inTransaction().delete().forPath(thisPath).and().commit();//只能删除叶子节点
            //client.delete().deletingChildrenIfNeeded().forPath("/Russia");//删除一个节点,并递归删除其所有子节点
            //client.delete().withVersion(5).forPath("/America");//强制指定版本进行删除
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 删除路径下的节点
    public static void zkDeleteToPath(String path) {
        try {
            if (!zkExists(path))
                return ; //不存在
            client.inTransaction().delete().forPath(path).and().commit();//只能删除叶子节点
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 判断节点是否存在
    public static Boolean zkExists(String path){
        try {
            Stat stat = client.checkExists().forPath(path);
            return stat != null ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断节点是否是持久化节点
     * @param path 路径
     * @return 2-节点不存在  | 1-是持久化 | 0-临时节点
     */
    public int isPersistentNode(String path) {
        try {
            Stat stat = client.checkExists().forPath(path);
            if (stat == null)
                return 2;
            if (stat.getEphemeralOwner() > 0)
                return 1;
            return 0;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    //获取子节点集合
    public static List<String> zkGetChildren(String path){
        try {
            return client.getChildren().forPath(Constants.ZK_USER_ONLINE_PARENT_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    // 关闭连接
    public static void zkClose() throws InterruptedException {
        client.close();
    }

    /*  下边列举了ZooKeeper中最常见的几个通知状态和事件类型
    KeeperState             EventType                        触发条件                                       说明

                            None（-1）              客户端与服务端成功建立连接
    SyncConnected（0）      NodeCreated（1）        Watcher监听的对应数据节点被创建
                            NodeDeleted（2）        Watcher监听的对应数据节点被删除                 此时客户端和服务器处于连接状态
                            NodeDataChanged（3）    Watcher监听的对应数据节点的数据内容发生变更
                            NodeChildChanged（4）   Wather监听的对应数据节点的子节点列表发生变更
    Disconnected（0）       None（-1）              客户端与ZooKeeper服务器断开连接                 此时客户端和服务器处于断开连接状态
    Expired（-112）         Node（-1）              会话超时                                        此时客户端会话失效，通常同时也会受到SessionExpiredException异常
    AuthFailed（4）         None（-1）              通常有两种情况，1：使用错误的schema进行权限检查 2：SASL权限检查失败/通常同时也会收到AuthFailedException异常
    */
    //注册监听器，用于监听PATH上的变化
    public static void zkNodeCache(){
        try {
            NodeCache nodeCache = new NodeCache(client, Constants.ZK_USER_ONLINE_PARENT_PATH, false);
            NodeCacheListener ncl = new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    ChildData childData = nodeCache.getCurrentData();
                    System.out.println("ZNode节点状态改变, path={}"+ childData.getPath());
                    System.out.println("ZNode节点数据改变, data={}"+ new String(childData.getData(), "Utf-8"));
                    System.out.println("ZNode节点状态改变, stat={}"+ childData.getStat());
                }
            };
            nodeCache.getListenable().addListener(ncl);
            nodeCache.start(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //注册监听器，用于监听PATH节点下所有子节点的变化
    public static void zkPathChildrenCache(String path){
        try {
            PathChildrenCache cache = new PathChildrenCache(client, path, true);
            PathChildrenCacheListener pccl = new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework client,PathChildrenCacheEvent event) {
                    try {
                        ChildData data = event.getData();
                        switch (event.getType()) {
                            case CHILD_ADDED:
                                System.out.println("子节点增加, path={}, data={}"+data.getPath()+ ",该节点数据为："+ new String(data.getData(), "UTF-8"));
                                break;
                            case CHILD_UPDATED:
                                System.out.println("子节点更新, path={}, data={}"+data.getPath()+ ",该节点数据为："+ new String(data.getData(), "UTF-8"));
                                break;
                            case CHILD_REMOVED:
                                System.out.println("子节点删除, path={}, data={}"+data.getPath()+ ",该节点数据为："+new String(data.getData(), "UTF-8"));
                                break;
                            default:
                                break;
                        }
                        } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            cache.getListenable().addListener(pccl);
            cache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
