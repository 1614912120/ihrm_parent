package com.ihrm.domain.company.response;

import com.ihrm.domain.system.Permission;
import com.ihrm.domain.system.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: RoleResult
 * Package: com.ihrm.domain.company.response
 * Description:
 *
 * @Author R
 * @Create 2024/3/26 13:06
 * @Version 1.0
 */
@Getter
@Setter
public class RoleResult {
    @Id
    private String id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 说明
     */
    private String description;
    /**
     * 企业id
     */
    private String companyId;

    private List<String> permIds = new ArrayList<>();

    public RoleResult(Role role) {
        BeanUtils.copyProperties(role,this);
        for (Permission perm:role.getPermissions()) {
            this.permIds.add(perm.getId());
        }
    }
}
