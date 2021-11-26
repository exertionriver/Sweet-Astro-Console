package astro.state

import astro.base.AspectCelestial
import console.state.ConsoleAspectsState
import console.state.ConsoleChartState
import console.state.ConsoleTimeAspectsState
import profile.base.Profiles
import kotlin.test.Test

object TestStateChart {

    @Test
    fun testStateChartGetAspectsChart() {
        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)

        val stateAspects = StateChart.getAspects(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART
            , AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val stateAspectsChart = StateChart.getAspectsChart(stateAspects)

        println("stateAspects for ${refProfile.profileName}:")
        stateAspects.forEach { println(it) }

        println("stateAspectsChart for ${refProfile.profileName}")
        stateAspectsChart.forEach { println(it) }
    }

    @Test
    fun testStateChartGetExtendedAspectsChart() {
        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)

        val stateAspects = StateChart.getAspects(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART
            , AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val stateExtendedAspects = StateChart.getExtendedAspects(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART
            , AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val stateAspectsChart = StateChart(stateAspects.plus(stateExtendedAspects))

        println("stateAspects for ${refProfile.profileName}")
        stateAspectsChart.getStateAspects().forEach { println(it) }
    }

    @Test
    fun testStateChart() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val synProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)

        val natalChart = StateChart(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART
            , AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        println("natal Chart:")
        for (verticalIdx in 0 until AspectCelestial.getChartSize())
            for (horizontalIdx in 0..verticalIdx) {
                println("[$horizontalIdx, $verticalIdx]: ${natalChart.chartRows[horizontalIdx].rowAspects[verticalIdx]}")
            }
        //extended aspects stored in last row
        natalChart.chartRows[AspectCelestial.getChartSize()].rowAspects.forEachIndexed { idx, it -> println( "[$idx, ${AspectCelestial.getChartSize()}]: $it") }

        val compChart = StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.COMPOSITE_CHART
            , AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        println("comp Chart:")
        for (verticalIdx in 0 until AspectCelestial.getChartSize())
            for (horizontalIdx in 0..verticalIdx) {
                println("[$horizontalIdx, $verticalIdx]: ${compChart.chartRows[horizontalIdx].rowAspects[verticalIdx]}")
            }
        //extended aspects stored in last row
        compChart.chartRows[AspectCelestial.getChartSize()].rowAspects.forEachIndexed { idx, it -> println( "[$idx, ${AspectCelestial.getChartSize()}]: $it") }

        val synChart = StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.SYNASTRY_CHART
            , AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT)

        println("syn Chart:")
        for (verticalIdx in 0 until AspectCelestial.getChartSize())
            for (horizontalIdx in 0 until AspectCelestial.getChartSize()) {
                println("[$horizontalIdx, $verticalIdx]: ${synChart.chartRows[horizontalIdx].rowAspects[verticalIdx]}")
            }
        //extended aspects stored in last row
        synChart.chartRows[AspectCelestial.getChartSize()].rowAspects.forEachIndexed { idx, it -> println( "[$idx, ${AspectCelestial.getChartSize()}]: $it") }
    }
}