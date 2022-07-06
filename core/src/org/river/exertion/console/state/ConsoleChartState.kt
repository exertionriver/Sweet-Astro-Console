package org.river.exertion.console.state

import org.river.exertion.Constants.KEY_EQUALS
import org.river.exertion.Constants.KEY_ESC
import org.river.exertion.Constants.KEY_PLUS
import org.river.exertion.Constants.KEY_USCORE
import org.river.exertion.Constants.KEY_x
import org.river.exertion.astro.state.ChartState
import org.river.exertion.profile.base.Profiles

@ExperimentalUnsignedTypes
object ConsoleChartState {

    fun getDefaultState() = ChartState.NATAL_CHART

    fun ChartState.getNextState(input: Int) = this.getState(input)

    fun ChartState.getState(input: Int): ChartState {

        return when (input) {
            KEY_PLUS -> ChartState.SYNASTRY_CHART
            KEY_EQUALS -> ChartState.COMPOSITE_CHART
            KEY_USCORE -> ChartState.NATAL_CHART
            KEY_ESC -> ChartState.NATAL_CHART

            KEY_x -> getDefaultState()
            else -> this
        }
    }

    fun isPresentChart(curChartState: ChartState, curProfileIdx: Int, synProfileIdx: Int) : Boolean {
        return ( (curChartState == ChartState.NATAL_CHART) && (Profiles.isCurNavProfile(curProfileIdx)) && (!Profiles.isStoredProfile(synProfileIdx)))
    }
    fun isPresentCompositeChart(curChartState: ChartState, curProfileIdx: Int, synProfileIdx: Int) : Boolean {
        return ( (curChartState == ChartState.COMPOSITE_CHART) && (Profiles.isCurNavProfile(curProfileIdx))  && (Profiles.isStoredProfile(synProfileIdx)) )
    }
    fun isPresentTransitChart(curChartState: ChartState, curProfileIdx: Int, synProfileIdx: Int) : Boolean {
        return ( (curChartState == ChartState.SYNASTRY_CHART) && (Profiles.isCurNavProfile(curProfileIdx))  && (Profiles.isStoredProfile(synProfileIdx)) )
    }
    fun isProfileChart(curChartState: ChartState, curProfileIdx: Int, synProfileIdx: Int) : Boolean {
        return ( (curChartState == ChartState.NATAL_CHART) && (Profiles.isStoredProfile(curProfileIdx)) && (!Profiles.isStoredProfile(synProfileIdx)))
    }
    fun isProfileCompositeChart(curChartState: ChartState, curProfileIdx: Int, synProfileIdx: Int) : Boolean {
        return ( (curChartState == ChartState.COMPOSITE_CHART) && (Profiles.isStoredProfile(curProfileIdx)) && (Profiles.isStoredProfile(synProfileIdx) ) )
    }
    fun isProfileSynastryChart(curChartState: ChartState, curProfileIdx: Int, synProfileIdx: Int) : Boolean {
        return ( (curChartState == ChartState.SYNASTRY_CHART) && (Profiles.isStoredProfile(curProfileIdx)) && (Profiles.isStoredProfile(synProfileIdx)) )
    }
}