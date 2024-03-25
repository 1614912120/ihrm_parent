package com.ihrm.system.dao;

import com.ihrm.domain.system.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ClassName: RoleDao
 * Package: com.ihrm.system.dao
 * Description:
 *
 * @Author R
 * @Create 2024/3/24 17:48
 * @Version 1.0
 */
public interface RoleDao extends JpaRepository<Role,String>, JpaSpecificationExecutor<Role> {
}
