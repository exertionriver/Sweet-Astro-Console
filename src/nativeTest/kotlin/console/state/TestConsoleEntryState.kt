package console.state

import astro.base.EarthLocation.Companion.getTimeZoneFromOffsetInt
import astro.base.EarthLocation.Companion.getTimeZoneOffsetStringInt
import astro.state.EntryState
import astro.state.NavDirState
import astro.state.NavState
import console.render.RenderCelestialHouses
import console.render.RenderHandler
import console.render.RenderSummary
import console.state.ConsoleEntryState
import console.state.ConsoleEntryState.getCurEntryProfile
import console.state.ConsoleNavState.getCurNavTime
import kotlinx.datetime.LocalDate
import profile.base.Profile
import profile.base.Profiles
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.ExperimentalTime

object TestConsoleEntryState {

    @Test
    fun testConsoleEntryNoEntry() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val outProfile = EntryState.NO_ENTRY.getCurEntryProfile(refProfile)

        assertEquals(outProfile, refProfile)
    }

    @Test
    fun testConsoleEntryDateEntry() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val outProfile = EntryState.DATE_ENTRY.getCurEntryProfile(refProfile, "2021.02.21")

        assertEquals(LocalDate(1978, 11, 16), refProfile.earthLocation.utcDateTime.date)
        assertEquals(LocalDate(2021, 2, 21), outProfile.earthLocation.utcDateTime.date)
    }

    @Test
    fun testConsoleEntryTimeEntry() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val outProfile = EntryState.TIME_ENTRY.getCurEntryProfile(refProfile, "12:34:56")

        assertEquals(18, refProfile.earthLocation.utcDateTime.hour)
        assertEquals(39, refProfile.earthLocation.utcDateTime.minute)
        assertEquals(0, refProfile.earthLocation.utcDateTime.second)
        assertEquals(12, outProfile.earthLocation.utcDateTime.hour)
        assertEquals(34, outProfile.earthLocation.utcDateTime.minute)
        assertEquals(56, outProfile.earthLocation.utcDateTime.second)
    }

    @Test
    fun testConsoleEntryTimeEntryTextFail() {
        val textEntry = "abcds"

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val outProfile = EntryState.TIME_ENTRY.getCurEntryProfile(refProfile, textEntry)
        assertEquals(18, refProfile.earthLocation.utcDateTime.hour)
        assertEquals(39, refProfile.earthLocation.utcDateTime.minute)
        assertEquals(0, refProfile.earthLocation.utcDateTime.second)
        assertEquals(18, outProfile.earthLocation.utcDateTime.hour)
        assertEquals(39, outProfile.earthLocation.utcDateTime.minute)
        assertEquals(0, outProfile.earthLocation.utcDateTime.second)


    }

    @Test
    fun testConsoleEntryLongitude() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        var outProfile = EntryState.LONG_ENTRY.getCurEntryProfile(refProfile, "12.3456")

        assertEquals(Constants.LON_ATX, refProfile.earthLocation.longitude)
        assertEquals(12.3456, outProfile.earthLocation.longitude)

        outProfile = EntryState.LONG_ENTRY.getCurEntryProfile(refProfile, "24.0")
        assertEquals(24.0, outProfile.earthLocation.longitude)
    }

    @Test
    fun testConsoleEntryLatitude() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        var outProfile = EntryState.LAT_ENTRY.getCurEntryProfile(refProfile, "-34.5678")

        assertEquals(Constants.LAT_ATX, refProfile.earthLocation.latitude)
        assertEquals(-34.5678, outProfile.earthLocation.latitude)

        outProfile = EntryState.LAT_ENTRY.getCurEntryProfile(refProfile, "-48.0")
        assertEquals(-48.0, outProfile.earthLocation.latitude)
    }

    @Test
    fun testConsoleEntryTimezone() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        var outProfile = EntryState.TZ_ENTRY.getCurEntryProfile(refProfile, "-2")

        assertEquals(getTimeZoneFromOffsetInt(Constants.TZ_CST), refProfile.earthLocation.timeZone)
        assertEquals(getTimeZoneFromOffsetInt(-2), outProfile.earthLocation.timeZone)

        outProfile = EntryState.TZ_ENTRY.getCurEntryProfile(refProfile, "7")
        assertEquals(getTimeZoneFromOffsetInt(7), outProfile.earthLocation.timeZone)
    }

    @Test
    fun testConsoleEntryTimezoneTextFail() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        var outProfile = EntryState.TZ_ENTRY.getCurEntryProfile(refProfile, "abcds")

        assertEquals(getTimeZoneFromOffsetInt(Constants.TZ_CST), refProfile.earthLocation.timeZone)
        assertEquals(getTimeZoneFromOffsetInt(Constants.TZ_CST), outProfile.earthLocation.timeZone)
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun testConsoleTzUpdate() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        var outProfile = EntryState.TZ_ENTRY.getCurEntryProfile(refProfile, "-2")

        assertEquals(getTimeZoneFromOffsetInt(-2), outProfile.earthLocation.timeZone)

        val curUTCTime = NavState.NAV_PAUSED.getCurNavTime(NavDirState.NAV_FORWARD, outProfile.earthLocation.utcDateTime)
        println ("$curUTCTime")

        println ("${Profile.getCopyWithDateTimeEntry(Profiles.getDefaultProfile(Profiles.PROFILE_CUR_NAV), curUTCTime, Profiles.getDefaultProfile(Profiles.PROFILE_CUR_NAV))}")

        println ("${Profile.getCopyWithDateTimeEntry(outProfile, curUTCTime, Profiles.getDefaultProfile(Profiles.PROFILE_CUR_NAV))}")
    }
}