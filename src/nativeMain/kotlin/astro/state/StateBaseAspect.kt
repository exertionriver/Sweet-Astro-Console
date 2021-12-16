package astro.state

import astro.base.AspectCelestial
import astro.base.AspectType

data class StateBaseAspect(val aspectCelestialFirst : AspectCelestial
       , val aspectCelestialSecond: AspectCelestial
       , val aspectType : AspectType
)
{
    companion object {
        fun List<StateAspect>.stateBaseAspects() : List<StateBaseAspect> = this.map { it.getStateBaseAspect() }
    }
}
