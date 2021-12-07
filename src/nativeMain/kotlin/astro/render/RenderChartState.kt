package astro.render

import astro.state.AnalysisState
import astro.state.ChartState
import console.render.RenderState

@ExperimentalUnsignedTypes
object RenderChartState {

    fun getRenderChartSumLabel(chartState : ChartState, colorString : String) : String =
        RenderState.getNestedLabelString(
            Constants.KCYN,
            Constants.SYM_SIGMA,
            colorString,
            chartState.getLabel()
        )

    fun getSumLabelGuide() : String = (Constants.KGRN + "+" + Constants.KCYN + "." + Constants.KRED + "-  ")

    fun getRenderChartPercentLabel(chartState : ChartState, colorString : String) : String =
        RenderState.getNestedLabelString(
            Constants.KCYN,
            "%",
            colorString,
            chartState.getLabel()
        )

    fun getPercentLabelGuide() : String = (Constants.KGRN + Constants.SYM_SUNNY + Constants.KCYN + " ." + Constants.KRED + Constants.SYM_CLOUDY + "  ")

    fun getRenderChartStimLabel(chartState : ChartState, colorString : String) : String =
        RenderState.getNestedLabelString(
            Constants.KCYN,
            "|" + Constants.SYM_SIGMA,
            colorString,
            chartState.getLabel()
        ) + Constants.KCYN + "|" + Constants.KNRM

    fun getStimLabelGuide() : String = ("  " + Constants.KYEL + Constants.SYM_STIMULATION + "  ")

}