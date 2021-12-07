plugins {
    kotlin("multiplatform") version "1.5.10"
}

group = "river.exertion"
version = "0.3.3"

repositories {
    mavenCentral()
    maven(url = "https://kotlin.bintray.com/kotlinx/")
}

kotlin {
    linuxX64("native") {
        compilations["main"].cinterops {
            val libswe by creating {
            }
        }
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }
    sourceSets {
        val nativeMain by getting
        val nativeTest by getting
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.1.1")
            }
        }
    }
}