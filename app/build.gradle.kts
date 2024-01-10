plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.utick.dvtcodingassessment"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.utick.dvtcodingassessment"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Deps.ANDROID_X_CORE)
    implementation(Deps.ANDROID_LIFECYCLE)
    implementation(Deps.ACTIVITY_COMPOSE)
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation(Deps.COMPOSE_UI)
    implementation(Deps.COMPOSE_UI_GRAPHICS)
    implementation(Deps.COMPOSE_TOOLING_PREVIEW)
    implementation(Deps.MATERIAL3)
    implementation(Deps.KTOR_CIO)
    implementation(Deps.KTOR_CORE)
    implementation(Deps.KTOR_CLIENT_ANDROID)
    implementation(Deps.KTOR_CLIENT_LOGGING)
    implementation(Deps.KTOR_CONTENT_NEGOTIATION)
    implementation(Deps.KOTLINX)
    testImplementation(Deps.JUNIT)
    implementation(Deps.KOIN)
    testImplementation(Deps.KOIN_JUNIT)
    implementation(Deps.KOIN_KTOR)
    implementation(Deps.KOIN_LOGGER)
    implementation(Deps.KOTLINX_JSON)
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}