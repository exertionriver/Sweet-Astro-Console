package org.river.exertion.astro.render

import org.river.exertion.astro.render.RenderChartStateType.getChartStateTypesLabel
import org.river.exertion.astro.render.RenderValue.Companion.negativeLabel
import org.river.exertion.astro.render.RenderValue.Companion.neutralLabel
import org.river.exertion.astro.render.RenderValue.Companion.positiveLabel
import org.river.exertion.Constants
import org.river.exertion.Constants.LABEL_SPACE
import org.river.exertion.astro.base.AspectCelestial
import org.river.exertion.astro.state.AnalysisState
import org.river.exertion.astro.value.ValueAspect
import kotlin.math.abs

@OptIn(ExperimentalUnsignedTypes::class)
data class RenderAspect(val valueAspect : ValueAspect) {

    val stateAspect = valueAspect.stateAspect

    fun getAspectRenderLabel() = when {
        (valueAspect.analysisState != AnalysisState.ROMANTIC_ANALYSIS) -> when {
            (valueAspect.getPositiveBaseValue() > 0) -> RenderAspectType.fromName(
                stateAspect.aspectAngle.getAspectType().toString()
            )!!.getLabel().positiveLabel()
            (valueAspect.getNegativeBaseValue() < 0) -> RenderAspectType.fromName(
                stateAspect.aspectAngle.getAspectType().toString()
            )!!.getLabel().negativeLabel()
            else -> RenderAspectType.fromName(stateAspect.aspectAngle.getAspectType().toString())!!.getLabel().neutralLabel()
        }
        else -> when { //analysis state
            (valueAspect.getPositiveBaseValue() > 0) && (valueAspect.getAspectModifier() < 0) -> RenderAspectType.fromName(
                stateAspect.aspectAngle.getAspectType().toString()
            )!!.getLabel().revLabel()
            (valueAspect.getNegativeBaseValue() < 0) && (valueAspect.getAspectModifier() > 0) -> RenderAspectType.fromName(
                stateAspect.aspectAngle.getAspectType().toString()
            )!!.getLabel().revLabel()
            (valueAspect.baseValue.getNet() != 0) && (valueAspect.baseValue.getNet() == 0) -> RenderAspectType.fromName(
                stateAspect.aspectAngle.getAspectType().toString()
            )!!.getLabel().revLabel()
            (valueAspect.getPositiveBaseValue() > 0) -> RenderAspectType.fromName(
                stateAspect.aspectAngle.getAspectType().toString()
            )!!.getLabel().positiveLabel()
            (valueAspect.getNegativeBaseValue() < 0) -> RenderAspectType.fromName(
                stateAspect.aspectAngle.getAspectType().toString()
            )!!.getLabel().negativeLabel()
            else -> RenderAspectType.fromName(stateAspect.aspectAngle.getAspectType().toString())!!.getLabel().neutralLabel()
        }
    }

    fun getAspectValueRenderLabel() = when {
        (valueAspect.analysisState != AnalysisState.ROMANTIC_ANALYSIS) -> when {
            (valueAspect.getPositiveBaseValue() > 0) -> valueAspect.getPositiveBaseValue().toString().padStart(3, ' ').positiveLabel()
            (valueAspect.getNegativeBaseValue() < 0) -> (-valueAspect.getNegativeBaseValue()).toString().padStart(3, ' ').negativeLabel()
            else -> "0".padStart(3, ' ').neutralLabel()
        }
        else -> when { //analysis state
            (valueAspect.getPositiveBaseValue() > 0) && (valueAspect.getAspectModifier() < 0) -> abs(valueAspect.getBaseModNetValue().getNet()).toString().padStart(3, ' ').revLabel()
            (valueAspect.getNegativeBaseValue() < 0) && (valueAspect.getAspectModifier() > 0) -> abs(valueAspect.getBaseModNetValue().getNet()).toString().padStart(3, ' ').revLabel()
            (valueAspect.baseValue.getNet() != 0) && (valueAspect.baseValue.getNet() == 0) -> abs(valueAspect.getBaseModNetValue().getNet()).toString().padStart(3, ' ').revLabel()
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
        if (valueAspect.analysisState != AnalysisState.ROMANTIC_ANALYSIS) return Constants.KNRM + ":" + Constants.KMAG + "(**) "

        return when {
            (valueAspect.getAspectModifier() > 0) -> when {
                (valueAspect.getAspectModifier() == 4) -> Constants.KNRM + ":" + Constants.KBBLU + "(+4) "
                else -> Constants.KNRM + ":" + Constants.KGRN + "(+${valueAspect.getAspectModifier()}) "
            }
            (valueAspect.getAspectModifier() < 0) -> when {
                (valueAspect.getAspectModifier() == -4) -> Constants.KNRM + ":" + Constants.KBRED + "(-4) "
                else -> Constants.KNRM + ":" + Constants.KRED + "(${valueAspect.getAspectModifier()}) "
            }
            else -> LABEL_SPACE//.padStart(RenderDetails.getModWidth(), ' ')
        } + Constants.KNRM
    }

    fun getRenderCharacterModLabel() : String {
        if (valueAspect.analysisState != AnalysisState.CHARACTER_ANALYSIS) return Constants.KNRM + ":" + Constants.KMAG + "(**)"

        return getChartStateTypesLabel(valueAspect.getAspectModifier())//.padStart(RenderDetails.getModWidth(), ' ')
    }

    companion object {

        //modifier flips value
        fun String.revLabel() : String = Constants.KYEL + this + Constants.KNRM

        fun getAspectNoneMarkerLabel() : String = Constants.KYEL + RenderAspectType.ASPECT_NONE.getLabel() + Constants.KNRM

        fun getLabelLength() : Int {
            return 23 //default Sign + Celestial + Aspect + Sign + Celestial + value
        }
    }
}