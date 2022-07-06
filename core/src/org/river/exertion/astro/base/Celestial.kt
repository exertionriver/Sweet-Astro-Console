package org.river.exertion.astro.base

enum class Celestial {
    SUN
    , MOON
    , MERCURY
    , VENUS
    , MARS
    , JUPITER
    , SATURN
    , URANUS
    , NEPTUNE
    , PLUTO
    , NORTH_NODE
    , BLACK_MOON_LILITH
    , CHIRON
    , PHOLUS
    , CERES
    , PALLAS
    , JUNO
    , VESTA
    ;

    companion object {
        fun fromOrdinal(ordinal: Int) = values().firstOrNull { it.ordinal == ordinal }
        fun fromName(name: String) = values().firstOrNull { it.name == name }

        fun getTransitMax() = PLUTO
    }
}