package com.icai.practicas.model;

import org.junit.Assert;


import org.junit.jupiter.api.Test;




public class TelefonoTest {
    @Test
    public void testTelefono(){

        Assert.assertEquals(true, new Telefono("670986614").validar());
        Assert.assertEquals(true, new Telefono("+34701658699").validar());
        Assert.assertEquals(true, new Telefono("911856488").validar());
        Assert.assertEquals(false, new Telefono("11111111A").validar());
        Assert.assertEquals(false, new Telefono("1111111111111111111").validar());
        Assert.assertEquals(true, new Telefono("+48916696855").validar());

    }
}
