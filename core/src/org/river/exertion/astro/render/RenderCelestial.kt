package org.river.exertion.astro.render

import org.river.exertion.Constants
import org.river.exertion.Constants.LABEL_SPACE

enum class RenderCelestial {
    SUN { override fun getLabel() = Constants.SYM_SUN.plus(LABEL_SPACE) }
    , MOON { override fun getLabel() = Constants.SYM_MOON.plus(LABEL_SPACE) }
    , MERCURY { override fun getLabel() = Constants.SYM_MERCURY.plus(LABEL_SPACE) }
    , VENUS { override fun getLabel() = Constants.SYM_VENUS.plus(LABEL_SPACE) }
    , MARS { override fun getLabel() = Constants.SYM_MARS.plus(LABEL_SPACE) }
    , JUPITER { override fun getLabel() = Constants.SYM_JUPITER.plus(LABEL_SPACE) }
    , SATURN { override fun getLabel() = Constants.SYM_SATURN.plus(LABEL_SPACE) }
    , URANUS { override fun getLabel() = Constants.SYM_URANUS.plus(LABEL_SPACE) }
    , NEPTUNE { override fun getLabel() = Constants.SYM_NEPTUNE.plus(LABEL_SPACE) }
    , PLUTO { override fun getLabel() = Constants.SYM_PLUTO.plus(LABEL_SPACE) }
    , NORTH_NODE { override fun getLabel() = Constants.SYM_NORTH_NODE.plus(LABEL_SPACE) }
    , BLACK_MOON_LILITH { override fun getLabel() = Constants.SYM_BLACK_MOON_LILITH.plus(LABEL_SPACE) }
    , CHIRON { override fun getLabel() = Constants.SYM_CHIRON.plus(LABEL_SPACE) }
    , PHOLUS { override fun getLabel() = Constants.SYM_PHOLUS }
    , CERES { override fun getLabel() = Constants.SYM_CERES.plus(LABEL_SPACE) }
    , PALLAS { override fun getLabel() = Constants.SYM_PALLAS.plus(LABEL_SPACE) }
    , JUNO { override fun getLabel() = Constants.SYM_JUNO.plus(LABEL_SPACE) }
    , VESTA { override fun getLabel() = Constants.SYM_VESTA.plus(LABEL_SPACE) }
    ;

    abstract fun getLabel() : String

    companion object {
        fun fromOrdinal(ordinal: Int) = values().firstOrNull { it.ordinal == ordinal }
        fun fromName(name: String) = values().firstOrNull { it.name == name }

        fun getCelestialsLabel() = Constants.SYM_CELESTIAL
        fun getCelestialsSignLabel() = Constants.SYM_SIGN + " (" + Constants.SYM_CELESTIAL + " )"
        fun getCelestialsLongitudeLabel() = Constants.SYM_CELESTIAL_LONG + "(" + Constants.SYM_CELESTIAL + " )"
        fun getCelestialsLatitudeLabel() = Constants.SYM_CELESTIAL_LAT + "(" + Constants.SYM_CELESTIAL + " )"
        fun getCelestialsDistanceLabel() = Constants.SYM_CELESTIAL_DIST + "(" + Constants.SYM_CELESTIAL + " )"
        fun getCelestialsLongitudeSpeedLabel() = "d(" + Constants.SYM_CELESTIAL_LONG + ")"
        fun getCelestialsLatitudeSpeedLabel() = "d(" + Constants.SYM_CELESTIAL_LAT + ")"
        fun getCelestialsDistanceSpeedLabel() = "d(" + Constants.SYM_CELESTIAL_DIST + ")"
        fun getCelestialsHouseLabel() = Constants.SYM_HOUSE + "(" + Constants.SYM_CELESTIAL + " )"
        fun getCelestialsTransitHouseLabel() = Constants.KMAG + Constants.SYM_HOUSE + " " + Constants.SYM_CONTAINS + " " + Constants.KCYN + Constants.SYM_CELESTIAL + " " + Constants.KNRM
        fun getCelestialsTransitCelestialsLabel() = Constants.KCYN + Constants.SYM_CELESTIAL + " " + Constants.KMAG + Constants.SYM_EXISTS_IN + " " + Constants.SYM_HOUSE + " " + Constants.KNRM

    }
}