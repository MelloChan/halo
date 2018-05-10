package com.halo.entity;

import java.util.Date;

/**
 * @author MelloChan
 */
public class Category {
  private Long id;
  private String cateName;
  private Date gmtCreate;
  private Date gmtUpdated;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCateName() {
    return cateName;
  }

  public void setCateName(String cateName) {
    this.cateName = cateName;
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

  @Override
  public String toString() {
    return "Category{" +
            "id=" + id +
            ", cateName='" + cateName + '\'' +
            ", gmtCreate=" + gmtCreate +
            ", gmtUpdated=" + gmtUpdated +
            '}';
  }
}
