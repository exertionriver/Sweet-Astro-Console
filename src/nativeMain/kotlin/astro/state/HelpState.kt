package astro.state

import Constants.LABEL_SPACE

enum class HelpState {
    HELP_DISABLED { override fun getRenderHelpFirstLine() = LABEL_SPACE.padEnd(HELP_ENABLED.getRenderHelpFirstLine().length, ' ')
                    override fun getRenderHelpSecondLine() = LABEL_SPACE.padEnd(HELP_ENABLED.getRenderHelpSecondLine().length, ' ')
    }
    , HELP_ENABLED { override fun getRenderHelpFirstLine() = "Navigate by: [s]econds, [m]inutes, [h]ours, [d]ays, [w]eeks, m[o]nths, [y]ears, [-]reverse nav; [ESC]return to current time; change [p]resets, [a]spects toggle, [t]ime toggle"
                    override fun getRenderHelpSecondLine() = "Pause actions: [SPC]pause; set [D]ate, set [T]ime, set l[A]titude, set l[O]ngitude, set time [Z]one; [0-9]recall profile, [)-(]set profile, [x]return to defaults"
    } ;

    abstract fun getRenderHelpFirstLine() : String
    abstract fun getRenderHelpSecondLine() : String
}