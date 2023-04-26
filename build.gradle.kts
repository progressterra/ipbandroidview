buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.android.tools.build:gradle:8.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
        classpath("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.8.20-1.0.11")
    }
}

plugins {
    id("com.android.library") version "8.0.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.0" apply false
}

subprojects {
    plugins.apply("maven-publish")

    val result = java.io.ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-list", "HEAD", "--count")
        standardOutput = result
        isIgnoreExitValue = true
    }
    val countCommits = result.toString().trim().toInt()

    afterEvaluate {
        configure<PublishingExtension> {
            val group = "com.progressterra.ipbandroidview"
            val fullVersion = "0.${(countCommits / 100)}.${countCommits % 100}"
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
                if (plugins.hasPlugin("com.android.library")) {
                    create<MavenPublication>("release") {
                        from(components.findByName("release"))
                        groupId = group
                        artifactId = "ipbandroidviewuiconfig"
                        version = fullVersion
                    }
                    create<MavenPublication>("debug") {
                        from(components.findByName("debug"))
                        groupId = group
                        artifactId = "ipbandroidviewuiconfigtest"
                        version = fullVersion
                    }
                }
                if (plugins.hasPlugin("java")) {
                    create<MavenPublication>("java") {
                        from(components.findByName("java"))
                        groupId = group
                        artifactId = project.name
                        version = fullVersion
                    }
                }
                println(fullVersion)
            }
        }

    }
}

allprojects {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/progressterra/ipbandroidapi")
            credentials {
                username = project.findProperty("gpr.user") as String?
                password = project.findProperty("gpr.key") as String?
            }
        }
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
