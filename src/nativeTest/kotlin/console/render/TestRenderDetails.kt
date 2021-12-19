package console.render

import astro.render.RenderAspect
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

        //got up to 62, should test higher
        val detailsSize = refNatalChart.getValueAspects().size

        (0 until RenderConsole.detailsRenderMaxIdx).forEach { idx ->
            val summaryDataLineFirstCol = RenderDetails.prepareDetailsDataFirstCol(idx, refNatalChart)
            val summaryDataLineSecondCol = RenderDetails.prepareDetailsDataSecondCol(idx, refNatalChart)
            val summaryDataLineThirdCol = RenderDetails.prepareDetailsDataThirdCol(idx, refNatalChart)
            println(summaryDataLineFirstCol + "|" + summaryDataLineFirstCol.length
                + summaryDataLineSecondCol + "|" + summaryDataLineSecondCol.length
                + summaryDataLineThirdCol + "|" + summaryDataLineThirdCol.length)

            val renderIdxFirstCol = idx
            val renderIdxSecondCol = renderIdxFirstCol + RenderDetails.detailsFirstColMaxIdx - 1
            val renderIdxThirdCol = renderIdxSecondCol + RenderConsole.detailsRenderMaxIdx - 1

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

    @Test
    fun testProfileDetails() {
        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)

        val refNatalChart = ValueChart(StateChart(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), AnalysisState.NO_ANALYSIS)

        println("no analysis details:")

        (0 until RenderConsole.detailsRenderMaxIdx).forEach { idx ->
            val summaryDataLineFirstCol = RenderDetails.prepareDetailsDataFirstCol(idx, refNatalChart)
            val summaryDataLineSecondCol = RenderDetails.prepareDetailsDataSecondCol(idx, refNatalChart)
            val summaryDataLineThirdCol = RenderDetails.prepareDetailsDataThirdCol(idx, refNatalChart)
            println(summaryDataLineFirstCol + "|" + summaryDataLineFirstCol.length
                   + summaryDataLineSecondCol + "|" + summaryDataLineSecondCol.length
                    + summaryDataLineThirdCol + "|" + summaryDataLineThirdCol.length)
            val renderIdxFirstCol = idx
            val renderIdxSecondCol = renderIdxFirstCol + RenderDetails.detailsFirstColMaxIdx - 1
            val renderIdxThirdCol = renderIdxSecondCol + RenderConsole.detailsRenderMaxIdx - 1

        }

    }

    @Test
    fun testProfileRomanticDetails() {
        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)

        val refNatalChart = ValueChart(StateChart(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), AnalysisState.ROMANTIC_ANALYSIS)

        println("romantic analysis aspects:")
        refNatalChart.getValueAspects().forEach { println(RenderAspect(it).getAspectValueRenderLabel() + "," + it.getBaseModNetValue() + ":" + it) }

        println("romantic analysis details:")
        (0 until RenderConsole.detailsRenderMaxIdx).forEach { idx ->
            val summaryDataLineFirstCol = RenderDetails.prepareDetailsDataFirstCol(idx, refNatalChart)
            val summaryDataLineSecondCol = RenderDetails.prepareDetailsDataSecondCol(idx, refNatalChart)
            val summaryDataLineThirdCol = RenderDetails.prepareDetailsDataThirdCol(idx, refNatalChart)
            println(summaryDataLineFirstCol + "|" + summaryDataLineFirstCol.length
                    + summaryDataLineSecondCol + "|" + summaryDataLineSecondCol.length
                    + summaryDataLineThirdCol + "|" + summaryDataLineThirdCol.length)
            val renderIdxFirstCol = idx
            val renderIdxSecondCol = renderIdxFirstCol + RenderDetails.detailsFirstColMaxIdx - 1
            val renderIdxThirdCol = renderIdxSecondCol + RenderConsole.detailsRenderMaxIdx - 1

        }
    }

    @Test
    fun testProfileCharacterDetailsComp() {
        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val synProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)

        val refNatalChart = StateChart(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val synNatalChart = StateChart(synProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val compChart = StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.COMPOSITE_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val synChart = StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.SYNASTRY_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT)

        val valueChart = ValueChart(ChartState.COMPOSITE_CHART, synChart, compChart, refNatalChart, synNatalChart)

        println("character analysis details (comp):")
        (0 until RenderConsole.detailsRenderMaxIdx).forEach { idx ->
            val summaryDataLineFirstCol = RenderDetails.prepareDetailsDataFirstCol(idx, valueChart)
            val summaryDataLineSecondCol = RenderDetails.prepareDetailsDataSecondCol(idx, valueChart)
            val summaryDataLineThirdCol = RenderDetails.prepareDetailsDataThirdCol(idx, valueChart)
            println(summaryDataLineFirstCol + "|" + summaryDataLineFirstCol.length
                    + summaryDataLineSecondCol + "|" + summaryDataLineSecondCol.length
                    + summaryDataLineThirdCol + "|" + summaryDataLineThirdCol.length)
            val renderIdxFirstCol = idx
            val renderIdxSecondCol = renderIdxFirstCol + RenderDetails.detailsFirstColMaxIdx - 1
            val renderIdxThirdCol = renderIdxSecondCol + RenderConsole.detailsRenderMaxIdx - 1

        }
    }

    @Test
    fun testProfileCharacterDetailsSyn() {
        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val synProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)

        val refNatalChart = StateChart(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val synNatalChart = StateChart(synProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val compChart = StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.COMPOSITE_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val synChart = StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.SYNASTRY_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT)

        val valueChart = ValueChart(ChartState.SYNASTRY_CHART, synChart, compChart, refNatalChart, synNatalChart)

        println("character analysis details (syn):")
        (0 until RenderConsole.detailsRenderMaxIdx).forEach { idx ->
            val summaryDataLineFirstCol = RenderDetails.prepareDetailsDataFirstCol(idx, valueChart)
            val summaryDataLineSecondCol = RenderDetails.prepareDetailsDataSecondCol(idx, valueChart)
            val summaryDataLineThirdCol = RenderDetails.prepareDetailsDataThirdCol(idx, valueChart)
            println(summaryDataLineFirstCol + "|" + summaryDataLineFirstCol.length
                    + summaryDataLineSecondCol + "|" + summaryDataLineSecondCol.length
                    + summaryDataLineThirdCol + "|" + summaryDataLineThirdCol.length)
            val renderIdxFirstCol = idx
            val renderIdxSecondCol = renderIdxFirstCol + RenderDetails.detailsFirstColMaxIdx - 1
            val renderIdxThirdCol = renderIdxSecondCol + RenderConsole.detailsRenderMaxIdx - 1

        }
    }
}