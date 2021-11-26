package astro.state

enum class AnalysisState {
    NO_ANALYSIS
    , CHARACTER_ANALYSIS { override fun getLabel() = Constants.SYM_CHARACTER }
    , ROMANTIC_ANALYSIS { override fun getLabel() = Constants.SYM_ROMANTIC }
    , PLANET_ANALYSIS { override fun getLabel() = Constants.SYM_PLANET }
    , ELEMENT_ANALYSIS { override fun getLabel() = Constants.SYM_ELEMENT }
    , MODE_ANALYSIS { override fun getLabel() = Constants.SYM_MODE }
    ;

    open fun getLabel() : String = " "
}