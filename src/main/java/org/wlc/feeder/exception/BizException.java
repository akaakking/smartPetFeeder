package org.wlc.feeder.exception;

import org.wlc.feeder.enums.BizExceptionCodeEnum;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/21 上午10:44
 */
public class BizException extends Exception {
    String msg;
    Integer code;

    public BizException(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public BizException(String msg) {
        this.msg = msg;
    }

    public BizException(BizExceptionCodeEnum bizExceptionCodeEnum) {
        this.msg = bizExceptionCodeEnum.getMsg();
        this.code = bizExceptionCodeEnum.getCode();
    }
}
