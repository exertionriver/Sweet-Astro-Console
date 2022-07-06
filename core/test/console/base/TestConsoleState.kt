package console.base

class TestConsoleState {
/*
    val testConsoleState = ConsoleState()

    @ExperimentalUnsignedTypes
    @Test
    fun testRenderProfile() {

        for (chart in ChartState.values()) {

            testConsoleState.curChartState = chart

            //reset from previous cycle
            testConsoleState.curProfileIdx = Profiles.PROFILE_CUR_NAV.ordinal

            println("chart :" + testConsoleState.curChartState.toString())

            println("inital: >" + RenderState.getRenderProfilesLabel(testConsoleState) + "<")

            testConsoleState.curProfileIdx = Profiles.PROFILE_1.ordinal

            println("curProfileIdx stored: >" + RenderState.getRenderProfilesLabel(testConsoleState) + "<")

            testConsoleState.synProfileIdx = Profiles.PROFILE_2.ordinal

            println("cur/synProfileIdx stored: >" + RenderState.getRenderProfilesLabel(testConsoleState) + "<")

            testConsoleState.synProfileIdx = Profiles.PROFILE_NONE.ordinal

            println("curProfileIdx stored / synProfileIdx none: >" + RenderState.getRenderProfilesLabel(testConsoleState) + "<")

            testConsoleState.curProfileIdx = Profiles.PROFILE_CUR_NAV.ordinal

            println("curProfileIdx curNav / synProfileIdx none: >" + RenderState.getRenderProfilesLabel(testConsoleState) + "<")

            testConsoleState.curProfileIdx = Profiles.PROFILE_2.ordinal
            testConsoleState.synProfileIdx = Profiles.PROFILE_CUR_NAV.ordinal

            println("curProfileIdx stored / synProfilesIdx curnav: >" + RenderState.getRenderProfilesLabel(testConsoleState) + "<")
        }
    }

    @ExperimentalUnsignedTypes
    @Test
    fun testRenderHeader() {

        SweEphePath.setPath()

        testConsoleState.curProfileIdx = Profiles.PROFILE_1.ordinal
        testConsoleState.synProfileIdx = Profiles.PROFILE_2.ordinal

        println( " " )

        for (navState in NavState.values()) {
            for (navDirState in NavDirState.values()) {
                for (aspectOverlayState in AspectOverlayState.values()) {
                    for (timeAspectsState in TimeAspectsState.values()) {
                        for (analysisState in AnalysisState.values()) {
                            testConsoleState.curNavState = navState
                            testConsoleState.curNavDirState = navDirState
                            testConsoleState.curAspectOverlayState = aspectOverlayState
                            testConsoleState.curTimeAspectsState = timeAspectsState
                            testConsoleState.curAnalysisState = analysisState

                            println (RenderState.getRenderHeader(testConsoleState) + " " + testConsoleState.curNavState + " " + testConsoleState.curNavDirState + " " + testConsoleState.curAspectOverlayState + " " + testConsoleState.curTimeAspectsState + " " + testConsoleState.curAnalysisState)
                        }
                    }
                }
            }
        }
    }*/
}