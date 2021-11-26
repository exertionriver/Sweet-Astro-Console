package astro.state

import Constants.LABEL_SPACE

enum class TimeAspectsState {
    TIME_ASPECTS_ENABLED { override fun getLabel() = LABEL_SPACE.plus(Constants.SYM_FULL_HGLASS) }
    , TIME_ASPECTS_DISABLED { override fun getLabel() = Constants.SYM_NEGATION + Constants.SYM_FULL_HGLASS } ;

    abstract fun getLabel(): String
}