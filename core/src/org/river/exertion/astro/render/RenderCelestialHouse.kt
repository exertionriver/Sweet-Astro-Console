package org.river.exertion.astro.render

import org.river.exertion.Constants
import org.river.exertion.Constants.LABEL_SPACE

enum class RenderCelestialHouse {
    HOUSE_1_ASC { override fun getLabel() = Constants.SYM_HOUSE_1_ASC }
    , HOUSE_2 { override fun getLabel() = Constants.SYM_HOUSE_2.plus(LABEL_SPACE) }
    , HOUSE_3 { override fun getLabel() = Constants.SYM_HOUSE_3.plus(LABEL_SPACE) }
    , HOUSE_4 { override fun getLabel() = Constants.SYM_HOUSE_4.plus(LABEL_SPACE) }
    , HOUSE_5 { override fun getLabel() = Constants.SYM_HOUSE_5.plus(LABEL_SPACE) }
    , HOUSE_6 { override fun getLabel() = Constants.SYM_HOUSE_6.plus(LABEL_SPACE) }
    , HOUSE_7 { override fun getLabel() = Constants.SYM_HOUSE_7.plus(LABEL_SPACE) }
    , HOUSE_8 { override fun getLabel() = Constants.SYM_HOUSE_8.plus(LABEL_SPACE) }
    , HOUSE_9 { override fun getLabel() = Constants.SYM_HOUSE_9.plus(LABEL_SPACE) }
    , HOUSE_10_MC { override fun getLabel() = Constants.SYM_HOUSE_10_MC }
    , HOUSE_11 { override fun getLabel() = Constants.SYM_HOUSE_11.plus(LABEL_SPACE) }
    , HOUSE_12 { override fun getLabel() = Constants.SYM_HOUSE_12.plus(LABEL_SPACE) }
    , VERTEX { override fun getLabel() = Constants.SYM_VERTEX }
    ;

    abstract fun getLabel(): String

    companion object {
        fun fromOrdinal(ordinal: Int) = values().firstOrNull { it.ordinal == ordinal }
        fun fromName(name: String) = values().firstOrNull { it.name == name }
        
        fun getPartOfFortuneLabel() : String {
            return Constants.SYM_PART_OF_FORTUNE.plus(LABEL_SPACE)
        }

        fun getPartOfSpiritLabel() : String {
            return Constants.SYM_PART_OF_SPIRIT.plus(LABEL_SPACE)
        }

        fun getHousesLabel() = Constants.SYM_HOUSE + "(" + Constants.SYM_EARTH_LONG + "," + Constants.SYM_EARTH_LAT + ")"
        fun getHousesSignLabel() = Constants.SYM_SIGN + " (" + Constants.SYM_HOUSE + ")"
        fun getHousesLongitudeLabel() = Constants.SYM_CELESTIAL_LONG + "(" + Constants.SYM_HOUSE + ")"

    }
}