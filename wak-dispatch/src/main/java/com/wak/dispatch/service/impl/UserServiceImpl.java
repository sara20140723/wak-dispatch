package com.wak.dispatch.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wak.api.idcenter.IdServices;
import com.wak.dispatch.constant.UserStatusEnum;
import com.wak.dispatch.dao.workstation.UserMapper;
import com.wak.dispatch.dao.workstation.UserProfileMapper;
import com.wak.dispatch.exception.CustomException;
import com.wak.dispatch.exception.CustomUnauthorizedException;
import com.wak.dispatch.model.common.Constant;
import com.wak.dispatch.model.dto.UserDto;
import com.wak.dispatch.model.dto.UserProfileDto;
import com.wak.dispatch.service.UserService;
import com.wak.dispatch.utils.AesCipherUtil;
import com.wak.dispatch.utils.JedisUtil;
import com.wak.dispatch.utils.UserUtil;
import com.wak.dispatch.utils.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by L.Answer on 2018-12-09 15:51
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

//    @Autowired
//    private IdService idService;

    @Reference(version = "${demo.service.version}",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}")
    private IdServices idService;

    @Autowired
    private UserUtil userUtil;

    @Override
    public List<UserDto> findUserList(Map<String, Object> params) {

        return userMapper.findRecordsBySelectKeys(params);
    }

    @Override
    public UserDto findUserByUserName(String account) {

        Map<String, Object> params = new HashMap<>();
        params.put("userName", account);
        params.put("status", UserStatusEnum.ON.getValue());
        return userMapper.findByParams(params);
    }

    @Override
    public UserDto findUserByUserNameWithoutStatus(String account) {

        Map<String, Object> params = new HashMap<>();
        params.put("userName", account);
        return userMapper.findByParams(params);
    }

    @Override
    @Transactional
    public int register(UserDto userDto) {

        if (StringUtil.isBlank(userDto.getUserName())) {
            throw new CustomException("用户名不能为空");
        }
        if (StringUtil.isBlank(userDto.getPhone())) {
            throw new CustomException("手机号不能为空");
        }
        if (StringUtil.isBlank(userDto.getPassword())) {
            throw new CustomException("密码不能为空");
        }
        int returnResult = 0;
        String userId = idService.getId();
        userDto.setUserId(userId);

        userDto.setPassword(AesCipherUtil.enCrypto(userDto.getUserName() + "123456"));
        int result = userMapper.insertSingleRecord(userDto);
        if (result > 0) {
            UserProfileDto userProfileDto = new UserProfileDto();
            userProfileDto.setDispatchUserId(userId);
            userProfileDto.setSex(0);
            userProfileDto.setDescription(null);
            userProfileDto.setNickName(null);
            userProfileDto.setAvatars(null);
            returnResult = userProfileMapper.insertSingleRecord(userProfileDto);
        }
        return returnResult;
    }

    @Override
    public int logout() {
        UserDto userDto = userUtil.getCurrentOnlineUser();

        // 清除可能存在的Shiro权限信息缓存
        if (JedisUtil.exists(Constant.PREFIX_SHIRO_CACHE + userDto.getUserName())) {
            JedisUtil.delKey(Constant.PREFIX_SHIRO_CACHE + userDto.getUserName());
        }

        Map<String, Object> params = new HashMap<>();
        params.put("status", UserStatusEnum.OFF.getValue());
        params.put("userId", userDto.getUserId());
        return userMapper.updateStatus(params);
    }

    @Override
    public Boolean login(UserDto userDto, HttpServletResponse httpServletResponse) {

        if (StringUtil.isBlank(userDto.getUserName())) {
            throw new CustomException("用户名不能为空");
        }
        if (StringUtil.isBlank(userDto.getPassword())) {
            throw new CustomException("密码不能为空");
        }

        // 查询数据库中的帐号信息
        UserDto userDtoTemp = findUserByUserNameWithoutStatus(userDto.getUserName());

        if (userDtoTemp == null) {
            throw new CustomUnauthorizedException("该帐号不存在");
        }
        // 密码进行AES解密
        String key = AesCipherUtil.deCrypto(userDtoTemp.getPassword());
        // 因为密码加密是以帐号+密码的形式进行加密的，所以解密后的对比是帐号+密码
        if (key.equals(userDto.getUserName() + userDto.getPassword())) {
            // 清除可能存在的Shiro权限信息缓存
            if (JedisUtil.exists(Constant.PREFIX_SHIRO_CACHE + userDto.getUserName())) {
                JedisUtil.delKey(Constant.PREFIX_SHIRO_CACHE + userDto.getUserName());
            }

            Map<String, Object> params = new HashMap<>();
            params.put("status", UserStatusEnum.ON.getValue());
            params.put("userId", userDtoTemp.getUserId());
            return userMapper.updateStatus(params) > 0;
        } else {
            throw new CustomUnauthorizedException("帐号或密码错误");
        }
    }

    @Override
    public int updateUser(UserDto entity) {

        return userMapper.updateByRecord(entity);
    }

    @Override
    public int deleteUserById(Long id) {

        return userMapper.deleteByPrimaryKey(id);
    }
}