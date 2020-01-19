package com.wak.dispatch.service.impl;

import com.wak.dispatch.dao.workstation.UserProfileMapper;
import com.wak.dispatch.model.dto.UserProfileDto;
import com.wak.dispatch.service.UserProfileService;
import com.wak.dispatch.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserProfileServiceImpl implements UserProfileService {


    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private DispatchOrderService dispatchOrderService;

    @Autowired
    UserUtil userUtil;

    @Override
    public UserProfileDto findByUserId() {
        String id = userUtil.getUserId();
        UserProfileDto userProfileDto = userProfileMapper.findByUserId(id);

        userProfileDto.setCumulativeDispatchTimes(dispatchOrderService.findDispatchTimesByUserId(id));
        userProfileDto.setCumulativeDispatchTimesByDay(dispatchOrderService.findDispatchTimesOfDayByUserId(id));
        return userProfileDto;
    }

    @Override
    public UserProfileDto currentUserProfileDto() {
        String account = userUtil.getAccount();
        return userProfileMapper.findByAccount(account);
    }
}
