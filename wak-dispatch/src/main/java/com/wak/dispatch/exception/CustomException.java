package com.wak.dispatch.exception;

/**
 * 自定义异常(CustomException)
 * @author sophy
 * @date 2020/02/02 13:59
 */
public class CustomException extends RuntimeException {

    public CustomException(String msg){
        super(msg);
    }

    public CustomException() {
        super();
    }
}
