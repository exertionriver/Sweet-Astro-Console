package console.state

import astro.state.EntryState
import console.base.Validation
import kotlinx.datetime.LocalDateTime
import profile.base.Profile
import console.render.RenderHandler
import console.state.ConsoleEntryState.getSynEntryProfileIdx

@ExperimentalUnsignedTypes
object ConsoleEntryState {

    fun getDefaultState() = EntryState.NO_ENTRY

    fun EntryState.getNextState(input: Int) = this.getState(input)

//    fun EntryState.getCurEntryProfile(prevCurProfile: Profile) = this.getCurEntryProfile(prevCurProfile)
//    fun getSynEntryProfileIdx(prevSynProfileIdx : Int) = this.getSynEntryProfileIdx(prevSynProfileIdx)

    fun EntryState.getState(input: Int): EntryState {

        return when (input) {
            Constants.KEY_D -> EntryState.DATE_ENTRY
            Constants.KEY_T -> EntryState.TIME_ENTRY
            Constants.KEY_O -> EntryState.LONG_ENTRY
            Constants.KEY_A -> EntryState.LAT_ENTRY
            Constants.KEY_Z -> EntryState.TZ_ENTRY
            Constants.KEY_BANG, Constants.KEY_AT, Constants.KEY_HASH, Constants.KEY_DOLLAR, Constants.KEY_PERCENT
                , Constants.KEY_CARET, Constants.KEY_AMPERSAND, Constants.KEY_ASTERISK, Constants.KEY_OPENPARENS
                , Constants.KEY_CLOSEPARENS -> EntryState.PROFILE_ENTRY
            Constants.KEY_PLUS, Constants.KEY_EQUALS -> EntryState.PROFILE_NUMBER_ENTRY
            Constants.KEY_USCORE, Constants.KEY_ESC -> EntryState.NO_ENTRY

            Constants.KEY_x -> getDefaultState()
            else -> this
        }
    }

    fun EntryState.getCurEntryProfile(prevCurProfile: Profile, inputOverride : String? = null): Profile {

        val returnCurProfile : Profile
        val kEntry : String

        //exclude no entry or syn profile entry
        if ( (this != EntryState.NO_ENTRY) && (this != EntryState.PROFILE_NUMBER_ENTRY) ) {

            kEntry = RenderHandler.getPauseEntryInput(this.getPrompt(), inputOverride)

            if ( kEntry.contains(Constants.KEY_ESC.toChar()) )  {
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
                val entry = kEntry.toDouble()

                if (!Validation.isValidEntry(entry, Validation.LONGITUDE_VALIDATION)) return prevCurProfile

                returnCurProfile = Profile.getCopyWithLongitudeEntry(prevCurProfile, entry)
            }
            EntryState.LAT_ENTRY -> {
                val entry = kEntry.toDouble()

                if (!Validation.isValidEntry(entry, Validation.LATITUDE_VALIDATION)) return prevCurProfile

                returnCurProfile = Profile.getCopyWithLatitudeEntry(prevCurProfile, entry)
            }
            EntryState.TZ_ENTRY -> {
                val entry = kEntry.toDouble()

                if (!Validation.isValidEntry(entry, Validation.TIMEZONE_VALIDATION)) return prevCurProfile

                returnCurProfile = Profile.getCopyWithTimezoneEntry(prevCurProfile, entry)
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

            if ( kEntry.contains(Constants.KEY_ESC.toChar()) )  {
                return prevSynProfileIdx
            }

            val entry = kEntry.toDouble().toInt()

            if (!Validation.isValidEntry(entry, Validation.PROFILE_IDX_VALIDATION)) return prevSynProfileIdx

            return entry
        }
        else return prevSynProfileIdx
    }
}