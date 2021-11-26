package astro.base

import astro.state.StateAspect
import kotlin.math.abs

@ExperimentalUnsignedTypes
class Chart (val chartRows: Array<ChartRow>) {

    constructor(chartAspects : Array<Aspect>) : this (
        getAspectsChart(chartAspects)
    )

    constructor(firstCelestialSnapshot: CelestialSnapshot, secondCelestialSnapshot: CelestialSnapshot = firstCelestialSnapshot) : this (
        getAspects(firstCelestialSnapshot, secondCelestialSnapshot)
    )

    fun getAspects() : List<Aspect> {
        val returnList = mutableListOf<Aspect>()

        chartRows.forEach { returnList.addAll( it.rowAspects ) }

        return returnList.filter { it.aspectAngle != AspectAngle.ASPECT_ANGLE_NONE }.sortedBy { it.aspectCelestialSecond.ordinal }.sortedBy { it.aspectCelestialFirst.ordinal }
    }

    companion object {

        fun getEmptyChart() = Chart(arrayOf(Aspect.getEmptyAspect()) )

        fun getAspects(firstCelestialSnapshot : CelestialSnapshot, secondCelestialSnapshot: CelestialSnapshot = firstCelestialSnapshot) : Array<Aspect> {

            val isNatComp = (firstCelestialSnapshot == secondCelestialSnapshot)

            val firstCelestialAspectMap = firstCelestialSnapshot.getAspectCelestialLongitudeMap()
            val secondCelestialAspectMap =
                if (isNatComp) firstCelestialSnapshot.getAspectCelestialLongitudeMap()
                else secondCelestialSnapshot.getAspectCelestialLongitudeMap()

            val returnAspects : MutableList<Aspect> = ArrayList()
            var returnAspectsIdx = 0

            for (firstCelestialAspectEntry in firstCelestialAspectMap) {
                for (secondCelestialAspectEntry in secondCelestialAspectMap) {
                    if ( ( firstCelestialAspectEntry.key == AspectCelestial.ASPECT_ASCENDANT)
                        && (secondCelestialAspectEntry.key == AspectCelestial.ASPECT_MIDHEAVEN)) continue

                    if (isNatComp && firstCelestialAspectEntry.key.ordinal >= secondCelestialAspectEntry.key.ordinal) continue

                    val aspect = Aspect(
                        Sign.getSignFromCelestialLongitude(firstCelestialAspectEntry.value)
                        , firstCelestialAspectEntry.key
                        , firstCelestialAspectEntry.value
                        , Sign.getSignFromCelestialLongitude(secondCelestialAspectEntry.value)
                        , secondCelestialAspectEntry.key
                        , secondCelestialAspectEntry.value
                    )

                    if (aspect.aspectAngle != AspectAngle.ASPECT_ANGLE_NONE)
                        returnAspects.add(returnAspectsIdx++, aspect)
                }
            }

            return returnAspects.toTypedArray()
        }

        fun getAspectsChart(chartAspects : Array<Aspect>) : Array<ChartRow> {

            val returnAspectsChart : MutableList<ChartRow> = ArrayList()

            for (verticalAspectCelestial in AspectCelestial.values().filter { it.isChartAspectCelestial() } ) {
                val chartRow : MutableList<Aspect> = ArrayList()

                for (horizontalAspectCelestial in AspectCelestial.values().filter { it.isChartAspectCelestial() }) {

                    chartRow.add(horizontalAspectCelestial.ordinal, chartAspects.firstOrNull {
                        (it.aspectCelestialFirst == verticalAspectCelestial) && (it.aspectCelestialSecond == horizontalAspectCelestial)
                    } ?: Aspect.getEmptyAspect(verticalAspectCelestial, horizontalAspectCelestial) )

                }
                returnAspectsChart.add(verticalAspectCelestial.ordinal, ChartRow(chartRow.toTypedArray()))
            }

            return returnAspectsChart.toTypedArray()
        }
    }
}