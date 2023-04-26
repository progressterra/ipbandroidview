plugins {
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

configurations {
    create("jarRelease")
}

val jarTask = tasks.register<Jar>("jarRelease") {
    from(sourceSets["main"].output)
    archiveClassifier.set("release")
}

artifacts {
    add("jarRelease", jarTask)
}

dependencies {

    implementation("com.google.devtools.ksp:symbol-processing-api:1.8.20-1.0.11")
}