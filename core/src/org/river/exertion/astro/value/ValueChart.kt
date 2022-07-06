package org.river.exertion.astro.value

import org.river.exertion.astro.base.AspectAngle
import org.river.exertion.astro.base.AspectCelestial
import org.river.exertion.astro.state.*
import org.river.exertion.astro.state.ChartStateType.Companion.encodeChartStateType
import org.river.exertion.astro.state.StateBaseAspect.Companion.stateBaseAspects
import kotlin.math.abs
import kotlin.math.max

@ExperimentalUnsignedTypes
class ValueChart (val chartRows: Array<ValueChartRow>, val analysisState: AnalysisState = AnalysisState.NO_ANALYSIS) {

    constructor(chartAspects : Array<ValueAspect>, analysisState: AnalysisState = AnalysisState.NO_ANALYSIS) : this (
        getAspectsChart(chartAspects), analysisState)

    constructor(stateChartAspects : Array<StateAspect>, chartState : ChartState, analysisState: AnalysisState = AnalysisState.NO_ANALYSIS) : this (
        getAspectsStateAspects(stateChartAspects, chartState, analysisState), analysisState)

    constructor(stateChart : StateChart, analysisState: AnalysisState) : this (stateChart.getStateAspects().toTypedArray(), stateChart.chartState, analysisState)

    //analysisState = CHARACTER_ANALYSIS
    constructor(chartState : ChartState, synChart : StateChart, compChart : StateChart, refNatalChart : StateChart, synNatalChart : StateChart) :
            this (getCharacterAspectsStateAspects(chartState, synChart, compChart, refNatalChart, synNatalChart), AnalysisState.CHARACTER_ANALYSIS)

    fun getBaseValue() = Value(getValueAspects().map { it.baseValue.positive }.reduce { acc, basePositive -> acc + basePositive },
            getValueAspects().map { it.baseValue.negative }.reduce { acc, baseNegative -> acc + baseNegative } )

    fun getModValue() = Value(getValueAspects().map { it.getModValue().positive }.reduce { acc, modPositive -> acc + modPositive },
        getValueAspects().map { it.getModValue().negative }.reduce { acc, modNegative -> acc + modNegative } )

    fun getValueAspects() : List<ValueAspect> {
        val returnList = mutableListOf<ValueAspect>()

        chartRows.forEach { returnList.addAll( it.rowAspects ) }

        return returnList.filter { it.stateAspect.aspectAngle != AspectAngle.ASPECT_ANGLE_NONE }.sortedBy { it.stateAspect.aspectCelestialSecond.ordinal }.sortedBy { it.stateAspect.aspectCelestialFirst.ordinal }
    }

    companion object {

        fun getEmptyChart() = ValueChart(arrayOf(ValueAspect.getEmptyAspect()))

        fun getAspectsStateAspects(
            stateChartAspects: Array<StateAspect>,
            chartState: ChartState,
            analysisState: AnalysisState
        ): Array<ValueAspect> {

            val returnAspects: MutableList<ValueAspect> = ArrayList()

            stateChartAspects.forEach { returnAspects.add(ValueAspect(it, chartState, analysisState)) }

            return returnAspects.toTypedArray()
        }

        fun getCharacterAspectsStateAspects(
            chartState: ChartState,
            synChart: StateChart,
            compChart: StateChart,
            refNatalChart: StateChart,
            synNatalChart: StateChart
        ): Array<ValueAspect> {

            if (chartState == ChartState.NATAL_CHART) return getAspectsStateAspects(
                refNatalChart.getStateAspects().toTypedArray(), chartState, AnalysisState.CHARACTER_ANALYSIS
            )

            val returnAspects: MutableList<ValueAspect> = ArrayList()

            val synAspects = synChart.getStateAspects()
            val compAspects = compChart.getStateAspects()
            val refNatalBaseAspects = refNatalChart.getStateAspects().stateBaseAspects()
            val synNatalBaseAspects = synNatalChart.getStateAspects().stateBaseAspects()

            if (chartState == ChartState.COMPOSITE_CHART) {
                val synBaseAspects = synAspects.stateBaseAspects()
                val synSharedAspects = mutableListOf<StateBaseAspect>()

                compAspects.forEach {
                    val baseAspect = it.getStateBaseAspect()

                    val commonAspectCharts = mutableListOf<ChartStateType>()

/*                    when {
                        refNatalBaseAspects.contains(baseAspect) && synBaseAspects.contains(baseAspect) -> {
                            commonAspectCharts.add(ChartStateType.REF_NATAL_CHART)
//                            commonAspectCharts.add(ChartStateType.REF_NATAL_OPP_CHART)
                            synSharedAspects.add(baseAspect)
                        }
                        synNatalBaseAspects.contains(baseAspect) && synBaseAspects.contains(baseAspect) -> {
                            commonAspectCharts.add(ChartStateType.SYN_NATAL_CHART)
//                            commonAspectCharts.add(ChartStateType.SYN_NATAL_OPP_CHART)
                            synSharedAspects.add(baseAspect)
                        }
*/
                    if (refNatalBaseAspects.contains(baseAspect)) commonAspectCharts.add(ChartStateType.REF_NATAL_CHART)
                    if (synNatalBaseAspects.contains(baseAspect)) commonAspectCharts.add(ChartStateType.SYN_NATAL_CHART)
                    if (synBaseAspects.contains(baseAspect)) commonAspectCharts.add(ChartStateType.SYNASTRY_CHART)

                    returnAspects.add(
                        ValueAspect(
                            it,
                            chartState,
                            AnalysisState.CHARACTER_ANALYSIS,
                            commonAspectCharts.encodeChartStateType()
                        )
                    )
                }

/* TODO: implement fourth chart rendering for character analysis

                synAspects.forEach {
                    val baseAspect = it.getStateBaseAspect()

                    if ( !synSharedAspects.contains(baseAspect) ) {

                        val commonAspectCharts = mutableListOf<ChartStateType>()

                        if ( refNatalBaseAspects.contains(baseAspect) ) commonAspectCharts.add(ChartStateType.REF_NATAL_OPP_CHART)
                        if ( synNatalBaseAspects.contains(baseAspect) ) commonAspectCharts.add(ChartStateType.SYN_NATAL_OPP_CHART)

                        returnAspects.add(ValueAspect(it, chartState, AnalysisState.CHARACTER_ANALYSIS, commonAspectCharts.encodeChartStateType()))
                    }
                }
*/
            } else { // (chartState == ChartState.SYNASTRY_CHART)
                val compBaseAspects = compAspects.stateBaseAspects()
                val compSharedAspects = mutableListOf<StateBaseAspect>()

                synAspects.forEach {
                    val baseAspect = it.getStateBaseAspect()

                    val commonAspectCharts = mutableListOf<ChartStateType>()

/*                    when {
                        refNatalBaseAspects.contains(baseAspect) && compBaseAspects.contains(baseAspect) -> {
                            commonAspectCharts.add(ChartStateType.REF_NATAL_CHART)
//                            commonAspectCharts.add(ChartStateType.REF_NATAL_OPP_CHART)
                            compSharedAspects.add(baseAspect)
                        }
                        synNatalBaseAspects.contains(baseAspect) && compBaseAspects.contains(baseAspect) -> {
                            commonAspectCharts.add(ChartStateType.SYN_NATAL_CHART)
//                            commonAspectCharts.add(ChartStateType.SYN_NATAL_OPP_CHART)
                            compSharedAspects.add(baseAspect)
                        }
*/
                    if (refNatalBaseAspects.contains(baseAspect)) commonAspectCharts.add(ChartStateType.REF_NATAL_CHART)
                    if (synNatalBaseAspects.contains(baseAspect)) commonAspectCharts.add(ChartStateType.SYN_NATAL_CHART)
                    if (compBaseAspects.contains(baseAspect)) commonAspectCharts.add(ChartStateType.COMPOSITE_CHART)

                    returnAspects.add(
                        ValueAspect(
                            it,
                            chartState,
                            AnalysisState.CHARACTER_ANALYSIS,
                            commonAspectCharts.encodeChartStateType()
                        )
                    )
                }

/* TODO: implement fourth chart rendering for character analysis

                compAspects.forEach {
                    val baseAspect = it.getStateBaseAspect()

                    if ( !compSharedAspects.contains(baseAspect) ) {

                        val commonAspectCharts = mutableListOf<ChartStateType>()

                        if ( refNatalBaseAspects.contains(baseAspect) ) commonAspectCharts.add(ChartStateType.REF_NATAL_OPP_CHART)
                        if ( synNatalBaseAspects.contains(baseAspect) ) commonAspectCharts.add(ChartStateType.SYN_NATAL_OPP_CHART)

                        returnAspects.add(ValueAspect(it, chartState, AnalysisState.CHARACTER_ANALYSIS, commonAspectCharts.encodeChartStateType()))
                    }
                }
*/
            }

            return returnAspects.toTypedArray()
        }

        fun getAspectsChart(chartAspects: Array<ValueAspect>): Array<ValueChartRow> {

            val returnAspectsChart: MutableList<ValueChartRow> = ArrayList()

            for (verticalAspectCelestial in AspectCelestial.values().filter { it.isChartAspectCelestial() }) {

                val chartRow: MutableList<ValueAspect> = mutableListOf()

                for (horizontalAspectCelestial in AspectCelestial.values().filter { it.isChartAspectCelestial() }) {

                    val chartAspect = chartAspects.firstOrNull {
                        (it.stateAspect.aspectCelestialFirst == verticalAspectCelestial) && (it.stateAspect.aspectCelestialSecond == horizontalAspectCelestial)
                    } ?: ValueAspect.getEmptyAspect(verticalAspectCelestial, horizontalAspectCelestial)

                    chartRow.add(horizontalAspectCelestial.ordinal, chartAspect)
                }
                returnAspectsChart.add(verticalAspectCelestial.ordinal, ValueChartRow(chartRow.toTypedArray()))
            }

            return returnAspectsChart.toTypedArray()
        }
    }
}