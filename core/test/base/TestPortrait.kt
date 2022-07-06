package base

import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test
import org.river.exertion.Constants
import org.river.exertion.astro.base.EarthLocation
import org.river.exertion.profile.base.Profiles

@ExperimentalUnsignedTypes
@OptIn(ExperimentalUnsignedTypes::class)
class TestPortrait {

    @Test
    fun testAvgRankingInDay() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)

        val checkLocation = EarthLocation(refProfile.earthLocation.longitude
            , refProfile.earthLocation.latitude
            , refProfile.earthLocation.altitude.toInt()
            , -6 //put hour offset here
            , LocalDate(1989, 6, 17)
        )

  //      listAvgRankingNoTimeAspects(refProfile, checkLocation, ChartState.COMPOSITE_CHART, AnalysisState.NO_ANALYSIS//ROMANTIC_ANALYSIS
  //          , duration = DateTimePeriod(hours = 12), granularity = DateTimePeriod(hours = 1), timeGranularity = true, limitResults = false
  //          , fileOutput = true)
    }

    @Test
    fun testAvgRankingOverDuration() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)

        val yearRange = 1 //from pivot date, e.g. '1' == 1 year forward, 1 year back
        val yearOffset = 4

        val checkLocation = EarthLocation(refProfile.earthLocation.longitude
            , refProfile.earthLocation.latitude
            , refProfile.earthLocation.altitude.toInt()
            , refProfile.earthLocation.getTimezoneOffsetInt() //put hour offset here
            , LocalDate(refProfile.earthLocation.utcDateTime.year + yearOffset
                , refProfile.earthLocation.utcDateTime.monthNumber
                , refProfile.earthLocation.utcDateTime.dayOfMonth)
        ) // center date

//        listAvgRankingNoTimeAspects(refProfile, checkLocation, ChartState.SYNASTRY_CHART, AnalysisState.NO_ANALYSIS
//            , duration = DateTimePeriod(years = yearRange), granularity = DateTimePeriod(hours = 1), timeGranularity = true, limitResults = false
//            , fileOutput = true)
    }

    @Test
    fun testAvgNestedRankingInDay() {

        val refLocation = EarthLocation(
            Constants.LON_ATX
            , Constants.LAT_ATX
            , Constants.ALT_ATX
            , -5 //put hour offset here
            , LocalDate(1971, 4, 5)
        )

        val checkLocation = EarthLocation(Constants.LON_ATX
            , Constants.LAT_ATX
            , Constants.ALT_ATX
            , 11 //put hour offset here
            , LocalDate(1972, 4, 9)
        )

//        listAvgNestedRankingNoTimeAspects(checkLocation, refLocation, ChartState.COMPOSITE_CHART, AnalysisState.NO_ANALYSIS//ROMANTIC_ANALYSIS
//            , duration = DateTimePeriod(hours = 12), granularity = DateTimePeriod(hours = 1), timeGranularity = true)
    }

/*
    fun listAvgRankingNoTimeAspects(refProfile: Profile, checkLocation: EarthLocation, chartState: ChartState, analysisState : AnalysisState = AnalysisState.NO_ANALYSIS
                                    , duration: DateTimePeriod = DateTimePeriod(months = 1), granularity: DateTimePeriod = DateTimePeriod(days = 1), timeGranularity : Boolean = false
                                    , limitResults : Boolean = false, fileOutput : Boolean = false) : Int {


        val natalChart = if (timeGranularity) Chart.getNatalChartForSnapshot(refProfile.celestialSnapshot, analysisState)
            else Chart.getNatalChartForSnapshotNoTime(refProfile.celestialSnapshot, analysisState)

        //noon UTC with offset for Profile timezone
        val timeZoneOffsetHour = 12 + checkLocation.getTimezoneOffsetInt()

        val startUTC = LocalDateTime(
            checkLocation.utcDateTime.year,
            checkLocation.utcDateTime.monthNumber,
            checkLocation.utcDateTime.dayOfMonth,
            timeZoneOffsetHour,
            0,
            0
        ).toInstant(TimeZone.UTC)

        val backwardMax = startUTC.minus(duration, TimeZone.UTC)
        val forwardMax = startUTC.plus(duration, TimeZone.UTC)

        var forwardPoll = backwardMax

        val outputList: MutableList<String> = mutableListOf("")

        outputList.add(0, RenderChart.getChartValueCalcDetailHeader() + ", avgWwh")
        outputList.add(1, RenderChart.getChartValueCalcRefRow(refProfile, natalChart, showTime = true))

        if (!limitResults) {
            println(outputList[0])
            println(outputList[1])
            println()
        }

        var avgWorthwhileNum = 0.0
        var avgWorthwhileDenom = 0
        var avgWorthwhileInt = 0
        var fileIdxCounter = 2

        while (forwardPoll < forwardMax) {
            val pollProfile = Profile(
                "pollProfile", EarthLocation(
                    checkLocation.longitude,
                    checkLocation.latitude,
                    checkLocation.altitude,
                    checkLocation.timeZone,
                    forwardPoll.toLocalDateTime(TimeZone.UTC)
                )
            )

            val pollNatal = if (timeGranularity) Chart.getNatalChartForSnapshot(pollProfile.celestialSnapshot, analysisState)
                else Chart.getNatalChartForSnapshotNoTime(pollProfile.celestialSnapshot, analysisState)


            val pollChart = if (timeGranularity) Chart.getDefaultChartForSnapshots(refProfile.celestialSnapshot, analysisState, pollProfile.celestialSnapshot, chartState)
                else Chart.getDefaultChartForSnapshotsNoTime(refProfile.celestialSnapshot, analysisState, pollProfile.celestialSnapshot, chartState)

            forwardPoll = forwardPoll.plus(granularity, TimeZone.UTC)

            avgWorthwhileNum += pollChart.getWorthwhile()
            avgWorthwhileDenom += 1
            avgWorthwhileInt = if (avgWorthwhileDenom > 0) (avgWorthwhileNum / avgWorthwhileDenom).toInt() else 0

            outputList.add(
                fileIdxCounter,
                RenderChart.getChartValueCalcDetailRow(
                    pollProfile,
                    pollNatal,
                    pollChart,
                    showTime = true
                ) + "," + avgWorthwhileInt
            )

            if ((!limitResults) || (forwardPoll == forwardMax)) {
                if ((!fileOutput) || (((fileIdxCounter - 2) % 90) == 0))
                    println(outputList[fileIdxCounter])
            }

            fileIdxCounter++
        }

        outputList[fileIdxCounter] = "Avg Worthwhile for range: $avgWorthwhileInt"

        if (!limitResults) println(outputList[fileIdxCounter])

        if (fileOutput) Csv.writeAllLines("./listAvgRankingNoTimeAspects_" + Clock.System.now().toLocalDateTime(TimeZone.UTC).toString(), outputList)


        return avgWorthwhileInt
    }

    // in case refProfile needs to have date approx
    fun listAvgNestedRankingNoTimeAspects(refLocation: EarthLocation, checkLocation: EarthLocation, chartState: ChartState, analysisState : AnalysisStates = AnalysisStates.NO_ANALYSIS
                                          , duration: DateTimePeriod = DateTimePeriod(months = 1), granularity: DateTimePeriod = DateTimePeriod(days = 1), timeGranularity : Boolean = false) {

        //noon UTC with offset for Profile timezone
        val timeZoneOffsetHour = 12 + refLocation.getTimezoneOffsetInt()

        val startUTC = LocalDateTime(
            refLocation.utcDateTime.year
            , refLocation.utcDateTime.monthNumber
            , refLocation.utcDateTime.dayOfMonth
            , timeZoneOffsetHour, 0, 0
        ).toInstant(TimeZone.UTC)

        val backwardMax = startUTC.minus(duration, TimeZone.UTC)
        val forwardMax = startUTC.plus(duration, TimeZone.UTC)

        var forwardPoll = backwardMax

        println (RenderChart.getChartValueCalcDetailHeader())

        var avgWorthwhileNum = 0.0
        var avgWorthwhileDenom = 0
        var avgWorthwhileInt = 0

        while (forwardPoll < forwardMax) {
            val pollProfile = Profile(
                "pollProfile", EarthLocation(
                    refLocation.longitude,
                    refLocation.latitude,
                    refLocation.altitude,
                    refLocation.timeZone,
                    forwardPoll.toLocalDateTime(TimeZone.UTC)
                )
            )

            val pollChart = Chart.getNatalChartForSnapshotNoTime(pollProfile.celestialSnapshot, analysisState)
            println (RenderChart.getChartValueCalcRefRow(pollProfile, pollChart, timeGranularity))

            val pollWorthwhile = listAvgRankingNoTimeAspects(pollProfile, checkLocation, chartState, analysisState
                , duration = DateTimePeriod(hours = 12), granularity = DateTimePeriod(hours = 1), timeGranularity = true, limitResults = true)

            forwardPoll = forwardPoll.plus(granularity, TimeZone.UTC)

            avgWorthwhileNum += pollWorthwhile
            avgWorthwhileDenom += 1
            avgWorthwhileInt = if (avgWorthwhileDenom > 0) (avgWorthwhileNum / avgWorthwhileDenom).toInt() else 0

        }

        println("Avg Worthwhile for range: $avgWorthwhileInt" )

    }*/

}