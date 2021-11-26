package astro.value

import astro.base.*
import astro.state.AnalysisState
import astro.state.ChartState
import astro.state.StateAspect

import kotlin.math.abs

data class ValueAspect (val stateAspect : StateAspect, val chartState: ChartState = ChartState.NATAL_CHART, val analysisState: AnalysisState = AnalysisState.NO_ANALYSIS) {

    fun getBaseAspect() = stateAspect.getBaseAspect()

    fun getBaseValue() = if (chartState == ChartState.SYNASTRY_CHART) Value(getPositiveBaseValue() / 2, getNegativeBaseValue() / 2) else Value(getPositiveBaseValue(), getNegativeBaseValue() )
    fun getModValue() = if (chartState == ChartState.SYNASTRY_CHART) Value(getPositiveModValue() / 2, getNegativeModValue() / 2) else Value(getPositiveModValue(), getNegativeModValue())

    fun getBaseModNetValue() = Value(getBaseValue().positive + getModValue().positive, getBaseValue().negative + getModValue().negative)

    fun getAspectModifier() = when (analysisState) {
        AnalysisState.ROMANTIC_ANALYSIS -> setRomanticModifier()
        else -> 0
    }
    fun getSignFirstModifier() = when (analysisState) {
        AnalysisState.ELEMENT_ANALYSIS -> getElementModifier(getBaseAspect().signFirst.getElement()).toInt()
        AnalysisState.MODE_ANALYSIS -> getModeModifier(getBaseAspect().signFirst.getMode()).toInt()
        else -> 0
    }
    fun getSignSecondModifier() = when (analysisState) {
        AnalysisState.ELEMENT_ANALYSIS -> getElementModifier(getBaseAspect().signSecond.getElement()).toInt()
        AnalysisState.MODE_ANALYSIS -> getModeModifier(getBaseAspect().signSecond.getMode()).toInt()
        else -> 0
    }
    fun getAspectCelestialFirstModifier() = when (analysisState) {
        AnalysisState.PLANET_ANALYSIS -> getCelestialModifier(getBaseAspect().aspectCelestialFirst).toInt()
        else -> 0
    }
    fun getAspectCelestialSecondModifier() = when (analysisState) {
        AnalysisState.PLANET_ANALYSIS -> getCelestialModifier(getBaseAspect().aspectCelestialSecond).toInt()
        else -> 0
    }

    private fun setRomanticModifier() : Int {

        //romMod is stored as 'orb' in baseAspect
        return ValueRomanticAspects.getRomanticAspects().firstOrNull() {
            (   (   ( (it.aspectCelestialFirst == getBaseAspect().aspectCelestialFirst) && (it.aspectCelestialSecond == getBaseAspect().aspectCelestialSecond) ) ||
                    ( (it.aspectCelestialFirst == getBaseAspect().aspectCelestialSecond) && (it.aspectCelestialSecond == getBaseAspect().aspectCelestialFirst) ) )
                    && (it.aspectAngle == getBaseAspect().aspectAngle) )}?.orb?.toInt() ?: 0
    }

    private fun getElementModifier(signElement : SignElement) : Double {
        val aspectCelestialFirstWeight = ValueAspectCelestial.fromName(getBaseAspect().aspectCelestialFirst.toString())!!.getWeight()
        val aspectCelestialSecondWeight = ValueAspectCelestial.fromName(getBaseAspect().aspectCelestialSecond.toString())!!.getWeight()
        val aspectCelestialBothWeight = aspectCelestialFirstWeight + aspectCelestialSecondWeight
        var returnWeight = 0.0

        if (getBaseAspect().signFirst.getElement() == signElement) returnWeight += aspectCelestialFirstWeight
        if (getBaseAspect().signSecond.getElement() == signElement) returnWeight += aspectCelestialSecondWeight

        return returnWeight / aspectCelestialBothWeight
    }

    private fun getModeModifier(signMode : SignMode) : Double {
        val aspectCelestialFirstWeight = ValueAspectCelestial.fromName(getBaseAspect().aspectCelestialFirst.toString())!!.getWeight()
        val aspectCelestialSecondWeight = ValueAspectCelestial.fromName(getBaseAspect().aspectCelestialSecond.toString())!!.getWeight()
        val aspectCelestialBothWeight = aspectCelestialFirstWeight + aspectCelestialSecondWeight
        var returnWeight = 0.0

        if (getBaseAspect().signFirst.getMode() == signMode) returnWeight += aspectCelestialFirstWeight
        if (getBaseAspect().signSecond.getMode() == signMode) returnWeight += aspectCelestialSecondWeight

        return returnWeight / aspectCelestialBothWeight
    }

    private fun getCelestialModifier(aspectCelestial: AspectCelestial) : Double {
        val aspectCelestialFirstWeight = ValueAspectCelestial.fromName(getBaseAspect().aspectCelestialFirst.toString())!!.getWeight()
        val aspectCelestialSecondWeight = ValueAspectCelestial.fromName(getBaseAspect().aspectCelestialSecond.toString())!!.getWeight()
        val aspectCelestialBothWeight = aspectCelestialFirstWeight + aspectCelestialSecondWeight
        var returnWeight = 0.0

        if (getBaseAspect().aspectCelestialFirst == aspectCelestial) returnWeight += aspectCelestialFirstWeight
        if (getBaseAspect().aspectCelestialSecond == aspectCelestial) returnWeight += aspectCelestialSecondWeight

        return returnWeight / aspectCelestialBothWeight
    }

    fun getPositiveModValue() : Int {

        var modPos = 0

        modPos += when {
            (getBaseValue().getNet() > 0) -> when {
                (getAspectModifier() > 0) -> (getBaseValue().positive * abs(getAspectModifier()) * .125).toInt()
                (getAspectModifier() < 0) -> (getBaseValue().positive * -abs(getAspectModifier()) * .125).toInt()
                else -> 0
            }
            else -> 0
        }
        modPos += when {
            (getBaseValue().getNet() > 0) -> when {
                (getSignFirstModifier() > 0) -> (getBaseValue().positive * abs(getSignFirstModifier()) * .125).toInt()
                (getSignFirstModifier() < 0) -> (getBaseValue().positive * -abs(getSignFirstModifier()) * .125).toInt()
                else -> 0
            }
            else -> 0
        }
        modPos += when {
            (getBaseValue().getNet() > 0) -> when {
                (getSignSecondModifier() > 0) -> (getBaseValue().positive * abs(getSignSecondModifier()) * .125).toInt()
                (getSignSecondModifier() < 0) -> (getBaseValue().positive * -abs(getSignSecondModifier()) * .125).toInt()
                else -> 0
            }
            else -> 0
        }
        modPos += when {
            (getBaseValue().getNet() > 0) -> when {
                (getAspectCelestialFirstModifier() > 0) -> (getBaseValue().positive * abs(getAspectCelestialFirstModifier()) * .125).toInt()
                (getAspectCelestialFirstModifier() < 0) -> (getBaseValue().positive * -abs(getAspectCelestialFirstModifier()) * .125).toInt()
                else -> 0
            }
            else -> 0
        }
        modPos += when {
            (getBaseValue().getNet() > 0) -> when {
                (getAspectCelestialSecondModifier() > 0) -> (getBaseValue().positive * abs(getAspectCelestialSecondModifier()) * .125).toInt()
                (getAspectCelestialSecondModifier() < 0) -> (getBaseValue().positive * -abs(getAspectCelestialSecondModifier()) * .125).toInt()
                else -> 0
            }
            else -> 0
        }

        return modPos
    }

    fun getNegativeModValue() : Int {

        var modNeg = 0

        modNeg += when {
            (getBaseValue().getNet() < 0) -> when {
                (getAspectModifier() > 0) -> (getBaseValue().negative * -abs(getAspectModifier()) * .125).toInt()
                (getAspectModifier() < 0) -> (getBaseValue().negative * abs(getAspectModifier()) * .125).toInt()
                else -> 0
            }
            else -> 0
        }
        modNeg += when {
            (getBaseValue().getNet() < 0) -> when {
                (getSignFirstModifier() > 0) -> (getBaseValue().negative * -abs(getSignFirstModifier()) * .125).toInt()
                (getSignFirstModifier() < 0) -> (getBaseValue().negative * abs(getSignFirstModifier()) * .125).toInt()
                else -> 0
            }
            else -> 0
        }
        modNeg += when {
            (getBaseValue().getNet() < 0) -> when {
                (getSignSecondModifier() > 0) -> (getBaseValue().negative * -abs(getSignSecondModifier()) * .125).toInt()
                (getSignSecondModifier() < 0) -> (getBaseValue().negative * abs(getSignSecondModifier()) * .125).toInt()
                else -> 0
            }
            else -> 0
        }
        modNeg += when {
            (getBaseValue().getNet() < 0) -> when {
                (getAspectCelestialFirstModifier() > 0) -> (getBaseValue().negative * -abs(getAspectCelestialFirstModifier()) * .125).toInt()
                (getAspectCelestialFirstModifier() < 0) -> (getBaseValue().negative * abs(getAspectCelestialFirstModifier()) * .125).toInt()
                else -> 0
            }
            else -> 0
        }
        modNeg += when {
            (getBaseValue().getNet() < 0) -> when {
                (getAspectCelestialSecondModifier() > 0) -> (getBaseValue().negative * -abs(getAspectCelestialSecondModifier()) * .125).toInt()
                (getAspectCelestialSecondModifier() < 0) -> (getBaseValue().negative * abs(getAspectCelestialSecondModifier()) * .125).toInt()
                else -> 0
            }
            else -> 0
        }

        return modNeg
    }

    fun getPositiveBaseValue() : Int {

        //hard aspects to sun / moon midpoint are positive
        return if ( (getBaseAspect().aspectCelestialFirst == AspectCelestial.ASPECT_SUN_MOON_MIDPOINT
                || getBaseAspect().aspectCelestialSecond == AspectCelestial.ASPECT_SUN_MOON_MIDPOINT) && (
                (getBaseAspect().aspectAngle.getAspectType() == AspectType.CONJUNCTION)
                        || (getBaseAspect().aspectAngle.getAspectType() == AspectType.OPPOSITION)
                        || (getBaseAspect().aspectAngle.getAspectType() == AspectType.SEMISQUARE)
                        || (getBaseAspect().aspectAngle.getAspectType() == AspectType.SQUARE) ) )
            getAspectBaseValue()
        else if (ValueAspectType.fromName(getBaseAspect().aspectAngle.getAspectType().toString())!!.isPositive()) getAspectBaseValue()
        else 0
    }

    fun getNegativeBaseValue() : Int {

//      https://en.wikipedia.org/wiki/Astrological_aspect#Conjunction
//      In particular, conjunctions involving the Sun, Venus, and/or Jupiter, in any of the three possible conjunction combinations, are
//      considered highly favourable, while conjunctions involving the Moon, Mars, and/or Saturn, again in any of the three possible
//      conjunction combinations, are considered highly unfavourable.
        return if ( (getBaseAspect().aspectAngle.getAspectType() == AspectType.CONJUNCTION) &&
                    (getBaseAspect().aspectCelestialFirst == AspectCelestial.ASPECT_MOON && (getBaseAspect().aspectCelestialSecond == AspectCelestial.ASPECT_MARS || getBaseAspect().aspectCelestialSecond == AspectCelestial.ASPECT_SATURN) )
                    || (getBaseAspect().aspectCelestialFirst == AspectCelestial.ASPECT_MARS && (getBaseAspect().aspectCelestialSecond == AspectCelestial.ASPECT_MOON || getBaseAspect().aspectCelestialSecond == AspectCelestial.ASPECT_SATURN) )
                    || (getBaseAspect().aspectCelestialFirst == AspectCelestial.ASPECT_SATURN && (getBaseAspect().aspectCelestialSecond == AspectCelestial.ASPECT_MOON || getBaseAspect().aspectCelestialSecond == AspectCelestial.ASPECT_MARS) ) )
            -getAspectBaseValue()
        else if (ValueAspectType.fromName(getBaseAspect().aspectAngle.getAspectType().toString())!!.isPositive()) 0
        else -getAspectBaseValue()
    }

    private fun getAspectBaseValue() : Int {

        if (ValueAspectType.fromName(getBaseAspect().aspectAngle.getAspectType().toString())!!.isNeutral()) return 0

        val weightFirst = ValueAspectCelestial.fromName(getBaseAspect().aspectCelestialFirst.toString())!!.getWeight()
        val weightSecond = ValueAspectCelestial.fromName(getBaseAspect().aspectCelestialSecond.toString())!!.getWeight()
        val weightAspect = stateAspect.aspectOverlayState.getAspectAngleOrb(getBaseAspect().aspectAngle)

        //full weightAspect at orb = 0, down to 0 weightAspect at the cusp of the orb
        val weightOrbAspect = ((60 * weightAspect) - (60 * getBaseAspect().orb)) / (60 * weightAspect);

//      debugging with lldb shows rounding error between AstroSWE and SAC -- e.g. aspectWeight for 4.9 in SAC is 4.89999999998 in AstroSWE, leading to rounding diffs
//        println (" orb)" + orb + ": 1W)" + weightFirst + " 2W)" + weightSecond + " aspectW)" + weightAspect + " orbAspectW)" + weightOrbAspect + " value)" + aspectValue )
        return ( (weightFirst * weightSecond) / 2 * weightAspect * weightOrbAspect).toInt()
    }

    override fun toString() = "ValueAspect($stateAspect $analysisState) : baseValue:${getBaseValue()} modValue:${getModValue()} AspectMod:${getAspectModifier()} signFirstMod:${getSignFirstModifier()} signSecondMod:${getSignSecondModifier()} aspectCelFirstMod:${getAspectCelestialFirstModifier()} aspectCelSecondMod:${getAspectCelestialSecondModifier()}"

    companion object {

        fun getEmptyAspect(firstAspectCelestialOverride : AspectCelestial = AspectCelestial.ASPECT_CELESTIAL_NONE,
                           secondAspectCelestialOverride : AspectCelestial = AspectCelestial.ASPECT_CELESTIAL_NONE) = ValueAspect(StateAspect.getEmptyAspect())
    }

}