package org.river.exertion.swe

import kotlinx.datetime.LocalDateTime
import swisseph.SweDate
import swisseph.SweDate.SE_GREG_CAL

//Note: see Julday for more on variances from swetest values
object UtcToJd {
    const val TERRESTRIAL_TIME = 0
    const val UNIVERSAL_TIME = 1

    fun getJulianTimeDecimal(utcTime : LocalDateTime, timeFlag : Int) : Double {

        val sd = SweDate()
        val checkValidInput = true

        val jdData = sd.getJDfromUTC(utcTime.year, utcTime.monthNumber, utcTime.dayOfMonth
                , utcTime.hour, utcTime.minute, utcTime.second.toDouble()
                , SE_GREG_CAL, checkValidInput)

        if (!checkValidInput) println("error: <getJulianTimeDecimal>\n") // TODO : put this to a logger

        return jdData[timeFlag]
    }
}