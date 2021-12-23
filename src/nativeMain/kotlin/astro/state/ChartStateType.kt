package astro.state

import platform.posix.pow

@ExperimentalUnsignedTypes
enum class ChartStateType {
    SYN_NATAL_CHART { override fun getChartState() = ChartState.NATAL_CHART }
    , REF_NATAL_CHART { override fun getChartState() = ChartState.NATAL_CHART }
    , COMPOSITE_CHART { override fun getChartState() = ChartState.COMPOSITE_CHART }
    , SYNASTRY_CHART { override fun getChartState() = ChartState.SYNASTRY_CHART } ;
//    , SYN_NATAL_OPP_CHART { override fun getChartState() = ChartState.NATAL_CHART }
//    , REF_NATAL_OPP_CHART { override fun getChartState() = ChartState.NATAL_CHART } ;

    abstract fun getChartState() : ChartState
    fun getIntEncoding() : Int = pow(2.0, this.ordinal.toDouble()).toInt()

    companion object {
        val renderMaxIdx = 3

        fun List<ChartStateType>.encodeChartStateType() : Int {
            var encoder = 0

            this.forEach { encoder += it.getIntEncoding() }

            return encoder
        }

        fun Int.decodeChartStateType() : List<ChartStateType> {
            var decoder = this
            val returnList = mutableListOf<ChartStateType>()

/*            if (decoder - REF_NATAL_OPP_CHART.getIntEncoding() >= 0 ) {
                decoder -= REF_NATAL_OPP_CHART.getIntEncoding()
                returnList.add(REF_NATAL_OPP_CHART)
            }

            if (decoder - SYN_NATAL_OPP_CHART.getIntEncoding() >= 0 ) {
                decoder -= SYN_NATAL_OPP_CHART.getIntEncoding()
                returnList.add(SYN_NATAL_OPP_CHART)
            }
*/
            if (decoder - SYNASTRY_CHART.getIntEncoding() >= 0 ) {
                decoder -= SYNASTRY_CHART.getIntEncoding()
                returnList.add(SYNASTRY_CHART)
            }

            if (decoder - COMPOSITE_CHART.getIntEncoding() >= 0 ) {
                decoder -= COMPOSITE_CHART.getIntEncoding()
                returnList.add(COMPOSITE_CHART)
            }

            if (decoder - REF_NATAL_CHART.getIntEncoding() >= 0 ) {
                decoder -= REF_NATAL_CHART.getIntEncoding()
                returnList.add(REF_NATAL_CHART)
            }

            if (decoder - SYN_NATAL_CHART.getIntEncoding() >= 0 ) {
                decoder -= SYN_NATAL_CHART.getIntEncoding()
                returnList.add(SYN_NATAL_CHART)
            }

            return returnList.sortedBy { it.ordinal }.toList()
        }
    }
}