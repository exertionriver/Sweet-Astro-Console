package console.render

import astro.render.RenderChartState
import astro.render.RenderChartState.chartLabel
import astro.render.RenderChartState.impChartLabel
import astro.render.RenderChartState.impRomChartLabel
import astro.render.RenderChartState.romChartLabel
import astro.render.RenderValue
import astro.state.AnalysisState
import astro.state.ChartState
import astro.value.Value
import astro.value.ValueAspect
import astro.value.ValueAspect.Companion.valueAspectReduceBaseModNet
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

    val characterNatalsIdx = 8
    val characterCompositeIdx = 9
    val characterSynastryIdx = 10
    val characterCompSynIdx = 11

    val renderMaxIdx = 19

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

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s  %s%s %s %s"
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

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s  %s%s %s %s"
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

                snprintf(summaryDataLine, summaryDataLineSize.value, "%s  %s%s %s %s"
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

                snprintf(summaryDataLine, summaryDataLineSize.value, "%s %s%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , RenderChartState.getChartSumLabel(ChartState.NATAL_CHART, Constants.KBLU).impChartLabel()
                    , RenderChartState.getChartImpLabel(curChart.getBaseValue(), refNatalChart.getBaseValue())
                    , RenderChartState.getChartImpPercentLabel(curChart.getBaseValue(), refNatalChart.getBaseValue())
                    , RenderChartState.getChartImpStimLabel(curChart.getBaseValue(), refNatalChart.getBaseValue())
                )

                returnString = summaryDataLine.toKString().padEnd(110, ' ')
            }
            (renderIdx == impSynNatalChartSummaryIdx) && (curChartState != ChartState.NATAL_CHART) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value, "%s %s%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , RenderChartState.getChartSumLabel(ChartState.NATAL_CHART, Constants.KMAG).impChartLabel()
                    , RenderChartState.getChartImpLabel(curChart.getBaseValue(), synNatalChart.getBaseValue())
                    , RenderChartState.getChartImpPercentLabel(curChart.getBaseValue(), synNatalChart.getBaseValue())
                    , RenderChartState.getChartImpStimLabel(curChart.getBaseValue(), synNatalChart.getBaseValue())
                )

                returnString = summaryDataLine.toKString().padEnd(110, ' ')
            }

            (renderIdx == romCurChartSummaryIdx) && (curChart.analysisState == AnalysisState.ROMANTIC_ANALYSIS) -> {
                val romValue = curChart.getValueAspects().valueAspectReduceBaseModNet()

                snprintf(summaryDataLine, summaryDataLineSize.value, "%s %s%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , RenderChartState.getChartSumLabel(ChartState.NATAL_CHART, Constants.KCYN).romChartLabel()
                    , RenderValue(romValue).getLabel()
                    , RenderValue(romValue).getPercentLabel()
                    , RenderValue(romValue).getStimLabel()

                )

                returnString = summaryDataLine.toKString().padEnd(118, ' ')
            }

            (renderIdx == romRefNatalChartSummaryIdx) && (curChart.analysisState == AnalysisState.ROMANTIC_ANALYSIS) && (curChartState != ChartState.NATAL_CHART) -> {
                val refRomValue = refNatalChart.getValueAspects().valueAspectReduceBaseModNet()

                snprintf(summaryDataLine, summaryDataLineSize.value, "%s %s%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , RenderChartState.getChartSumLabel(ChartState.NATAL_CHART, Constants.KBLU).romChartLabel()
                    , RenderValue(refRomValue).getLabel()
                    , RenderValue(refRomValue).getPercentLabel()
                    , RenderValue(refRomValue).getStimLabel()

                )

                returnString = summaryDataLine.toKString().padEnd(118, ' ')
            }

            (renderIdx == romSynNatalChartSummaryIdx) && (curChart.analysisState == AnalysisState.ROMANTIC_ANALYSIS) && (curChartState != ChartState.NATAL_CHART) -> {
                val synRomValue = synNatalChart.getValueAspects().valueAspectReduceBaseModNet()

                snprintf(summaryDataLine, summaryDataLineSize.value, "%s %s%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , RenderChartState.getChartSumLabel(ChartState.NATAL_CHART, Constants.KMAG).romChartLabel()
                    , RenderValue(synRomValue).getLabel()
                    , RenderValue(synRomValue).getPercentLabel()
                    , RenderValue(synRomValue).getStimLabel()
                )

                returnString = summaryDataLine.toKString().padEnd(118, ' ')
            }

            (renderIdx == impRomRefNatalChartSummaryIdx) && (curChart.analysisState == AnalysisState.ROMANTIC_ANALYSIS) && (curChartState != ChartState.NATAL_CHART) -> {
                val romValue = curChart.getValueAspects().valueAspectReduceBaseModNet()
                val refRomValue = refNatalChart.getValueAspects().valueAspectReduceBaseModNet()

                snprintf(summaryDataLine, summaryDataLineSize.value, "%s%s%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , RenderChartState.getChartSumLabel(ChartState.NATAL_CHART, Constants.KBLU).impRomChartLabel()
                    , RenderChartState.getChartImpLabel(romValue, refRomValue)
                    , RenderChartState.getChartImpPercentLabel(romValue, refRomValue)
                    , RenderChartState.getChartImpStimLabel(romValue, refRomValue)
                )

                returnString = summaryDataLine.toKString().padEnd(115, ' ')
            }

            (renderIdx == impRomSynNatalChartSummaryIdx) && (curChart.analysisState == AnalysisState.ROMANTIC_ANALYSIS) && (curChartState != ChartState.NATAL_CHART) -> {
                val romValue = curChart.getValueAspects().valueAspectReduceBaseModNet()
                val synRomValue = synNatalChart.getValueAspects().valueAspectReduceBaseModNet()

                snprintf(summaryDataLine, summaryDataLineSize.value, "%s%s%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , RenderChartState.getChartSumLabel(ChartState.NATAL_CHART, Constants.KMAG).impRomChartLabel()
                    , RenderChartState.getChartImpLabel(romValue, synRomValue)
                    , RenderChartState.getChartImpPercentLabel(romValue, synRomValue)
                    , RenderChartState.getChartImpStimLabel(romValue, synRomValue)
                )

                returnString = summaryDataLine.toKString().padEnd(115, ' ')
            }

            /* TODO: implement character analysis summary
            (renderIdx == characterNatalsIdx) && (curChartState != ChartState.NATAL_CHART) -> {
       //         List<ValueAspect>.getCharacterValue(chartStateType : ChartStateType) : Value
            }
            (renderIdx == characterCompositeIdx) && (curChartState != ChartState.NATAL_CHART) -> {

            }
            (renderIdx == characterSynastryIdx) && (curChartState != ChartState.NATAL_CHART) -> {

            }
            (renderIdx == characterCompSynIdx) && (curChartState != ChartState.NATAL_CHART) -> {

            }
*/
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