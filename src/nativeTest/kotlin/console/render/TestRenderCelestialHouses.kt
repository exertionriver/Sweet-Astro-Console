package console.render

import profile.base.Profiles
import kotlin.test.Test
import kotlin.test.assertEquals

object TestRenderCelestialHouses {

    @Test
    fun testPrepareHouseData() {

        val refProfile = Profiles.getDefaultProfile(Profiles.PROFILE_1)

        (0..RenderCelestialHouses.housesRenderMaxIdx + 2).forEach { idx ->
            val houseDataLine = RenderCelestialHouses.prepareHouseData(idx, refProfile.celestialSnapshot)
            println(houseDataLine + "|" + houseDataLine.length )

            if (idx <= RenderCelestialHouses.housesRenderMaxIdx)
                assertEquals(47, houseDataLine.length)
            else
                assertEquals(38, houseDataLine.length)
        }
    }
}