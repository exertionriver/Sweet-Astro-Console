package swe

import org.river.exertion.astro.base.Celestial
import kotlinx.datetime.LocalDateTime
import org.river.exertion.swe.CalcUt
import org.river.exertion.swe.Houses
import org.river.exertion.swe.Julday
import org.junit.jupiter.api.Test
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
        assertEquals(234.05402102854407, uniCelestials[0].longitude)
        assertEquals(250.6743259173525, uniCelestials[4].longitude)
        assertEquals(257.1583850784961, uniCelestials[8].longitude)

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