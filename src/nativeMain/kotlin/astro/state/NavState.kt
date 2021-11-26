package astro.state

import kotlinx.datetime.*
import kotlin.time.ExperimentalTime

@ExperimentalUnsignedTypes
enum class NavState {
    CURRENT { override fun getLabel() = "Current Time".padEnd(padLength, ' '); override fun isCurrent() = true }
    , NAV_PAUSED { override fun getLabel() = "Nav:Paused".padEnd(padLength, ' ') ; override fun isPaused() = true }
    , NAV_SECOND { override fun getLabel() = "Nav:Second".padEnd(padLength, ' ') }
    , NAV_MINUTE { override fun getLabel() = "Nav:Minute".padEnd(padLength, ' ') }
    , NAV_HOUR { override fun getLabel() = "Nav:Hour".padEnd(padLength, ' ') }
    , NAV_DAY { override fun getLabel() = "Nav:Day".padEnd(padLength, ' ') }
    , NAV_WEEK { override fun getLabel() = "Nav:Week".padEnd(padLength, ' ') }
    , NAV_MONTH { override fun getLabel() = "Nav:Month".padEnd(padLength, ' ') }
    , NAV_YEAR { override fun getLabel() = "Nav:Year".padEnd(padLength, ' ') }
    , ENTRY_PAUSED { override fun getLabel() = "Entry:Paused".padEnd(padLength, ' '); override fun isPaused() = true }
    , PROFILE_RECALL { override fun getLabel() = "Profile Recall".padEnd(padLength, ' ') ; override fun isPaused() = true } ;

    abstract fun getLabel(): String
    open fun isCurrent() = false
    open fun isPaused() = false

    val padLength = 14
}