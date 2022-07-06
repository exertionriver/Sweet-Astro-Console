package org.river.exertion.astro.base

enum class AspectCelestial {
    ASPECT_SUN
    , ASPECT_MOON
    , ASPECT_MERCURY
    , ASPECT_VENUS
    , ASPECT_MARS
    , ASPECT_JUPITER
    , ASPECT_SATURN
    , ASPECT_URANUS
    , ASPECT_NEPTUNE
    , ASPECT_PLUTO
    , ASPECT_NORTH_NODE
    , ASPECT_CHIRON
    , ASPECT_PART_OF_FORTUNE { override fun isTimeAspect() = true }
    , ASPECT_VERTEX { override fun isTimeAspect() = true }
    , ASPECT_BLACK_MOON_LILITH
    , ASPECT_ASCENDANT { override fun isTimeAspect() = true }
    , ASPECT_MIDHEAVEN { override fun isTimeAspect() = true }
    , ASPECT_CELESTIAL_NONE
//extended aspects are not rendered on chart but can affect detail sums, as in the case of romantic aspects
    , ASPECT_SUN_MOON_MIDPOINT { override fun isExtendedAspect() = true}
    , ASPECT_FIRST_HOUSE { override fun isTimeAspect() = true; override fun isExtendedAspect() = true }
    , ASPECT_SEVENTH_HOUSE { override fun isTimeAspect() = true; override fun isExtendedAspect() = true }
    ;

    open fun isTimeAspect(): Boolean = false
    open fun isExtendedAspect() : Boolean = false

    fun isChartAspectCelestial() = ( !this.isExtendedAspect() && (this != ASPECT_CELESTIAL_NONE) )

    companion object {
        fun fromOrdinal(ordinal: Int) = values().firstOrNull { it.ordinal == ordinal }
        fun fromName(name: String) = values().firstOrNull { it.name == name }

        fun getChartSize() = values().filter { !it.isExtendedAspect() && it.isChartAspectCelestial() }.size
    }
}