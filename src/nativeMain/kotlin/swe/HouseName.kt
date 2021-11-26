package swe

import astro.base.CelestialHouse
import kotlinx.cinterop.toKString
import libswe.swe_house_name

object HouseName {

    fun getHouseName() = swe_house_name(CelestialHouse.getHouseSystem().toInt())!!.toKString()

}