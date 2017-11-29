package com.binwang.util;

import java.util.UUID;

/**
 * Created by owen on 17/5/8.
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
