package com.wak.idcenter.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wak.api.idcenter.IdServices;
import com.wak.idcenter.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class IdService implements IdServices {
    @Value("${dataCenterId}")
    private Integer dataCenterId;
    @Value("${workId}")
    private Integer workId;

    private static final Map<String, IdWorker> workMap = new HashMap<>();

    @Override
    public String getId() {
        IdWorker idWorker = workMap.get(dataCenterId + "_" + workId);
        if (idWorker != null) {
            return StringUtils.leftPad(Long.toHexString(idWorker.nextId()), 18, "0");
        }
        idWorker = new IdWorker(dataCenterId, workId);
        workMap.put(dataCenterId + "_" + workId, idWorker);
        return StringUtils.leftPad(Long.toHexString(idWorker.nextId()), 18, "0");
    }
}
