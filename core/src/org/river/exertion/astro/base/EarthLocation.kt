package org.river.exertion.astro.base

import org.river.exertion.astro.state.EntryState
import kotlinx.datetime.*
import org.river.exertion.Constants.ALT_TNM
import org.river.exertion.Constants.LAT_TNM
import org.river.exertion.Constants.LON_TNM
import org.river.exertion.Constants.TZ_MST

@ExperimentalUnsignedTypes
data class EarthLocation(val longitude : Double = LON_TNM
    , val latitude : Double = LAT_TNM
    , val altitude : Double = ALT_TNM.toDouble()
    , val timeZone : TimeZone = TimeZone.currentSystemDefault()
    , val utcDateTime : LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    , val timeUnknown : Boolean = false ) {

    constructor(initLongitude : Double, initLatitude : Double, initAltitude : Double, initTimezoneOffset : Double
                , initUtcDateTime : LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC)
                , initTimeUnknown : Boolean = false) :
            this(initLongitude, initLatitude, initAltitude, getTimeZoneFromOffsetInt(initTimezoneOffset.toInt()), initUtcDateTime, initTimeUnknown) {}

    constructor(initLongitude : Double, initLatitude : Double, initAltitude : Int, initTimezone : Int
                , initUtcDateTime : LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC)
                , initTimeUnknown : Boolean = false) :
            this(initLongitude, initLatitude, initAltitude.toDouble(), initTimezone.toDouble(), initUtcDateTime, initTimeUnknown) {}

    constructor(initLongitude : Double, initLatitude : Double, initAltitude : Int, initTimezone : Int
                , initUtcDate : LocalDate) :
        this(initLongitude, initLatitude, initAltitude.toDouble(), initTimezone.toDouble(), getDefaultLocalDateTime(initUtcDate), true) {}

    val localDateTime = utcDateTime.toInstant(TimeZone.UTC).toLocalDateTime(timeZone)

    fun getUTCDateTimeString() = getDateTimeString(utcDateTime)

    fun getUTCTimeString() = getTimeString(utcDateTime)

    fun getLocalTimeString() = getTimeString(localDateTime)

    fun getTimezoneOffsetString() : String = getOffsetStringDouble(this.timeZone)

    fun getTimezoneOffsetInt() : Int = getTimezoneOffsetInt(this.timeZone)

    override fun toString(): String {
        return "EarthLocation(longitude=$longitude, latitude=$latitude, altitude=$altitude, timeZone=$timeZone, utcDateTime=$utcDateTime, localDateTime=$localDateTime, timeUnknown=$timeUnknown)"
    }

    companion object {
        fun getDateTimeString(dateTime : LocalDateTime) : String {

            return dateTime.year.toString() + EntryState.DATE_ENTRY.getDelim() + dateTime.monthNumber.toString().padStart(2, '0') + EntryState.DATE_ENTRY.getDelim() + dateTime.dayOfMonth.toString().padStart(2, '0') + "@" +
                    dateTime.hour.toString().padStart(2, '0') + EntryState.TIME_ENTRY.getDelim() + dateTime.minute.toString().padStart(2, '0') + EntryState.TIME_ENTRY.getDelim() + dateTime.second.toString().padStart(2, '0') + "." + dateTime.nanosecond.toString()
        }

        fun getDateString(dateTime : LocalDateTime) : String {

            return dateTime.year.toString() + EntryState.DATE_ENTRY.getDelim() + dateTime.monthNumber.toString().padStart(2, '0') + EntryState.DATE_ENTRY.getDelim() + dateTime.dayOfMonth.toString().padStart(2, '0')
        }

        fun getTimeString(dateTime : LocalDateTime) : String {

            return dateTime.hour.toString().padStart(2, '0') + EntryState.TIME_ENTRY.getDelim() + dateTime.minute.toString().padStart(2, '0') + EntryState.TIME_ENTRY.getDelim() + dateTime.second.toString().padStart(2, '0')
        }

        fun getDefaultEarthLocation(utcDate : LocalDate) = EarthLocation(LON_TNM, LAT_TNM, ALT_TNM, TZ_MST, utcDate)

        fun getDefaultLocalDateTime(ldt : LocalDate) : LocalDateTime = LocalDateTime(ldt.year, ldt.monthNumber, ldt.dayOfMonth, 12, 0,0)

        fun getOffsetStringInt(timeZone: TimeZone) : String = getTimeZoneOffsetStringInt(getTimezoneOffsetInt(timeZone))

        fun getTimeZoneOffsetStringInt(timeZoneOffset : Int) : String = if (timeZoneOffset >= 0) "+${timeZoneOffset}" else "$timeZoneOffset"

        private fun getTimezoneOffsetInt(timeZone : TimeZone) : Int =
            if (timeZone == TimeZone.UTC) 0 else timeZone.offsetAt(Clock.System.now()).toString().split(":")[0].toInt()


        fun getTimeZoneFromOffsetInt(timeZoneOffset : Int) : TimeZone = TimeZone.of(getTimeZoneString(timeZoneOffset))

        fun getTimeZoneString(timeZoneOffset : Int) : String = if (timeZoneOffset == 0) "Z" else getTimeZoneOffsetStringInt(timeZoneOffset)


        fun getOffsetStringDouble(timeZone: TimeZone) : String = getTimeZoneOffsetStringDouble(getTimezoneOffsetInt(timeZone))

        private fun getTimeZoneOffsetStringDouble(timeZoneOffset : Int) : String = if (timeZoneOffset >= 0) "+${timeZoneOffset.toDouble()}" else "${timeZoneOffset.toDouble()}"
    }
}