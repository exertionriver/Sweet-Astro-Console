package console.state

import astro.state.EntryState
import astro.state.NavDirState
import astro.state.NavState
import kotlinx.datetime.*
import kotlin.time.ExperimentalTime

@ExperimentalUnsignedTypes
object ConsoleNavState {

    fun getDefaultState() = NavState.CURRENT

    fun NavState.getNextState(input: Int) = this.getState(input)

    fun NavState.getNextState(entryState: EntryState) = this.getNextStateFromEntryState(entryState)

//    @ExperimentalTime
//    fun NavState.getCurNavTime(navDirState: NavDirState, curNavTime: LocalDateTime) = this.getCurNavTime(navDirState, curNavTime)

    fun NavState.getNextStateFromEntryState(entryState: EntryState) : NavState {

        return when (entryState) {
            EntryState.PROFILE_ENTRY -> NavState.PROFILE_RECALL
            EntryState.PROFILE_NUMBER_ENTRY -> this
            EntryState.NO_ENTRY -> this
            else -> NavState.NAV_PAUSED
        }
    }

    fun NavState.getState(input: Int): NavState {

        return when (input) {
            Constants.KEY_SPACE -> if (this.isPaused()) NavState.NAV_SECOND else NavState.NAV_PAUSED
            Constants.KEY_ESC -> NavState.CURRENT
            Constants.KEY_MINUS -> if (this == NavState.CURRENT) NavState.NAV_SECOND else this //curNavDirState reverses direction
            Constants.KEY_s -> NavState.NAV_SECOND
            Constants.KEY_m -> NavState.NAV_MINUTE
            Constants.KEY_h -> NavState.NAV_HOUR
            Constants.KEY_d -> NavState.NAV_DAY
            Constants.KEY_w -> NavState.NAV_WEEK
            Constants.KEY_o -> NavState.NAV_MONTH
            Constants.KEY_y -> NavState.NAV_YEAR
//entry states
            Constants.KEY_D, Constants.KEY_T, Constants.KEY_O, Constants.KEY_A, Constants.KEY_Z -> NavState.ENTRY_PAUSED
//profile entry
            Constants.KEY_BANG, Constants.KEY_AT, Constants.KEY_HASH, Constants.KEY_DOLLAR, Constants.KEY_PERCENT
                , Constants.KEY_CARET, Constants.KEY_AMPERSAND, Constants.KEY_ASTERISK, Constants.KEY_OPENPARENS
                , Constants.KEY_CLOSEPARENS -> NavState.ENTRY_PAUSED
//profile recall
            Constants.KEY_1, Constants.KEY_2, Constants.KEY_3, Constants.KEY_4, Constants.KEY_5, Constants.KEY_6
                , Constants.KEY_7, Constants.KEY_8, Constants.KEY_9, Constants.KEY_0 -> NavState.PROFILE_RECALL

            Constants.KEY_x -> getDefaultState()
            else -> return this // no change
        }
    }

    @ExperimentalTime
    fun NavState.getCurNavTime(navDirState: NavDirState, curNavTime: LocalDateTime): LocalDateTime {

        val curNavTimeInstant = curNavTime.toInstant(TimeZone.UTC)

        return when (this) {
            NavState.CURRENT -> Clock.System.now().toLocalDateTime(TimeZone.UTC)
            NavState.NAV_SECOND -> curNavTimeInstant.plus(navDirState.getIncDec(), DateTimeUnit.SECOND).toLocalDateTime(
                TimeZone.UTC)
            NavState.NAV_MINUTE -> curNavTimeInstant.plus(navDirState.getIncDec(), DateTimeUnit.MINUTE).toLocalDateTime(
                TimeZone.UTC)
            NavState.NAV_HOUR -> curNavTimeInstant.plus(navDirState.getIncDec(), DateTimeUnit.HOUR).toLocalDateTime(
                TimeZone.UTC)
            NavState.NAV_DAY -> curNavTimeInstant.plus(DateTimePeriod(days = navDirState.getIncDec()), TimeZone.UTC).toLocalDateTime(
                TimeZone.UTC)
            NavState.NAV_WEEK -> curNavTimeInstant.plus(DateTimePeriod(days = navDirState.getIncDec() * 7), TimeZone.UTC).toLocalDateTime(
                TimeZone.UTC)
            NavState.NAV_MONTH -> curNavTimeInstant.plus(DateTimePeriod(months = navDirState.getIncDec()), TimeZone.UTC).toLocalDateTime(
                TimeZone.UTC)
            NavState.NAV_YEAR -> curNavTimeInstant.plus(DateTimePeriod(years = navDirState.getIncDec()), TimeZone.UTC).toLocalDateTime(
                TimeZone.UTC)
            else -> curNavTime
        }
    }
}