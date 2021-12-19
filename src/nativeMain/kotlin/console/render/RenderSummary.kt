package console.render

import astro.render.RenderChartState
import astro.render.RenderChartState.chartLabel
import astro.render.RenderChartState.impChartLabel
import astro.render.RenderValue
import astro.state.ChartState
import astro.value.ValueChart
import kotlinx.cinterop.*
import platform.posix.printf
import platform.posix.size_tVar
import platform.posix.snprintf

object RenderSummary {

    val summaryHeaderIdx = 0
    val baseCurChartSummaryIdx = 1
    val baseRefNatalChartSummaryIdx = 2
    val baseSynNatalChartSummaryIdx = 3

    val impRefNatalChartSummaryIdx = 5
    val impSynNatalChartSummaryIdx = 6

    val romCurChartSummaryIdx = 8
    val romRefNatalChartSummaryIdx = 9
    val romSynNatalChartSummaryIdx = 10

    val impRomRefNatalChartSummaryIdx = 12
    val impRomSynNatalChartSummaryIdx = 13

    fun renderSummaryData(renderIdx: Int, curChart: ValueChart, refNatalChart: ValueChart, synNatalChart: ValueChart, curChartState : ChartState) {

        printf("%s", prepareSummaryData(renderIdx, curChart, refNatalChart, synNatalChart, curChartState) )
    }

    fun prepareSummaryData(renderIdx: Int, curChart: ValueChart, refNatalChart: ValueChart, synNatalChart: ValueChart, curChartState : ChartState) : String = memScoped {

        val curSummaryColumnWidth = RenderDetails.getDetailsColumnWidth()

        val summaryDataLineSize = alloc<size_tVar>()
        summaryDataLineSize.value = 256u

        val summaryDataLine = allocArray<ByteVar>(summaryDataLineSize.value.toInt())

        var returnString = ""

        when {
            (renderIdx == summaryHeaderIdx) -> {
                snprintf(summaryDataLine, summaryDataLineSize.value, "%s"
                    , getTopSummaryColBorderShape(curSummaryColumnWidth)
                )

                returnString = summaryDataLine.toKString()
            }
            (renderIdx == baseCurChartSummaryIdx) -> {
                val baseValue = curChart.getBaseValue()

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , RenderChartState.getChartSumLabel(curChartState, Constants.KCYN).chartLabel()
                    , RenderValue(baseValue).getLabel()
                    , RenderValue(baseValue).getPercentLabel()
                    , RenderValue(baseValue).getStimLabel()
                )

                returnString = summaryDataLine.toKString().padEnd(122, ' ')
            }
            (renderIdx == baseRefNatalChartSummaryIdx) && (curChartState != ChartState.NATAL_CHART) -> {
                val refBaseValue = refNatalChart.getBaseValue()

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , RenderChartState.getChartSumLabel(ChartState.NATAL_CHART, Constants.KBLU).chartLabel()
                    , RenderValue(refBaseValue).getLabel()
                    , RenderValue(refBaseValue).getPercentLabel()
                    , RenderValue(refBaseValue).getStimLabel()
                )

                returnString = summaryDataLine.toKString().padEnd(122, ' ')
            }
            (renderIdx == baseSynNatalChartSummaryIdx) && (curChartState != ChartState.NATAL_CHART) -> {
                val synBaseValue = synNatalChart.getBaseValue()

                snprintf(summaryDataLine, summaryDataLineSize.value, "%s %s%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , RenderChartState.getChartSumLabel(ChartState.NATAL_CHART, Constants.KMAG).chartLabel()
                    , RenderValue(synBaseValue).getLabel()
                    , RenderValue(synBaseValue).getPercentLabel()
                    , RenderValue(synBaseValue).getStimLabel()
                )

                returnString = summaryDataLine.toKString().padEnd(122, ' ')
            }
        //improvement
            (renderIdx == impRefNatalChartSummaryIdx) && (curChartState != ChartState.NATAL_CHART) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value, "%s%s%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , RenderChartState.getChartSumLabel(ChartState.NATAL_CHART, Constants.KBLU).impChartLabel()
                    , RenderChartState.getChartImpLabel(curChart, refNatalChart)
                    , RenderChartState.getChartImpPercentLabel(curChart, refNatalChart)
                    , RenderChartState.getChartImpStimLabel(curChart, refNatalChart)
                )

                returnString = summaryDataLine.toKString().padEnd(110, ' ')
            }
            (renderIdx == impSynNatalChartSummaryIdx) && (curChartState != ChartState.NATAL_CHART) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value, "%s%s%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , RenderChartState.getChartSumLabel(ChartState.NATAL_CHART, Constants.KMAG).impChartLabel()
                    , RenderChartState.getChartImpLabel(curChart, synNatalChart)
                    , RenderChartState.getChartImpPercentLabel(curChart, synNatalChart)
                    , RenderChartState.getChartImpStimLabel(curChart, synNatalChart)
                )

                returnString = summaryDataLine.toKString().padEnd(110, ' ')
            }
            else -> {
                snprintf(summaryDataLine, summaryDataLineSize.value,"%s%*s"
                    , getSummaryColBorderShape(renderIdx)
                    , curSummaryColumnWidth, "")

                returnString = summaryDataLine.toKString()
            }
        }

        return returnString
    }

    fun getSummaryRowBorderShape(idx : Int) : String {

        return when {
            (idx == 1) -> Constants.BOX_DOUBLE_HORZ
            (idx % 10 == 0) -> Constants.SYM_CIRCLE_DOUBLE_ORNAMENT
            (idx % 5 == 0) -> Constants.SYM_DIAMOND_DOUBLE_ORNAMENT
            ((idx - 1) % 5 == 0) -> " "//space following idx % 5
            else -> Constants.BOX_DOUBLE_HORZ
        }
    }

    fun getTopSummaryColBorderShape(colWidth : Int) : String {
        var returnShape = Constants.KCYN + Constants.BOX_DOUBLE_CORNER_DOWN_TO_RIGHT

        for (colIdx in 1..colWidth) {
            returnShape = returnShape.plus(RenderDetails.getBorderColor(colIdx) + getSummaryRowBorderShape(colIdx))
        }

        return returnShape.plus(Constants.KNRM)
    }

    fun getSummaryColBorderShape(rowIdx : Int) : String {

        return when (rowIdx) {
            1 -> Constants.KMAG + Constants.SYM_THEREFORE + Constants.KNRM
            2 -> Constants.KCYN + Constants.BOX_DOUBLE_VERT + Constants.KNRM
            3 -> Constants.KCYN + Constants.SYM_CIRCLE_DOUBLE_ORNAMENT + Constants.KNRM
            else -> " "
        }
    }
}