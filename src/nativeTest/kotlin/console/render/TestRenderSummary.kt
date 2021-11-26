package render

import astro.state.*
import astro.value.ValueChart
import console.render.RenderChart
import console.render.RenderSummary
import profile.base.Profiles
import kotlin.test.Test
import kotlin.test.assertEquals

object TestRenderSummary {

    @Test
    fun testBorderShapes() {

        for(idx in 0..30) {
            when (idx == 30) {
                true -> println(RenderSummary.getSummaryRowBorderShape(idx = idx))
                else -> print(RenderSummary.getSummaryRowBorderShape(idx = idx))
            }
        }

        println(RenderSummary.getTopSummaryColBorderShape(colWidth = 30))

        for(idx in 1..4) {
            println(RenderSummary.getSummaryColBorderShape(rowIdx = idx))
        }
    }

    @Test
    fun testNatalSummary() {
        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_CUR_NAV)

        val refNatalChart = ValueChart(StateChart(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), AnalysisState.NO_ANALYSIS)

        val vertShapeMaxIdx = 3

        (0..20).forEach { idx ->
            val summaryDataLine = RenderSummary.prepareSummaryData(idx, refNatalChart, refNatalChart, refNatalChart, ChartState.NATAL_CHART)
            println(summaryDataLine + "|" + summaryDataLine.length )

            when {
                (idx == RenderSummary.summaryHeaderIdx) -> assertEquals(143, summaryDataLine.length)
                (idx == RenderSummary.baseCurChartSummaryIdx) -> assertEquals(91, summaryDataLine.length)
                (idx <= vertShapeMaxIdx) -> assertEquals(33, summaryDataLine.length)
                else -> assertEquals(24, summaryDataLine.length)
            }
        }
    }

    @Test
    fun testCompSummary() {
        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_CUR_NAV)
        val synProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)

        val compChart = ValueChart(StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.COMPOSITE_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), AnalysisState.NO_ANALYSIS)

        val refNatalChart = ValueChart(StateChart(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), AnalysisState.NO_ANALYSIS)

        val synNatalChart = ValueChart(StateChart(synProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), AnalysisState.NO_ANALYSIS)

        (0..20).forEach { idx ->
            val summaryDataLine = RenderSummary.prepareSummaryData(idx, compChart, refNatalChart, synNatalChart, ChartState.COMPOSITE_CHART)
            println(summaryDataLine + "|" + summaryDataLine.length )

            when {
                (idx == RenderSummary.summaryHeaderIdx) -> assertEquals(143, summaryDataLine.length)
                (idx == RenderSummary.baseCurChartSummaryIdx) -> assertEquals(91, summaryDataLine.length)
                (idx == RenderSummary.baseRefNatalChartSummaryIdx) -> assertEquals(91, summaryDataLine.length)
                (idx == RenderSummary.baseSynNatalChartSummaryIdx) -> assertEquals(91, summaryDataLine.length)
                else -> assertEquals(24, summaryDataLine.length)
            }
        }
    }

    @Test
    fun testSynSummary() {
        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_CUR_NAV)
        val synProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)

        val synChart = ValueChart(StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.SYNASTRY_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), AnalysisState.NO_ANALYSIS)

        val refNatalChart = ValueChart(StateChart(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), AnalysisState.NO_ANALYSIS)

        val synNatalChart = ValueChart(StateChart(synProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), AnalysisState.NO_ANALYSIS)

        (0..20).forEach { idx ->
            val summaryDataLine = RenderSummary.prepareSummaryData(idx, synChart, refNatalChart, synNatalChart, ChartState.SYNASTRY_CHART)
            println(summaryDataLine + "|" + summaryDataLine.length )

            when {
                (idx == RenderSummary.summaryHeaderIdx) -> assertEquals(143, summaryDataLine.length)
                (idx == RenderSummary.baseCurChartSummaryIdx) -> assertEquals(91, summaryDataLine.length)
                (idx == RenderSummary.baseRefNatalChartSummaryIdx) -> assertEquals(91, summaryDataLine.length)
                (idx == RenderSummary.baseSynNatalChartSummaryIdx) -> assertEquals(91, summaryDataLine.length)
                else -> assertEquals(24, summaryDataLine.length)
            }
        }
    }
}