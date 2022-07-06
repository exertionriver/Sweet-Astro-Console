package org.river.exertion.astro.state

import org.river.exertion.Constants.SYM_CHARACTER
import org.river.exertion.Constants.SYM_ELEMENT
import org.river.exertion.Constants.SYM_MODE
import org.river.exertion.Constants.SYM_PLANET
import org.river.exertion.Constants.SYM_ROMANTIC

enum class AnalysisState {
    NO_ANALYSIS
    , CHARACTER_ANALYSIS { override fun getLabel() = SYM_CHARACTER }
    , ROMANTIC_ANALYSIS { override fun getLabel() = SYM_ROMANTIC }
    , PLANET_ANALYSIS { override fun getLabel() = SYM_PLANET }
    , ELEMENT_ANALYSIS { override fun getLabel() = SYM_ELEMENT }
    , MODE_ANALYSIS { override fun getLabel() = SYM_MODE }
    ;

    open fun getLabel() : String = " "
}