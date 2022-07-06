package org.river.exertion.swe

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import swisseph.SweDate
import swisseph.SweDate.SE_GREG_CAL

object Julday {
    const val TERRESTRIAL_TIME = 0
    const val UNIVERSAL_TIME = 1

    const val minInHour = 60
    const val secInHour = minInHour * 60
    const val nSecInHour : Double = secInHour.toDouble() * 1000000000

    fun getJulianUTCTimeDecimal(timeUTCLDT : LocalDateTime, timeFlag : Int = UNIVERSAL_TIME) : Double {

        val hourDec : Double = (timeUTCLDT.hour.toDouble() + timeUTCLDT.minute.toDouble() / minInHour + timeUTCLDT.second.toDouble() / secInHour + timeUTCLDT.nanosecond / nSecInHour)

        val sd = SweDate(timeUTCLDT.year, timeUTCLDT.monthNumber, timeUTCLDT.dayOfMonth, hourDec, SE_GREG_CAL)

        val ttmJulDay = sd.julDay + sd.deltaT

        return if (timeFlag == TERRESTRIAL_TIME) ttmJulDay else sd.julDay
    }

    fun getJulianUTCTimeDecimal(timeUTC : Instant, timeFlag : Int = UNIVERSAL_TIME) : Double =
        getJulianUTCTimeDecimal(timeUTC.toLocalDateTime(TimeZone.UTC), timeFlag)
}