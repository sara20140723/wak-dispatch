package com.wak.dispatch.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.awt.*;
import java.io.Serializable;

/**
 * User
 *
 * @author sophy
 * @date 2020/02/02 14:43
 */
@Data
public class UserAddress implements Serializable {

    @ApiModelProperty("联系人")
    private String userName;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("地址")
    private String address;

//    @ApiModelProperty("位置")
//    private Point location;
}