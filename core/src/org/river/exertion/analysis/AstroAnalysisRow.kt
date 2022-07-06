package org.river.exertion.analysis

import kotlinx.datetime.*
import org.river.exertion.astro.base.AspectCelestial
import org.river.exertion.astro.base.Sign

data class AstroAnalysisRow(val initDataRows : MutableList<AstroDataRow>) {

    var sortedDataRows = initDataRows.sortedBy { it.pollUTC }
    var workingDataRows = sortedDataRows

    fun refUTC(): LocalDateTime? =
        if (sortedDataRows.isNotEmpty()) sortedDataRows.first().refUTC else null //Clock.System.now().toLocalDateTime(TimeZone.UTC)

    fun minPollUTC() = workingDataRows.minOf { it.pollUTC }
    fun maxPollUTC() = workingDataRows.maxOf { it.pollUTC }

    fun resetWorkingDataRows() { workingDataRows = sortedDataRows }

    // byUTC narrows data set
    fun byYear(year: Int) {
        workingDataRows = workingDataRows.filter { it.pollUTC.year == year }
    }

    fun byMonthNumber(monthNumber: Int) {
        workingDataRows = workingDataRows.filter { it.pollUTC.monthNumber == monthNumber }
    }

    fun byDayOfMonth(dayOfMonth: Int) {
        workingDataRows = workingDataRows.filter { it.pollUTC.dayOfMonth == dayOfMonth }
    }

    fun byAspectCelestialIn(aspectCelestial : AspectCelestial, sign : Sign) {
        workingDataRows = workingDataRows.filter { Sign.getSignFromCelestialLongitude(it.pollAspectCelestials[aspectCelestial]!!) == sign }
    }

    fun maxStimulation() = workingDataRows.maxBy { it.value.getStimulation() }
    fun avgStimulation() : AstroDataRow {
        val avg = workingDataRows.sumOf { it.value.getStimulation() } / workingDataRows.size
        return workingDataRows.sortedBy { it.value.getStimulation() }.firstOrNull { it.value.getStimulation() >= avg } ?: workingDataRows.last()
    }
    fun minStimulation() = workingDataRows.minBy { it.value.getStimulation() }
    fun medStimulation() = workingDataRows.sortedBy { it.value.getStimulation() }[workingDataRows.size / 2]
    fun rankStimulation(evalStimulation : Int) : Double {
        val stimEntries = workingDataRows.sortedBy { it.value.getStimulation() }
        val entry = stimEntries.firstOrNull { it.value.getStimulation() >= evalStimulation } ?: workingDataRows.last()
        return 100.0 * stimEntries.indexOf(entry) / stimEntries.size
    }

    fun maxPositive() = workingDataRows.maxBy { it.value.positive }
    fun avgPositive() : AstroDataRow {
        val avg = workingDataRows.sumOf { it.value.positive } / workingDataRows.size
        return workingDataRows.sortedBy { it.value.positive }.firstOrNull { it.value.positive >= avg } ?: workingDataRows.last()
    }
    fun minPositive() = workingDataRows.minBy { it.value.positive }
    fun medPositive() = workingDataRows.sortedBy { it.value.positive }[workingDataRows.size / 2]
    fun rankPositive(evalPositive : Int) : Double {
        val posEntries = workingDataRows.sortedBy { it.value.positive }
        val entry = posEntries.firstOrNull { it.value.positive >= evalPositive } ?: workingDataRows.last()
        return 100.0 * posEntries.indexOf(entry) / posEntries.size
    }

    fun maxConsonance() = workingDataRows.maxBy { it.value.getConsonance() }
    fun avgConsonance() : AstroDataRow {
        val avg = workingDataRows.sumOf { it.value.getConsonance() } / workingDataRows.size
        return workingDataRows.sortedBy { it.value.getConsonance() }.firstOrNull { it.value.getConsonance() >= avg } ?: workingDataRows.last()
    }
    fun minConsonance() = workingDataRows.minBy { it.value.getConsonance() }
    fun medConsonance() = workingDataRows.sortedBy { it.value.getConsonance() }[workingDataRows.size / 2]
    fun rankConsonance(evalConsonance : Double) : Double {
        val consEntries = workingDataRows.sortedByDescending { it.value.getConsonance() }
        val entry = consEntries.firstOrNull { it.value.getConsonance() <= evalConsonance } ?: workingDataRows.last()
        return 100.0 * consEntries.indexOf(entry) / consEntries.size
    }
}