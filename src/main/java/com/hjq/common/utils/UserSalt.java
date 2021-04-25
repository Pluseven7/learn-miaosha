package com.hjq.common.utils;



import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * 生成私盐
 */
@Data
public class UserSalt {

    private String PublicSalt = "miaosha";

    private StringBuffer PrivateSalt;

    private String getSalt(int n){
        char[] chars = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm0123456789!@#$%^&*()".toCharArray();
        for(int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            PrivateSalt.append(aChar);
        }
        return PrivateSalt+PublicSalt;
    }
}
