package com.hjq.common;

import com.hjq.common.Enum.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *    描述：自定义异常
 * </pre>
 *
 * @author hjq
 * @version v1.0
 *
 */
public class SysException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(SysException.class);

    private String code;

    public static final String resCodeSplitFlat = "~";

    public SysException() {
        super();
    }

    public SysException(String message) {
        this(String.valueOf(Code.ERROR.getCode()), message);
    }

    public SysException(String code, String message) {
        super(code + resCodeSplitFlat + message);
        this.code = code;
    }

    public SysException(Throwable cause) {
        super(cause);
    }

    public SysException(Code code) {
        this(String.valueOf(code.getCode()), code.getMsg());
    }

    public SysException(String code, String message, Throwable cause) {
        super(code + resCodeSplitFlat + message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static String getCode(SysException e) {
        return e.getMessage().split(resCodeSplitFlat)[0];
    }

    public static String getErrorMessage(SysException e) {
        String errMsg = e.getMessage().split(resCodeSplitFlat)[1];
        if (errMsg != null) {
            errMsg = errMsg.split("com.hjq.common.utils")[0].trim();
        }
        return errMsg;
    }

    public static void printLogger(SysException e) {
        if (e instanceof SysException) {
            SysException be = (SysException) e;
            //如果是密码错了就不打堆栈了
            if (String.valueOf(Code.USER_LOGIN_ERROR.getCode()).equals(be.getCode())) {
                logger.warn(e.getMessage());
            } else {
                logger.warn(e.getMessage(), e);
            }
        } else {
            logger.warn(e.getMessage(), e);
        }
    }

}