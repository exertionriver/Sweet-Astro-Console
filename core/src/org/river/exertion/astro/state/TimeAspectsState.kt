package org.river.exertion.astro.state

import org.river.exertion.Constants.LABEL_SPACE
import org.river.exertion.Constants.SYM_FULL_HGLASS
import org.river.exertion.Constants.SYM_NEGATION

enum class TimeAspectsState {
    TIME_ASPECTS_ENABLED { override fun getLabel() = LABEL_SPACE.plus(SYM_FULL_HGLASS) }
    , TIME_ASPECTS_DISABLED { override fun getLabel() = SYM_NEGATION + SYM_FULL_HGLASS } ;

    abstract fun getLabel(): String
}