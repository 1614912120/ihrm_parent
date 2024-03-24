package com.ihrm.common.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * ClassName: BaseService
 * Package: com.ihrm.common.service
 * Description:
 *
 * @Author R
 * @Create 2024/3/24 11:58
 * @Version 1.0
 */
public class BaseService<T> {
    @ModelAttribute
    public Specification<T> getSpec(String companyId) {
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //根据id查询
                return criteriaBuilder.equal(root.get("companyId").as(String.class),companyId);
            }
        };
        return spec;
    }
}
