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
public class Workstation implements Serializable {

    @ApiModelProperty("工作站ID")
    private String workstationId;

    @ApiModelProperty("订单ID")
    private String workstationName;

    @ApiModelProperty("状态(0:正常;1:禁用)")
    private Integer status;
}