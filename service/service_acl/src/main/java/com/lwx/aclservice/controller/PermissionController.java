package com.lwx.aclservice.controller;


import com.lwx.aclservice.entity.Permission;
import com.lwx.aclservice.service.PermissionService;
import com.lwx.common.MyResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/permission")
//@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public MyResult indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenuGuli();
        return MyResult.ok().data("children",list);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public MyResult remove(@PathVariable String id) {
        permissionService.removeChildByIdGuli(id);
        return MyResult.ok();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public MyResult doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId,permissionId);
        return MyResult.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public MyResult toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return MyResult.ok().data("children", list);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public MyResult save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return MyResult.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public MyResult updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return MyResult.ok();
    }

}

