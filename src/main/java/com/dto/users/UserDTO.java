package com.dto.users;

import javax.validation.constraints.Size;

public class UserDTO {

    private int id;

    // TODO: Kirill отражение этих ограниченией в базе есть?
    @Size(min = 3, max = 15, message = "Поле \"Имя пользователя\" должно содержать от 3 до 15 знаков")
    private String userName;

    @Size(min = 6, max = 15, message = "Поле \"Пароль\" должно содержать от 6 до 15 знаков")
    private String password;

    private String nameRole;

    private boolean enabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }
}
