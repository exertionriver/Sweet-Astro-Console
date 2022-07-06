package org.river.exertion.analysis

import kotlinx.datetime.LocalDateTime
import org.river.exertion.astro.base.AspectCelestial
import org.river.exertion.astro.state.ChartState
import org.river.exertion.astro.value.Value

data class AstroDataRow(val refUTC : LocalDateTime, val pollUTC : LocalDateTime, val pollAspectCelestials : Map<AspectCelestial, Double>, val chartState : ChartState, val value : Value)
