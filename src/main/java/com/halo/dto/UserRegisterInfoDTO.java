package com.halo.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author MelloChan
 * @date 2018/5/27
 */
public class UserRegisterInfoDTO implements Serializable {
    private static final long serialVersionUID = 6836419933762293902L;

    @NotEmpty
    @Size(min = 11, max = 11)
    private String phone;
    @NotEmpty
    @Size(min = 1, max = 32)
    private String username;
    @NotEmpty
    @Size(min = 8, max = 16)
    private String pwd;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "UserRegisterDTO{" +
                "phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
