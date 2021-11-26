package console.render

import Constants
import kotlinx.cinterop.*
import kotlinx.datetime.*
import platform.posix.*
import profile.base.Profile
import swe.SweEphePath

object RenderHandler {

    private val initTerminalAttributes : termios = nativeHeap.alloc()
    private val immediateTerminalAttributes : termios = nativeHeap.alloc()
    private val initFcntlFlags = fcntl(0, F_GETFL, 0)
    private val nonBlockFcntlFlags = initFcntlFlags.or(O_NONBLOCK)

    @ExperimentalUnsignedTypes
    fun init() {
        SweEphePath.setPath()

        system("clear")
        system("tput civis")

        /*tcgetattr gets the parameters of the current terminal
        STDIN_FILENO will tell tcgetattr that it should write the settings
        of stdin to oldt*/
        tcgetattr(STDIN_FILENO, initTerminalAttributes.ptr)
        tcgetattr(STDIN_FILENO, immediateTerminalAttributes.ptr)

        /*ICANON normally takes care that one line at a time will be processed
        that means it will return if it sees a "\n" or an EOF or an EOL*/
        //https://blog.nelhage.com/2009/12/a-brief-introduction-to-termios-termios3-and-stty/
        immediateTerminalAttributes.c_lflag = immediateTerminalAttributes.c_lflag.and(ICANON.toUInt().inv()).and(ECHO.toUInt().inv())

        enableImmediateInput()

//        https://dev.to/mreichelt/handling-sigint-in-kotlin-native-2ac6
        signal(SIGINT, staticCFunction<Int, Unit> {
            system("tput cup 42 0")

            println("\n" + Constants.SYM_PEACE + " " + Constants.SYM_LOVE + " " + Constants.SYM_HARMONY)

            disableImmediateInput()
            system("tput cnorm")

            signal(SIGINT, SIG_DFL)

            free(initTerminalAttributes.ptr)
            free(immediateTerminalAttributes.ptr)

            exit(0)
        } )
    }

    fun disableImmediateInput() {
        tcsetattr(STDIN_FILENO, TCSANOW, initTerminalAttributes.ptr)
        fcntl(0, F_SETFL, initFcntlFlags)
    }

    fun enableImmediateInput() {
        tcsetattr(STDIN_FILENO, TCSANOW, immediateTerminalAttributes.ptr)
        fcntl(0, F_SETFL, nonBlockFcntlFlags)
    }

    //https://github.com/Kotlin/kotlinx.coroutines/issues/1225
    fun delayRun(timeSec: Long = 1) = memScoped {

        val oneSecondInNanoSeconds = 1000000000

//        sleep for #seconds - 1 - #nanoseconds until next second
        val timespec = alloc<timespec>()
        timespec.tv_sec = timeSec - 1
        timespec.tv_nsec = (oneSecondInNanoSeconds - Clock.System.now().toLocalDateTime(TimeZone.UTC).nanosecond).toLong()
        nanosleep(timespec.ptr, null)
    }

    fun clearScreen() {
        system("tput home")
    }

    fun getMainLoopInput() : Int {
        return getchar()
    }

    @ExperimentalUnsignedTypes
    fun getPauseEntryInput(prompt : String, inputOverride : String? = null) : String {

        val entrySize = Profile.getMaxProfileNameLength() //longest entry length we expect
        val cEntry = nativeHeap.allocArray<ByteVar>(entrySize)

        val returnString : String = if (inputOverride == null) {
            disableImmediateInput()

            printf("%s", prompt)
            fgets(cEntry, entrySize, stdin)

            enableImmediateInput()

            cEntry.toKString().trim()
        } else inputOverride.trim()

        if (returnString.contains(Constants.KEY_ESC.toChar()) || (returnString.isEmpty()) ) {
            nativeHeap.free(cEntry)
            return Constants.KEY_ESC.toString()
        }

        nativeHeap.free(cEntry)

        return returnString
    }
}


