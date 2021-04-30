package com.hjq.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;

import javax.crypto.SecretKey;
import java.lang.reflect.Array;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;


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
    public static String decrypt(String content,String secretKey) throws Exception{
        JSONObject result = JSONObject.parseObject(content);
        String encryptAesKeyStr = (String) result.get("aesk");
        String encryptContent = (String) result.get("content");

        //将Base64编码后的Server私钥转换成PrivateKey对象
        PrivateKey serverPrivateKey = RSAUtil.string2PrivateKey(secretKey);
        //用Server私钥解密AES秘钥
        byte[] aesKeyBytes = RSAUtil.privateDecrypt(RSAUtil.base642Byte(encryptAesKeyStr), serverPrivateKey);
        SecretKey aesKey = AESUtil.loadKeyAES(new String(aesKeyBytes));
        //用AES秘钥解密请求内容
        byte[] request = AESUtil.decryptAES(RSAUtil.base642Byte(encryptContent), aesKey);
        return new String(request);
    }

    //生成签名（内容+发送方信息的hash散列结果用私钥加密，用公钥解密，比对是否一致）
    public static String createSignature(String content,String senderAddr,String secretKey) throws Exception {
        Sha1Hash sha1Hash = new Sha1Hash(content+"&&"+senderAddr,null,1014);
        byte[] hashContent = sha1Hash.getBytes();
        byte[] digest = RSAUtil.privateEncrypt(hashContent,RSAUtil.string2PrivateKey(secretKey));
        return RSAUtil.byte2Base64(digest).replaceAll("\r\n", "");
    }

    //获取签名并比对
    public static String getSignature(String content,String senderAddr,String digest,String secretKey) throws Exception {
        Sha1Hash sha1Hash = new Sha1Hash(content+"&&"+senderAddr,null,1014);
        byte[] hashContent = sha1Hash.getBytes();
        digest.replaceAll("\r\n", "");
        byte[] sign= RSAUtil.publicDecrypt(digest.getBytes(), RSAUtil.string2PublicKey(secretKey));
        JSONObject result = new JSONObject();
        if(Arrays.equals(hashContent,sign)){
            result.put("sign:",RSAUtil.byte2Base64(sign).replaceAll("\r\n", ""));
            result.put("0","通过签名");
        }else {
            result.put("1", "签名失败");
        }
        return result.toString();
    }
}
