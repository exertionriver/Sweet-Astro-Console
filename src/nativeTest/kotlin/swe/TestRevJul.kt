package swe

import astro.base.EarthLocation
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals

class TestRevJul {

    @Test
    fun testLDTUTC() {
        val curMoment = Clock.System.now()

        val retLDT = RevJul.getLDT(Julday.getJulianUTCTimeDecimal(curMoment))
        val retInstant = RevJul.getInstant(Julday.getJulianUTCTimeDecimal(curMoment))

        println("retInstant: $retInstant")

        println("retYear: ${retLDT.year}")
        println("retMin: ${retLDT.monthNumber}")
        println("retDay: ${retLDT.dayOfMonth}")
        println("retHour: ${retLDT.hour}")
        println("retMin: ${retLDT.minute}")
        println("retSec: ${retLDT.second}")
        println("retNSec: ${retLDT.nanosecond}")
    }

    @Test
    fun testLDTLocalTime() {
        val curMoment = Clock.System.now()

        val retLDT = RevJul.getLDT(Julday.getJulianUTCTimeDecimal(curMoment), TimeZone.currentSystemDefault())
        val retInstant = RevJul.getInstant(Julday.getJulianUTCTimeDecimal(curMoment), TimeZone.currentSystemDefault())

        println("retInstant: $retInstant")

        println("retYear: ${retLDT.year}")
        println("retMin: ${retLDT.monthNumber}")
        println("retDay: ${retLDT.dayOfMonth}")
        println("retHour: ${retLDT.hour}")
        println("retMin: ${retLDT.minute}")
        println("retSec: ${retLDT.second}")
        println("retNSec: ${retLDT.nanosecond}")
    }

    @ExperimentalUnsignedTypes
    @Test
    fun testLDTTimezone() {
        val curMoment = Clock.System.now()

        val retLDT = RevJul.getLDT(Julday.getJulianUTCTimeDecimal(curMoment), TimeZone.of("America/Denver"))
        val retInstant = RevJul.getInstant(Julday.getJulianUTCTimeDecimal(curMoment), TimeZone.of("America/Denver"))

        val retLDTOffset = RevJul.getLDT(Julday.getJulianUTCTimeDecimal(curMoment), EarthLocation.getTimeZoneFromOffsetInt(-7))
        val retInstantOffset = RevJul.getInstant(Julday.getJulianUTCTimeDecimal(curMoment), EarthLocation.getTimeZoneFromOffsetInt(-7))

        println("retInstant: $retInstant")

        println("retYear: ${retLDT.year}")
        println("retMin: ${retLDT.monthNumber}")
        println("retDay: ${retLDT.dayOfMonth}")
        println("retHour: ${retLDT.hour}")
        println("retMin: ${retLDT.minute}")
        println("retSec: ${retLDT.second}")
        println("retNSec: ${retLDT.nanosecond}")

        assertEquals(retLDTOffset, retLDT)
        assertEquals(retInstantOffset, retInstant)
    }
}