package file

import kotlinx.cinterop.memScoped
import platform.posix.EOF
import platform.posix.fclose
import platform.posix.fopen
import platform.posix.fputs

object Csv {

    private val WRITE_MODE = "w"

    fun writeAllLines(filePath: String, lines: List<String>, lineEnding: String = "\n") = memScoped {

        val file = fopen(filePath, WRITE_MODE) ?: throw IllegalArgumentException("Cannot open output file $filePath")
        try {
            lines.forEach {
                if (fputs(it + lineEnding, file) == EOF) {
                    throw Error("File write error")
                }
            }
        } finally {
            fclose(file)
        }
    }
}