import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CArrayPointer
import kotlinx.cinterop.get
import kotlinx.cinterop.toKString
import platform.posix.pow

@Suppress("UNUSED")
object Constants {
    const val APP_NAME = "SweetAstroConsole"
    const val APP_VERSION = "0.3.3"

    //EPHE_PATH works for main code. Created ln -s $EPHE_PATH /users/ephe for test code
    const val EPHE_PATH = "/usr/local/swe/ephe/"

    const val LABEL_SPACE = " "
    const val SYM_RETRO = "\u211E\uFE0F"
    //https://en.wikipedia.org/wiki/Longitude
    const val SYM_EARTH_LONG = "\u03BB" //lambda
    const val SYM_EARTH_LAT = "\u03C6" //phi
    const val SYM_EARTH_ALT = "\u21A8" //vertical
    const val SYM_UTC = "\u2D40 " //yah
    //const val SYM_UTC = "\u29B6" //'circled vertical bar'
    const val SYM_TZ = "\u25CD " //circle with lines
    //https://en.wikipedia.org/wiki/Ecliptic_coordinate_system#Spherical_coordinates
    const val SYM_CELESTIAL_LAT = "\u03B2" //beta
    const val SYM_CELESTIAL_LONG = "\u03BB" //lambda
    const val SYM_CELESTIAL_DIST = "\u0394" //upper-delta

    const val SYM_CELESTIAL = "\u25CE" //bullseye
    const val SYM_SUN = "\u2609"
    const val SYM_MOON = "\u263E"
    const val SYM_REV_MOON = "\u263D"
    const val SYM_MERCURY = "\u263F"
    const val SYM_VENUS = "\u2640"
    const val SYM_EARTH = "\u2d32 " //yabh
//    const val SYM_EARTH = "\u2295" //'circled plus'
//  const val SYM_EARTH = "\u2A01\uFE0E" //'n-ary circled plus'
    const val SYM_MARS = "\u2642"
    const val SYM_JUPITER = "\u2643"
    const val SYM_SATURN = "\u2644"
    const val SYM_URANUS = "\u2645"
    const val SYM_NEPTUNE = "\u2646"
    const val SYM_PLUTO = "\u2647"
    const val SYM_NORTH_NODE = "\u260A"
    const val SYM_BLACK_MOON_LILITH = "\u26B8"
    const val SYM_CHIRON = "\u26B7"
    const val SYM_PHOLUS = "P\u2095" //intended to be "\u2BDB" Pholus
    const val SYM_CERES = "\u26B3"
    const val SYM_PALLAS = "\u26B4"
    const val SYM_JUNO = "\u26B5"
    const val SYM_VESTA = "\u26B6"

    const val SYM_HOUSE = "\u2302" //house
    const val SYM_HOUSE_1_ASC = "\u2460A"
    const val SYM_HOUSE_1 = "\u2460" //no asc
    const val SYM_HOUSE_2 = "\u2461"
    const val SYM_HOUSE_3 = "\u2462"
    const val SYM_HOUSE_4 = "\u2463"
    const val SYM_HOUSE_5 = "\u2464"
    const val SYM_HOUSE_6 = "\u2465"
    const val SYM_HOUSE_7 = "\u2466"
    const val SYM_HOUSE_8 = "\u2467"
    const val SYM_HOUSE_9 = "\u2468"
    const val SYM_HOUSE_10_MC = "\u2469M"
    const val SYM_HOUSE_11 = "\u246A"
    const val SYM_HOUSE_12 = "\u246B"
    const val SYM_VERTEX = "V\u2093"
    const val SYM_PART_OF_FORTUNE = "\u2297"
    const val SYM_PART_OF_SPIRIT = "\u23C0"
    const val SYM_PART_OF_EROS = "\u2619"

    const val SYM_ONE_EIGHTH = "\u215B"
    const val SYM_ONE_QUARTER = "\u00BC"
    const val SYM_THREE_EIGHTHS = "\u215C"
    const val SYM_ONE_HALF = "\u00BD"
    const val SYM_FIVE_EIGHTHS = "\u215D"
    const val SYM_THREE_QUARTERS = "\u00BE"
    const val SYM_SEVEN_EIGHTHS = "\u215E"

    //text selector used (uFE0E)
    const val SYM_SIGN = "\u2630" //heaven trigram
    const val SYM_ARIES = "\u2648\uFE0E"
    const val SYM_TAURUS = "\u2649\uFE0E"
    const val SYM_GEMINI = "\u264A\uFE0E"
    const val SYM_CANCER = "\u264B\uFE0E"
    const val SYM_LEO = "\u264C\uFE0E"
    const val SYM_VIRGO = "\u264D\uFE0E"
    const val SYM_LIBRA = "\u264E\uFE0E"
    const val SYM_SCORPIO = "\u264F\uFE0E"
    const val SYM_SAGITTARIUS = "\u2650\uFE0E"
    const val SYM_CAPRICORN = "\u2651\uFE0E"
    const val SYM_AQUARIUS = "\u2652\uFE0E"
    const val SYM_PISCES = "\u2653\uFE0E"

    const val SYM_EXISTS_IN = "\u2208"
    const val SYM_CONTAINS = "\u220B"

    const val SYM_CONJUNCTION = "\u260C"
    const val SYM_SEXTILE = "\u26B9"
    const val SYM_SQUARE = "\u25A1"
    const val SYM_TRINE = "\u25B3"
    const val SYM_OPPOSITION = "\u260D"
    const val SYM_QUINCUNX = "\u26BB"
    const val SYM_SEMISEXTILE = "\u26BA"
    const val SYM_QUINTILE = "\u2B20"
    const val SYM_BIQUINTILE = "\u2B20\u00B2"
    const val SYM_SEMISQUARE = "\u2220"
    const val SYM_SESQUISQUARE = "\u26BC"
    const val SYM_SEPTILE = "S\u2077"
    const val SYM_NOVILE = "N\u2092"
    const val SYM_ASPECT_NONE = "\u26AC"

    //elements
    const val SYM_FIRE_ELEMENT = "\u2632" //fire trigram
    const val SYM_EARTH_ELEMENT = "\u2637" //earth trigram
    const val SYM_AIR_ELEMENT = "\u2634" //wind trigram
    const val SYM_WATER_ELEMENT = "\u2635" //water trigram

    //modes
    const val SYM_CARDINAL_MODE = "\u23C5" //intended to be "\u1F70D" sulfur
    const val SYM_FIXED_MODE = "\u2296" //salt with 'circled-minus'
    //const val SYM_FIXED_MODE = "\u2D31" //salt with yab
    const val SYM_MUTABLE_MODE = "\u263F" //mercury

    //terminal colors
    const val KNRM = "\u001B[0m"
    const val KRED = "\u001B[31m"
    const val KGRN = "\u001B[32m"
    const val KYEL = "\u001B[33m"
    const val KBLU = "\u001B[34m"
    const val KMAG = "\u001B[35m"
    const val KCYN = "\u001B[36m"
    const val KWHT = "\u001B[37m"
    const val KGRN_BG = "\u001B[42m"
    const val KBBLK = "\u001B[90m"
    const val KBRED = "\u001B[91m"
    const val KBGRN = "\u001B[92m"
    const val KBYEL = "\u001B[93m"
    const val KBBLU = "\u001B[94m"
    const val KBMAG = "\u001B[95m"
    const val KBCYN = "\u001B[96m"
    const val KBWHT = "\u001B[97m"

    const val TZ_EDT = (-4)
    const val TZ_EST = (-5)
    const val TZ_CDT = (-5)
    const val TZ_CST = (-6)
    const val TZ_MDT = (-6)
    const val TZ_MST = (-7)
    //-97.75, 30.2667 : astro.com long/lat for ATX
    const val LON_ATX = (-97.7431)
    const val LAT_ATX = 30.2672
    const val ALT_ATX = (130 + 305) / 2 //avg elevation
    const val LON_TNM = (-105.5734)
    const val LAT_TNM = 36.4072
    const val ALT_TNM = 2124
    //-96.3334, 30.6333 : astro.com long/lat for BCS
    const val LON_BCS = (-96.37)
    const val LAT_BCS = 30.6744
    const val ALT_BCS = 114
    const val LON_TNY = (-73.6918)
    const val LAT_TNY = 42.7284
    const val ALT_TNY = (0 + 200) / 2 //avg elevation
    const val KEY_ESC = 27
    const val KEY_SPACE = 32
    const val KEY_PLUS = 43
    const val KEY_EQUALS = 61

//used to set profiles
    const val KEY_BANG = 33
    const val KEY_AT = 64
    const val KEY_HASH = 35
    const val KEY_DOLLAR = 36
    const val KEY_PERCENT = 37
    const val KEY_CARET = 94
    const val KEY_AMPERSAND = 38
    const val KEY_ASTERISK = 42
    const val KEY_OPENPARENS = 40
    const val KEY_CLOSEPARENS = 41

//used to recall profiles
    const val KEY_1 = 49
    const val KEY_2 = 50
    const val KEY_3 = 51
    const val KEY_4 = 52
    const val KEY_5 = 53
    const val KEY_6 = 54
    const val KEY_7 = 55
    const val KEY_8 = 56
    const val KEY_9 = 57
    const val KEY_0 = 48

    const val KEY_MINUS = 45
    const val KEY_QMARK = 63

    const val KEY_A = 65 //Latitude entry
    const val KEY_C = 67 //Character analysis
    const val KEY_D = 68 //Date entry
    const val KEY_E = 69 //Element analysis
    const val KEY_M = 77 //Mode analysis
    const val KEY_O = 79 //Longitude entry
    const val KEY_P = 80 //Planet analysis
    const val KEY_R = 82 //Romantic analysis
    const val KEY_T = 84 //Time entry
    const val KEY_Z = 90 //Timezone entry
    const val KEY_USCORE = 95 //return syn/comp to natal

    const val KEY_a = 97 //cycle through aspect settings (major / minor / all)
    const val KEY_d = 100 //navigate by day
    const val KEY_e = 101 //toggle details
    const val KEY_h = 104 //navigate by hour
    const val KEY_m = 109 //navigate by minute
    const val KEY_r = 114 //toggle asteroids
    const val KEY_s = 115 //navigate by second
    const val KEY_t = 116 //toggle time aspect
    const val KEY_w = 119 //navigate by week
    const val KEY_o = 111 //navigate by month
    const val KEY_p = 112 //cycle through presets (default / selective / hybrid)
    const val KEY_x = 120 //restore defaults
    const val KEY_y = 121 //navigate by year
    const val KEY_z = 122 // ?

    const val SYM_NATCOMP_CHART = "\u25A7" //diagonal box
    const val SYM_SYNASTRY_CHART = "\u25A6" //crosshatch box
    const val SYM_GREG_CALENDAR = "\u25A4 " //square with horizontal fill
    const val SYM_DIAMOND_DOUBLE_ORNAMENT = "\u25C8"
    const val SYM_CIRCLE_DOUBLE_ORNAMENT = "\u25C9"
    const val SYM_DIAMOND_SINGLE_ORNAMENT = "\u22C4"
    const val SYM_CIRCLE_SINGLE_ORNAMENT = "\u233E"
//    const val SYM_CIRCLE_SINGLE_ORNAMENT = "\u29BE" // circled white bullet

//    const val SYM_DEFAULT_ORBS = "\u25A1" //white square
//    const val SYM_SELECTIVE_ORBS = "\u25A0" //black square
//    const val SYM_HYBRID_ORBS = "\u25A3" //black within white square

    const val BOX_DOUBLE_HORZ = "\u2550"
    const val BOX_DOUBLE_VERT = "\u2551"
    const val BOX_TRIPLE_VERT = "\u2980"
    const val BOX_DOUBLE_CORNER_DOWN_TO_RIGHT = "\u2554"
    const val BOX_SINGLE_HORZ = "\u2500"
    const val BOX_SINGLE_VERT = "\u2502"
    const val BOX_SINGLE_CORNER_DOWN_TO_RIGHT = "\u250C"
    const val SYM_SIGMA = "\u2211"

    const val SYM_BECAUSE = "\u2235"
    const val SYM_THEREFORE = "\u2234"

    const val SYM_EMPTY_HGLASS = "\u29D6"
    const val SYM_FULL_HGLASS = "\u29D7"
    const val SYM_ROMAN_TWO = "\u2161"
    const val SYM_ROMAN_THREE = "\u2162"

    const val SYM_DEFAULT_ORBS = "\u24D3" //circle d
    const val SYM_SELECTIVE_ORBS = "\u24E2" //circle s
    const val SYM_HYBRID_ORBS = "\u24D7" //circle h

    const val SYM_NEGATION = "\u00AC" //not
    const val SYM_ANGLE = "\u2222" //angle

    const val SYM_MAJOR_BOX = "\u229E" //box with plus
    const val SYM_MINOR_BOX = "\u229F" //box with minus
    const val SYM_ALL_BOX = "\u22A0" //box with cross

    const val SYM_SUNNY = "\u2600"
    const val SYM_CLOUDY = "\u2601"
    const val SYM_STIMULATION = "\u26A1\uFE0E" //voltage
    const val SYM_IMPROVEMENT = "\u2195" //updown arrow
    const val SYM_CHARACTER = "\u2638" //dharmachakra
    const val SYM_ROMANTIC = "\u2661" //empty heart
    const val SYM_PLANET = SYM_CELESTIAL
    const val SYM_ELEMENT = "\u2697" //alembic
    const val SYM_MODE = "\u229B" //circled-asterisk

    const val SYM_PEACE = "\u262E" //peace symbol
    const val SYM_LOVE = "\u2665" //full heart
    const val SYM_HARMONY = "\u262F" //yin-yang

    fun byteVarArrayToInt(bytes: CArrayPointer<ByteVar>): Int {
        val lengthIdx = bytes.toKString().length - 2
        var result = 0
        var powIdx : Int

        for (idx in 0..lengthIdx) {
            powIdx = lengthIdx - idx
            result += ( (bytes[idx].toInt() - KEY_0) * pow(10.0, powIdx.toDouble()).toInt() )
        }
        return result
    }

    fun isFirst63Primes(value : Int) : Boolean {
        return when (value) {
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71
                , 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173
                , 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281
                , 283, 293, 307
            -> true
            else -> false
        }
    }

    fun isFirst15Fibonaccis(value : Int) : Boolean {
        return when (value) {
            0, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377
            -> true
            else -> false
        }
    }

    fun isFirst10Power2s(value : Int) : Boolean {
        return when (value) {
            1, 2, 4, 8, 16, 32, 64, 128, 256, 512
            -> true
            else -> false
        }
    }

    const val InvalidOrb = -1.0

    fun Double.normalizeDeg() : Double {
        var returnAngle = this

        while ( returnAngle >= 360.0 ) returnAngle -= 360.0
        while ( returnAngle < 0.0 ) returnAngle += 360.0

        return returnAngle
    }
}
