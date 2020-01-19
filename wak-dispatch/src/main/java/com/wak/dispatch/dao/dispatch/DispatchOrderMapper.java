package com.wak.dispatch.dao.dispatch;

import com.wak.dispatch.dao.CommonDao;
import com.wak.dispatch.model.dto.DispatchOrderDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * DispatchOrderMapper
 *
 * @author sophy
 * @date 2020/02/02 14:42
 */
public interface DispatchOrderMapper extends CommonDao<Long> {

    Integer findDispatchTimesByUserId(@Param("userId") String userId);

    Integer findDispatchTimesOfDayByUserId(@Param("userId") String userId);

    List<DispatchOrderDto> findDispatchOrdersByUserId(Map<String, Object> params);

    Integer findDispatchOrdersCntByUserId(Map<String, Object> params);

    Integer updateDispatchStatus(Map<String, Object> params);
}