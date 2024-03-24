package com.ihrm.company.dao;

import com.ihrm.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ClassName: CompanyDao
 * Package: com.ihrm.company.dao
 * Description:
 *
 * @Author R
 * @Create 2024/3/21 23:38
 * @Version 1.0
 */

/**
 * JpaRepository<Company,String>  实体类类型 主键类型
 * JpaSpecificationExecutor<Company> 实体类类型
 */
public interface CompanyDao extends JpaRepository<Company,String>
        , JpaSpecificationExecutor<Company> {

}
