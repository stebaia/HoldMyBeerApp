package com.sbaiardi.holdmybeer.model

import org.junit.Assert.*
import org.junit.Test

class FilterTest {
    private val testFilter: Filter = Filter("","")

    @Test
    fun testBuildDateErrorTwoZero(){
        val expected = "Error"
        assertEquals(expected, Filter.buildDate("00", "0000"))
    }


    @Test
    fun testBuildDateErrorOneZeroMonth(){
        val expected = "Error"
        assertEquals(expected, Filter.buildDate("00", "0001"))
    }
    @Test
    fun testBuildDateErrorTwoYear(){
        val expected = "Error"
        assertEquals(expected, Filter.buildDate("00", "0000"))
    }
    @Test
    fun testBuildDateErrorLength(){
        val expected = "Error"
        assertEquals(expected, Filter.buildDate("1", "0001"))
    }

    @Test
    fun testBuildDateCorrectDate(){
        val expected = "01-2000"
        assertEquals(expected, Filter.buildDate("01", "2000"))
    }

    @Test
    fun testBuildDateCorrectLength(){
        val expected = "01-0001"
        assertEquals(expected, Filter.buildDate("01", "0001"))
    }

}