package swe

import kotlinx.cinterop.refTo
import libswe.swe_set_ephe_path

object SweEphePath {
    private const val ephePath = Constants.EPHE_PATH

    fun setPath() {
        swe_set_ephe_path(ephePath.encodeToByteArray().refTo(0))
    }

}