package console.render

import astro.state.ChartState
import console.state.ConsoleChartState
import console.base.ConsoleState
import profile.base.Profile
import profile.base.Profiles

object RenderState {

    @ExperimentalUnsignedTypes
    fun getRenderHeader(consoleState : ConsoleState) : String {

        return Constants.KCYN +
                Constants.APP_NAME + " v" +
                Constants.APP_VERSION + ": " +
                Constants.KNRM + " " +
                consoleState.curNavState.getLabel() +
                getHeaderLabel(consoleState.curNavDirState.getLabel()) +
                getHeaderLabel(consoleState.curAspectOverlayState.getLabel()) +
                getHeaderLabel(consoleState.curAspectsState.getLabel()) +
                getHeaderLabel(consoleState.curTimeAspectsState.getLabel()) +
                getHeaderLabel(consoleState.curAnalysisState.getLabel()) +
                getRenderProfilesLabel(consoleState) +
                Constants.KNRM
    }

    fun getRenderProfilesLabel(consoleState : ConsoleState): String {

        val maxProfilesLength = Profile.getMaxProfileNameLength() * 2 + 3

        val curLabelOut = when {
            Profiles.isCurNavProfile(consoleState.curProfileIdx) -> "[" + Profiles.PROFILE_CUR_NAV.getDefaultName() + "]"
            else -> "[" + consoleState.profiles[consoleState.curProfileIdx].profileName + "]"
        }

        val synLabelOut = when {
            (consoleState.curChartState == ChartState.NATAL_CHART) -> " ".padEnd(maxProfilesLength, ' ')
            Profiles.isStoredProfile(consoleState.synProfileIdx) -> Constants.KMAG + "[" + consoleState.profiles[consoleState.synProfileIdx].profileName + "]" + Constants.KNRM
            else -> "[" + Profiles.PROFILE_NONE.getDefaultName() + "]" + Constants.KNRM
        }

        return when {
            ConsoleChartState.isPresentChart(consoleState.curChartState, consoleState.curProfileIdx, consoleState.synProfileIdx) -> " ".padEnd(maxProfilesLength, ' ')
            (consoleState.curChartState == ChartState.NATAL_CHART) -> curLabelOut
            else -> Constants.KBLU + curLabelOut + Constants.KNRM + " " + consoleState.curChartState.getOperatorLabel() + " " + synLabelOut
        }
    }

    fun getNestedLabelString(outerLabelColorString : String, outerLabelString : String, innerLabelColorString : String = outerLabelColorString, innerLabelString : String = outerLabelString, nestedLabelColorString : String = outerLabelColorString) : String {

        return outerLabelColorString + outerLabelString + nestedLabelColorString + "(" + innerLabelColorString + innerLabelString + nestedLabelColorString + ")" + Constants.KNRM
    }

    fun getLabelString(labelString : String, labelColorString : String) : String {

        return labelColorString + labelString + Constants.KNRM
    }

    fun getHeaderLabel(labelString : String) : String {

        return getLabelString(labelString, Constants.KCYN)
    }
}