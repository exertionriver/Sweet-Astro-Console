package astro.render

import org.river.exertion.astro.base.Sign
import org.river.exertion.astro.render.RenderAspect
import org.river.exertion.astro.state.*
import org.river.exertion.astro.value.ValueAspect
import org.river.exertion.astro.value.ValueChart
import org.river.exertion.profile.base.Profiles
import org.junit.jupiter.api.Test

@OptIn(ExperimentalUnsignedTypes::class)
class TestRenderAspect {

    @Test
    fun testRenderAspect() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_CUR_NAV)
        val refCelestialAspectMap = refProfile.celestialSnapshot.getAspectCelestialLongitudeMap()

        refCelestialAspectMap.entries.forEach {
            println("ac:${it.key}, long:${it.value}")
        }

        val renderAspect = RenderAspect(
            ValueAspect(
                StateAspect(
                    Sign.getSignFromCelestialLongitude(refCelestialAspectMap.entries.first().value)
                , refCelestialAspectMap.entries.first().key
                , refCelestialAspectMap.entries.first().value
                , Sign.getSignFromCelestialLongitude(refCelestialAspectMap.entries.last().value)
                , refCelestialAspectMap.entries.last().key
                , refCelestialAspectMap.entries.last().value
                , AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)
            )
        )
        println(renderAspect.getRenderLabel())
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testValueChartRenderAspects() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_CUR_NAV)
        val refCelestialAspectMap = refProfile.celestialSnapshot.getAspectCelestialLongitudeMap()

        refCelestialAspectMap.entries.forEach {
            println("ac:${it.key}, long:${it.value}")
        }

        val valueAspects = ValueChart(
            StateChart.getAspects(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART
            , AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), ChartState.NATAL_CHART).getValueAspects()

        println("aspects for ${refProfile.profileName}")
        valueAspects.forEach { println(RenderAspect(it).getRenderLabel() ) }
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testRenderAspectsAnalysis() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val refCelestialAspectMap = refProfile.celestialSnapshot.getAspectCelestialLongitudeMap()

        refCelestialAspectMap.entries.forEach {
            println("ac:${it.key}, long:${it.value}")
        }

        val stateAspects = StateChart.getAspects(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART
            , AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        var valueAspects = ValueChart(stateAspects, ChartState.NATAL_CHART, AnalysisState.NO_ANALYSIS).getValueAspects()

        println("NO_ANALYSIS aspects for ${refProfile.profileName}")
        valueAspects.forEach { println(it) }

        valueAspects = ValueChart(stateAspects, ChartState.NATAL_CHART, AnalysisState.CHARACTER_ANALYSIS).getValueAspects()

        println("CHARACTER_ANALYSIS aspects for ${refProfile.profileName}")
        valueAspects.forEach { println(it) }

        valueAspects = ValueChart(stateAspects, ChartState.NATAL_CHART, AnalysisState.ROMANTIC_ANALYSIS).getValueAspects()

        println("ROMANTIC_ANALYSIS aspects for ${refProfile.profileName}")
        valueAspects.forEach { println(it) }

        valueAspects = ValueChart(stateAspects, ChartState.NATAL_CHART, AnalysisState.PLANET_ANALYSIS).getValueAspects()

        println("PLANET_ANALYSIS aspects for ${refProfile.profileName}")
        valueAspects.forEach { println(it) }

        valueAspects = ValueChart(stateAspects, ChartState.NATAL_CHART, AnalysisState.ELEMENT_ANALYSIS).getValueAspects()

        println("ELEMENT_ANALYSIS aspects for ${refProfile.profileName}")
        valueAspects.forEach { println(it) }

        valueAspects = ValueChart(stateAspects, ChartState.NATAL_CHART, AnalysisState.MODE_ANALYSIS).getValueAspects()

        println("MODE_ANALYSIS aspects for ${refProfile.profileName}")
        valueAspects.forEach { println(it) }
    }
}