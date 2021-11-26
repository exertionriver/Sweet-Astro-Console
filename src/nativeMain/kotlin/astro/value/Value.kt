package astro.value

import kotlin.math.abs

data class Value (val positive : Int, val negative : Int) {

    fun getNet() = positive + negative
    fun getStimulation() = positive + abs(negative)
//    val consonance = (positive.toDouble() / stimulation.toDouble() * 100).toInt()

    override fun toString() = "Value(pos:$positive neg:$negative) : net:${getNet()} stimulation:${getStimulation()}"

    companion object {
        fun getEmptyValue() = Value(0, 0)
    }

}