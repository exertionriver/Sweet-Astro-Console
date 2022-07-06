package org.river.exertion.console.state

import kotlinx.datetime.*
import org.river.exertion.Constants.KEY_0
import org.river.exertion.Constants.KEY_1
import org.river.exertion.Constants.KEY_2
import org.river.exertion.Constants.KEY_3
import org.river.exertion.Constants.KEY_4
import org.river.exertion.Constants.KEY_5
import org.river.exertion.Constants.KEY_6
import org.river.exertion.Constants.KEY_7
import org.river.exertion.Constants.KEY_8
import org.river.exertion.Constants.KEY_9
import org.river.exertion.Constants.KEY_A
import org.river.exertion.Constants.KEY_AMPERSAND
import org.river.exertion.Constants.KEY_ASTERISK
import org.river.exertion.Constants.KEY_AT
import org.river.exertion.Constants.KEY_BANG
import org.river.exertion.Constants.KEY_CARET
import org.river.exertion.Constants.KEY_CLOSEPARENS
import org.river.exertion.Constants.KEY_D
import org.river.exertion.Constants.KEY_DOLLAR
import org.river.exertion.Constants.KEY_ESC
import org.river.exertion.Constants.KEY_HASH
import org.river.exertion.Constants.KEY_MINUS
import org.river.exertion.Constants.KEY_O
import org.river.exertion.Constants.KEY_OPENPARENS
import org.river.exertion.Constants.KEY_PERCENT
import org.river.exertion.Constants.KEY_SPACE
import org.river.exertion.Constants.KEY_T
import org.river.exertion.Constants.KEY_Z
import org.river.exertion.Constants.KEY_d
import org.river.exertion.Constants.KEY_h
import org.river.exertion.Constants.KEY_m
import org.river.exertion.Constants.KEY_o
import org.river.exertion.Constants.KEY_s
import org.river.exertion.Constants.KEY_w
import org.river.exertion.Constants.KEY_x
import org.river.exertion.Constants.KEY_y
import org.river.exertion.astro.state.EntryState
import org.river.exertion.astro.state.NavDirState
import org.river.exertion.astro.state.NavState
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
            KEY_SPACE -> if (this.isPaused()) NavState.NAV_SECOND else NavState.NAV_PAUSED
            KEY_ESC -> NavState.CURRENT
            KEY_MINUS -> if (this == NavState.CURRENT) NavState.NAV_SECOND else this //curNavDirState reverses direction
            KEY_s -> NavState.NAV_SECOND
            KEY_m -> NavState.NAV_MINUTE
            KEY_h -> NavState.NAV_HOUR
            KEY_d -> NavState.NAV_DAY
            KEY_w -> NavState.NAV_WEEK
            KEY_o -> NavState.NAV_MONTH
            KEY_y -> NavState.NAV_YEAR
//entry states
            KEY_D, KEY_T, KEY_O, KEY_A, KEY_Z -> NavState.ENTRY_PAUSED
//profile entry
            KEY_BANG, KEY_AT, KEY_HASH, KEY_DOLLAR, KEY_PERCENT
                , KEY_CARET, KEY_AMPERSAND, KEY_ASTERISK, KEY_OPENPARENS
                , KEY_CLOSEPARENS -> NavState.ENTRY_PAUSED
//profile recall
            KEY_1, KEY_2, KEY_3, KEY_4, KEY_5, KEY_6
                , KEY_7, KEY_8, KEY_9, KEY_0 -> NavState.PROFILE_RECALL

            KEY_x -> getDefaultState()
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