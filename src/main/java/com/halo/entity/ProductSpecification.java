package com.halo.entity;

import java.util.Date;

/**
 * @author MelloChan
 */
public class ProductSpecification {
  private Integer id;
  private Integer proId;
  private String specification;
  private Date gmtCreate;
  private Date gmtUpdated;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getProId() {
    return proId;
  }

  public void setProId(Integer proId) {
    this.proId = proId;
  }

  public String getSpecification() {
    return specification;
  }

  public void setSpecification(String specification) {
    this.specification = specification;
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
