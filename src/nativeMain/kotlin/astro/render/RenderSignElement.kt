package astro.render

import Constants

enum class RenderSignElement {
    FIRE_ELEMENT { override fun getLabel() = Constants.SYM_FIRE_ELEMENT }
    , EARTH_ELEMENT { override fun getLabel() = Constants.SYM_EARTH_ELEMENT }
    , AIR_ELEMENT { override fun getLabel() = Constants.SYM_AIR_ELEMENT }
    , WATER_ELEMENT { override fun getLabel() = Constants.SYM_WATER_ELEMENT }
    , NONE_ELEMENT { override fun getLabel() = Constants.SYM_ASPECT_NONE }
    ;

    abstract fun getLabel(): String

    companion object {
        fun fromOrdinal(ordinal: Int) = values().firstOrNull { it.ordinal == ordinal }
        fun fromName(name: String) = values().firstOrNull { it.name == name }
    }
}