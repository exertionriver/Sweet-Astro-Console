package org.river.exertion.astro.value

import org.river.exertion.astro.base.*

//data gathered from https://cafeastrology.com/synastry-2.html
//see: https://cafeastrology.com/termsofuse.html "Limited License"

object ValueRomanticAspects {

/*It is desirable to have:

    At least one combination in the Strongest Compatibility (weight 4) category
    At least 4 or 5 in the Very Strong Compatibility (weight 3) category
    Zero or at most 1 in the Red Alert (weight -4) category
    At most 3 in the weight -3 category
    At least 3 but no more than 7 in the weight -2 category [Note that it IS desirable to have some challenging aspects in a relationship.]
*/
    fun getExtendedAspects() : List<Aspect> {

        val extendedAspects: List<Aspect> = listOf(

//Strongest Compatibility in Synastry (weight 4) // "Blue Ribbons"

//    Sun hard aspect (conjunct, semi-square, square, opposition) Sun/Moon midpoint 4
            Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.CONJUNCTION_0, 4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.OPPOSITION_180, 4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SQUARE_90, 4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SQUARE_270, 4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SEMISQUARE_45, 4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SEMISQUARE_315, 4.0)
//    Sun in 7th house 4
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SEVENTH_HOUSE, AspectAngle.CONJUNCTION_0, 4.0)
//    Moon hard aspect (conjunct, semi-square, square, opposition) Sun/Moon midpoint 4
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.CONJUNCTION_0, 4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.OPPOSITION_180, 4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SQUARE_90, 4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SQUARE_270, 4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SEMISQUARE_45, 4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SEMISQUARE_315, 4.0)

//Very Strong Compatibility Factors in Synastry (weight 3)

//    Sun in 1st house 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_FIRST_HOUSE, AspectAngle.CONJUNCTION_0, 3.0)
//    Moon in 7th house 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_SEVENTH_HOUSE, AspectAngle.CONJUNCTION_0, 3.0)
//    Moon in 1st house 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_FIRST_HOUSE, AspectAngle.CONJUNCTION_0, 3.0)
//    Venus hard aspect (conjunct, semi-square, square, opposition) Sun/Moon midpoint 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.CONJUNCTION_0, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.OPPOSITION_180, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SQUARE_90, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SQUARE_270, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SEMISQUARE_45, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SEMISQUARE_315, 3.0)
//    Venus in 7th house 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SEVENTH_HOUSE, AspectAngle.CONJUNCTION_0, 3.0)
//    Mars hard aspect (conjunct, semi-square, square, opposition) Sun/Moon midpoint 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.CONJUNCTION_0, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.OPPOSITION_180, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SQUARE_90, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SQUARE_270, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SEMISQUARE_45, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SEMISQUARE_315, 3.0)
//    Ascendant hard aspect (conjunct, semi-square, square, opposition) Sun/Moon midpoint 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.CONJUNCTION_0, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.OPPOSITION_180, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SQUARE_90, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SQUARE_270, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SEMISQUARE_45, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SEMISQUARE_315, 3.0)
//    Ascendant in 7th house 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_SEVENTH_HOUSE, AspectAngle.CONJUNCTION_0, 3.0)
//    Vertex hard aspect (conjunct, semi-square, square, opposition) Sun/Moon midpoint 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.CONJUNCTION_0, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.OPPOSITION_180, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SQUARE_90, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SQUARE_270, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SEMISQUARE_45, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN_MOON_MIDPOINT, AspectAngle.SEMISQUARE_315, 3.0)

//Good Compatibility Factors in Synastry (weight 2)

//    Venus in 1st house 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_FIRST_HOUSE, AspectAngle.CONJUNCTION_0, 2.0)
//    Jupiter in 7th house 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_SEVENTH_HOUSE, AspectAngle.CONJUNCTION_0, 2.0)
        )

        return extendedAspects
    }

    fun getRomanticAspects() : List<Aspect> {

        val romanticAspects: List<Aspect> = listOf(

//Strongest Compatibility in Synastry (weight 4) // "Blue Ribbons"

//    Sun conjunct Descendant 4
            Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.OPPOSITION_180, 4.0)
//    Sun conjunct or opposition Vertex 4
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.CONJUNCTION_0, 4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.OPPOSITION_180, 4.0)
//    Sun conjunct North Node 4
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.CONJUNCTION_0, 4.0)
//    Moon conjunct Descendant 4
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.OPPOSITION_180, 4.0)
//    Moon conjunct or opposition Vertex 4
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.CONJUNCTION_0, 4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.OPPOSITION_180, 4.0)
//    Moon conjunct North Node 4
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.CONJUNCTION_0, 4.0)
//    Ascendant conjunct North Node 4
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.CONJUNCTION_0, 4.0)

//Very Strong Compatibility Factors in Synastry (weight 3)

//    Sun trine or sextile Sun 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, AspectAngle.SEXTILE_300, 3.0)
//    Sun conjunct, trine, or sextile Moon 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, AspectAngle.CONJUNCTION_0, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, AspectAngle.SEXTILE_300, 3.0)
//    Sun conjunct Venus 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.CONJUNCTION_0, 3.0)
//    Sun trine Venus 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.TRINE_240, 3.0)
//    Sun trine or sextile Mars 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SEXTILE_300, 3.0)
//    Sun conjunct, sextile, or trine Jupiter 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.CONJUNCTION_0, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SEXTILE_300, 3.0)
//    Sun sextile or trine Saturn 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SEXTILE_300, 3.0)
//    Sun conjunct, trine, or sextile Chiron 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_CHIRON, AspectAngle.CONJUNCTION_0, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_CHIRON, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_CHIRON, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_CHIRON, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_CHIRON, AspectAngle.SEXTILE_300, 3.0)
//    Sun conjunct Ascendant 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.CONJUNCTION_0, 3.0)
//    Sun conjunct South Node 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.OPPOSITION_180, 3.0)
//    Moon conjunct, sextile, or trine Moon 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, AspectAngle.CONJUNCTION_0, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, AspectAngle.SEXTILE_300, 3.0)
//    Moon conjunct, sextile, or trine Venus 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.CONJUNCTION_0, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.SEXTILE_300, 3.0)
//    Moon sextile or trine Mars 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SEXTILE_300, 3.0)
//    Moon conjunct, sextile, or trine Jupiter 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.CONJUNCTION_0, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SEXTILE_300, 3.0)
//    Moon sextile or trine Saturn 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SEXTILE_300, 3.0)
//    Moon conjunct, trine, or sextile Chiron 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_CHIRON, AspectAngle.CONJUNCTION_0, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_CHIRON, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_CHIRON, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_CHIRON, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_CHIRON, AspectAngle.SEXTILE_300, 3.0)
//    Moon conjunct Ascendant 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.CONJUNCTION_0, 3.0)
//    Moon sextile or trine Ascendant 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_300, 3.0)
//    Moon conjunct IC 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MIDHEAVEN, AspectAngle.OPPOSITION_180, 3.0)
//    Moon conjunct South Node 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.OPPOSITION_180, 3.0)
//    Mercury conjunct, sextile, or trine Jupiter 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.CONJUNCTION_0, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SEXTILE_300, 3.0)
//    Venus conjunct, sextile, trine, or opposition Venus 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.CONJUNCTION_0, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.SEXTILE_300, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.OPPOSITION_180, 3.0)
//    Venus conjunct, sextile, or trine Mars 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.CONJUNCTION_0, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SEXTILE_300, 3.0)
//    Venus conjunct Jupiter 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.CONJUNCTION_0, 3.0)
//    Venus sextile or trine Saturn 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SEXTILE_300, 3.0)
//    Venus conjunct, trine, or sextile Chiron 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_CHIRON, AspectAngle.CONJUNCTION_0, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_CHIRON, AspectAngle.TRINE_120, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_CHIRON, AspectAngle.TRINE_240, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_CHIRON, AspectAngle.SEXTILE_60, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_CHIRON, AspectAngle.SEXTILE_300, 3.0)
//    Venus conjunct Ascendant 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.CONJUNCTION_0, 3.0)
//    Venus conjunct Descendant 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.OPPOSITION_180, 3.0)
//    Ascendant conjunct South Node 3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.OPPOSITION_180, 3.0)
//    Nodes of the Moon conjunct IC/MC 3
            //n.node
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, Sign.SIGN_NONE, AspectCelestial.ASPECT_MIDHEAVEN, AspectAngle.CONJUNCTION_0, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, Sign.SIGN_NONE, AspectCelestial.ASPECT_MIDHEAVEN, AspectAngle.OPPOSITION_180, 3.0)
            //s.node
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, Sign.SIGN_NONE, AspectCelestial.ASPECT_MIDHEAVEN, AspectAngle.OPPOSITION_180, 3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, Sign.SIGN_NONE, AspectCelestial.ASPECT_MIDHEAVEN, AspectAngle.CONJUNCTION_0, 3.0)

//Good Compatibility Factors in Synastry (weight 2)

//    Sun opposition Sun 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, AspectAngle.OPPOSITION_180, 2.0)
//    Sun opposition Moon 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, AspectAngle.OPPOSITION_180, 2.0)
//    Sun conjunct Mercury 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.CONJUNCTION_0, 2.0)
//    Sun sextile or trine Mercury 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.SEXTILE_300, 2.0)
//    Sun sextile Venus 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.SEXTILE_300, 2.0)
//    Sun conjunct Mars 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.CONJUNCTION_0, 2.0)
//    Sun sextile or trine Uranus 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SEXTILE_300, 2.0)
//    Sun sextile or trine Ascendant 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_300, 2.0)
//    Sun conjunct IC 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MIDHEAVEN, AspectAngle.OPPOSITION_180, 2.0)
//    Sun sextile or trine Nodes of the Moon 2
            //n.node
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SEXTILE_300, 2.0)
            //s.node duplicates n.node
//    Sun square Nodes of the Moon 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SQUARE_90, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SQUARE_270, 2.0)
//    Moon conjunct, sextile, or trine Mercury 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.CONJUNCTION_0, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.SEXTILE_300, 2.0)
//    Moon sextile or trine Uranus 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SEXTILE_300, 2.0)
//    Moon sextile or trine Neptune 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SEXTILE_300, 2.0)
//    Moon sextile or trine Pluto 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SEXTILE_300, 2.0)
//    Moon trine or sextile Nodes of the Moon 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SEXTILE_300, 2.0)
            //s.node duplicates n.node
//    Moon square Nodes of the Moon 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SQUARE_90, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SQUARE_270, 2.0)
            //s.node duplicates n.node
//    Mercury sextile, conjunct, or trine Mercury 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.CONJUNCTION_0, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.SEXTILE_300, 2.0)
//    Mercury conjunct, sextile, or trine Venus 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.CONJUNCTION_0, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.SEXTILE_300, 2.0)
//    Mercury sextile or trine Mars 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SEXTILE_300, 2.0)
//    Mercury conjunct the Ascendant 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.CONJUNCTION_0, 2.0)
//    Mercury conjunct the Descendant 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.OPPOSITION_180, 2.0)
//    Mercury conjunct IC or MC 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MIDHEAVEN, AspectAngle.CONJUNCTION_0, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MIDHEAVEN, AspectAngle.OPPOSITION_180, 2.0)
//    Venus square or opposition Mars 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SQUARE_90, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SQUARE_270, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.OPPOSITION_180, 2.0)
//    Venus trine or sextile Jupiter 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SEXTILE_300, 2.0)
//    Venus conjunct Saturn 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.CONJUNCTION_0, 2.0)
//    Venus sextile or trine Uranus 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SEXTILE_300, 2.0)
//    Venus conjunct Neptune 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.CONJUNCTION_0, 2.0)
//    Venus trine or sextile Neptune 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SEXTILE_300, 2.0)
//    Venus conjunct, trine or sextile Pluto 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.CONJUNCTION_0, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SEXTILE_300, 2.0)
//    Venus sextile or trine Ascendant 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_300, 2.0)
//    Venus conjunct IC or MC 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MIDHEAVEN, AspectAngle.CONJUNCTION_0, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MIDHEAVEN, AspectAngle.OPPOSITION_180, 2.0)
//    Venus conjunct or opposition Vertex 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.CONJUNCTION_0, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.OPPOSITION_180, 2.0)
//    Venus in aspect to Nodes of the Moon 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.CONJUNCTION_0, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SQUARE_90, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.OPPOSITION_180, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SQUARE_270, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SEXTILE_300, 2.0)
//    Mars conjunct, sextile, or trine Mars 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.CONJUNCTION_0, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SEXTILE_300, 2.0)
//    Mars in aspect to Jupiter 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.CONJUNCTION_0, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SQUARE_90, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.OPPOSITION_180, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SQUARE_270, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SEXTILE_300, 2.0)
//    Mars conjunct, trine, or sextile Pluto 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.CONJUNCTION_0, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SEXTILE_300, 2.0)
//    Mars conjunct, sextile, or trine the Ascendant 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.CONJUNCTION_0, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_300, 2.0)
//    Mars conjunct the Descendant 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.OPPOSITION_180, 2.0)
//    Mars conjunct or opposition Vertex 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.CONJUNCTION_0, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.OPPOSITION_180, 2.0)
//    Jupiter in aspect to Jupiter 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.CONJUNCTION_0, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SQUARE_90, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.OPPOSITION_180, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SQUARE_270, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SEXTILE_300, 2.0)
//    Jupiter conjunct the Ascendant 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.CONJUNCTION_0, 2.0)
//    Jupiter conjunct the Descendant 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.OPPOSITION_180, 2.0)
//    Jupiter conjunct or opposition the Vertex 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.CONJUNCTION_0, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.OPPOSITION_180, 2.0)
//    Ascendant sextile or trine Ascendant 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_300, 2.0)
//    Ascendant opposition Ascendant 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.OPPOSITION_180, 2.0)
//    Ascendant trine or sextile Nodes of the Moon 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.TRINE_120, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.TRINE_240, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SEXTILE_60, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SEXTILE_300, 2.0)
            //s.node duplicates n.node
//    Ascendant square Nodes of the Moon 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SQUARE_90, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SQUARE_270, 2.0)
            //s.node duplicates n.node
//    Vertex conjunct either Node of the Moon 2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.CONJUNCTION_0, 2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.OPPOSITION_180, 2.0)

//Helpful Compatibility Factors in Synastry (weight 1)

//    Sun conjunct Sun 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, AspectAngle.CONJUNCTION_0, 1.0)
//    Sun square or opposition Jupiter 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.OPPOSITION_180, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SQUARE_90, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SQUARE_270, 1.0)
//    Sun sextile or trine Neptune 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SEXTILE_300, 1.0)
//    Sun conjunct, sextile or trine Pluto 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.CONJUNCTION_0, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SEXTILE_300, 1.0)
//    Moon opposition Moon 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, AspectAngle.OPPOSITION_180, 1.0)
//    Moon square or opposition Venus 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.OPPOSITION_180, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.SQUARE_90, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.SQUARE_270, 1.0)
//    Moon square or opposition Jupiter 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.OPPOSITION_180, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SQUARE_90, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SQUARE_270, 1.0)
//    Moon conjunct Neptune 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.CONJUNCTION_0, 1.0)
//    Moon conjunct Pluto 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.CONJUNCTION_0, 1.0)
//    Mercury square or opposition Jupiter 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.OPPOSITION_180, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SQUARE_90, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SQUARE_270, 1.0)
//    Mercury sextile or trine Saturn 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SEXTILE_300, 1.0)
//    Mercury in aspect to Uranus 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.CONJUNCTION_0, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SQUARE_90, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.OPPOSITION_180, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SQUARE_270, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SEXTILE_300, 1.0)
//    Mercury in aspect to Neptune 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.CONJUNCTION_0, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SQUARE_90, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.OPPOSITION_180, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SQUARE_270, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SEXTILE_300, 1.0)
//    Mercury in aspect to Pluto 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.CONJUNCTION_0, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SQUARE_90, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.OPPOSITION_180, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SQUARE_270, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SEXTILE_300, 1.0)
//    Mercury sextile or trine Ascendant 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_300, 1.0)
//    Mercury conjunct or opposition Vertex 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.CONJUNCTION_0, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.OPPOSITION_180, 1.0)
//    Mercury in aspect to Nodes of the Moon 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.CONJUNCTION_0, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SQUARE_90, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.OPPOSITION_180, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SQUARE_270, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SEXTILE_300, 1.0)
//    Venus square Venus 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.SQUARE_90, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.SQUARE_270, 1.0)
//    Venus square or opposite Jupiter 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.OPPOSITION_180, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SQUARE_90, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, AspectAngle.SQUARE_270, 1.0)
//    Venus conjunct Uranus 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.CONJUNCTION_0, 1.0)
//    Venus square Ascendant 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SQUARE_90, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SQUARE_270, 1.0)
//    Mars sextile or trine Saturn 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SEXTILE_300, 1.0)
//    Mars sextile or trine Neptune 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_300, 1.0)
//    Mars conjunct, sextile, or trine Uranus 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.CONJUNCTION_0, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SEXTILE_300, 1.0)
//    Mars in aspect to Nodes of the Moon 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.CONJUNCTION_0, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SQUARE_90, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.OPPOSITION_180, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SQUARE_270, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SEXTILE_300, 1.0)
//    Jupiter conjunct, sextile, or trine Saturn 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.CONJUNCTION_0, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SEXTILE_300, 1.0)
//    Jupiter in aspect to Uranus 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.CONJUNCTION_0, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SQUARE_90, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.OPPOSITION_180, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SQUARE_270, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SEXTILE_300, 1.0)
//    Jupiter conjunct, sextile, or trine Neptune 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.CONJUNCTION_0, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SEXTILE_300, 1.0)
//    Jupiter conjunct, trine or sextile Pluto 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.CONJUNCTION_0, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SEXTILE_300, 1.0)
//    Jupiter sextile, trine, or square Ascendant 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_300, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SQUARE_90, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SQUARE_270, 1.0)
//    Jupiter in aspect to the Nodes of the Moon 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.CONJUNCTION_0, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SQUARE_90, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.OPPOSITION_180, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SQUARE_270, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SEXTILE_300, 1.0)
//    Saturn sextile or trine Saturn 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SEXTILE_300, 1.0)
//    Saturn sextile or trine Ascendant 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_120, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.TRINE_240, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_60, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SEXTILE_300, 1.0)
//    Pluto conjunct Descendant or Vertex 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.OPPOSITION_180, 1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.CONJUNCTION_0, 1.0)
//    Ascendant conjunct Ascendant 1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.CONJUNCTION_0, 1.0)

//Very Challenging Factors in Synastry – Red Alerts (weight -4)

//    Moon square Saturn -4 Red Alert
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SQUARE_90, -4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SQUARE_270, -4.0)
//    Mercury square Mercury -4 Red Alert
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.SQUARE_90, -4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.SQUARE_270, -4.0)
//    Venus square Saturn -4 Red Alert
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SQUARE_90, -4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SQUARE_270, -4.0)
//    Mars square or opposition Saturn -4 Red Alert
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.OPPOSITION_180, -4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SQUARE_90, -4.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SQUARE_270, -4.0)

//Challenging Factors in Synastry (weight -3)

//    Sun `square Moon` -3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, AspectAngle.SQUARE_90, -3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, AspectAngle.SQUARE_270, -3.0)
//    Moon square Moon -3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, AspectAngle.SQUARE_90, -3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, AspectAngle.SQUARE_270, -3.0)
//    Moon square Mars -3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SQUARE_90, -3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SQUARE_270, -3.0)
//    Moon conjunct or opposition Saturn -3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.CONJUNCTION_0, -3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.OPPOSITION_180, -3.0)
//    Moon square or opposition Neptune -3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.OPPOSITION_180, -3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SQUARE_90, -3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SQUARE_270, -3.0)
//    Moon square or opposition Pluto -3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.OPPOSITION_180, -3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SQUARE_90, -3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SQUARE_270, -3.0)
//    Mercury square or opposition Neptune -3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.OPPOSITION_180, -3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SQUARE_90, -3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SQUARE_270, -3.0)
//    Venus opposition Saturn -3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.OPPOSITION_180, -3.0)
//    Venus square or opposition Neptune -3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.OPPOSITION_180, -3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SQUARE_90, -3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SQUARE_270, -3.0)
//    Mars conjunct Saturn -3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.CONJUNCTION_0, -3.0)
//    Mars square or opposition Pluto -3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.OPPOSITION_180, -3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SQUARE_90, -3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SQUARE_270, -3.0)
//    Uranus conjunct Descendant or Vertex -3
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.OPPOSITION_180, -3.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.CONJUNCTION_0, -3.0)

//Somewhat Challenging Factors in Synastry (weight -2)

//    Sun square Sun -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, AspectAngle.SQUARE_270, -2.0)
//    Sun square or opposition Mars -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SQUARE_270, -2.0)
//    Sun conjunct, square or opposition Saturn -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.CONJUNCTION_0, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SQUARE_270, -2.0)
//    Sun conjunct, square, or opposition Neptune -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.CONJUNCTION_0, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SQUARE_270, -2.0)
//    Sun square or opposition Pluto -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SQUARE_270, -2.0)
//    Sun square Ascendant -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SQUARE_270, -2.0)
//    Moon square or opposition Mercury -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.SQUARE_270, -2.0)
//    Moon opposition Mars -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.OPPOSITION_180, -2.0)
//    Moon square or opposition Uranus -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SQUARE_270, -2.0)
//    Moon square Ascendant -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SQUARE_270, -2.0)
//    Mercury opposition Mercury -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.OPPOSITION_180, -2.0)
//    Mercury square or opposition Mars -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SQUARE_270, -2.0)
//    Mercury conjunct, square, or opposition Saturn -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.CONJUNCTION_0, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SQUARE_270, -2.0)
//    Venus square or opposition Uranus -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SQUARE_270, -2.0)
//    Venus square or opposition Pluto -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SQUARE_270, -2.0)
//    Mars square or opposition Mars -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.SQUARE_270, -2.0)
//    Mars square or opposition Uranus -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SQUARE_270, -2.0)
//    Mars conjunct, square, or opposition Neptune -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.CONJUNCTION_0, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SQUARE_270, -2.0)
//    Saturn conjunct, square or opposition Ascendant -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.CONJUNCTION_0, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SQUARE_270, -2.0)
//    Saturn conjunct or opposition Vertex -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.CONJUNCTION_0, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.OPPOSITION_180, -2.0)
//    Saturn conjunct or square Nodes of the Moon -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.CONJUNCTION_0, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_NORTH_NODE, AspectAngle.SQUARE_270, -2.0)
//    Neptune conjunct Descendant, Ascendant, or Vertex -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.CONJUNCTION_0, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.OPPOSITION_180, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, Sign.SIGN_NONE, AspectCelestial.ASPECT_VERTEX, AspectAngle.CONJUNCTION_0, -2.0)
//    Ascendant square Ascendant -2
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SQUARE_90, -2.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SQUARE_270, -2.0)

//Minor Challenges in Synastry (weight -1)

//    Sun square or opposition Mercury -1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.OPPOSITION_180, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.SQUARE_90, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, AspectAngle.SQUARE_270, -1.0)
//    Sun square or opposition Venus -1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.OPPOSITION_180, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.SQUARE_90, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.SQUARE_270, -1.0)
//    Sun conjunct, square or opposition Uranus -1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.CONJUNCTION_0, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.OPPOSITION_180, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SQUARE_90, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SUN, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.SQUARE_270, -1.0)
//    Moon conjunct Mars -1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.CONJUNCTION_0, -1.0)
//    Moon conjunct Uranus -1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MOON, Sign.SIGN_NONE, AspectCelestial.ASPECT_URANUS, AspectAngle.CONJUNCTION_0, -1.0)
//    Mercury square or opposition Venus -1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.OPPOSITION_180, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.SQUARE_90, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_VENUS, AspectAngle.SQUARE_270, -1.0)
//    Mercury conjunct Mars -1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, AspectAngle.CONJUNCTION_0, -1.0)
//    Mercury square Ascendant -1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SQUARE_90, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MERCURY, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SQUARE_270, -1.0)
//    Mars square Ascendant -1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SQUARE_90, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_MARS, Sign.SIGN_NONE, AspectCelestial.ASPECT_ASCENDANT, AspectAngle.SQUARE_270, -1.0)
//    Jupiter square or opposition Saturn -1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.OPPOSITION_180, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SQUARE_90, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SQUARE_270, -1.0)
//    Jupiter square or opposition Neptune -1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.OPPOSITION_180, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SQUARE_90, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_NEPTUNE, AspectAngle.SQUARE_270, -1.0)
//    Jupiter square or opposition Pluto -1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.OPPOSITION_180, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SQUARE_90, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_JUPITER, Sign.SIGN_NONE, AspectCelestial.ASPECT_PLUTO, AspectAngle.SQUARE_270, -1.0)
//    Saturn square or opposition Saturn -1
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.OPPOSITION_180, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SQUARE_90, -1.0)
            , Aspect(Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, Sign.SIGN_NONE, AspectCelestial.ASPECT_SATURN, AspectAngle.SQUARE_270, -1.0)
        )
        return romanticAspects.plus(getExtendedAspects())
    }

}

