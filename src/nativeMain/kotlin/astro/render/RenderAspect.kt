package astro.render

import Constants.LABEL_SPACE
import astro.base.AspectCelestial
import astro.state.AnalysisState
import astro.value.ValueAspect
import kotlin.math.abs

data class RenderAspect(val valueAspect : ValueAspect) {

    val baseAspect = valueAspect.getBaseAspect()

    fun getAspectRenderLabel() = when {
        (valueAspect.analysisState == AnalysisState.NO_ANALYSIS) -> when {
            (valueAspect.getPositiveBaseValue() > 0) -> RenderAspectType.fromName(valueAspect.getBaseAspect().aspectAngle.getAspectType().toString())!!.getLabel().positiveLabel()
            (valueAspect.getNegativeBaseValue() < 0) -> RenderAspectType.fromName(valueAspect.getBaseAspect().aspectAngle.getAspectType().toString())!!.getLabel().negativeLabel()
            else -> RenderAspectType.fromName(valueAspect.getBaseAspect().aspectAngle.getAspectType().toString())!!.getLabel().neutralLabel()
        }
        else -> when { //analysis state
            (valueAspect.getPositiveBaseValue() > 0) && (valueAspect.getNegativeModValue() < 0) -> RenderAspectType.fromName(valueAspect.getBaseAspect().aspectAngle.getAspectType().toString())!!.getLabel().revLabel()
            (valueAspect.getNegativeBaseValue() < 0) && (valueAspect.getPositiveModValue() > 0) -> RenderAspectType.fromName(valueAspect.getBaseAspect().aspectAngle.getAspectType().toString())!!.getLabel().revLabel()
            (valueAspect.getBaseValue().getNet() != 0) && (valueAspect.getBaseValue().getNet() == 0) -> RenderAspectType.fromName(valueAspect.getBaseAspect().aspectAngle.getAspectType().toString())!!.getLabel().revLabel()
            (valueAspect.getPositiveModValue() > 0) -> RenderAspectType.fromName(valueAspect.getBaseAspect().aspectAngle.getAspectType().toString())!!.getLabel().positiveLabel()
            (valueAspect.getNegativeModValue() < 0) -> RenderAspectType.fromName(valueAspect.getBaseAspect().aspectAngle.getAspectType().toString())!!.getLabel().negativeLabel()
            else -> RenderAspectType.fromName(valueAspect.getBaseAspect().aspectAngle.getAspectType().toString())!!.getLabel().neutralLabel()
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
            (valueAspect.getPositiveModValue() > 0) -> abs(valueAspect.getBaseModNetValue().getNet()).toString().padStart(3, ' ').positiveLabel()
            (valueAspect.getNegativeModValue() < 0) -> abs(valueAspect.getBaseModNetValue().getNet()).toString().padStart(3, ' ').negativeLabel()
            else -> abs(valueAspect.getBaseModNetValue().getNet()).toString().padStart(3, ' ').neutralLabel()
        }
    }

    fun getRenderLabel() : String {

        val firstAspectSpace = if (baseAspect.aspectCelestialFirst != AspectCelestial.ASPECT_SUN_MOON_MIDPOINT) LABEL_SPACE else ""
        val secondAspectSpace = if (baseAspect.aspectCelestialSecond != AspectCelestial.ASPECT_SUN_MOON_MIDPOINT) LABEL_SPACE else ""

        val commonLabel = RenderSign.getElementLabel(baseAspect.signFirst) + LABEL_SPACE +
                RenderAspectCelestial.fromName(baseAspect.aspectCelestialFirst.toString())!!.getLabel() + firstAspectSpace +
                getAspectRenderLabel() + LABEL_SPACE +
                RenderSign.getElementLabel(baseAspect.signSecond) + LABEL_SPACE +
                RenderAspectCelestial.fromName(baseAspect.aspectCelestialSecond.toString())!!.getLabel() + secondAspectSpace

        return if (valueAspect.analysisState == AnalysisState.ROMANTIC_ANALYSIS)
            commonLabel //+ getRomModLabel(analysisState, valueAspect.aspectModifier) +  "=" + getLabel(aspect.aspectValue, AspectValue.NET_VALUE, aspect.romanticModifier)
        else
            commonLabel + "=" + getAspectValueRenderLabel()
    }

    companion object {

        fun getEmptyAspect() = RenderAspect(
            ValueAspect.getEmptyAspect()
        )

        fun getAspectNoneMarkerLabel() : String = Constants.KYEL + RenderAspectType.ASPECT_NONE.getLabel() + Constants.KNRM

        fun getLabelLength(analysisState: AnalysisState) : Int {
            return when (analysisState) {
                AnalysisState.ROMANTIC_ANALYSIS -> getLabelLength() + 5 // for "(-x) "
                else -> getLabelLength()
            }
        }

        fun String.positiveLabel() : String = Constants.KGRN + this + Constants.KNRM
        fun String.neutralLabel() : String = Constants.KNRM + this + Constants.KNRM
        fun String.negativeLabel() : String = Constants.KRED + this + Constants.KNRM
        //modifier flips value
        fun String.revLabel() : String = Constants.KYEL + this + Constants.KNRM

        fun getLabelLength() : Int {
            return 15 + VALUE_LABEL_LENGTH //default Sign + Celestial + Aspect + Sign + Celestial = 5-char value
        }

        const val VALUE_LABEL_LENGTH = 5

    }
}