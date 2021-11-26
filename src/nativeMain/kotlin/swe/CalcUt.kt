package swe

import astro.base.AspectCelestial
import astro.base.CelestialData
import astro.base.CelestialHouse
import astro.base.Celestial
import kotlinx.cinterop.*
import libswe.*
import platform.posix.printf

object CalcUt {

    fun getSweCelestialIdx(celestialIdx: Int) : Int {

        return when (Celestial.fromOrdinal(celestialIdx)!!) {
            Celestial.SUN -> SE_SUN
            Celestial.MOON -> SE_MOON
            Celestial.MERCURY -> SE_MERCURY
            Celestial.VENUS -> SE_VENUS
            Celestial.MARS -> SE_MARS
            Celestial.JUPITER -> SE_JUPITER
            Celestial.SATURN -> SE_SATURN
            Celestial.URANUS -> SE_URANUS
            Celestial.NEPTUNE -> SE_NEPTUNE
            Celestial.PLUTO -> SE_PLUTO
            Celestial.NORTH_NODE -> SE_TRUE_NODE
            Celestial.BLACK_MOON_LILITH -> SE_MEAN_APOG
            Celestial.CHIRON -> SE_CHIRON
            Celestial.PHOLUS -> SE_PHOLUS
            Celestial.CERES -> SE_CERES
            Celestial.PALLAS -> SE_PALLAS
            Celestial.JUNO -> SE_JUNO
            Celestial.VESTA -> SE_VESTA
            else -> 0
        }
    }

    private fun getCelestialData(julianUtcTimeDecimal : Double, celestialIdx : Int, celestialHousesData : DoubleArray, synCelestialHousesData : DoubleArray, aspectCelestialOverride: AspectCelestial = AspectCelestial.ASPECT_CELESTIAL_NONE, aspectCelestialLongOverride : Double = 0.0) : CelestialData = memScoped {

        val calcUtDatas = allocArray<platform.posix.double_tVar>(CalcUtDatas.SIZE)
        val errorOut = allocArray<ByteVar>(AS_MAXCH)
        val retVal = alloc<int32Var>()

        val celestialData = DoubleArray(CalcUtDatas.EXT_SIZE) //for house and transitHouse data

        retVal.value = swe_calc_ut(julianUtcTimeDecimal, getSweCelestialIdx(celestialIdx), SEFLG_SPEED, calcUtDatas, errorOut)
        if (retVal.value < 0) printf("error: %s\n", errorOut.toKString()) // TODO : put this to a logger

        for (data in CalcUtDatas.values()) {
            celestialData[data.ordinal] = calcUtDatas[data.ordinal]
//            println (data.ordinal.toString() + ":" +calcUtDatas[data.ordinal].toString())
        }

        celestialData[CalcUtDatas.HOUSE_DATA_IDX] = CelestialHouse.getHouseData(calcUtDatas[CalcUtDatas.LONGITUDE_DATA.ordinal], celestialHousesData)
        celestialData[CalcUtDatas.TRANSIT_HOUSE_DATA_IDX] = CelestialHouse.getHouseData(calcUtDatas[CalcUtDatas.LONGITUDE_DATA.ordinal], synCelestialHousesData)

//        println("celesitalIdx: " + celestialIdx + "house: " + celestialData[CalcUtDatas.HOUSE_DATA_IDX] + "transit house: " + celestialData[CalcUtDatas.TRANSIT_HOUSE_DATA_IDX])

        return CelestialData(celestialData)
    }

    fun getCelestialsData(julianUtcTimeDecimal : Double, celestialHousesData : DoubleArray, synCelestialHousesData : DoubleArray = celestialHousesData, aspectCelestialOverride: AspectCelestial = AspectCelestial.ASPECT_CELESTIAL_NONE, aspectCelestialLongOverride : Double = 0.0) : Array<CelestialData> {

        return Array(Celestial.values().size) { celestialIdx:Int -> getCelestialData(julianUtcTimeDecimal, celestialIdx, celestialHousesData, synCelestialHousesData, aspectCelestialOverride, aspectCelestialLongOverride) }
    }

    private fun getCompositeCelestialData(celestialIdx : Int, compositeCelestialHousesData : DoubleArray, firstCelestialsData: Array<CelestialData>, secondCelestialsData: Array<CelestialData>) : CelestialData = memScoped {

        val compositeCelestialData = DoubleArray(CalcUtDatas.EXT_SIZE)
        val calcUtDatasExtSizeIdx = CalcUtDatas.EXT_SIZE - 1

        for (datasIdx in 0..calcUtDatasExtSizeIdx)
            when(datasIdx) {
                CalcUtDatas.LONGITUDE_DATA.ordinal -> compositeCelestialData[datasIdx] = DegMidp.getMidpoint(firstCelestialsData[celestialIdx].longitude, secondCelestialsData[celestialIdx].longitude)
                CalcUtDatas.LATITUDE_DATA.ordinal -> compositeCelestialData[datasIdx] = DegMidp.getMidpoint(firstCelestialsData[celestialIdx].latitude, secondCelestialsData[celestialIdx].latitude)
                CalcUtDatas.DISTANCE_DATA.ordinal -> compositeCelestialData[datasIdx] = (firstCelestialsData[celestialIdx].distance + secondCelestialsData[celestialIdx].distance) / 2
                CalcUtDatas.LONG_SPEED_DATA.ordinal -> compositeCelestialData[datasIdx] = (firstCelestialsData[celestialIdx].longitudeSpeed + secondCelestialsData[celestialIdx].longitudeSpeed) / 2
                CalcUtDatas.LAT_SPEED_DATA.ordinal -> compositeCelestialData[datasIdx] = (firstCelestialsData[celestialIdx].latitudeSpeed + secondCelestialsData[celestialIdx].latitudeSpeed) / 2
                CalcUtDatas.DIST_SPEED_DATA.ordinal -> compositeCelestialData[datasIdx] = (firstCelestialsData[celestialIdx].distanceSpeed + secondCelestialsData[celestialIdx].distanceSpeed) / 2
                CalcUtDatas.HOUSE_DATA_IDX -> compositeCelestialData[datasIdx] = CelestialHouse.getHouseData(compositeCelestialData[CalcUtDatas.LONGITUDE_DATA.ordinal], compositeCelestialHousesData)
                //transit_house_data doesn't have a value on a composite chart
                CalcUtDatas.TRANSIT_HOUSE_DATA_IDX -> compositeCelestialData[datasIdx] = CelestialHouse.getHouseData(compositeCelestialData[CalcUtDatas.LONGITUDE_DATA.ordinal], compositeCelestialHousesData)
            }

        return CelestialData(compositeCelestialData)
    }

    fun getCompositeCelestialsData(compositeCelestialHousesData : DoubleArray, firstCelestialsData: Array<CelestialData>, secondCelestialsData: Array<CelestialData>) : Array<CelestialData> {

        return Array(Celestial.values().size) { celestialIdx:Int -> getCompositeCelestialData(celestialIdx, compositeCelestialHousesData, firstCelestialsData, secondCelestialsData) }

    }

}
