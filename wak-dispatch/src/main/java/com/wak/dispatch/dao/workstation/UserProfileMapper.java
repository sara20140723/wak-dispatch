package com.wak.dispatch.dao.workstation;


import com.wak.dispatch.dao.CommonDao;
import com.wak.dispatch.model.dto.UserProfileDto;
import org.apache.ibatis.annotations.Param;

/**
 * PermissionMapper
 *
 * @author sophy
 * @date 2020/02/02 14:42
 */
public interface UserProfileMapper extends CommonDao<Long> {
    UserProfileDto findByUserId(@Param("userId") String userId);

    UserProfileDto findByAccount(@Param("account") String account);
}