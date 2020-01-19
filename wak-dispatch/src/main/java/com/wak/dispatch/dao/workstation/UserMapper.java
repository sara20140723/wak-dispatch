package com.wak.dispatch.dao.workstation;

import com.wak.dispatch.dao.CommonDao;
import com.wak.dispatch.model.dto.UserDto;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * UserMapper
 *
 * @author sophy
 * @date 2020/02/02 14:43
 */
public interface UserMapper extends CommonDao<Long> {

    UserDto findByParams(Map<String, Object> params);

    Integer updateStatus(Map<String, Object> params);
}