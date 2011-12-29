package com.boothj5.util;

import java.util.ArrayList;
import java.util.List;

public class IterationUtil {
    public static List<Integer> upTo(int max) {
        List<Integer> result = new ArrayList<Integer>() ;
        int i = 0 ;
        while (i < max)
            result.add(i++) ;
        return result ;
    }
    
    public static List<Integer> range(int start, int max) {
        List<Integer> result = new ArrayList<Integer>() ;
        int i = start ;
        while (i < max)
            result.add(i++) ;
        return result ;
    }
    
    public static List<Integer> toZeroFrom(int start) {
        List<Integer> result = new ArrayList<Integer>() ;
        int i = start ;
        while (i >= 0)
            result.add(i--) ;
        return result ;
    }
}
