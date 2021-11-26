package console.state

import astro.state.TimeAspectsState

object ConsoleTimeAspectsState {

    fun getDefaultState() = TimeAspectsState.TIME_ASPECTS_ENABLED

    fun TimeAspectsState.getNextState(input: Int) = this.getState(input)

    fun TimeAspectsState.getState(input: Int): TimeAspectsState {

        return when (input) {
            Constants.KEY_t -> if (this == TimeAspectsState.TIME_ASPECTS_DISABLED) TimeAspectsState.TIME_ASPECTS_ENABLED else TimeAspectsState.TIME_ASPECTS_DISABLED

            Constants.KEY_x -> getDefaultState()
            else -> this
        }
    }
}