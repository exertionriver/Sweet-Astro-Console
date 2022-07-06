package org.river.exertion.astro.value

import kotlin.math.abs

data class Value (val positive : Int, val negative : Int) {

    fun getNet() = positive + negative
    fun getStimulation() = positive + abs(negative)
    fun getConsonance() : Double = positive / getStimulation().toDouble()

    override fun toString() = "Value(pos:$positive neg:$negative) : net:${getNet()} stimulation:${getStimulation()}"

    companion object {
        fun getEmptyValue() = Value(0, 0)
    }

}