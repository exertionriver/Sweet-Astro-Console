package astro.render

import astro.base.AspectCelestial
import astro.state.ChartState
import astro.state.ChartStateType.Companion.decodeChartStateType
import astro.state.ChartStateType.Companion.encodeChartStateType
import console.state.ConsoleAspectsState
import console.state.ConsoleChartState
import console.state.ConsoleTimeAspectsState
import profile.base.Profiles
import kotlin.test.Test
import kotlin.test.assertEquals

object TestRenderChartStateType {

    @Test
    fun testRenderChartStateTypeDecoding() {
        val firstDecoding = RenderChartStateType.getChartStateTypesLabel(6)
        println ("firstDecodedChartLabel: $firstDecoding")

        val firstDecodingExclusive = RenderChartStateType.getChartStateTypesLabel(6)
        println ("firstDecodedExclusiveChartLabel: $firstDecodingExclusive")

        val secondDecoding = RenderChartStateType.getChartStateTypesLabel(9)
        println ("secondDecodedChartLabel: $secondDecoding")

        val thirdDecoding = RenderChartStateType.getChartStateTypesLabel(15)
        println ("thirdDecodedChartLabel: $thirdDecoding")

        val thirdDecodingSwitchChart = RenderChartStateType.getChartStateTypesLabel(15)
        println ("thirdDecodedSwitchChartLabel: $thirdDecodingSwitchChart")
    }

}