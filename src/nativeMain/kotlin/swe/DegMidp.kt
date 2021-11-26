package swe

import libswe.swe_deg_midp

object DegMidp {
    fun getMidpoint(firstDegree : Double, secondDegree : Double) : Double {
        return swe_deg_midp(firstDegree, secondDegree)
    }
}