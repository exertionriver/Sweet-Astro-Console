package astro.state

import astro.base.*
import astro.value.ValueAspect
import astro.value.ValueRomanticAspects
import console.state.ConsoleAspectsState
import console.state.ConsoleChartState
import console.state.ConsoleChartState.getDefaultState
import console.state.ConsoleTimeAspectsState
import swe.DegMidp

@ExperimentalUnsignedTypes
class StateChart (val chartRows: Array<StateChartRow>, val chartState: ChartState = ChartState.NATAL_CHART) {

    constructor(chartAspects : Array<StateAspect>, chartState: ChartState = ChartState.NATAL_CHART) : this (getAspectsChart(chartAspects), chartState)

    constructor(firstCelestialSnapshot: CelestialSnapshot, secondCelestialSnapshot: CelestialSnapshot
                , chartState: ChartState, aspectsState: AspectsState, timeAspectsState: TimeAspectsState, aspectOverlayState: AspectOverlayState
    ) : this (
        getAspects(firstCelestialSnapshot, secondCelestialSnapshot, chartState, aspectsState, timeAspectsState, aspectOverlayState).plus(
            getExtendedAspects(firstCelestialSnapshot, secondCelestialSnapshot, chartState, aspectsState, timeAspectsState, aspectOverlayState)
        ), chartState
    )

    //natal or composite--no need for second snapshot
    constructor(firstCelestialSnapshot: CelestialSnapshot, chartState: ChartState, aspectsState: AspectsState, timeAspectsState: TimeAspectsState, aspectOverlayState: AspectOverlayState
    ) : this (firstCelestialSnapshot, firstCelestialSnapshot, chartState, aspectsState, timeAspectsState, aspectOverlayState)

    fun getStateAspects() : List<StateAspect> {
        val returnList = mutableListOf<StateAspect>()

        chartRows.forEach { returnList.addAll( it.rowAspects ) }

        return returnList.filter { it.aspectAngle != AspectAngle.ASPECT_ANGLE_NONE }.sortedBy { it.aspectCelestialSecond.ordinal }.sortedBy { it.aspectCelestialFirst.ordinal }

    }

    companion object {

        fun getEmptyChart() = StateChart( arrayOf(StateAspect.getEmptyAspect()) )

        fun getAspects(firstCelestialSnapshot : CelestialSnapshot, secondCelestialSnapshot: CelestialSnapshot = firstCelestialSnapshot
                       , chartState: ChartState, aspectsState: AspectsState, timeAspectsState: TimeAspectsState, aspectOverlayState: AspectOverlayState
        ) : Array<StateAspect> {

            val firstCelestialAspectMap = if (chartState != ChartState.COMPOSITE_CHART) firstCelestialSnapshot.getAspectCelestialLongitudeMap()
            else CelestialSnapshot.getCompositeSnapshot(firstCelestialSnapshot, secondCelestialSnapshot).getAspectCelestialLongitudeMap()
            val secondCelestialAspectMap = if (chartState != ChartState.COMPOSITE_CHART) secondCelestialSnapshot.getAspectCelestialLongitudeMap()
            else CelestialSnapshot.getCompositeSnapshot(firstCelestialSnapshot, secondCelestialSnapshot).getAspectCelestialLongitudeMap()

            val returnAspects : MutableList<StateAspect> = ArrayList()
            var returnAspectsIdx = 0

            for (firstCelestialAspectEntry in firstCelestialAspectMap) {
                for (secondCelestialAspectEntry in secondCelestialAspectMap) {

                    if ( (chartState != ChartState.SYNASTRY_CHART)
                        && (firstCelestialAspectEntry.key.ordinal >= secondCelestialAspectEntry.key.ordinal) ) continue

                    if ( ( firstCelestialAspectEntry.key == AspectCelestial.ASPECT_ASCENDANT)
                        && (secondCelestialAspectEntry.key == AspectCelestial.ASPECT_MIDHEAVEN)) continue

                    if ( ( firstCelestialAspectEntry.key == AspectCelestial.ASPECT_MIDHEAVEN)
                        && (secondCelestialAspectEntry.key == AspectCelestial.ASPECT_ASCENDANT)) continue

                    val aspect = StateAspect(
                        Sign.getSignFromCelestialLongitude(firstCelestialAspectEntry.value)
                        , firstCelestialAspectEntry.key
                        , firstCelestialAspectEntry.value
                        , Sign.getSignFromCelestialLongitude(secondCelestialAspectEntry.value)
                        , secondCelestialAspectEntry.key
                        , secondCelestialAspectEntry.value
                        , aspectsState, timeAspectsState, aspectOverlayState
                    )

                    if (aspect.aspectAngle != AspectAngle.ASPECT_ANGLE_NONE)
                        returnAspects.add(returnAspectsIdx++, aspect)

                }
            }

            return returnAspects.toTypedArray()
        }

        fun getExtendedAspects(firstCelestialSnapshot : CelestialSnapshot, secondCelestialSnapshot : CelestialSnapshot
                                       , chartState: ChartState, aspectsState: AspectsState, timeAspectsState: TimeAspectsState, aspectOverlayState: AspectOverlayState
        ) : Array<StateAspect> {

            val firstCelestialAspectMap = if (chartState != ChartState.COMPOSITE_CHART) firstCelestialSnapshot.getAspectCelestialLongitudeMap()
            else CelestialSnapshot.getCompositeSnapshot(firstCelestialSnapshot, secondCelestialSnapshot).getAspectCelestialLongitudeMap()
            val secondCelestialAspectMap = if (chartState != ChartState.COMPOSITE_CHART) secondCelestialSnapshot.getAspectCelestialLongitudeMap()
            else CelestialSnapshot.getCompositeSnapshot(firstCelestialSnapshot, secondCelestialSnapshot).getAspectCelestialLongitudeMap()

            val returnAspects : MutableList<StateAspect> = ArrayList()
            var returnAspectsIdx = 0

            var firstExtendedCelestialLongitude : Double
            var secondExtendedCelestialLongitude : Double
            var aspect : StateAspect

            //extended aspects are only in the second position at current
            for(extendedAspect in ValueRomanticAspects.getExtendedAspects()) {
                firstExtendedCelestialLongitude = firstCelestialAspectMap[extendedAspect.aspectCelestialFirst]!!

                secondExtendedCelestialLongitude = when (extendedAspect.aspectCelestialSecond) {
                    AspectCelestial.ASPECT_SUN_MOON_MIDPOINT -> DegMidp.getMidpoint(
                        secondCelestialAspectMap[AspectCelestial.ASPECT_SUN]!!,
                        secondCelestialAspectMap[AspectCelestial.ASPECT_MOON]!!
                    )
                    AspectCelestial.ASPECT_FIRST_HOUSE -> DegMidp.getMidpoint(
                        secondCelestialSnapshot.refCelestialHouseData[CelestialHouse.HOUSE_1_ASC.ordinal],
                        secondCelestialSnapshot.refCelestialHouseData[CelestialHouse.HOUSE_2.ordinal]
                    )
                    AspectCelestial.ASPECT_SEVENTH_HOUSE -> DegMidp.getMidpoint(
                        secondCelestialSnapshot.refCelestialHouseData[CelestialHouse.HOUSE_7.ordinal],
                        secondCelestialSnapshot.refCelestialHouseData[CelestialHouse.HOUSE_8.ordinal]
                    )
                    else -> secondCelestialAspectMap[extendedAspect.aspectCelestialSecond]!!
                }

                aspect = StateAspect(
                        Sign.getSignFromCelestialLongitude(firstExtendedCelestialLongitude)
                        , extendedAspect.aspectCelestialFirst
                        , firstExtendedCelestialLongitude
                        , Sign.getSignFromCelestialLongitude(secondExtendedCelestialLongitude)
                        , extendedAspect.aspectCelestialSecond
                        , secondExtendedCelestialLongitude
                        , aspectsState, timeAspectsState, aspectOverlayState
                    )

                if (aspect.aspectAngle == extendedAspect.aspectAngle) {

                    returnAspects.add(returnAspectsIdx++, aspect)
                }

                if (chartState == ChartState.SYNASTRY_CHART) {
                    //check the reverse
                    firstExtendedCelestialLongitude = when (extendedAspect.aspectCelestialSecond) {
                        AspectCelestial.ASPECT_SUN_MOON_MIDPOINT -> DegMidp.getMidpoint(
                            firstCelestialAspectMap[AspectCelestial.ASPECT_SUN]!!,
                            firstCelestialAspectMap[AspectCelestial.ASPECT_MOON]!!
                        )
                        AspectCelestial.ASPECT_FIRST_HOUSE -> DegMidp.getMidpoint(
                            firstCelestialSnapshot.refCelestialHouseData[CelestialHouse.HOUSE_1_ASC.ordinal],
                            firstCelestialSnapshot.refCelestialHouseData[CelestialHouse.HOUSE_2.ordinal]
                        )
                        AspectCelestial.ASPECT_SEVENTH_HOUSE -> DegMidp.getMidpoint(
                            firstCelestialSnapshot.refCelestialHouseData[CelestialHouse.HOUSE_7.ordinal],
                            firstCelestialSnapshot.refCelestialHouseData[CelestialHouse.HOUSE_8.ordinal]
                        )
                        else -> firstCelestialAspectMap[extendedAspect.aspectCelestialSecond]!!
                    }

                    secondExtendedCelestialLongitude = secondCelestialAspectMap[extendedAspect.aspectCelestialFirst]!!

                    aspect = StateAspect(
                            Sign.getSignFromCelestialLongitude(firstExtendedCelestialLongitude)
                            , extendedAspect.aspectCelestialSecond
                            , firstExtendedCelestialLongitude
                            , Sign.getSignFromCelestialLongitude(secondExtendedCelestialLongitude)
                            , extendedAspect.aspectCelestialFirst
                            , secondExtendedCelestialLongitude
                            , aspectsState, timeAspectsState, aspectOverlayState
                        )

                    if (aspect.aspectAngle == extendedAspect.aspectAngle) {

                        returnAspects.add(returnAspectsIdx++, aspect)
                    }
                }

            }
            return returnAspects.toTypedArray()
        }

        fun getAspectsChart(chartAspects : Array<StateAspect>) : Array<StateChartRow> {

            val returnAspectsChart : MutableList<StateChartRow> = ArrayList()

            for (verticalAspectCelestial in AspectCelestial.values().filter { it.isChartAspectCelestial() }) {

                val chartRow : MutableList<StateAspect> = mutableListOf()

                for (horizontalAspectCelestial in AspectCelestial.values().filter { it.isChartAspectCelestial() }) {

                    val chartAspect = chartAspects.firstOrNull {
                        (it.aspectCelestialFirst == verticalAspectCelestial) && (it.aspectCelestialSecond == horizontalAspectCelestial)
                    } ?: StateAspect.getEmptyAspect(verticalAspectCelestial, horizontalAspectCelestial)

                    chartRow.add(horizontalAspectCelestial.ordinal, chartAspect)
                }
                returnAspectsChart.add(verticalAspectCelestial.ordinal, StateChartRow(chartRow.toTypedArray()))
            }

            //add extended aspects as row
            val extendedAspectsRow = StateChartRow(chartAspects.filter { it.aspectCelestialFirst.isExtendedAspect() || it.aspectCelestialSecond.isExtendedAspect() }.toMutableList().toTypedArray())

            returnAspectsChart.add(AspectCelestial.values().filter { it.isChartAspectCelestial() }.size, extendedAspectsRow)

            return returnAspectsChart.toTypedArray()
        }
    }
}