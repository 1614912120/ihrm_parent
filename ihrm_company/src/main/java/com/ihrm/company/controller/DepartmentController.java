package com.ihrm.company.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.company.dao.DepartmentDao;
import com.ihrm.company.service.CompanyService;
import com.ihrm.company.service.DepartmentService;
import com.ihrm.domain.company.Company;
import com.ihrm.domain.company.Department;
import com.ihrm.domain.company.response.DeptListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import sun.java2d.d3d.D3DDrawImage;

import java.util.List;

/**
 * ClassName: DepartmentController
 * Package: com.ihrm.company.controller
 * Description:
 *
 * @Author R
 * @Create 2024/3/24 10:58
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/company")
public class DepartmentController extends BaseController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CompanyService companyService;
    /**
     * 保存
     */
    @PostMapping("/department")
    public Result save(@RequestBody Department department) {
        department.setCompanyId(companyId);
        //2.调用service保存
        departmentService.save(department);
        //3 构造返回结果
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询企业列表 先指定企业id
     * @return
     */
    @GetMapping("/department")
    public Result findAll() {
        Company company = companyService.findById(companyId);
        List<Department> list = departmentService.findAll(companyId);
        //构造返回结果
        DeptListResult deptListResult = new DeptListResult(company, list);
        return new Result(ResultCode.SUCCESS,deptListResult);
    }

    /**
     * 根据id查询department
     */
    @GetMapping("/department/{id}")
    public Result findById(@PathVariable("id") String id) {
        Department department = departmentService.findById(id);
        return new Result(ResultCode.SUCCESS,department);
    }

    /**
     * 修改
     */
    @PutMapping("/department/{id}")
    public Result update(@PathVariable("id") String id,@RequestBody Department department) {
        //设置修改的部门id
        department.setId(id);
        //service更新
        departmentService.update(department);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 删除
     */
    @DeleteMapping("/department/{id}")
    public Result delete(@PathVariable("id") String id) {
        departmentService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

}
