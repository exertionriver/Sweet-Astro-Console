package astro.state

import Constants.LABEL_SPACE

@ExperimentalUnsignedTypes
enum class ChartState {
    NATAL_CHART
    , COMPOSITE_CHART { override fun getOperatorLabel() = "=" }
    , SYNASTRY_CHART { override fun getLabel() = Constants.SYM_SYNASTRY_CHART.plus(LABEL_SPACE) ; override fun getOperatorLabel() = "+"; override fun isNatComp() = false } ;

    open fun getLabel() : String = Constants.SYM_NATCOMP_CHART.plus(LABEL_SPACE)
    open fun getOperatorLabel() : String = ""
    open fun isNatComp() : Boolean = true
}