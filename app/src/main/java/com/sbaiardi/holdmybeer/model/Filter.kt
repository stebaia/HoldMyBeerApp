package com.sbaiardi.holdmybeer.model

import java.lang.NumberFormatException

data class Filter(val startDate: String, val endDate: String){

    companion object {
        fun buildDate(month: Int, year: Int): String {
            if (month in 1..12 && year in 1800..2021) {
                    if (month in 1..9) {
                        return "0${month}-${year}"
                    }
                    return "${month}-${year}"
            }
            throw NumberFormatException("no valid date found, check filter")
        }
    }

}