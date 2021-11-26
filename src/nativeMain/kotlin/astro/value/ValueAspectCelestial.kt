package astro.value

enum class ValueAspectCelestial {
    ASPECT_SUN { override fun getWeight() = (10).toDouble() }
    , ASPECT_MOON { override fun getWeight() = (10).toDouble() }
    , ASPECT_MERCURY { override fun getWeight() = (2.9).toDouble() }
    , ASPECT_VENUS { override fun getWeight() = (4.8).toDouble() }
    , ASPECT_MARS { override fun getWeight() = (3.5).toDouble() }
    , ASPECT_JUPITER { override fun getWeight() = (4.9).toDouble() }
    , ASPECT_SATURN { override fun getWeight() = (3.8).toDouble() }
    , ASPECT_URANUS { override fun getWeight() = (1.7).toDouble() }
    , ASPECT_NEPTUNE { override fun getWeight() = (1.1).toDouble() }
    , ASPECT_PLUTO { override fun getWeight() = (0.3).toDouble() }
    , ASPECT_NORTH_NODE { override fun getWeight() = (2.5).toDouble() }
    , ASPECT_CHIRON { override fun getWeight() = (0.3).toDouble() }
    , ASPECT_PART_OF_FORTUNE { override fun getWeight() = (0.75).toDouble() }
    , ASPECT_VERTEX { override fun getWeight() = (2.5).toDouble() }
    , ASPECT_BLACK_MOON_LILITH { override fun getWeight() = (2.5).toDouble() }
    , ASPECT_ASCENDANT { override fun getWeight() = (4).toDouble() }
    , ASPECT_MIDHEAVEN { override fun getWeight() = (4).toDouble() }
    , ASPECT_CELESTIAL_NONE
//extended aspects are not rendered on chart but can affect detail sums, as in the case of romantic aspects
    , ASPECT_SUN_MOON_MIDPOINT { override fun getWeight() = (10).toDouble() }
    , ASPECT_FIRST_HOUSE { override fun getWeight() = (4).toDouble() }
    , ASPECT_SEVENTH_HOUSE { override fun getWeight() = (4).toDouble() }
    ;

    //for weights: https://en.wikipedia.org/wiki/Angular_diameter
    //ln(avg(angular diameter)) normalized to sun = 10
    //NN through MC estimated
    //used to calculate aspect values
    open fun getWeight() : Double = 0.0 //TODO: use swe function to return apparent size

    companion object {
        fun fromOrdinal(ordinal: Int) = values().firstOrNull { it.ordinal == ordinal }
        fun fromName(name: String) = values().firstOrNull { it.name == name }
    }
}