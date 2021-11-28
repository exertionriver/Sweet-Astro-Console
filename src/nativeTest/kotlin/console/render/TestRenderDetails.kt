package console.render

import astro.state.*
import astro.value.ValueChart
import platform.linux.CONTINUE
import profile.base.Profiles
import kotlin.test.Test
import kotlin.test.assertEquals

object TestRenderDetails {

    @Test
    fun testCurDetails() {
        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)

        val refNatalChart = ValueChart(StateChart(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), AnalysisState.NO_ANALYSIS)

        println("details column width:" + RenderDetails.getDetailsColumnWidth() )
        println("details end column width:" + RenderDetails.getDetailsEndColumnWidth() )

        //got up to 62, should test higher
        val detailsSize = refNatalChart.getValueAspects().size

        (0 until RenderConsole.detailsRenderMaxIdx).forEach { idx ->
            val summaryDataLineFirstCol = RenderDetails.prepareDetailsDataFirstCol(idx, idx, refNatalChart)
            val summaryDataLineSecondCol = RenderDetails.prepareDetailsDataSecondCol(idx, idx, refNatalChart)
            val summaryDataLineThirdCol = RenderDetails.prepareDetailsDataThirdCol(idx, idx, refNatalChart)
            println(summaryDataLineFirstCol + "|" + summaryDataLineFirstCol.length
                + summaryDataLineSecondCol + "|" + summaryDataLineSecondCol.length
                + summaryDataLineThirdCol + "|" + summaryDataLineThirdCol.length)

            val renderIdxFirstCol = idx
            val renderIdxSecondCol = renderIdxFirstCol + RenderDetails.detailsFirstColMaxIdx - 1
            val renderIdxThirdCol = renderIdxSecondCol + RenderConsole.detailsRenderMaxIdx + 4

            //to verify size / idx
/*            println("detailsSize: $detailsSize")
            println("1c-idx: $renderIdxFirstCol")
            println("2c-idx: $renderIdxSecondCol")
            println("3c-idx: $renderIdxThirdCol")
*/
            val dataPresentFirstCol = detailsSize >= renderIdxFirstCol
            val dataPresentSecondCol = detailsSize >= renderIdxSecondCol
            val dataPresentThirdCol = detailsSize >= renderIdxThirdCol
/*
            when {
                //header
                (idx == 0) -> {
                    //base, no color; headers only
                    assertEquals(26, summaryDataLineFirstCol.length)
                    if (dataPresentSecondCol) assertEquals(30, summaryDataLineSecondCol.length) else assertEquals(72, summaryDataLineSecondCol.length)
                    if (dataPresentThirdCol) assertEquals(57, summaryDataLineThirdCol.length) else assertEquals(48, summaryDataLineThirdCol.length)
                }
                (idx < RenderDetails.detailsFirstColMaxIdx) -> {
                    //base, no color
                    if (dataPresentFirstCol) {
                        assertEquals(33, summaryDataLineFirstCol.length)
                        if (dataPresentSecondCol) assertEquals(33, summaryDataLineSecondCol.length) else assertEquals(72, summaryDataLineSecondCol.length)
                        if (dataPresentThirdCol) assertEquals(57, summaryDataLineThirdCol.length) else assertEquals(48, summaryDataLineThirdCol.length)
                    } else {
                        assertEquals(96, summaryDataLineFirstCol.length)
                        assertEquals(0, summaryDataLineSecondCol.length)
                        assertEquals(0, summaryDataLineThirdCol.length)
                    }
                }
                (idx == RenderDetails.detailsFirstColMaxIdx) -> {
                    if (dataPresentFirstCol) {
                        assertEquals(23, summaryDataLineFirstCol.length)
                        if (dataPresentSecondCol) assertEquals(33, summaryDataLineSecondCol.length) else assertEquals(72, summaryDataLineSecondCol.length)
                        if (dataPresentThirdCol) assertEquals(57, summaryDataLineThirdCol.length) else assertEquals(48, summaryDataLineThirdCol.length)
                    } else {
                        assertEquals(96, summaryDataLineFirstCol.length)
                        assertEquals(0, summaryDataLineSecondCol.length)
                        assertEquals(0, summaryDataLineThirdCol.length)
                    }
                }
                else -> { // idx > RenderDetails.detailsFirstColMaxIdx
                    if (dataPresentFirstCol) {
                        assertEquals(0, summaryDataLineFirstCol.length) // space for summary
                        if (dataPresentSecondCol) assertEquals(33, summaryDataLineSecondCol.length) else assertEquals(72, summaryDataLineSecondCol.length)
                        if (dataPresentThirdCol) assertEquals(57, summaryDataLineThirdCol.length) else assertEquals(48, summaryDataLineThirdCol.length)
                    } else {
                        assertEquals(96, summaryDataLineFirstCol.length)
                        assertEquals(0, summaryDataLineSecondCol.length)
                        assertEquals(0, summaryDataLineThirdCol.length)
                    }
                }
            }
*/
        }

    }

}