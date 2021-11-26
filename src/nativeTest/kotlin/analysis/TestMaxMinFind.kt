package analysis

import Constants
import astro.base.*
import console.*
import kotlinx.datetime.*
import profile.base.Profile
import profile.base.Profiles
import astro.value.ValueAspect
import console.base.*
import kotlin.math.abs
import kotlin.test.Test

@ExperimentalUnsignedTypes
class TestMaxMinFind {
/*
    val profile1MaxTheoretical =  mapOf(
        Celestial.SUN.ordinal to 197
        , Celestial.MOON.ordinal to 197
        , Celestial.MERCURY.ordinal to 197
        , Celestial.VENUS.ordinal to 197
        , Celestial.MARS.ordinal to 197
        , Celestial.JUPITER.ordinal to 197
        , Celestial.SATURN.ordinal to 197
        , Celestial.URANUS.ordinal to 197
        , Celestial.NEPTUNE.ordinal to 197
        , Celestial.PLUTO.ordinal to 197
        , Celestial.NORTH_NODE.ordinal to 196
        , Celestial.BLACK_MOON_LILITH.ordinal to 196
        , Celestial.CHIRON.ordinal to 197)

    val profile1MaxAscOffset = 2

    val profile1MinTheoretical =  mapOf(
        Celestial.SUN.ordinal to 347
        , Celestial.MOON.ordinal to 165
        , Celestial.MERCURY.ordinal to 347
        , Celestial.VENUS.ordinal to 347
        , Celestial.MARS.ordinal to 165
        , Celestial.JUPITER.ordinal to 347
        , Celestial.SATURN.ordinal to 347
        , Celestial.URANUS.ordinal to 347
        , Celestial.NEPTUNE.ordinal to 346
        , Celestial.PLUTO.ordinal to 347
        , Celestial.NORTH_NODE.ordinal to 167
        , Celestial.BLACK_MOON_LILITH.ordinal to 167
        , Celestial.CHIRON.ordinal to 347)

    val profile1MinAscOffset = 189

    val profile2MaxTheoretical =  mapOf(
        Celestial.SUN.ordinal to 114
        , Celestial.MOON.ordinal to 114
        , Celestial.MERCURY.ordinal to 114
        , Celestial.VENUS.ordinal to 114
        , Celestial.MARS.ordinal to 114
        , Celestial.JUPITER.ordinal to 114
        , Celestial.SATURN.ordinal to 114
        , Celestial.URANUS.ordinal to 114
        , Celestial.NEPTUNE.ordinal to 114
        , Celestial.PLUTO.ordinal to 114
        , Celestial.NORTH_NODE.ordinal to 113
        , Celestial.BLACK_MOON_LILITH.ordinal to 113
        , Celestial.CHIRON.ordinal to 114)

    val profile2MaxAscOffset = 36

    val profile2MinTheoretical =  mapOf(
        Celestial.SUN.ordinal to 261
        , Celestial.MOON.ordinal to 211
        , Celestial.MERCURY.ordinal to 261
        , Celestial.VENUS.ordinal to 261
        , Celestial.MARS.ordinal to 350
        , Celestial.JUPITER.ordinal to 261
        , Celestial.SATURN.ordinal to 350
        , Celestial.URANUS.ordinal to 261
        , Celestial.NEPTUNE.ordinal to 260
        , Celestial.PLUTO.ordinal to 260
        , Celestial.NORTH_NODE.ordinal to 261
        , Celestial.BLACK_MOON_LILITH.ordinal to 261
        , Celestial.CHIRON.ordinal to 260)

    val profile2MinAscOffset = 91



    @Test
    fun minMaxActual() {

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

        val duration = DateTimePeriod(years = 12)
        val granularity = DateTimePeriod(days = 1)

        val backwardMax = startUTC.minus(duration, TimeZone.UTC)
        val forwardMax = startUTC.plus(duration, TimeZone.UTC)

        var forwardPoll = backwardMax

        var maxStimulation = 0
        var maxStimulationTime = startUTC
        var minStimulation = 4000
        var minStimulationTime = startUTC

        var maxPositive = 0
        var maxPositiveTime = startUTC
        var minPositive = 4000
        var minPositiveTime = startUTC

        var maxConsonance = 0
        var maxConsonanceTime = startUTC
        var minConsonance = 100
        var minConsonanceTime = startUTC

        while (forwardPoll < forwardMax) {

            val pollSnapshot = CelestialSnapshot(
                EarthLocation(refProfile.earthLocation.longitude, refProfile.earthLocation.latitude, refProfile.earthLocation.altitude, refProfile.earthLocation.timeZone
                    , forwardPoll.toLocalDateTime(TimeZone.UTC) ) )

            val pollChart =
                Chart(ChartState.COMPOSITE_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_DISABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, refProfile.celestialSnapshot, pollSnapshot)

            if (pollChart.getChartValue().stimulation > maxStimulation) {
                maxStimulation = pollChart.getChartValue().stimulation
                maxStimulationTime = forwardPoll
                println ("new max Stimulation: " + maxStimulation + " @" + maxStimulationTime.toLocalDateTime(TimeZone.UTC).toString())
            }
            if (pollChart.getChartValue().stimulation < minStimulation) {
                minStimulation = pollChart.getChartValue().stimulation
                minStimulationTime = forwardPoll
                println ("new min Stimulation: " + minStimulation + " @" + minStimulationTime.toLocalDateTime(TimeZone.UTC).toString())
            }

            if (pollChart.getChartValue().positive > maxPositive) {
                maxPositive = pollChart.getChartValue().positive
                maxPositiveTime = forwardPoll
                println ("new max Positive: " + maxPositive + " @" + maxPositiveTime.toLocalDateTime(TimeZone.UTC).toString())
            }
            if (pollChart.getChartValue().positive < minPositive) {
                minPositive = pollChart.getChartValue().positive
                minPositiveTime = forwardPoll
                println ("new min Positive: " + minPositive + " @" + minPositiveTime.toLocalDateTime(TimeZone.UTC).toString())
            }

            if (pollChart.getChartValue().consonance > maxConsonance) {
                maxConsonance = pollChart.getChartValue().consonance
                maxConsonanceTime = forwardPoll
                println ("new max Consonance: " + maxConsonance + " @" + maxConsonanceTime.toLocalDateTime(TimeZone.UTC).toString())
            }
            if (pollChart.getChartValue().consonance < minConsonance) {
                minConsonance = pollChart.getChartValue().consonance
                minConsonanceTime = forwardPoll
                println ("new min Consonance: " + minConsonance + " @" + minConsonanceTime.toLocalDateTime(TimeZone.UTC).toString())
            }

            forwardPoll = forwardPoll.plus(granularity, TimeZone.UTC)
        }

        println ("Final max / min for " + startUTC.toLocalDateTime(TimeZone.UTC).toString() + " duration: " + duration.toString() + " granularity: " + granularity.toString())
        println ("max Stimulation: " + maxStimulation + " @" + maxStimulationTime.toLocalDateTime(TimeZone.UTC).toString())
        println ("min Stimulation: " + minStimulation + " @" + minStimulationTime.toLocalDateTime(TimeZone.UTC).toString())
        println ("max Positive: " + maxPositive + " @" + maxPositiveTime.toLocalDateTime(TimeZone.UTC).toString())
        println ("min Positive: " + minPositive + " @" + minPositiveTime.toLocalDateTime(TimeZone.UTC).toString())
        println ("max Consonance: " + maxConsonance + " @" + maxConsonanceTime.toLocalDateTime(TimeZone.UTC).toString())
        println ("min Consonance: " + minConsonance + " @" + minConsonanceTime.toLocalDateTime(TimeZone.UTC).toString())

    }

    @Test
    fun minMaxActualRanking() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)

        val profileUTC = refProfile.earthLocation.utcDateTime

        val startUTC = LocalDateTime(profileUTC.year,profileUTC.monthNumber, profileUTC.dayOfMonth
            , 0, 0, 0).toInstant(TimeZone.UTC)

        val duration = DateTimePeriod(years = 1)
        val timeInvariant = startUTC.plus(duration, TimeZone.UTC)

//        val granularity = DateTimeUnit.HOUR
        val granularity = DateTimePeriod(days = 1)

        var forwardPoll = startUTC
//        var backwardPoll = startUTC.minus(granularity)
        var backwardPoll = startUTC.minus(granularity, TimeZone.UTC)

        var maxConsonance = 0
        var maxConsonanceTime = startUTC
        var minConsonance = 4000
        var minConsonanceTime = startUTC

        var maxDissonance = 0
        var maxDissonanceTime = startUTC
        var minDissonance = -4000
        var minDissonanceTime = startUTC

        val unsortedMap : MutableMap<Instant, ValueAspect> = HashMap()

        while(forwardPoll < timeInvariant) {
            val forwardProfile = Profile("posProfile"
                , EarthLocation(refProfile.earthLocation.longitude, refProfile.earthLocation.latitude, refProfile.earthLocation.altitude, refProfile.earthLocation.timeZone
                    , forwardPoll.toLocalDateTime(TimeZone.UTC) ) )

            val backwardProfile = Profile("negProfile"
                , EarthLocation(refProfile.earthLocation.longitude, refProfile.earthLocation.latitude, refProfile.earthLocation.altitude, refProfile.earthLocation.timeZone
                    , backwardPoll.toLocalDateTime(TimeZone.UTC) ) )

            val forwardChart =
                Chart(ChartState.SYNASTRY_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_DISABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, refProfile.celestialSnapshot, forwardProfile.celestialSnapshot)

            val backwardChart =
                Chart(ChartState.SYNASTRY_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_DISABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, refProfile.celestialSnapshot, backwardProfile.celestialSnapshot)

            unsortedMap[forwardPoll] = ValueAspect(forwardChart.getChartValue().positive, forwardChart.getChartValue().negative)
            unsortedMap[backwardPoll] = ValueAspect(backwardChart.getChartValue().positive, backwardChart.getChartValue().negative)


/*
            if (forwardChart.getChartValue().positive > maxConsonance) {
                maxConsonance = forwardChart.getChartValue().positive
                maxConsonanceTime = forwardPoll
                println ("new max Consonance: " + maxConsonance + " @" + maxConsonanceTime.toLocalDateTime(TimeZone.UTC).toString())
            }
            if (backwardChart.getChartValue().positive > maxConsonance) {
                maxConsonance = backwardChart.getChartValue().positive
                maxConsonanceTime = backwardPoll
                println ("new max Consonance: " + maxConsonance + " @" + maxConsonanceTime.toLocalDateTime(TimeZone.UTC).toString())
            }
            if (forwardChart.getChartValue().positive < minConsonance) {
                minConsonance = forwardChart.getChartValue().positive
                minConsonanceTime = forwardPoll
                println ("new min Consonance: " + minConsonance + " @" + minConsonanceTime.toLocalDateTime(TimeZone.UTC).toString())
            }
            if (backwardChart.getChartValue().positive < minConsonance) {
                minConsonance = backwardChart.getChartValue().positive
                minConsonanceTime = backwardPoll
                println ("new min Consonance: " + minConsonance + " @" + minConsonanceTime.toLocalDateTime(TimeZone.UTC).toString())
            }

            if (forwardChart.getChartValue().negative < maxDissonance) {
                maxDissonance = forwardChart.getChartValue().negative
                maxDissonanceTime = forwardPoll
                println ("new max Dissonance: " + maxDissonance + " @" + maxDissonanceTime.toLocalDateTime(TimeZone.UTC).toString())
            }
            if (backwardChart.getChartValue().negative < maxDissonance) {
                maxDissonance = backwardChart.getChartValue().negative
                maxDissonanceTime = backwardPoll
                println ("new max Dissonance: " + maxDissonance + " @" + maxDissonanceTime.toLocalDateTime(TimeZone.UTC).toString())
            }
            if (forwardChart.getChartValue().negative > minDissonance) {
                minDissonance = forwardChart.getChartValue().negative
                minDissonanceTime = forwardPoll
                println ("new min Dissonance: " + minDissonance + " @" + minDissonanceTime.toLocalDateTime(TimeZone.UTC).toString())
            }
            if (backwardChart.getChartValue().negative > minDissonance) {
                minDissonance = backwardChart.getChartValue().negative
                minDissonanceTime = backwardPoll
                println ("new min Dissonance: " + minDissonance + " @" + minDissonanceTime.toLocalDateTime(TimeZone.UTC).toString())
            }
*/
//            forwardPoll = forwardPoll.plus(granularity)
//            backwardPoll = backwardPoll.minus(granularity)
            forwardPoll = forwardPoll.plus(granularity, TimeZone.UTC)
            backwardPoll = backwardPoll.minus(granularity, TimeZone.UTC)
        }

        val consonanceMap : MutableMap<Instant, ValueAspect> = LinkedHashMap()
        val dissonanceMap : MutableMap<Instant, ValueAspect> = LinkedHashMap()

        unsortedMap.entries.sortedBy { it.value.positive }.forEach { consonanceMap[it.key] = it.value }
        unsortedMap.entries.sortedBy { abs(it.value.negative) }.forEach { dissonanceMap[it.key] = it.value }

//        val consSearch = 1104 //ip and suz
//        val dissSearch = -483
//        val consSearch = 729 //gdp and vjp
//        val dissSearch = -267
//        val consSearch = 408 //bl and jl
//        val dissSearch = -795
//        val consSearch = 817 //map and au
//        val dissSearch = -664
//        val consSearch = 1060 //sep and jb
//        val dissSearch = -1287
        val consSearch = 711 //cl and qs
        val dissSearch = -657

        val consFirstValue = consonanceMap.values.first { it.positive >= consSearch }
        val consIndex = consonanceMap.values.indexOf(consFirstValue)

        println("index: " + consIndex.toString() +
                "of : " + consonanceMap.values.size + "(" + 100 * consIndex / (consonanceMap.values.size - 1) + ")" +
                " = (" + consonanceMap.values.elementAt(consIndex).positive + ", "
                + consonanceMap.values.elementAt(consIndex).negative)

        if ((consIndex + 1) < consonanceMap.values.size) {
            println(
                "index: " + (consIndex + 1).toString() +
                        "of : " + consonanceMap.values.size + "(" + 100 * (consIndex + 1) / (consonanceMap.values.size - 1) + ")" +
                        " = (" + consonanceMap.values.elementAt(consIndex + 1).positive + ", "
                        + consonanceMap.values.elementAt(consIndex + 1).negative
            )
        } else println ("at max")

        if (consIndex > 0) {
            println("index: " + (consIndex - 1).toString() +
                "of : " + consonanceMap.values.size + "(" + 100 * (consIndex - 1) / (consonanceMap.values.size - 1) + ")" +
                " = (" + consonanceMap.values.elementAt(consIndex - 1).positive + ", "
                + consonanceMap.values.elementAt(consIndex - 1).negative)
        } else println ("at min")

        val dissFirstValue = dissonanceMap.values.first { abs(it.negative) >= abs(dissSearch) }
        val dissIndex = dissonanceMap.values.indexOf(dissFirstValue)

        println("index: " + dissIndex.toString() +
                "of : " + dissonanceMap.values.size + "(" + (100 - (100 * dissIndex / (dissonanceMap.values.size - 1))) + ")" +
                " = (" + dissonanceMap.values.elementAt(dissIndex).positive + ", "
                + dissonanceMap.values.elementAt(dissIndex).negative)

        if ((dissIndex + 1) < dissonanceMap.values.size) {
            println(
                "index: " + (dissIndex + 1).toString() +
                        "of : " + dissonanceMap.values.size + "(" + (100 - (100 * (dissIndex + 1) / (dissonanceMap.values.size - 1))) + ")" +
                        " = (" + dissonanceMap.values.elementAt(dissIndex + 1).positive + ", "
                        + dissonanceMap.values.elementAt(dissIndex + 1).negative
            )
        } else println ("at max")

        if (dissIndex > 0) {
            println("index: " + (dissIndex - 1).toString() +
                    "of : " + dissonanceMap.values.size + "(" + (100 - (100 * (dissIndex - 1) / (dissonanceMap.values.size - 1))) + ")" +
                    " = (" + dissonanceMap.values.elementAt(dissIndex - 1).positive + ", "
                    + dissonanceMap.values.elementAt(dissIndex - 1).negative)
        } else println ("at min")

/*      //by date
        val searchIdx : Instant = LocalDateTime(1981, 7,21,0, 0,0,0).toInstant(TimeZone.UTC)
        println("index: " + searchIdx.toLocalDateTime(TimeZone.UTC).toString() + " : "
                + consonanceMap.keys.indexOf(searchIdx) +
                "of : " + consonanceMap.keys.size +
                " = (" + consonanceMap[searchIdx]!!.positive + ", " + consonanceMap[searchIdx]!!.negative)
*/

//        println ("Final max / min for " + startUTC.toLocalDateTime(TimeZone.UTC).toString() + " duration: " + duration.toString() + " granularity: " + granularity.toString())
//        println ("max Consonance: " + maxConsonance + " @" + maxConsonanceTime.toLocalDateTime(TimeZone.UTC).toString())
//        println ("min Consonance: " + minConsonance + " @" + minConsonanceTime.toLocalDateTime(TimeZone.UTC).toString())
//        println ("max Dissonance: " + maxDissonance + " @" + maxDissonanceTime.toLocalDateTime(TimeZone.UTC).toString())
//        println ("min Dissonance: " + minDissonance + " @" + minDissonanceTime.toLocalDateTime(TimeZone.UTC).toString())

    }


    @Test
    fun minMaxTheoretical() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)

        for (celestial in Celestial.values()) {
            celestialMinMax(refProfile, celestial)
        }
    }

    @Test
    fun minMaxHouseTheoretical() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)

        celestialHouseMinMax(refProfile)
    }

    @Test
    fun maxTheoreticalChart() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)
        val modMap = profile2MaxTheoretical
        val modAsc = profile2MaxAscOffset

        val modSnapshot = CelestialSnapshot(refProfile.earthLocation, refProfile.earthLocation
            , CelestialHouse.getCelestialHousesDataOverride(refProfile.celestialSnapshot.celestialHouseData, modAsc)
            , CelestialHouse.getCelestialHousesDataOverride(refProfile.celestialSnapshot.celestialHouseData, modAsc)
            , CelestialData.getCelestialsDataOverride(refProfile.celestialSnapshot.celestialData, modMap) )

        val modChart =
            Chart(ChartState.SYNASTRY_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, refProfile.celestialSnapshot, modSnapshot)

        println(RenderAspect.getLabel(modChart.getChartValue(), AspectValue.POSITIVE_VALUE) + "." + RenderAspect.getLabel(modChart.getChartValue(), AspectValue.NEGATIVE_VALUE))

        for (aspect in modChart.aspects) {
            println (RenderAspect.getLabel(aspect))
        }

    }

    @Test
    fun minTheoreticalChart() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val modMap = profile1MinTheoretical
        val modAsc = profile1MinAscOffset

        val modSnapshot = CelestialSnapshot(refProfile.earthLocation, refProfile.earthLocation
            , CelestialHouse.getCelestialHousesDataOverride(refProfile.celestialSnapshot.celestialHouseData, modAsc)
            , CelestialHouse.getCelestialHousesDataOverride(refProfile.celestialSnapshot.celestialHouseData, modAsc)
            , CelestialData.getCelestialsDataOverride(refProfile.celestialSnapshot.celestialData, modMap) )

        val modChart =
            Chart(ChartState.SYNASTRY_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, refProfile.celestialSnapshot, modSnapshot)

        println(RenderAspect.getLabel(modChart.getChartValue(), AspectValue.POSITIVE_VALUE) + "." + RenderAspect.getLabel(modChart.getChartValue(), AspectValue.NEGATIVE_VALUE))

        for (aspect in modChart.aspects) {
            println (RenderAspect.getLabel(aspect))
        }

    }

    private fun celestialMinMax(refProfile : Profile, modCelestial : Celestial) {

        var maxChart = 0
        var minChart = 2000
        var minChartPos = 0
        var maxChartPos = 0

        for (modPosition in 0..360) {
            val modMap = mapOf(modCelestial.ordinal to modPosition)

            val modSnapshot = CelestialSnapshot(refProfile.earthLocation, refProfile.earthLocation
                , CelestialHouse.getCelestialHousesDataOverride(refProfile.celestialSnapshot.celestialHouseData)
                , CelestialHouse.getCelestialHousesDataOverride(refProfile.celestialSnapshot.celestialHouseData)
                , CelestialData.getCelestialsDataOverride(refProfile.celestialSnapshot.celestialData, modMap) )

            val modChart =
                Chart(ChartState.SYNASTRY_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, refProfile.celestialSnapshot, modSnapshot)

//            println(modCelestial.getLabel() + " " + modPosition + " " + Signs.getSignFromCelesitalLongitude(modPosition.toDouble()) + ": " +
//                    modChart.getChartValue().getLabel(AspectValue.NET_VALUE))

            if (modChart.getChartValue().getNet() < minChart) {
                minChartPos = modPosition
                minChart = modChart.getChartValue().getNet()
            }
            if (modChart.getChartValue().getNet() > maxChart) {
                maxChartPos = modPosition
                maxChart = modChart.getChartValue().getNet()
            }

        }

        println("min("+modCelestial.getLabel() + "): " + minChartPos + " " + Sign.getSignFromCelestialLongitude(minChartPos.toDouble()) + ": " + minChart)
        println("max("+modCelestial.getLabel() + "): " + maxChartPos + " " + Sign.getSignFromCelestialLongitude(maxChartPos.toDouble()) + ": " + maxChart)
    }

    private fun celestialHouseMinMax(refProfile : Profile) {

        var maxChart = 0
        var minChart = 2000
        var minChartPos = 0
        var maxChartPos = 0
        var minModPos = 0
        var maxModPos = 0

        for (modPosition in 0..360) {
            val modSnapshot = CelestialSnapshot(refProfile.earthLocation, refProfile.earthLocation
                , CelestialHouse.getCelestialHousesDataOverride(refProfile.celestialSnapshot.celestialHouseData, modPosition)
                , CelestialHouse.getCelestialHousesDataOverride(refProfile.celestialSnapshot.celestialHouseData, modPosition)
                , CelestialData.getCelestialsDataOverride(refProfile.celestialSnapshot.celestialData) )

            val modChart =
                Chart(ChartState.SYNASTRY_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, refProfile.celestialSnapshot, modSnapshot)

            val ascPosition = DegNorm.normalize(refProfile.celestialSnapshot.celestialHouseData[CelestialHouse.HOUSE_1_ASC.ordinal] + modPosition)

//            println("offset: " + modPosition.toString() + " " + ascPosition + " " + Signs.getSignFromCelesitalLongitude(ascPosition) + ": " +
//                    modChart.getChartValue().getLabel(AspectValue.NET_VALUE))

            if (modChart.getChartValue().getNet() < minChart) {
                minChartPos = ascPosition.toInt()
                minChart = modChart.getChartValue().getNet()
                minModPos = modPosition
            }
            if (modChart.getChartValue().getNet() > maxChart) {
                maxChartPos = ascPosition.toInt()
                maxChart = modChart.getChartValue().getNet()
                maxModPos = modPosition
            }

        }

        println("min("+ Constants.SYM_HOUSE + "+): " + minChartPos + "@ " + minModPos + " " + Sign.getSignFromCelestialLongitude(minChartPos.toDouble()) + ": " + minChart)
        println("max("+ Constants.SYM_HOUSE + "+): " + maxChartPos + "@ " + maxModPos + " " + Sign.getSignFromCelestialLongitude(maxChartPos.toDouble()) + ": " + maxChart)
    }*/
}