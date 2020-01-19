package com.wak.dispatch.service;

import java.util.Map;

/**
 * Created by L.Answer on 2018-12-07 15:20
 */
public interface WorkstationService {

    Map<String, Object> findDispatchOrdersByUserId(Integer page, Integer pageSize);
}