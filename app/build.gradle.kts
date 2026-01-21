plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt") // ✅ REQUIRED FOR ROOM

}

android {
    namespace = "com.example.newsapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.newsapp"
        minSdk = 24
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
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // AndroidX Core + Lifecycle
    implementation("androidx.core:core-ktx:1.16.0")          // compatible with AGP 8.8.2
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.0")

    // Activity + Compose
    implementation("androidx.activity:activity-compose:1.10.0") // compatible with AGP 8.8.2
    implementation(platform("androidx.compose:compose-bom:2024.04.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.2.0")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.6.0") // compatible with Compose 1.4-1.5

    // Coil for images
    implementation("io.coil-kt:coil-compose:2.4.0") // compatible with Compose 1.4-1.5

    // Retrofit + Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp + logging
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation(libs.androidx.paging.common.android)

    // ---------- TESTING ----------
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.04.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.datastore:datastore-preferences:1.1.0")
    implementation("androidx.paging:paging-compose:3.2.0") // make sure version matches paging
    implementation("androidx.paging:paging-runtime:3.2.0") // core paging

    // UNIT TEST (MUST HAVE)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    testImplementation("io.mockk:mockk:1.13.11")



    // ✅ ADD THIS for pullRefresh
    implementation("androidx.compose.material:material:1.6.1")

    // Room (Database)
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1") // ✅ THIS WAS MISSING



}