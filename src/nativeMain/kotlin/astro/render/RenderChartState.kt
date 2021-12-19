package astro.render

import astro.render.RenderValue.Companion.valueDivider
import astro.state.ChartState
import astro.value.ValueChart
import console.render.RenderState
import kotlin.math.abs

@ExperimentalUnsignedTypes
object RenderChartState {

    fun String.chartLabel() : String = this + Constants.KNRM + ":"
    fun String.impChartLabel() : String = Constants.KBYEL + Constants.SYM_IMPROVEMENT + this + Constants.KNRM + ":"

    fun getChartSumLabel(chartState : ChartState, colorString : String) : String =
        RenderState.getNestedLabelString(
            Constants.KCYN,
            Constants.SYM_SIGMA,
            colorString,
            chartState.getLabel()
        )

    fun getChartImpLabel(sharedChart : ValueChart, natalChart : ValueChart) : String {
        val pos = sharedChart.getBaseValue().positive - natalChart.getBaseValue().positive
        val neg = -1 * (sharedChart.getBaseValue().negative - natalChart.getBaseValue().negative)
        val posLabel = ( if (pos > 0) Constants.KGRN else if (pos < 0) Constants.KRED else Constants.KNRM ) + abs(pos).toString().padStart(4, ' ')
        val negLabel = ( if (neg > 0) Constants.KRED else if (neg < 0) Constants.KGRN else Constants.KNRM ) + abs(neg).toString().padStart(4, ' ')

        return posLabel.valueDivider().plus(negLabel)
    }

    fun getChartImpPercentLabel(sharedChart : ValueChart, natalChart : ValueChart) : String {
        val cons = (100 * sharedChart.getBaseValue().positive.toDouble() / natalChart.getBaseValue().positive.toDouble()).toInt()
        val diss = (100 * sharedChart.getBaseValue().negative.toDouble() / natalChart.getBaseValue().negative.toDouble()).toInt()
        val consLabel = ( if (cons > 100) Constants.KGRN else if (cons < 100) Constants.KRED else Constants.KNRM ) + abs(100 - cons).toString().padStart(2, ' ')
        val dissLabel = ( if (diss > 100) Constants.KRED else if (diss < 100) Constants.KGRN else Constants.KNRM ) + abs(100 - diss).toString().padStart(2, ' ')

        return consLabel.valueDivider().plus(dissLabel)
    }

    fun getChartImpStimLabel(sharedChart : ValueChart, natalChart : ValueChart) : String {

        val impStim = sharedChart.getBaseValue().getStimulation() - natalChart.getBaseValue().getStimulation()
        val impStimLabel = ( if (impStim > 0) Constants.KGRN else if (impStim < 0) Constants.KRED else Constants.KNRM ) + abs(impStim).toString().padStart(4, ' ')

        return impStimLabel + Constants.KNRM
    }
}