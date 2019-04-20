package com.javalitterboy.ibk.constant;


/**
 * @author 14183
 */
public class StatusCode {
    /**
     * 成功
     */
    public static int SC_OK = 1;
    /**
     * 失败
     */
    public static int SC_ERROR = -1;
    /**
     * 未授权
     */
    public static int SC_UNAUTHORIZED = 401;
    /**
     * 内部服务器错误
     */
    public static int SC_INTERNAL_SERVER_ERROR = 500;

    /** SESSION超时*/
    public static int SC_SESSION_TIME_OUT = 999;
}
