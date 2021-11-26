package console.render

import astro.base.CelestialSnapshot
import astro.render.RenderAspect
import astro.state.AnalysisState
import astro.state.ChartState
import astro.state.DetailsState
import astro.state.StateChart
import astro.value.ValueAspect
import astro.value.ValueChart
import console.state.ConsoleAnalysisState
import console.state.ConsoleChartState
import console.state.ConsoleDetailsState
import kotlinx.cinterop.*
import kotlinx.cinterop.nativeHeap.alloc
import platform.posix.printf
import platform.posix.size_tVar
import platform.posix.snprintf
import profile.base.Profile

object RenderDetails {

    val detailsFirstColMaxIdx = 19
    val dataColumnEndWidth = getDetailsEndColumnWidth()

    fun renderDetailsData(celestialRenderIdx: Int, housesRenderIdx: Int, curChart: ValueChart) {
        printf("%s%s%s", prepareDetailsDataFirstCol(celestialRenderIdx, housesRenderIdx, curChart)
            , prepareDetailsDataSecondCol(celestialRenderIdx, housesRenderIdx, curChart)
            , prepareDetailsDataThirdCol(celestialRenderIdx, housesRenderIdx, curChart) )
    }

    fun prepareDetailsDataFirstCol(celestialRenderIdx: Int, housesRenderIdx: Int, curChart: ValueChart) : String = memScoped {

        val detailsDataLineFirstColSize = alloc<size_tVar>()
        detailsDataLineFirstColSize.value = 128u
        val detailsDataLineFirstCol = allocArray<ByteVar>(detailsDataLineFirstColSize.value.toInt())

        val detailsColumnWidth = getDetailsColumnWidth(curChart.analysisState)

        val dataIdxMax = curChart.getValueAspects().size - 1

        val detailsIdxFirstCol = if (celestialRenderIdx == RenderConsole.noRenderCelestials) (housesRenderIdx + RenderCelestials.celestialsRenderMaxIdx + 1) else celestialRenderIdx

        val dataIdxFirstCol = detailsIdxFirstCol - 1

        //adding 1 since data is 0-indexed
        val firstColBorder = getBorderColor(dataIdxFirstCol + 1) + getFirstColBorderShape(dataIdxFirstCol + 1) + Constants.KNRM

        when {
            (detailsIdxFirstCol == 0) -> {
                if (dataIdxMax > dataIdxFirstCol) {
                    snprintf(detailsDataLineFirstCol, detailsDataLineFirstColSize.value, "%s", getTopFirstColBorderShape())
                    } else {
                        snprintf(detailsDataLineFirstCol, detailsDataLineFirstColSize.value,"%*s", detailsColumnWidth * 3, "")
                    }
            }
            else -> {
                if (dataIdxMax >= dataIdxFirstCol) {
                    val idxSpace = if (detailsIdxFirstCol <= 10) " " else ""

                    if (detailsIdxFirstCol < detailsFirstColMaxIdx) {
                        val firstColumnAspect = curChart.getValueAspects()[dataIdxFirstCol]
                        val firstColumnData = firstColBorder + " " + idxSpace + dataIdxFirstCol.toString() + ":" + RenderAspect(firstColumnAspect).getRenderLabel()

                        snprintf(detailsDataLineFirstCol, detailsDataLineFirstColSize.value,"%s", firstColumnData)
                    } else if (detailsIdxFirstCol == detailsFirstColMaxIdx) {
                        snprintf(detailsDataLineFirstCol, detailsDataLineFirstColSize.value,"%*s", detailsColumnWidth - 1, "")
                    } //else summary lines formatting
                } else {
                    snprintf(detailsDataLineFirstCol, detailsDataLineFirstColSize.value,"%*s",detailsColumnWidth * 4, "")
                }
            }
        }

        return detailsDataLineFirstCol.toKString()
    }

    fun prepareDetailsDataSecondCol(celestialRenderIdx: Int, housesRenderIdx: Int, curChart: ValueChart) : String = memScoped {

        val detailsDataLineSecondColSize = alloc<size_tVar>()
        detailsDataLineSecondColSize.value = 128u
        val detailsDataLineSecondCol = allocArray<ByteVar>(detailsDataLineSecondColSize.value.toInt())

        val detailsColumnWidth = getDetailsColumnWidth(curChart.analysisState)

        val dataIdxMax = curChart.getValueAspects().size - 1

        val detailsIdxFirstCol = if (celestialRenderIdx == RenderConsole.noRenderCelestials) (housesRenderIdx + RenderCelestials.celestialsRenderMaxIdx + 1) else celestialRenderIdx

        val dataIdxFirstCol = detailsIdxFirstCol - 1
        val dataIdxSecondCol = dataIdxFirstCol + detailsFirstColMaxIdx - 1

        //adding 1 since data is 0-indexed
        val secondColBorder = getBorderColor(dataIdxSecondCol + 1) + getAddlColBorderShape(dataIdxSecondCol + 1) + Constants.KNRM

        when {
            (detailsIdxFirstCol == 0) -> {
                if (dataIdxMax > dataIdxSecondCol) {
                    snprintf(detailsDataLineSecondCol, detailsDataLineSecondColSize.value,"%*s%s", detailsColumnWidth - getTopFirstColBorderShapeLength(), "", getTopAddlColBordersShape())

                } else {
                    snprintf(detailsDataLineSecondCol, detailsDataLineSecondColSize.value,"%*s", detailsColumnWidth * 3, "")
                }
            }
            else -> {
                if (dataIdxMax >= dataIdxFirstCol) {

                    if (dataIdxMax >= dataIdxSecondCol) {
                        val secondColumnAspect = curChart.getValueAspects()[dataIdxSecondCol]
                        val secondColumnData = secondColBorder + " " + dataIdxSecondCol.toString() + ":" + RenderAspect(secondColumnAspect).getRenderLabel()

                        snprintf(detailsDataLineSecondCol, detailsDataLineSecondColSize.value,"%s", secondColumnData)

                    } else {
                        snprintf(detailsDataLineSecondCol, detailsDataLineSecondColSize.value,"%*s",detailsColumnWidth * 3, "")
                    }
                }
           }
        }

        return detailsDataLineSecondCol.toKString()
    }

    fun prepareDetailsDataThirdCol(celestialRenderIdx: Int, housesRenderIdx: Int, curChart: ValueChart) : String = memScoped {

        val detailsDataLineThirdColSize = alloc<size_tVar>()
        detailsDataLineThirdColSize.value = 128u
        val detailsDataLineThirdCol = allocArray<ByteVar>(detailsDataLineThirdColSize.value.toInt())

        val detailsColumnWidth = getDetailsColumnWidth(curChart.analysisState)

        val dataIdxMax = curChart.getValueAspects().size - 1

        val detailsIdxFirstCol = if (celestialRenderIdx == RenderConsole.noRenderCelestials) (housesRenderIdx + RenderCelestials.celestialsRenderMaxIdx + 1) else celestialRenderIdx
        val dataIdxFirstCol = detailsIdxFirstCol - 1
        val dataIdxSecondCol = dataIdxFirstCol + detailsFirstColMaxIdx - 1
        val dataIdxThirdCol = dataIdxSecondCol + RenderConsole.detailsRenderMaxIdx + 4

        //adding 1 since data is 0-indexed
        val thirdColBorder = getBorderColor(dataIdxThirdCol + 1) + getAddlColBorderShape(dataIdxThirdCol + 1) + Constants.KNRM

        when {
            (detailsIdxFirstCol == 0) -> {
                if (dataIdxMax > dataIdxThirdCol) {
                    snprintf(detailsDataLineThirdCol, detailsDataLineThirdColSize.value,"%*s%s%*s"
                        , detailsColumnWidth - getTopAddlColBorderShapeLength(), "", getTopAddlColBordersShape()
                        , detailsColumnWidth, "")
                } else {
                    snprintf(detailsDataLineThirdCol, detailsDataLineThirdColSize.value,"%*s", detailsColumnWidth * 2, "")
                }
            }
            else -> {
                if (dataIdxMax >= dataIdxFirstCol) {
                    if (dataIdxMax >= dataIdxThirdCol) {
                        val thirdColumnAspect = curChart.getValueAspects()[dataIdxThirdCol]
                        val thirdColumnData = thirdColBorder + " " + dataIdxThirdCol.toString() + ":" + RenderAspect(thirdColumnAspect).getRenderLabel()

                        snprintf(detailsDataLineThirdCol, detailsDataLineThirdColSize.value,"%s%*s"
                            , thirdColumnData, detailsColumnWidth, "")
                    } else {
                        //width plus label plus column space
                        snprintf(detailsDataLineThirdCol, detailsDataLineThirdColSize.value,"%*s", detailsColumnWidth * 2, "")
                    }
                }
            }
        }

        return detailsDataLineThirdCol.toKString()
    }

    @ExperimentalUnsignedTypes
    fun renderCelestialsDetailsData(
        refSnapshot: CelestialSnapshot,
        synSnapshot: CelestialSnapshot,
        curChart: ValueChart,
        curChartState: ChartState,
        curDetailsState : DetailsState,
        curAnalysisState : AnalysisState
    ) {
        for (celestialRenderIdx in 0..RenderConsole.celestialsRenderMaxIdx) {

            RenderCelestials.renderCelestialsData(celestialRenderIdx, curChartState, refSnapshot, synSnapshot)

            if (curDetailsState == DetailsState.SHOW_DETAILS)
                renderDetailsData(celestialRenderIdx, RenderConsole.noRenderHouseAspect, curChart)
            else
                printf("%*s", getDetailsColumnWidth(curAnalysisState) * 4, "")

            printf("\n")
        }
    }

    @ExperimentalUnsignedTypes
    fun renderHouseAspectDetailsData(
        refSnapshot: CelestialSnapshot,
        curChart: ValueChart,
        refNatalChart : ValueChart,
        synNatalChart : ValueChart,
        curChartState : ChartState,
        curDetailsState : DetailsState,
        curAnalysisState: AnalysisState
    ) {
        for (houseAspectRenderIdx in 0..RenderConsole.aspectNatalCompRenderMaxIdx) {
            RenderCelestialHouses.renderHouseData(houseAspectRenderIdx, refSnapshot)

            if (curChartState != ChartState.SYNASTRY_CHART) {
                RenderChart.renderNatalCompAspectData(houseAspectRenderIdx, curChart)
            } else {
                RenderChart.renderSynAspectData(houseAspectRenderIdx, curChart)
            }

            RenderSummary.renderSummaryData(houseAspectRenderIdx, curChart, refNatalChart, synNatalChart, curChartState)

           if (curDetailsState == DetailsState.SHOW_DETAILS)
                renderDetailsData(RenderConsole.noRenderCelestials, houseAspectRenderIdx, curChart)
            else
                printf("%*s", getDetailsColumnWidth(curAnalysisState) * 3, "")

            printf("\n")
        }
    }

    fun getDetailsDataColumnWidth(analysisState: AnalysisState = AnalysisState.NO_ANALYSIS) : Int {
        return RenderAspect.getLabelLength(analysisState)
    }

    fun getDetailsColumnWidth(analysisState: AnalysisState = AnalysisState.NO_ANALYSIS) : Int {
        return getDetailsDataColumnWidth(analysisState) + getDetailsEndColumnWidth()
    }

    fun getDetailsEndColumnWidth() : Int {
        return 4
    }

    fun getBorderColor(idx : Int) : String {
        return when {
            Constants.isFirst63Primes(idx) && Constants.isFirst15Fibonaccis(idx) && Constants.isFirst10Power2s(idx) -> Constants.KWHT
            Constants.isFirst63Primes(idx) && Constants.isFirst15Fibonaccis(idx) && !Constants.isFirst10Power2s(idx) -> Constants.KGRN
            Constants.isFirst63Primes(idx) && !Constants.isFirst15Fibonaccis(idx) && Constants.isFirst10Power2s(idx) -> Constants.KBBLK //no such number
            Constants.isFirst63Primes(idx) && !Constants.isFirst15Fibonaccis(idx) && !Constants.isFirst10Power2s(idx) -> Constants.KYEL
            !Constants.isFirst63Primes(idx) && Constants.isFirst15Fibonaccis(idx) && Constants.isFirst10Power2s(idx) -> Constants.KMAG
            !Constants.isFirst63Primes(idx) && Constants.isFirst15Fibonaccis(idx) && !Constants.isFirst10Power2s(idx) -> Constants.KBLU
            !Constants.isFirst63Primes(idx) && !Constants.isFirst15Fibonaccis(idx) && Constants.isFirst10Power2s(idx) -> Constants.KRED
            else -> Constants.KCYN
        }
    }

    fun getFirstColBorderShape(idx : Int) : String {

        return when {
            (idx % 10 == 0) -> Constants.SYM_CIRCLE_DOUBLE_ORNAMENT
            (idx % 5 == 0) -> Constants.SYM_DIAMOND_DOUBLE_ORNAMENT
            else -> Constants.BOX_DOUBLE_VERT
        }
    }

    fun getTopFirstColBorderShape() : String {
        return Constants.KCYN + Constants.BOX_DOUBLE_CORNER_DOWN_TO_RIGHT + Constants.BOX_DOUBLE_HORZ + " " +
                Constants.KMAG + Constants.SYM_BECAUSE + Constants.KCYN + " " + Constants.BOX_DOUBLE_HORZ +
                Constants.SYM_CIRCLE_DOUBLE_ORNAMENT + Constants.KNRM
    }

    fun getTopFirstColBorderShapeLength() : Int {
        return 7
    }

    fun getAddlColBorderShape(idx : Int) : String {

        return when {
            (idx % 10 == 0) -> Constants.SYM_CIRCLE_SINGLE_ORNAMENT
            (idx % 5 == 0) -> Constants.SYM_DIAMOND_SINGLE_ORNAMENT
            else -> Constants.BOX_SINGLE_VERT
        }
    }

    fun getTopAddlColBordersShape() : String {
        return Constants.KCYN + Constants.BOX_SINGLE_CORNER_DOWN_TO_RIGHT + Constants.BOX_SINGLE_HORZ +
                Constants.BOX_SINGLE_HORZ + Constants.SYM_CIRCLE_SINGLE_ORNAMENT + Constants.KNRM
    }

    fun getTopAddlColBorderShapeLength() : Int {
        return 4
    }
}