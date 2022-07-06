package org.river.exertion.astro.base

import org.river.exertion.Constants.InvalidOrb
import org.river.exertion.Constants.normalizeDeg
import kotlin.math.abs

data class Aspect(val signFirst : Sign
    , val aspectCelestialFirst : AspectCelestial
    , val signSecond : Sign
    , val aspectCelestialSecond : AspectCelestial
    , val aspectAngle : AspectAngle
    , val orb : Double = 0.0
    ) {

    //calcOrb constructor
    constructor(signFirst : Sign
                , aspectCelestialFirst : AspectCelestial
                , aspectCelestialFirstAngle : Double
                , signSecond : Sign
                , aspectCelestialSecond: AspectCelestial
                , aspectCelestialSecondAngle : Double
                , aspectAngle : AspectAngle
    ) : this (
        signFirst
        , aspectCelestialFirst
        , signSecond
        , aspectCelestialSecond
        , aspectAngle
        , calcOrb(aspectAngle, aspectCelestialFirstAngle, aspectCelestialSecondAngle)
    )

    //findAspectAngle constructor
    constructor(signFirst : Sign
                , aspectCelestialFirst : AspectCelestial
                , aspectCelestialFirstAngle : Double
                , signSecond : Sign
                , aspectCelestialSecond: AspectCelestial
                , aspectCelestialSecondAngle : Double
                , orbLimit : Double = defaultOrbLimit
    ) : this (
        signFirst
        , aspectCelestialFirst
        , aspectCelestialFirstAngle
        , signSecond
        , aspectCelestialSecond
        , aspectCelestialSecondAngle
        , findAspectAngle(aspectCelestialFirstAngle, aspectCelestialSecondAngle, orbLimit)
    )

    override fun toString() = "Aspect($signFirst $aspectCelestialFirst $aspectAngle $signSecond $aspectCelestialSecond $orb)"

    companion object {

        private const val defaultOrbLimit = 8.0

        fun getEmptyAspect(firstAspectCelestialOverride : AspectCelestial = AspectCelestial.ASPECT_CELESTIAL_NONE,
                           secondAspectCelestialOverride : AspectCelestial = AspectCelestial.ASPECT_CELESTIAL_NONE
        ) = Aspect(
            Sign.SIGN_NONE, firstAspectCelestialOverride
            , Sign.SIGN_NONE, secondAspectCelestialOverride
            , AspectAngle.ASPECT_ANGLE_NONE, orb = InvalidOrb
        )

        fun calcNormAngleDiff(aspectCelestialFirstAngle : Double, aspectCelestialSecondAngle : Double) = (aspectCelestialSecondAngle - aspectCelestialFirstAngle).normalizeDeg()

        fun findAspectAngle(aspectCelestialFirstAngle : Double, aspectCelestialSecondAngle : Double, orbLimit : Double) : AspectAngle {

            var returnAspectAngle = AspectAngle.ASPECT_ANGLE_NONE
            var minOrb = 360.0

            val aspectCelestialAngleDiff = calcNormAngleDiff(aspectCelestialFirstAngle, aspectCelestialSecondAngle)
//            println("findAspectAngle() aspectCelestialAngleDiff: $aspectCelestialAngleDiff")
//            println(AspectAngle.values().filter { it.isAspectAngle() }.sortedByDescending { it.getAngleDegree() })

            //iterate through aspectAngles to find one within orbLimit
            for (aspectAngle in AspectAngle.values().filter { it.isAspectAngle() }.sortedByDescending { it.getAngleDegree() } ) {
//                println("findAspectAngle() iteration: $aspectAngle, ${abs(aspectAngle.getAngleDegree() - aspectCelestialAngleDiff)}")

                if (abs(aspectAngle.getAngleDegree() - aspectCelestialAngleDiff) <= orbLimit) {
                    val aspectAngleOrb = calcOrb(aspectAngle, aspectCelestialFirstAngle, aspectCelestialSecondAngle)

//                    println("$aspectAngleOrb, $minOrb")

                    if (aspectAngleOrb < minOrb) {
                        returnAspectAngle = aspectAngle
                        minOrb = aspectAngleOrb
                    }
                }
            }

//            println("selected aspectAngle:$returnAspectAngle, orb: $minOrb")
            return returnAspectAngle
        }

        fun calcOrb(aspectAngle : AspectAngle, aspectCelestialFirstAngle : Double, aspectCelestialSecondAngle : Double) =
            if (aspectAngle == AspectAngle.ASPECT_ANGLE_NONE) InvalidOrb
            else abs(aspectAngle.getAngleDegree() - calcNormAngleDiff(aspectCelestialFirstAngle, aspectCelestialSecondAngle) )
    }
}