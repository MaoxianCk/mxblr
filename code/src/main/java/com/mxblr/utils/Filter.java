package com.mxblr.utils;

/**
 * @author Ck
 * #date 2019/06/12 8:46
 */
public final class Filter {
    public static String xssFilter(String src){
        return src.replaceAll("<","").replaceAll(">","");
    }
}

