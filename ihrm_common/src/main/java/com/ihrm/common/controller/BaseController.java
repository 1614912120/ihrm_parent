package com.ihrm.common.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: BaseController
 * Package: com.ihrm.common
 * Description:
 *
 * @Author R
 * @Create 2024/3/24 11:49
 * @Version 1.0
 */
public class BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected String companyId;

    protected String companyName;

    @ModelAttribute
    public void setResAnReq(HttpServletRequest request,HttpServletResponse response) {
        this.request= request;
        this.response = response;
        //以后解决companyid
        this.companyId = "1";
        this.companyName = "船只博客";
    }
}
