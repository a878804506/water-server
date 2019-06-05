package com.cyh.water.service.impl;

import com.cyh.water.mapper.MenuPermissionMapper;
import com.cyh.water.pojo.MenuPermission;
import com.cyh.water.service.MenuPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuPermissionServiceImpl implements MenuPermissionService {

    @Autowired
    private MenuPermissionMapper menuPermissionMapper;

    @Override
    public List<MenuPermission> getAllMenu() {
        return menuPermissionMapper.getAllMenu();
    }

    @Override
    public List<Integer> getMenuPermissionByRoleId(int rid) {
        return menuPermissionMapper.getMenuPermissionByRoleId(rid);
    }

    @Override
    public int deleteMenuPermissionByRoleId(int rid) {
        return menuPermissionMapper.deleteMenuPermissionByRoleId(rid);
    }

    @Override
    public int insertMenuPermissionByRoleId(int rid,int[] permissionsId) {
        return menuPermissionMapper.insertMenuPermissionByRoleId(rid,permissionsId);
    }

    @Override
    public List<MenuPermission> getAllMenusAndPermissionsByRoleIds(List<Integer> roleIds) {
        return menuPermissionMapper.getAllMenusAndPermissionsByRoleIds(roleIds);
    }
}
