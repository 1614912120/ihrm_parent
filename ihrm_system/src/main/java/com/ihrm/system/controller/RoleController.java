package com.ihrm.system.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.company.response.RoleResult;
import com.ihrm.domain.system.Role;
import com.ihrm.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: RoleController
 * Package: com.ihrm.system.controller
 * Description:
 *
 * @Author R
 * @Create 2024/3/24 18:03
 * @Version 1.0
 */
@CrossOrigin
@RestController()
@RequestMapping("/sys")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    /**
     * 保存
     */
    @PostMapping("/role")
    public Result save(@RequestBody Role role) {
        role.setCompanyId(companyId);
        //2.调用service保存
        roleService.save(role);
        //3 构造返回结果
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询企业列表 先指定企业id
     * @return
     */
    @GetMapping("/role")
    public Result findAll(int page, int pagesize, @RequestParam Map map) {
        //1.获得企业id
        map.put("companyId",companyId);
        //2.分页查询
        Page<Role> pageUser = roleService.findAll(map, page, pagesize);
        //构造返回结果
        PageResult<Role> pageResult = new PageResult(pageUser.getTotalElements(),pageUser.getContent());
        return new Result(ResultCode.SUCCESS,pageResult);
    }
//    @RequestMapping(value="/role" ,method=RequestMethod.GET)
//    public Result findAll(int page,int size)  {
//        Page<Role> all = roleService.findAll(page,size);
//        return new Result(ResultCode.SUCCESS,all);
//    }

    /**
     * 根据id查询user
     */
    @GetMapping("/role/{id}")
    public Result findById(@PathVariable("id") String id) {
        Role role = roleService.findById(id);
        RoleResult roleResult = new RoleResult(role);
        return new Result(ResultCode.SUCCESS,roleResult);
    }

    @RequestMapping(value="/role/list" ,method=RequestMethod.GET)
    public Result findAll() throws Exception {
        List<Role> roleList = roleService.findAlls(companyId);
        return new Result(ResultCode.SUCCESS,roleList);
    }


    /**
     * 修改
     */
    @PutMapping("/role/{id}")
    public Result update(@PathVariable("id") String id,@RequestBody Role role) {
        //设置修改的部门id
        role.setId(id);
        //service更新
        roleService.update(role);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 删除
     */
    @DeleteMapping("/role/{id}")
    public Result delete(@PathVariable("id") String id) {
        roleService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 分配权限
     */
    @PutMapping("/role/assignRoles")
    public Result save(@RequestBody Map<String,Object> map) {
        //获取被分配角色id
        String roleId = (String)map.get("id");
        //获取权限id列表
        List<String> permIds = (List<String>) map.get("permIds");
        //调用service完成权限分配
        roleService.assginPerms(roleId,permIds);
        return new Result(ResultCode.SUCCESS);
    }
}
