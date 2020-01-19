package com.wak.dispatch.service;

import com.wak.dispatch.model.dto.UserDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by L.Answer on 2018-12-07 15:20
 */
public interface UserService {

    List<UserDto> findUserList(Map<String, Object> params);

    UserDto findUserByUserName(String account);

    UserDto findUserByUserNameWithoutStatus(String account);

    int register(UserDto userEntity);

    int logout();

    Boolean login(UserDto userDto, HttpServletResponse httpServletResponse);

    int updateUser(UserDto entity);

    int deleteUserById(Long id);

}