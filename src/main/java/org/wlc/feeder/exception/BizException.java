package org.wlc.feeder.exception;

import lombok.Data;
import org.wlc.feeder.enums.BizExceptionCodeEnum;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/21 上午10:44
 */
public class BizException extends Exception {
    Integer code;

    public BizException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    public BizException(String msg) {
        super(msg);
    }

    public BizException(BizExceptionCodeEnum bizExceptionCodeEnum) {
        super(bizExceptionCodeEnum.getMsg());
        this.code = bizExceptionCodeEnum.getCode();
    }
}
