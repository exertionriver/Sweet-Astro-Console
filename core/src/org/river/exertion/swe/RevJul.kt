package org.river.exertion.swe

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import swisseph.SweDate
import swisseph.SweDate.SE_GREG_CAL

object RevJul {

    fun getLDT(julTime : Double, timeZone : TimeZone = TimeZone.UTC) : LocalDateTime {

        val sd = SweDate(julTime, SE_GREG_CAL)

        val retHour = sd.hour.toInt()
        val retHourRemainder = sd.hour - retHour

        val retMin = (retHourRemainder * Julday.minInHour).toInt()
        val retMinRemainder = (retMin.toDouble() / Julday.minInHour)

        val retSec = ((retHourRemainder - retMinRemainder) * Julday.secInHour).toInt()
        val retSecRemainder = (retSec.toDouble() / Julday.secInHour)

        val retNSec = ((retHourRemainder - retMinRemainder - retSecRemainder) * Julday.nSecInHour).toInt()

        return LocalDateTime(sd.year, sd.month, sd.day, retHour, retMin, retSec, retNSec).toInstant(TimeZone.UTC).toLocalDateTime(timeZone)
    }

    fun getInstant(julTime : Double, timeZone : TimeZone = TimeZone.UTC) = getLDT(julTime, timeZone).toInstant(timeZone)
}