package org.river.exertion.astro.base

enum class Sign {
    ARIES { override fun getElement() = SignElement.FIRE_ELEMENT; override fun getMode() = SignMode.CARDINAL_MODE }
    , TAURUS { override fun getElement() = SignElement.EARTH_ELEMENT; override fun getMode() = SignMode.FIXED_MODE }
    , GEMINI { override fun getElement() = SignElement.AIR_ELEMENT; override fun getMode() = SignMode.MUTABLE_MODE }
    , CANCER { override fun getElement() = SignElement.WATER_ELEMENT; override fun getMode() = SignMode.CARDINAL_MODE }
    , LEO { override fun getElement() = SignElement.FIRE_ELEMENT; override fun getMode() = SignMode.FIXED_MODE }
    , VIRGO { override fun getElement() = SignElement.EARTH_ELEMENT; override fun getMode() = SignMode.MUTABLE_MODE }
    , LIBRA { override fun getElement() = SignElement.AIR_ELEMENT; override fun getMode() = SignMode.CARDINAL_MODE }
    , SCORPIO { override fun getElement() = SignElement.WATER_ELEMENT; override fun getMode() = SignMode.FIXED_MODE }
    , SAGITTARIUS { override fun getElement() = SignElement.FIRE_ELEMENT; override fun getMode() =
        SignMode.MUTABLE_MODE
    }
    , CAPRICORN { override fun getElement() = SignElement.EARTH_ELEMENT; override fun getMode() =
        SignMode.CARDINAL_MODE
    }
    , AQUARIUS { override fun getElement() = SignElement.AIR_ELEMENT; override fun getMode() = SignMode.FIXED_MODE }
    , PISCES { override fun getElement() = SignElement.WATER_ELEMENT; override fun getMode() = SignMode.MUTABLE_MODE }
    , SIGN_NONE { override fun getElement() = SignElement.NONE_ELEMENT; override fun getMode() = SignMode.NONE_MODE }
    ;

    abstract fun getElement() : SignElement
    abstract fun getMode() : SignMode

    companion object {
        fun fromOrdinal(ordinal: Int) = values().firstOrNull { it.ordinal == ordinal }
        fun fromName(name: String) = values().firstOrNull { it.name == name }

        fun getSignFromCelestialLongitude(celestialLongitude: Double) : Sign = fromOrdinal((celestialLongitude / 30).toInt())!!
    }
}