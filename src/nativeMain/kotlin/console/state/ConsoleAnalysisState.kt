package console.state

import astro.state.AnalysisState

object ConsoleAnalysisState {

    fun getDefaultState() = AnalysisState.NO_ANALYSIS

    fun AnalysisState.getNextState(input: Int) = this.getState(input)

    fun AnalysisState.getState(input: Int): AnalysisState {

        return when (input) {
            Constants.KEY_C -> if (this == AnalysisState.CHARACTER_ANALYSIS) AnalysisState.NO_ANALYSIS else AnalysisState.CHARACTER_ANALYSIS
            Constants.KEY_E -> if (this == AnalysisState.ELEMENT_ANALYSIS) AnalysisState.NO_ANALYSIS else AnalysisState.ELEMENT_ANALYSIS
            Constants.KEY_M -> if (this == AnalysisState.MODE_ANALYSIS) AnalysisState.NO_ANALYSIS else AnalysisState.MODE_ANALYSIS
            Constants.KEY_P -> if (this == AnalysisState.PLANET_ANALYSIS) AnalysisState.NO_ANALYSIS else AnalysisState.PLANET_ANALYSIS
            Constants.KEY_R -> if (this == AnalysisState.ROMANTIC_ANALYSIS) AnalysisState.NO_ANALYSIS else AnalysisState.ROMANTIC_ANALYSIS
            Constants.KEY_ESC -> AnalysisState.NO_ANALYSIS

            Constants.KEY_x -> getDefaultState()
            else -> this
        }
    }
}