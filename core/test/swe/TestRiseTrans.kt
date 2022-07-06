package swe

import org.river.exertion.astro.base.Celestial
import org.river.exertion.astro.base.EarthLocation
import kotlinx.datetime.*
import org.river.exertion.Constants.ALT_TNM
import org.river.exertion.Constants.LAT_TNM
import org.river.exertion.Constants.LON_TNM
import org.river.exertion.Constants.TZ_MST
import org.river.exertion.profile.base.Profile
import org.river.exertion.swe.Julday
import org.river.exertion.swe.RiseTrans
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@ExperimentalUnsignedTypes
class TestRiseTrans {

    private val refProfile = Profile("testRiseTransProfile"
        , EarthLocation(LON_TNM, LAT_TNM, ALT_TNM, TZ_MST
        , LocalDate(2021, 11,8))
    )

    @Test
    fun testRiseTrans() {

        println(refProfile.earthLocation.getLocalTimeString())

        for (celestial in Celestial.values()) {

//            println(RenderCelestial.fromName(celestial.toString())!!.getLabel())
            print("rising: ") ; pollRising(celestial.ordinal)
            print("setting: ") ; pollSetting(celestial.ordinal)
            print("upper meridian: ") ; pollUpperMeridian(celestial.ordinal)
            print("lower meridian: ") ; pollLowerMeridian(celestial.ordinal)
        }
    }

    private fun pollRising(celestialIdx : Int, earthLocation : EarthLocation = refProfile.earthLocation) {

        val retLDT = RiseTrans.getRiseLDTForCelestial(Julday.getJulianUTCTimeDecimal(earthLocation.utcDateTime), earthLocation, celestialIdx)

        println(EarthLocation.getTimeString(retLDT))
    }

    @Test
    fun testRising() {

        val retLDT = RiseTrans.getRiseLDTForCelestial(
            Julday.getJulianUTCTimeDecimal(refProfile.earthLocation.utcDateTime), refProfile.earthLocation, Celestial.SUN.ordinal)

        assertEquals(2021, retLDT.year)
        assertEquals(11, retLDT.monthNumber)
        assertEquals(8, retLDT.dayOfMonth)
        assertEquals(6, retLDT.hour)
        assertEquals(33, retLDT.minute)
        assertEquals(24, retLDT.second)
//        assertEquals(38029789, retLDT.nanosecond)
    }

    private fun pollSetting(celestialIdx : Int, earthLocation : EarthLocation = refProfile.earthLocation) {

        val retLDT = RiseTrans.getSetLDTForCelestial(Julday.getJulianUTCTimeDecimal(earthLocation.utcDateTime), earthLocation, celestialIdx)

        println(EarthLocation.getTimeString(retLDT))
    }

    @Test
    fun testSetting() {

        val retLDT = RiseTrans.getSetLDTForCelestial(
            Julday.getJulianUTCTimeDecimal(refProfile.earthLocation.utcDateTime), refProfile.earthLocation, Celestial.MERCURY.ordinal)

        assertEquals(2021, retLDT.year)
        assertEquals(11, retLDT.monthNumber)
        assertEquals(8, retLDT.dayOfMonth)
        assertEquals(16, retLDT.hour)
        assertEquals(28, retLDT.minute)
        assertEquals(21, retLDT.second)
//        assertEquals(126128613, retLDT.nanosecond)
    }

    private fun pollUpperMeridian(celestialIdx : Int, earthLocation : EarthLocation = refProfile.earthLocation) {

        val retLDT = RiseTrans.getUpperMeridLDTForCelestial(Julday.getJulianUTCTimeDecimal(earthLocation.utcDateTime), earthLocation, celestialIdx)

        println(EarthLocation.getTimeString(retLDT))
    }

    @Test
    fun testUpperMeridian() {

        val retLDT = RiseTrans.getUpperMeridLDTForCelestial(
            Julday.getJulianUTCTimeDecimal(refProfile.earthLocation.utcDateTime), refProfile.earthLocation, Celestial.MARS.ordinal)

        assertEquals(2021, retLDT.year)
        assertEquals(11, retLDT.monthNumber)
        assertEquals(8, retLDT.dayOfMonth)
        assertEquals(11, retLDT.hour)
        assertEquals(5, retLDT.minute)
        assertEquals(39, retLDT.second)
//        assertEquals(967055559, retLDT.nanosecond)
    }

    private fun pollLowerMeridian(celestialIdx : Int, earthLocation : EarthLocation = refProfile.earthLocation) {

        val retLDT = RiseTrans.getLowerMeridLDTForCelestial(Julday.getJulianUTCTimeDecimal(earthLocation.utcDateTime), earthLocation, celestialIdx)

        println(EarthLocation.getTimeString(retLDT))
    }

    @Test
    fun testLowerMeridian() {

        val retLDT = RiseTrans.getLowerMeridLDTForCelestial(
            Julday.getJulianUTCTimeDecimal(refProfile.earthLocation.utcDateTime), refProfile.earthLocation, Celestial.SATURN.ordinal)

        assertEquals(2021, retLDT.year)
        assertEquals(11, retLDT.monthNumber)
        assertEquals(8, retLDT.dayOfMonth)
        assertEquals(5, retLDT.hour)
        assertEquals(31, retLDT.minute)
        assertEquals(21, retLDT.second)
//        assertEquals(435811936, retLDT.nanosecond)
    }
}
