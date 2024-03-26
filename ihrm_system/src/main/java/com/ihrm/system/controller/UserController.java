package com.ihrm.system.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.company.Company;
import com.ihrm.domain.company.response.DeptListResult;
import com.ihrm.domain.system.User;
import com.ihrm.domain.system.response.UserResult;
import com.ihrm.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

/**
 * ClassName: UserController
 * Package: com.ihrm.system.controller
 * Description:
 *
 * @Author R
 * @Create 2024/3/24 16:18
 * @Version 1.0
 */
@CrossOrigin
@RestController()
@RequestMapping("/sys")    //继承父类
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    /**
     * 保存
     */
    @PostMapping("/user")
    public Result save(@RequestBody User user) {
        user.setCompanyName(companyName);
        user.setCompanyId(companyId);
        //2.调用service保存
        userService.save(user);
        //3 构造返回结果
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询企业列表 先指定企业id
     * @return
     */
    @GetMapping("/user")
    public Result findAll(int page, int size, @RequestParam Map map) {
        //1.获得企业id
        map.put("companyId",companyId);
        //2.分页查询
        Page pageUser = userService.findAll(map, page, size);
        //构造返回结果
        PageResult pageResult = new PageResult(pageUser.getTotalElements(),pageUser.getContent());
        return new Result(ResultCode.SUCCESS,pageResult);
    }

    /**
     * 根据id查询user
     */
    @GetMapping("/user/{id}")
    public Result findById(@PathVariable("id") String id) {
        User user = userService.findById(id);
        UserResult userResult = new UserResult(user);
        return new Result(ResultCode.SUCCESS,userResult);
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

    /**
     * 分配角色
     */
    @PutMapping("/user/assignRoles")
    public Result save(@RequestBody Map<String,Object> map) {
        //获取被分配角色id
        String userId = (String)map.get("id");
        //获取角色id列表
        List<String> roleId = (List<String>) map.get("roleIds");
        //调用service完成角色分配
        userService.assginRoles(userId,roleId);
        return new Result(ResultCode.SUCCESS);
    }
}
