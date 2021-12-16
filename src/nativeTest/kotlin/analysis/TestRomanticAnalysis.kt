package analysis

import astro.render.RenderAspect
import astro.render.RenderValue
import astro.state.*
import astro.state.StateBaseAspect.Companion.stateBaseAspects
import astro.value.ValueAspect
import astro.value.ValueAspect.Companion.stateAspectReduceBase
import astro.value.ValueAspect.Companion.valueAspectReduceBase
import astro.value.ValueAspect.Companion.valueAspectReduceBaseModNet
import astro.value.ValueChart
import profile.base.Profiles
import kotlin.test.Test

@ExperimentalUnsignedTypes
class TestRomanticAnalysis {

    val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_7)
    val synProfile = Profiles.getDefaultProfile(Profiles.PROFILE_8)
    val timeAspectsState = TimeAspectsState.TIME_ASPECTS_DISABLED

    @Test
    fun testCompareNatals() {

        val refNatalChart = ValueChart(StateChart(refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), AnalysisState.ROMANTIC_ANALYSIS)

        val synNatalChart = ValueChart(StateChart(synProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), AnalysisState.ROMANTIC_ANALYSIS)

        val natal1Aspects = refNatalChart.getValueAspects()
        val natal2Aspects = synNatalChart.getValueAspects()

//        println("refNatal valueAspects:")
//        natal1Aspects.filter { it.modValue.getNet() != 0 }.forEach { println(it) }
        println("refNatal base: ${RenderValue(natal1Aspects.valueAspectReduceBase()).getLabel()}")
        println("refNatal romantic: ${RenderValue(natal1Aspects.valueAspectReduceBaseModNet()).getLabel()}")

//        println("synNatal valueAspects:")
//        natal2Aspects.filter { it.modValue.getNet() != 0 }.forEach { println(it) }
        println("synNatal base: ${RenderValue(natal2Aspects.valueAspectReduceBase()).getLabel()}")
        println("synNatal romantic: ${RenderValue(natal2Aspects.valueAspectReduceBaseModNet()).getLabel()}")
    }

    @Test
    fun testCompareSynastry() {
        val synChart = ValueChart(StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.SYNASTRY_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT), AnalysisState.ROMANTIC_ANALYSIS)

        val synAspects = synChart.getValueAspects()
        println("synChart base: ${RenderValue(synAspects.valueAspectReduceBase()).getLabel()}")
        println("synChart romantic: ${RenderValue(synAspects.valueAspectReduceBaseModNet()).getLabel()}")

    }

    @Test
    fun testCompareComposite() {
        val compChart = ValueChart(StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.COMPOSITE_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), AnalysisState.ROMANTIC_ANALYSIS)

        val compAspects = compChart.getValueAspects()
        println("compChart base: ${RenderValue(compAspects.valueAspectReduceBase()).getLabel()}")
        println("compChart romantic: ${RenderValue(compAspects.valueAspectReduceBaseModNet()).getLabel()}")
    }
}