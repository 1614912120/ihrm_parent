package com.ihrm.system.dao;

import com.ihrm.domain.system.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ClassName: UserDao
 * Package: com.ihrm.system.dao
 * Description:
 *
 * @Author R
 * @Create 2024/3/24 15:46
 * @Version 1.0
 */
public interface UserDao extends JpaRepository<User,String>,
        JpaSpecificationExecutor<User> {
    public User findByMobile(String mobile);
}
