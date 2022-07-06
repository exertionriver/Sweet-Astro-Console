package org.river.exertion.console.state

import org.river.exertion.Constants.KEY_QMARK
import org.river.exertion.Constants.KEY_x
import org.river.exertion.astro.state.HelpState

object ConsoleHelpState {

    fun getDefaultState() = HelpState.HELP_DISABLED

    fun HelpState.getNextState(input: Int) = this.getState(input)

    fun HelpState.getState(input: Int): HelpState {

        return when (input) {
            KEY_QMARK -> if (this == HelpState.HELP_DISABLED) HelpState.HELP_ENABLED else HelpState.HELP_DISABLED

            KEY_x -> getDefaultState()
            else -> this
        }
    }
}