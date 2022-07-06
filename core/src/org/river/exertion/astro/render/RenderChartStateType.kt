package org.river.exertion.astro.render

import org.river.exertion.Constants
import org.river.exertion.Constants.LABEL_SPACE
import org.river.exertion.astro.state.ChartStateType
import org.river.exertion.astro.state.ChartStateType.Companion.decodeChartStateType

@ExperimentalUnsignedTypes
object RenderChartStateType {

    fun getChartStateTypesLabel(chartStateTypeEncoding : Int) : String {
        var returnString = ""

        val chartStateTypes = chartStateTypeEncoding.decodeChartStateType()

        var counter = 0

        chartStateTypes.sortedDescending().filter { it.ordinal <= ChartStateType.renderMaxIdx }.forEach {
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