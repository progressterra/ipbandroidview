plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("maven-publish")
}

android {

    defaultConfig {
        minSdk = 24
        compileSdk = 33

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
        kotlinCompilerExtensionVersion = "1.4.6"
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

tasks.register<Copy>("copyProcessorsJar") {
    dependsOn(":processors:jar")

    from("../processors/build/libs") {
        include("processors.jar")
    }

    into("libs")
}

tasks.getByName("preBuild").dependsOn(tasks.getByName("copyProcessorsJar"))

dependencies {
    // Core
    api("androidx.core:core-ktx:1.10.1")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.9.0")

    // Coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Orbit, MVI library
    api("org.orbit-mvi:orbit-core:6.0.0")
    api("org.orbit-mvi:orbit-viewmodel:6.0.0")
    api("org.orbit-mvi:orbit-compose:6.0.0")

    // Activity
    api("androidx.activity:activity-ktx:1.7.2")
    api("androidx.activity:activity-compose:1.7.2")

    // Compose
    api("androidx.compose.ui:ui:1.4.3")
    api("androidx.compose.ui:ui-util:1.4.3")
    api("androidx.compose.ui:ui-tooling:1.4.3")
    api("androidx.compose.ui:ui-tooling-preview:1.4.3")
    api("androidx.compose.foundation:foundation:1.4.3")
    api("androidx.compose.material:material:1.4.3")
    api("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Koin, DI library
    api("io.insert-koin:koin-android:3.4.1")
    api("io.insert-koin:koin-androidx-compose:3.4.5")

    // Accompanist, useful composables
    api("com.google.accompanist:accompanist-systemuicontroller:0.30.0")
    api("com.google.accompanist:accompanist-pager-indicators:0.30.0")

    // Testing
    api("org.orbit-mvi:orbit-test:6.0.0")
    testApi("junit:junit:4.13.2")

    // Appyx, navigation library
    api("com.bumble.appyx:core:1.2.0")

    // Google Firebase platform
    api(platform("com.google.firebase:firebase-bom:32.1.0"))
    api("com.google.firebase:firebase-analytics-ktx")
    api("com.google.firebase:firebase-messaging-ktx")

    // Google services
    api("com.google.android.gms:play-services-location:21.0.1")
    api("com.google.maps.android:maps-compose:2.11.4")
    api("com.google.android.gms:play-services-maps:18.1.0")

    // iProBonusAndroidAPI
    api("com.progressterra.ipbandroidapi:ipbandroidapi:0.5.18")

    // Kotpref
    api("com.chibatching.kotpref:kotpref:2.13.2")
    api("com.chibatching.kotpref:gson-support:2.13.2")

    // Landscapist, image library with coil implementation
    api("com.github.skydoves:landscapist-fresco:2.2.1")
    api("com.github.skydoves:landscapist-placeholder:2.2.1")

    api("androidx.paging:paging-runtime-ktx:3.1.1")
    api("androidx.paging:paging-compose:3.2.0-beta01")

    // QR
    api("com.google.zxing:core:3.5.1")

    // HTML
    api("de.charlex.compose:html-text:1.4.1")

    // Reflection
    api("org.jetbrains.kotlin:kotlin-reflect:1.8.20")

    ksp(files("libs/processors.jar"))
    implementation(files("libs/processors.jar"))
}
