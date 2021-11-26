package console.state

import astro.state.DetailsState

object ConsoleDetailsState {

    fun getDefaultState() = DetailsState.SHOW_DETAILS

    fun DetailsState.getNextState(input: Int) = this.getState(input)

    fun DetailsState.getState(input: Int): DetailsState {

        return when (input) {
            Constants.KEY_e -> if (this == DetailsState.SHOW_DETAILS) DetailsState.NO_DETAILS else DetailsState.SHOW_DETAILS

            Constants.KEY_x -> getDefaultState()
            else -> this
        }
    }
}