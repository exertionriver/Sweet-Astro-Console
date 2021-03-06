package org.river.exertion.astro.state

import org.river.exertion.console.state.ConsoleAspectOverlayState
import org.river.exertion.console.state.ConsoleAspectsState
import org.river.exertion.console.state.ConsoleTimeAspectsState
import org.river.exertion.astro.base.Aspect
import org.river.exertion.astro.base.AspectAngle
import org.river.exertion.astro.base.AspectCelestial
import org.river.exertion.astro.base.Sign
import org.river.exertion.astro.value.Value
import org.river.exertion.astro.value.ValueAspect
import kotlin.math.abs

@OptIn(ExperimentalUnsignedTypes::class)
data class StateAspect(val signFirst : Sign
    , val aspectCelestialFirst : AspectCelestial
    , val signSecond : Sign
    , val aspectCelestialSecond: AspectCelestial
    , val aspectAngle : AspectAngle
    , val orb : Double = 0.0
    , val aspectsState : AspectsState
    , val timeAspectsState: TimeAspectsState
    , val aspectOverlayState : AspectOverlayState
    ) {

    fun getStateBaseAspect() = StateBaseAspect(this.aspectCelestialFirst, this.aspectCelestialSecond, this.aspectAngle.getAspectType())

    //calcOrb constructor
    constructor(
        signFirst : Sign
        , aspectCelestialFirst : AspectCelestial
        , aspectCelestialFirstAngle : Double
        , signSecond : Sign
        , aspectCelestialSecond: AspectCelestial
        , aspectCelestialSecondAngle : Double
        , aspectAngle : AspectAngle
        , aspectsState : AspectsState
        , timeAspectsState: TimeAspectsState
        , aspectOverlayState : AspectOverlayState
    ) : this (
        signFirst
        , aspectCelestialFirst
        , signSecond
        , aspectCelestialSecond
        , aspectAngle
        , Aspect.calcOrb(aspectAngle, aspectCelestialFirstAngle, aspectCelestialSecondAngle)
        , aspectsState
        , timeAspectsState
        , aspectOverlayState
    )

    //findAspectAngle constructor
    constructor(
        signFirst : Sign
        , aspectCelestialFirst : AspectCelestial
        , aspectCelestialFirstAngle : Double
        , signSecond : Sign
        , aspectCelestialSecond: AspectCelestial
        , aspectCelestialSecondAngle : Double
        , aspectsState : AspectsState
        , timeAspectsState: TimeAspectsState
        , aspectOverlayState : AspectOverlayState
    ) : this (
        signFirst
        , aspectCelestialFirst
        , aspectCelestialFirstAngle
        , signSecond
        , aspectCelestialSecond
        , aspectCelestialSecondAngle
        , findAspectAngle(aspectCelestialFirst, aspectCelestialFirstAngle, aspectCelestialSecond, aspectCelestialSecondAngle, aspectsState, timeAspectsState, aspectOverlayState)
        , aspectsState
        , timeAspectsState
        , aspectOverlayState
    )

    override fun toString() = "StateAspect($signFirst $aspectCelestialFirst $signSecond $aspectCelestialSecond $aspectAngle $orb $aspectsState $timeAspectsState $aspectOverlayState)"

    companion object{

        fun getEmptyAspect(firstAspectCelestialOverride : AspectCelestial = AspectCelestial.ASPECT_CELESTIAL_NONE,
                           secondAspectCelestialOverride : AspectCelestial = AspectCelestial.ASPECT_CELESTIAL_NONE) : StateAspect {

            val baseEmptyAspect = Aspect.getEmptyAspect(firstAspectCelestialOverride, secondAspectCelestialOverride)

            return StateAspect(
                baseEmptyAspect.signFirst,
                baseEmptyAspect.aspectCelestialFirst,
                baseEmptyAspect.signSecond,
                baseEmptyAspect.aspectCelestialSecond,
                baseEmptyAspect.aspectAngle,
                baseEmptyAspect.orb,
                ConsoleAspectsState.getDefaultState(),
                ConsoleTimeAspectsState.getDefaultState(),
                ConsoleAspectOverlayState.getDefaultState()
            )
        }

        fun calcOrbLimit (aspectOverlayState : AspectOverlayState
            , aspectCelestialFirst : AspectCelestial
            , aspectCelestialSecond : AspectCelestial
            , aspectAngle : AspectAngle
        ) =
                aspectOverlayState.getAspectAngleOrb(aspectAngle) * minOf(
                aspectOverlayState.getAspectCelestialOrbModifier(aspectCelestialFirst),
                aspectOverlayState.getAspectCelestialOrbModifier(aspectCelestialSecond) )

        fun findAspectAngle(aspectCelestialFirst : AspectCelestial
                            , aspectCelestialFirstAngle : Double
                            , aspectCelestialSecond : AspectCelestial
                            , aspectCelestialSecondAngle : Double
                            , aspectsState : AspectsState
                            , timeAspectsState: TimeAspectsState
                            , aspectOverlayState : AspectOverlayState
        ) : AspectAngle {

//            println("$aspectCelestialFirst $aspectCelestialFirstAngle $aspectCelestialSecond $aspectCelestialSecondAngle $aspectsState $timeAspectsState $aspectOverlayState")
            var returnAspectAngle = AspectAngle.ASPECT_ANGLE_NONE
            var minOrb = 360.0

            val maxOrbLimit = aspectOverlayState.getAspectAngleOrb(AspectAngle.CONJUNCTION_0)

            val aspectCelestialAngleDiff = Aspect.calcNormAngleDiff(aspectCelestialFirstAngle, aspectCelestialSecondAngle)
//             println("findAspectAngle() aspectCelestialAngleDiff: $aspectCelestialAngleDiff")
//             println(AspectAngle.values().filter { it.isAspectAngle() }.sortedByDescending { it.getAngleDegree() })

            //iterate through aspectAngles
            for (aspectAngle in AspectAngle.values().filter { it.isAspectAngle() }.sortedByDescending { it.getAngleDegree() } ) {
                if ((aspectsState == AspectsState.MAJOR_ASPECTS) && (!aspectAngle.getAspectType().isMajor() )) continue ;
                if ((aspectsState == AspectsState.MINOR_ASPECTS) && (!aspectAngle.getAspectType().isMajor() && !aspectAngle.getAspectType().isMinor()) ) continue ;
                if ((timeAspectsState == TimeAspectsState.TIME_ASPECTS_DISABLED) && (aspectCelestialFirst.isTimeAspect())) continue ;
                if ((timeAspectsState == TimeAspectsState.TIME_ASPECTS_DISABLED) && (aspectCelestialSecond.isTimeAspect())) continue ;

                val orbLimit = calcOrbLimit(aspectOverlayState, aspectCelestialFirst, aspectCelestialSecond, aspectAngle)

//                println("findAspectAngle() iteration: $aspectAngle, ${abs(aspectAngle.getAngleDegree() - aspectCelestialAngleDiff)}, ${orbLimit}")

                if (abs(aspectAngle.getAngleDegree() - aspectCelestialAngleDiff) <= orbLimit) {
                    val aspectAngleOrb = Aspect.calcOrb(aspectAngle, aspectCelestialFirstAngle, aspectCelestialSecondAngle)

//                     println("$aspectAngleOrb, $minOrb")

                    if (aspectAngleOrb < minOrb) {
                        returnAspectAngle = aspectAngle
                        minOrb = aspectAngleOrb
                    }
                }
            }

//             println("selected aspectAngle:$returnAspectAngle, orb: $minOrb")
            return returnAspectAngle
        }

        fun List<StateAspect>.stateAspectReduceBase(chartState: ChartState = ChartState.NATAL_CHART, analysisState: AnalysisState = AnalysisState.NO_ANALYSIS) : Value {
            val pos = this.sumOf { ValueAspect(it, chartState, analysisState).getPositiveBaseValue() }
            val neg = this.sumOf { ValueAspect(it, chartState, analysisState).getNegativeBaseValue() }

            return Value(pos, neg)
        }

        fun List<StateAspect>.stateAspectReduceMod(chartState: ChartState = ChartState.NATAL_CHART, analysisState: AnalysisState = AnalysisState.NO_ANALYSIS) : Value {
            val pos = this.sumOf { ValueAspect(it, chartState, analysisState).getPositiveModValue() }
            val neg = this.sumOf { ValueAspect(it, chartState, analysisState).getNegativeModValue() }

            return Value(pos, neg)
        }
    }
}