package astro.state

import Constants.LABEL_SPACE
import astro.base.AspectAngle
import astro.base.AspectCelestial
import astro.base.AspectType

//Default Orb defn is based on https://www.astro.com/astrology/in_aspect_e.htm?nhor=432453&nho2=860236
//Selective Orb defn is based on readings found at https://astro.cafeastrology.com/natal.php
//Hybrid Orb defn is an original creation
enum class AspectOverlayState {
    ASPECT_NATCOMP_OVERLAY_DEFAULT {
        override fun getLabel() = Constants.SYM_DEFAULT_ORBS.plus(LABEL_SPACE).plus(Constants.SYM_NATCOMP_CHART).plus(
            LABEL_SPACE
        )

        override fun getAspectAngleOrb(aspectAngle: AspectAngle): Double = when (aspectAngle.getAspectType()) {
            AspectType.CONJUNCTION -> (10).toDouble()
            AspectType.SEXTILE -> (6).toDouble()
            AspectType.SQUARE -> (10).toDouble()
            AspectType.TRINE -> (10).toDouble()
            AspectType.OPPOSITION -> (10).toDouble()
            AspectType.QUINCUNX -> (3).toDouble()
            AspectType.SEMISEXTILE -> (3).toDouble()
            AspectType.QUINTILE -> (2).toDouble()
            AspectType.BIQUINTILE -> (2).toDouble()
            AspectType.SEMISQUARE -> (3).toDouble()
            AspectType.SESQUISQUARE -> (3).toDouble()
            else -> (0).toDouble() // aspects limited to overlay
        }

        override fun getAspectCelestialOrbModifier(aspectCelestial: AspectCelestial): Double = when (aspectCelestial) {
            AspectCelestial.ASPECT_NORTH_NODE -> (0.5).toDouble()
            AspectCelestial.ASPECT_BLACK_MOON_LILITH -> (0.5).toDouble()
            AspectCelestial.ASPECT_PART_OF_FORTUNE -> (0.5).toDouble()
            AspectCelestial.ASPECT_VERTEX -> (0.5).toDouble()
            else -> (1).toDouble() // otherwise, no modifier
        }

        override fun isNatComp() = true
    },
    ASPECT_NATCOMP_OVERLAY_SELECTIVE {
        override fun getLabel() = Constants.SYM_SELECTIVE_ORBS.plus(LABEL_SPACE).plus(Constants.SYM_NATCOMP_CHART).plus(
            LABEL_SPACE
        )

        override fun getAspectAngleOrb(aspectAngle: AspectAngle): Double = when (aspectAngle.getAspectType()) {
            AspectType.CONJUNCTION -> (10).toDouble()
            AspectType.SEXTILE -> (5).toDouble()
            AspectType.SQUARE -> (6).toDouble()
            AspectType.TRINE -> (8).toDouble()
            AspectType.OPPOSITION -> (9).toDouble()
            else -> (0).toDouble() // aspects limited to overlay
        }

        override fun getAspectCelestialOrbModifier(aspectCelestial: AspectCelestial): Double = when (aspectCelestial) {
            AspectCelestial.ASPECT_CHIRON -> (0).toDouble()
            AspectCelestial.ASPECT_PART_OF_FORTUNE -> (0).toDouble()
            AspectCelestial.ASPECT_VERTEX -> (0).toDouble()
            else -> (1).toDouble() // otherwise, no modifier
        }

        override fun isNatComp() = true
    },
    ASPECT_NATCOMP_OVERLAY_HYBRID {
        override fun getLabel() = Constants.SYM_HYBRID_ORBS.plus(LABEL_SPACE).plus(Constants.SYM_NATCOMP_CHART).plus(
            LABEL_SPACE
        )

        override fun getAspectAngleOrb(aspectAngle: AspectAngle): Double = when (aspectAngle.getAspectType()) {
            AspectType.CONJUNCTION -> (10).toDouble()
            AspectType.SEXTILE -> (5).toDouble()
            AspectType.SQUARE -> (6).toDouble()
            AspectType.TRINE -> (8).toDouble()
            AspectType.OPPOSITION -> (9).toDouble()
            AspectType.QUINCUNX -> (2.5).toDouble()
            AspectType.SEMISEXTILE -> (2.5).toDouble()
            AspectType.QUINTILE -> (2).toDouble()
            AspectType.BIQUINTILE -> (2).toDouble()
            AspectType.SEMISQUARE -> (2.5).toDouble()
            AspectType.SESQUISQUARE -> (2.5).toDouble()
            AspectType.SEPTILE -> (1).toDouble()
            AspectType.NOVILE -> (1).toDouble()
            else -> (0).toDouble() // aspects limited to overlay
        }

        override fun getAspectCelestialOrbModifier(aspectCelestial: AspectCelestial): Double =
            (1).toDouble() // no modifier

        override fun isNatComp() = true
    },
    ASPECT_SYNASTRY_OVERLAY_DEFAULT {
        override fun getLabel() = Constants.SYM_DEFAULT_ORBS.plus(LABEL_SPACE).plus(Constants.SYM_SYNASTRY_CHART).plus(
            LABEL_SPACE
        )

        override fun getAspectAngleOrb(aspectAngle: AspectAngle): Double =
            ASPECT_NATCOMP_OVERLAY_DEFAULT.getAspectAngleOrb(aspectAngle)

        override fun getAspectCelestialOrbModifier(aspectCelestial: AspectCelestial): Double = when (aspectCelestial) {
            AspectCelestial.ASPECT_NORTH_NODE -> (0.25).toDouble()
            AspectCelestial.ASPECT_BLACK_MOON_LILITH -> (0.25).toDouble()
            AspectCelestial.ASPECT_PART_OF_FORTUNE -> (0.25).toDouble()
            AspectCelestial.ASPECT_VERTEX -> (0.25).toDouble()
            else -> (0.5).toDouble() // otherwise, half modifier
        }

        override fun isNatComp() = false
    },
    ASPECT_SYNASTRY_OVERLAY_SELECTIVE {
        override fun getLabel() = Constants.SYM_SELECTIVE_ORBS.plus(LABEL_SPACE).plus(Constants.SYM_SYNASTRY_CHART).plus(
            LABEL_SPACE
        )

        override fun getAspectAngleOrb(aspectAngle: AspectAngle): Double = when (aspectAngle.getAspectType()) {
            AspectType.CONJUNCTION -> (10).toDouble()
            AspectType.SEXTILE -> (4).toDouble()
            AspectType.SQUARE -> (6).toDouble()
            AspectType.TRINE-> (7).toDouble()
            AspectType.OPPOSITION -> (9).toDouble()
            else -> (0).toDouble() // aspects limited to overlay
        }

        override fun getAspectCelestialOrbModifier(aspectCelestial: AspectCelestial): Double = when (aspectCelestial) {
            AspectCelestial.ASPECT_CHIRON -> (0).toDouble()
            AspectCelestial.ASPECT_PART_OF_FORTUNE -> (0).toDouble()
            AspectCelestial.ASPECT_VERTEX -> (0).toDouble()
            else -> (0.5).toDouble() // otherwise, half modifier
        }

        override fun isNatComp() = false
    },
    ASPECT_SYNASTRY_OVERLAY_HYBRID {
        override fun getLabel() = Constants.SYM_HYBRID_ORBS.plus(LABEL_SPACE).plus(Constants.SYM_SYNASTRY_CHART).plus(
            LABEL_SPACE
        )

        override fun getAspectAngleOrb(aspectAngle: AspectAngle): Double = ASPECT_NATCOMP_OVERLAY_HYBRID.getAspectAngleOrb(aspectAngle)

        override fun getAspectCelestialOrbModifier(aspectCelestial: AspectCelestial): Double =
            (0.5).toDouble() // half modifier

        override fun isNatComp() = false
    };

    abstract fun getLabel(): String
    abstract fun getAspectAngleOrb(aspectAngle: AspectAngle): Double
    abstract fun getAspectCelestialOrbModifier(aspectCelestial: AspectCelestial): Double
    abstract fun isNatComp(): Boolean

}