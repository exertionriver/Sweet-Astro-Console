package console.render

import astro.render.RenderChartState
import astro.render.RenderChartState.getPercentLabelGuide
import astro.render.RenderChartState.getStimLabelGuide
import astro.render.RenderChartState.getSumLabelGuide
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
    val baseCurChartPercentIdx = 2
    val baseCurChartStimulationIdx = 3

    val baseRefNatalChartSummaryIdx = 5
    val baseRefNatalChartPercentIdx = 6
    val baseRefNatalChartStimulationIdx = 7

    val baseSynNatalChartSummaryIdx = 9
    val baseSynNatalChartPercentIdx = 10
    val baseSynNatalChartStimulationIdx = 11

    val impRefNatalChartSummaryIdx = 13
    val impRefNatalChartPercentIdx = 14
    val impRefNatalChartStimulationIdx = 15

    val impSynNatalChartSummaryIdx = 17
    val impSynNatalChartPercentIdx = 18
    val impSynNatalChartStimulationIdx = 19

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
                    , getTopSummaryColBorderShape(curSummaryColumnWidth)
                )
            }
            (renderIdx == baseCurChartSummaryIdx) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , getChartSumLine(curChart, RenderChartState.getRenderChartSumLabel(curChartState, Constants.KCYN))
                    , getSumLabelGuide()
                )
            }
            (renderIdx == baseCurChartPercentIdx) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , getChartPercentLine(curChart, RenderChartState.getRenderChartPercentLabel(curChartState, Constants.KCYN))
                    , getPercentLabelGuide()
                )
            }
            (renderIdx == baseCurChartStimulationIdx) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , getChartStimulationLine(curChart, RenderChartState.getRenderChartStimLabel(curChartState, Constants.KCYN))
                    , getStimLabelGuide()
                )
            }
            (renderIdx == baseRefNatalChartSummaryIdx) && (curChartState != ChartState.NATAL_CHART) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , getChartSumLine(refNatalChart, RenderChartState.getRenderChartSumLabel(ChartState.NATAL_CHART, Constants.KBLU))
                    , getSumLabelGuide()
                )
            }
            (renderIdx == baseRefNatalChartPercentIdx) && (curChartState != ChartState.NATAL_CHART) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , getChartPercentLine(refNatalChart, RenderChartState.getRenderChartPercentLabel(ChartState.NATAL_CHART, Constants.KBLU))
                    , getPercentLabelGuide()
                )
            }
            (renderIdx == baseRefNatalChartStimulationIdx) && (curChartState != ChartState.NATAL_CHART) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , getChartStimulationLine(refNatalChart, RenderChartState.getRenderChartStimLabel(ChartState.NATAL_CHART, Constants.KBLU))
                    , getStimLabelGuide()
                )
            }
            (renderIdx == baseSynNatalChartSummaryIdx) && (curChartState != ChartState.NATAL_CHART) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , getChartSumLine(synNatalChart, RenderChartState.getRenderChartSumLabel(ChartState.NATAL_CHART, Constants.KMAG))
                    , getSumLabelGuide()
                )
            }
            (renderIdx == baseSynNatalChartPercentIdx) && (curChartState != ChartState.NATAL_CHART) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , getChartPercentLine(synNatalChart, RenderChartState.getRenderChartPercentLabel(ChartState.NATAL_CHART, Constants.KMAG))
                    , getPercentLabelGuide()
                )
            }
            (renderIdx == baseSynNatalChartStimulationIdx) && (curChartState != ChartState.NATAL_CHART) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , getChartStimulationLine(synNatalChart, RenderChartState.getRenderChartStimLabel(ChartState.NATAL_CHART, Constants.KMAG))
                    , getStimLabelGuide()
                )
            }
        //improvement
            (renderIdx == impRefNatalChartSummaryIdx) && (curChartState != ChartState.NATAL_CHART) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , getChartImpSumLine(curChart, refNatalChart, RenderChartState.getRenderChartSumLabel(ChartState.NATAL_CHART, Constants.KBLU))
                    , getSumLabelGuide()
                )
            }
            (renderIdx == impRefNatalChartPercentIdx) && (curChartState != ChartState.NATAL_CHART) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , getChartImpPercentLine(curChart, refNatalChart, RenderChartState.getRenderChartPercentLabel(ChartState.NATAL_CHART, Constants.KBLU))
                    , getPercentLabelGuide()
                )
            }
            (renderIdx == impRefNatalChartStimulationIdx) && (curChartState != ChartState.NATAL_CHART) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , getChartImpStimulationLine(curChart, refNatalChart, RenderChartState.getRenderChartStimLabel(ChartState.NATAL_CHART, Constants.KBLU))
                    , getStimLabelGuide()
                )
            }
            (renderIdx == impSynNatalChartSummaryIdx) && (curChartState != ChartState.NATAL_CHART) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , getChartImpSumLine(curChart, synNatalChart, RenderChartState.getRenderChartSumLabel(ChartState.NATAL_CHART, Constants.KMAG))
                    , getSumLabelGuide()
                )
            }
            (renderIdx == impSynNatalChartPercentIdx) && (curChartState != ChartState.NATAL_CHART) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , getChartImpPercentLine(curChart, synNatalChart, RenderChartState.getRenderChartPercentLabel(ChartState.NATAL_CHART, Constants.KMAG))
                    , getPercentLabelGuide()
                )
            }
            (renderIdx == impSynNatalChartStimulationIdx) && (curChartState != ChartState.NATAL_CHART) -> {

                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %s %s"
                    , getSummaryColBorderShape(renderIdx)
                    , getChartImpStimulationLine(curChart, synNatalChart, RenderChartState.getRenderChartStimLabel(ChartState.NATAL_CHART, Constants.KMAG))
                    , getStimLabelGuide()
                )
            }
            else ->
                snprintf(summaryDataLine, summaryDataLineSize.value,"%s %*s"
                    , getSummaryColBorderShape(renderIdx)
                    , curSummaryColumnWidth, "")
        }

        return summaryDataLine.toKString()
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

    fun getChartSumLine(firstChart : ValueChart, chartLabel : String) : String {
        val pos = firstChart.getBaseValue().positive
        val neg = firstChart.getBaseValue().negative
        val posLabel = Constants.KGRN + abs(pos).toString().padStart(4, ' ')
        val negLabel = Constants.KRED + abs(neg).toString().padStart(4, ' ')

        //TODO: move label to Astro folder
        return chartLabel + ":" + posLabel + Constants.KCYN + "." + negLabel + " " + Constants.KNRM
    }

    fun getChartPercentLine(firstChart : ValueChart, chartLabel : String) : String {
        val pos = firstChart.getBaseValue().positive
        val neg = firstChart.getBaseValue().negative
        val total = abs(pos) + abs(neg)

        val cons_num = (if(pos > 0) pos else 0) + (if(neg > 0) neg else 0)
        val diss_num = (if(pos < 0) pos else 0) + (if(neg < 0) neg else 0)
        val cons = abs(cons_num.toDouble() / total.toDouble() * 100).toInt()
        val diss = abs(diss_num.toDouble() / total.toDouble() * 100).toInt()
        val consLabel = Constants.KGRN + cons.toString().padStart(4, ' ')
        val dissLabel = Constants.KRED + diss.toString().padStart(4, ' ')

        //TODO: move label to Astro folder
        return chartLabel + ":" + consLabel + Constants.KCYN + "." + dissLabel + Constants.KNRM
    }

    fun getChartStimulationLine(firstChart : ValueChart, chartLabel : String) : String {

        //TODO: move label to Astro folder
        return chartLabel + ":" + Constants.KYEL + firstChart.getBaseValue().getStimulation().toString().padStart(7, ' ')
    }

    fun getChartImpSumLine(sharedChart : ValueChart, natalChart : ValueChart, chartLabel : String) : String {
        val pos = sharedChart.getBaseValue().positive - natalChart.getBaseValue().positive
        val neg = -1 * (sharedChart.getBaseValue().negative - natalChart.getBaseValue().negative)
        val posLabel = ( if (pos > 0) Constants.KGRN else if (pos < 0) Constants.KRED else Constants.KNRM ) + abs(pos).toString().padStart(3, ' ')
        val negLabel = ( if (neg > 0) Constants.KRED else if (neg < 0) Constants.KGRN else Constants.KNRM ) + abs(neg).toString().padStart(4, ' ')

        //TODO: move label to Astro folder
        return Constants.KBYEL + Constants.SYM_IMPROVEMENT + chartLabel + ":" + posLabel + Constants.KCYN + "." + negLabel + " " + Constants.KNRM
    }

    fun getChartImpPercentLine(sharedChart : ValueChart, natalChart : ValueChart, chartLabel : String) : String {
        val cons = (100 * sharedChart.getBaseValue().positive.toDouble() / natalChart.getBaseValue().positive.toDouble()).toInt()
        val diss = (100 * sharedChart.getBaseValue().negative.toDouble() / natalChart.getBaseValue().negative.toDouble()).toInt()
        val consLabel = ( if (cons > 100) Constants.KGRN else if (cons < 100) Constants.KRED else Constants.KNRM ) + abs(100 - cons).toString().padStart(3, ' ')
        val dissLabel = ( if (diss > 100) Constants.KRED else if (diss < 100) Constants.KGRN else Constants.KNRM ) + abs(100 - diss).toString().padStart(4, ' ')

        //TODO: move label to Astro folder
        return Constants.KBYEL + Constants.SYM_IMPROVEMENT + chartLabel + ":" + consLabel + Constants.KCYN + "." + dissLabel + Constants.KNRM
    }

    fun getChartImpStimulationLine(sharedChart : ValueChart, natalChart : ValueChart, chartLabel : String) : String {

        val impStim = sharedChart.getBaseValue().getStimulation() - natalChart.getBaseValue().getStimulation()
        val impStimLabel = ( if (impStim > 0) Constants.KGRN else if (impStim < 0) Constants.KRED else Constants.KNRM ) + abs(impStim).toString().padStart(6, ' ')

        //TODO: move label to Astro folder
        return Constants.KBYEL + Constants.SYM_IMPROVEMENT + chartLabel + ":" + impStimLabel
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