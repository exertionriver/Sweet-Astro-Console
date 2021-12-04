package astro.state

import console.base.Validation
import kotlinx.datetime.LocalDateTime
import profile.base.Profile
import console.render.RenderHandler

@ExperimentalUnsignedTypes
enum class EntryState {

    NO_ENTRY
    , DATE_ENTRY { override fun getLabel() = "Date Entry (UTC)"; override fun getPrompt() = "yyyy.mm.dd (UTC) : "; override fun getDelim() = "." }
    , TIME_ENTRY { override fun getLabel() = "Time Entry (UTC)"; override fun getPrompt() = "hh:mm:ss (UTC) : "; override fun getDelim() = ":" }
    , LAT_ENTRY { override fun getLabel() = "Latitude Entry"; override fun getPrompt() = "(-S)xy.z123 : " }
    , LONG_ENTRY { override fun getLabel() = "Longitude Entry"; override fun getPrompt() = "(-W)xy.z123 : " }
    , TZ_ENTRY { override fun getLabel() = "Timezone Entry"; override fun getPrompt() = "(-W)hh : " }
    , PROFILE_ENTRY { override fun getLabel() = "Profile Entry"; override fun getPrompt() = "profile name: " }
    , PROFILE_NUMBER_ENTRY { override fun getLabel() = "Profile Number Entry"; override fun getPrompt() = "profile number: " }
    , RESET_DEFAULTS ;

    open fun getLabel(): String = ""
    open fun getPrompt(): String = ""
    open fun getDelim(): String = ""

}