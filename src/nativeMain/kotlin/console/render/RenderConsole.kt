package console.render

import Constants
import astro.base.*
import astro.render.RenderEarthLocation
import astro.state.HelpState
import console.base.ConsoleState
import platform.posix.system

object RenderConsole {

    const val celestialsTitleOffset = 1
    const val celestialsBottomSpaceOffset = 1
    const val housesTitleOffset = 2
    const val housesBottomSpaceOffset = 1
    const val aspectsBottomSpaceOffset = 2
    val celestialsRenderMaxIdx =
        (Celestial.values().size + celestialsTitleOffset + celestialsBottomSpaceOffset) - 1
//    val housesRenderMaxIdx = (CelestialHouse.values().size + housesTitleOffset + housesBottomSpaceOffset) - 1
    val aspectNatalCompRenderMaxIdx = (AspectCelestial.getChartSize() + aspectsBottomSpaceOffset)
    val detailsRenderMaxIdx = celestialsRenderMaxIdx + aspectNatalCompRenderMaxIdx + 2
    const val noRenderCelestials = -1
    const val noRenderHouseAspect = -1

    fun error(errorString: String) : String {
        println(errorString)
        RenderHandler.delayRun(1)

        return errorString
    }

    @ExperimentalUnsignedTypes
    fun renderHeader(consoleState : ConsoleState) {

        println( RenderState.getRenderHeader(consoleState) )
    }

    @ExperimentalUnsignedTypes
    fun renderEarthData(curEarthLocation: EarthLocation) {

        println( RenderEarthLocation.getRenderEarthLocationDataFirstLine(curEarthLocation) )
        println( RenderEarthLocation.getRenderEarthLocationDataSecondLine(curEarthLocation) )
    }

    @ExperimentalUnsignedTypes
    fun renderHelpLabels(curHelpState : HelpState) {

        println( curHelpState.getRenderHelpFirstLine() )
        println( curHelpState.getRenderHelpSecondLine() )
    }
}