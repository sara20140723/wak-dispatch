package com.wak.dispatch.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.wak.dispatch.constant.DispatchStatusEnum;
import com.wak.dispatch.dao.dispatch.DispatchOrderMapper;
import com.wak.dispatch.exception.CustomException;
import com.wak.dispatch.model.dto.DispatchOrderDto;
import com.wak.dispatch.model.dto.UserDto;
import com.wak.dispatch.model.vo.ProductOrderVO;
import com.wak.dispatch.service.DispatchService;
import com.wak.dispatch.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DispatchOrderService implements DispatchService {

//	/**
//	 * 使用默认数据源
//	 * @param page
//	 * @param user
//	 * @return
//	 */
//	public PageAjax<DispatchOrderDto> queryUserDefPage(PageAjax<DispatchOrderDto> page, DispatchOrderDto user){
//		return queryPage(page, user);
//	}
//
//	@TargetDataSource("dispatch")
//	public PageAjax<DispatchOrderDto> queryUserDsPage(PageAjax<DispatchOrderDto> page, DispatchOrderDto user){
//		return queryPage(page, user);
//	}

    @Autowired
    private DispatchOrderMapper dispatchOrderMapper;

    @Autowired
    private UserUtil userUtil;

    public Map<String, Object> findDispatchOrdersByUserId(Integer page, Integer pageSize, Integer status) {
        Map<String, Object> params = getSearchConditionMap(page, pageSize, status);

        Integer count = dispatchOrderMapper.findDispatchOrdersCntByUserId(params);

        List<DispatchOrderDto> list = dispatchOrderMapper.findDispatchOrdersByUserId(params);

        list.stream().forEach(item -> {
			List<ProductOrderVO> product = JSONArray.parseArray(item.getOrderJson(), ProductOrderVO.class);
			item.setProductOrderVO(product);
        });

        Map<String, Object> result = new HashMap<>(16);
        result.put("data", list);
        result.put("count", count);
        return result;
    }

    public Integer updateDispatchStatus(String orderId, Integer status) {
        Integer result = 0;

        UserDto userDto = userUtil.getCurrentOnlineUser();
        switch (status) {
            case 1:
                result = updateDispatchStatusForReceiveCustomerOrder(orderId, userDto.getUserId());
                break;
            case 2:
                result = updateDispatchStatusForPick(orderId, userDto.getUserId());
                break;
            case 3:
                result = updateDispatchStatusForFinishDispatch(orderId, userDto.getUserId());
                break;
        }
        return result;
    }

    public Integer findDispatchTimesByUserId(String userId) {
        return dispatchOrderMapper.findDispatchTimesByUserId(userId);
    }

    public Integer findDispatchTimesOfDayByUserId(String userId) {
        return dispatchOrderMapper.findDispatchTimesOfDayByUserId(userId);
    }


    private Map<String, Object> getSearchConditionMap(Integer page, Integer pageSize, Integer status) {
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
        if (status < 0) {
            params.put("status", status);
        }
        params.put("userId", userDto.getUserId());

        params.put("offset", (page - 1) * pageSize);
        params.put("limit", pageSize);
        return params;
    }

    public Integer updateDispatchStatusForReceiveCustomerOrder(String orderId, String userId) {

        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("userId", userId);
        params.put("status", DispatchStatusEnum.RECEIVE_CUSTOMER_ORDER.getValue());
        return dispatchOrderMapper.updateDispatchStatus(params);
    }

    public Integer updateDispatchStatusForPick(String orderId, String userId) {

        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("userId", userId);
        params.put("status", DispatchStatusEnum.PICK_PACKAGE.getValue());
        return dispatchOrderMapper.updateDispatchStatus(params);
    }

    public Integer updateDispatchStatusForFinishDispatch(String orderId, String userId) {

        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("userId", userId);
        params.put("status", DispatchStatusEnum.FINISH_DISPATCH.getValue());
        return dispatchOrderMapper.updateDispatchStatus(params);
    }
}
