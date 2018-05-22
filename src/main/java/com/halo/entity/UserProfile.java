package com.halo.entity;

import java.util.Date;

/**
 * @author MelloChan
 */
public class UserProfile {
  private Long id;
  private String username;
  private String avatar;
  private Short securityLevel;
  private String email;
  private String phone;
  private String pwdProtection;
  private Long hlCoin;
  private Date gmtCreate;
  private Date gmtUpdated;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public Short getSecurityLevel() {
    return securityLevel;
  }

  public void setSecurityLevel(Short securityLevel) {
    this.securityLevel = securityLevel;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPwdProtection() {
    return pwdProtection;
  }

  public void setPwdProtection(String pwdProtection) {
    this.pwdProtection = pwdProtection;
  }

  public Long getHlCoin() {
    return hlCoin;
  }

  public void setHlCoin(Long hlCoin) {
    this.hlCoin = hlCoin;
  }

  public Date getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(Date gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  public Date getGmtUpdated() {
    return gmtUpdated;
  }

  public void setGmtUpdated(Date gmtUpdated) {
    this.gmtUpdated = gmtUpdated;
  }
}
