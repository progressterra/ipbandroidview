package com.progressterra.ipbandroidview

import com.progressterra.ipbandroidview.ext.isEmail
import org.junit.Assert
import org.junit.Test

internal class EmailValidationTest {

    @Test
    fun `success validated when correct data`() {
        Assert.assertEquals(true, "lalala@gmkak.ccc".isEmail())
    }

    @Test
    fun `failed validated when incorrect data`() {
        Assert.assertEquals(false, "lalalagmkakcc123c".isEmail())
    }
}