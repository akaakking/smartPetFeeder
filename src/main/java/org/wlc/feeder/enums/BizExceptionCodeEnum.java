package org.wlc.feeder.enums;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/17 下午8:10
 */
public enum BizExceptionCodeEnum {
    UNAUTHORIZED(403, "Unauthorized"),
    GET_WECHAT_ACCESS_TOKEN_ERROR(1001, "code有误导致获取微信access_token失败"),

    GET_WECHAT_USER_INFO_ERROR(1002, "获取微信用户信息失败");

    private int code;
    private String msg;

    BizExceptionCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
