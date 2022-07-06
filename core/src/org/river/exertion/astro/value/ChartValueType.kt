package org.river.exertion.astro.value

import org.river.exertion.astro.state.ChartStateType

@ExperimentalUnsignedTypes
enum class ChartValueType {
    APPRECIATION { override fun getChartStateTypes() = listOf(ChartStateType.REF_NATAL_CHART, ChartStateType.SYN_NATAL_CHART) }
    , AFFINITY { override fun getChartStateTypes() = listOf(ChartStateType.REF_NATAL_CHART, ChartStateType.SYNASTRY_CHART) }
    , COMMONALITY { override fun getChartStateTypes() = listOf(ChartStateType.REF_NATAL_CHART, ChartStateType.COMPOSITE_CHART) }
    , COMPATIBILITY { override fun getChartStateTypes() = listOf(ChartStateType.COMPOSITE_CHART, ChartStateType.SYNASTRY_CHART) } ;

    abstract fun getChartStateTypes() : List<ChartStateType>
}