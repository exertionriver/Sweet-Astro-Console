import console.base.ConsoleState
import console.render.RenderHandler
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalUnsignedTypes
fun main() {
    RenderHandler.init()

    val consoleState = ConsoleState()

    consoleState.mainLoop()
}