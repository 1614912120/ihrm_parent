package com.ihrm.domain.system.response;

import com.fasterxml.jackson.core.SerializableString;
import com.ihrm.domain.system.Permission;
import com.ihrm.domain.system.Role;
import com.ihrm.domain.system.User;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * ClassName: ProfileResult
 * Package: com.ihrm.domain.system.response
 * Description:
 *
 * @Author R
 * @Create 2024/3/26 14:53
 * @Version 1.0
 */
@Getter
@Setter
public class ProfileResult {
    private String mobile;
    private String username;
    private String companyName;
    private Map<String,Object> roles = new HashMap<>();

    public ProfileResult(User user) {
        this.mobile = user.getMobile();
        this.username = user.getUsername();
        this.companyName = user.getCompanyName();
        HashSet<String> menus = new HashSet<>();
        HashSet<String> points = new HashSet<>();
        HashSet<String> apis = new HashSet<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            Set<Permission> permissions = role.getPermissions();
            for (Permission perm : permissions) {
                String code = perm.getCode();
                if(perm.getType() == 1){
                    menus.add(code);
                }else if (perm.getType() == 2) {
                    points.add(code);
                }else {
                    apis.add(code);
                }
            }
        }
        this.roles.put("menus",menus);
        this.roles.put("points",points);
        this.roles.put("apis",apis);
    }

    public ProfileResult(User user, List<Permission> list) {
        this.mobile = user.getMobile();
        this.username = user.getUsername();
        this.companyName = user.getCompanyName();

        HashSet<String> menus = new HashSet<>();
        HashSet<String> points = new HashSet<>();
        HashSet<String> apis = new HashSet<>();
        for (Permission perm : list) {
            String code = perm.getCode();
            if(perm.getType() == 1){
                menus.add(code);
            }else if (perm.getType() == 2) {
                points.add(code);
            }else {
                apis.add(code);
            }
        }
        this.roles.put("menus",menus);
        this.roles.put("points",points);
        this.roles.put("apis",apis);
    }
}
