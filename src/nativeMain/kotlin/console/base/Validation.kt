package console.base

import astro.state.EntryState
import profile.base.Profile
import console.render.RenderValidations

enum class Validation {
    YEAR_VALIDATION { override fun isValid(entry : Any) = ( (entry is Int) && (entry >= -5401) && (entry <= 5400) ) }
    , MON_VALIDATION { override fun isValid(entry : Any) = ( (entry is Int) && (entry >= 1) && (entry <= 12) ) }
    , MDAY_VALIDATION { override fun isValid(entry : Any) = ( (entry is Int) && (entry >= 1) && (entry <= 31) ) }
    , HOUR_VALIDATION { override fun isValid(entry : Any) = ( (entry is Int) && (entry >= 0) && (entry <= 23) ) }
    , MIN_VALIDATION { override fun isValid(entry : Any) = ( (entry is Int) && (entry >= 0) && (entry <= 59) ) }
    , SEC_VALIDATION { override fun isValid(entry : Any) = ( (entry is Int) && (entry >= 0) && (entry <= 59 ) ) }
    , LONGITUDE_VALIDATION { override fun isValid(entry : Any) = ( (entry is Double) && (entry >= -180) && (entry <= 180) ) }
    , LATITUDE_VALIDATION { override fun isValid(entry : Any) = ( (entry is Double) && (entry >= -90) && (entry <= 90) ) }
    , TIMEZONE_VALIDATION { override fun isValid(entry : Any) = ( (entry is Double) && (entry >= -12) && (entry <= 14) ) }
    , PROFILE_NAME_VALIDATION { override fun isValid(entry : Any) = ( (entry is String) && (entry.length <= Profile.getMaxProfileNameLength()) ) }
    , PROFILE_IDX_VALIDATION { override fun isValid(entry : Any) = ( (entry is Int) && (entry >= 0) && (entry <= 9) ) } ;

    abstract fun isValid(entry : Any): Boolean

    @ExperimentalUnsignedTypes
    companion object {

        fun isValidDateStringFormat(dateString : String, report : Boolean = true) : Boolean {

            val isValid = dateString.contains(Regex("^\\d{4}${EntryState.DATE_ENTRY}\\d{2}${EntryState.DATE_ENTRY}\\d{2}$") )

            return (RenderValidations.getRenderIsValidDateString(dateString, isValid, report) == RenderValidations.isValidString)
        }

        fun isValidDate(year : Int, mon : Int, mday : Int, report : Boolean = true) : Boolean {

            val isValid = (YEAR_VALIDATION.isValid(year) && MON_VALIDATION.isValid(mon) && MDAY_VALIDATION.isValid(mday))

            val dateString = "$year${EntryState.DATE_ENTRY}$mon${EntryState.DATE_ENTRY}$mday"

            return (RenderValidations.getRenderIsValidDateString(dateString, isValid, report) == RenderValidations.isValidString)
        }

        fun isValidTimeStringFormat(timeString : String, report : Boolean = true) : Boolean {

            val isValid = timeString.contains(Regex("^\\d{2}${EntryState.TIME_ENTRY}\\d{2}${EntryState.TIME_ENTRY}\\d{2}$") )

            return (RenderValidations.getRenderIsValidTimeString(timeString, isValid, report) == RenderValidations.isValidString)
        }

        fun isValidTime(hour : Int, min : Int, sec : Int, report : Boolean = true) : Boolean {

            val isValid = (HOUR_VALIDATION.isValid(hour) && MIN_VALIDATION.isValid(min) && SEC_VALIDATION.isValid(sec))

            val timeString = "$hour${EntryState.TIME_ENTRY}$min${EntryState.TIME_ENTRY}$sec"

            return (RenderValidations.getRenderIsValidTimeString(timeString, isValid, report) == RenderValidations.isValidString)
        }

        fun isValidEntry(any: Any, anyValidation: Validation, report: Boolean = true) : Boolean {

            val isValid = anyValidation.isValid(any)

            return (RenderValidations.getRenderIsValidAnyString(any.toString(), isValid, report) == RenderValidations.isValidString)
        }
    }
}