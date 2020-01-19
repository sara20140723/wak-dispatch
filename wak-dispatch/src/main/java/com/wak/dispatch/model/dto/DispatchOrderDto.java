package com.wak.dispatch.model.dto;


import com.wak.dispatch.model.entity.DispatchOrder;
import com.wak.dispatch.model.vo.ProductOrderVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author sophy
 * @date 2020/02/02 10:34
 */
@Data
public class DispatchOrderDto extends DispatchOrder {

    @ApiModelProperty("订单详情")
    private List<ProductOrderVO> productOrderVO;

//    @ApiModelProperty("收货地址")
//    private UserAddress userAddress;
}
