package swe

import astro.base.CelestialHouse
import kotlinx.cinterop.*
import libswe.SE_VERTEX
import libswe.int32Var
import libswe.swe_houses
import platform.posix.double_tVar
import platform.posix.printf

object Houses {

    fun getCelestialHousesData(julianUtcTimeDecimal : Double, earthLatitude : Double, earthLongitude : Double) : DoubleArray = memScoped  {

        var houseIdx : Int
        var cuspIdx : Int

        val houseCusps = allocArray<double_tVar>(13)
        val ascmc = allocArray<double_tVar>(10) //used for vertex.
        val retVal = alloc<int32Var>()

        val celestialHousesData = DoubleArray(CelestialHouse.values().size)

        retVal.value = swe_houses(julianUtcTimeDecimal, earthLatitude, earthLongitude, CelestialHouse.getHouseSystem().toInt(), houseCusps, ascmc)
        if (retVal.value < 0) printf("error: <populateCelestialHousePositionData>\n" ) // TODO : put this to a logger

        for (house in CelestialHouse.values() ) {
            houseIdx = house.ordinal
            cuspIdx = houseIdx + 1

            celestialHousesData[houseIdx] =
                when (house) {
                    CelestialHouse.VERTEX -> ascmc[SE_VERTEX]
                    else -> houseCusps[cuspIdx]
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