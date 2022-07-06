package analysis

import kotlinx.datetime.*
import org.junit.jupiter.api.Test
import org.river.exertion.analysis.AstroAnalysisRow
import org.river.exertion.analysis.AstroDataRow
import org.river.exertion.astro.base.*
import org.river.exertion.astro.state.*
import org.river.exertion.astro.value.Value
import org.river.exertion.astro.value.ValueChart
import org.river.exertion.profile.base.Profile
import org.river.exertion.profile.base.Profiles

@ExperimentalUnsignedTypes
class TestMaxMinFind {

    @Test
    fun testMinMaxAnalysis() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val profileUTC = refProfile.earthLocation.utcDateTime

        //noon UTC with offset for Profile timezone
        val timeZoneOffsetHour = 12 + refProfile.earthLocation.getTimezoneOffsetInt()

        val startUTC = LocalDateTime(
            profileUTC.year
            , profileUTC.monthNumber
            , profileUTC.dayOfMonth
            , timeZoneOffsetHour, 0, 0
        ).toInstant(TimeZone.UTC)

        val duration = DateTimePeriod(days = 1)
        val granularity = DateTimePeriod(minutes = 15)

        val backwardMax = startUTC.minus(duration, TimeZone.UTC)
        val forwardMax = startUTC.plus(duration, TimeZone.UTC)

        var forwardPoll = backwardMax

        val dataRows = mutableListOf<AstroDataRow>()

        while (forwardPoll <= forwardMax) {

            val pollSnapshot = CelestialSnapshot(
                EarthLocation(refProfile.earthLocation.longitude, refProfile.earthLocation.latitude, refProfile.earthLocation.altitude, refProfile.earthLocation.timeZone
                    , forwardPoll.toLocalDateTime(TimeZone.UTC) ) )

            val pollCompareChart = ValueChart(StateChart(refProfile.celestialSnapshot, pollSnapshot, ChartState.SYNASTRY_CHART,
                AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT), AnalysisState.NO_ANALYSIS)

            dataRows.add(AstroDataRow(refProfile.earthLocation.utcDateTime, pollSnapshot.refEarthLocation.utcDateTime, pollSnapshot.getAspectCelestialLongitudeMap(), ChartState.SYNASTRY_CHART, pollCompareChart.getBaseValue()))

            forwardPoll = forwardPoll.plus(granularity, TimeZone.UTC)
        }

        val analysisRow = AstroAnalysisRow(dataRows)

        println ("Final max / min for " + analysisRow.refUTC()!! + " duration: " + duration.toString() + " granularity: " + granularity.toString())
        println ("Poll Range: min " + analysisRow.minPollUTC() + " / max " + analysisRow.maxPollUTC())
        println ("max Stimulation: " + analysisRow.maxStimulation().value.getStimulation() + " @" + analysisRow.maxStimulation().pollUTC.toString())
        println ("min Stimulation: " + analysisRow.minStimulation().value.getStimulation() + " @" + analysisRow.minStimulation().pollUTC.toString())
        println ("avg Stimulation: " + analysisRow.avgStimulation().value.getStimulation() + " @" + analysisRow.avgStimulation().pollUTC.toString())
        println ("max Positive: " + analysisRow.maxPositive().value.positive + " @" + analysisRow.maxPositive().pollUTC.toString())
        println ("min Positive: " + analysisRow.minPositive().value.positive + " @" + analysisRow.minPositive().pollUTC.toString())
        println ("avg Positive: " + analysisRow.avgPositive().value.positive + " @" + analysisRow.avgPositive().pollUTC.toString())
        println ("max Consonance: " + analysisRow.maxConsonance().value.getConsonance() + " @" + analysisRow.maxConsonance().pollUTC.toString())
        println ("min Consonance: " + analysisRow.minConsonance().value.getConsonance() + " @" + analysisRow.minConsonance().pollUTC.toString())
        println ("avg Consonance: " + analysisRow.avgConsonance().value.getConsonance() + " @" + analysisRow.avgConsonance().pollUTC.toString())
        println ("count: ${analysisRow.workingDataRows.size}")
    }

    @Test
    fun testMinMaxAnalysisBy() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val profileUTC = refProfile.earthLocation.utcDateTime

        //noon UTC with offset for Profile timezone
        val timeZoneOffsetHour = 12 + refProfile.earthLocation.getTimezoneOffsetInt()

        val startUTC = LocalDateTime(
            profileUTC.year
            , profileUTC.monthNumber
            , profileUTC.dayOfMonth
            , timeZoneOffsetHour, 0, 0
        ).toInstant(TimeZone.UTC)

        val duration = DateTimePeriod(days = 100)
        val granularity = DateTimePeriod(minutes = 15)

        val backwardMax = startUTC.minus(duration, TimeZone.UTC)
        val forwardMax = startUTC.plus(duration, TimeZone.UTC)

        var forwardPoll = backwardMax

        val dataRows = mutableListOf<AstroDataRow>()

        while (forwardPoll <= forwardMax) {

            val pollSnapshot = CelestialSnapshot(
                EarthLocation(refProfile.earthLocation.longitude, refProfile.earthLocation.latitude, refProfile.earthLocation.altitude, refProfile.earthLocation.timeZone
                    , forwardPoll.toLocalDateTime(TimeZone.UTC) ) )

            val pollCompareChart = ValueChart(StateChart(refProfile.celestialSnapshot, pollSnapshot, ChartState.SYNASTRY_CHART,
                AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT), AnalysisState.NO_ANALYSIS)

            dataRows.add(AstroDataRow(refProfile.earthLocation.utcDateTime, pollSnapshot.refEarthLocation.utcDateTime, pollSnapshot.getAspectCelestialLongitudeMap(), ChartState.SYNASTRY_CHART, pollCompareChart.getBaseValue()))

            forwardPoll = forwardPoll.plus(granularity, TimeZone.UTC)
        }

/*        val analysisRow = AstroAnalysisRow(dataRows).apply {
            this.byAspectCelestialIn(AspectCelestial.ASPECT_SUN, Sign.SCORPIO)
        }.apply {
            this.byAspectCelestialIn(AspectCelestial.ASPECT_MOON, Sign.GEMINI)
        }*/

        val analysisRow = AstroAnalysisRow(dataRows).apply {
            this.byAspectCelestialIn(AspectCelestial.ASPECT_VENUS, Sign.SCORPIO)
        }

        println ("Final max / min for " + analysisRow.refUTC()!! + " duration: " + duration.toString() + " granularity: " + granularity.toString())
        println ("Poll Range: min " + analysisRow.minPollUTC() + " / max " + analysisRow.maxPollUTC())
        println ("max Stimulation: " + analysisRow.maxStimulation().value.getStimulation() + " @" + analysisRow.maxStimulation().pollUTC.toString())
        println ("min Stimulation: " + analysisRow.minStimulation().value.getStimulation() + " @" + analysisRow.minStimulation().pollUTC.toString())
        println ("avg Stimulation: " + analysisRow.avgStimulation().value.getStimulation() + " @" + analysisRow.avgStimulation().pollUTC.toString())
        println ("max Positive: " + analysisRow.maxPositive().value.positive + " @" + analysisRow.maxPositive().pollUTC.toString())
        println ("min Positive: " + analysisRow.minPositive().value.positive + " @" + analysisRow.minPositive().pollUTC.toString())
        println ("avg Positive: " + analysisRow.avgPositive().value.positive + " @" + analysisRow.avgPositive().pollUTC.toString())
        println ("max Consonance: " + analysisRow.maxConsonance().value.getConsonance() + " @" + analysisRow.maxConsonance().pollUTC.toString())
        println ("min Consonance: " + analysisRow.minConsonance().value.getConsonance() + " @" + analysisRow.minConsonance().pollUTC.toString())
        println ("avg Consonance: " + analysisRow.avgConsonance().value.getConsonance() + " @" + analysisRow.avgConsonance().pollUTC.toString())
        println ("count: ${analysisRow.workingDataRows.size}")
    }

    @Test
    fun testMinMaxAnalysisRanking() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val profileUTC = refProfile.earthLocation.utcDateTime

        //noon UTC with offset for Profile timezone
        val timeZoneOffsetHour = 12 + refProfile.earthLocation.getTimezoneOffsetInt()

        val startUTC = LocalDateTime(
            profileUTC.year
            , profileUTC.monthNumber
            , profileUTC.dayOfMonth
            , timeZoneOffsetHour, 0, 0
        ).toInstant(TimeZone.UTC)

        val duration = DateTimePeriod(months = 1)
        val granularity = DateTimePeriod(days = 1)

        val backwardMax = startUTC.minus(duration, TimeZone.UTC)
        val forwardMax = startUTC.plus(duration, TimeZone.UTC)

        var forwardPoll = backwardMax

        val dataRows = mutableListOf<AstroDataRow>()

        while (forwardPoll <= forwardMax) {

            val pollSnapshot = CelestialSnapshot(
                EarthLocation(refProfile.earthLocation.longitude, refProfile.earthLocation.latitude, refProfile.earthLocation.altitude, refProfile.earthLocation.timeZone
                    , forwardPoll.toLocalDateTime(TimeZone.UTC) ) )

            val pollCompareChart = ValueChart(StateChart(refProfile.celestialSnapshot, pollSnapshot, ChartState.SYNASTRY_CHART,
                AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_DISABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT), AnalysisState.NO_ANALYSIS)

            dataRows.add(AstroDataRow(refProfile.earthLocation.utcDateTime, pollSnapshot.refEarthLocation.utcDateTime, pollSnapshot.getAspectCelestialLongitudeMap(), ChartState.SYNASTRY_CHART, pollCompareChart.getBaseValue()))

            forwardPoll = forwardPoll.plus(granularity, TimeZone.UTC)
        }

        val analysisRow = AstroAnalysisRow(dataRows)

        val stimSearch = 788
        val posSearch = 548
        val consSearch = .6954

        println ("Final max / min for " + analysisRow.refUTC()!! + " duration: " + duration.toString() + " granularity: " + granularity.toString())
        println ("Poll Range: min " + analysisRow.minPollUTC() + " / max " + analysisRow.maxPollUTC())
        println ("max Stimulation: " + analysisRow.maxStimulation().value.getStimulation() + " @" + analysisRow.maxStimulation().pollUTC.toString())
        println ("min Stimulation: " + analysisRow.minStimulation().value.getStimulation() + " @" + analysisRow.minStimulation().pollUTC.toString())
        println ("med Stimulation: " + analysisRow.medStimulation().value.getStimulation() + " @" + analysisRow.medStimulation().pollUTC.toString())
        println ("avg Stimulation: " + analysisRow.avgStimulation().value.getStimulation() + " @" + analysisRow.avgStimulation().pollUTC.toString())
        println ("ranking Stimulation: $stimSearch (${analysisRow.rankStimulation(stimSearch)})")
        println ("max Positive: " + analysisRow.maxPositive().value.positive + " @" + analysisRow.maxPositive().pollUTC.toString())
        println ("min Positive: " + analysisRow.minPositive().value.positive + " @" + analysisRow.minPositive().pollUTC.toString())
        println ("med Positive: " + analysisRow.medPositive().value.positive + " @" + analysisRow.medPositive().pollUTC.toString())
        println ("avg Positive: " + analysisRow.avgPositive().value.positive + " @" + analysisRow.avgPositive().pollUTC.toString())
        println ("ranking Positive: $posSearch (${analysisRow.rankPositive(posSearch)})")
        println ("max Consonance: " + analysisRow.maxConsonance().value.getConsonance() + " @" + analysisRow.maxConsonance().pollUTC.toString())
        println ("min Consonance: " + analysisRow.minConsonance().value.getConsonance() + " @" + analysisRow.minConsonance().pollUTC.toString())
        println ("med Consonance: " + analysisRow.medConsonance().value.getConsonance() + " @" + analysisRow.medConsonance().pollUTC.toString())
        println ("avg Consonance: " + analysisRow.avgConsonance().value.getConsonance() + " @" + analysisRow.avgConsonance().pollUTC.toString())
        println ("ranking Consonance: $consSearch (${analysisRow.rankConsonance(consSearch)})")
        println ("count: ${analysisRow.workingDataRows.size}")
    }
}