package swe

enum class CalcUtDatas {
    LONGITUDE_DATA, LATITUDE_DATA, DISTANCE_DATA, LONG_SPEED_DATA, LAT_SPEED_DATA, DIST_SPEED_DATA;

    companion object {
        val HOUSE_DATA_IDX = run { values().size }
        val TRANSIT_HOUSE_DATA_IDX = run { values().size + 1 }
        val SIZE = run { values().size }
        val EXT_SIZE = run { values().size + 2 }
    }
}

