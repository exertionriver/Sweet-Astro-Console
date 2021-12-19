package astro.render

import Constants.LABEL_SPACE
import astro.render.RenderValue.Companion.valueDivider
import astro.state.AnalysisState
import astro.state.ChartState
import astro.state.ChartStateType
import astro.state.ChartStateType.Companion.decodeChartStateType
import astro.value.ValueChart
import console.render.RenderState
import kotlin.math.abs

@ExperimentalUnsignedTypes
object RenderChartStateType {

    fun getChartStateTypesLabel(chartStateTypeEncoding : Int) : String {
        var returnString = ""

        val chartStateTypes = chartStateTypeEncoding.decodeChartStateType()

        var counter = 0

        chartStateTypes.sortedDescending().forEach {
            returnString += when (it) {
                ChartStateType.REF_NATAL_CHART -> Constants.KBLU
                ChartStateType.SYN_NATAL_CHART -> Constants.KMAG
                else -> Constants.KCYN
            }

            returnString += it.getChartState().getLabel() + Constants.KNRM

            counter++
        }

        (counter..2).forEach {
            returnString += Constants.KNRM + LABEL_SPACE + LABEL_SPACE
        }

        return returnString
    }
}