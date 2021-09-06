package com.sbaiardi.holdmybeer.model

import net.bytebuddy.implementation.bytecode.Throw
import org.junit.Assert.*
import org.junit.Test
import java.lang.NumberFormatException

class FilterTest {
    private val testFilter: Filter = Filter("","")

    @Test(expected = NumberFormatException::class)
    fun testBuildDateErrorTwoZero(){
        val expected= "error"
        assertEquals(expected, Filter.buildDate(0,0 ))
    }


    @Test(expected = NumberFormatException::class)
    fun testBuildDateErrorOneZeroMonth(){
        val expected = "Error"
        assertEquals(expected, Filter.buildDate(0, 1))
    }

    @Test(expected = NumberFormatException::class)
    fun testBuildDateErrorTwoYear(){
        val expected = "Error"
        assertEquals(expected, Filter.buildDate(1, 0))
    }
    @Test(expected = NumberFormatException::class)
    fun testBuildDateErrorLength(){
        val expected = "Error"
        assertEquals(expected, Filter.buildDate(1, 1))
    }

    @Test
    fun testBuildDateCorrectDateOneNumber(){
        val expected = "01-2000"
        assertEquals(expected, Filter.buildDate(1, 2000))
    }
    @Test
    fun testBuildDateCorrectDate(){
        val expected = "11-2000"
        assertEquals(expected, Filter.buildDate(11, 2000))
    }

}