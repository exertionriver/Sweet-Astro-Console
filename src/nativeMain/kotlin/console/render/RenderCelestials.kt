package console.render

import astro.base.*
import astro.render.RenderCelestial
import astro.render.RenderCelestialHouse
import console.state.ConsoleChartState
import astro.base.Eighths
import astro.render.RenderCelestial.Companion.getCelestialsDistanceLabel
import astro.render.RenderCelestial.Companion.getCelestialsHouseLabel
import astro.render.RenderCelestial.Companion.getCelestialsLabel
import astro.render.RenderCelestial.Companion.getCelestialsLongitudeLabel
import astro.render.RenderCelestial.Companion.getCelestialsLongitudeSpeedLabel
import astro.render.RenderCelestial.Companion.getCelestialsSignLabel
import astro.render.RenderCelestial.Companion.getCelestialsTransitCelestialsLabel
import astro.render.RenderCelestial.Companion.getCelestialsTransitHouseLabel
import astro.render.RenderEighths
import astro.render.RenderSign
import astro.state.ChartState
import kotlinx.cinterop.*
import platform.posix.fmod
import platform.posix.printf
import platform.posix.size_tVar
import platform.posix.snprintf
import profile.base.Profile

object RenderCelestials {

    val celestialsRenderMaxIdx = (Celestial.values().size + 1 + 1) - 1
    val transitCelestialRenderMax = Celestial.getTransitMax().ordinal

    fun renderCelestialsData(renderIdx: Int, curChartState: ChartState, refSnapshot: CelestialSnapshot, synSnapshot: CelestialSnapshot = refSnapshot) {
        printf("%s", prepareCelestialsData(renderIdx, curChartState, refSnapshot, synSnapshot))
    }

    private fun getCelestialTransitsLabel(transitHouseNumInt: Int, synCelestialData: Array<CelestialData>): String {

        var transitCelestialsLabel = ""
        val transitCelestialRenderMax = Celestial.getTransitMax().ordinal
        var celestialHouseNumInt: Int

        for (celestialTransitPlanetIdx in Celestial.SUN.ordinal..transitCelestialRenderMax) {

            celestialHouseNumInt = synCelestialData[celestialTransitPlanetIdx].celestialHouse.toInt()
            if (celestialHouseNumInt == transitHouseNumInt) {
                transitCelestialsLabel =
                    transitCelestialsLabel.plus(RenderCelestial.fromOrdinal(celestialTransitPlanetIdx)!!.getLabel())
            }
        }

        return transitCelestialsLabel
    }

//    fun renderCelestialPositionTransitData(celestialIdx: Int, refSnapshot: CelestialSnapshot, synSnapshot: CelestialSnapshot) {
//        printf("%s", prepareCelestialPositionTransitData(celestialIdx, refSnapshot, synSnapshot) )
//    }

    fun prepareCelestialPositionTransitData(
        celestialIdx: Int,
        refSnapshot: CelestialSnapshot,
        synSnapshot: CelestialSnapshot
    ) : String = memScoped {

        val refCelestialData = refSnapshot.refCelestialData
        val synCelestialData = synSnapshot.refCelestialData

        val celestialPositionTransitDataLineSize = alloc<size_tVar>()
        celestialPositionTransitDataLineSize.value = 64u

        val celestialPositionTransitDataLine = allocArray<ByteVar>(celestialPositionTransitDataLineSize.value.toInt())

        if (celestialIdx <= transitCelestialRenderMax) {
            val houseNum = refCelestialData[celestialIdx].transitHouse
            val houseNumInt = houseNum.toInt()
            val houseIdx = houseNumInt - 1

            val transitHousePart = fmod(houseNum, houseNumInt.toDouble())
            val transitPartOut = RenderEighths.fromName(Eighths.getEighth(transitHousePart).toString())!!.getLabel()

            val houseOut = RenderCelestialHouse.fromOrdinal(houseIdx)!!.getLabel()

            val transitCelestialsLabelMaxLength = 12
            val transitCelestialsLabel = getCelestialTransitsLabel(houseNumInt, synCelestialData)

            //https://stackoverflow.com/questions/2461667/centering-strings-with-printf
            val padlen: Int = ((transitCelestialsLabelMaxLength - transitCelestialsLabel.length) / 2)

            snprintf(celestialPositionTransitDataLine, celestialPositionTransitDataLineSize.value,
                "%s%4s%s%1s%s%2s%*s%s%*s%s%2s",
                Constants.KMAG,
                "",
                houseOut,
                "",
                transitPartOut,
                "",
                padlen,
                "",
                transitCelestialsLabel,
                padlen,
                "",
                Constants.KNRM,
                ""
            )
        }

        return celestialPositionTransitDataLine.toKString()
    }

    @ExperimentalUnsignedTypes
    fun prepareCelestialsData(
        renderIdx: Int,
        curChartState: ChartState,
        refSnapshot: CelestialSnapshot,
        synSnapshot: CelestialSnapshot = refSnapshot,
    ) : String = memScoped {
        val columnHeaderRenderIdx = 0

        val sectionWidth = 96
        val titleOffset = 1
        val noTransitTitleWidthOffset = 30
        val transitTitleWidthOffset = 10
        val noTransitDataWidthOffset = 29
        val transitDataWidthOffset = 5
        val transitEmptyDataWidthOffset = 29
        val celestialIdx = renderIdx - titleOffset

        val refCelestialData = refSnapshot.refCelestialData

        var showTransit = false

        if ( (refSnapshot != synSnapshot) && (curChartState == ChartState.SYNASTRY_CHART)) {
            showTransit = true
        }

        val celestialsDataLineSize = alloc<size_tVar>()
        celestialsDataLineSize.value = 128u

        val celestialsDataLine = allocArray<ByteVar>(celestialsDataLineSize.value.toInt())

        val celestialsHouseDataLineSize = alloc<size_tVar>()
        celestialsHouseDataLineSize.value = 64u

        val celestialsHouseDataLine = allocArray<ByteVar>(celestialsDataLineSize.value.toInt())

        val celestialsTransitDataLineSize = alloc<size_tVar>()
        celestialsTransitDataLineSize.value = 128u

        val celestialsTransitDataLine = allocArray<ByteVar>(celestialsDataLineSize.value.toInt())

        when {
            (renderIdx == columnHeaderRenderIdx) -> {
                snprintf(celestialsDataLine, celestialsDataLineSize.value,
                    "%s%3s%s%5s%s%5s%s%6s%s%5s%s%4s%s%6s%s%s",
                    Constants.KCYN, "", getCelestialsLabel(),
                    "", getCelestialsSignLabel(), "", getCelestialsLongitudeLabel(),
                    "", RenderSign.getSignLongitudeLabel(), "", getCelestialsHouseLabel(),
                    "", getCelestialsDistanceLabel(), "", getCelestialsLongitudeSpeedLabel(),
                    Constants.KNRM
                )
                snprintf(celestialsHouseDataLine, celestialsHouseDataLineSize.value, "")

                if (showTransit) {
                    //colors are handled in label methods
                    snprintf(celestialsTransitDataLine, celestialsTransitDataLineSize.value,
                        "%4s%s%4s%s%*s",
                        "",
                        getCelestialsTransitHouseLabel(),
                        "",
                        getCelestialsTransitCelestialsLabel(),
                        transitTitleWidthOffset,
                        ""
                    )
                } else snprintf(celestialsTransitDataLine, celestialsTransitDataLineSize.value, "%*s", noTransitTitleWidthOffset, "")
            }
            (renderIdx > celestialsRenderMaxIdx) -> { /* no-op */
                //data has been rendered, do nothing
            }
//            ( (renderIdx > celestialsRenderMaxIdx - celestialsBottomSpaceOffset) &&
//                (renderIdx <= celestialsRenderMaxIdx ) ) -> {
            (renderIdx == celestialsRenderMaxIdx) -> {
                snprintf(celestialsDataLine, celestialsDataLineSize.value,"%*s", sectionWidth, "")
            }
            else -> {
                val signOut: String = RenderSign.getSignLabelFromCelestialData(refCelestialData[celestialIdx])

                val signLongitudeOut: String = CelestialData.getFormattedSignLongitude(refCelestialData[celestialIdx].longitude)

                val houseNum: Double = refCelestialData[celestialIdx].celestialHouse
                val houseNumInt = houseNum.toInt()

                val houseIdx: Int = houseNumInt - 1
                val houseOut = RenderCelestialHouse.fromOrdinal(houseIdx)!!.getLabel()

                val housePart = fmod(houseNum, houseNumInt.toDouble())
                val housePartOut: String = RenderEighths.fromName(Eighths.getEighth(housePart).toString())!!.getLabel()

                snprintf(celestialsDataLine, celestialsDataLineSize.value,
                    "%3s%s%5s%s%3s%9.4f%3s%9s",
                    "",
                    RenderCelestial.fromOrdinal(celestialIdx)!!.getLabel(),
                    "",
                    signOut,
                    "",
                    refCelestialData[celestialIdx].longitude,
                    "",
                    signLongitudeOut
                )
                snprintf(celestialsHouseDataLine, celestialsHouseDataLineSize.value,
                    "%4s%s%1s%s%2s%8.4f%3s%8.4f",
                    "",
                    houseOut,
                    "",
                    housePartOut,
                    "",
                    refCelestialData[celestialIdx].distance,
                    "",
                    refCelestialData[celestialIdx].longitudeSpeed
                )

                if (showTransit) {
                    if (celestialIdx <= Celestial.getTransitMax().ordinal) {
                        snprintf(celestialsTransitDataLine, celestialsTransitDataLineSize.value,"%s%*s", prepareCelestialPositionTransitData(celestialIdx, refSnapshot, synSnapshot), transitDataWidthOffset, "")
                    } else
                        snprintf(celestialsTransitDataLine, celestialsTransitDataLineSize.value,"%*s", transitEmptyDataWidthOffset, "")
                } else snprintf(celestialsTransitDataLine, celestialsTransitDataLineSize.value,"%*s", noTransitDataWidthOffset, "")
            }
        }

        return celestialsDataLine.toKString() + celestialsHouseDataLine.toKString() + celestialsTransitDataLine.toKString()

    }
}