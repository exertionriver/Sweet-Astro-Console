package astro.base

data class ChartRow(val rowAspects: Array<Aspect>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as ChartRow

        if (!rowAspects.contentEquals(other.rowAspects)) return false
        return true
    }

    override fun hashCode(): Int {
        return rowAspects.contentHashCode()
    }

}