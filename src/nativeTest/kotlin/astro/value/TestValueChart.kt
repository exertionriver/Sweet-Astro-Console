package astro.value

import astro.base.Sign
import astro.state.*
import console.state.ConsoleAspectsState
import console.state.ConsoleChartState
import console.state.ConsoleTimeAspectsState
import profile.base.Profiles
import kotlin.test.Test

object TestValueChart {

    @Test
    fun testValueChart() {
        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)

        val stateChart = StateChart(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART
            , AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        stateChart.getStateAspects().forEach { println("stateAspect: $it")}

        val valueChart = ValueChart(stateChart, AnalysisState.NO_ANALYSIS)

        valueChart.getValueAspects().forEach { println("valueAspect: $it")}

        println("base: ${valueChart.getBaseValue().positive}, ${valueChart.getBaseValue().negative}, = ${valueChart.getBaseValue().getNet()}")

    }

    @Test
    fun testValueChartAnalysis() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val refCelestialAspectMap = refProfile.celestialSnapshot.getAspectCelestialLongitudeMap()

        refCelestialAspectMap.entries.forEach {
            println("ac:${it.key}, long:${it.value}")
        }

        val stateAspects = StateChart.getAspects(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART
            , AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        var valueChart = ValueChart(stateAspects, ChartState.NATAL_CHART, AnalysisState.NO_ANALYSIS)
        println("NO_ANALYSIS aspects for ${refProfile.profileName}")
        valueChart.getValueAspects().forEach { println(it) }
        println("base:${valueChart.getBaseValue()} mod:${valueChart.getModValue()}")

        valueChart = ValueChart(stateAspects, ChartState.NATAL_CHART, AnalysisState.CHARACTER_ANALYSIS)
        println("CHARACTER_ANALYSIS aspects for ${refProfile.profileName}")
        valueChart.getValueAspects().forEach { println(it) }
        println("base:${valueChart.getBaseValue()} mod:${valueChart.getModValue()}")

        valueChart = ValueChart(stateAspects, ChartState.NATAL_CHART, AnalysisState.ROMANTIC_ANALYSIS)
        println("ROMANTIC_ANALYSIS aspects for ${refProfile.profileName}")
        valueChart.getValueAspects().forEach { println(it) }
        println("base:${valueChart.getBaseValue()} mod:${valueChart.getModValue()}")

        valueChart = ValueChart(stateAspects, ChartState.NATAL_CHART, AnalysisState.PLANET_ANALYSIS)
        println("PLANET_ANALYSIS aspects for ${refProfile.profileName}")
        valueChart.getValueAspects().forEach { println(it) }
        println("base:${valueChart.getBaseValue()} mod:${valueChart.getModValue()}")

        valueChart = ValueChart(stateAspects, ChartState.NATAL_CHART, AnalysisState.ELEMENT_ANALYSIS)
        println("ELEMENT_ANALYSIS aspects for ${refProfile.profileName}")
        valueChart.getValueAspects().forEach { println(it) }
        println("base:${valueChart.getBaseValue()} mod:${valueChart.getModValue()}")

        valueChart = ValueChart(stateAspects, ChartState.NATAL_CHART, AnalysisState.MODE_ANALYSIS)
        println("MODE_ANALYSIS aspects for ${refProfile.profileName}")
        valueChart.getValueAspects().forEach { println(it) }
        println("base:${valueChart.getBaseValue()} mod:${valueChart.getModValue()}")
    }
}