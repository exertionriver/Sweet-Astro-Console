package analysis

import Constants
import console.*
import profile.base.Profiles
import kotlin.math.abs
import kotlin.test.Test

@ExperimentalUnsignedTypes
class TestCharacterAnalysis {
/*
    @Test
    fun horizontalAspects() {

        val refFirstProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val refSecondProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)

        var cons = 0
        var diss = 0
        var base = 0

        val refFirstProfileChart =
            Chart(ChartState.NATAL_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, refFirstProfile.celestialSnapshot, refFirstProfile.celestialSnapshot)

        val refSecondProfileChart =
            Chart(ChartState.NATAL_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, refSecondProfile.celestialSnapshot, refSecondProfile.celestialSnapshot)

        for (aspect in refFirstProfileChart.aspects) {

            val secondAspect = refSecondProfileChart.aspects.firstOrNull {
                (it.aspectCelestialFirst == aspect.aspectCelestialFirst) &&
                        (it.aspectCelestialSecond == aspect.aspectCelestialSecond) &&
                        (it.aspectAngle.getAspect() == aspect.aspectAngle.getAspect())
            }

            if (secondAspect != null) {
                println(RenderAspect.getLabel(aspect) + " " + RenderAspect.getLabel(secondAspect) )

                if (aspect.aspectValue.getNet() > 0) cons += aspect.aspectValue.getNet()
                if (secondAspect.aspectValue.getNet() > 0) cons += secondAspect.aspectValue.getNet()
                if (aspect.aspectValue.getNet() < 0) diss += aspect.aspectValue.getNet()
                if (secondAspect.aspectValue.getNet() < 0) diss += secondAspect.aspectValue.getNet()

                base += abs(aspect.aspectValue.getNet()) + abs(secondAspect.aspectValue.getNet())
            }
        }
        println (
            Constants.KGRN + (100 * cons.toDouble() / base.toDouble()).toInt() + "."
                + Constants.KRED + (100 * diss.toDouble() / base.toDouble()).toInt() )

    }

    @Test
    fun verticalAspects() {

        val refFirstProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val refSecondProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)
        val compositeProfile = Profiles.getCompositeProfile(refFirstProfile, refSecondProfile)

        var cons = 0
        var diss = 0
        var base = 0

        val refFirstProfileChart =
            Chart(ChartState.NATAL_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, refFirstProfile.celestialSnapshot, refFirstProfile.celestialSnapshot)

        val refSecondProfileChart =
            Chart(ChartState.NATAL_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, refSecondProfile.celestialSnapshot, refSecondProfile.celestialSnapshot)

        val synChart =
            Chart(ChartState.SYNASTRY_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, refFirstProfile.celestialSnapshot, refSecondProfile.celestialSnapshot)

        val compChart =
            Chart(ChartState.COMPOSITE_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, compositeProfile.celestialSnapshot, compositeProfile.celestialSnapshot)

        println("comp chart (first):")
        for (aspect in refFirstProfileChart.aspects) {

            val secondAspect = compChart.aspects.firstOrNull {
                (it.aspectCelestialFirst == aspect.aspectCelestialFirst) &&
                        (it.aspectCelestialSecond == aspect.aspectCelestialSecond) &&
                        (it.aspectAngle.getAspect() == aspect.aspectAngle.getAspect())
            }

            if (secondAspect != null) {
                println(RenderAspect.getLabel(aspect) + " " + RenderAspect.getLabel(secondAspect) )

                if (aspect.aspectValue.getNet() > 0) cons += aspect.aspectValue.getNet()
                if (secondAspect.aspectValue.getNet() > 0) cons += secondAspect.aspectValue.getNet()
                if (aspect.aspectValue.getNet() < 0) diss += aspect.aspectValue.getNet()
                if (secondAspect.aspectValue.getNet() < 0) diss += secondAspect.aspectValue.getNet()

                base += abs(aspect.aspectValue.getNet()) + abs(secondAspect.aspectValue.getNet())
            }
        }
        println (
            Constants.KGRN + (100 * cons.toDouble() / base.toDouble()).toInt() + "."
                + Constants.KRED + (100 * diss.toDouble() / base.toDouble()).toInt() )

        cons = 0; diss = 0; base = 0
        println("syn chart (first):")
        for (aspect in refFirstProfileChart.aspects) {

            val secondAspect = synChart.aspects.firstOrNull {
                (it.aspectCelestialFirst == aspect.aspectCelestialFirst) &&
                        (it.aspectCelestialSecond == aspect.aspectCelestialSecond) &&
                        (it.aspectAngle.getAspect() == aspect.aspectAngle.getAspect())
            }

            if (secondAspect != null) {
                println(RenderAspect.getLabel(aspect) + " " + RenderAspect.getLabel(secondAspect) )

                if (aspect.aspectValue.getNet() > 0) cons += aspect.aspectValue.getNet()
                if (secondAspect.aspectValue.getNet() > 0) cons += secondAspect.aspectValue.getNet()
                if (aspect.aspectValue.getNet() < 0) diss += aspect.aspectValue.getNet()
                if (secondAspect.aspectValue.getNet() < 0) diss += secondAspect.aspectValue.getNet()

                base += abs(aspect.aspectValue.getNet()) + abs(secondAspect.aspectValue.getNet())
            }
        }
        println (
            Constants.KGRN + (100 * cons.toDouble() / base.toDouble()).toInt() + "."
                + Constants.KRED + (100 * diss.toDouble() / base.toDouble()).toInt() )

        cons = 0; diss = 0; base = 0
        println("comp chart (second):")
        for (aspect in refSecondProfileChart.aspects) {

            val secondAspect = compChart.aspects.firstOrNull {
                (it.aspectCelestialFirst == aspect.aspectCelestialFirst) &&
                        (it.aspectCelestialSecond == aspect.aspectCelestialSecond) &&
                        (it.aspectAngle.getAspect() == aspect.aspectAngle.getAspect())
            }

            if (secondAspect != null) {
                println(RenderAspect.getLabel(aspect) + " " + RenderAspect.getLabel(secondAspect) )

                if (aspect.aspectValue.getNet() > 0) cons += aspect.aspectValue.getNet()
                if (secondAspect.aspectValue.getNet() > 0) cons += secondAspect.aspectValue.getNet()
                if (aspect.aspectValue.getNet() < 0) diss += aspect.aspectValue.getNet()
                if (secondAspect.aspectValue.getNet() < 0) diss += secondAspect.aspectValue.getNet()

                base += abs(aspect.aspectValue.getNet()) + abs(secondAspect.aspectValue.getNet())
            }
        }
        println (
            Constants.KGRN + (100 * cons.toDouble() / base.toDouble()).toInt() + "."
                + Constants.KRED + (100 * diss.toDouble() / base.toDouble()).toInt() )

        cons = 0; diss = 0; base = 0
        println("syn chart (second):")
        for (aspect in refSecondProfileChart.aspects) {

            val secondAspect = synChart.aspects.firstOrNull {
                (it.aspectCelestialFirst == aspect.aspectCelestialFirst) &&
                        (it.aspectCelestialSecond == aspect.aspectCelestialSecond) &&
                        (it.aspectAngle.getAspect() == aspect.aspectAngle.getAspect())
            }

            if (secondAspect != null) {
                println(RenderAspect.getLabel(aspect) + " " + RenderAspect.getLabel(secondAspect) )

                if (aspect.aspectValue.getNet() > 0) cons += aspect.aspectValue.getNet()
                if (secondAspect.aspectValue.getNet() > 0) cons += secondAspect.aspectValue.getNet()
                if (aspect.aspectValue.getNet() < 0) diss += aspect.aspectValue.getNet()
                if (secondAspect.aspectValue.getNet() < 0) diss += secondAspect.aspectValue.getNet()

                base += abs(aspect.aspectValue.getNet()) + abs(secondAspect.aspectValue.getNet())
            }
        }
        println (
            Constants.KGRN + (100 * cons.toDouble() / base.toDouble()).toInt() + "."
                + Constants.KRED + (100 * diss.toDouble() / base.toDouble()).toInt() )
    }

    @Test
    fun crossAspects() {

        val refFirstProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val refSecondProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)
        val compositeProfile = Profiles.getCompositeProfile(refFirstProfile, refSecondProfile)

        var cons = 0
        var diss = 0
        var base = 0

        val compChart =
            Chart(ChartState.COMPOSITE_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, compositeProfile.celestialSnapshot, compositeProfile.celestialSnapshot)

        val synChart =
            Chart(ChartState.SYNASTRY_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, refFirstProfile.celestialSnapshot, refSecondProfile.celestialSnapshot)

        for (aspect in compChart.aspects) {

            val secondAspect = synChart.aspects.firstOrNull {
                (it.aspectCelestialFirst == aspect.aspectCelestialFirst) &&
                        (it.aspectCelestialSecond == aspect.aspectCelestialSecond) &&
                        (it.aspectAngle.getAspect() == aspect.aspectAngle.getAspect())
            }

            if (secondAspect != null) {
                println(RenderAspect.getLabel(aspect) + " " + RenderAspect.getLabel(secondAspect) )

                if (aspect.aspectValue.getNet() > 0) cons += aspect.aspectValue.getNet()
                if (secondAspect.aspectValue.getNet() > 0) cons += secondAspect.aspectValue.getNet()
                if (aspect.aspectValue.getNet() < 0) diss += aspect.aspectValue.getNet()
                if (secondAspect.aspectValue.getNet() < 0) diss += secondAspect.aspectValue.getNet()

                base += abs(aspect.aspectValue.getNet()) + abs(secondAspect.aspectValue.getNet())
            }

        }
        println (
            Constants.KGRN + (100 * cons.toDouble() / base.toDouble()).toInt() + "."
                + Constants.KRED + (100 * diss.toDouble() / base.toDouble()).toInt() )
    }*/
}