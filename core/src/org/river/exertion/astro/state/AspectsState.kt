package org.river.exertion.astro.state

import org.river.exertion.Constants.LABEL_SPACE
import org.river.exertion.Constants.SYM_ALL_BOX
import org.river.exertion.Constants.SYM_MAJOR_BOX
import org.river.exertion.Constants.SYM_MINOR_BOX

enum class AspectsState {
    ALL_ASPECTS { override fun getLabel() = SYM_ALL_BOX.plus(LABEL_SPACE) }
    , MAJOR_ASPECTS { override fun getLabel() = SYM_MAJOR_BOX.plus(LABEL_SPACE) }
    , MINOR_ASPECTS { override fun getLabel() = SYM_MINOR_BOX.plus(LABEL_SPACE) };

    abstract fun getLabel() : String

}