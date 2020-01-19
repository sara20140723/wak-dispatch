package com.wak.dispatch.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class UserProfile implements Serializable {

    @ApiModelProperty("派送人编号")
    private String dispatchUserId;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("头像")
    private String avatars;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("注册时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date regTime;

    @ApiModelProperty("工作状态(0:休息1:上班)")
    private Integer status;
}