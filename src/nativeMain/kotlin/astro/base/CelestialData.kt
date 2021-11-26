package astro.base

import kotlinx.cinterop.*
import platform.posix.size_tVar
import platform.posix.snprintf
import swe.CalcUtDatas
import kotlin.math.roundToInt

data class CelestialData(val celestialDataArray : DoubleArray) {

    val longitude = celestialDataArray[CalcUtDatas.LONGITUDE_DATA.ordinal]
    val latitude = celestialDataArray[CalcUtDatas.LATITUDE_DATA.ordinal]
    val distance = celestialDataArray[CalcUtDatas.DISTANCE_DATA.ordinal]
    val longitudeSpeed = celestialDataArray[CalcUtDatas.LONG_SPEED_DATA.ordinal]
    val latitudeSpeed = celestialDataArray[CalcUtDatas.LAT_SPEED_DATA.ordinal]
    val distanceSpeed = celestialDataArray[CalcUtDatas.DIST_SPEED_DATA.ordinal]
    val celestialHouse = celestialDataArray[CalcUtDatas.HOUSE_DATA_IDX]
    val transitHouse = celestialDataArray[CalcUtDatas.TRANSIT_HOUSE_DATA_IDX]

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as CelestialData

        if (longitude != other.longitude) return false
        if (latitude != other.latitude) return false
        if (distance != other.distance) return false
        if (longitudeSpeed != other.longitudeSpeed) return false
        if (latitudeSpeed != other.latitudeSpeed) return false
        if (distanceSpeed != other.distanceSpeed) return false
        if (celestialHouse != other.celestialHouse) return false
        if (transitHouse != other.transitHouse) return false

        return true
    }

    override fun hashCode(): Int {
        var result = longitude.hashCode()
        result = 31 * result + latitude.hashCode()
        result = 31 * result + distance.hashCode()
        result = 31 * result + longitudeSpeed.hashCode()
        result = 31 * result + latitudeSpeed.hashCode()
        result = 31 * result + distanceSpeed.hashCode()
        result = 31 * result + celestialHouse.hashCode()
        result = 31 * result + transitHouse.hashCode()
        return result
    }

    companion object {

        val ROUND_PRECISION_SEC = 1
        val ROUND_PRECISION_SEC_DEC = 2

        fun getFormattedSignLongitude(longitude : Double, roundPrecision : Int = ROUND_PRECISION_SEC) : String = memScoped {

            val longDeg = longitude.toInt()
            val signDeg = longitude.toInt() % 30
            val longMin = ((longitude - longDeg) * 60).toInt()
            val longSec = ((longitude - longDeg - longMin.toDouble() / 60) * 3600)
            val longSecInt = ((longitude - longDeg - longMin.toDouble() / 60) * 3600).roundToInt()

            val formattedLongitudeSize = alloc<size_tVar>()
            formattedLongitudeSize.value = 32u

            val formattedLongitude = allocArray<ByteVar>(formattedLongitudeSize.value.toInt())

            if (roundPrecision == ROUND_PRECISION_SEC_DEC)
                snprintf(formattedLongitude, formattedLongitudeSize.value, "%2i°%2i'%2.4f", signDeg, longMin, longSec)
            else
                snprintf(formattedLongitude, formattedLongitudeSize.value, "%2i°%2i'%2i", signDeg, longMin, longSecInt)

            return formattedLongitude.toKString()
        }

        fun getCelestialsDataOverride(
            celestialsData: Array<CelestialData>,
            celestialsOverrideMap: Map<Int, Int> = mapOf(-1 to -1)
        ): Array<CelestialData> {

            return Array(celestialsData.size) { celestialIdx: Int ->

                if (celestialsOverrideMap.containsKey(celestialIdx) )
                    CelestialData(
                        doubleArrayOf(
                            celestialsOverrideMap[celestialIdx]!!.toDouble()
                            , celestialsData[celestialIdx].latitude
                            , celestialsData[celestialIdx].distance
                            , celestialsData[celestialIdx].longitudeSpeed
                            , celestialsData[celestialIdx].latitudeSpeed
                            , celestialsData[celestialIdx].distanceSpeed
                            , 0.0 //celestialsData[celestialIdx].celestialHouse
                            , 0.0 //celestialsData[celestialIdx].transitHouse
                        )
                    )
                else
                    celestialsData[celestialIdx]
            }
        }

    }
}