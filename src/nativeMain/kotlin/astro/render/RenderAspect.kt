package astro.render

import Constants.LABEL_SPACE
import astro.base.AspectCelestial
import astro.render.RenderValue.Companion.negativeLabel
import astro.render.RenderValue.Companion.neutralLabel
import astro.render.RenderValue.Companion.positiveLabel
import astro.state.AnalysisState
import astro.value.ValueAspect
import kotlin.math.abs

data class RenderAspect(val valueAspect : ValueAspect) {

    val stateAspect = valueAspect.stateAspect

    fun getAspectRenderLabel() = when {
        (valueAspect.analysisState == AnalysisState.NO_ANALYSIS) -> when {
            (valueAspect.getPositiveBaseValue() > 0) -> RenderAspectType.fromName(stateAspect.aspectAngle.getAspectType().toString())!!.getLabel().positiveLabel()
            (valueAspect.getNegativeBaseValue() < 0) -> RenderAspectType.fromName(stateAspect.aspectAngle.getAspectType().toString())!!.getLabel().negativeLabel()
            else -> RenderAspectType.fromName(stateAspect.aspectAngle.getAspectType().toString())!!.getLabel().neutralLabel()
        }
        else -> when { //analysis state
            (valueAspect.getPositiveBaseValue() > 0) && (valueAspect.getNegativeModValue() < 0) -> RenderAspectType.fromName(stateAspect.aspectAngle.getAspectType().toString())!!.getLabel().revLabel()
            (valueAspect.getNegativeBaseValue() < 0) && (valueAspect.getPositiveModValue() > 0) -> RenderAspectType.fromName(stateAspect.aspectAngle.getAspectType().toString())!!.getLabel().revLabel()
            (valueAspect.getBaseValue().getNet() != 0) && (valueAspect.getBaseValue().getNet() == 0) -> RenderAspectType.fromName(stateAspect.aspectAngle.getAspectType().toString())!!.getLabel().revLabel()
            (valueAspect.getPositiveBaseValue() > 0) -> RenderAspectType.fromName(stateAspect.aspectAngle.getAspectType().toString())!!.getLabel().positiveLabel()
            (valueAspect.getNegativeBaseValue() < 0) -> RenderAspectType.fromName(stateAspect.aspectAngle.getAspectType().toString())!!.getLabel().negativeLabel()
            else -> RenderAspectType.fromName(stateAspect.aspectAngle.getAspectType().toString())!!.getLabel().neutralLabel()
        }
    }

    fun getAspectValueRenderLabel() = when {
        (valueAspect.analysisState == AnalysisState.NO_ANALYSIS) -> when {
            (valueAspect.getPositiveBaseValue() > 0) -> valueAspect.getPositiveBaseValue().toString().padStart(3, ' ').positiveLabel()
            (valueAspect.getNegativeBaseValue() < 0) -> (-valueAspect.getNegativeBaseValue()).toString().padStart(3, ' ').negativeLabel()
            else -> "0".padStart(3, ' ').neutralLabel()
        }
        else -> when { //analysis state
            (valueAspect.getPositiveBaseValue() > 0) && (valueAspect.getNegativeModValue() < 0) -> abs(valueAspect.getBaseModNetValue().getNet()).toString().padStart(3, ' ').revLabel()
            (valueAspect.getNegativeBaseValue() < 0) && (valueAspect.getPositiveModValue() > 0) -> abs(valueAspect.getBaseModNetValue().getNet()).toString().padStart(3, ' ').revLabel()
            (valueAspect.getBaseValue().getNet() != 0) && (valueAspect.getBaseValue().getNet() == 0) -> abs(valueAspect.getBaseModNetValue().getNet()).toString().padStart(3, ' ').revLabel()
            (valueAspect.getPositiveBaseValue() > 0) -> abs(valueAspect.getBaseModNetValue().getNet()).toString().padStart(3, ' ').positiveLabel()
            (valueAspect.getNegativeBaseValue() < 0) -> abs(valueAspect.getBaseModNetValue().getNet()).toString().padStart(3, ' ').negativeLabel()
            else -> abs(valueAspect.getBaseModNetValue().getNet()).toString().padStart(3, ' ').neutralLabel()
        }
    }

    fun getRenderLabel() : String {

        val firstAspectSpace = if (stateAspect.aspectCelestialFirst != AspectCelestial.ASPECT_SUN_MOON_MIDPOINT) LABEL_SPACE else ""
        val secondAspectSpace = if (stateAspect.aspectCelestialSecond != AspectCelestial.ASPECT_SUN_MOON_MIDPOINT) LABEL_SPACE else ""

        val commonLabel = RenderSign.getElementLabel(stateAspect.signFirst) + LABEL_SPACE +
                RenderAspectCelestial.fromName(stateAspect.aspectCelestialFirst.toString())!!.getLabel() + firstAspectSpace +
                getAspectRenderLabel() + LABEL_SPACE +
                RenderSign.getElementLabel(stateAspect.signSecond) + LABEL_SPACE +
                RenderAspectCelestial.fromName(stateAspect.aspectCelestialSecond.toString())!!.getLabel() + secondAspectSpace

        return commonLabel + "=" + getAspectValueRenderLabel()

   }

    fun getRenderRomanticModLabel() : String {
        if (valueAspect.analysisState != AnalysisState.ROMANTIC_ANALYSIS) return Constants.KMAG + "(**)"

        return when {
            (valueAspect.getAspectModifier() > 0) -> when {
                (valueAspect.getAspectModifier() == 4) -> Constants.KBBLU + "(+4)"
                else -> Constants.KGRN + "(+${valueAspect.getAspectModifier()})"
            }
            (valueAspect.getAspectModifier() < 0) -> when {
                (valueAspect.getAspectModifier() == -4) -> Constants.KBRED + "(-4)"
                else -> Constants.KRED + "(${valueAspect.getAspectModifier()})"
            }
            else -> LABEL_SPACE.padStart(4, ' ')
        } + Constants.KNRM
    }

    fun getRenderCharacterModLabel() : String {
        if (valueAspect.analysisState != AnalysisState.CHARACTER_ANALYSIS) return Constants.KMAG + "(**)"

        return when {
            (valueAspect.getAspectModifier() > 0) -> when {
                (valueAspect.getAspectModifier() == 4) -> Constants.KBBLU + "(+4)"
                else -> Constants.KGRN + "(+${valueAspect.getAspectModifier()})"
            }
            (valueAspect.getAspectModifier() < 0) -> when {
                (valueAspect.getAspectModifier() == -4) -> Constants.KBRED + "(-4)"
                else -> Constants.KRED + "(${valueAspect.getAspectModifier()})"
            }
            else -> LABEL_SPACE.padStart(4, ' ')
        } + Constants.KNRM
    }
    companion object {

        //modifier flips value
        fun String.revLabel() : String = Constants.KYEL + this + Constants.KNRM

        fun getAspectNoneMarkerLabel() : String = Constants.KYEL + RenderAspectType.ASPECT_NONE.getLabel() + Constants.KNRM

        fun getLabelLength() : Int {
            return 23 //default Sign + Celestial + Aspect + Sign + Celestial + value + space for mod
        }
    }
}