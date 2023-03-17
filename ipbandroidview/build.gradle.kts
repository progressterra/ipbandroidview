import java.io.ByteArrayOutputStream

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("maven-publish")
}

android {

    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }

    namespace = "com.progressterra.ipbandroidview"

    publishing {
        multipleVariants {
            includeBuildTypeValues("debug", "release")
        }
    }
}

afterEvaluate {
    val result = ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-list", "HEAD", "--count")
        standardOutput = result
        isIgnoreExitValue = true
    }
    val countCommits = result.toString().trim().toInt()
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
            println(fullVersion)
        }
    }

}

dependencies {
    // Core
    api("androidx.core:core-ktx:1.10.0")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.8.0")

    // Coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Orbit, MVI library
    api("org.orbit-mvi:orbit-core:4.6.1")
    api("org.orbit-mvi:orbit-viewmodel:4.6.1")
    api("org.orbit-mvi:orbit-compose:4.6.1")

    // Activity
    api("androidx.activity:activity-ktx:1.7.1")
    api("androidx.activity:activity-compose:1.7.1")

    // Compose
    api("androidx.compose.ui:ui:1.4.2")
    api("androidx.compose.ui:ui-util:1.4.2")
    api("androidx.compose.ui:ui-tooling:1.4.2")
    api("androidx.compose.ui:ui-tooling-preview:1.4.2")
    api("androidx.compose.foundation:foundation:1.4.2")
    api("androidx.compose.material:material:1.4.2")
    api("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Koin, DI library
    api("io.insert-koin:koin-android:3.4.0")
    api("io.insert-koin:koin-androidx-compose:3.4.4")

    // Accompanist, useful composables
    api("com.google.accompanist:accompanist-systemuicontroller:0.30.0")
    api("com.google.accompanist:accompanist-pager-indicators:0.30.0")

    // Testing
    api("org.orbit-mvi:orbit-test:4.6.1")
    testApi("junit:junit:4.13.2")

    // Appyx, navigation library
    api("com.bumble.appyx:core:1.2.0")

    // Google Firebase platform
    api(platform("com.google.firebase:firebase-bom:31.5.0"))
    api("com.google.firebase:firebase-analytics-ktx")
    api("com.google.firebase:firebase-messaging-ktx")

    // Google services
    api("com.google.android.gms:play-services-location:21.0.1")
    api("com.google.maps.android:maps-compose:2.11.4")
    api("com.google.android.gms:play-services-maps:18.1.0")

    // iProBonusAndroidAPI
    releaseApi("com.progressterra.ipbandroidapi:ipbandroidapi:0.4.93")
    debugApi("com.progressterra.ipbandroidapi:ipbandroidapitest:0.4.93")

    // Kotpref
    api("com.chibatching.kotpref:kotpref:2.13.2")
    api("com.chibatching.kotpref:gson-support:2.13.2")

    // Compose-written calendar dialog
    api("com.squaredem:composecalendar:1.0.4")

    // Landscapist, image library with coil implementation
    api("com.github.skydoves:landscapist-fresco:2.1.11")
    api("com.github.skydoves:landscapist-placeholder:2.1.11")

    // TODO paging if there will be grid support then need to delete own solution
    api("androidx.paging:paging-runtime-ktx:3.1.1")
    api("androidx.paging:paging-compose:1.0.0-alpha18")

    // QR
    api("com.google.zxing:core:3.5.1")

    // HTML
    api("de.charlex.compose:html-text:1.4.1")

    // Reflection
    api("org.jetbrains.kotlin:kotlin-reflect:1.8.20")

    ksp(files("processors.jar"))
    implementation(files("processors.jar"))
}
