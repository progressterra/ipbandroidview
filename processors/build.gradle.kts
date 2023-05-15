plugins {
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<Jar> {
    archiveFileName.set("processors.jar")
}

dependencies {

    implementation("com.google.devtools.ksp:symbol-processing-api:1.8.20-1.0.11")
}