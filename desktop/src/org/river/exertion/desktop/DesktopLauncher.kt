package org.river.exertion.desktop;

import com.badlogic.gdx.Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import org.river.exertion.SweetAstroConsole

object DesktopLauncher {
	val windowWidth = 1366
	val windowHeight = 768

	@JvmStatic
	fun main(arg: Array<String>) {
		val config = Lwjgl3ApplicationConfiguration().apply {
			setTitle("sweetAstroConsole v0.4")
			setWindowedMode(windowWidth, windowHeight)
			setBackBufferConfig(8, 8, 8, 8, 16, 0, 16)
		}

		Lwjgl3Application(SweetAstroConsole(), config).logLevel = Application.LOG_DEBUG
	}
}
