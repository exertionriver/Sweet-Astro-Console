package org.river.exertion.astro.base

import org.river.exertion.Constants.InvalidOrb

enum class AspectType {
    CONJUNCTION {override fun isMajor() = true; override fun getAngleDegree() = 0.0 }
    , SEXTILE { override fun isMajor() = true; override fun getAngleDegree() = 60.0 }
    , SQUARE { override fun isMajor() = true; override fun getAngleDegree() = 90.0 }
    , TRINE { override fun isMajor() = true; ; override fun getAngleDegree() = 120.0 }
    , OPPOSITION { override fun isMajor() = true; override fun getAngleDegree() = 180.0 }
    , QUINCUNX { override fun isMinor() = true; override fun getAngleDegree() = 150.0 }
    , SEMISEXTILE { override fun isMinor() = true; override fun getAngleDegree() = 30.0 }
    , QUINTILE { override fun isMinor() = true; override fun getAngleDegree() = 72.0 }
    , BIQUINTILE { override fun isMinor() = true; override fun getAngleDegree() = 144.0 }
    , SEMISQUARE { override fun isMinor() = true; override fun getAngleDegree() = 45.0 }
    , SESQUISQUARE { override fun isMinor() = true; override fun getAngleDegree() = 135.0 }
    , SEPTILE { override fun getAngleDegree() = 360.0 / 7 }
    , NOVILE{ override fun getAngleDegree() = 40.0 }
    , ASPECT_NONE { override fun getAngleDegree() = InvalidOrb }
    ;

    open fun isMajor(): Boolean = false
    open fun isMinor(): Boolean = false
    abstract fun getAngleDegree(): Double

    companion object {
        fun fromOrdinal(ordinal: Int) = AspectCelestial.values().firstOrNull { it.ordinal == ordinal }
        fun fromName(name: String) = AspectCelestial.values().firstOrNull { it.name == name }
    }
}