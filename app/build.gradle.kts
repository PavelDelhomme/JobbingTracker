plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)  // 🔧 indispensable pour Compose + Kotlin 2.x
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}


kapt {
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas")
        //arg("room.incremental", "true")
        //arg("room.expandProjection", "true")
    }
}

android {
    namespace = "com.delhomme.jobbingtrack"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.delhomme.jobbingtrack"
        minSdk = 28
        targetSdk = 35
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
        languageVersion = "2.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"  // version correcte avec Kotlin 2.x
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.places)
    implementation(libs.androidx.annotation)
    implementation(libs.common)
    androidTestImplementation(libs.androidx.espresso.core)

    // Material
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)

    // Retrofit & Moshi
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)

    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Jetpack Lifecycle (ViewModel, LiveData)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Jetpack Navigation Compose
    implementation(libs.androidx.navigation.compose)

    // Secure Storage
    implementation(libs.androidx.security.crypto)

    // Material Components (déjà via Material3 normalement mais au cas où pour compatibilité)
    implementation(libs.material)
    implementation(libs.material3)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))

    // UI
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //implementation(libs.androidx.ui.tooling.preview)

    // Testing
    testImplementation(libs.junit)
    testImplementation(project(":app"))
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Calendar
    implementation("com.kizitonwose.calendar:compose:2.6.2")

    // Room
    implementation(libs.androidx.room.common.jvm)
    implementation(libs.androidx.room.runtime)
    kapt("androidx.room:room-compiler:2.7.1")
    implementation("androidx.room:room-ktx:2.7.1")

    // Charts
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.56.2")
    kapt("com.google.dagger:hilt-android-compiler:2.56.2")

    // Pour intégration Compose Navigation (optionnel mais recommandé)
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
}
