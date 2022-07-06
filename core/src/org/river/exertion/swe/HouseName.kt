package org.river.exertion.swe

import org.river.exertion.astro.base.CelestialHouse
import swisseph.SwissEph

object HouseName {

//    val sw = SwissEph("../android/assets/ephe")

    fun getHouseName() = Swe.sw.swe_house_name(CelestialHouse.getHouseSystem())

}