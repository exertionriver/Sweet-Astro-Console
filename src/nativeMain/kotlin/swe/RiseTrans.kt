package swe

import astro.base.EarthLocation
import kotlinx.cinterop.*
import kotlinx.datetime.LocalDateTime
import libswe.*
import platform.posix.double_tVar
import platform.posix.printf

object RiseTrans {

    @ExperimentalUnsignedTypes
    fun getRiseTransForCelestial(julTime : Double, earthLocation: EarthLocation, celestialIdx : Int, flag : Int = SE_CALC_RISE) : Double = memScoped {

        val retVal = alloc<int32Var>()
        val starName = null //sun, moon, or planet
        val geopos = allocArray<double_tVar>(3)
        val atmosPressure = 0.0
        val atmosTemp = 0.0
        val tRiseTrans = alloc<double_tVar>()
        val tRiseTransRet = alloc<double_tVar>()
        val errorOut = allocArray<ByteVar>(AS_MAXCH)

        geopos[0] = earthLocation.longitude
        geopos[1] = earthLocation.latitude
        geopos[2] = earthLocation.altitude

        swe_set_topo(geopos[0], geopos[1], geopos[2])

        retVal.value = swe_rise_trans(julTime, CalcUt.getSweCelestialIdx(celestialIdx), starName, SEFLG_SWIEPH
            , flag, geopos, atmosPressure, atmosTemp, tRiseTrans.ptr, errorOut);

        if (retVal.value < 0) printf("error: %s\n", errorOut.toKString()) // TODO : put this to a logger

        //mirroring ut_to_lmt_lat in swetest.c:2577 //never quite got this working; times diff from swetest by 20s or so
//        swe_lmt_to_lat(tRiseTrans.value + (geopos[0] / 360), geopos[0], tRiseTrans.ptr, errorOut)

//        if (retVal.value < 0) printf("error: %s\n", errorOut.toKString()) // TODO : put this to a logger

        return tRiseTrans.value
    }


    fun getRiseLDTForCelestial(julTime : Double, earthLocation: EarthLocation, celestialIdx : Int) : LocalDateTime = memScoped {

      return RevJul.getLDT(getRiseTransForCelestial(julTime, earthLocation, celestialIdx, SE_CALC_RISE), earthLocation.timeZone)
    }

    fun getSetLDTForCelestial(julTime : Double, earthLocation: EarthLocation, celestialIdx : Int) : LocalDateTime = memScoped {

        return RevJul.getLDT(getRiseTransForCelestial(julTime, earthLocation, celestialIdx, SE_CALC_SET), earthLocation.timeZone)
    }

    fun getUpperMeridLDTForCelestial(julTime : Double, earthLocation: EarthLocation, celestialIdx : Int) : LocalDateTime = memScoped {

        return RevJul.getLDT(getRiseTransForCelestial(julTime, earthLocation, celestialIdx, SE_CALC_MTRANSIT), earthLocation.timeZone)
    }

    fun getLowerMeridLDTForCelestial(julTime : Double, earthLocation: EarthLocation, celestialIdx : Int) : LocalDateTime = memScoped {

        return RevJul.getLDT(getRiseTransForCelestial(julTime, earthLocation, celestialIdx, SE_CALC_ITRANSIT), earthLocation.timeZone)
    }

}