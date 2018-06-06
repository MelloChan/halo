package com.halo.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author MelloChan
 * @date 2018/5/27
 */
public class UserRegisterInfoDTO implements Serializable {
    private static final long serialVersionUID = 5403963758408522704L;
    @NotEmpty(message = "手机号不能为空")
    @Size(min = 11, max = 11)
    private String phone;
    @NotEmpty(message = "用户名不能为空")
    @Size(min = 1, max = 32)
    private String username;
    @NotEmpty(message = "密码不能为空")
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
        return "UserRegisterInfoDTO{" +
                "phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
