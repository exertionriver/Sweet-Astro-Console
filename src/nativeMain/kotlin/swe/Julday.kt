package swe

import kotlinx.cinterop.*
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import libswe.*

/* from: https://groups.io/g/swisseph/topic/75678673?p=Created,,,20,2,0,0::,,,0,0,0,75678673
"The function swe_julday(), which is the older and simpler function, expects the input date as UT1 -- although clocktimes are never given in UT1. It does not know any leap seconds. It just does not care. On the other hand, swe_utc_to_jd() expects UTC as input and does take into account leap seconds.
"The difference, which is always smaller than 1 sec, is relevant if you know a birth time with an accuracy better than a second and if your astrological methods are sensitive to an error of this extent. Then you should use swe_utc_to_jd().
"Dieter"

In order to get longitudes that match swetest / astro.com (rounding aside), use julday with UNIVERSAL_TIME settings for houses and celestials, tested with
lldb -- /usr/local/swe/swe_2.10/src/swetest -b16.11.1978 -n1 -s1 -fPLBRS -pp -eswe -house[-97.75,30.2667,P] -ut18:39:00 -edir/usr/local/swe/ephe/
(lldb) breakpoint set --file swetest.c --line 1389
(lldb) run
(lldb) print tjd
(lldb) print thour
(lldb) thread step-over
(lldb) print tjd

e.g. use the following lines in CelestialSnapshot constructor:
    , private val initCelestialHouseData : DoubleArray = Houses.getCelestialHousesData(Julday.getJulianTimeDecimal(initCurEarthLocation.utcTime, Julday.UNIVERSAL_TIME), initCurEarthLocation.latitude, initCurEarthLocation.longitude) //as per documentation, "/* calculate houses with tjd_ut */"
    , private val initSynCelestialHouseData : DoubleArray = Houses.getCelestialHousesData(Julday.getJulianTimeDecimal(initSynEarthLocation.utcTime, Julday.UNIVERSAL_TIME), initSynEarthLocation.latitude, initSynEarthLocation.longitude) //as per documentation, "/* calculate houses with tjd_ut */"
    , private val initCelestialData: Array<CelestialData> = CalcUt.getCelestialsData(Julday.getJulianTimeDecimal(initCurEarthLocation.utcTime, Julday.UNIVERSAL_TIME), initCelestialHouseData, initSynCelestialHouseData)) { //as per documentation, "/* calculate planet with tjd_et */"
 */
object Julday {
    const val TERRESTRIAL_TIME = 0
    const val UNIVERSAL_TIME = 1

    const val minInHour = 60
    const val secInHour = minInHour * 60
    const val nSecInHour : Double = secInHour.toDouble() * 1000000000

    fun getJulianUTCTimeDecimal(timeUTCLDT : LocalDateTime, timeFlag : Int = UNIVERSAL_TIME) : Double = memScoped {

        val hourDec : Double = (timeUTCLDT.hour.toDouble() + timeUTCLDT.minute.toDouble() / minInHour + timeUTCLDT.second.toDouble() / secInHour + timeUTCLDT.nanosecond / nSecInHour)

        val julDay : Double = swe_julday(timeUTCLDT.year, timeUTCLDT.monthNumber, timeUTCLDT.dayOfMonth, hourDec, SE_GREG_CAL)

        val ttmJulDay = julDay + swe_deltat(julDay)

        return if (timeFlag == TERRESTRIAL_TIME) ttmJulDay else julDay
    }

    fun getJulianUTCTimeDecimal(timeUTC : Instant, timeFlag : Int = UNIVERSAL_TIME) : Double =
        getJulianUTCTimeDecimal(timeUTC.toLocalDateTime(TimeZone.UTC), timeFlag)
}