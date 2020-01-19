package com.wak.dispatch.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * User
 *
 * @author sophy
 * @date 2020/02/02 14:43
 */
@Data
public class CustomerOrder implements Serializable {

    @ApiModelProperty("工作站ID")
    private String workstationId;

    @ApiModelProperty("订单ID")
    private String orderId;

    @ApiModelProperty("订单编号")
    private String orderNumber;

    @ApiModelProperty("收货地址编号")
    private String addressId;

    @ApiModelProperty("产品编号")
    private String productId;

    @ApiModelProperty("派送人编号")
    private String dispatchUserId;

    @ApiModelProperty("销售价格")
    private String amount;

    @ApiModelProperty("订单金额")
    private String orderPrice;

    @ApiModelProperty("优惠金额")
    private String discountPrice;

    @ApiModelProperty("实付金额")
    private String actualPrice;

    @ApiModelProperty("客户编号")
    private String customerId;

    @ApiModelProperty("支付状态")
    private String payStatus;

    @ApiModelProperty("状态(0:已下单1:已关闭2:已出库)")
    private Integer status;

    @ApiModelProperty("配送状态(0:未接单1:已接单2:已取货3:已完成4:派送已取消5:客户取消)")
    private Integer dispatchOrderStatus;

    @ApiModelProperty("产品详情")
    private String productJson;

    @ApiModelProperty("收货地址详情")
    private String addressJson;
}