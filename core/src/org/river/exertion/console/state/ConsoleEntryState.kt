package org.river.exertion.console.state

import org.river.exertion.Constants.KEY_A
import org.river.exertion.Constants.KEY_AMPERSAND
import org.river.exertion.Constants.KEY_ASTERISK
import org.river.exertion.Constants.KEY_AT
import org.river.exertion.Constants.KEY_BANG
import org.river.exertion.Constants.KEY_CARET
import org.river.exertion.Constants.KEY_CLOSEPARENS
import org.river.exertion.Constants.KEY_D
import org.river.exertion.Constants.KEY_DOLLAR
import org.river.exertion.Constants.KEY_EQUALS
import org.river.exertion.Constants.KEY_ESC
import org.river.exertion.Constants.KEY_HASH
import org.river.exertion.Constants.KEY_O
import org.river.exertion.Constants.KEY_OPENPARENS
import org.river.exertion.Constants.KEY_PERCENT
import org.river.exertion.Constants.KEY_PLUS
import org.river.exertion.Constants.KEY_T
import org.river.exertion.Constants.KEY_USCORE
import org.river.exertion.Constants.KEY_Z
import org.river.exertion.Constants.KEY_x
import org.river.exertion.astro.state.EntryState

@ExperimentalUnsignedTypes
object ConsoleEntryState {

    fun getDefaultState() = EntryState.NO_ENTRY

    fun EntryState.getNextState(input: Int) = this.getState(input)

    fun EntryState.getState(input: Int): EntryState {

        return when (input) {
            KEY_D -> EntryState.DATE_ENTRY
            KEY_T -> EntryState.TIME_ENTRY
            KEY_O -> EntryState.LONG_ENTRY
            KEY_A -> EntryState.LAT_ENTRY
            KEY_Z -> EntryState.TZ_ENTRY
            KEY_BANG, KEY_AT, KEY_HASH, KEY_DOLLAR, KEY_PERCENT
                , KEY_CARET, KEY_AMPERSAND, KEY_ASTERISK, KEY_OPENPARENS
                , KEY_CLOSEPARENS -> EntryState.PROFILE_ENTRY
            KEY_PLUS, KEY_EQUALS -> EntryState.PROFILE_NUMBER_ENTRY
            KEY_USCORE, KEY_ESC -> EntryState.NO_ENTRY

            KEY_x -> EntryState.RESET_DEFAULTS
            else -> this
        }
    }
/*
    fun EntryState.getCurEntryProfile(prevCurProfile: Profile, inputOverride : String? = null): Profile {

        val returnCurProfile : Profile
        val kEntry : String

        //exclude no entry or syn profile entry
        if ( (this != EntryState.NO_ENTRY) && (this != EntryState.PROFILE_NUMBER_ENTRY) ) {

            kEntry = RenderHandler.getPauseEntryInput(this.getPrompt(), inputOverride)

            if ( kEntry.contains(KEY_ESC.toChar()) )  {
                return prevCurProfile
            }
        } else kEntry = ""

        when (this) {
            EntryState.DATE_ENTRY -> {
                if (!Validation.isValidDateStringFormat(kEntry)) return prevCurProfile

                val entry = kEntry.split(this.getDelim())

                val entryYear = entry[0].toDouble().toInt(); val entryMonth = entry[1].toDouble().toInt(); val entryDayOfMonth = entry[2].toDouble().toInt()

                if (!Validation.isValidDate(entryYear, entryMonth, entryDayOfMonth)) return prevCurProfile

                val prevCurProfileDateTime = prevCurProfile.earthLocation.utcDateTime
                val newDateTime = LocalDateTime(entryYear, entryMonth, entryDayOfMonth,
                    prevCurProfileDateTime.hour, prevCurProfileDateTime.minute, prevCurProfileDateTime.second, prevCurProfileDateTime.nanosecond)

                returnCurProfile = Profile.getCopyWithDateTimeEntry(prevCurProfile, newDateTime)
            }
            EntryState.TIME_ENTRY -> {
                if (!Validation.isValidTimeStringFormat(kEntry)) return prevCurProfile

                val entry = kEntry.split(this.getDelim())
                val entryHour = entry[0].toDouble().toInt(); val entryMinute = entry[1].toDouble().toInt(); val entrySecond = entry[2].toDouble().toInt()

                if (!Validation.isValidTime(entryHour, entryMinute, entrySecond)) return prevCurProfile

                val prevCurProfileDateTime = prevCurProfile.earthLocation.utcDateTime
                val newDateTime = LocalDateTime(prevCurProfileDateTime.year, prevCurProfileDateTime.monthNumber, prevCurProfileDateTime.dayOfMonth,
                    entryHour, entryMinute, entrySecond, prevCurProfileDateTime.nanosecond)

                returnCurProfile = Profile.getCopyWithDateTimeEntry(prevCurProfile, newDateTime)
            }
            EntryState.LONG_ENTRY -> {
                if (!Validation.isValidDoubleFormat(kEntry)) return prevCurProfile

                val entry = kEntry.toDouble()

                if (!Validation.isValidEntry(entry, Validation.LONGITUDE_VALIDATION)) return prevCurProfile

                returnCurProfile = Profile.getCopyWithLongitudeEntry(prevCurProfile, entry)
            }
            EntryState.LAT_ENTRY -> {
                if (!Validation.isValidDoubleFormat(kEntry)) return prevCurProfile

                val entry = kEntry.toDouble()

                if (!Validation.isValidEntry(entry, Validation.LATITUDE_VALIDATION)) return prevCurProfile

                returnCurProfile = Profile.getCopyWithLatitudeEntry(prevCurProfile, entry)
            }
            EntryState.TZ_ENTRY -> {
                if (!Validation.isValidIntFormat(kEntry)) return prevCurProfile

                val entry = kEntry.toInt()

                if (!Validation.isValidEntry(entry, Validation.TIMEZONE_VALIDATION)) return prevCurProfile

                returnCurProfile = Profile.getCopyWithTimezoneEntry(prevCurProfile, entry.toDouble())
            }
            EntryState.PROFILE_ENTRY -> {
                if (!Validation.isValidEntry(kEntry, Validation.PROFILE_NAME_VALIDATION)) return prevCurProfile

                returnCurProfile = Profile.getCopyWithNameEntry(prevCurProfile, kEntry)
            }
            else -> {
                return prevCurProfile
            }
        }

        return returnCurProfile
    }

    fun EntryState.getSynEntryProfileIdx(prevSynProfileIdx : Int) : Int {

        if (this == EntryState.PROFILE_NUMBER_ENTRY) {

            val kEntry = RenderHandler.getPauseEntryInput(this.getPrompt())

            if ( kEntry.contains(KEY_ESC.toChar()) )  {
                return prevSynProfileIdx
            }

            val entry = kEntry.toDouble().toInt()

            if (!Validation.isValidEntry(entry, Validation.PROFILE_IDX_VALIDATION)) return prevSynProfileIdx

            return entry
        }
        else return prevSynProfileIdx
    }*/
}