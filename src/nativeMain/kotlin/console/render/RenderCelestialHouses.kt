package console.render

import astro.base.CelestialData
import astro.base.CelestialHouse
import astro.base.CelestialSnapshot
import astro.render.RenderCelestialHouse
import astro.render.RenderCelestialHouse.Companion.getHousesLabel
import astro.render.RenderCelestialHouse.Companion.getHousesLongitudeLabel
import astro.render.RenderCelestialHouse.Companion.getHousesSignLabel
import astro.render.RenderSign
import kotlinx.cinterop.*
import platform.posix.printf
import platform.posix.size_tVar
import platform.posix.snprintf
import profile.base.Profile
import swe.HouseName

object RenderCelestialHouses {

    val housesRenderMaxIdx = (CelestialHouse.values().size + 2 + 1)
    val housesTitleOffset = 2
    val housesBottomSpaceOffset = 1

    fun renderHouseData(renderIdx: Int, refSnapshot: CelestialSnapshot) {
        printf("%s", prepareHouseData(renderIdx, refSnapshot))
    }

    @ExperimentalUnsignedTypes
    fun prepareHouseData(renderIdx: Int, refSnapshot: CelestialSnapshot) : String = memScoped {

        val houseHeaderRenderIdx = 0
        val columnHeaderRenderIdx = 1
        val pofRowRenderIdx = housesRenderMaxIdx - 1
        val posRowRenderIdx = housesRenderMaxIdx

        val sectionWidth = 38
        val sectionEndWidth = 1
        val secondTitleWidthOffset = 2
        val houseIdx = renderIdx - housesTitleOffset
        val signOut: String
        val signLongitudeOut: String

        val houseDataLineSize = alloc<size_tVar>()
        houseDataLineSize.value = 64u

        val houseDataLine = allocArray<ByteVar>(houseDataLineSize.value.toInt())

        when {
            (renderIdx == houseHeaderRenderIdx) -> {
                val houseSysName = HouseName.getHouseName()
                val padlen: Int = ((sectionWidth - houseSysName.length) / 2)

                snprintf(houseDataLine, houseDataLineSize.value,
                    "%s%*s%s%*s%s%*s",
                    Constants.KCYN,
                    padlen, "",
                    houseSysName,
                    padlen, "",
                    Constants.KNRM,
                    sectionEndWidth - 1,
                    ""
                )
            }
            (renderIdx == columnHeaderRenderIdx) -> {
                snprintf(houseDataLine, houseDataLineSize.value,
                    "%s%1s%s%2s%s%3s%10s%4s%11s%*s%s%*s",
                    Constants.KCYN,
                    "",
                    getHousesLabel(),
                    "",
                    getHousesSignLabel(),
                    "",
                    getHousesLongitudeLabel(),
                    "",
                    RenderSign.getSignLongitudeLabel(),
                    secondTitleWidthOffset,
                    "",
                    Constants.KNRM,
                    sectionEndWidth - 1,
                    ""
                )
            }
            (renderIdx > housesRenderMaxIdx) -> {
                snprintf(houseDataLine, houseDataLineSize.value, "%*s%*s", sectionWidth, "", sectionEndWidth - 1, "")
            }
            (renderIdx == pofRowRenderIdx) -> {
                signOut = RenderSign.getSignLabelFromCelestialLongitude(refSnapshot.partOfFortuneData)

                signLongitudeOut = CelestialData.getFormattedSignLongitude(refSnapshot.partOfFortuneData)

                snprintf(houseDataLine, houseDataLineSize.value,
                    "%3s%s%5s%s%3s%9.4f%3s%9s%*s",
                    "",
                    RenderCelestialHouse.getPartOfFortuneLabel(),
                    "",
                    signOut,
                    "",
                    refSnapshot.partOfFortuneData,
                    "",
                    signLongitudeOut,
                    sectionEndWidth,
                    ""
                )
            }
            (renderIdx == posRowRenderIdx) -> {
                signOut = RenderSign.getSignLabelFromCelestialLongitude(refSnapshot.partOfSpiritData)

                signLongitudeOut = CelestialData.getFormattedSignLongitude(refSnapshot.partOfSpiritData)

                snprintf(houseDataLine, houseDataLineSize.value,
                    "%3s%s%5s%s%3s%9.4f%3s%9s%*s",
                    "",
                    RenderCelestialHouse.getPartOfSpiritLabel(),
                    "",
                    signOut,
                    "",
                    refSnapshot.partOfSpiritData,
                    "",
                    signLongitudeOut,
                    sectionEndWidth,
                    ""
                )
            }
            else -> {
                signOut =
                    RenderSign.getSignLabelFromCelestialLongitude(refSnapshot.refCelestialHouseData[houseIdx])

                signLongitudeOut = CelestialData.getFormattedSignLongitude(
                    refSnapshot.refCelestialHouseData[houseIdx],
                )

                snprintf(houseDataLine, houseDataLineSize.value,
                    "%3s%s%5s%s%3s%9.4f%3s%9s%*s",
                    "",
                    RenderCelestialHouse.fromOrdinal(houseIdx)!!.getLabel(),
                    "",
                    signOut,
                    "",
                    refSnapshot.refCelestialHouseData[houseIdx],
                    "",
                    signLongitudeOut,
                    sectionEndWidth,
                    ""
                )
            }
        }

        return houseDataLine.toKString()
    }
}