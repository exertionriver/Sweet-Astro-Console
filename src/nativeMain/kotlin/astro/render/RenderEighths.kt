package astro.render

import Constants
import Constants.LABEL_SPACE
import astro.base.AspectCelestial

@Suppress("UNUSED")
enum class RenderEighths {
    ZERO_EIGHTHS
    , ONE_EIGHTH { override fun getLabel() = Constants.SYM_ONE_EIGHTH }
    , ONE_QUARTER { override fun getLabel() = Constants.SYM_ONE_QUARTER }
    , THREE_EIGHTHS { override fun getLabel() = Constants.SYM_THREE_EIGHTHS }
    , ONE_HALF { override fun getLabel() = Constants.SYM_ONE_HALF }
    , FIVE_EIGHTHS { override fun getLabel() = Constants.SYM_FIVE_EIGHTHS }
    , THREE_QUARTERS { override fun getLabel() = Constants.SYM_THREE_QUARTERS }
    , SEVEN_EIGHTHS { override fun getLabel() = Constants.SYM_SEVEN_EIGHTHS }
    , EIGHT_EIGHTHS
    ;

    open fun getLabel(): String = LABEL_SPACE

    companion object {
        fun fromOrdinal(ordinal: Int) = values().firstOrNull { it.ordinal == ordinal }
        fun fromName(name: String) = values().firstOrNull { it.name == name }
    }
}