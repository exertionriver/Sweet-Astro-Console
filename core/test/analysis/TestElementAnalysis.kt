package analysis

@ExperimentalUnsignedTypes
class TestElementAnalysis {
/*
    @Test
    fun testRender() {

        val firstProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val secondProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)
        val compProfile = Profiles.getCompositeProfile(firstProfile, secondProfile)

        val firstProfileChart =
            Chart(ChartState.NATAL_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, firstProfile.celestialSnapshot, firstProfile.celestialSnapshot)

        val secondProfileChart =
            Chart(ChartState.NATAL_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, secondProfile.celestialSnapshot, secondProfile.celestialSnapshot)

        val compProfileChart =
            Chart(ChartState.COMPOSITE_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, compProfile.celestialSnapshot, compProfile.celestialSnapshot)

        val synProfileChart =
            Chart(ChartState.SYNASTRY_CHART, AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT, AnalysisState.NO_ANALYSIS, firstProfile.celestialSnapshot, secondProfile.celestialSnapshot)

        renderChartData(firstProfileChart)
        renderChartData(secondProfileChart)
        renderChartData(compProfileChart)
        renderChartData(synProfileChart)

    }

    private fun renderChartData(renderChart: Chart) {

        var firePos = 0; var fireNeg = 0; var fireBase = 0
        var earthPos = 0; var earthNeg = 0; var earthBase = 0
        var airPos = 0; var airNeg = 0; var airBase = 0
        var waterPos = 0; var waterNeg = 0; var waterBase = 0

        println(RenderAspect.getLabel(renderChart.getChartValue(), AspectValue.POSITIVE_VALUE) + " " +
                RenderAspect.getLabel(renderChart.getChartValue(), AspectValue.NEGATIVE_VALUE) )

        for (aspect in renderChart.aspects) {
            val fireValue = aspect.getElementModifier(SignElement.FIRE_ELEMENT) * aspect.aspectValue.getNet()
            val earthValue = aspect.getElementModifier(SignElement.EARTH_ELEMENT) * aspect.aspectValue.getNet()
            val airValue = aspect.getElementModifier(SignElement.AIR_ELEMENT) * aspect.aspectValue.getNet()
            val waterValue = aspect.getElementModifier(SignElement.WATER_ELEMENT) * aspect.aspectValue.getNet()

            val fireLabel = if (fireValue.toInt() != 0) SignElement.FIRE_ELEMENT.getLabel() + "." + fireValue else " "
            val earthLabel = if (earthValue.toInt() != 0) SignElement.EARTH_ELEMENT.getLabel() + "." + earthValue else " "
            val airLabel = if (airValue.toInt() != 0) SignElement.AIR_ELEMENT.getLabel() + "." + airValue else " "
            val waterLabel = if (waterValue.toInt() != 0) SignElement.WATER_ELEMENT.getLabel() + "." + waterValue else " "

            firePos += if (fireValue >= 0) fireValue.toInt() else 0
            fireNeg += if (fireValue < 0) fireValue.toInt() else 0
            fireBase += if (fireValue.toInt() != 0) abs(fireValue).toInt() else 0
            earthPos += if (earthValue >= 0) earthValue.toInt() else 0
            earthNeg += if (earthValue < 0) earthValue.toInt() else 0
            earthBase += if (earthValue.toInt() != 0) abs(earthValue).toInt() else 0
            airPos += if (airValue >= 0) airValue.toInt() else 0
            airNeg += if (airValue < 0) airValue.toInt() else 0
            airBase += if (airValue.toInt() != 0) abs(airValue).toInt() else 0
            waterPos += if (waterValue >= 0) waterValue.toInt() else 0
            waterNeg += if (waterValue < 0) waterValue.toInt() else 0
            waterBase += if (waterValue.toInt() != 0) abs(waterValue).toInt() else 0

            val outLabel = RenderAspect.getLabel(aspect) + " :" + fireLabel + earthLabel + airLabel + waterLabel

         //   println (outLabel)
        }

        val totalBase = fireBase + earthBase + airBase + waterBase

        println("totals: $totalBase")
        println(Constants.KRED + SignElement.FIRE_ELEMENT.getLabel() + Constants.KNRM + " Base:" + (100 * fireBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * firePos / fireBase).toString() + "." + Constants.KRED + (100 * fireNeg / fireBase).toString() + Constants.KNRM)
        println(Constants.KYEL + SignElement.EARTH_ELEMENT.getLabel() + Constants.KNRM + " Base:" + (100 * earthBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * earthPos / earthBase).toString() + "." + Constants.KRED + (100 * earthNeg / earthBase).toString() + Constants.KNRM)
        println(Constants.KGRN + SignElement.AIR_ELEMENT.getLabel() + Constants.KNRM + " Base:" + (100 * airBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * airPos / airBase).toString() + "." + Constants.KRED + (100 * airNeg / airBase).toString() + Constants.KNRM)
        println(Constants.KBLU + SignElement.FIRE_ELEMENT.getLabel() + Constants.KNRM + " Base:" + (100 * waterBase / totalBase).toString() + ", Balance:" + Constants.KGRN + (100 * waterPos / waterBase).toString() + "." + Constants.KRED + (100 * waterNeg / waterBase).toString() + Constants.KNRM)
    }*/
}