package console.render

object TestRenderSummary {
/*
    val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
    val synProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)
    val timeAspectsState = TimeAspectsState.TIME_ASPECTS_ENABLED
    val analysisState = AnalysisState.ROMANTIC_ANALYSIS

    @Test
    fun testBorderShapes() {

        for(idx in 0..30) {
            when (idx == 30) {
                true -> println(RenderSummary.getSummaryRowBorderShape(idx = idx))
                else -> print(RenderSummary.getSummaryRowBorderShape(idx = idx))
            }
        }

        println(RenderSummary.getTopSummaryColBorderShape(colWidth = 30))

        for(idx in 1..4) {
            println(RenderSummary.getSummaryColBorderShape(rowIdx = idx))
        }
    }

    @Test
    fun testNatalSummary() {

        val refNatalChart = ValueChart(StateChart(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), analysisState)

        val vertShapeMaxIdx = 3

        (0..20).forEach { idx ->
            val summaryDataLine = RenderSummary.prepareSummaryData(idx, refNatalChart, refNatalChart, refNatalChart, ChartState.NATAL_CHART)
            println(summaryDataLine + "|" + summaryDataLine.length )

/*            when {
                (idx == RenderSummary.summaryHeaderIdx) -> assertEquals(143, summaryDataLine.length)
                (idx == RenderSummary.baseCurChartSummaryIdx) -> assertEquals(91, summaryDataLine.length)
                (idx <= vertShapeMaxIdx) -> assertEquals(33, summaryDataLine.length)
                else -> assertEquals(24, summaryDataLine.length)
            }
  */      }
    }

    @Test
    fun testNatalSummaryAgainstDetails() {

        val refNatalChart = ValueChart(StateChart(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), analysisState)

        assertEquals(refNatalChart.getBaseValue().positive, refNatalChart.getValueAspects().map { it.getBaseValue().positive }.reduce { acc, basePositive -> acc + basePositive })
        assertEquals(refNatalChart.getBaseValue().negative, refNatalChart.getValueAspects().map { it.getBaseValue().negative }.reduce { acc, baseNegative -> acc + baseNegative })
        assertEquals(refNatalChart.getBaseValue().getStimulation(), refNatalChart.getValueAspects().map { it.getBaseValue().getStimulation() }.reduce { acc, baseStimulation -> acc + baseStimulation })
        assertEquals(refNatalChart.getBaseValue().getNet(), refNatalChart.getValueAspects().map { it.getBaseValue().getNet() }.reduce { acc, baseNet -> acc + baseNet })

        assertEquals(0, refNatalChart.getModValue().positive)
        assertEquals(0, refNatalChart.getModValue().negative)
        assertEquals(0, refNatalChart.getModValue().getStimulation())
        assertEquals(0, refNatalChart.getModValue().getNet())
    }

    @Test
    fun testCompSummary() {

        val compChart = ValueChart(StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.COMPOSITE_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), analysisState)

        val refNatalChart = ValueChart(StateChart(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), analysisState)

        val synNatalChart = ValueChart(StateChart(synProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), analysisState)

        (0..20).forEach { idx ->
            val summaryDataLine = RenderSummary.prepareSummaryData(idx, compChart, refNatalChart, synNatalChart, ChartState.COMPOSITE_CHART)
            println(summaryDataLine + "|" + summaryDataLine.length )

/*            when {
                (idx == RenderSummary.summaryHeaderIdx) -> assertEquals(143, summaryDataLine.length)
                (idx == RenderSummary.baseCurChartSummaryIdx) -> assertEquals(91, summaryDataLine.length)
                (idx == RenderSummary.baseRefNatalChartSummaryIdx) -> assertEquals(91, summaryDataLine.length)
                (idx == RenderSummary.baseSynNatalChartSummaryIdx) -> assertEquals(91, summaryDataLine.length)
                else -> assertEquals(24, summaryDataLine.length)
            }
*/        }
    }

    @Test
    fun testCompSummaryAgainstDetails() {

        val refCompChart = ValueChart(StateChart(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.COMPOSITE_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), analysisState)

        assertEquals(refCompChart.getBaseValue().positive, refCompChart.getValueAspects().map { it.getBaseValue().positive }.reduce { acc, basePositive -> acc + basePositive })
        assertEquals(refCompChart.getBaseValue().negative, refCompChart.getValueAspects().map { it.getBaseValue().negative }.reduce { acc, baseNegative -> acc + baseNegative })
        assertEquals(refCompChart.getBaseValue().getStimulation(), refCompChart.getValueAspects().map { it.getBaseValue().getStimulation() }.reduce { acc, baseStimulation -> acc + baseStimulation })
        assertEquals(refCompChart.getBaseValue().getNet(), refCompChart.getValueAspects().map { it.getBaseValue().getNet() }.reduce { acc, baseNet -> acc + baseNet })

        assertEquals(0, refCompChart.getModValue().positive)
        assertEquals(0, refCompChart.getModValue().negative)
        assertEquals(0, refCompChart.getModValue().getStimulation())
        assertEquals(0, refCompChart.getModValue().getNet())
    }

    @Test
    fun testSynSummary() {

        val synChart = ValueChart(StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.SYNASTRY_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT), analysisState)

        println(synChart.getValueAspects().size)

        val refNatalChart = ValueChart(StateChart(refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), analysisState)

        println(refNatalChart.getValueAspects().size)

        val synNatalChart = ValueChart(StateChart(synProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), analysisState)

        println(synNatalChart.getValueAspects().size)

        (0..20).forEach { idx ->
            val summaryDataLine = RenderSummary.prepareSummaryData(idx, synChart, refNatalChart, synNatalChart, ChartState.SYNASTRY_CHART)
            println(summaryDataLine + "|" + summaryDataLine.length )

/*            when {
                (idx == RenderSummary.summaryHeaderIdx) -> assertEquals(143, summaryDataLine.length)
                (idx == RenderSummary.baseCurChartSummaryIdx) ||
                (idx == RenderSummary.baseRefNatalChartSummaryIdx) ||
                (idx == RenderSummary.baseSynNatalChartSummaryIdx) -> assertEquals(91, summaryDataLine.length)
                (idx == RenderSummary.impRefNatalChartSummaryIdx) ||
                (idx == RenderSummary.impSynNatalChartSummaryIdx) -> assertEquals(82, summaryDataLine.length)
                else -> assertEquals(24, summaryDataLine.length)
            }
 */       }
    }

    @Test
    fun testSynSummaryAgainstDetails() {

        val synChart = ValueChart(StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.SYNASTRY_CHART,
            AspectsState.ALL_ASPECTS, timeAspectsState, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT), analysisState)

        assertEquals(synChart.getBaseValue().positive, synChart.getValueAspects().map { it.getBaseValue().positive }.reduce { acc, basePositive -> acc + basePositive })
        assertEquals(synChart.getBaseValue().negative, synChart.getValueAspects().map { it.getBaseValue().negative }.reduce { acc, baseNegative -> acc + baseNegative })
        assertEquals(synChart.getBaseValue().getStimulation(), synChart.getValueAspects().map { it.getBaseValue().getStimulation() }.reduce { acc, baseStimulation -> acc + baseStimulation })
        assertEquals(synChart.getBaseValue().getNet(), synChart.getValueAspects().map { it.getBaseValue().getNet() }.reduce { acc, baseNet -> acc + baseNet })

        assertEquals(0, synChart.getModValue().positive)
        assertEquals(0, synChart.getModValue().negative)
        assertEquals(0, synChart.getModValue().getStimulation())
        assertEquals(0, synChart.getModValue().getNet())
    }*/
}