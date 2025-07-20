package com.example.alura.forum.enums;

public enum RoleNameEnum {
    ROLE_USER("USER", 1),
    ROLE_ADMIN("ADMIN", 2);

    RoleNameEnum(String roleNameEnum, Integer key) {
    }

    public String roleNameEnum;
    public Integer key;

    public String getRoleNameEnum() {
        return roleNameEnum;
    }

    public Integer getKey() {
        return key;
    }
}
