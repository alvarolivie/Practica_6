package com.icai.practicas.model;

import org.junit.Assert;

import org.junit.jupiter.api.Test;

public class DNITest {
    @Test
    public void testDNI(){

        Assert.assertEquals(true, new DNI("51500336R").validar());
        Assert.assertEquals(false, new DNI("987654321").validar());
        Assert.assertEquals(false, new DNI("98A65432D").validar());
        Assert.assertEquals(false, new DNI("00000000T").validar());
        Assert.assertEquals(false, new DNI("00000001R").validar());
        Assert.assertEquals(false, new DNI("99999999R").validar());

    }

}