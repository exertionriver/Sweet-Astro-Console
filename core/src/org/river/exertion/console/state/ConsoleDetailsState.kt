package org.river.exertion.console.state

import org.river.exertion.Constants.KEY_e
import org.river.exertion.Constants.KEY_x
import org.river.exertion.astro.state.DetailsState

object ConsoleDetailsState {

    fun getDefaultState() = DetailsState.SHOW_DETAILS

    fun DetailsState.getNextState(input: Int) = this.getState(input)

    fun DetailsState.getState(input: Int): DetailsState {

        return when (input) {
            KEY_e -> if (this == DetailsState.SHOW_DETAILS) DetailsState.NO_DETAILS else DetailsState.SHOW_DETAILS

            KEY_x -> getDefaultState()
            else -> this
        }
    }
}