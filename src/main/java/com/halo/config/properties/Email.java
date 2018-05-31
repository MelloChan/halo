package com.halo.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author MelloChan
 * @date 2018/5/31
 */
@Configuration
@ConfigurationProperties(prefix = "halo")
@PropertySource("classpath:email.properties")
public class Email {
    private String stmp;
    private String email;
    private String password;

    public String getStmp() {
        return stmp;
    }

    public void setStmp(String stmp) {
        this.stmp = stmp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Email{" +
                "stmp='" + stmp + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
