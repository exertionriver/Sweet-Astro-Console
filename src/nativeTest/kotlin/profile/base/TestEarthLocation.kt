package profile.base

import astro.base.EarthLocation
import kotlinx.datetime.LocalDate
import astro.render.RenderEarthLocation
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalUnsignedTypes
class TestEarthLocation {

    private val refLocation = EarthLocation.getDefaultEarthLocation(LocalDate(2021, 11, 8))

    @Test
    fun testRender() {

        println(RenderEarthLocation.getRenderLocalTimeLabel(refLocation.localDateTime))
        println(RenderEarthLocation.getRenderUTCTimeLabel(refLocation.utcDateTime))
        println(RenderEarthLocation.getRenderLocalDateLabel(refLocation.localDateTime))
        println(RenderEarthLocation.getRenderUTCDateLabel(refLocation.utcDateTime))
        println(RenderEarthLocation.getRenderLocalTimezoneLabel(refLocation.getTimezoneOffsetString()))
        println(RenderEarthLocation.getRenderUTCTimezoneLabel())
        println(RenderEarthLocation.getRenderLocalLatitudeLabel(refLocation.latitude))
        println(RenderEarthLocation.getRenderLocalLongitudeLabel(refLocation.longitude))

        println(RenderEarthLocation.getRenderEarthLocationDataFirstLine(refLocation))
        println(RenderEarthLocation.getRenderEarthLocationDataSecondLine(refLocation))

        println(EarthLocation.getTimeString(refLocation.localDateTime))
        println(EarthLocation.getTimeString(refLocation.utcDateTime))
    }

    @Test
    fun testTimezoneIteration()  {
        //outside of -18 to 18 throws kotlinx.datetime.IllegalTimeZoneException
        for (timeZoneOffset in -18..18) {
            val timeZoneStringInt =
                EarthLocation.getOffsetStringInt(EarthLocation.getTimeZoneFromOffsetInt(timeZoneOffset))
            val timeZoneStringDouble =
                EarthLocation.getOffsetStringDouble(EarthLocation.getTimeZoneFromOffsetInt(timeZoneOffset))

            if (timeZoneOffset % 2 == 0)
                println ("timeZone: $timeZoneOffset: $timeZoneStringInt")
            else
                println ("timeZone: $timeZoneOffset: $timeZoneStringDouble")

            if (timeZoneOffset == -12) assertEquals("-12.0", timeZoneStringDouble)

            if (timeZoneOffset == 0) assertEquals("Z", EarthLocation.getTimeZoneString(timeZoneOffset))

            if (timeZoneOffset == 5) assertEquals("+5", timeZoneStringInt)
        }
    }
}