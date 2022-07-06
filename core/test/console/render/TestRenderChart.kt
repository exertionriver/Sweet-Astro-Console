package console.render

object TestRenderChart {
/*
    @Test
    fun testPrepareNatalCompAspectData() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val synProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)

        val refNatalChart = ValueChart(StateChart(refProfile.celestialSnapshot, refProfile.celestialSnapshot, ChartState.NATAL_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), AnalysisState.NO_ANALYSIS)

        val refCompChart = ValueChart(StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.COMPOSITE_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_NATCOMP_OVERLAY_DEFAULT), AnalysisState.NO_ANALYSIS)

        println("Natal chart for ${refProfile.profileName}")
            (0..RenderChart.aspectNatalCompRenderMaxIdx + 2).forEach { idx ->
            val chartDataLine = RenderChart.prepareNatalCompAspectData(idx, refNatalChart)
            println(chartDataLine + "|" + chartDataLine.length )

/*          State Chart width values
            if (idx > 0 && idx < RenderChart.aspectNatalCompRenderMaxIdx - 1)
                assertEquals(66, chartDataLine.length)
            else
                assertEquals(57, chartDataLine.length)
*/        }

        println("Comp chart for ${refProfile.profileName} and ${synProfile.profileName}")
        (0..RenderChart.aspectNatalCompRenderMaxIdx + 2).forEach { idx ->
            val chartDataLine = RenderChart.prepareNatalCompAspectData(idx, refCompChart)
            println(chartDataLine + "|" + chartDataLine.length )

/*      State Chart width values
            if (idx > 0 && idx < RenderChart.aspectNatalCompRenderMaxIdx - 1)
                assertEquals(66, chartDataLine.length)
            else
                assertEquals(57, chartDataLine.length)
*/        }
    }

    @Test
    fun testPrepareSynAspectData() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val synProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)

        val refSynChart = ValueChart(StateChart(refProfile.celestialSnapshot, synProfile.celestialSnapshot, ChartState.SYNASTRY_CHART,
            AspectsState.ALL_ASPECTS, TimeAspectsState.TIME_ASPECTS_ENABLED, AspectOverlayState.ASPECT_SYNASTRY_OVERLAY_DEFAULT), AnalysisState.NO_ANALYSIS)

        println("Syn chart for ${refProfile.profileName} and ${synProfile.profileName}")
        (0..RenderChart.aspectNatalCompRenderMaxIdx + 2).forEach { idx ->
            val chartDataLine = RenderChart.prepareSynAspectData(idx, refSynChart)
            println(chartDataLine + "|" + chartDataLine.length )
/*
            when {
                //header
                (idx == 0) -> assertEquals(210, chartDataLine.length)
                //aspect at horiz == vert
                (idx < RenderChart.aspectNatalCompRenderMaxIdx - 1) && (refSynChart.chartRows[idx-1].rowAspects[idx-1].aspectAngle.isAspectAngle()) -> assertEquals(66, chartDataLine.length)
                (idx < RenderChart.aspectNatalCompRenderMaxIdx - 1) -> assertEquals(75, chartDataLine.length)
                else -> assertEquals(57, chartDataLine.length)
            }
*/        }
    }*/
}