package astro.value

data class ValueChartRow(val initRowAspects: Array<ValueAspect>) {

    val rowAspects : Array<ValueAspect> = initRowAspects

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as ValueChartRow

        if (!rowAspects.contentEquals(other.rowAspects)) return false
        return true
    }

    override fun hashCode(): Int {
        val result = rowAspects.contentHashCode()
        return result
    }

}