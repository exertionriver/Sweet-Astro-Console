package astro.state

import org.junit.jupiter.api.Test
import org.river.exertion.astro.base.Sign
import org.river.exertion.astro.state.*
import org.river.exertion.profile.base.Profiles

object TestStateAspect {

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testStateAspect() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val refCelestialAspectMap = refProfile.celestialSnapshot.getAspectCelestialLongitudeMap()

        refCelestialAspectMap.entries.forEach {
            println("ac:${it.key}, long:${it.value}")
        }

        val stateAspect = StateAspect(
            Sign.getSignFromCelestialLongitude(refCelestialAspectMap.entries.first().value)
            , refCelestialAspectMap.entries.first().key
            , refCelestialAspectMap.entries.first().value
            , Sign.getSignFromCelestialLongitude(refCelestialAspectMap.entries.last().value)
            , refCelestialAspectMap.entries.last().key
            , refCelestialAspectMap.entries.last().value
            , AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        println(stateAspect)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testStateChartGetAspects() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val refCelestialAspectMap = refProfile.celestialSnapshot.getAspectCelestialLongitudeMap()

        refCelestialAspectMap.entries.forEach {
            println("ac:${it.key}, long:${it.value}")
        }

        val stateAspects = StateChart.getAspects(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART
            , AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        println("aspects for ${refProfile.profileName}")
        stateAspects.forEach { println(it) }
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testStateChartGetExtendedAspects() {
        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)

        val stateExtendedAspects = StateChart.getExtendedAspects(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART
            , AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT)

        println("extendedAspects for ${refProfile.profileName}:")
        stateExtendedAspects.forEach { println(it) }
    }

}