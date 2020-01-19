package com.wak.dispatch.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * User
 *
 * @author sophy
 * @date 2020/02/02 14:43
 */
@Data
public class DispatchOrder implements Serializable {

    @ApiModelProperty("派送人编号")
    private String userId;

    @ApiModelProperty("工作站ID")
    private String workstationId;

    @ApiModelProperty("订单ID")
    private String orderId;


    @ApiModelProperty("收货人")
    private String receiveUserName;

    @ApiModelProperty("收货人地址")
    private String receiveAddress;

    @ApiModelProperty("收货人电话")
    private String receivePhone;

    @ApiModelProperty("收货人下单时间")
    private Date receiveCreateTime;

    @ApiModelProperty("配送状态(0:未接单1:已接单2:已取货3:已完成4:派送已取消5:客户取消)")
    private Integer dispatchStatus;

    @ApiModelProperty("送货位置")
    private String addressLocation;

    @ApiModelProperty("送货位置X")
    private String locationX;

    @ApiModelProperty("送货位置Y")
    private String locationY;

    @JsonIgnore
    @ApiModelProperty("订单详情")
    private String orderJson;
}