package org.river.exertion.swe

import swisseph.SwissLib

object DegMidp {
    @Suppress("NewApi")
    fun getMidpoint(firstDegree : Double, secondDegree : Double) : Double {

        val sl = SwissLib()

        return sl.swe_deg_midp(firstDegree, secondDegree)
    }
}