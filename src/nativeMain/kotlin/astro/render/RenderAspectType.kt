package astro.render

import Constants
import Constants.LABEL_SPACE

enum class RenderAspectType {
    CONJUNCTION { override fun getLabel() = Constants.SYM_CONJUNCTION.plus(LABEL_SPACE) }
    , SEXTILE { override fun getLabel() = Constants.SYM_SEXTILE.plus(LABEL_SPACE) }
    , SQUARE { override fun getLabel() = Constants.SYM_SQUARE.plus(LABEL_SPACE) }
    , TRINE { override fun getLabel() = Constants.SYM_TRINE.plus(LABEL_SPACE) }
    , OPPOSITION { override fun getLabel() = Constants.SYM_OPPOSITION.plus(LABEL_SPACE) }
    , QUINCUNX { override fun getLabel() = Constants.SYM_QUINCUNX.plus(LABEL_SPACE) }
    , SEMISEXTILE { override fun getLabel() = Constants.SYM_SEMISEXTILE.plus(LABEL_SPACE) }
    , QUINTILE { override fun getLabel() = Constants.SYM_QUINTILE.plus(LABEL_SPACE) }
    , BIQUINTILE { override fun getLabel() = Constants.SYM_BIQUINTILE }
    , SEMISQUARE { override fun getLabel() = Constants.SYM_SEMISQUARE.plus(LABEL_SPACE) }
    , SESQUISQUARE { override fun getLabel() = Constants.SYM_SESQUISQUARE.plus(LABEL_SPACE) }
    , SEPTILE { override fun getLabel() = Constants.SYM_SEPTILE }
    , NOVILE { override fun getLabel() = Constants.SYM_NOVILE }
    , ASPECT_NONE { override fun getLabel() = Constants.SYM_ASPECT_NONE.plus(LABEL_SPACE) }
    ;

    abstract fun getLabel(): String

    companion object {
        fun fromOrdinal(ordinal: Int) = values().firstOrNull { it.ordinal == ordinal }
        fun fromName(name: String) = values().firstOrNull { it.name == name }
    }
}