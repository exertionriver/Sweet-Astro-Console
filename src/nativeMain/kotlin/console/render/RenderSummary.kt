package console.render

import astro.render.RenderChartState
import astro.state.AnalysisState
import astro.state.ChartState
import astro.state.StateChart
import astro.value.ValueChart
import kotlinx.cinterop.*
import platform.posix.printf
import platform.posix.size_tVar
import platform.posix.snprintf
import kotlin.math.abs

object RenderSummary {

    val summaryHeaderIdx = 0
    val baseCurChartSummaryIdx = 1
    val baseRefNatalChartSummaryIdx = 2
    val baseSynNatalChartSummaryIdx = 3

    fun renderSummaryData(renderIdx: Int, curChart: ValueChart, refNatalChart: ValueChart, synNatalChart: ValueChart, curChartState : ChartState) {

        printf("%s", prepareSummaryData(renderIdx, curChart, refNatalChart, synNatalChart, curChartState) )
    }

    fun prepareSummaryData(renderIdx: Int, curChart: ValueChart, refNatalChart: ValueChart, synNatalChart: ValueChart, curChartState : ChartState) : String = memScoped {

        val curSummaryColumnWidth = RenderDetails.getDetailsColumnWidth(curChart.analysisState) - 2

        val summaryDataLineSize = alloc<size_tVar>()
        summaryDataLineSize.value = 256u

        val summaryDataLine = allocArray<ByteVar>(summaryDataLineSize.value.toInt())

        when {
            (renderIdx == summaryHeaderIdx) -> {
                snprintf(summaryDataLine, summaryDataLineSize.value, "%s "
                    , getTopSummaryColBorderShape(curSummaryColumnWidth) )
            }
            (renderIdx == baseCurChartSummaryIdx) -> {
                val baseSumLine = getSumLine(curChart, curChart, RenderChartState.getRenderChartSumLabel(curChartState, Constants.KCYN))

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s "
                    , getSummaryColBorderShape(renderIdx)
                    , baseSumLine)
            }
            (renderIdx == baseRefNatalChartSummaryIdx) && (curChartState != ChartState.NATAL_CHART) -> {
                val baseSumLine = getSumLine(refNatalChart, refNatalChart, RenderChartState.getRenderChartSumLabel(ChartState.NATAL_CHART, Constants.KBLU))

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s "
                    , getSummaryColBorderShape(renderIdx)
                    , baseSumLine)
            }
            (renderIdx == baseSynNatalChartSummaryIdx) && (curChartState != ChartState.NATAL_CHART) -> {
                val baseSumLine = getSumLine(synNatalChart, synNatalChart, RenderChartState.getRenderChartSumLabel(ChartState.NATAL_CHART, Constants.KMAG))

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s "
                    , getSummaryColBorderShape(renderIdx)
                    , baseSumLine)
            }
            else ->
                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %*s"
                    , getSummaryColBorderShape(renderIdx)
                    , curSummaryColumnWidth, "")

/*                when (curChart.chartState) {
                    ChartState.NATAL_CHART -> prepareNatalSummary(renderIdx, valueCurChart, valueRefNatalChart, curAnalysisState)
                    //comp / syn charts
                    else -> prepareCompSynSummary(renderIdx, valueCurChart, valueRefNatalChart, valueSynNatalChart, curAnalysisState)
                }
  */      }

        return summaryDataLine.toKString()
    }

    fun prepareNatalSummary(renderIdx: Int, valueCurChart: ValueChart, valueRefNatalChart: ValueChart, curAnalysisState: AnalysisState) : String {

        val summaryColumnWidth = RenderDetails.getDetailsColumnWidth(curAnalysisState)

        //chart summary

        //analysis summary
        when (curAnalysisState) {

        }

        return "natalSummary"
    }

    fun prepareCompSynSummary(renderIdx: Int, valueCurChart: ValueChart, valueRefNatalChart: ValueChart, valueSynNatalChart: ValueChart, curAnalysisState: AnalysisState) : String {

        val summaryColumnWidth = RenderDetails.getDetailsColumnWidth(curAnalysisState)

        //chart and natals summary
        val baseRefProfileSummaryIdx = baseCurChartSummaryIdx + 1
        val baseSynProfileSummaryIdx = baseCurChartSummaryIdx + 2

        //analysis summary
        when (curAnalysisState) {

        }

        return "compSynSummary"
    }

    fun renderSummaryDataLegacy(renderIdx: Int, curChart: StateChart, refNatalChart: StateChart, synNatalChart: StateChart, curAnalysisState: AnalysisState) {
/*
        val summaryColumnWidth = RenderDetails.getDetailsColumnWidth(curAnalysisState)
        val summaryDataColumnEndWidth = RenderDetails.getDetailsEndColumnWidth()

        val valueCurChart = ValueChart(curChart, curAnalysisState)
        val valueRefProfileChart = ValueChart(refNatalChart, curAnalysisState)
        val valueSynProfileChart = ValueChart(synNatalChart, curAnalysisState)

        val summaryValOffset = RenderDetails.getDetailsColumnWidth(curAnalysisState) - RenderDetails.getDetailsColumnWidth(
            AnalysisState.NO_ANALYSIS
        )

        val noAnalysisCurChartSummaryIdx = 1
        val noAnalysisCurProfileSummaryIdx = if (curChart.chartState != ChartState.NATAL_CHART) noAnalysisCurChartSummaryIdx + 1 else -1
        val noAnalysisSynProfileSummaryIdx = if (curChart.chartState != ChartState.NATAL_CHART) noAnalysisCurChartSummaryIdx + 2 else -1

        //percentage values
        val noAnalysisCurChartPercentageIdx = if (curChart.chartState != ChartState.NATAL_CHART) noAnalysisCurChartSummaryIdx + 4 else -1
        val noAnalysisCurProfilePercentageIdx = if (curChart.chartState != ChartState.NATAL_CHART) noAnalysisCurChartPercentageIdx + 1 else -1
        val noAnalysisSynProfilePercentageIdx = if (curChart.chartState != ChartState.NATAL_CHART) noAnalysisCurChartPercentageIdx + 2 else -1

        //these have analysis summary values
        val curChartSummaryIdx = if (curChart.chartState != ChartState.NATAL_CHART) noAnalysisCurChartPercentageIdx + 4 else noAnalysisCurChartSummaryIdx + 2
        val curProfileSummaryIdx = if (curChart.chartState != ChartState.NATAL_CHART) curChartSummaryIdx + 1 else -1
        val synProfileSummaryIdx = if (curChart.chartState != ChartState.NATAL_CHART) curChartSummaryIdx + 2 else -1

        //percentage values
        val curChartPercentageIdx = if (curChart.chartState != ChartState.NATAL_CHART) curChartSummaryIdx + 4 else -1
        val curProfilePercentageIdx = if (curChart.chartState != ChartState.NATAL_CHART) curChartPercentageIdx + 1 else -1
        val synProfilePercentageIdx = if (curChart.chartState != ChartState.NATAL_CHART) curChartPercentageIdx + 2 else -1

        when {
            (renderIdx == summaryHeaderIdx) -> {
                printf("%s", getTopSummaryColBorderShape(summaryColumnWidth - summaryDataColumnEndWidth))

                printf("%*s", summaryDataColumnEndWidth, "")
            }
            (renderIdx == noAnalysisCurChartSummaryIdx) -> {
                printf("%s ", getSummaryColBorderShape(renderIdx))

                val sumLine = getSumLine(valueCurChart, valueCurChart, RenderChartState.getRenderChartSumLabel(curChart.chartState, Constants.KCYN) )

                printf("%s%*s%*s", sumLine, summaryValOffset, "", summaryDataColumnEndWidth, "")
            }
            (renderIdx == noAnalysisCurProfileSummaryIdx) -> {
                printf("%s ", getSummaryColBorderShape(renderIdx))

                if (curChart.chartState != ChartState.NATAL_CHART) {

                    val sumLine = getSumLine(valueRefProfileChart, valueRefProfileChart, RenderChartState.getRenderChartSumLabel(curChart.chartState, Constants.KBLU) )

                    printf("%s%*s%*s", sumLine, summaryValOffset, "", summaryDataColumnEndWidth, "")
                } else
                    printf("%*s", summaryColumnWidth - 1, "")
            }
            (renderIdx == noAnalysisSynProfileSummaryIdx) -> {
                printf("%s ", getSummaryColBorderShape(renderIdx))

                if (curChart.chartState != ChartState.NATAL_CHART) {

                    val sumLine = getSumLine(valueSynProfileChart, valueSynProfileChart, RenderChartState.getRenderChartSumLabel(curChart.chartState, Constants.KMAG) )

                    printf("%s%*s%*s", sumLine, summaryValOffset, "", summaryDataColumnEndWidth, "")
                } else
                    printf("%*s", summaryColumnWidth - 1, "")
            }
            (renderIdx == noAnalysisCurChartPercentageIdx) -> {
                printf("%s ", getSummaryColBorderShape(renderIdx))

                val sumLine = getSumLine(valueCurChart, valueCurChart, RenderChartState.getRenderChartSumLabel(curChart.chartState, Constants.KCYN) )

                printf("%s%*s", sumLine, summaryValOffset, "")
            }
            (renderIdx == noAnalysisCurProfilePercentageIdx) -> {
                printf("%s ", getSummaryColBorderShape(renderIdx))

                if (curChart.chartState != ChartState.NATAL_CHART) {

                    val sumLine = getSumLine(valueCurChart, valueRefProfileChart, RenderChartState.getRenderChartSumLabel(curChart.chartState, Constants.KBLU) )

                    printf("%s%*s", sumLine, summaryValOffset, "")
                } else
                    printf("%*s", summaryColumnWidth - 1, "")
            }
            (renderIdx == noAnalysisSynProfilePercentageIdx) -> {
                printf("%s ", getSummaryColBorderShape(renderIdx))

                if (curChart.chartState != ChartState.NATAL_CHART) {
                    val sumLine = getSumLine(valueCurChart, valueSynProfileChart, RenderChartState.getRenderChartSumLabel(curChart.chartState, Constants.KMAG) )

                    printf("%s%*s", sumLine, summaryValOffset, "")
                } else
                    printf("%*s", summaryColumnWidth - 1, "")
            }
            (renderIdx == curChartSummaryIdx) -> {
                val borderSpace = if (curChartSummaryIdx == noAnalysisCurChartSummaryIdx + 2) " " else ""
                val valueSpace = if (curChartSummaryIdx == noAnalysisCurChartSummaryIdx + 2) "" else " "

                printf("%s%s", getSummaryColBorderShape(renderIdx), borderSpace)

                if (curAnalysisState == AnalysisState.ROMANTIC_ANALYSIS) {
                    val netRomanticValue = valueCurChart.getModValue().getNet()
                    val netRomanticValueLength = if (netRomanticValue > 0) netRomanticValue.toString().length + 1 else netRomanticValue.toString().length //to account for + in positive

                    val pos = valueCurChart.getBaseValue().positive
                    val neg = valueCurChart.getBaseValue().negative
                    val posLabel = pos
                    val negLabel = neg
//                    val posLabel = RenderAspect.getLabel(curChart.getChartValue(), AspectValue.POSITIVE_VALUE, AnalysisState.ROMANTIC_ANALYSIS.ordinal)
//                    val negLabel = RenderAspect.getLabel(curChart.getChartValue(), AspectValue.NEGATIVE_VALUE, AnalysisState.ROMANTIC_ANALYSIS.ordinal)
                    val total = pos - neg
                    val cons = (pos.toDouble() / total.toDouble() * 100).toInt()
                    val diss = (neg.toDouble() / total.toDouble() * 100).toInt()

                    val sumLine = RenderChartState.getRenderChartSumLabel(curChart.chartState, Constants.KCYN) + ":" +
                            posLabel + Constants.KCYN + "." + negLabel + " " +
                            Constants.KGRN + cons.toString().padStart(2, ' ') + Constants.KCYN + "." +
                            Constants.KRED + diss.toString().padStart(3, ' ') + Constants.KNRM +
                            netRomanticValue
//                            RenderAspect.getRomModLabel(curAnalysisState, netRomanticValue)

                    printf("%s%*s", sumLine, ((summaryDataColumnEndWidth * 2) - netRomanticValueLength) - 1, "") //space is included at end of romModLabel
                } else
                    printf("%*s", summaryColumnWidth - 1, "")
            }
            (renderIdx == curProfileSummaryIdx) -> {
                printf("%s", getSummaryColBorderShape(renderIdx))

                if (curAnalysisState == AnalysisState.ROMANTIC_ANALYSIS) {
                    val netRomanticValue = valueCurChart.getModValue().getNet()
                    val netRomanticValueLength = if (netRomanticValue > 0) netRomanticValue.toString().length + 1 else netRomanticValue.toString().length //to account for + in positive

                    val pos = valueCurChart.getBaseValue().positive
                    val neg = valueCurChart.getBaseValue().negative
                    val posLabel = pos
                    val negLabel = neg
//                    val posLabel = RenderAspect.getLabel(refProfileChart.getChartValue(), AspectValue.POSITIVE_VALUE, AnalysisStates.ROMANTIC_ANALYSIS.ordinal)
//                    val negLabel = RenderAspect.getLabel(refProfileChart.getChartValue(), AspectValue.NEGATIVE_VALUE, AnalysisStates.ROMANTIC_ANALYSIS.ordinal)
                    val total = pos - neg
                    val cons = (pos.toDouble() / total.toDouble() * 100).toInt()
                    val diss = (neg.toDouble() / total.toDouble() * 100).toInt()

                    val sumLine = RenderChartState.getRenderChartSumLabel(curChart.chartState, Constants.KBLU) + ":" +
                            posLabel + Constants.KCYN + "." + negLabel + " " +
                            Constants.KGRN + cons.toString().padStart(2, ' ') + Constants.KCYN + "." +
                            Constants.KRED + diss.toString().padStart(3, ' ') + Constants.KNRM +
                            netRomanticValue
//                            RenderAspect.getRomModLabel(curAnalysisState, netRomanticValue)

                    printf("%s%*s", sumLine, ((summaryDataColumnEndWidth * 2) - netRomanticValueLength) - 1, "") //space is included at end of romModLabel
                } else
                    printf("%*s", summaryColumnWidth - 1, "")
            }
            (renderIdx == synProfileSummaryIdx) -> {
                printf("%s", getSummaryColBorderShape(renderIdx))

                if (curAnalysisState == AnalysisState.ROMANTIC_ANALYSIS) {
                    val netRomanticValue = valueSynProfileChart.getModValue().getNet()
                    val netRomanticValueLength = if (netRomanticValue > 0) netRomanticValue.toString().length + 1 else netRomanticValue.toString().length //to account for + in positive

                    val pos = valueCurChart.getBaseValue().positive
                    val neg = valueCurChart.getBaseValue().negative
                    val posLabel = pos
                    val negLabel = neg
//                    val posLabel = RenderAspect.getLabel(refProfileChart.getChartValue(), AspectValue.POSITIVE_VALUE, AnalysisStates.ROMANTIC_ANALYSIS.ordinal)
//                    val negLabel = RenderAspect.getLabel(refProfileChart.getChartValue(), AspectValue.NEGATIVE_VALUE, AnalysisStates.ROMANTIC_ANALYSIS.ordinal)
                    val total = pos - neg
                    val cons = (pos.toDouble() / total.toDouble() * 100).toInt()
                    val diss = (neg.toDouble() / total.toDouble() * 100).toInt()

                    val sumLine = RenderChartState.getRenderChartSumLabel(curChart.chartState, Constants.KMAG) + ":" +
                            posLabel + Constants.KCYN + "." + negLabel + " " +
                            Constants.KGRN + cons.toString().padStart(2, ' ') + Constants.KCYN + "." +
                            Constants.KRED + diss.toString().padStart(3, ' ') + Constants.KNRM +
                            netRomanticValue
//                            RenderAspect.getRomModLabel(curAnalysisState, netRomanticValue)

                    printf("%s%*s", sumLine, ((summaryDataColumnEndWidth * 2) - netRomanticValueLength) - 1, "") //space is included at end of romModLabel
                } else
                    printf("%*s", summaryColumnWidth - 1, "")
            }
            (renderIdx == curChartPercentageIdx) -> {
                printf("%s ", getSummaryColBorderShape(renderIdx))

                if (curAnalysisState == AnalysisState.ROMANTIC_ANALYSIS) {
                    val pos = valueCurChart.getModValue().positive - valueCurChart.getBaseValue().positive
                    val neg = valueCurChart.getModValue().negative - valueCurChart.getBaseValue().negative
                    //val posLabel = synProfileChart.getChartValue().getLabel(AspectValue.POSITIVE_VALUE)
                    //val negLabel = synProfileChart.getChartValue().getLabel(AspectValue.NEGATIVE_VALUE)
                    val total = abs(pos) + abs(neg)
                    val cons_num = (if(pos > 0) pos else 0) + (if(neg > 0) neg else 0)
                    val diss_num = (if(pos < 0) pos else 0) + (if(neg < 0) neg else 0)
                    val cons = (cons_num.toDouble() / total.toDouble() * 100).toInt()
                    val diss = (diss_num.toDouble() / total.toDouble() * 100).toInt()

                    val sumLine = RenderChartState.getRenderChartPercentageLabel(Constants.KCYN) + ":" +
                            pos.toString().padStart(5, ' ') + Constants.KCYN + "." + neg.toString().padStart(5, ' ') + " " +
                            Constants.KGRN + cons.toString().padStart(2, ' ') + Constants.KCYN + "." +
                            Constants.KRED + diss.toString().padStart(3, ' ') + Constants.KNRM

                    printf("%s%*s", sumLine, summaryValOffset, "")
                } else
                    printf("%*s", summaryColumnWidth - 1, "")
            }
            (renderIdx == curProfilePercentageIdx) -> {
                printf("%s ", RenderSummary.getSummaryColBorderShape(renderIdx))

                if (curAnalysisState == AnalysisState.ROMANTIC_ANALYSIS) {
                    val netRomanticValueDiff = valueCurChart.getModValue().getNet() - valueRefProfileChart.getModValue().getNet()

                    val pos = valueCurChart.getModValue().positive - valueRefProfileChart.getModValue().positive
                    val neg = valueCurChart.getModValue().negative - valueRefProfileChart.getModValue().negative
                    //val posLabel = synProfileChart.getChartValue().getLabel(AspectValue.POSITIVE_VALUE)
                    //val negLabel = synProfileChart.getChartValue().getLabel(AspectValue.NEGATIVE_VALUE)
                    val total = abs(pos) + abs(neg)
                    val cons_num = (if(pos > 0) pos else 0) + (if(neg > 0) neg else 0)
                    val diss_num = (if(pos < 0) pos else 0) + (if(neg < 0) neg else 0)
                    val cons = (cons_num.toDouble() / total.toDouble() * 100).toInt()
                    val diss = (diss_num.toDouble() / total.toDouble() * 100).toInt()

                    val sumLine = RenderChartState.getRenderChartPercentageLabel(Constants.KBLU) + ":" +
                            pos.toString().padStart(5, ' ') + Constants.KCYN + "." + neg.toString().padStart(5, ' ') + " " +
                            Constants.KGRN + cons.toString().padStart(2, ' ') + Constants.KCYN + "." +
                            Constants.KRED + diss.toString().padStart(3, ' ') + Constants.KNRM +
                            netRomanticValueDiff
//                            RenderAspect.getRomModLabel(curAnalysisState, netRomanticValueDiff)

                    printf("%s%*s", sumLine, summaryValOffset, "")
                } else
                    printf("%*s", summaryColumnWidth - 1, "")
            }
            (renderIdx == synProfilePercentageIdx) -> {
                printf("%s ", getSummaryColBorderShape(renderIdx))

                if (curAnalysisState == AnalysisState.ROMANTIC_ANALYSIS) {
                    val netRomanticValueDiff = valueCurChart.getModValue().getNet() - valueSynProfileChart.getModValue().getNet()

                    val pos = valueCurChart.getModValue().positive - valueSynProfileChart.getModValue().positive
                    val neg = valueCurChart.getModValue().negative - valueSynProfileChart.getModValue().negative
                    //val posLabel = synProfileChart.getChartValue().getLabel(AspectValue.POSITIVE_VALUE)
                    //val negLabel = synProfileChart.getChartValue().getLabel(AspectValue.NEGATIVE_VALUE)
                    val total = abs(pos) + abs(neg)
                    val cons_num = (if(pos > 0) pos else 0) + (if(neg > 0) neg else 0)
                    val diss_num = (if(pos < 0) pos else 0) + (if(neg < 0) neg else 0)
                    val cons = (cons_num.toDouble() / total.toDouble() * 100).toInt()
                    val diss = (diss_num.toDouble() / total.toDouble() * 100).toInt()

                    val sumLine = RenderChartState.getRenderChartPercentageLabel(Constants.KMAG) + ":" +
                            pos.toString().padStart(5, ' ') + Constants.KCYN + "." + neg.toString().padStart(5, ' ') + " " +
                            Constants.KGRN + cons.toString().padStart(2, ' ') + Constants.KCYN + "." +
                            Constants.KRED + diss.toString().padStart(3, ' ') + Constants.KNRM +
                            netRomanticValueDiff
//                            RenderAspect.getRomModLabel(curAnalysisState, netRomanticValueDiff)

                    printf("%s%*s", sumLine, summaryValOffset, "")
                } else
                    printf("%*s", summaryColumnWidth - 1, "")
            }
            else -> {
                printf("%s ", RenderSummary.getSummaryColBorderShape(renderIdx))

                printf("%*s", summaryColumnWidth - 1, "")
            }
        }
  */  }

    fun getSumLine(firstChart : ValueChart, secondChart : ValueChart, chartLabel : String) : String {
        val pos = ((firstChart.getBaseValue().positive + secondChart.getBaseValue().positive) / 2)
        val neg = ((firstChart.getBaseValue().negative + secondChart.getBaseValue().negative) / 2)
//        val avgVal = AspectValue(pos, neg)
        val posLabel = abs(pos)
        val negLabel = abs(neg)
//        val posLabel = RenderAspect.getLabel(avgVal, AspectValue.POSITIVE_VALUE)
//        val negLabel = RenderAspect.getLabel(avgVal, AspectValue.NEGATIVE_VALUE)
        val total = abs(pos) + abs(neg)
        val cons_num = (if(pos > 0) pos else 0) + (if(neg > 0) neg else 0)
        val diss_num = (if(pos < 0) pos else 0) + (if(neg < 0) neg else 0)
        val cons = abs(cons_num.toDouble() / total.toDouble() * 100).toInt()
        val diss = abs(diss_num.toDouble() / total.toDouble() * 100).toInt()

        //TODO: move label to Astro folder
        return chartLabel + ":" +
                Constants.KGRN + posLabel.toString().padStart(4, ' ') + Constants.KCYN + "." +
                Constants.KRED + negLabel.toString().padStart(4, ' ') + " " +
                Constants.KGRN + cons.toString().padStart(2, ' ') + Constants.KCYN + "." +
                Constants.KRED + diss.toString().padStart(2, ' ') + Constants.KNRM
    }
/*
    fun getRomanticSumLine(valueCurChart : ValueChart, chartLabel : String) : String {

        val pos = valueCurChart.getBaseValue().positive
        val neg = valueCurChart.getBaseValue().negative
        val posLabel = abs(pos)
        val negLabel = abs(neg)
//                    val posLabel = RenderAspect.getLabel(refProfileChart.getChartValue(), AspectValue.POSITIVE_VALUE, AnalysisStates.ROMANTIC_ANALYSIS.ordinal)
//                    val negLabel = RenderAspect.getLabel(refProfileChart.getChartValue(), AspectValue.NEGATIVE_VALUE, AnalysisStates.ROMANTIC_ANALYSIS.ordinal)
        val total = abs(pos) + abs(neg)
        val cons_num = (if(pos > 0) pos else 0) + (if(neg > 0) neg else 0)
        val diss_num = (if(pos < 0) pos else 0) + (if(neg < 0) neg else 0)
        val cons = abs(cons_num.toDouble() / total.toDouble() * 100).toInt()
        val diss = abs(diss_num.toDouble() / total.toDouble() * 100).toInt()

        return chartLabel + ":" + posLabel.toString().padStart(4, ' ') +
                Constants.KCYN + "." + negLabel.toString().padStart(4, ' ') + " " +
                Constants.KGRN + cons.toString().padStart(2, ' ') + Constants.KCYN + "." +
                Constants.KRED + diss.toString().padStart(2, ' ') + Constants.KNRM
    }
*/
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