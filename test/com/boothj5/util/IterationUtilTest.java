package com.boothj5.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static com.boothj5.util.IterationUtil.* ;

public class IterationUtilTest {

    @Test
    public void testUpToSameResultAsFor() {
        List<Integer> usingFor = new ArrayList<Integer>() ;
        List<Integer> usingUtil = new ArrayList<Integer>() ;
        
        for(int i = 0 ; i < 10 ; i++)
            usingFor.add(i) ;
        
        for(int i : upTo(10)) 
            usingUtil.add(i) ;

        assertEquals(usingFor, usingUtil) ;
    }

    @Test
    public void testRangeSameResultAsFor() {
        List<Integer> usingFor = new ArrayList<Integer>() ;
        List<Integer> usingUtil = new ArrayList<Integer>() ;
        
        for(int i = 3 ; i < 20 ; i++)
            usingFor.add(i) ;
        
        for(int i : range(3, 20)) 
            usingUtil.add(i) ;

        assertEquals(usingFor, usingUtil) ;
    }

    @Test
    public void testToZeroFromSameResultAsFor() {
        List<Integer> usingFor = new ArrayList<Integer>() ;
        List<Integer> usingUtil = new ArrayList<Integer>() ;
        
        for(int i = 18 ; i >= 0 ; i--)
            usingFor.add(i) ;
        
        for(int i : toZeroFrom(18))
            usingUtil.add(i) ;

        assertEquals(usingFor, usingUtil) ;
    }
}
