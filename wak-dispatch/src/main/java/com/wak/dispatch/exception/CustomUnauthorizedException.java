package com.wak.dispatch.exception;

/**
 * 自定义401无权限异常(UnauthorizedException)
 * @author sophy
 * @date 2020/02/02 13:59
 */
public class CustomUnauthorizedException extends RuntimeException {

    public CustomUnauthorizedException(String msg){
        super(msg);
    }

    public CustomUnauthorizedException() {
        super();
    }
}
