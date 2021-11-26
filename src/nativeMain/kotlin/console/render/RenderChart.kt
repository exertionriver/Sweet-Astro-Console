package console.render

import astro.base.AspectCelestial
import astro.base.AspectType
import astro.base.EarthLocation
import astro.render.RenderAspect
import astro.render.RenderAspectCelestial
import astro.state.AnalysisState
import astro.state.StateChart
import astro.value.ValueAspect
import astro.value.ValueChart
import kotlinx.cinterop.*
import platform.posix.printf
import platform.posix.size_tVar
import platform.posix.snprintf
import profile.base.Profile

object RenderChart {

    private const val aspectsBottomSpaceOffset = 2
    val aspectNatalCompRenderMaxIdx = (AspectCelestial.getChartSize() + aspectsBottomSpaceOffset)

    fun renderNatalCompAspectData(renderIdx: Int, curChart: ValueChart) {
        printf("%s", prepareNatalCompAspectData(renderIdx, curChart) )
    }

    fun prepareNatalCompAspectData(renderIdx: Int, curChart: ValueChart) : String = memScoped {

        val aspectsHeaderRenderIdx = 0
        val aspectFooterFirstRow = aspectNatalCompRenderMaxIdx - 1

        val sectionWidth = 54
        val rowStartSpace = 3
        val cellWidth = 3
        val verticalIdx = renderIdx - 1
        val sectionEndWidth = sectionWidth - (renderIdx * cellWidth) - 1

        val natalCompAspectDataLineSize = alloc<size_tVar>()
        natalCompAspectDataLineSize.value = 256u

        val natalCompAspectDataLine = allocArray<ByteVar>(natalCompAspectDataLineSize.value.toInt())

        when {
            (renderIdx == aspectsHeaderRenderIdx) -> snprintf(natalCompAspectDataLine, natalCompAspectDataLineSize.value,"%*s%*s", rowStartSpace, "", sectionWidth, "")
            (renderIdx >= aspectFooterFirstRow) -> snprintf(natalCompAspectDataLine, natalCompAspectDataLineSize.value,"%*s%*s", rowStartSpace, "", sectionWidth, "")
            else -> {
                var label = ""
                for (horizontalIdx in 0..verticalIdx) {

                    label += if (verticalIdx == horizontalIdx) {
                        " ${RenderAspectCelestial.fromOrdinal(verticalIdx)!!.getHeaderLabel()} "
                    } else {
                        " ${RenderAspect(curChart.chartRows[horizontalIdx].rowAspects[verticalIdx]).getAspectRenderLabel()}"
                    }
                }
                snprintf(natalCompAspectDataLine, natalCompAspectDataLineSize.value,"%*s%s%*s", rowStartSpace, "", label, sectionEndWidth, "")
            }
        }

        return natalCompAspectDataLine.toKString()
    }

    fun renderSynAspectData(renderIdx: Int, curChart: ValueChart) {
        printf("%s", prepareSynAspectData(renderIdx, curChart) )
    }

    fun prepareSynAspectData(renderIdx: Int, curChart: ValueChart) : String = memScoped {

        val aspectsHeaderRenderIdx = 0

        val sectionWidth = 54
        val sectionEndWidth = 2
        val headerStartWidth = 3
        val headerEndWidth = 1

        val headerIdx = -1
        val verticalIdx = renderIdx - 1
        val verticalIdxMax = aspectNatalCompRenderMaxIdx - 1
        val horizontalIdxMax = AspectCelestial.getChartSize() - 1 //get idx from size

        val synAspectDataLineSize = alloc<size_tVar>()
        synAspectDataLineSize.value = 512u

        val synAspectDataLine = allocArray<ByteVar>(synAspectDataLineSize.value.toInt())

        when {
            (renderIdx == aspectsHeaderRenderIdx) -> {
                var label = ""

                for (horizontalIdx in headerIdx..horizontalIdxMax) {
                    label += when (horizontalIdx) {
                        headerIdx -> continue // no-op
                        else -> " ${RenderAspectCelestial.fromOrdinal(horizontalIdx)!!.getSynHeaderLabel()}"
                    }
                }
                snprintf(synAspectDataLine, synAspectDataLineSize.value,
                    "%*s%s%*s%*s",
                    headerStartWidth, "",
                    label,
                    headerEndWidth, "",
                    sectionEndWidth, "") //end of section
            }
            (renderIdx >= verticalIdxMax) -> snprintf(synAspectDataLine, synAspectDataLineSize.value,"%*s", sectionWidth + sectionEndWidth + 1, "")
            else -> {
                var label = ""

                    for (horizontalIdx in headerIdx..horizontalIdxMax) {

                        if (horizontalIdx == headerIdx) {
                            label += " ${RenderAspectCelestial.fromOrdinal(verticalIdx)!!.getSynLabel()}"
                            continue
                        }
                        val aspectOut = curChart.chartRows[horizontalIdx].rowAspects[verticalIdx]
//                        println("horizontalIdx:$horizontalIdx verticalIdx:$verticalIdx aspectIdx:" + aspectOut.aspectAngle.getLabel())

                        label += if ((horizontalIdx == verticalIdx) && (AspectType.ASPECT_NONE == aspectOut.getBaseAspect().aspectAngle.getAspectType()))
                            "${RenderAspect.getAspectNoneMarkerLabel()} "
                        else
                            "${RenderAspect(curChart.chartRows[horizontalIdx].rowAspects[verticalIdx]).getAspectRenderLabel()} "

                    }

                snprintf(synAspectDataLine, synAspectDataLineSize.value, " %s%*s", label, sectionEndWidth, "")

            }
        }

        return synAspectDataLine.toKString()
    }

    fun getChartValueCalcDetailHeader(): String {

        return "utcTime" +
                ",p.stim".padEnd(8, ' ') +
                ",p.pos".padEnd(8, ' ') +
                ",p.cons".padEnd(8, ' ') +
                ",s.stim".padEnd(8, ' ') +
                ",s.pos".padEnd(8, ' ') +
                ",s.cons".padEnd(8, ' ') +
                ",i.stim".padEnd(8, ' ') +
                ",i.pos".padEnd(8, ' ') +
                ",i.cons".padEnd(8, ' ') +
                ",i.avg".padEnd(8, ' ') +
                ",b.stim".padEnd(8, ' ') +
                ",b.pos".padEnd(8, ' ') +
                ",b.cons".padEnd(8, ' ') +
                ",r.stim".padEnd(8, ' ') +
                ",r.pos".padEnd(8, ' ') +
                ",r.cons".padEnd(8, ' ') +
                ",r.avg".padEnd(8, ' ') +
                ",wwh".padEnd(8, ' ')
    }

    @ExperimentalUnsignedTypes
    fun getChartValueCalcRefRow(refProfile: Profile, refNatal: ValueChart, showTime : Boolean = false): String {

        val utcString = if (showTime) EarthLocation.getDateTimeString(refProfile.earthLocation.utcDateTime) else
            EarthLocation.getDateString(refProfile.earthLocation.utcDateTime)


        return utcString //+
//                "," + refNatal.stimulation.toString().padEnd(8, ' ') +
//                "," + refNatal.positive.toString().padEnd(8, ' ') +
//                "," + refNatal.consonance.toString().padEnd(8, ' ')
    }

    @ExperimentalUnsignedTypes
    fun getChartValueCalcDetailRow(evalProfile: Profile, evalNatal: ValueChart, sharedChart: ValueChart, showTime: Boolean = false): String {

        val utcString = if (showTime) EarthLocation.getDateTimeString(evalProfile.earthLocation.utcDateTime) else
            EarthLocation.getDateString(evalProfile.earthLocation.utcDateTime)

        return utcString //+
//                "," + evalNatal.stimulation.toString().padEnd(8, ' ') +
  //              "," + evalNatal.positive.toString().padEnd(8, ' ') +
      //          "," + evalNatal.consonance.toString().padEnd(8, ' ') +
    //            "," + sharedChart.stimulation.toString().padEnd(8, ' ') +
        //        "," + sharedChart.positive.toString().padEnd(8, ' ') +
          //      "," + sharedChart.consonance.toString().padEnd(8, ' ') //+
/*                "," + sharedChart.getRefImprovedStim().toInt().toString().padEnd(8, ' ') +
                "," + sharedChart.getRefImprovedPos().toInt().toString().padEnd(8, ' ') +
                "," + sharedChart.getRefImprovedCons().toInt().toString().padEnd(8, ' ') +
                "," + sharedChart.getRefImprovedAvg().toInt().toString().padEnd(8, ' ') +
                "," + sharedChart.getBalanceStim().toInt().toString().padEnd(8, ' ') +
                "," + sharedChart.getBalancePos().toInt().toString().padEnd(8, ' ') +
                "," + sharedChart.getBalanceCons().toInt().toString().padEnd(8, ' ') +
                "," + sharedChart.getRefParityStim().toInt().toString().padEnd(8, ' ') +
                "," + sharedChart.getRefParityPos().toInt().toString().padEnd(8, ' ') +
                "," + sharedChart.getRefParityCons().toInt().toString().padEnd(8, ' ') +
                "," + sharedChart.getRefParityAvg().toInt().toString().padEnd(8, ' ') +
                "," + sharedChart.getWorthwhile().toInt().toString().padEnd(8, ' ')
*/    }
}