package com.hjq.common.utils;

import com.alibaba.fastjson.JSONObject;

import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.security.PublicKey;

public class ServerEncryptUtil {

    public static String encrypt(String content,String secretKey) throws Exception{
        //将Base64编码后的Server公钥转换成PublicKey对象
        PublicKey serverPublicKey = RSAUtil.string2PublicKey(secretKey);//客户端需要知道服务端的公钥信息
        //每次都随机生成AES秘钥
        String aesKeyStr = AESUtil.genKeyAES();
        SecretKey aesKey = AESUtil.loadKeyAES(aesKeyStr);
        //用Server公钥加密AES秘钥
        byte[] encryptAesKey = RSAUtil.publicEncrypt(aesKeyStr.getBytes(), serverPublicKey);
        //用AES秘钥加密请求内容
        byte[] encryptRequest = AESUtil.encryptAES(content.getBytes(), aesKey);

        JSONObject result = new JSONObject();
        result.put("aesk", RSAUtil.byte2Base64(encryptAesKey).replaceAll("\r\n", ""));
        result.put("content", RSAUtil.byte2Base64(encryptRequest).replaceAll("\r\n", ""));
        return result.toString();
    }

    //服务器解密APP的请求内容
    public static String decrypt(String content,String secretKe) throws Exception{
        JSONObject result = JSONObject.parseObject(content);
        String encryptAesKeyStr = (String) result.get("aesk");
        String encryptContent = (String) result.get("content");

        //将Base64编码后的Server私钥转换成PrivateKey对象
        PrivateKey serverPrivateKey = RSAUtil.string2PrivateKey(secretKe);
        //用Server私钥解密AES秘钥
        byte[] aesKeyBytes = RSAUtil.privateDecrypt(RSAUtil.base642Byte(encryptAesKeyStr), serverPrivateKey);
        SecretKey aesKey = AESUtil.loadKeyAES(new String(aesKeyBytes));
        //用AES秘钥解密请求内容
        byte[] request = AESUtil.decryptAES(RSAUtil.base642Byte(encryptContent), aesKey);
        return new String(request);
    }
}
