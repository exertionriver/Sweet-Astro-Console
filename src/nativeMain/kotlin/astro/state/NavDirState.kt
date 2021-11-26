package astro.state

import Constants
import Constants.LABEL_SPACE

enum class NavDirState {
    NAV_FORWARD { override fun getIncDec() = 1 }
    , NAV_REVERSE { override fun getLabel() = Constants.SYM_RETRO; override fun getIncDec() = -1 } ;

    open fun getLabel(): String = LABEL_SPACE.padEnd(2, ' ')
    abstract fun getIncDec(): Int
}