package com.ihrm.company.controller;

import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.company.service.CompanyService;
import com.ihrm.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: CompanyController
 * Package: com.ihrm.company.controller
 * Description:
 *
 * @Author R
 * @Create 2024/3/22 9:44
 * @Version 1.0
 */
//解决跨域问题
@CrossOrigin
@RestController
@RequestMapping(value = "/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    /**
     * 新增
     * @param company
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Result save(@RequestBody Company company) {
        companyService.add(company);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     *根据id更新
     */
   @PutMapping("/{id}")
    public Result update(@PathVariable(value = "id") String id,@RequestBody Company company) {
        company.setId(id);
        companyService.update(company);
        return new Result(ResultCode.SUCCESS);
    }
    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(value = "id") String id) {
        companyService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据id查询企业
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") String  id)  {
        //throw new CommonException(ResultCode.UNAUTHORISE);
        Company company = companyService.findById(id);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(company);
        return result;
    }

    /**
     * 查询全部列表
     */
    @GetMapping("")
    public  Result findAll(){
        List<Company> list = companyService.findAll();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(list);
        return result;
    }

}
