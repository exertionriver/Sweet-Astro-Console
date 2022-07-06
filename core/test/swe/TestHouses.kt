package swe

import org.river.exertion.astro.base.CelestialHouse
import kotlinx.datetime.LocalDateTime
import org.river.exertion.swe.Houses
import org.river.exertion.swe.Julday
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestHouses {

    @Test
    fun testGetJulianUTCTimeDecimal() {
        val testLDT = LocalDateTime(1978,11,16,18,39,0,0)
        val testLat = 30.2667
        val testLong = -97.75

        println("test localdatetime: $testLDT")
        println("test lat/long: $testLat / $testLong")

        val uniTimeDec = Julday.getJulianUTCTimeDecimal(testLDT, Julday.UNIVERSAL_TIME)
        val uniTimeHouses = Houses.getCelestialHousesData(uniTimeDec, testLat, testLong)

        println("Universal Time Houses (raw)")
        uniTimeHouses.forEachIndexed { idx, house -> println ("${CelestialHouse.fromOrdinal(idx)}, $house") }
        assertEquals(315.2041939883415, uniTimeHouses[0])
        assertEquals(83.2357377394306, uniTimeHouses[4])
        assertEquals(212.06931738990784, uniTimeHouses[8])

        val uniTimeHousesDeg = uniTimeHouses.map{ it.toInt() }
        val uniTimeHousesMin = uniTimeHouses.mapIndexed { idx, house -> ((house - uniTimeHousesDeg[idx]) * 60).toInt() }
        val uniTimeHousesSec = uniTimeHouses.mapIndexed { idx, house -> ((house - uniTimeHousesDeg[idx] - uniTimeHousesMin[idx].toDouble() / 60) * 3600) }

        println("Universal Time Houses (calc'd)")
        uniTimeHousesDeg.forEachIndexed { idx, houseDeg -> println ("${CelestialHouse.fromOrdinal(idx)}, $houseDeg ${uniTimeHousesMin[idx]}'${"%2.4f".format(uniTimeHousesSec[idx])}") }
        assertEquals(356, uniTimeHousesDeg[1])
        assertEquals(106, uniTimeHousesDeg[5])
        assertEquals(239, uniTimeHousesDeg[9])

        assertEquals(4, uniTimeHousesMin[2])
        assertEquals(12, uniTimeHousesMin[6])
        assertEquals(14, uniTimeHousesMin[10])

        assertEquals(20.577770035137767, uniTimeHousesSec[3])
        assertEquals(38.658907906451475, uniTimeHousesSec[7])
        assertEquals(31.86929591172887, uniTimeHousesSec[11])
    }
}