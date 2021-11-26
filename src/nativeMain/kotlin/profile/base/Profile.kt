package profile.base

import astro.base.CelestialSnapshot
import astro.base.EarthLocation
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@ExperimentalUnsignedTypes
data class Profile (val profileName : String
    , val earthLocation: EarthLocation
    , val isProfileBlank: Boolean = false // set to true for 'none' and empty profiles in Profiles.getDefaultProfile
    , val synEarthLocation: EarthLocation = earthLocation
    , val celestialSnapshot: CelestialSnapshot = CelestialSnapshot(earthLocation, synEarthLocation) ) {

    companion object {
        fun getMaxProfileNameLength() = 64

        fun getCopy(prevProfile: Profile): Profile {
            return Profile(prevProfile.profileName, prevProfile.earthLocation)
        }

        fun getCopyWithDateTimeEntry(prevProfile: Profile, datetime: LocalDateTime, synProfile: Profile) : Profile {
            return Profile(
                prevProfile.profileName, EarthLocation(
                    prevProfile.earthLocation.longitude,
                    prevProfile.earthLocation.latitude,
                    prevProfile.earthLocation.altitude,
                    prevProfile.earthLocation.timeZone,
                    datetime
                ), prevProfile.isProfileBlank, synProfile.earthLocation
            )
        }

        fun getCopyWithDateTimeEntry(prevProfile: Profile, datetime : LocalDateTime): Profile {
            return getCopyWithDateTimeEntry(prevProfile, datetime, prevProfile)
        }

        fun getCopyWithLongitudeEntry(prevProfile: Profile, longitude : Double): Profile {
            return Profile(
                prevProfile.profileName, EarthLocation(
                    longitude,
                    prevProfile.earthLocation.latitude,
                    prevProfile.earthLocation.altitude,
                    prevProfile.earthLocation.timeZone,
                    prevProfile.earthLocation.utcDateTime
                )
            )
        }
        fun getCopyWithLatitudeEntry(prevProfile: Profile, latitude: Double): Profile {
            return Profile(
                prevProfile.profileName, EarthLocation(
                    prevProfile.earthLocation.longitude,
                    latitude,
                    prevProfile.earthLocation.altitude,
                    prevProfile.earthLocation.timeZone,
                    prevProfile.earthLocation.utcDateTime
                )
            )
        }
        fun getCopyWithTimezoneEntry(prevProfile: Profile, timezone: Double): Profile {
            return Profile(
                prevProfile.profileName, EarthLocation(
                    prevProfile.earthLocation.longitude,
                    prevProfile.earthLocation.latitude,
                    prevProfile.earthLocation.altitude,
                    timezone,
                    prevProfile.earthLocation.utcDateTime
                )
            )
        }
        fun getCopyWithNameEntry(prevProfile: Profile, name : String): Profile {
            return Profile(name, prevProfile.earthLocation)
        }
    }
}