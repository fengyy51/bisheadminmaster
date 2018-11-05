package com.binwang.util;

import java.util.List;

public class ArrayUtil {
    public static String join(String join,List<String> listStr){
        StringBuffer sb=new StringBuffer();
        for(int i=0,len =listStr.size();i<len;i++){
            if(i==(len-1)){
                sb.append(listStr.get(i));
            }else{
                sb.append(listStr.get(i)).append(join);
            }
        }
        return sb.toString();
    }
}
