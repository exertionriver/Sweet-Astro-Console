package org.river.exertion.astro.state

import org.river.exertion.astro.base.AspectCelestial
import org.river.exertion.astro.base.AspectType

data class StateBaseAspect(val aspectCelestialFirst : AspectCelestial
       , val aspectCelestialSecond: AspectCelestial
       , val aspectType : AspectType
)
{
    companion object {
        fun List<StateAspect>.stateBaseAspects() : List<StateBaseAspect> = this.map { it.getStateBaseAspect() }
    }
}
