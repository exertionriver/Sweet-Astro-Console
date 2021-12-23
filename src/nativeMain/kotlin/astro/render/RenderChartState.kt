package astro.render

import astro.render.RenderValue.Companion.valueDivider
import astro.state.ChartState
import astro.value.Value
import astro.value.ValueChart
import console.render.RenderState
import kotlin.math.abs

@ExperimentalUnsignedTypes
object RenderChartState {

    fun String.chartLabel() : String = this + Constants.KNRM + ":"
    fun String.impChartLabel() : String = Constants.KBYEL + Constants.SYM_IMPROVEMENT + this + Constants.KNRM + ":"
    fun String.romChartLabel() : String = Constants.KBRED + Constants.SYM_ROMANTIC + this + Constants.KNRM + ":"
    fun String.impRomChartLabel() : String = Constants.KBYEL + Constants.SYM_IMPROVEMENT + Constants.KBRED + Constants.SYM_ROMANTIC + this + Constants.KNRM + ":"

    fun getChartSumLabel(chartState : ChartState, colorString : String) : String =
        RenderState.getNestedLabelString(
            Constants.KCYN,
            Constants.SYM_SIGMA,
            colorString,
            chartState.getLabel()
        )

    fun getChartImpLabel(sharedChartValue : Value, natalChartValue : Value) : String {
        val pos = sharedChartValue.positive - natalChartValue.positive
        val neg = -1 * (sharedChartValue.negative - natalChartValue.negative)
        val posLabel = ( if (pos > 0) Constants.KGRN else if (pos < 0) Constants.KRED else Constants.KNRM ) + abs(pos).toString().padStart(4, ' ')
        val negLabel = ( if (neg > 0) Constants.KRED else if (neg < 0) Constants.KGRN else Constants.KNRM ) + abs(neg).toString().padStart(4, ' ')

        return posLabel.valueDivider().plus(negLabel)
    }

    fun getChartImpPercentLabel(sharedChartValue : Value, natalChartValue : Value) : String {
        val consPosChange = (sharedChartValue.positive > natalChartValue.positive)
        val consNegChange = (sharedChartValue.positive < natalChartValue.positive)
        val dissPosChange = (sharedChartValue.negative > natalChartValue.negative)
        val dissNegChange = (sharedChartValue.negative < natalChartValue.negative)

        val cons = if (consPosChange)
                (100 * sharedChartValue.positive.toDouble() / natalChartValue.positive.toDouble()).toInt() - 100
            else if (consNegChange)
                (100 * natalChartValue.positive.toDouble() / sharedChartValue.positive.toDouble()).toInt() - 100
            else 0
        val diss = if (dissPosChange)
                (100 * abs(natalChartValue.negative.toDouble()) / abs(sharedChartValue.negative.toDouble())).toInt() - 100
            else if (dissNegChange)
                (100 * abs(sharedChartValue.negative.toDouble()) / abs(natalChartValue.negative.toDouble())).toInt() - 100
            else 0
        val consLabel = ( if (consPosChange) Constants.KGRN else if (consNegChange) Constants.KRED else Constants.KNRM ) + cons.toString().padStart(2, ' ')
        val dissLabel = ( if (dissPosChange) Constants.KGRN else if (dissNegChange) Constants.KRED else Constants.KNRM ) + diss.toString().padStart(2, ' ')

        return consLabel.valueDivider().plus(dissLabel)
    }

    fun getChartImpStimLabel(sharedChartValue : Value, natalChartValue : Value) : String {

        val impStim = sharedChartValue.getStimulation() - natalChartValue.getStimulation()
        val impStimLabel = ( if (impStim > 0) Constants.KGRN else if (impStim < 0) Constants.KRED else Constants.KNRM ) + abs(impStim).toString().padStart(4, ' ')

        return impStimLabel + Constants.KNRM
    }
}