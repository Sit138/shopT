package com.dto.users;

import javax.validation.constraints.Size;

public class RoleDTO {

    private int id;

    @Size(min = 3, max = 15, message = "Поле \"Имя роли\" должно содержать от 3 до 15 знаков")
    private String nameRole;

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
