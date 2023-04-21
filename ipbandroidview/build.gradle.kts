plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.android")
}

apply(from = "publish.gradle.kts")

android {

    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
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
    api("androidx.activity:activity-ktx:1.7.0")
    api("androidx.activity:activity-compose:1.7.0")

    // Compose
    api("androidx.compose.ui:ui:1.4.1")
    api("androidx.compose.ui:ui-util:1.4.1")
    api("androidx.compose.ui:ui-tooling:1.4.1")
    api("androidx.compose.ui:ui-tooling-preview:1.4.1")
    api("androidx.compose.foundation:foundation:1.4.1")
    api("androidx.compose.material:material:1.4.1")
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
    releaseApi("com.progressterra.ipbandroidapi:ipbandroidapi:0.4.88")
    debugApi("com.progressterra.ipbandroidapi:ipbandroidapitest:0.4.88")

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
}

