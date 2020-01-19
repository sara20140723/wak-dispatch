package com.wak.dispatch.controller;

import com.wak.dispatch.exception.CustomException;
import com.wak.dispatch.model.common.ResponseResult;
import com.wak.dispatch.service.impl.DispatchOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "订单管理", description = "订单管理API")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private DispatchOrderService dispatchOrderService;

    // add logger
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 我的接单中心
     *
     * @param page
     * @param pageSize
     * @param status
     * @return com.wak.model.common.ResponseResult
     * @author sophy
     * @date 2020/02/02 16:21
     */
    @ApiOperation(value = "我的接单中心", notes = "根据用户token获取在线用户负责的站点数据（status=-1 =》全部）")
    @PostMapping("/list")
    @RequiresAuthentication
    public ResponseResult findByDispatchUserId(@ApiParam(value = "每页显示条数", required = true) @RequestParam("page") Integer page,
                                               @ApiParam(value = "页号", required = true) @RequestParam("pageSize") Integer pageSize,
                                               @ApiParam(value = "派送状态") @RequestParam("status") Integer status) {

        logger.info("---/order/list begin---: page={},status={}", page, status);
        Map<String, Object> result = dispatchOrderService.findDispatchOrdersByUserId(page, pageSize, status);
        if (result == null) {
            throw new CustomException("查询失败(Query Failure)");
        }
        logger.info("---/order/list end---: result={}", result);
        return new ResponseResult(HttpStatus.OK.value(), "查询成功(Query was successful)", result);
    }

    /**
     * 更新派送状态
     *
     * @param orderId
     * @param status
     * @return com.wak.model.common.ResponseResult
     * @author sophy
     * @date 2020/02/02 10:42
     */
    @ApiOperation(value = "更新派送状态", notes = "根据用户token更新在线用户指定派送单的状态")
    @PatchMapping("/{orderId}/{status}")
    @RequiresAuthentication
    public ResponseResult update(@ApiParam(value = "订单编号") @PathVariable("orderId") String orderId,
                                 @ApiParam(value = "派送状态") @PathVariable("status") Integer status) {

        logger.info("---/order/ begin---: orderId={},status={}", orderId, status);
        Integer result = dispatchOrderService.updateDispatchStatus(orderId, status);
        logger.info("---/order/ end---: result={}", result);
        return new ResponseResult(HttpStatus.OK.value(), "更新成功(Update Success)", result);
    }
}