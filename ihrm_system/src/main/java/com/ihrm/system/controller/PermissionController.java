package com.ihrm.system.controller;

import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.domain.system.Permission;
import com.ihrm.domain.system.User;
import com.ihrm.system.service.PermissionService;
import com.ihrm.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName: PermissionController
 * Package: com.ihrm.system.controller
 * Description:
 *
 * @Author R
 * @Create 2024/3/25 11:27
 * @Version 1.0
 */
@CrossOrigin
@RestController()
@RequestMapping("/sys")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    /**
     * 保存
     */
    @PostMapping("/permission")
    public Result save(@RequestBody Map<String,Object> map) throws Exception {
        permissionService.save(map);
      return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询企业列表 先指定企业id
     * @return
     */
    @GetMapping("/permission")
    public Result findAll(@RequestParam Map map) {
        List list = permissionService.findAll(map);
        return new Result(ResultCode.SUCCESS,list);
    }

    /**
     * 根据id查询user
     */
    @GetMapping("/permission/{id}")
    public Result findById(@PathVariable("id") String id) throws Exception {
        Map map = permissionService.findById(id);
        return new Result(ResultCode.SUCCESS,map);
    }

    /**
     * 修改
     */
    @PutMapping("/permission/{id}")
    public Result update(@PathVariable("id") String id,@RequestBody Map<String,Object> map) throws Exception {
        map.put("id",id);
        permissionService.update(map);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 删除
     */
    @DeleteMapping("/permission/{id}")
    public Result delete(@PathVariable("id") String id) throws CommonException {
        permissionService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

}
