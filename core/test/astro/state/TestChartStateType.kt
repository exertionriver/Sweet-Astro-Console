package astro.state

import org.junit.jupiter.api.Test
import org.river.exertion.astro.state.ChartStateType
import org.river.exertion.astro.state.ChartStateType.Companion.decodeChartStateType
import org.river.exertion.astro.state.ChartStateType.Companion.encodeChartStateType
import kotlin.test.assertEquals

object TestChartStateType {

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testChartStateTypeEncoding() {
        val firstEncoding = listOf(ChartStateType.SYN_NATAL_CHART, ChartStateType.COMPOSITE_CHART, ChartStateType.SYNASTRY_CHART).encodeChartStateType()
        assertEquals(13, firstEncoding)

        val secondEncoding = listOf(ChartStateType.REF_NATAL_CHART, ChartStateType.SYN_NATAL_CHART, ChartStateType.COMPOSITE_CHART).encodeChartStateType()
        assertEquals(7, secondEncoding)

        val thirdEncoding = listOf(ChartStateType.REF_NATAL_CHART, ChartStateType.SYNASTRY_CHART).encodeChartStateType()
        assertEquals(10, thirdEncoding)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
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