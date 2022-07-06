package org.river.exertion.swe

import kotlinx.datetime.LocalDateTime
import org.river.exertion.astro.base.EarthLocation
import swisseph.DblObj
import swisseph.SweConst.*
import swisseph.SwissEph
import java.nio.file.Paths

@OptIn(ExperimentalUnsignedTypes::class)
object RiseTrans {

    @Suppress("NewApi")
    @ExperimentalUnsignedTypes
    fun getRiseTransForCelestial(julTime : Double, earthLocation: EarthLocation, celestialIdx : Int, flag : Int = SE_CALC_RISE) : Double {

        //val sw = SwissEph("../android/assets/ephe")

//        println("path: ${Paths.get("../android/assets/ephe").toAbsolutePath()}")

        var retVal = 0
        val starName = null //sun, moon, or planet
        val geopos = DoubleArray(3)
        val atmosPressure = 0.0
        val atmosTemp = 0.0
        val tRiseTrans = DblObj()
        val errorOut = StringBuffer()

        geopos[0] = earthLocation.longitude
        geopos[1] = earthLocation.latitude
        geopos[2] = earthLocation.altitude

        Swe.sw.swe_set_topo(geopos[0], geopos[1], geopos[2])

        retVal = Swe.sw.swe_rise_trans(julTime, CalcUt.getSweCelestialIdx(celestialIdx), starName, SEFLG_SWIEPH
            , flag, geopos, atmosPressure, atmosTemp, tRiseTrans, errorOut);

        if (retVal < 0) println("error: $errorOut") // TODO : put this to a logger

        //mirroring ut_to_lmt_lat in swetest.c:2577 //never quite got this working; times diff from swetest by 20s or so
//        swe_lmt_to_lat(tRiseTrans.value + (geopos[0] / 360), geopos[0], tRiseTrans.ptr, errorOut)

//        if (retVal.value < 0) printf("error: %s\n", errorOut.toKString()) // TODO : put this to a logger

        return tRiseTrans.`val`
    }


    fun getRiseLDTForCelestial(julTime : Double, earthLocation: EarthLocation, celestialIdx : Int) : LocalDateTime {

      return RevJul.getLDT(getRiseTransForCelestial(julTime, earthLocation, celestialIdx, SE_CALC_RISE), earthLocation.timeZone)
    }

    fun getSetLDTForCelestial(julTime : Double, earthLocation: EarthLocation, celestialIdx : Int) : LocalDateTime {

        return RevJul.getLDT(getRiseTransForCelestial(julTime, earthLocation, celestialIdx, SE_CALC_SET), earthLocation.timeZone)
    }

    fun getUpperMeridLDTForCelestial(julTime : Double, earthLocation: EarthLocation, celestialIdx : Int) : LocalDateTime {

        return RevJul.getLDT(getRiseTransForCelestial(julTime, earthLocation, celestialIdx, SE_CALC_MTRANSIT), earthLocation.timeZone)
    }

    fun getLowerMeridLDTForCelestial(julTime : Double, earthLocation: EarthLocation, celestialIdx : Int) : LocalDateTime {

        return RevJul.getLDT(getRiseTransForCelestial(julTime, earthLocation, celestialIdx, SE_CALC_ITRANSIT), earthLocation.timeZone)
    }

}