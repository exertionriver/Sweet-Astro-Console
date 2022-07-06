package astro.value

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.junit.jupiter.api.Test
import org.river.exertion.astro.base.EarthLocation
import org.river.exertion.astro.state.*
import org.river.exertion.astro.value.ValueChart
import org.river.exertion.profile.base.Profile
import org.river.exertion.profile.base.Profiles

@OptIn(ExperimentalUnsignedTypes::class)
object TestValueChart {

    @Test
    fun testValueChart() {
        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)

    //    val synProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)

        //noon UTC with offset for Profile timezone
        val timeZoneOffsetHour = 12 + refProfile.earthLocation.getTimezoneOffsetInt()

        val synUTC = LocalDateTime(
            1966
            , 11
            , 17
            , timeZoneOffsetHour, 0, 0
        ).toInstant(TimeZone.UTC)

        val synProfile = Profile("testProfile"
            , EarthLocation(refProfile.earthLocation.longitude, refProfile.earthLocation.latitude, refProfile.earthLocation.altitude, refProfile.earthLocation.timeZone
                , synUTC.toLocalDateTime(TimeZone.UTC) ) )

        val stateChart = StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.SYNASTRY_CHART
            , AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_DISABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT)

        stateChart.getStateAspects().forEach { println("stateAspect: $it")}

        val valueChart = ValueChart(stateChart, AnalysisState.NO_ANALYSIS)

        valueChart.getValueAspects().forEach { println("valueAspect: $it")}

        println("base: ${valueChart.getBaseValue().positive}, ${valueChart.getBaseValue().negative}, = ${valueChart.getBaseValue().getNet()}, ${valueChart.getBaseValue().getConsonance()}, ${valueChart.getBaseValue().getStimulation()}")

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