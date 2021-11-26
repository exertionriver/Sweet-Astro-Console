package astro.base

import Constants.InvalidOrb

enum class AspectAngle {
    CONJUNCTION_0 { override fun getAspectType() = AspectType.CONJUNCTION; override fun getAngleDegree() = 0.0 }
    , CONJUNCTION_360 { override fun getAspectType() = AspectType.CONJUNCTION; override fun getAngleDegree() = 360.0 }
    , SEXTILE_60 { override fun getAspectType() = AspectType.SEXTILE; override fun getAngleDegree() = 60.0 }
    , SEXTILE_300 { override fun getAspectType() = AspectType.SEXTILE; override fun getAngleDegree() = 300.0  }
    , SQUARE_90 { override fun getAspectType() = AspectType.SQUARE; override fun getAngleDegree() = 90.0 }
    , SQUARE_270 { override fun getAspectType() = AspectType.SQUARE; override fun getAngleDegree() = 270.0 }
    , TRINE_120 { override fun getAspectType() = AspectType.TRINE; override fun getAngleDegree() = 120.0 }
    , TRINE_240 { override fun getAspectType() = AspectType.TRINE; override fun getAngleDegree() = 240.0 }
    , OPPOSITION_180 { override fun getAspectType() = AspectType.OPPOSITION; override fun getAngleDegree() = 180.0 }
    , QUINCUNX_150 { override fun getAspectType() = AspectType.QUINCUNX; override fun getAngleDegree() = 150.0 }
    , QUINCUNX_210 { override fun getAspectType() = AspectType.QUINCUNX; override fun getAngleDegree() = 210.0 }
    , SEMISEXTILE_30 { override fun getAspectType() = AspectType.SEMISEXTILE; override fun getAngleDegree() = 30.0 }
    , SEMISEXTILE_330 { override fun getAspectType() = AspectType.SEMISEXTILE; override fun getAngleDegree() = 330.0 }
    , QUINTILE_72 { override fun getAspectType() = AspectType.QUINTILE; override fun getAngleDegree() = 72.0 }
    , QUINTILE_288 { override fun getAspectType() = AspectType.QUINTILE; override fun getAngleDegree() = 288.0 }
    , BIQUINTILE_144 { override fun getAspectType() = AspectType.BIQUINTILE; override fun getAngleDegree() = 144.0 }
    , BIQUINTILE_216 { override fun getAspectType() = AspectType.BIQUINTILE; override fun getAngleDegree() = 216.0 }
    , SEMISQUARE_45 { override fun getAspectType() = AspectType.SEMISQUARE; override fun getAngleDegree() = 45.0 }
    , SEMISQUARE_315 { override fun getAspectType() = AspectType.SEMISQUARE; override fun getAngleDegree() = 315.0 }
    , SESQUISQUARE_135 { override fun getAspectType() = AspectType.SESQUISQUARE; override fun getAngleDegree() = 135.0 }
    , SESQUISQUARE_225 { override fun getAspectType() = AspectType.SESQUISQUARE; override fun getAngleDegree() = 225.0 }
    , SEPTILE_51 { override fun getAspectType() = AspectType.SEPTILE; override fun getAngleDegree() = 360.0 / 7 * 1 }
    , SEPTILE_103 { override fun getAspectType() = AspectType.SEPTILE; override fun getAngleDegree() = 360.0 / 7 * 2 }
    , SEPTILE_154 { override fun getAspectType() = AspectType.SEPTILE; override fun getAngleDegree() = 360.0 / 7 * 3 }
    , SEPTILE_206 { override fun getAspectType() = AspectType.SEPTILE; override fun getAngleDegree() = 360.0 / 7 * 4 }
    , SEPTILE_257 { override fun getAspectType() = AspectType.SEPTILE; override fun getAngleDegree() = 360.0 / 7 * 5 }
    , SEPTILE_309 { override fun getAspectType() = AspectType.SEPTILE; override fun getAngleDegree() = 360.0 / 7 * 6 }
    , NOVILE_40 { override fun getAspectType() = AspectType.NOVILE; override fun getAngleDegree() = 40.0 }
    , NOVILE_80 { override fun getAspectType() = AspectType.NOVILE; override fun getAngleDegree() = 80.0 }
    , NOVILE_160 { override fun getAspectType() = AspectType.NOVILE; override fun getAngleDegree() = 160.0 }
    , NOVILE_200 { override fun getAspectType() = AspectType.NOVILE; override fun getAngleDegree() = 200.0 }
    , NOVILE_280 { override fun getAspectType() = AspectType.NOVILE; override fun getAngleDegree() = 280.0 }
    , NOVILE_320 { override fun getAspectType() = AspectType.NOVILE; override fun getAngleDegree() = 320.0 }
    , ASPECT_ANGLE_NONE { override fun getAspectType() = AspectType.ASPECT_NONE; override fun getAngleDegree() = InvalidOrb }
    ;

    abstract fun getAspectType(): AspectType
    abstract fun getAngleDegree(): Double

    fun isAspectAngle() = this != ASPECT_ANGLE_NONE

    companion object {
        fun fromOrdinal(ordinal: Int) = values().firstOrNull { it.ordinal == ordinal }
        fun fromName(name: String) = values().firstOrNull { it.name == name }
    }
}