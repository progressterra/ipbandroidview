plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("maven-publish")
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
        kotlinCompilerExtensionVersion = "1.5.3"
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
    api("com.google.android.material:material:1.10.0")

    // Coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Activity
    api("androidx.activity:activity-ktx:1.8.0")
    api("androidx.activity:activity-compose:1.8.0")

    // Compose
    api("androidx.compose.ui:ui:1.5.4")
    api("androidx.compose.ui:ui-util:1.5.4")
    api("androidx.compose.ui:ui-tooling:1.5.4")
    api("androidx.compose.ui:ui-tooling-preview:1.5.4")
    api("androidx.compose.foundation:foundation:1.5.4")
    api("androidx.compose.material:material:1.5.4")
    api("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Dialogs
    api("com.maxkeppeler.sheets-compose-dialogs:core:1.2.0")
    api("com.maxkeppeler.sheets-compose-dialogs:calendar:1.2.0")

    // Yandex
    api("com.yandex.android:maps.mobile:4.4.0-lite")

    // Koin, DI library
    api("io.insert-koin:koin-android:3.5.0")
    api("io.insert-koin:koin-androidx-compose:3.5.0")

    // Accompanist, useful composables
    api("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
    api("com.google.accompanist:accompanist-pager-indicators:0.30.1")

    // Testing
    testApi("junit:junit:4.13.2")

    // Appyx, navigation library
    api("com.bumble.appyx:core:1.3.0")

    // Google Firebase platform
    api(platform("com.google.firebase:firebase-bom:32.3.1"))
    api("com.google.firebase:firebase-analytics-ktx")
    api("com.google.firebase:firebase-messaging-ktx")
    api("com.google.firebase:firebase-crashlytics-ktx")

    // Google services
    api("com.google.android.gms:play-services-location:21.0.1")
    api("com.google.maps.android:maps-compose:2.15.0")
    api("com.google.android.gms:play-services-maps:18.2.0")

    // iProBonusAndroidAPI
    api("com.progressterra.ipbandroidapi:ipbandroidapi:0.5.59")

    // Kotpref
    api("com.chibatching.kotpref:kotpref:2.13.2")
    api("com.chibatching.kotpref:gson-support:2.13.2")

    // Landscapist, image library with coil implementation
    api("com.github.skydoves:landscapist-fresco:2.2.10")
    api("com.github.skydoves:landscapist-placeholder:2.2.10")

    api("androidx.paging:paging-runtime-ktx:3.2.1")
    api("androidx.paging:paging-compose:3.2.1")

    // Reflection
    api("org.jetbrains.kotlin:kotlin-reflect:1.9.10")
}
