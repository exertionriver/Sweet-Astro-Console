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
/*
    fun getRomModLabel(analysisState: AnalysisState, romanticModifier : Int) : String {
        return when (analysisState) {
            AnalysisState.ROMANTIC_ANALYSIS ->
                when {
                    (romanticModifier > 0) -> "${Constants.KCYN}(${Constants.KGRN}+$romanticModifier${Constants.KCYN})${Constants.KNRM} "
                    (romanticModifier < 0) -> "${Constants.KCYN}(${Constants.KRED}$romanticModifier${Constants.KCYN})${Constants.KNRM} " //negative included
                    else -> "     " //no mod
                }
            else -> ""
        }
    }
*/


//    fun getValueLabel(aspectAngle : AspectAngles, valueOverride : Int = 0, extremeAspect : Boolean = false) : String =
//        getValueLabel(aspectAngle.getAspect(), valueOverride, extremeAspect)
/*
    fun getValueLabel(aspectValue: ValueAspect, valueOverride : Int = 0, extremeAspect : Boolean = false): String =
        when {
            (aspectValue.isPositive() || aspect.isNegative()) -> getLabelColor(valueOverride, extremeAspect) + aspect.getLabel() + Constants.KNRM
            (aspect == AspectTypes.ASPECT_NONE) -> Constants.KBBLK + aspect.getLabel() + Constants.KNRM
            else -> aspect.getLabel() //neutral aspect
        }
*//*
    private fun getLabelColor(valueOverride : Int, extremeAspect: Boolean) : String {

        return when {
            (valueOverride > 0) -> when (extremeAspect) {
                true -> Constants.KBGRN
                else -> Constants.KGRN
            }
            (valueOverride < 0) -> when (extremeAspect) {
                true -> Constants.KBRED
                else -> Constants.KRED
            }
            else -> Constants.KNRM //value-neutralized aspect
        }
    }
*/
    /*
    fun getLabelColor(aspectValue : ValueAspect, valueType: Int, romanticModifier: Int = 0 /*AspectValue.NO_ROMANTIC_MODIFIER*/) : String {

        val colorPrefix = Constants.KNRM
/*
            val colorPrefix = when (valueType) {
            AspectValue.POSITIVE_VALUE -> Constants.KGRN
            AspectValue.NEGATIVE_VALUE -> Constants.KRED
            else -> when {
                (aspectValue.getNet() > 0) -> Constants.KGRN
                (aspectValue.getNet() < 0) -> Constants.KRED
                else -> Constants.KNRM
            }
        }
*/
        /*
        //bright-yellow for flipped aspects via romantic modifier
        val romColorPrefix = when {
            (romanticModifier > 0) -> when {
                (colorPrefix == Constants.KGRN) -> Constants.KBGRN
                (colorPrefix == Constants.KRED) -> Constants.KBYEL
                else -> colorPrefix
            }
            (romanticModifier < 0) -> when {
                (colorPrefix == Constants.KRED) -> Constants.KBRED
                (colorPrefix == Constants.KGRN) -> Constants.KBYEL
                else -> colorPrefix
            }
            else -> colorPrefix
        }

        return romColorPrefix
    }*/
/*
    fun getLabel(aspectValue : ValueAspect, valueType : Int, romanticModifier : Int = 0) : String {

        val romColorPrefix = getLabelColor(aspectValue, valueType, romanticModifier)

        val valueString = "?"


/*        val valueString = when (valueType) {
            AspectValue.POSITIVE_VALUE -> aspectValue.positive.toString()
            AspectValue.NEGATIVE_VALUE -> aspectValue.negative.toString()
            else -> aspectValue.getNet().toString()
        }
*/
        return romColorPrefix //+ valueString.padStart(AspectValue.VALUE_LABEL_LENGTH, ' ') + Constants.KNRM
    }
*/


    override fun toString() = "RenderAspect($valueAspect)"
*/
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