package astro.state

import astro.base.AspectCelestial
import astro.state.ChartStateType.Companion.decodeChartStateType
import astro.state.ChartStateType.Companion.encodeChartStateType
import console.state.ConsoleAspectsState
import console.state.ConsoleChartState
import console.state.ConsoleTimeAspectsState
import profile.base.Profiles
import kotlin.test.Test
import kotlin.test.assertEquals

object TestChartStateType {

    @Test
    fun testChartStateTypeEncoding() {
        val firstEncoding = listOf(ChartStateType.SYN_NATAL_CHART, ChartStateType.COMPOSITE_CHART, ChartStateType.SYNASTRY_CHART).encodeChartStateType()
        assertEquals(13, firstEncoding)

        val secondEncoding = listOf(ChartStateType.REF_NATAL_CHART, ChartStateType.SYN_NATAL_CHART, ChartStateType.COMPOSITE_CHART).encodeChartStateType()
        assertEquals(7, secondEncoding)

        val thirdEncoding = listOf(ChartStateType.REF_NATAL_CHART, ChartStateType.SYNASTRY_CHART).encodeChartStateType()
        assertEquals(10, thirdEncoding)
    }

    @Test
    fun testChartStateTypeDecoding() {
        val firstDecoding = 6.decodeChartStateType()
        assertEquals(listOf(ChartStateType.REF_NATAL_CHART, ChartStateType.COMPOSITE_CHART), firstDecoding)

        val secondDecoding = 9.decodeChartStateType()
        assertEquals(listOf(ChartStateType.SYN_NATAL_CHART, ChartStateType.SYNASTRY_CHART), secondDecoding)

        val thirdDecoding = 15.decodeChartStateType()
        assertEquals(listOf(ChartStateType.SYN_NATAL_CHART, ChartStateType.REF_NATAL_CHART, ChartStateType.COMPOSITE_CHART, ChartStateType.SYNASTRY_CHART), thirdDecoding)
    }

}