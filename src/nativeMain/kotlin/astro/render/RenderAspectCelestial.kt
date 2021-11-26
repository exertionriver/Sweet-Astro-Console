package astro.render

enum class RenderAspectCelestial {
    ASPECT_SUN { override fun getLabel() = RenderCelestial.SUN.getLabel() }
    , ASPECT_MOON { override fun getLabel() = RenderCelestial.MOON.getLabel() }
    , ASPECT_MERCURY { override fun getLabel() = RenderCelestial.MERCURY.getLabel() }
    , ASPECT_VENUS { override fun getLabel() = RenderCelestial.VENUS.getLabel() }
    , ASPECT_MARS { override fun getLabel() = RenderCelestial.MARS.getLabel() }
    , ASPECT_JUPITER { override fun getLabel() = RenderCelestial.JUPITER.getLabel() }
    , ASPECT_SATURN { override fun getLabel() = RenderCelestial.SATURN.getLabel() }
    , ASPECT_URANUS { override fun getLabel() = RenderCelestial.URANUS.getLabel() }
    , ASPECT_NEPTUNE { override fun getLabel() = RenderCelestial.NEPTUNE.getLabel() }
    , ASPECT_PLUTO { override fun getLabel() = RenderCelestial.PLUTO.getLabel() }
    , ASPECT_NORTH_NODE { override fun getLabel() = RenderCelestial.NORTH_NODE.getLabel() }
    , ASPECT_CHIRON { override fun getLabel() = RenderCelestial.CHIRON.getLabel() }
    , ASPECT_PART_OF_FORTUNE { override fun getLabel() = RenderCelestialHouse.getPartOfFortuneLabel() }
    , ASPECT_VERTEX { override fun getLabel() = RenderCelestialHouse.VERTEX.getLabel() }
    , ASPECT_BLACK_MOON_LILITH { override fun getLabel() = RenderCelestial.BLACK_MOON_LILITH.getLabel() }
    , ASPECT_ASCENDANT { override fun getLabel() = RenderCelestialHouse.HOUSE_1_ASC.getLabel() }
    , ASPECT_MIDHEAVEN { override fun getLabel() = RenderCelestialHouse.HOUSE_10_MC.getLabel() }
    , ASPECT_CELESTIAL_NONE
//extended aspects are not rendered on chart but can affect detail sums, as in the case of romantic aspects
    , ASPECT_SUN_MOON_MIDPOINT { override fun getLabel() = Constants.SYM_SUN + "/" + Constants.SYM_MOON }
    , ASPECT_FIRST_HOUSE { override fun getLabel() = Constants.SYM_HOUSE_1 + " " }
    , ASPECT_SEVENTH_HOUSE { override fun getLabel() = RenderCelestialHouse.HOUSE_7.getLabel() }
    ;

    open fun getLabel(): String = ""

    fun getHeaderLabel() : String = Constants.KCYN + getLabel() + Constants.KNRM
    fun getSynHeaderLabel() : String = Constants.KBLU + getLabel() + Constants.KNRM
    fun getSynLabel() : String = Constants.KMAG + getLabel() + Constants.KNRM

    companion object {
        fun fromOrdinal(ordinal: Int) = values().firstOrNull { it.ordinal == ordinal }
        fun fromName(name: String) = values().firstOrNull { it.name == name }
    }

}