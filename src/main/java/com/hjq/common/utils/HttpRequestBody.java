package com.hjq.common.utils;

/**
 * <pre>
 *   描述:请求封装
 * </pre>
 *
 * @author hjq
 * @version v1.0
 */

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;
import java.util.SortedMap;


public class HttpRequestBody implements Serializable {


    private static final long serialVersionUID = 1805020496982216343L;
    /**
     * <pre>
     *     描述：签名
     * </pre>
     */
    private String sign;



    /**
     * <pre>
     *     描述：请求时间戳
     * </pre>
     */
    private Long timestamp;


    /**
     * <pre>
     *     描述：业务参数json串(cipherText的明文)
     * </pre>
     */
    private String plainText;




    /**
     * 错误码
     */
    private String code;

    /**
     * 失败描述
     */
    private String msg;

    /**
     * <pre>
     *      功能描述: 业务参数转换Map
     * </pre>
     */
    public Map<String, Object> getBusinessParameterMap(String plainText) {
        Map<String, Object> sortedMap = JSONObject.parseObject(plainText, new TypeReference<SortedMap<String, Object>>() {});
        return sortedMap == null ? Maps.newTreeMap() : sortedMap;
    }



    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "HttpRequestBody{" +
                ", sign='" + sign + '\'' +
                ", timestamp=" + timestamp +
                ", plainText='" + plainText + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
