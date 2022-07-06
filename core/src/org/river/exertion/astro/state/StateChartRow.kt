package org.river.exertion.astro.state

data class StateChartRow(val initRowAspects: Array<StateAspect>) {

    val rowAspects : Array<StateAspect> = initRowAspects

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as StateChartRow

        if (!rowAspects.contentEquals(other.rowAspects)) return false
        return true
    }

    override fun hashCode(): Int {
        val result = rowAspects.contentHashCode()
        return result
    }
}