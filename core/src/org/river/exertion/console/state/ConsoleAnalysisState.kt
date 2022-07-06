package console.state

import org.river.exertion.Constants.KEY_C
import org.river.exertion.Constants.KEY_E
import org.river.exertion.Constants.KEY_ESC
import org.river.exertion.Constants.KEY_M
import org.river.exertion.Constants.KEY_P
import org.river.exertion.Constants.KEY_R
import org.river.exertion.Constants.KEY_x
import org.river.exertion.astro.state.AnalysisState

object ConsoleAnalysisState {

    fun getDefaultState() = AnalysisState.NO_ANALYSIS

    fun AnalysisState.getNextState(input: Int) = this.getState(input)

    fun AnalysisState.getState(input: Int): AnalysisState {

        return when (input) {
            KEY_C -> if (this == AnalysisState.CHARACTER_ANALYSIS) AnalysisState.NO_ANALYSIS else AnalysisState.CHARACTER_ANALYSIS
            KEY_E -> if (this == AnalysisState.ELEMENT_ANALYSIS) AnalysisState.NO_ANALYSIS else AnalysisState.ELEMENT_ANALYSIS
            KEY_M -> if (this == AnalysisState.MODE_ANALYSIS) AnalysisState.NO_ANALYSIS else AnalysisState.MODE_ANALYSIS
            KEY_P -> if (this == AnalysisState.PLANET_ANALYSIS) AnalysisState.NO_ANALYSIS else AnalysisState.PLANET_ANALYSIS
            KEY_R -> if (this == AnalysisState.ROMANTIC_ANALYSIS) AnalysisState.NO_ANALYSIS else AnalysisState.ROMANTIC_ANALYSIS
            KEY_ESC -> AnalysisState.NO_ANALYSIS

            KEY_x -> getDefaultState()
            else -> this
        }
    }
}