package console.state

import astro.state.HelpState

object ConsoleHelpState {

    fun getDefaultState() = HelpState.HELP_DISABLED

    fun HelpState.getNextState(input: Int) = this.getState(input)

    fun HelpState.getState(input: Int): HelpState {

        return when (input) {
            Constants.KEY_QMARK -> if (this == HelpState.HELP_DISABLED) HelpState.HELP_ENABLED else HelpState.HELP_DISABLED

            Constants.KEY_x -> getDefaultState()
            else -> this
        }
    }
}