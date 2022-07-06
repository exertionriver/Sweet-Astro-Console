package console.render

object TestRenderCelestials {
/*
    @Test
    fun testPrepareCelestialPositionTransitData() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val synProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)

        (0..RenderCelestials.celestialsRenderMaxIdx + 2).forEach { idx ->
            val celestialsDataLine = RenderCelestials.prepareCelestialPositionTransitData(idx, refProfile.celestialSnapshot, synProfile.celestialSnapshot)
            println(celestialsDataLine + "|" + celestialsDataLine.length )

            if (idx <= RenderCelestials.transitCelestialRenderMax)
                assertEquals(33, celestialsDataLine.length)
            else
                assertEquals(0, celestialsDataLine.length)
        }
    }

    @Test
    fun testPrepareCelestialsData() {

        var refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val synProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)

        refProfile = Profile.getCopyWithDateTimeEntry(refProfile, refProfile.earthLocation.utcDateTime, synProfile)

        println("testing Natal: ${ChartState.NATAL_CHART.getLabel()}")
        (0..RenderCelestials.celestialsRenderMaxIdx + 2).forEach { idx ->
            val celestialsDataLine = RenderCelestials.prepareCelestialsData(idx, ChartState.NATAL_CHART, refProfile.celestialSnapshot, synProfile.celestialSnapshot)
            println(celestialsDataLine + "|" + celestialsDataLine.length )

            when {
                //header
                (idx == 0) -> assertEquals(104, celestialsDataLine.length)
                (idx == RenderCelestials.celestialsRenderMaxIdx) -> assertEquals(96, celestialsDataLine.length)
                //if retrograde
                (idx < RenderCelestials.celestialsRenderMaxIdx) && (refProfile.celestialSnapshot.refCelestialData[idx - 1].longitudeSpeed < 0) -> assertEquals(105, celestialsDataLine.length)
                (idx < RenderCelestials.celestialsRenderMaxIdx) && (refProfile.celestialSnapshot.refCelestialData[idx - 1].longitudeSpeed >= 0) -> assertEquals(104, celestialsDataLine.length)
                else -> assertEquals(0, celestialsDataLine.length)
            }
        }

        println("testing Synastry: ${ChartState.SYNASTRY_CHART.getLabel()}")
        (0..RenderCelestials.celestialsRenderMaxIdx + 2).forEach { idx ->
            val celestialsDataLine = RenderCelestials.prepareCelestialsData(idx, ChartState.SYNASTRY_CHART, refProfile.celestialSnapshot, synProfile.celestialSnapshot)
            println(celestialsDataLine + "|" + celestialsDataLine.length )

            when {
                //header
                (idx == 0) -> assertEquals(132, celestialsDataLine.length)
                (idx == RenderCelestials.celestialsRenderMaxIdx) -> assertEquals(96, celestialsDataLine.length)
                //transit
                //if retrograde
                (idx <= RenderCelestials.transitCelestialRenderMax + 1) && (refProfile.celestialSnapshot.refCelestialData[idx - 1].longitudeSpeed < 0) -> assertEquals(114, celestialsDataLine.length)
                (idx <= RenderCelestials.transitCelestialRenderMax + 1) && (refProfile.celestialSnapshot.refCelestialData[idx - 1].longitudeSpeed >= 0) -> assertEquals(113, celestialsDataLine.length)
                //if retrograde
                (idx < RenderCelestials.celestialsRenderMaxIdx) && (refProfile.celestialSnapshot.refCelestialData[idx - 1].longitudeSpeed < 0) -> assertEquals(105, celestialsDataLine.length)
                (idx < RenderCelestials.celestialsRenderMaxIdx) && (refProfile.celestialSnapshot.refCelestialData[idx - 1].longitudeSpeed >= 0) -> assertEquals(104, celestialsDataLine.length)
                else -> assertEquals(0, celestialsDataLine.length)
            }

        }

        println("testing Composite: ${ChartState.COMPOSITE_CHART.getLabel()}")

        val compProfile = Profiles.getCompositeProfile(refProfile, synProfile)

        (0..RenderCelestials.celestialsRenderMaxIdx + 2).forEach { idx ->
            val celestialsDataLine = RenderCelestials.prepareCelestialsData(idx, ChartState.COMPOSITE_CHART, compProfile.celestialSnapshot, compProfile.celestialSnapshot)
            println(celestialsDataLine + "|" + celestialsDataLine.length )

            when {
                //header
                (idx == 0) -> assertEquals(104, celestialsDataLine.length)
                (idx == RenderCelestials.celestialsRenderMaxIdx) -> assertEquals(96, celestialsDataLine.length)
                //if retrograde
                (idx < RenderCelestials.celestialsRenderMaxIdx) && (compProfile.celestialSnapshot.refCelestialData[idx - 1].longitudeSpeed < 0) -> assertEquals(105, celestialsDataLine.length)
                (idx < RenderCelestials.celestialsRenderMaxIdx) && (compProfile.celestialSnapshot.refCelestialData[idx - 1].longitudeSpeed >= 0) -> assertEquals(104, celestialsDataLine.length)
                else -> assertEquals(0, celestialsDataLine.length)
            }
        }
     }*/
}