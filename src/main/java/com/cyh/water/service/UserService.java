package com.cyh.water.service;

import com.cyh.water.common.ResultJSON;
import com.cyh.water.pojo.Role;
import com.cyh.water.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    ResultJSON loginUser(User user);

    void updateUser(User user);

    //获取所有用户列表
    List<User> getAllUsers();

    //修改系统用户状态
    int updateUserStrutsByUid(@Param(value = "uid") int uid, @Param(value = "struts") int struts);

    User getUserById(int uid);

    //重置系统用户密码
    int updateUserPwdByUid(int uid, String newPwd);

    //新建用户
    int createUser(User user);

    //获取所有可登陆用户列表  struts=1
    List<Map<String,Object>> getAllStrutsUsers();

    //用户修改自己的信息
    void updateUserInfoByUserId(User user);

    //所有角色列表
    List<Role> getAllRoles();

    //新建角色
    int createRole(Role role);

    //修改角色
    int updateRole(Role role);

    //修改角色状态
    int updateRoleStatus(Role role);

    //根据用户id获取角色集合
    List<Integer> getRolesByUserId(int userId);

    //根据用户id删除用户角色的所有映射
    int deleteUserAndRolesMappingsByUserId(int userId);

    //增加用户角色的映射
    int insertUserAndRolesMappings(int userId, int[] roleIds);

    //根据用户id 获取有效角色的id集合
    List<Integer> getRolesListByUserId(int userId);
}
