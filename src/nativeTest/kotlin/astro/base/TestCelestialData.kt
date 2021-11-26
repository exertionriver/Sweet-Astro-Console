package astro.base

import astro.base.CelestialData.Companion.ROUND_PRECISION_SEC_DEC
import kotlinx.datetime.LocalDateTime
import swe.CalcUt
import swe.Houses
import swe.Julday
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals

class TestCelestialData {

    @Test
    fun testGetFormattedSignLongitude() {
        val testLDT = LocalDateTime(1978,11,16,18,39,0,0)
        val testLat = 30.2667
        val testLong = -97.75

        println("test localdatetime: $testLDT")
        println("test lat/long: $testLat / $testLong")

        val uniTimeDec = Julday.getJulianUTCTimeDecimal(testLDT, Julday.UNIVERSAL_TIME)
        val uniTimeHouses = Houses.getCelestialHousesData(uniTimeDec, testLat, testLong)
        val uniCelestials = CalcUt.getCelestialsData(uniTimeDec, uniTimeHouses)

        println("Universal Time Celestials (raw-long)")
        uniCelestials.forEachIndexed { idx, celestial -> println ("${Celestial.fromOrdinal(idx)}, ${celestial.longitude}") }
        assertEquals(234.0540210285634, uniCelestials[0].longitude)
        assertEquals(250.67432591735695, uniCelestials[4].longitude)
        assertEquals(257.15838507849634, uniCelestials[8].longitude)

        val uniCelestialsFormattedSecDec = uniCelestials.map{ CelestialData.getFormattedSignLongitude(it.longitude, ROUND_PRECISION_SEC_DEC) }

        println("Universal Time Celestials (calc'd-long, sign, second-decimal precision)")
        uniCelestialsFormattedSecDec.forEachIndexed { idx, celestialFormatted -> println ("${Celestial.fromOrdinal(idx)}, $celestialFormatted") }
        assertEquals("16°48'20.4689", uniCelestialsFormattedSecDec[1]) //swetest varies with "16 48'20.4697
        assertEquals(" 8°55'26.3547", uniCelestialsFormattedSecDec[5])
        assertEquals("18° 0'30.5948", uniCelestialsFormattedSecDec[9])

        val uniCelestialsFormattedSec = uniCelestials.map{ CelestialData.getFormattedSignLongitude(it.longitude) }

        println("Universal Time Celestials (calc'd-long, sign, second precision (default))")
        uniCelestialsFormattedSec.forEachIndexed { idx, celestialFormatted -> println ("${Celestial.fromOrdinal(idx)}, $celestialFormatted") }
        assertEquals("16°28'25", uniCelestialsFormattedSec[2])
        assertEquals("12°38'11", uniCelestialsFormattedSec[6])
        assertEquals("24°55'19", uniCelestialsFormattedSec[10])
    }

}