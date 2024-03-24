package com.ihrm.company.service;

import com.ihrm.common.service.BaseService;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.company.dao.DepartmentDao;
import com.ihrm.domain.company.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * ClassName: DepartmentService
 * Package: com.ihrm.company.controller
 * Description:
 *
 * @Author R
 * @Create 2024/3/24 10:50
 * @Version 1.0
 */
@Service
public class DepartmentService extends BaseService<Department> {

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private IdWorker idWorker;
    /**
     * 保存部门
     */
    public void save(Department department) {
        String id = idWorker.nextId()+"";
        department.setId(id);
        departmentDao.save(department);
    }
    /**
     * 更新部门
     */
    public void update(Department department) {
        Department dept = departmentDao.findById(department.getId()).get();
        dept.setCode(department.getCode());
        dept.setIntroduce(department.getIntroduce());
        dept.setName(department.getName());
        //更新部门
        departmentDao.save(department);
    }
    /**
     * 根据id查询部门
     */
    public Department findById(String id) {
        return departmentDao.findById(id).get();
    }
    /**
     * 查询全部部门列表
     */
    public List<Department> findAll(String companyId) {
        /**
         * 很多地方需要根据companyId查询
         * 所以抽取出来
         */
        /**
         * 用户构造条件
         * root 包含所有对象数据
         * cb 构造查询条件
         */
//        Specification<Department> spec = new Specification<Department>() {
//            @Override
//            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                //根据id查询
//                return criteriaBuilder.equal(root.get("companyId").as(String.class),companyId);
//            }
//        };

        return departmentDao.findAll(getSpec(companyId));
    }
    /**
     * 根据id删除部门
     */
    public void deleteById(String id) {
        departmentDao.deleteById(id);
    }
}
