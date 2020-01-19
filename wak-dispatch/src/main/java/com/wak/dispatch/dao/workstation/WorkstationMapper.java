package com.wak.dispatch.dao.workstation;


import com.wak.dispatch.dao.CommonDao;
import com.wak.dispatch.model.dto.WorkstationDto;

import java.util.List;
import java.util.Map;

/**
 * WorkstationMapper
 *
 * @author sophy
 * @date 2020/02/02 14:42
 */
public interface WorkstationMapper extends CommonDao<Long> {

    List<WorkstationDto> findWorkstationsByUserId(Map<String, Object> params);


    Integer findWorkstationsCntByUserId(Map<String, Object> params);
}