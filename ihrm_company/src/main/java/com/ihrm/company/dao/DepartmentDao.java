package com.ihrm.company.dao;

import com.ihrm.domain.company.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ClassName: DepartmentDao
 * Package: com.ihrm.company.dao
 * Description:
 *
 * @Author R
 * @Create 2024/3/24 10:49
 * @Version 1.0
 */

/**
 * 部门接口
 */
public interface DepartmentDao extends JpaRepository<Department,String>,
        JpaSpecificationExecutor<Department> {
}
