import java.io.ByteArrayOutputStream

apply(plugin = "maven-publish")

fun countCommits(): Int {
    val result = ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-list", "HEAD", "--count")
        standardOutput = result
        isIgnoreExitValue = true
    }
    val output = result.toString().trim()
    return output.toInt()
        ?: throw RuntimeException("Could not generate commit count on this platform")
}

fun minorVersion(): String = "${(countCommits() / 100)}.${countCommits() % 100}"

afterEvaluate {
    configure<org.gradle.api.publish.PublishingExtension> {
        val major = "0"
        val group = "com.progressterra.ipbandroidview"
        val fullVersion = "$major.${minorVersion()}"
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/progressterra/ipbandroidview")
                credentials {
                    username = project.findProperty("gpr.user") as String?
                    password = project.findProperty("gpr.key") as String?
                }
            }
        }
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = group
                artifactId = "ipbandroidviewuiconfig"
                version = fullVersion
            }
            create<MavenPublication>("debug") {
                from(components["debug"])
                groupId = group
                artifactId = "ipbandroidviewuiconfigtest"
                version = fullVersion
            }
        }
        println(fullVersion)
    }
}
