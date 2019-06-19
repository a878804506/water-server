package com.cyh.water.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cyh.water.common.Constants;
import com.cyh.water.common.Properties;
import com.cyh.water.common.ResultJSON;
import com.cyh.water.exception.MyException;
import com.cyh.water.mapper.UserMapper;
import com.cyh.water.pojo.MenuPermission;
import com.cyh.water.pojo.Role;
import com.cyh.water.pojo.User;
import com.cyh.water.service.MenuPermissionService;
import com.cyh.water.service.UserService;
import com.cyh.water.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuPermissionService menuPermissionService;

    public ResultJSON loginUser(User user) {
        //密码加密后再去数据库进行匹配
        try {
            user.setPassword(EncryptionAndDecryptionUtil.getEncryptionPassword(user.getPassword()));
        } catch (MyException e) {
            e.printStackTrace();
            return new ResultJSON(Constants.ERROR,"服务器内部错误！");
        }
        User loginuser = userMapper.loginUser(user);
        if (loginuser == null) { // 如果数据库查找不到该用户说明用户名或者密码错误！
            return new ResultJSON(Constants.REFUSE,"账号或密码错误,请重试！");
        } else if(loginuser.getStruts().equals("0")){ //账号处于冻结状态
            return new ResultJSON(Constants.REFUSE,"此账号已冻结，请联系管理员！");
        }else {

            //获取系统登录用户的ip信息
            JSONObject IPInfo = GetUserIpUtil.getUserIpAddress(RequestUtil.getRequest());
            if (IPInfo != null && 0 == (int) IPInfo.get("code")) {
                JSONObject info = (JSONObject) IPInfo.get("data");
                // 存入用户的ip地址
                loginuser.setIp((String) info.get("ip"));
                //存入用户所在省
                loginuser.setRegion((String) info.get("region"));
                // 存入用户所在城市
                loginuser.setCity((String) info.get("city"));
            } else {
                //没有获取到登录用户的ip详细信息
                loginuser.setIp(IPInfo.get("ip").toString());
                loginuser.setRegion("未知省份");
                loginuser.setCity("未知城市");
            }

           /*
           // 登陆成功后 返回基本登陆后的信息
            Map<String, Object> resultData = new HashMap<>();
           //根据用户id获取用户的有效角色的id
            List<Integer> roleIds = userMapper.getRolesListByUserId(loginuser.getId());
            if (null != roleIds && roleIds.size() != 0) {
                // 菜单和权限集合
                List<MenuPermission> allMenusAndPermissions = menuPermissionService.getAllMenusAndPermissionsByRoleIds(roleIds);
                // 分离的菜单
                List<MenuPermission> menus = new ArrayList<>();
                // 分离的页面内权限
                List<MenuPermission> permissions = new ArrayList<>();

                for (MenuPermission mp : allMenusAndPermissions) {
                    if ("menu".equals(mp.getType())) {
                        menus.add(mp);
                    } else if ("permission".equals(mp.getType())) {
                        permissions.add(mp);
                    }
                }
                //变成菜单tree,然后存入resultData
                resultData.put("menuList", JSON.toJSONString(MenuPermissionUtil.getMenuTree(menus)));
                //用户的授权 并存入resultData
                resultData.put("permissionList", permissions);
            }

            // 登录信息存入resultData
            resultData.put("loginuser", loginuser);

            //如果开启了聊天服务  就获取聊天服务器ip  以方便页面的WebSocket连接聊天服务器
            resultData.put("webSocketChatSwitch", Properties.webSocketChatSwitch);

            if (Properties.webSocketChatSwitch) {  //再在resultData中存入聊天服务器所在地址
                resultData.put("webSocketChatAddress", Properties.webSocketChatAddress);
                //启动一个线程将当前登陆用户在线状态更新到redis中,并且再zk上创建一个有规律的子节点
                SessionListenerUtil slu = new SessionListenerUtil(loginuser.getId());
                Thread thread = new Thread(slu);
                thread.start();
            }
*/
            // 将时间转化成有格式的字符串
            loginuser.setTime(new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT).format(new Date()));
            userMapper.updateUser(loginuser); // 将此次登录的时间记录
            return new ResultJSON(Constants.SUCCESS, "登陆成功！",
                    JWTUtil.sign(loginuser.getUserName(),loginuser.getId()));
        }
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public int updateUserStrutsByUid(int uid, int struts) {
        return userMapper.updateUserStrutsByUid(uid,struts);
    }

    @Override
    public User getUserById(int uid) {
        return userMapper.getUserById(uid);
    }

    @Override
    public int updateUserPwdByUid(int uid, String newPwd) {
        return userMapper.updateUserPwdByUid(uid,newPwd);
    }

    @Override
    public int createUser(User user) {
        return userMapper.createUser(user);
    }

    @Override
    public List<Map<String, Object>> getAllStrutsUsers() {
        return userMapper.getAllStrutsUsers();
    }

    @Override
    public void updateUserInfoByUserId(User user) {
        userMapper.updateUserInfoByUserId(user);
    }

    @Override
    public List<Role> getAllRoles() {
        return userMapper.getAllRoles();
    }

    @Override
    public int createRole(Role role) {
        return userMapper.createRole(role);
    }

    @Override
    public int updateRole(Role role) {
        return userMapper.updateRole(role);
    }

    @Override
    public int updateRoleStatus(Role role) {
        return userMapper.updateRoleStatus(role);
    }

    @Override
    public List<Integer> getRolesByUserId(int userId) {
        return userMapper.getRolesByUserId(userId);
    }

    @Override
    public int deleteUserAndRolesMappingsByUserId(int userId) {
        return userMapper.deleteUserAndRolesMappingsByUserId(userId);
    }

    @Override
    public int insertUserAndRolesMappings(int userId, int[] roleIds) {
        return userMapper.insertUserAndRolesMappings(userId,roleIds);
    }

    @Override
    public List<Integer> getRolesListByUserId(int userId) {
        return userMapper.getRolesListByUserId(userId);
    }
}