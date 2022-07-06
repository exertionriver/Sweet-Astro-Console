package org.river.exertion.astro.value

import kotlin.math.abs
import kotlin.math.max

@OptIn(ExperimentalUnsignedTypes::class)
data class ValueChartCompare(val modValueChart : ValueChart, val firstNatalValueChart : ValueChart, val secondNatalValueChart : ValueChart) {

    fun getRefImprovedStim() : Double = modValueChart.getModValue().getStimulation() / firstNatalValueChart.getModValue().getStimulation() * 100.0
    fun getEvalImprovedStim() : Double = modValueChart.getModValue().getStimulation() / secondNatalValueChart.getModValue().getStimulation() * 100.0

    fun getRefImprovedPos() : Double = modValueChart.getModValue().positive / firstNatalValueChart.getModValue().positive * 100.0
    fun getEvalImprovedPos() : Double = modValueChart.getModValue().positive / secondNatalValueChart.getModValue().positive * 100.0

    fun getRefImprovedCons() : Double = modValueChart.getModValue().getConsonance() / firstNatalValueChart.getModValue().getConsonance() * 100.0
    fun getEvalImprovedCons() : Double = modValueChart.getModValue().getConsonance() / secondNatalValueChart.getModValue().getConsonance() * 100.0

//    fun getRefImprovedAvg() = doubleArrayOf(getRefImprovedStim(), getRefImprovedPos(), getRefImprovedCons()).average()

    private fun getMaxStim() = max(getRefImprovedStim(), getEvalImprovedStim())
    private fun getMaxPos() = max(getRefImprovedPos(), getEvalImprovedPos())
    private fun getMaxCons() = max(getRefImprovedCons(), getEvalImprovedCons())

    private fun getRangeStim() = abs(getRefImprovedStim() - getEvalImprovedStim())
    private fun getRangePos() = abs(getRefImprovedPos() - getEvalImprovedPos())
    private fun getRangeCons() = abs(getRefImprovedCons() - getEvalImprovedCons())

    private fun getParityStimMod() = getMaxStim() / (getMaxStim() + getRangeStim())
    private fun getParityPosMod() = getMaxPos() / (getMaxPos() + getRangePos())
    private fun getParityConsMod() = getMaxCons() / (getMaxCons() + getRangeCons())

    fun getBalanceStim() = (getRefImprovedStim() + getEvalImprovedStim()) / 2
    fun getBalancePos() = (getRefImprovedPos() + getEvalImprovedPos()) / 2
    fun getBalanceCons() = (getRefImprovedCons() + getEvalImprovedCons()) / 2

    fun getRefParityStim() = (getBalanceStim() * getParityStimMod())
    fun getRefParityPos() = (getBalancePos() * getParityPosMod())
    fun getRefParityCons() = (getBalanceCons() * getParityConsMod())

    fun getRefParityAvg() = doubleArrayOf(getRefParityStim(), getRefParityPos(), getRefParityCons()).average()

    fun getWorthwhile() =
        doubleArrayOf(
            getRefParityStim(), getRefParityPos(), getRefParityCons()
            , getRefImprovedStim(), getRefImprovedPos(), getRefImprovedCons()
        ).average()
}