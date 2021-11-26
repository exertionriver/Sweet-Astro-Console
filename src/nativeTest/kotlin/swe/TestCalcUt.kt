package swe

import astro.base.Celestial
import astro.base.EarthLocation
import kotlinx.cinterop.memScoped
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals

class TestCalcUt {

    @Test
    fun testGetCelestialsData() {
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

        val uniCelestialsDeg = uniCelestials.map{ it.longitude.toInt() }
        val uniCelestialsMin = uniCelestials.mapIndexed { idx, celestial -> ((celestial.longitude - uniCelestialsDeg[idx]) * 60).toInt() }
        val uniCelestialsSec = uniCelestials.mapIndexed { idx, celestial -> ((celestial.longitude - uniCelestialsDeg[idx] - uniCelestialsMin[idx].toDouble() / 60) * 3600) }

        println("Universal Time Celestials (calc'd-long)")
        uniCelestialsDeg.forEachIndexed { idx, celestialDeg -> println ("${Celestial.fromOrdinal(idx)}, $celestialDeg ${uniCelestialsMin[idx]}'${uniCelestialsSec[idx]}") }
        assertEquals(76, uniCelestialsDeg[1])
        assertEquals(128, uniCelestialsDeg[5])
        assertEquals(198, uniCelestialsDeg[9])

        assertEquals(28, uniCelestialsMin[2])
        assertEquals(38, uniCelestialsMin[6])
        assertEquals(55, uniCelestialsMin[10])

        assertEquals(9.200924522779152, uniCelestialsSec[3])
        assertEquals(29.869690206339815, uniCelestialsSec[7])
        assertEquals(26.21396531054976, uniCelestialsSec[11])
    }
}