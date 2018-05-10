package com.halo.entity;

import java.util.Date;

/**
 * @author MelloChan
 */
public class Order {
  private String id;
  private Long userId;
  private Long payType;
  private Long shipmentTime;
  private Long shipmentType;
  private Long shipmentAmount;
  private Long orderStatus;
  private Long orderAmount;
  private Long payAmount;
  private Date gmtCreate;
  private Date gmtUpdated;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getPayType() {
    return payType;
  }

  public void setPayType(Long payType) {
    this.payType = payType;
  }

  public Long getShipmentTime() {
    return shipmentTime;
  }

  public void setShipmentTime(Long shipmentTime) {
    this.shipmentTime = shipmentTime;
  }

  public Long getShipmentType() {
    return shipmentType;
  }

  public void setShipmentType(Long shipmentType) {
    this.shipmentType = shipmentType;
  }

  public Long getShipmentAmount() {
    return shipmentAmount;
  }

  public void setShipmentAmount(Long shipmentAmount) {
    this.shipmentAmount = shipmentAmount;
  }

  public Long getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(Long orderStatus) {
    this.orderStatus = orderStatus;
  }

  public Long getOrderAmount() {
    return orderAmount;
  }

  public void setOrderAmount(Long orderAmount) {
    this.orderAmount = orderAmount;
  }

  public Long getPayAmount() {
    return payAmount;
  }

  public void setPayAmount(Long payAmount) {
    this.payAmount = payAmount;
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
