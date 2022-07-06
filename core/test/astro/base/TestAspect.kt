package astro.base

import org.river.exertion.Constants.InvalidOrb
import org.river.exertion.astro.base.Aspect
import org.river.exertion.astro.base.AspectAngle
import org.river.exertion.astro.base.AspectCelestial
import org.river.exertion.astro.base.Chart
import org.river.exertion.profile.base.Profiles
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalUnsignedTypes::class)
class TestAspect {

    val emptyAspect = Aspect.getEmptyAspect()

    @Test
    fun testEmptyAspect() {
        println("empty aspect: ${Aspect.getEmptyAspect()}")
        assertEquals(AspectAngle.ASPECT_ANGLE_NONE, emptyAspect.aspectAngle)
    }

    @Test
    fun testCalcNormAngleDiff() {

        var testCalc = Aspect.calcNormAngleDiff(90.0, 120.0)

        println("calcNormAngleDiff (asc): $testCalc")
        assertEquals(30.0, testCalc)

        testCalc = Aspect.calcNormAngleDiff(144.0, 120.0)

        println("calcNormAngleDiff (desc): $testCalc")
        assertEquals(336.0, testCalc)
    }

    @Test
    fun testCalcOrb() {

        var testCalc = Aspect.calcOrb(AspectAngle.TRINE_120, 30.0, 151.0)

        println("calcOrb (asc): $testCalc")
        assertEquals(1.0, testCalc)

        testCalc = Aspect.calcOrb(AspectAngle.SQUARE_90, 90.0, 179.0)

        println("calcOrb (asc): $testCalc")
        assertEquals(1.0, testCalc)

        testCalc = Aspect.calcOrb(AspectAngle.TRINE_240, 144.0, 25.0)

        println("calcOrb (desc): $testCalc")
        assertEquals(1.0, testCalc)

        testCalc = Aspect.calcOrb(AspectAngle.SEMISEXTILE_30, 350.0, 22.0)

        println("calcOrb (desc): $testCalc")
        assertEquals(2.0, testCalc)

    }


    @Test
    fun testFindAspectAngle() {

        var testCalc = Aspect.findAspectAngle(120.0, 90.0, 31.0)

        println("findAspectAngle (orb ok, desc): $testCalc")
        assertEquals(AspectAngle.SEMISEXTILE_330, testCalc)

        testCalc = Aspect.findAspectAngle(90.0, 120.0, 31.0)

        println("findAspectAngle (orb ok, asc): $testCalc")
        assertEquals(AspectAngle.SEMISEXTILE_30, testCalc)

        testCalc = Aspect.findAspectAngle(120.0, 144.0, 5.0)

        println("findAspectAngle (orb excl): $testCalc")
        assertEquals(AspectAngle.ASPECT_ANGLE_NONE, testCalc)
    }

    @Test
    fun testConstructor() {

        var testBaseAspect = Aspect(emptyAspect.signFirst
            , AspectCelestial.ASPECT_SUN
            , emptyAspect.signFirst
            , AspectCelestial.ASPECT_MOON
            , AspectAngle.OPPOSITION_180
            ,5.5)

        println("test base aspect setting orb : $testBaseAspect")
        assertEquals(185.5,testBaseAspect.aspectAngle.getAngleDegree() + testBaseAspect.orb)

        testBaseAspect = Aspect(emptyAspect.signFirst
            , AspectCelestial.ASPECT_SUN
            , 90.0
            , emptyAspect.signFirst
            , AspectCelestial.ASPECT_MOON
            , 90.0 - 5.5)

        println("test base aspect calc orb (asc): $testBaseAspect")
        assertEquals(AspectAngle.CONJUNCTION_360, testBaseAspect.aspectAngle)
        assertEquals(5.5, testBaseAspect.orb)

        testBaseAspect = Aspect(emptyAspect.signFirst
            , AspectCelestial.ASPECT_SUN
            , 270.0
            , emptyAspect.signFirst
            , AspectCelestial.ASPECT_MOON
            , 270.0 + 4.5)

        println("test base aspect calc orb (desc): $testBaseAspect")
        assertEquals(AspectAngle.CONJUNCTION_0, testBaseAspect.aspectAngle)
        assertEquals(4.5, testBaseAspect.orb)

        testBaseAspect = Aspect(emptyAspect.signFirst
            , AspectCelestial.ASPECT_SUN
            , 120.0
            , emptyAspect.signFirst
            , AspectCelestial.ASPECT_MOON
            , 120.0 + 6.5
            , 3.5)

        println("test base aspect calc orb limit: $testBaseAspect")
        assertEquals(AspectAngle.ASPECT_ANGLE_NONE, testBaseAspect.aspectAngle)
        assertEquals(InvalidOrb, testBaseAspect.orb)
    }

    @Test
    fun testChartGetAspects() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)

        refProfile.celestialSnapshot.getAspectCelestialLongitudeMap().entries.forEach {
            println("ac:${it.key}, long:${it.value}")
        }

        val aspects = Chart.getAspects(refProfile.celestialSnapshot)

        println("aspects for ${refProfile.profileName}")
        aspects.forEach { println(it) }

    }
}