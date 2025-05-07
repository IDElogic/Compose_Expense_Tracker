plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("io.realm.kotlin") version "3.0.0"
    id("io.sentry.android.gradle") version "3.4.2"

}

android {
    namespace = "com.android.expensetracker"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.android.expensetracker"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("com.marosseleng.android:compose-material3-datetime-pickers:0.6.0")
    implementation (libs.accompanist.permissions)
    implementation ("io.sentry:sentry-android:8.10.0")
    implementation ("io.sentry:sentry-compose-android:8.10.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
    implementation("com.google.accompanist:accompanist-pager:0.36.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.36.0")
    implementation ("com.github.skydoves:colorpicker-compose:1.0.0")
    implementation ("me.saket.swipe:swipe:1.3.0")
    implementation ("io.github.serpro69:kotlin-faker:1.13.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
    implementation ("io.realm.kotlin:library-base:3.0.0")

    // Navigation
    implementation("androidx.navigation:navigation-runtime-ktx:2.8.9")
    implementation("androidx.navigation:navigation-compose:2.8.9")

    implementation ("androidx.compose.material3:material3:1.3.2")
    implementation ("androidx.compose.material3:material3-window-size-class:1.3.2")
    implementation ("androidx.compose.material3:material3-adaptive-navigation-suite:1.4.0-alpha13")
    implementation("androidx.compose.material:material:1.9.0-alpha01")
    
    implementation ("androidx.compose.animation:animation:1.8.0")
}