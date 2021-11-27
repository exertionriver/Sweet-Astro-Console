package astro.value

import astro.base.*
import astro.state.*
import swe.DegMidp
import console.state.*
import kotlin.math.abs

@ExperimentalUnsignedTypes
class ValueChart (val chartRows: Array<ValueChartRow>, val analysisState: AnalysisState = AnalysisState.NO_ANALYSIS) {

    constructor(chartAspects : Array<ValueAspect>, analysisState: AnalysisState = AnalysisState.NO_ANALYSIS) : this (getAspectsChart(chartAspects), analysisState)

    constructor(stateChartAspects : Array<StateAspect>, chartState : ChartState, analysisState: AnalysisState = AnalysisState.NO_ANALYSIS) : this (getAspectsStateAspects(stateChartAspects, chartState, analysisState), analysisState)

    constructor(stateChart : StateChart, analysisState: AnalysisState) : this (stateChart.getStateAspects().toTypedArray(), stateChart.chartState, analysisState)

    fun getBaseValue() = Value(getValueAspects().map { it.getBaseValue().positive }.reduce { acc, modPositive -> acc + modPositive },
            getValueAspects().map { it.getBaseValue().negative }.reduce { acc, modNegative -> acc + modNegative } )

    fun getModValue() = Value(getValueAspects().map { it.getModValue().positive }.reduce { acc, modPositive -> acc + modPositive },
        getValueAspects().map { it.getModValue().negative }.reduce { acc, modNegative -> acc + modNegative } )

    //TODO: getBaseModNet()

    fun getValueAspects() : List<ValueAspect> {
        val returnList = mutableListOf<ValueAspect>()

        chartRows.forEach { returnList.addAll( it.rowAspects ) }

        return returnList.filter { it.getBaseAspect().aspectAngle != AspectAngle.ASPECT_ANGLE_NONE }.sortedBy { it.getBaseAspect().aspectCelestialSecond.ordinal }.sortedBy { it.getBaseAspect().aspectCelestialFirst.ordinal }
    }

    companion object {

        fun getEmptyChart() = ValueChart(arrayOf(ValueAspect.getEmptyAspect()) )

        fun getAspectsStateAspects(stateChartAspects : Array<StateAspect>, chartState: ChartState, analysisState: AnalysisState) : Array<ValueAspect> {

            val returnAspects : MutableList<ValueAspect> = ArrayList()

            stateChartAspects.forEach { returnAspects.add(ValueAspect(it, chartState, analysisState)) }

            return returnAspects.toTypedArray()
        }

        fun getAspectsChart(chartAspects : Array<ValueAspect>) : Array<ValueChartRow> {

            val returnAspectsChart : MutableList<ValueChartRow> = ArrayList()

            for (verticalAspectCelestial in AspectCelestial.values().filter { it.isChartAspectCelestial() }) {

                val chartRow : MutableList<ValueAspect> = mutableListOf()

                for (horizontalAspectCelestial in AspectCelestial.values().filter { it.isChartAspectCelestial() }) {

                    val chartAspect = chartAspects.firstOrNull {
                        (it.getBaseAspect().aspectCelestialFirst == verticalAspectCelestial) && (it.getBaseAspect().aspectCelestialSecond == horizontalAspectCelestial)
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