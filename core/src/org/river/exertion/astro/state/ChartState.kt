package org.river.exertion.astro.state

import org.river.exertion.Constants.LABEL_SPACE
import org.river.exertion.Constants.SYM_NATCOMP_CHART
import org.river.exertion.Constants.SYM_SYNASTRY_CHART

enum class ChartState {
    NATAL_CHART
    , COMPOSITE_CHART { override fun getOperatorLabel() = "=" }
    , SYNASTRY_CHART { override fun getLabel() = SYM_SYNASTRY_CHART.plus(LABEL_SPACE) ; override fun getOperatorLabel() = "+"; override fun isNatComp() = false } ;

    open fun getLabel() : String = SYM_NATCOMP_CHART.plus(LABEL_SPACE)
    open fun getOperatorLabel() : String = ""
    open fun isNatComp() : Boolean = true
}