package swe

import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import kotlinx.datetime.*
import libswe.*
import platform.posix.double_tVar

object RevJul {

    fun getLDT(julTime : Double, timeZone : TimeZone = TimeZone.UTC) : LocalDateTime = memScoped {

        val retYear = alloc<int32Var>()
        val retMonth = alloc<int32Var>()
        val retDay = alloc<int32Var>()
        val retHourDec = alloc<double_tVar>()

        swe_revjul(julTime, SE_GREG_CAL, retYear.ptr, retMonth.ptr, retDay.ptr, retHourDec.ptr)

        val retHour = retHourDec.value.toInt()
        val retHourRemainder = retHourDec.value - retHour

        val retMin = (retHourRemainder * Julday.minInHour).toInt()
        val retMinRemainder = (retMin.toDouble() / Julday.minInHour)

        val retSec = ((retHourRemainder - retMinRemainder) * Julday.secInHour).toInt()
        val retSecRemainder = (retSec.toDouble() / Julday.secInHour)

        val retNSec = ((retHourRemainder - retMinRemainder - retSecRemainder) * Julday.nSecInHour).toInt()

        return LocalDateTime(retYear.value, retMonth.value, retDay.value, retHour, retMin, retSec, retNSec).toInstant(TimeZone.UTC).toLocalDateTime(timeZone)
    }

    fun getInstant(julTime : Double, timeZone : TimeZone = TimeZone.UTC) = getLDT(julTime, timeZone).toInstant(timeZone)
}