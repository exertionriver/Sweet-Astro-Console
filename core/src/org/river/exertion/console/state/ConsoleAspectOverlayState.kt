package org.river.exertion.console.state

import org.river.exertion.Constants.KEY_p
import org.river.exertion.Constants.KEY_x
import org.river.exertion.astro.state.AspectOverlayState
import org.river.exertion.astro.state.ChartState

@ExperimentalUnsignedTypes
object ConsoleAspectOverlayState {

    fun getDefaultState() = AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT

    fun AspectOverlayState.getNextState(curChartState: ChartState, input: Int) = this.getState(curChartState, input)

    fun AspectOverlayState.getToggledState(): AspectOverlayState {

        return when (this) {
            AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT -> AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT
            AspectOverlayState.ASPECT_NATCOMP_OVERLAY_SELECTIVE -> AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_SELECTIVE
            AspectOverlayState.ASPECT_NATCOMP_OVERLAY_HYBRID -> AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_HYBRID
            AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT -> AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT
            AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_SELECTIVE -> AspectOverlayState.ASPECT_NATCOMP_OVERLAY_SELECTIVE
            AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_HYBRID -> AspectOverlayState.ASPECT_NATCOMP_OVERLAY_HYBRID
        }
    }

    fun AspectOverlayState.getCycledState(): AspectOverlayState {

        return when (this) {
            AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT -> AspectOverlayState.ASPECT_NATCOMP_OVERLAY_SELECTIVE
            AspectOverlayState.ASPECT_NATCOMP_OVERLAY_SELECTIVE -> AspectOverlayState.ASPECT_NATCOMP_OVERLAY_HYBRID
            AspectOverlayState.ASPECT_NATCOMP_OVERLAY_HYBRID -> AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT
            AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT -> AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_SELECTIVE
            AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_SELECTIVE -> AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_HYBRID
            AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_HYBRID -> AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT
        }
    }

    fun AspectOverlayState.getState(chartState: ChartState, input: Int = 0): AspectOverlayState {

        val cycleKeyPress = (input == KEY_p)
        val resetKeyPress = (input == KEY_x)

        if (resetKeyPress) return getDefaultState()

        return when (this.isNatComp()) {
            true ->
                if (cycleKeyPress) {
                    if (chartState.isNatComp()) this.getCycledState()
                        else this.getCycledState().getToggledState()
                } else {
                    if (chartState.isNatComp()) this
                        else this.getToggledState()
                }
            else ->
                if (cycleKeyPress) {
                    if (chartState.isNatComp()) this.getCycledState().getToggledState()
                        else this.getCycledState()
                } else {
                    if (chartState.isNatComp()) this.getToggledState()
                        else this
                }
        }
    }
}