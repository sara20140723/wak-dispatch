package com.wak.dispatch.utils;

import com.wak.dispatch.dao.workstation.UserMapper;
import com.wak.dispatch.exception.CustomException;
import com.wak.dispatch.model.common.Constant;
import com.wak.dispatch.model.dto.UserDto;
import com.wak.dispatch.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取当前登录用户工具类
 *
 * @author sophy[wang.su.fei@qq.com]
 * @date 2020/02/02 11:45
 */
@Component
public class UserUtil {

    private final UserMapper userMapper;

    private final UserService userService;

    @Autowired
    public UserUtil(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    /**
     * 获取当前登录用户
     *
     * @param
     * @return com.orgtec.model.UserDto
     * @author sophy[wang.su.fei@qq.com]
     * @date 2020/02/02 11:48
     */
    public UserDto getCurrentOnlineUser() {
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        // 解密获得Account
        String account = JwtUtil.getClaim(token, Constant.ACCOUNT);
        UserDto userDto = userService.findUserByUserName(account);
        // 用户是否存在
        if (userDto == null) {
            throw new CustomException("该帐号不存在或者状态已无效");
        }
        return userDto;
    }

    /**
     * 获取当前登录用户Id
     *
     * @param
     * @return com.orgtec.model.UserDto
     * @author sophy[wang.su.fei@qq.com]
     * @date 2020/02/02 11:48
     */
    public String getUserId() {
        return getCurrentOnlineUser().getUserId();
    }

    /**
     * 获取当前登录用户Token
     *
     * @param
     * @return com.orgtec.model.UserDto
     * @author sophy[wang.su.fei@qq.com]
     * @date 2020/02/02 11:48
     */
    public String getToken() {
        return SecurityUtils.getSubject().getPrincipal().toString();
    }

    /**
     * 获取当前登录用户Account
     *
     * @param
     * @return com.orgtec.model.UserDto
     * @author sophy[wang.su.fei@qq.com]
     * @date 2020/02/02 11:48
     */
    public String getAccount() {
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        // 解密获得Account
        return JwtUtil.getClaim(token, Constant.ACCOUNT);
    }
}
