package console.state

import Constants
import astro.state.NavDirState

object ConsoleNavDirState {

    fun getDefaultState() = NavDirState.NAV_FORWARD

    fun NavDirState.getNextState(input: Int) = this.getState(input)

    fun NavDirState.getState(input: Int): NavDirState {

        return when (input) {
            Constants.KEY_MINUS -> if (this == NavDirState.NAV_FORWARD) NavDirState.NAV_REVERSE else NavDirState.NAV_FORWARD

            Constants.KEY_x -> getDefaultState()
            else -> this
        }
    }
}