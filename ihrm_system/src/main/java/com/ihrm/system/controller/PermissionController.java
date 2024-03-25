package com.ihrm.system.controller;

import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.system.User;
import com.ihrm.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
public class PermissionController {
    @Autowired
    private UserService userService;
    /**
     * 保存
     */
    @PostMapping("/user")
    public Result save(@RequestBody User user) {
      return new Result();
    }

    /**
     * 查询企业列表 先指定企业id
     * @return
     */
    @GetMapping("/user")
    public Result findAll(int page, int size, @RequestParam Map map) {
      return new Result();
    }

    /**
     * 根据id查询user
     */
    @GetMapping("/user/{id}")
    public Result findById(@PathVariable("id") String id) {
        User user = userService.findById(id);
        return new Result(ResultCode.SUCCESS,user);
    }

    /**
     * 修改
     */
    @PutMapping("/user/{id}")
    public Result update(@PathVariable("id") String id,@RequestBody User user) {
        //设置修改的部门id
        user.setId(id);
        //service更新
        userService.update(user);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 删除
     */
    @DeleteMapping("/user/{id}")
    public Result delete(@PathVariable("id") String id) {
        userService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

}
