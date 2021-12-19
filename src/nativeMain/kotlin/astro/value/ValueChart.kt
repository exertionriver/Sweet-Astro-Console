package astro.value

import astro.base.*
import astro.state.*
import astro.state.ChartStateType.Companion.encodeChartStateType
import astro.state.StateBaseAspect.Companion.stateBaseAspects
import swe.DegMidp
import console.state.*
import kotlin.math.abs

@ExperimentalUnsignedTypes
class ValueChart (val chartRows: Array<ValueChartRow>, val analysisState: AnalysisState = AnalysisState.NO_ANALYSIS) {

    constructor(chartAspects : Array<ValueAspect>, analysisState: AnalysisState = AnalysisState.NO_ANALYSIS) : this (getAspectsChart(chartAspects), analysisState)

    constructor(stateChartAspects : Array<StateAspect>, chartState : ChartState, analysisState: AnalysisState = AnalysisState.NO_ANALYSIS) : this (getAspectsStateAspects(stateChartAspects, chartState, analysisState), analysisState)

    constructor(stateChart : StateChart, analysisState: AnalysisState) : this (stateChart.getStateAspects().toTypedArray(), stateChart.chartState, analysisState)

    //analysisState = CHARACTER_ANALYSIS
    constructor(chartState : ChartState, synChart : StateChart, compChart : StateChart, refNatalChart : StateChart, synNatalChart : StateChart) :
            this (getCharacterAspectsStateAspects(chartState, synChart, compChart, refNatalChart, synNatalChart), AnalysisState.CHARACTER_ANALYSIS)

    fun getBaseValue() = Value(getValueAspects().map { it.getBaseValue().positive }.reduce { acc, basePositive -> acc + basePositive },
            getValueAspects().map { it.getBaseValue().negative }.reduce { acc, baseNegative -> acc + baseNegative } )

    fun getModValue() = Value(getValueAspects().map { it.getModValue().positive }.reduce { acc, modPositive -> acc + modPositive },
        getValueAspects().map { it.getModValue().negative }.reduce { acc, modNegative -> acc + modNegative } )

    fun getValueAspects() : List<ValueAspect> {
        val returnList = mutableListOf<ValueAspect>()

        chartRows.forEach { returnList.addAll( it.rowAspects ) }

        return returnList.filter { it.stateAspect.aspectAngle != AspectAngle.ASPECT_ANGLE_NONE }.sortedBy { it.stateAspect.aspectCelestialSecond.ordinal }.sortedBy { it.stateAspect.aspectCelestialFirst.ordinal }
    }

    companion object {

        fun getEmptyChart() = ValueChart(arrayOf(ValueAspect.getEmptyAspect()) )

        fun getAspectsStateAspects(stateChartAspects : Array<StateAspect>, chartState: ChartState, analysisState: AnalysisState) : Array<ValueAspect> {

            val returnAspects : MutableList<ValueAspect> = ArrayList()

            stateChartAspects.forEach { returnAspects.add(ValueAspect(it, chartState, analysisState)) }

            return returnAspects.toTypedArray()
        }

        fun getCharacterAspectsStateAspects(chartState : ChartState, synChart : StateChart, compChart : StateChart, refNatalChart : StateChart, synNatalChart : StateChart) : Array<ValueAspect> {

            if (chartState == ChartState.NATAL_CHART) return getAspectsStateAspects(refNatalChart.getStateAspects().toTypedArray(), chartState, AnalysisState.CHARACTER_ANALYSIS)

            val returnAspects : MutableList<ValueAspect> = ArrayList()

            val synAspects = synChart.getStateAspects()
            val compAspects = compChart.getStateAspects()
            val refNatalBaseAspects = refNatalChart.getStateAspects().stateBaseAspects()
            val synNatalBaseAspects = synNatalChart.getStateAspects().stateBaseAspects()

            if (chartState == ChartState.COMPOSITE_CHART) {
                val synBaseAspects = synAspects.stateBaseAspects()

                compAspects.forEach {
                    val baseAspect = it.getStateBaseAspect()

                    val commonAspectCharts = mutableListOf<ChartStateType>()

                    if ( synBaseAspects.contains(baseAspect) ) commonAspectCharts.add(ChartStateType.SYNASTRY_CHART)
                    if ( refNatalBaseAspects.contains(baseAspect) ) commonAspectCharts.add(ChartStateType.REF_NATAL_CHART)
                    if ( synNatalBaseAspects.contains(baseAspect) ) commonAspectCharts.add(ChartStateType.SYN_NATAL_CHART)

                    returnAspects.add(ValueAspect(it, chartState, AnalysisState.CHARACTER_ANALYSIS, commonAspectCharts.encodeChartStateType())) }

            } else { // (chartState == ChartState.SYNASTRY_CHART)
                val compBaseAspects = compAspects.stateBaseAspects()

                synAspects.forEach {
                    val baseAspect = it.getStateBaseAspect()

                    val commonAspectCharts = mutableListOf<ChartStateType>()

                    if ( compBaseAspects.contains(baseAspect) ) commonAspectCharts.add(ChartStateType.COMPOSITE_CHART)
                    if ( refNatalBaseAspects.contains(baseAspect) ) commonAspectCharts.add(ChartStateType.REF_NATAL_CHART)
                    if ( synNatalBaseAspects.contains(baseAspect) ) commonAspectCharts.add(ChartStateType.SYN_NATAL_CHART)

                    returnAspects.add(ValueAspect(it, chartState, AnalysisState.CHARACTER_ANALYSIS, commonAspectCharts.encodeChartStateType())) }
            }

            return returnAspects.toTypedArray()
        }

        fun getAspectsChart(chartAspects : Array<ValueAspect>) : Array<ValueChartRow> {

            val returnAspectsChart : MutableList<ValueChartRow> = ArrayList()

            for (verticalAspectCelestial in AspectCelestial.values().filter { it.isChartAspectCelestial() }) {

                val chartRow : MutableList<ValueAspect> = mutableListOf()

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
        /* keep<--
    fun getRefImprovedStim() : Double = valueChart.getModValue().getStimulation().toDouble() / refSnapshotNatalValue.stimulation.toDouble() * 100
    private fun getEvalImprovedStim() : Double = valueChart.getModValue().getStimulation().toDouble() / evalSnapshotNatalValue.stimulation.toDouble() * 100

    fun getRefImprovedPos() : Double = valueChart.positive.toDouble() / refSnapshotNatalValue.positive.toDouble() * 100
    private fun getEvalImprovedPos() : Double = valueChart.positive.toDouble() / evalSnapshotNatalValue.positive.toDouble() * 100

    fun getRefImprovedCons() : Double = valueChart.consonance.toDouble() / refSnapshotNatalValue.consonance.toDouble() * 100
    private fun getEvalImprovedCons() : Double = valueChart.consonance.toDouble() / evalSnapshotNatalValue.consonance.toDouble() * 100

    fun getRefImprovedAvg() = doubleArrayOf(getRefImprovedStim(), getRefImprovedPos(), getRefImprovedCons()).average()

    private fun getMaxStim() = max(getRefImprovedStim(), getEvalImprovedStim())
    private fun getMaxPos() = max(getRefImprovedPos(), getEvalImprovedPos())
    private fun getMaxCons() = max(getRefImprovedCons(), getEvalImprovedCons())

    private fun getRangeStim() = abs(getRefImprovedStim() - getEvalImprovedStim())
    private fun getRangePos() = abs(getRefImprovedPos() - getEvalImprovedPos())
    private fun getRangeCons() = abs(getRefImprovedCons() - getEvalImprovedCons())

    private fun getParityStimMod() = getMaxStim() / (getMaxStim() + getRangeStim())
    private fun getParityPosMod() = getMaxPos() / (getMaxPos() + getRangePos())
    private fun getParityConsMod() = getMaxCons() / (getMaxCons() + getRangeCons())

    fun getBalanceStim() = (getRefImprovedStim() + getEvalImprovedStim()) / 2
    fun getBalancePos() = (getRefImprovedPos() + getEvalImprovedPos()) / 2
    fun getBalanceCons() = (getRefImprovedCons() + getEvalImprovedCons()) / 2

    fun getRefParityStim() = (getBalanceStim() * getParityStimMod())
    fun getRefParityPos() = (getBalancePos() * getParityPosMod())
    fun getRefParityCons() = (getBalanceCons() * getParityConsMod())

    fun getRefParityAvg() = doubleArrayOf(getRefParityStim(), getRefParityPos(), getRefParityCons()).average()

    fun getWorthwhile() =
        doubleArrayOf(
            getRefParityStim(), getRefParityPos(), getRefParityCons()
            , getRefImprovedStim(), getRefImprovedPos(), getRefImprovedCons()
        ).average()
*/
    }
}