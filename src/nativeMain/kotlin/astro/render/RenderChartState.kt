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

    fun getRenderChartPercentageLabel(colorString : String) : String =
        RenderState.getNestedLabelString(Constants.KCYN, "%", colorString, Constants.SYM_SIGMA)

    fun getRenderChartSumRomLabel(chartState : ChartState, colorString : String) : String =
        RenderState.getNestedLabelString(
            Constants.KCYN,
            AnalysisState.ROMANTIC_ANALYSIS.getLabel(),
            colorString,
            chartState.getLabel()
        )

    fun getRenderChartPercentageRomLabel(colorString : String) : String =
        RenderState.getNestedLabelString(
            Constants.KCYN,
            "%",
            colorString,
            AnalysisState.ROMANTIC_ANALYSIS.getLabel()
        )
}