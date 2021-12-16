package astro.render

import astro.value.Value
import astro.value.ValueChart
import kotlin.math.abs

data class RenderValue(val value : Value) {

    fun getLabel() : String = when {
        (value.positive > 0) && (value.negative < 0) -> value.positive.toString().padStart(4, ' ').positiveLabel().valueDivider().plus(
            abs(value.negative).toString().padStart(4, ' ').negativeLabel()
        )
        (value.positive > 0) && (value.negative == 0) -> value.positive.toString().padStart(4, ' ').positiveLabel().valueDivider().plus(
            abs(value.negative).toString().padStart(4, ' ').neutralLabel()
        )
        (value.positive == 0) && (value.negative < 0) -> value.positive.toString().padStart(4, ' ').neutralLabel().valueDivider().plus(
            abs(value.negative).toString().padStart(4, ' ').negativeLabel()
        )
        else -> value.positive.toString().padStart(4, ' ').neutralLabel().valueDivider().plus(
            abs(value.negative).toString().padStart(4, ' ').neutralLabel()
        )
    }

    fun getPercentLabel() : String {
        val cons = abs(100 * value.positive.toDouble() / value.getStimulation().toDouble() ).toInt()
        val diss = abs(100 * value.negative.toDouble() / value.getStimulation().toDouble() ).toInt()
        val consLabel = Constants.KGRN + cons.toString().padStart(2, ' ')
        val dissLabel = Constants.KRED + diss.toString().padStart(2, ' ')

        return consLabel.valueDivider().plus(dissLabel)
    }

    fun getStimLabel() : String {

        //TODO: move label to Astro folder
        return Constants.KYEL + value.getStimulation().toString().padStart(4, ' ') + Constants.KNRM
    }

    companion object {

        fun String.positiveLabel() : String = Constants.KGRN + this + Constants.KNRM
        fun String.neutralLabel() : String = Constants.KNRM + this + Constants.KNRM
        fun String.negativeLabel() : String = Constants.KRED + this + Constants.KNRM
        fun String.valueDivider() : String = this + Constants.KCYN + "." + Constants.KNRM

    }
}