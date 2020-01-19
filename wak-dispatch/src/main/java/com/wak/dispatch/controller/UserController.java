package com.wak.dispatch.controller;

import com.alibaba.fastjson.JSONObject;
import com.wak.dispatch.exception.CustomException;
import com.wak.dispatch.utils.JedisUtil;
import com.wak.dispatch.utils.JwtUtil;
import com.wak.dispatch.model.common.Constant;
import com.wak.dispatch.model.common.ResponseResult;
import com.wak.dispatch.model.dto.UserDto;
import com.wak.dispatch.model.dto.UserProfileDto;
import com.wak.dispatch.model.group.UserLoginValidGroup;
import com.wak.dispatch.service.UserProfileService;
import com.wak.dispatch.service.UserService;
import com.wak.dispatch.service.impl.WorkstationServiceImpl;
import com.wak.dispatch.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api(value = "用户管理", description = "用户管理API")
@RestController
@RequestMapping("/user")
@PropertySource("classpath:config.properties")
public class UserController {

    /**
     * RefreshToken过期时间
     */
    @Value("${refreshTokenExpireTime}")
    private String refreshTokenExpireTime;

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private WorkstationServiceImpl workstationService;

    @Autowired
    private UserUtil userUtil;

    // add logger
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 登录授权
     *
     * @param userDto
     * @return com.orgtec.model.common.ResponseResult
     * @author sophy
     * @date 2020/02/02 16:21
     */
    @ApiOperation(value = "登录", notes = "根据登录账号/密码获取用户信息")
    @ResponseBody
    @PostMapping("/login")
    public ResponseResult login(@Validated(UserLoginValidGroup.class) @RequestBody UserDto userDto, HttpServletResponse httpServletResponse) {

        logger.info("---/user/login begin---: userDtoJson={}", JSONObject.toJSON(userDto));

        userService.login(userDto, httpServletResponse);

        // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        JedisUtil.setObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + userDto.getUserName(), currentTimeMillis, Integer.parseInt(refreshTokenExpireTime));
        // 从Header中Authorization返回AccessToken，时间戳为当前时间戳
        String token = JwtUtil.sign(userDto.getUserName(), currentTimeMillis);

        httpServletResponse.setHeader("Authorization", token);
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");

        logger.info("---/user/login end---: token={} ", token);
        return new ResponseResult(HttpStatus.OK.value(), "登录成功(Login Success.)", null);
    }

    /**
     * 获取指定用户
     *
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author sophy
     * @date 2020/02/02 10:42
     */
    @ApiOperation(value = "获取用户详细信息", notes = "根据用户的id来获取在线用户详细信息")
    @ResponseBody
    @GetMapping("/")
    @RequiresAuthentication
    public ResponseResult findById() {

        logger.info("---/user/ begin---: userDto={} ", JSONObject.toJSON(userUtil.getCurrentOnlineUser()));
        UserProfileDto userProfileDto = userProfileService.findByUserId();
        if (userProfileDto == null) {
            throw new CustomException("查询失败(Query Failure)");
        }

        logger.info("---/user/ end---: userProfileDto={} ", JSONObject.toJSON(userProfileDto));
        return new ResponseResult(HttpStatus.OK.value(), "执行成功", userProfileDto);
    }

    /**
     * 获取指定用户的工作站
     *
     * @param page
     * @param pageSize
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author sophy
     * @date 2020/02/02 10:42
     */
    @ApiOperation(value = "我负责的站点", notes = "根据用户token获取在线用户负责的站点数据")
    @ResponseBody
    @GetMapping("/workstations")
    @RequiresAuthentication
    public ResponseResult findWorkstationByUserId(@ApiParam(value = "每页显示条数", required = true) @RequestParam("page") Integer page,
                                                  @ApiParam(value = "页号", required = true) @RequestParam("pageSize") Integer pageSize) {

        logger.info("---/user/workstations begin---: userDto={},page={}", JSONObject.toJSON(userUtil.getCurrentOnlineUser()), page);
        Map<String, Object> result = workstationService.findDispatchOrdersByUserId(page, pageSize);
        if (result == null) {
            throw new CustomException("查询失败(Query Failure)");
        }
        logger.info("---/user/workstations end---: result={}", JSONObject.toJSON(result));
        return new ResponseResult(HttpStatus.OK.value(), "执行成功", result);
    }

    @ApiOperation(value = "注册")
    @ResponseBody
    @PostMapping("/register")
    public ResponseResult register(@Validated(UserLoginValidGroup.class) @RequestBody UserDto userDto) {

        logger.info("---/user/register begin---: userDto={}", JSONObject.toJSON(userDto));
        int result = userService.register(userDto);
        logger.info("---/user/register end---: userDto={},result={}", JSONObject.toJSON(userDto), result);
        return new ResponseResult(HttpStatus.OK.value(), "执行成功", result);
    }

    @ApiOperation(value = "退出登录", notes = "清理对应redis，修改status状态")
    @ResponseBody
    @PatchMapping("/logout")
    public ResponseResult logout() {

        logger.info("---/user/logout begin---: userDto={}", JSONObject.toJSON(userUtil.getCurrentOnlineUser()));
        int result = userService.logout();
        logger.info("---/user/logout end---: userDto={},result={} ", JSONObject.toJSON(userUtil.getCurrentOnlineUser()), result);
        return new ResponseResult(HttpStatus.OK.value(), "执行成功", result);
    }
}
