package com.boothj5.shithead.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShitheadExceptionTest {

    @Test
    public void containsMessage() {
        ShitheadException exception = new ShitheadException("Argh!") ;
        assertEquals(exception.getMessage(), "Argh!") ;
    }

    @Test
    public void doesntFailWithEmptyMessage() {
        ShitheadException exception = new ShitheadException("") ;
        assertEquals(exception.getMessage(), "") ;
    }

    @Test
    public void doesntFailWithNullMessage() {
        ShitheadException exception = new ShitheadException(null) ;
        assertEquals(exception.getMessage(), null) ;
    }
}
