package com.ihrm.system.service;

import com.ihrm.common.service.BaseService;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.system.Role;
import com.ihrm.system.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: RoleService
 * Package: com.ihrm.system.service
 * Description:
 *
 * @Author R
 * @Create 2024/3/24 15:47
 * @Version 1.0
 */
@Service
public class RoleService extends BaseService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private IdWorker idWorker;
    /**
     * 保存角色
     */
    public void save(Role role) {
        String id = idWorker.nextId()+"";
        role.setName(role.getName());
        role.setDescription(role.getDescription());
        role.setId(id);
        roleDao.save(role);
    }
    /**
     * 更新角色
     */
    public void update(Role role) {
        Role role1 = roleDao.findById(role.getId()).get();
        role1.setName(role.getName());
        role1.setDescription(role.getDescription());
        role1.setCompanyId(role.getCompanyId());
        //更新部门
        roleDao.save(role1);
    }
    /**
     * 根据id查询角色
     */
    public Role findById(String id) {
        return roleDao.findById(id).get();
    }
    /**
     * 查询全部部门列表
     */
    public Page<Role> findAll(Map<String,Object> map, int page, int size) {
        //1.需要查询条件
        /**
         * 动态拼接查询条件
         */
        //2.需要分页 hasDept departmentId departmentId
        Page<Role> pageRole = roleDao.findAll(new PageRequest(page-1,size));

        return pageRole;
    }


    /**
     * 根据id删除角色
     */
    public void deleteById(String id) {
        roleDao.deleteById(id);
    }

    /**
     * 修改权限
     */
    public void updateRole(String id, Set permIds) {
        Role roleUser = roleDao.findById(id).get();
        roleUser.setPermissions(permIds);
        //更新部门
        roleDao.save(roleUser);
    }

}
