package org.river.exertion.astro.state

import org.river.exertion.Constants.LABEL_SPACE
import org.river.exertion.Constants.SYM_RETRO

enum class NavDirState {
    NAV_FORWARD { override fun getIncDec() = 1 }
    , NAV_REVERSE { override fun getLabel() = SYM_RETRO; override fun getIncDec() = -1 } ;

    open fun getLabel(): String = LABEL_SPACE.padEnd(2, ' ')
    abstract fun getIncDec(): Int
}