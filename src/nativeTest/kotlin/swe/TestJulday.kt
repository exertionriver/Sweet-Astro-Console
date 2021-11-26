package swe

import astro.base.EarthLocation
import kotlinx.cinterop.memScoped
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals

class TestJulday {

    @Test
    fun testGetJulianUTCTimeDecimal() {
        val testLDT = LocalDateTime(1978,11,16,18,39,0,0)

        println("test localdatetime: $testLDT")

        val uniTimeDec = Julday.getJulianUTCTimeDecimal(testLDT, Julday.UNIVERSAL_TIME)
        println("julian date universal time: $uniTimeDec")
        assertEquals(2443829.277083333, uniTimeDec)

        val terTimeDec = Julday.getJulianUTCTimeDecimal(testLDT, Julday.TERRESTRIAL_TIME)
        println("julian date terrestrial time: $terTimeDec")
        assertEquals(2443829.277655773, terTimeDec)
    }
}