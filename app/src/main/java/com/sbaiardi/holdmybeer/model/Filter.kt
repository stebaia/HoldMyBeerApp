package com.sbaiardi.holdmybeer.model

data class Filter(val startDate: String, val endDate: String){

    companion object {
        fun buildDate(month: String, year: String): String {
            if (month != "00" && year != "0000") {
                if (year.length >= 4 && month.length >= 2) {
                    return "${month}-${year}"
               }
            }
            return "Error"
        }
    }

}