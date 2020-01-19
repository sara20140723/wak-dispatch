package com.wak.dispatch.service.impl;

import com.wak.dispatch.dao.workstation.WorkstationMapper;
import com.wak.dispatch.exception.CustomException;
import com.wak.dispatch.model.dto.UserDto;
import com.wak.dispatch.model.dto.WorkstationDto;
import com.wak.dispatch.service.WorkstationService;
import com.wak.dispatch.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkstationServiceImpl implements WorkstationService {


    @Autowired
    private WorkstationMapper workstationMapper;

    @Autowired
    private UserUtil userUtil;

    @Override
    public Map<String, Object> findDispatchOrdersByUserId(Integer page, Integer pageSize) {
        Map<String, Object> params = getSearchConditionMap(page, pageSize);

        Integer count = workstationMapper.findWorkstationsCntByUserId(params);

        List<WorkstationDto> list = workstationMapper.findWorkstationsByUserId(params);


        Map<String, Object> result = new HashMap<>(16);
        result.put("data", list);
        result.put("count", count);
        return result;
    }

    private Map<String, Object> getSearchConditionMap(Integer page, Integer pageSize) {
        UserDto userDto = userUtil.getCurrentOnlineUser();
        if (userDto == null) {
            throw new CustomException("current user is missing");
        }
        Map<String, Object> params = new HashMap<>();
        if (page != null) {
            params.put("page", page);
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }
        params.put("userId", userDto.getUserId());

        params.put("offset", (page - 1) * pageSize);
        params.put("limit", pageSize);
        return params;
    }
}
