package com.hjq.common.utils;




import com.hjq.common.Enum.Code;
import com.hjq.common.SysException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApiModel(description = "返回响应数据")
public class HttpResponseBody<D> implements Serializable {

    private static final long serialVersionUID = 5259675535558202880L;

    @ApiModelProperty(value = "状态码")
    private int code;

    @ApiModelProperty(value = "错误信息")
    private String msg;

    @ApiModelProperty(value = "数据对象")
    private D data;

    public HttpResponseBody() {
        this(Code.SUCCESS.getCode(), Code.SUCCESS.getMsg());
    }

    public HttpResponseBody(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public HttpResponseBody(int code, String msg, D data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static HttpResponseBody successResponse(String message) {
        return new HttpResponseBody(Code.SUCCESS.getCode(), message);
    }

    public static HttpResponseBody successResponse() {
        return new HttpResponseBody(Code.SUCCESS.getCode(), Code.SUCCESS.getMsg());
    }

    public static <D> HttpResponseBody<D> successResponse(D singleData) {
        return new HttpResponseBody(Code.SUCCESS.getCode(), Code.SUCCESS.getMsg(), singleData);
    }


    public static <D> HttpResponseBody<D> successResponse(String message, D singleData) {
        return new HttpResponseBody<>(Code.SUCCESS.getCode(), message, singleData);
    }

    public static HttpResponseBody failResponse(int code, String message) {
        return new HttpResponseBody(code, message);
    }

    public static HttpResponseBody failResponse(String message) {
        return new HttpResponseBody(Code.ERROR.getCode(), message);
    }

    public static HttpResponseBody failResponse(Code code) {
        return new HttpResponseBody(code.getCode(), code.getMsg());
    }

    /**
     * <pre>
     *      功能描述: 异常输出
     * </pre>
     *
     * @param e 异常栈
     * @return com.gda.utils.HttpResponseBody
     * @author hjq
     *
     */
    public static HttpResponseBody failResponse(Throwable e) {
        if (e instanceof SysException) {
            int code = Code.ERROR.getCode();
            String delimiter = SysException.resCodeSplitFlat;
            String errMessage = e.getMessage();
            if (e.getCause() instanceof SysException) {
                return failResponse(e.getCause());
            } else if (errMessage.contains(delimiter)) {
                String[] split = errMessage.split(delimiter);
                errMessage = split[1];
                code = Integer.valueOf(split[0]);
            }
            //把异常消息的小尾巴去掉(后面那一截英文)
            if (errMessage != null) {
                //切了之后长这样：此手机号码已经被注册\n
                errMessage = errMessage.split("com.gda.utils")[0].trim();
            }
            return new HttpResponseBody(code, errMessage);
        } else if (e.getCause() instanceof SysException) {
            return failResponse(e.getCause());
        } else {
            //针对activiti报错
            String message = e.getMessage();
            Pattern pattern = Pattern.compile("\\d+~(.+)\\r\\n");
            Matcher matcher = pattern.matcher(message);
            if (matcher.find()) {
                return new HttpResponseBody(Code.ERROR.getCode(), matcher.group(1));
            }
        }
        return new HttpResponseBody(Code.ERROR.getCode(), Code.ERROR.getMsg());
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }


}

