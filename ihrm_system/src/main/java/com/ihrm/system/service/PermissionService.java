package com.ihrm.system.service;

import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.common.utils.BeanMapUtils;
import com.ihrm.common.utils.PermissionConstants;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.system.*;
import com.ihrm.system.dao.PermissionApiDao;
import com.ihrm.system.dao.PermissionDao;
import com.ihrm.system.dao.PermissionMenuDao;
import com.ihrm.system.dao.PermissionPointDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
@Transactional
public class PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private PermissionMenuDao permissionMenuDao;
    @Autowired
    private PermissionPointDao permissionPointDao;
    @Autowired
    private PermissionApiDao permissionApiDao;
    @Autowired
    private IdWorker idWorker;
    /**
     * 保存用户
     */
    public void save(Map<String,Object> map) throws Exception {
        //设置主键的值
        String id = idWorker.nextId()+"";
        //通过map构造permission对象
        Permission perm = BeanMapUtils.mapToBean(map, Permission.class);
        perm.setId(id);
        //根据类型构造不同的资源对象 菜单 按钮 api
        int type = perm.getType();
        switch (type) {
            case PermissionConstants.PY_MENU:
                PermissionMenu menu = BeanMapUtils.mapToBean(map,PermissionMenu.class);
                menu.setId(id);
                permissionMenuDao.save(menu);
                break;
            case PermissionConstants.PY_POINT:
                PermissionPoint point = BeanMapUtils.mapToBean(map,PermissionPoint.class);
                point.setId(id);
                permissionPointDao.save(point);
                break;
            case PermissionConstants.PY_API:
                PermissionApi api = BeanMapUtils.mapToBean(map,PermissionApi.class);
                api.setId(id);
                permissionApiDao.save(api);
                break;
            default:
                throw new CommonException(ResultCode.FAIL);
        }
        //保存
        permissionDao.save(perm);
    }
    /**
     * 更新用户
     */
    public void update(Map<String,Object> map) throws Exception {
        //1.根据传递的权限id查询权限
        Permission perm = BeanMapUtils.mapToBean(map, Permission.class);
        Permission permission = permissionDao.findById(perm.getId()).get();
        permission.setName(perm.getName());
        permission.setCode(perm.getCode());
        permission.setDescription(perm.getDescription());
        permission.setEnVisible(perm.getEnVisible());
        //2.根据类型构造出不同的资源
        int type = perm.getType();
        switch (type) {
            case PermissionConstants.PY_MENU:
                PermissionMenu menu = BeanMapUtils.mapToBean(map,PermissionMenu.class);
                menu.setId(perm.getId());
                permissionMenuDao.save(menu);
                break;
            case PermissionConstants.PY_POINT:
                PermissionPoint point = BeanMapUtils.mapToBean(map,PermissionPoint.class);
                point.setId(perm.getId());
                permissionPointDao.save(point);
                break;
            case PermissionConstants.PY_API:
                PermissionApi api = BeanMapUtils.mapToBean(map,PermissionApi.class);
                api.setId(perm.getId());
                permissionApiDao.save(api);
                break;
            default:
                throw new CommonException(ResultCode.FAIL);
        }
        //3.查询不同资源设置修改权限
        //4.更新权限 更新资源
        //通过map构造permission对象

        //根据类型构造不同的资源对象 菜单 按钮 api

        //保存
        permissionDao.save(permission);
    }
    /**
     * 根据id查询用户
     */
    public Map<String,Object> findById(String id) throws Exception {
        //1.查询权限
        Permission perm = permissionDao.findById(id).get();
        Integer type = perm.getType();
        //2.查询权限对应的资源
        Object object = null;
        if(type == PermissionConstants.PY_MENU){
            object = permissionMenuDao.findById(id).get();
        }else if(type == PermissionConstants.PY_POINT){
            object = permissionPointDao.findById(id).get();
        }else if(type == PermissionConstants.PY_API) {
            object = permissionApiDao.findById(id).get();
        }else {
            throw new CommonException(ResultCode.FAIL);
        }
        //3.构造permission map集合
        Map<String, Object> map = BeanMapUtils.beanToMap(object);
        map.put("name",perm.getName());
        map.put("type",perm.getType());
        map.put("code",perm.getCode());
        map.put("description",perm.getDescription());
        map.put("pid",perm.getPid());
        map.put("enVisible",perm.getEnVisible());

        return map;
    }
    /**
     * 查询全部部门列表
     * pid
     * enVisible
     * type
     * 查询全部权限列表type：0：菜单 + 按钮（权限点） 1：菜单2：按钮（权限点）3：API接口
     */
    public List<Permission> findAll(Map<String,Object> map) {
        Specification<Permission> spec = new Specification<Permission>() {
            @Override
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list =  new ArrayList<>();
                //根据父id查询
                if(!StringUtils.isEmpty(map.get("pid"))) {
                    list.add(criteriaBuilder.equal(root.get("pid").as(String.class),(String)map.get("pid")));
                }
                //根据enVisible
                if(!StringUtils.isEmpty(map.get("enVisible"))){
                    list.add(criteriaBuilder.equal(root.get("enVisible").as(String.class),(String)map.get("enVisible")));
                }
                //根据类型
                if(!StringUtils.isEmpty(map.get("type"))){
                    String ty = (String)map.get("type");
                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("type"));
                    if("0".equals(ty)){
                        in.value(1).value(2);
                    }else {
                        in.value(Integer.parseInt(ty));
                    }
                    list.add(in);
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return permissionDao.findAll(spec);
    }
    /**
     * 根据id删除用户
     *  //删除权限
     *         //删除权限对应的资源
     */
    public void deleteById(String id) throws CommonException {
        //通过权限id查询权限
        Permission permission = permissionDao.findById(id).get();
        permissionDao.delete(permission);
        //根据类型找资源
        Integer type = permission.getType();
        switch (type) {
            case PermissionConstants.PY_MENU:
                permissionMenuDao.deleteById(id);
                break;
            case PermissionConstants.PY_POINT:
                permissionPointDao.deleteById(id);
                break;
            case PermissionConstants.PY_API:
                permissionApiDao.deleteById(id);
                break;
            default:
                throw new CommonException(ResultCode.FAIL);
        }
    }
}
