package astro.render

import org.river.exertion.astro.render.RenderChartStateType
import org.junit.jupiter.api.Test

@OptIn(ExperimentalUnsignedTypes::class)
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