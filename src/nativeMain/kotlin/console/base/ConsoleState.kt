package console.base

import astro.state.*
import astro.value.ValueChart
import console.render.RenderHandler
import console.render.RenderConsole
import kotlinx.datetime.*
import profile.base.Profile
import profile.base.Profiles
import console.render.RenderDetails
import console.state.*
import console.state.ConsoleAnalysisState.getNextState
import console.state.ConsoleAspectOverlayState.getNextState
import console.state.ConsoleAspectOverlayState.getToggledState
import console.state.ConsoleAspectsState.getNextState
import console.state.ConsoleChartState.getNextState
import console.state.ConsoleDetailsState.getNextState
import console.state.ConsoleEntryState.getCurEntryProfile
import console.state.ConsoleEntryState.getNextState
import console.state.ConsoleEntryState.getSynEntryProfileIdx
import console.state.ConsoleHelpState.getNextState
import console.state.ConsoleNavDirState.getNextState
import console.state.ConsoleNavState.getCurNavTime
import console.state.ConsoleNavState.getNextState
import console.state.ConsoleTimeAspectsState.getNextState
import kotlin.time.ExperimentalTime

@ExperimentalUnsignedTypes
class ConsoleState {

    var curNavState = ConsoleNavState.getDefaultState()
    var curNavDirState = ConsoleNavDirState.getDefaultState()
    var curEntryState = ConsoleEntryState.getDefaultState()
    var curHelpState = ConsoleHelpState.getDefaultState()
    var curAspectsState = ConsoleAspectsState.getDefaultState()
    var curTimeAspectsState = ConsoleTimeAspectsState.getDefaultState()
    var curDetailsState = ConsoleDetailsState.getDefaultState()
    var curAnalysisState = ConsoleAnalysisState.getDefaultState()

    //chart render state
    var curChartState = ConsoleChartState.getDefaultState()
    var curAspectOverlayState = ConsoleAspectOverlayState.getDefaultState()

    var curProfileIdx = Profiles.PROFILE_CUR_NAV.ordinal
    var synProfileIdx = Profiles.PROFILE_CUR_NAV.ordinal

    var profiles: Array<Profile> = Profiles.getDefaultProfiles()

    var curNavProfile = Profiles.getDefaultProfile(Profiles.PROFILE_CUR_NAV)

    var refSnapshot = curNavProfile.celestialSnapshot
    var synSnapshot = curNavProfile.celestialSnapshot
    var compSnapshot = curNavProfile.celestialSnapshot

    @ExperimentalUnsignedTypes
    var curNavUTCTime : LocalDateTime = curNavProfile.earthLocation.utcDateTime

    private var curChart = StateChart.getEmptyChart()
    private var curRefNatalChart = StateChart.getEmptyChart()
    private var curSynNatalChart = StateChart.getEmptyChart()

    private var curValueChart = ValueChart.getEmptyChart()
    private var curRefNatalValueChart = ValueChart.getEmptyChart()
    private var curSynNatalValueChart = ValueChart.getEmptyChart()

    private var prevChart = StateChart.getEmptyChart()
    private var prevRefNatalChart = StateChart.getEmptyChart()
    private var prevSynNatalChart = StateChart.getEmptyChart()

    private var input = 0

    @ExperimentalTime
    @ExperimentalUnsignedTypes
    fun mainLoop() {

        while (true) {

            RenderHandler.clearScreen()

            input = RenderHandler.getMainLoopInput()

            //based on input, set states / profiles
            curNavState = curNavState.getNextState(input)
            curNavDirState = curNavDirState.getNextState(input)
            curEntryState = curEntryState.getNextState(input)

            //resetting defaults if appropriate
            if (curEntryState == EntryState.RESET_DEFAULTS) {

                curNavState = ConsoleNavState.getDefaultState()
                curNavDirState = ConsoleNavDirState.getDefaultState()
                curEntryState = ConsoleEntryState.getDefaultState()

                curNavProfile = Profiles.getDefaultProfile(Profiles.PROFILE_CUR_NAV)
            }

            curHelpState = curHelpState.getNextState(input)
            curAspectsState = curAspectsState.getNextState(input)
            curTimeAspectsState = curTimeAspectsState.getNextState(input)
            curDetailsState = curDetailsState.getNextState(input)
            curAnalysisState = curAnalysisState.getNextState(input)

            curChartState = curChartState.getNextState(input)
            curAspectOverlayState = curAspectOverlayState.getNextState(curChartState, input)

            curProfileIdx = Profiles.getCurProfileIdx(curProfileIdx, input) // changes with input--default is Profiles.PROFILE_CUR_NAV
            synProfileIdx = Profiles.getSynProfileIdx(synProfileIdx, input) // changes with input--default is Profiles.PROFILE_CUR_NAV, selecting state is Profiles.PROFILE_NONE

            if (Profiles.isStoredProfile(curProfileIdx) && curNavState == NavState.PROFILE_RECALL) {
                curNavUTCTime = profiles[curProfileIdx].earthLocation.utcDateTime
                curNavProfile = Profile.getCopyWithDateTimeEntry(profiles[curProfileIdx], curNavUTCTime, profiles[synProfileIdx])
            }
            else {
                curNavUTCTime = curNavState.getCurNavTime(curNavDirState, curNavProfile.earthLocation.utcDateTime)
                //set curNav profile, with synProfile transits (for synastry chart)
                curNavProfile = Profile.getCopyWithDateTimeEntry(curNavProfile, curNavUTCTime, profiles[synProfileIdx])
            }

            //re-initialize these to current / navigation snapshot
            refSnapshot = curNavProfile.celestialSnapshot
            synSnapshot = curNavProfile.celestialSnapshot
            //composite snapshot
            compSnapshot = curNavProfile.celestialSnapshot

            curRefNatalChart = StateChart(refSnapshot, ChartState.NATAL_CHART, curAspectsState, curTimeAspectsState, curAspectOverlayState)

            //if no second profile is chosen, render previous chart
            if ( Profiles.isNoneProfile(synProfileIdx) ) {
                curChart = prevChart
                curSynNatalChart = prevSynNatalChart
            } else {
                //second profile chosen, get composite profile if composite chart
                if (curChartState == ChartState.COMPOSITE_CHART) {
                    curNavProfile = Profiles.getCompositeProfile(curNavProfile, profiles[synProfileIdx])
                    compSnapshot = curNavProfile.celestialSnapshot
                }

                if (curChartState != ChartState.NATAL_CHART) synSnapshot = profiles[synProfileIdx].celestialSnapshot

                curChart = StateChart(refSnapshot, synSnapshot, curChartState, curAspectsState, curTimeAspectsState, curAspectOverlayState)

                //natal charts need natcomp overlay
                if (curChartState == ChartState.SYNASTRY_CHART) {
                    curRefNatalChart = StateChart(refSnapshot, ChartState.NATAL_CHART, curAspectsState, curTimeAspectsState, curAspectOverlayState.getToggledState())
                    curSynNatalChart = StateChart(synSnapshot, ChartState.NATAL_CHART, curAspectsState, curTimeAspectsState, curAspectOverlayState.getToggledState())
                } else {
                    curSynNatalChart = StateChart(synSnapshot, ChartState.NATAL_CHART, curAspectsState, curTimeAspectsState, curAspectOverlayState)
                }

            }

            //if composite profile was made, set refSnapshot to that snapshot
            refSnapshot = compSnapshot

            //prep for possible next input pause
            prevChart = curChart
            prevRefNatalChart = curRefNatalChart
            prevSynNatalChart = curSynNatalChart

            curValueChart = ValueChart(curChart, curAnalysisState)
            curRefNatalValueChart = ValueChart(curRefNatalChart, curAnalysisState)
            curSynNatalValueChart = ValueChart(curSynNatalChart, curAnalysisState)

            RenderConsole.renderHeader(this)
            RenderConsole.renderEarthData(curNavProfile.earthLocation)
            RenderDetails.renderCelestialsDetailsData(refSnapshot, synSnapshot, curValueChart, curChart.chartState, curDetailsState, curAnalysisState)
            RenderDetails.renderHouseAspectDetailsData(refSnapshot, curValueChart, curRefNatalValueChart, curSynNatalValueChart, curChart.chartState, curDetailsState, curAnalysisState)

            //entry state handling
            curNavProfile = curEntryState.getCurEntryProfile(curNavProfile) // return profile copy of current if entry is good, previous if no input or validation fail

            profiles[Profiles.getStoreCurInputProfileIdx(input)] = curNavProfile //get desired profile slot Idx, or NONE if no input

            synProfileIdx = curEntryState.getSynEntryProfileIdx(synProfileIdx) //in case of profile recall, or previous if no input

            if (!curNavProfile.isProfileBlank) curNavState = curNavState.getNextState(curEntryState) //in case of profile recall or curnav entry

            curEntryState = EntryState.NO_ENTRY //reset entry to none

            RenderConsole.renderHelpLabels(curHelpState)

            RenderHandler.delayRun(1)
        }
    }
}