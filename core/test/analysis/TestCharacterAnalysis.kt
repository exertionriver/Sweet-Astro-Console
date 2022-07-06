package analysis

import org.river.exertion.astro.render.RenderAspect
import org.river.exertion.astro.render.RenderValue
import org.river.exertion.astro.state.*
import org.river.exertion.astro.state.StateAspect.Companion.stateAspectReduceBase
import org.river.exertion.astro.state.StateBaseAspect.Companion.stateBaseAspects
import org.river.exertion.astro.value.ValueAspect
import org.river.exertion.astro.value.ValueChart
import org.river.exertion.profile.base.Profiles
import org.junit.jupiter.api.Test

@ExperimentalUnsignedTypes
class TestCharacterAnalysis {

    val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
    val synProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)
    val timeAspectsState = TimeAspectsState.TIME_ASPECTS_DISABLED

    @Test
    fun testCompareNatals() {
        val refNatalChart = StateChart(refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val synNatalChart = StateChart(synProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val refNatalAspects = refNatalChart.getStateAspects().stateBaseAspects()
        val synNatalAspects = synNatalChart.getStateAspects().stateBaseAspects()

        val sharedNatalAspects = synNatalChart.getStateAspects().filter { refNatalAspects.contains(it.getStateBaseAspect()) }.toMutableList()
        sharedNatalAspects.addAll(refNatalChart.getStateAspects().filter { synNatalAspects.contains(it.getStateBaseAspect()) })

        println("natal1 shared with natal2")
        sharedNatalAspects.forEach { println(RenderAspect(ValueAspect(it)).getRenderLabel()) }

        println("appreciation: ${RenderValue(sharedNatalAspects.stateAspectReduceBase()).getLabel()}")
    }

    @Test
    fun testCompareSynastry() {
        val synChart = StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.SYNASTRY_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT)

        val refNatalChart = StateChart(refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val synNatalChart = StateChart(synProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val synAspects = synChart.getStateAspects().stateBaseAspects()
        val refNatalAspects = refNatalChart.getStateAspects().stateBaseAspects()
        val synNatalAspects = synNatalChart.getStateAspects().stateBaseAspects()

        val sharedNatal1Aspects = refNatalChart.getStateAspects().filter { synAspects.contains(it.getStateBaseAspect()) }.toMutableList()
        sharedNatal1Aspects.addAll(synChart.getStateAspects().filter { refNatalAspects.contains(it.getStateBaseAspect()) })

        val sharedNatal2Aspects = synNatalChart.getStateAspects().filter { synAspects.contains(it.getStateBaseAspect()) }.toMutableList()
        sharedNatal2Aspects.addAll(synChart.getStateAspects().filter { synNatalAspects.contains(it.getStateBaseAspect()) })

        println("natal1 shared with synastry chart")
        sharedNatal1Aspects.forEach { println(RenderAspect(ValueAspect(it)).getRenderLabel()) }

        println("natal2 shared with synastry chart")
        sharedNatal2Aspects.forEach { println(RenderAspect(ValueAspect(it)).getRenderLabel()) }

        println("affinity: ${RenderValue(sharedNatal1Aspects.plus(sharedNatal2Aspects).stateAspectReduceBase()).getLabel()}")

    }

    @Test
    fun testCompareComposite() {
        val compChart = StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.COMPOSITE_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val refNatalChart = StateChart(refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val synNatalChart = StateChart(synProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val compAspects = compChart.getStateAspects().stateBaseAspects()
        val refNatalAspects = refNatalChart.getStateAspects().stateBaseAspects()
        val synNatalAspects = synNatalChart.getStateAspects().stateBaseAspects()

        val sharedNatal1Aspects = refNatalChart.getStateAspects().filter { compAspects.contains(it.getStateBaseAspect()) }.toMutableList()
        sharedNatal1Aspects.addAll(compChart.getStateAspects().filter { refNatalAspects.contains(it.getStateBaseAspect()) })

        val sharedNatal2Aspects = synNatalChart.getStateAspects().filter { compAspects.contains(it.getStateBaseAspect()) }.toMutableList()
        sharedNatal2Aspects.addAll(compChart.getStateAspects().filter { synNatalAspects.contains(it.getStateBaseAspect()) })

        println("natal1 shared with composite chart")
        sharedNatal1Aspects.forEach { println(RenderAspect(ValueAspect(it)).getRenderLabel()) }

        println("natal2 shared with composite chart")
        sharedNatal2Aspects.forEach { println(RenderAspect(ValueAspect(it)).getRenderLabel()) }

        println("commonality: ${RenderValue(sharedNatal1Aspects.plus(sharedNatal2Aspects).stateAspectReduceBase()).getLabel()}")
    }

    @Test
    fun testCompareSynastryComposite() {
        val synChart = StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.SYNASTRY_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT)

        val compChart = StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.COMPOSITE_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val compAspects = compChart.getStateAspects().stateBaseAspects()
        val synAspects = synChart.getStateAspects().stateBaseAspects()

        val sharedAspects = synChart.getStateAspects().filter { compAspects.contains(it.getStateBaseAspect()) }.toMutableList()
        sharedAspects.addAll(compChart.getStateAspects().filter { synAspects.contains(it.getStateBaseAspect()) })

        println("shared between synastry and composite chart")
        sharedAspects.forEach { println(RenderAspect(ValueAspect(it)).getRenderLabel()) }

        println("compatibility: ${RenderValue(sharedAspects.stateAspectReduceBase()).getLabel()}")

    }

    @Test
    fun testCompareSynastryValueChart() {
        val refNatalChart = StateChart(refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val synNatalChart = StateChart(synProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val synChart = StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.SYNASTRY_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT)

        val compChart = StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.COMPOSITE_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val valueChart = ValueChart(synChart.chartState, synChart, compChart, refNatalChart, synNatalChart)

        println("character analysis synastry value chart")
        valueChart.getValueAspects().forEach { println(RenderAspect(it).getRenderLabel() + ":" + RenderAspect(it).getRenderCharacterModLabel()) }
    }

    @Test
    fun testCompareCompositeValueChart() {
        val refNatalChart = StateChart(refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val synNatalChart = StateChart(synProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val synChart = StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.SYNASTRY_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT)

        val compChart = StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.COMPOSITE_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        val valueChart = ValueChart(compChart.chartState, synChart, compChart, refNatalChart, synNatalChart)

        println("character analysis composite value chart")
        valueChart.getValueAspects().forEach { println(RenderAspect(it).getRenderLabel() + ":" + RenderAspect(it).getRenderCharacterModLabel()) }
    }

}