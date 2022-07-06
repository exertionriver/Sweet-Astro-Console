package org.river.exertion.swe

import org.river.exertion.Constants.degrees
import org.river.exertion.astro.base.CelestialHouse
import swisseph.SweConst.SEFLG_RADIANS
import swisseph.SweConst.SE_VERTEX
import swisseph.SwissEph

object Houses {

    fun getCelestialHousesData(julianUtcTimeDecimal : Double, earthLatitude : Double, earthLongitude : Double) : DoubleArray {

        //val sw = SwissEph("../android/assets/ephe")

        var houseIdx : Int
        var cuspIdx : Int

        var houseCusps = DoubleArray(13)
        var ascmc = DoubleArray(10) //used for vertex.
        var retVal = 0

        val celestialHousesData = DoubleArray(CelestialHouse.values().size)

        retVal = Swe.sw.swe_houses(julianUtcTimeDecimal, SEFLG_RADIANS, earthLatitude, earthLongitude, CelestialHouse.getHouseSystem().code, houseCusps, ascmc)
        if (retVal< 0) println("error: <populateCelestialHousePositionData>\n" ) // TODO : put this to a logger

        for (house in CelestialHouse.values() ) {
            houseIdx = house.ordinal
            cuspIdx = houseIdx + 1

            celestialHousesData[houseIdx] =
                when (house) {
                    CelestialHouse.VERTEX -> ascmc[SE_VERTEX].degrees()
                    else -> houseCusps[cuspIdx].degrees()
                }
        }

        return celestialHousesData
    }

    fun getCompositeCelestialHousesData(firstCelestialHousesData: DoubleArray, secondCelestialHouses: DoubleArray) : DoubleArray {

        val compositeCelestialHousesData = DoubleArray(CelestialHouse.values().size)

        for (house in CelestialHouse.values() ) {
            compositeCelestialHousesData[house.ordinal] = DegMidp.getMidpoint(firstCelestialHousesData[house.ordinal], secondCelestialHouses[house.ordinal])
        }

        return compositeCelestialHousesData
    }
}