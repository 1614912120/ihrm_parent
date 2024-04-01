package com.ihrm.system.service;

import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.system.Role;
import com.ihrm.domain.system.User;
import com.ihrm.system.dao.RoleDao;
import com.ihrm.system.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * ClassName: UserService
 * Package: com.ihrm.system.service
 * Description:
 *
 * @Author R
 * @Create 2024/3/24 15:47
 * @Version 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RoleDao roleDao;
    /**
     * 保存用户
     */
    public void save(User user) {
        String id = idWorker.nextId()+"";
        user.setPassword("123456");
        user.setEnableState(1);
        user.setId(id);
        userDao.save(user);
    }
    /**
     * 更新用户
     */
    public void update(User user) {
        User user1 = userDao.findById(user.getId()).get();
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setDepartmentId(user.getDepartmentId());
        user1.setDepartmentName(user.getDepartmentName());
        //更新部门
        userDao.save(user1);
    }
    /**
     * 根据id查询用户
     */
    public User findById(String id) {
        return userDao.findById(id).get();
    }
    /**
     * 查询全部部门列表
     */
    public Page<User> findAll(Map<String,Object> map, int page, int size) {
        //1.需要查询条件
        /**
         * 动态拼接查询条件
         */
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list =  new ArrayList<>();
                //根据请求的companyId是否为空构造查询条件
                if(!StringUtils.isEmpty(map.get("companyId"))) {
                    list.add(criteriaBuilder.equal(root.get("companyId").as(String.class),(String)map.get("companyId")));
                }
                //根据请求的部门id构造条件
                if(!StringUtils.isEmpty(map.get("departmentId"))){
                    list.add(criteriaBuilder.equal(root.get("departmentId").as(String.class),(String)map.get("departmentId")));
                }

                if(!StringUtils.isEmpty(map.get("hasDept"))){
                    //根据请求的hasDept判断是否分配部门 0 未分配
                    if(StringUtils.isEmpty(map.get("hasDept")) || "0".equals((String)map.get("hasDept"))){
                        list.add(criteriaBuilder.isNull(root.get("departmentId")));
                    }else{
                        list.add(criteriaBuilder.isNotNull(root.get("departmentId")));
                    }
                }

                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        //2.需要分页 hasDept departmentId departmentId
        Page<User> pageUser = userDao.findAll(spec, new PageRequest(page-1, size));
        return pageUser;
    }
    /**
     * 根据id删除用户
     */
    public void deleteById(String id) {
        userDao.deleteById(id);
    }

    /**
     * 分配角色
     */
    public void assginRoles(String userId, List<String> roleId) {
        //1.根据id查询用户
        User user = userDao.findById(userId).get();
        //2.设置用户角色集合
        HashSet<Role> roles = new HashSet<Role>();
        for (String s : roleId) {
            Role role = roleDao.findById(s).get();
            roles.add(role);
        }
        //3.设置用户和角色的关系
        user.setRoles(roles);
        //更新用户
        userDao.save(user);
    }


    /**
     * 根据mobile查询用户
     */
    public User findByMobile(String mobile) {
        User byMobile = userDao.findByMobile(mobile);
        return byMobile;
    }
}
