package com.wak.dispatch.model.entity;

import com.wak.dispatch.model.group.UserEditValidGroup;
import com.wak.dispatch.model.group.UserLoginValidGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * User
 * @author sophy
 * @date 2020/02/02 14:43
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 3342723124953988435L;

    /**
     * ID
     */
    private String userId;

    /**
     * 帐号
     */
    @NotNull(message = "帐号不能为空", groups = { UserLoginValidGroup.class, UserEditValidGroup.class })
    private String userName;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空", groups = {UserLoginValidGroup.class, UserEditValidGroup.class})
    private String password;

    @ApiModelProperty("联系电话")
    private String phone;

}