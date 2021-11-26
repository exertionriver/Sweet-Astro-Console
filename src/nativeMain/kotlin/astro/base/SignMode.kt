package astro.base

enum class SignMode {
    CARDINAL_MODE
    , FIXED_MODE
    , MUTABLE_MODE
    , NONE_MODE
    ;

    companion object {
        fun fromOrdinal(ordinal: Int) = Sign.values().firstOrNull { it.ordinal == ordinal }
        fun fromName(name: String) = Sign.values().firstOrNull { it.name == name }
    }
}