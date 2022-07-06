package org.river.exertion.profile.base

import kotlinx.datetime.LocalDateTime
import org.river.exertion.Constants.ALT_ATX
import org.river.exertion.Constants.ALT_BCS
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
import org.river.exertion.Constants.KEY_AMPERSAND
import org.river.exertion.Constants.KEY_ASTERISK
import org.river.exertion.Constants.KEY_AT
import org.river.exertion.Constants.KEY_BANG
import org.river.exertion.Constants.KEY_CARET
import org.river.exertion.Constants.KEY_CLOSEPARENS
import org.river.exertion.Constants.KEY_DOLLAR
import org.river.exertion.Constants.KEY_EQUALS
import org.river.exertion.Constants.KEY_ESC
import org.river.exertion.Constants.KEY_HASH
import org.river.exertion.Constants.KEY_OPENPARENS
import org.river.exertion.Constants.KEY_PERCENT
import org.river.exertion.Constants.KEY_PLUS
import org.river.exertion.Constants.KEY_USCORE
import org.river.exertion.Constants.LAT_ATX
import org.river.exertion.Constants.LAT_BCS
import org.river.exertion.Constants.LON_ATX
import org.river.exertion.Constants.LON_BCS
import org.river.exertion.Constants.TZ_CDT
import org.river.exertion.Constants.TZ_CST
import org.river.exertion.astro.base.CelestialSnapshot
import org.river.exertion.astro.base.EarthLocation

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
                        , EarthLocation(LON_ATX, LAT_ATX, ALT_ATX, TZ_CST
                        , LocalDateTime(1978, 11, 16, 18, 39, 0, 0) ) )
                PROFILE_2 -> Profile("emL"
                        , EarthLocation(LON_BCS, LAT_BCS, ALT_BCS, TZ_CDT
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
                KEY_1 -> PROFILE_1.ordinal
                KEY_2 -> PROFILE_2.ordinal
                KEY_3 -> PROFILE_3.ordinal
                KEY_4 -> PROFILE_4.ordinal
                KEY_5 -> PROFILE_5.ordinal
                KEY_6 -> PROFILE_6.ordinal
                KEY_7 -> PROFILE_7.ordinal
                KEY_8 -> PROFILE_8.ordinal
                KEY_9 -> PROFILE_9.ordinal
                KEY_0 -> PROFILE_0.ordinal
                KEY_ESC -> PROFILE_CUR_NAV.ordinal
                else -> prevCurProfileIdx
            }
        }

        fun getSynProfileIdx(prevSynProfileIdx : Int, input : Int) : Int {

            return when (input) {
                KEY_PLUS, KEY_EQUALS, KEY_USCORE -> PROFILE_NONE.ordinal
                KEY_ESC -> PROFILE_CUR_NAV.ordinal
                else -> prevSynProfileIdx
            }
        }

        fun getStoreCurInputProfileIdx(input: Int): Int {

            return when (input) {
                KEY_BANG -> PROFILE_1.ordinal
                KEY_AT -> PROFILE_2.ordinal
                KEY_HASH -> PROFILE_3.ordinal
                KEY_DOLLAR -> PROFILE_4.ordinal
                KEY_PERCENT -> PROFILE_5.ordinal
                KEY_CARET -> PROFILE_6.ordinal
                KEY_AMPERSAND -> PROFILE_7.ordinal
                KEY_ASTERISK -> PROFILE_8.ordinal
                KEY_OPENPARENS -> PROFILE_9.ordinal
                KEY_CLOSEPARENS -> PROFILE_0.ordinal
                else -> PROFILE_NONE.ordinal
            }
        }

    }
}
