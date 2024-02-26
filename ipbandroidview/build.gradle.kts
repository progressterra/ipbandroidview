import de.undercouch.gradle.tasks.download.Download

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("maven-publish")
    id("de.undercouch.download")
}

android {

    defaultConfig {
        minSdk = 26
        compileSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt")
            )
            buildConfigField("String", "VERSION", "\"${project.version}\"")
        }
        debug {
            buildConfigField("String", "VERSION", "\"${project.version}\"")
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
        viewBinding = true
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }

    namespace = "com.progressterra.ipbandroidview"

    publishing {
        singleVariant("release")
    }
}

afterEvaluate {
    configure<PublishingExtension> {
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
                artifactId = "ipbandroidviewuiconfig"
            }
        }
    }
}

dependencies {
    // Core
    api("androidx.core:core-ktx:1.12.0")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.11.0")
    api("androidx.fragment:fragment-compose:1.7.0-alpha10")

    // Coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Activity
    api("androidx.activity:activity-ktx:1.8.2")
    api("androidx.activity:activity-compose:1.8.2")

    // Compose
    api("androidx.compose.ui:ui:1.6.2")
    api("androidx.compose.ui:ui-util:1.6.2")
    api("androidx.compose.ui:ui-tooling:1.6.2")
    api("androidx.compose.ui:ui-tooling-preview:1.6.2")
    api("androidx.compose.foundation:foundation:1.6.2")
    api("androidx.compose.material:material:1.6.2")
    api("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Dialogs
    api("com.maxkeppeler.sheets-compose-dialogs:core:1.3.0")
    api("com.maxkeppeler.sheets-compose-dialogs:calendar:1.3.0")

    // Yandex
    api("com.yandex.android:maps.mobile:4.4.0-lite")

    // Koin, DI library
    api("io.insert-koin:koin-android:3.5.3")
    api("io.insert-koin:koin-androidx-compose:3.5.3")

    // Accompanist, useful composables
    api("com.google.accompanist:accompanist-systemuicontroller:0.34.0")
    api("com.google.accompanist:accompanist-pager-indicators:0.34.0")
    api("com.google.accompanist:accompanist-permissions:0.34.0")

    // Testing
    testApi("junit:junit:4.13.2")

    // Appyx
    api("com.bumble.appyx:core:1.4.0")

    // Google Firebase platform
    api(platform("com.google.firebase:firebase-bom:32.7.2"))
    api("com.google.firebase:firebase-analytics-ktx")
    api("com.google.firebase:firebase-messaging-ktx")
    api("com.google.firebase:firebase-crashlytics-ktx")

    // Google services
    api("com.google.android.gms:play-services-location:21.1.0")
    api("com.google.android.gms:play-services-maps:18.2.0")

    // iProBonusAndroidAPI
    api("com.progressterra.ipbandroidapi:ipbandroidapi:1.1.16")
    api("com.progressterra:shared:1.0")

    // Kotpref
    api("com.chibatching.kotpref:kotpref:2.13.2")
    api("com.chibatching.kotpref:gson-support:2.13.2")

    api("de.hdodenhof:circleimageview:3.1.0")

    // Landscapist
    api("com.github.skydoves:landscapist-glide:2.3.1")
    api("com.github.skydoves:landscapist-placeholder:2.3.1")

    api("androidx.paging:paging-runtime-ktx:3.2.1")
    api("androidx.paging:paging-compose:3.2.1")

    // WorkManager
    api("androidx.work:work-runtime-ktx:2.9.0")

    // Video
    api("androidx.media3:media3-exoplayer:1.2.1")
    api("androidx.media3:media3-ui:1.2.1")

    // Payments
    api("ru.yoomoney.sdk.kassa.payments:yookassa-android-sdk:6.10.0")

    val cameraxVersion = "1.3.1"
    api("androidx.camera:camera-core:$cameraxVersion")
    api("androidx.camera:camera-camera2:$cameraxVersion")
    api("androidx.camera:camera-lifecycle:$cameraxVersion")
    api("androidx.camera:camera-view:$cameraxVersion")

    api("com.jakewharton.timber:timber:5.0.1")
}
