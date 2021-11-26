package astro.base

import Constants.normalizeDeg

enum class CelestialHouse {
    HOUSE_1_ASC
    , HOUSE_2
    , HOUSE_3
    , HOUSE_4
    , HOUSE_5
    , HOUSE_6
    , HOUSE_7
    , HOUSE_8
    , HOUSE_9
    , HOUSE_10_MC
    , HOUSE_11
    , HOUSE_12
    , VERTEX
    ;


    companion object {
        fun fromOrdinal(ordinal: Int) = values().firstOrNull { it.ordinal == ordinal }
        fun fromName(name: String) = values().firstOrNull { it.name == name }

        fun getHouseSystem(): Char = 'P' // Placidus

        fun getHouseData(celestialPosition: Double, celestialHousesData : DoubleArray) : Double {

            var topCusp : Double
            var bottomCusp : Double

            var houseIdx = 0
            var houseFraction = 0.0

            //recalculate celestialPosition house data for cusps
            var nextHouse : CelestialHouse

                for (house in values()) {
                    if (house == VERTEX) break

                    houseIdx = house.ordinal

                    nextHouse = fromOrdinal(houseIdx + 1)!!

                    topCusp = if (house == HOUSE_12) {
                        celestialHousesData[HOUSE_1_ASC.ordinal]
                    } else {
                        celestialHousesData[nextHouse.ordinal]
                    }

                    bottomCusp = celestialHousesData[houseIdx]

                    if (topCusp > bottomCusp) { //normal case
                        if ((bottomCusp <= celestialPosition) && (topCusp > celestialPosition)) {
                            houseFraction = (celestialPosition - bottomCusp) / (topCusp - bottomCusp)
                            break
                        }
                    } else { //house circles through 0deg
                        if (celestialPosition >= bottomCusp) { // position is < 360 deg
                            houseFraction = (celestialPosition - bottomCusp) / (360 + topCusp - bottomCusp)
                            break
                        } else if (celestialPosition < topCusp) { // position is >= 0 deg
                            houseFraction = (360 + celestialPosition - bottomCusp) / (360 + topCusp - bottomCusp)
                            break
                        }
                    }
                }
            return houseIdx + 1 + houseFraction
        }

        fun getCelestialHousesDataOverride(celestialHousesData: DoubleArray, celestialHouseOverrideOffset : Int = 0) : DoubleArray {

            return DoubleArray(celestialHousesData.size) { celestialHouseIdx: Int ->

                (celestialHousesData[celestialHouseIdx] + celestialHouseOverrideOffset.toDouble()).normalizeDeg()
            }
        }

    }
}