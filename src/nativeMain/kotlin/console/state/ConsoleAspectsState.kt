package console.state

import astro.state.AspectsState

object ConsoleAspectsState {

    fun getDefaultState() = AspectsState.ALL_ASPECTS

    fun AspectsState.getNextState(input: Int) = this.getState(input)

    fun AspectsState.getState(input: Int): AspectsState {

        return when (input) {
            Constants.KEY_a -> when (this) {
                AspectsState.ALL_ASPECTS -> AspectsState.MAJOR_ASPECTS
                AspectsState.MAJOR_ASPECTS -> AspectsState.MINOR_ASPECTS
                else -> AspectsState.ALL_ASPECTS
            }

            Constants.KEY_x -> getDefaultState()
            else -> this
        }
    }
}