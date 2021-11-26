package astro.base

@Suppress("UNUSED")
enum class Eighths {
    ZERO_EIGHTHS { override fun getCusp() = 0.0 }
    , ONE_EIGHTH { override fun getCusp() = 0.125 }
    , ONE_QUARTER { override fun getCusp() = 0.25 }
    , THREE_EIGHTHS { override fun getCusp() = 0.375 }
    , ONE_HALF { override fun getCusp() = 0.5 }
    , FIVE_EIGHTHS { override fun getCusp() = 0.625 }
    , THREE_QUARTERS { override fun getCusp() = 0.75 }
    , SEVEN_EIGHTHS { override fun getCusp() = 0.875 }
    , EIGHT_EIGHTHS { override fun getCusp() = 1.0 }
    ;

    abstract fun getCusp(): Double

    companion object {
        fun fromOrdinal(value: Int) = values().firstOrNull { it.ordinal == value }
        fun fromName(name: String) = values().firstOrNull { it.name == name }

        fun getEighth(value : Double) : Eighths {
            var nextEighth : Eighths
            for (eighth in values()) {
                if (eighth == EIGHT_EIGHTHS) break

                nextEighth = fromOrdinal( eighth.ordinal + 1 )!!

                if ( (value >= eighth.getCusp() ) && (value < nextEighth.getCusp() ) ) {
                    return eighth
                }
            }
            return ZERO_EIGHTHS
        }
    }
}