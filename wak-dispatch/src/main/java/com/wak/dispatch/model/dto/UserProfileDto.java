package com.wak.dispatch.model.dto;

import com.wak.dispatch.model.entity.UserProfile;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 *
 * @author sophy
 * @date 2020/02/02 10:34
 */
@Data
public class UserProfileDto extends UserProfile {
    @ApiModelProperty("累计配送量")
    private Integer cumulativeDispatchTimes;

    @ApiModelProperty("今日已配送")
    private Integer cumulativeDispatchTimesByDay;
}
