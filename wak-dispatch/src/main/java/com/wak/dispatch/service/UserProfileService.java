package com.wak.dispatch.service;

import com.wak.dispatch.model.dto.UserProfileDto;


/**
 * Created by L.Answer on 2018-12-07 15:20
 */
public interface UserProfileService {

    UserProfileDto findByUserId();

    UserProfileDto currentUserProfileDto();
}