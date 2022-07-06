package analysis

@ExperimentalUnsignedTypes
class TestCelestialAnalysis {
/*
    @Test
    fun testRender() {

        val firstProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val secondProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)
        val compProfile = Profiles.getCompositeProfile(firstProfile, secondProfile)

        val firstProfileChart =
            Chart(ChartState.NATAL_CHART, AspectsStates.ALL_ASPECTS, TimeAspectsStates.TIME_ASPECTS_ENABLED, AspectOverlayStates.ASPECT_NATCOMP_OVERLAY_DEFAULT, AnalysisStates.NO_ANALYSIS, firstProfile.celestialSnapshot, firstProfile.celestialSnapshot)

        val secondProfileChart =
            Chart(ChartState.NATAL_CHART, AspectsStates.ALL_ASPECTS, TimeAspectsStates.TIME_ASPECTS_ENABLED, AspectOverlayStates.ASPECT_NATCOMP_OVERLAY_DEFAULT, AnalysisStates.NO_ANALYSIS, secondProfile.celestialSnapshot, secondProfile.celestialSnapshot)

        val compProfileChart =
            Chart(ChartStates.COMPOSITE_CHART, AspectsStates.ALL_ASPECTS, TimeAspectsStates.TIME_ASPECTS_ENABLED, AspectOverlayStates.ASPECT_NATCOMP_OVERLAY_DEFAULT, AnalysisStates.NO_ANALYSIS, compProfile.celestialSnapshot, compProfile.celestialSnapshot)

        val synProfileChart =
            Chart(ChartStates.SYNASTRY_CHART, AspectsStates.ALL_ASPECTS, TimeAspectsStates.TIME_ASPECTS_ENABLED, AspectOverlayStates.ASPECT_SYNASTRY_OVERLAY_DEFAULT, AnalysisStates.NO_ANALYSIS, firstProfile.celestialSnapshot, secondProfile.celestialSnapshot)

        renderChartData(firstProfileChart)
        renderChartData(secondProfileChart)
        renderChartData(compProfileChart)
        renderChartData(synProfileChart)

    }

    private fun renderChartData(valueChart: ValueChart) {

        var sunPos = 0; var sunNeg = 0; var sunBase = 0
        var moonPos = 0; var moonNeg = 0; var moonBase = 0
        var mercuryPos = 0; var mercuryNeg = 0; var mercuryBase = 0
        var venusPos = 0; var venusNeg = 0; var venusBase = 0
        var marsPos = 0; var marsNeg = 0; var marsBase = 0
        var jupiterPos = 0; var jupiterNeg = 0; var jupiterBase = 0
        var saturnPos = 0; var saturnNeg = 0; var saturnBase = 0
        var uranusPos = 0; var uranusNeg = 0; var uranusBase = 0
        var neptunePos = 0; var neptuneNeg = 0; var neptuneBase = 0
        var plutoPos = 0; var plutoNeg = 0; var plutoBase = 0
        var northNodePos = 0; var northNodeNeg = 0; var northNodeBase = 0
        var blackMoonLilithPos = 0; var blackMoonLilithNeg = 0; var blackMoonLilithBase = 0
        var chironPos = 0; var chironNeg = 0; var chironBase = 0

        println(RenderAspect.getLabel(renderChart.getChartValue(), AspectValue.POSITIVE_VALUE) + " " +
                RenderAspect.getLabel(renderChart.getChartValue(), AspectValue.NEGATIVE_VALUE) )

        for (aspect in valueChart.getValueAspects()) {
            val sunValue = aspect.getCelestialModifier(AspectCelestial.ASPECT_SUN) * aspect.aspectValue.getNet()
            val moonValue = aspect.getCelestialModifier(AspectCelestial.ASPECT_MOON) * aspect.aspectValue.getNet()
            val mercuryValue = aspect.getCelestialModifier(AspectCelestial.ASPECT_MERCURY) * aspect.aspectValue.getNet()
            val venusValue = aspect.getCelestialModifier(AspectCelestial.ASPECT_VENUS) * aspect.aspectValue.getNet()
            val marsValue = aspect.getCelestialModifier(AspectCelestial.ASPECT_MARS) * aspect.aspectValue.getNet()
            val jupiterValue = aspect.getCelestialModifier(AspectCelestial.ASPECT_JUPITER) * aspect.aspectValue.getNet()
            val saturnValue = aspect.getCelestialModifier(AspectCelestial.ASPECT_SATURN) * aspect.aspectValue.getNet()
            val uranusValue = aspect.getCelestialModifier(AspectCelestial.ASPECT_URANUS) * aspect.aspectValue.getNet()
            val neptuneValue = aspect.getCelestialModifier(AspectCelestial.ASPECT_NEPTUNE) * aspect.aspectValue.getNet()
            val plutoValue = aspect.getCelestialModifier(AspectCelestial.ASPECT_PLUTO) * aspect.aspectValue.getNet()
            val northNodeValue = aspect.getCelestialModifier(AspectCelestial.ASPECT_NORTH_NODE) * aspect.aspectValue.getNet()
            val blackMoonLilithValue = aspect.getCelestialModifier(AspectCelestial.ASPECT_BLACK_MOON_LILITH) * aspect.aspectValue.getNet()
            val chironValue = aspect.getCelestialModifier(AspectCelestial.ASPECT_CHIRON) * aspect.aspectValue.getNet()

            val sunLabel = if (sunValue.toInt() != 0) AspectCelestial.ASPECT_SUN.getValueAspectCelestial().getRenderAspectCelestial().getLabel() + "." + sunValue else ""
            val moonLabel = if (moonValue.toInt() != 0) AspectCelestial.ASPECT_MOON.getLabel() + "." + moonValue else ""
            val mercuryLabel = if (mercuryValue.toInt() != 0) AspectCelestial.ASPECT_MERCURY.getLabel() + "." + mercuryValue else ""
            val venusLabel = if (venusValue.toInt() != 0) AspectCelestial.ASPECT_VENUS.getLabel() + "." + venusValue else ""
            val marsLabel = if (marsValue.toInt() != 0) AspectCelestial.ASPECT_MARS.getLabel() + "." + marsValue else ""
            val jupiterLabel = if (jupiterValue.toInt() != 0) AspectCelestial.ASPECT_JUPITER.getLabel() + "." + jupiterValue else ""
            val saturnLabel = if (saturnValue.toInt() != 0) AspectCelestial.ASPECT_SATURN.getLabel() + "." + saturnValue else ""
            val uranusLabel = if (uranusValue.toInt() != 0) AspectCelestial.ASPECT_URANUS.getLabel() + "." + uranusValue else ""
            val neptuneLabel = if (neptuneValue.toInt() != 0) AspectCelestial.ASPECT_NEPTUNE.getLabel() + "." + neptuneValue else ""
            val plutoLabel = if (plutoValue.toInt() != 0) AspectCelestial.ASPECT_PLUTO.getLabel() + "." + plutoValue else ""
            val northNodeLabel = if (northNodeValue.toInt() != 0) AspectCelestial.ASPECT_NORTH_NODE.getLabel() + "." + northNodeValue else ""
            val blackMoonLilithLabel = if (blackMoonLilithValue.toInt() != 0) AspectCelestial.ASPECT_BLACK_MOON_LILITH.getLabel() + "." + blackMoonLilithValue else ""
            val chironLabel = if (chironValue.toInt() != 0) AspectCelestial.ASPECT_CHIRON.getLabel() + "." + chironValue else ""

            sunPos += if (sunValue >= 0) sunValue.toInt() else 0
            sunNeg += if (sunValue < 0) sunValue.toInt() else 0
            sunBase += if (sunValue.toInt() != 0) abs(sunValue).toInt() else 0

            moonPos += if (moonValue >= 0) moonValue.toInt() else 0
            moonNeg += if (moonValue < 0) moonValue.toInt() else 0
            moonBase += if (moonValue.toInt() != 0) abs(moonValue).toInt() else 0

            mercuryPos += if (mercuryValue >= 0) mercuryValue.toInt() else 0
            mercuryNeg += if (mercuryValue < 0) mercuryValue.toInt() else 0
            mercuryBase += if (mercuryValue.toInt() != 0) abs(mercuryValue).toInt() else 0

            venusPos += if (venusValue >= 0) venusValue.toInt() else 0
            venusNeg += if (venusValue < 0) venusValue.toInt() else 0
            venusBase += if (venusValue.toInt() != 0) abs(venusValue).toInt() else 0

            marsPos += if (marsValue >= 0) marsValue.toInt() else 0
            marsNeg += if (marsValue < 0) marsValue.toInt() else 0
            marsBase += if (marsValue.toInt() != 0) abs(marsValue).toInt() else 0

            jupiterPos += if (jupiterValue >= 0) jupiterValue.toInt() else 0
            jupiterNeg += if (jupiterValue < 0) jupiterValue.toInt() else 0
            jupiterBase += if (jupiterValue.toInt() != 0) abs(jupiterValue).toInt() else 0

            saturnPos += if (saturnValue >= 0) saturnValue.toInt() else 0
            saturnNeg += if (saturnValue < 0) saturnValue.toInt() else 0
            saturnBase += if (saturnValue.toInt() != 0) abs(saturnValue).toInt() else 0

            uranusPos += if (uranusValue >= 0) uranusValue.toInt() else 0
            uranusNeg += if (uranusValue < 0) uranusValue.toInt() else 0
            uranusBase += if (uranusValue.toInt() != 0) abs(uranusValue).toInt() else 0

            neptunePos += if (neptuneValue >= 0) neptuneValue.toInt() else 0
            neptuneNeg += if (neptuneValue < 0) neptuneValue.toInt() else 0
            neptuneBase += if (neptuneValue.toInt() != 0) abs(neptuneValue).toInt() else 0

            plutoPos += if (plutoValue >= 0) plutoValue.toInt() else 0
            plutoNeg += if (plutoValue < 0) plutoValue.toInt() else 0
            plutoBase += if (plutoValue.toInt() != 0) abs(plutoValue).toInt() else 0

            northNodePos += if (northNodeValue >= 0) northNodeValue.toInt() else 0
            northNodeNeg += if (northNodeValue < 0) northNodeValue.toInt() else 0
            northNodeBase += if (northNodeValue.toInt() != 0) abs(northNodeValue).toInt() else 0

            blackMoonLilithPos += if (blackMoonLilithValue >= 0) blackMoonLilithValue.toInt() else 0
            blackMoonLilithNeg += if (blackMoonLilithValue < 0) blackMoonLilithValue.toInt() else 0
            blackMoonLilithBase += if (blackMoonLilithValue.toInt() != 0) abs(blackMoonLilithValue).toInt() else 0

            chironPos += if (chironValue >= 0) chironValue.toInt() else 0
            chironNeg += if (chironValue < 0) chironValue.toInt() else 0
            chironBase += if (chironValue.toInt() != 0) abs(chironValue).toInt() else 0

//            val outLabel = aspect.
//            RenderAspect.getLabel(aspect) + " :" + sunLabel + moonLabel + mercuryLabel + venusLabel + marsLabel + jupiterLabel + saturnLabel + uranusLabel + neptuneLabel + plutoLabel + northNodeLabel + blackMoonLilithLabel + chironLabel

          //  println (outLabel)
        }

        val totalBase = sunBase + moonBase + mercuryBase + venusBase + marsBase + jupiterBase + saturnBase + uranusBase + neptuneBase + plutoBase + northNodeBase + blackMoonLilithBase + chironBase

        println("totals: $totalBase")
        if (sunBase > 0) println(Constants.KCYN + AspectCelestial.ASPECT_SUN.getLabel() + Constants.KNRM + " Base:" + (100 * sunBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * sunPos / sunBase).toString() + "." + Constants.KRED + (100 * sunNeg / sunBase).toString() + Constants.KNRM)
        if (moonBase > 0) println(Constants.KCYN + AspectCelestial.ASPECT_MOON.getLabel() + Constants.KNRM + " Base:" + (100 * moonBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * moonPos / moonBase).toString() + "." + Constants.KRED + (100 * moonNeg / moonBase).toString() + Constants.KNRM)
        if (mercuryBase > 0) println(Constants.KCYN + AspectCelestial.ASPECT_MERCURY.getLabel() + Constants.KNRM + " Base:" + (100 * mercuryBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * mercuryPos / mercuryBase).toString() + "." + Constants.KRED + (100 * mercuryNeg / mercuryBase).toString() + Constants.KNRM)
        if (venusBase > 0) println(Constants.KCYN + AspectCelestial.ASPECT_VENUS.getLabel() + Constants.KNRM + " Base:" + (100 * venusBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * venusPos / venusBase).toString() + "." + Constants.KRED + (100 * venusNeg / venusBase).toString() + Constants.KNRM)
        if (marsBase > 0) println(Constants.KCYN + AspectCelestial.ASPECT_MARS.getLabel() + Constants.KNRM + " Base:" + (100 * marsBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * marsPos / marsBase).toString() + "." + Constants.KRED + (100 * marsNeg / marsBase).toString() + Constants.KNRM)
        if (jupiterBase > 0) println(Constants.KCYN + AspectCelestial.ASPECT_JUPITER.getLabel() + Constants.KNRM + " Base:" + (100 * jupiterBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * jupiterPos / jupiterBase).toString() + "." + Constants.KRED + (100 * jupiterNeg / jupiterBase).toString() + Constants.KNRM)
        if (saturnBase > 0) println(Constants.KCYN + AspectCelestial.ASPECT_SATURN.getLabel() + Constants.KNRM + " Base:" + (100 * saturnBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * saturnPos / saturnBase).toString() + "." + Constants.KRED + (100 * saturnNeg / saturnBase).toString() + Constants.KNRM)
        if (uranusBase > 0) println(Constants.KCYN + AspectCelestial.ASPECT_URANUS.getLabel() + Constants.KNRM + " Base:" + (100 * uranusBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * uranusPos / uranusBase).toString() + "." + Constants.KRED + (100 * uranusNeg / uranusBase).toString() + Constants.KNRM)
        if (neptuneBase > 0) println(Constants.KCYN + AspectCelestial.ASPECT_NEPTUNE.getLabel() + Constants.KNRM + " Base:" + (100 * neptuneBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * neptunePos / neptuneBase).toString() + "." + Constants.KRED + (100 * neptuneNeg / neptuneBase).toString() + Constants.KNRM)
        if (plutoBase > 0) println(Constants.KCYN + AspectCelestial.ASPECT_PLUTO.getLabel() + Constants.KNRM + " Base:" + (100 * plutoBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * plutoPos / plutoBase).toString() + "." + Constants.KRED + (100 * plutoNeg / plutoBase).toString() + Constants.KNRM)
        if (northNodeBase > 0) println(Constants.KCYN + AspectCelestial.ASPECT_NORTH_NODE.getLabel() + Constants.KNRM + " Base:" + (100 * northNodeBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * northNodePos / northNodeBase).toString() + "." + Constants.KRED + (100 * northNodeNeg / northNodeBase).toString() + Constants.KNRM)
        if (blackMoonLilithBase > 0) println(Constants.KCYN + AspectCelestial.ASPECT_BLACK_MOON_LILITH.getLabel() + Constants.KNRM + " Base:" + (100 * blackMoonLilithBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * blackMoonLilithPos / blackMoonLilithBase).toString() + "." + Constants.KRED + (100 * blackMoonLilithNeg / blackMoonLilithBase).toString() + Constants.KNRM)
        if (chironBase > 0) println(Constants.KCYN + AspectCelestial.ASPECT_CHIRON.getLabel() + Constants.KNRM + " Base:" + (100 * chironBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * chironPos / chironBase).toString() + "." + Constants.KRED + (100 * chironNeg / chironBase).toString() + Constants.KNRM)

    }
*/
}