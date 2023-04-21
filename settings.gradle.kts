pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            } else if (requested.id.namespace == "com.google.gms") {
                useModule("com.google.gms:${requested.id.name}:${requested.version}")
            }
        }
    }
}

rootProject.name = "ipbandroidview"
include(":ipbandroidview")
