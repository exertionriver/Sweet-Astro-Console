package astro.state

import Constants.LABEL_SPACE

enum class AspectsState {
    ALL_ASPECTS { override fun getLabel() = Constants.SYM_ALL_BOX.plus(LABEL_SPACE) }
    , MAJOR_ASPECTS { override fun getLabel() = Constants.SYM_MAJOR_BOX.plus(LABEL_SPACE) }
    , MINOR_ASPECTS { override fun getLabel() = Constants.SYM_MINOR_BOX.plus(LABEL_SPACE) };

    abstract fun getLabel() : String

}