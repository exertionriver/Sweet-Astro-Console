package swe

import kotlinx.cinterop.*
import kotlinx.datetime.*
import libswe.*
import platform.posix.double_tVar

//Note: see Julday for more on variances from swetest values
object UtcToJd {
    const val TERRESTRIAL_TIME = 0
    const val UNIVERSAL_TIME = 1

    fun getJulianTimeDecimal(utcTime : LocalDateTime, timeFlag : Int) : Double = memScoped {

        val jdData = allocArray<double_tVar>(2)
        val errorOut = allocArray<ByteVar>(AS_MAXCH)
        val retVal = alloc<int32Var>()

        swe_utc_to_jd(utcTime.year, utcTime.monthNumber, utcTime.dayOfMonth
                , utcTime.hour, utcTime.minute, utcTime.second.toDouble()
                , SE_GREG_CAL, jdData, errorOut)
        if (retVal.value < 0) platform.posix.printf("error: %s\n", errorOut.toString()) // TODO : put this to a logger

        return jdData[timeFlag]
    }
}