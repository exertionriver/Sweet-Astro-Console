package astro.base

import profile.base.Profiles
import kotlin.test.Test

object TestChart {

    @Test
    fun testChartGetAspectsChart() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)

        val aspects = Chart.getAspects(refProfile.celestialSnapshot)

        val aspectsChart = Chart.getAspectsChart(aspects)

        println("aspects for ${refProfile.profileName}:")
        aspects.forEach { println(it) }

        println("aspectsChart for ${refProfile.profileName}")
        aspectsChart.forEach { println(it) }
    }

    @Test
    fun testChart() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)
        val synProfile = Profiles.getDefaultProfile(Profiles.PROFILE_2)

        val natalChart = Chart(refProfile.celestialSnapshot)

        println("natal Chart:")
        for (verticalIdx in 0 until AspectCelestial.getChartSize())
            for (horizontalIdx in 0..verticalIdx) {
                println("[$horizontalIdx, $verticalIdx]: ${natalChart.chartRows[horizontalIdx].rowAspects[verticalIdx]}")
            }

        val compChart = Chart(CelestialSnapshot.getCompositeSnapshot(refProfile.celestialSnapshot, synProfile.celestialSnapshot) )

        println("comp Chart:")
        for (verticalIdx in 0 until AspectCelestial.getChartSize())
            for (horizontalIdx in 0..verticalIdx) {
                println("[$horizontalIdx, $verticalIdx]: ${compChart.chartRows[horizontalIdx].rowAspects[verticalIdx]}")
            }

        val synChart = Chart(refProfile.celestialSnapshot, synProfile.celestialSnapshot)

        println("syn Chart:")
        for (verticalIdx in 0 until AspectCelestial.getChartSize())
            for (horizontalIdx in 0 until AspectCelestial.getChartSize()) {
                println("[$horizontalIdx, $verticalIdx]: ${synChart.chartRows[horizontalIdx].rowAspects[verticalIdx]}")
            }

    }
}