package profile.base

import Constants
import astro.base.CelestialSnapshot
import astro.base.EarthLocation
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

@ExperimentalUnsignedTypes
enum class Profiles {
    PROFILE_0, PROFILE_1, PROFILE_2, PROFILE_3, PROFILE_4, PROFILE_5, PROFILE_6, PROFILE_7, PROFILE_8, PROFILE_9
    , PROFILE_CUR_NAV {override fun getDefaultName() = "CUR_NAV"}, PROFILE_NONE {override fun getDefaultName() = "?"} ;

    open fun getDefaultName() : String = "<empty>"

    companion object {
        fun fromOrdinal(ordinal: Int) = values().firstOrNull { it.ordinal == ordinal }

        fun isCurNavProfile(profileIdx: Int) = (profileIdx == PROFILE_CUR_NAV.ordinal)

        fun isNoneProfile(profileIdx: Int) = (profileIdx == PROFILE_NONE.ordinal)

        fun isStoredProfile(profileIdx : Int) = ( !isCurNavProfile(profileIdx) && !isNoneProfile(profileIdx) )

        fun getDefaultProfile(profile : Profiles) : Profile {

            return when (profile) {
                PROFILE_1 -> Profile("exR"
                        , EarthLocation(Constants.LON_ATX, Constants.LAT_ATX, Constants.ALT_ATX, Constants.TZ_CST
                        , LocalDateTime(1978, 11, 16, 18, 39, 0, 0) ) )
                PROFILE_2 -> Profile("emL"
                        , EarthLocation(Constants.LON_BCS, Constants.LAT_BCS, Constants.ALT_BCS, Constants.TZ_CDT
                        , LocalDateTime(1981, 7, 21, 15, 39, 0, 0) ) )
                PROFILE_CUR_NAV -> Profile(profile.getDefaultName(), EarthLocation() )
                PROFILE_NONE -> Profile(profile.getDefaultName(), EarthLocation(), true )
                else -> Profile(profile.getDefaultName(), EarthLocation() )
            }
        }

        fun getDefaultProfiles() : Array<Profile> {

            return Array(values().size) { profileIdx: Int -> getDefaultProfile(fromOrdinal(profileIdx)!!) }

        }

        fun getCompositeProfile(firstProfile : Profile, secondProfile : Profile) : Profile {

            return Profile(firstProfile.profileName + "-" + secondProfile.profileName
                    , firstProfile.earthLocation, false, secondProfile.earthLocation
                    , CelestialSnapshot.getCompositeSnapshot(firstProfile.celestialSnapshot, secondProfile.celestialSnapshot
                        , firstProfile.earthLocation, secondProfile.earthLocation)
                    )

        }

        fun getCurProfileIdx(prevCurProfileIdx : Int, input: Int): Int {

            return when (input) {
                Constants.KEY_1 -> PROFILE_1.ordinal
                Constants.KEY_2 -> PROFILE_2.ordinal
                Constants.KEY_3 -> PROFILE_3.ordinal
                Constants.KEY_4 -> PROFILE_4.ordinal
                Constants.KEY_5 -> PROFILE_5.ordinal
                Constants.KEY_6 -> PROFILE_6.ordinal
                Constants.KEY_7 -> PROFILE_7.ordinal
                Constants.KEY_8 -> PROFILE_8.ordinal
                Constants.KEY_9 -> PROFILE_9.ordinal
                Constants.KEY_0 -> PROFILE_0.ordinal
                Constants.KEY_ESC -> PROFILE_CUR_NAV.ordinal
                else -> prevCurProfileIdx
            }
        }

        fun getSynProfileIdx(prevSynProfileIdx : Int, input : Int) : Int {

            return when (input) {
                Constants.KEY_PLUS, Constants.KEY_EQUALS, Constants.KEY_USCORE -> PROFILE_NONE.ordinal
                Constants.KEY_ESC -> PROFILE_CUR_NAV.ordinal
                else -> prevSynProfileIdx
            }
        }

        fun getStoreCurInputProfileIdx(input: Int): Int {

            return when (input) {
                Constants.KEY_BANG -> PROFILE_1.ordinal
                Constants.KEY_AT -> PROFILE_2.ordinal
                Constants.KEY_HASH -> PROFILE_3.ordinal
                Constants.KEY_DOLLAR -> PROFILE_4.ordinal
                Constants.KEY_PERCENT -> PROFILE_5.ordinal
                Constants.KEY_CARET -> PROFILE_6.ordinal
                Constants.KEY_AMPERSAND -> PROFILE_7.ordinal
                Constants.KEY_ASTERISK -> PROFILE_8.ordinal
                Constants.KEY_OPENPARENS -> PROFILE_9.ordinal
                Constants.KEY_CLOSEPARENS -> PROFILE_0.ordinal
                else -> PROFILE_NONE.ordinal
            }
        }

    }
}
