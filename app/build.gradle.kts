plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(28)

    defaultConfig {
        applicationId = "br.com.wellingtoncosta.coroutines"
        minSdkVersion(16)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    dataBinding {
        isEnabled = true
    }

    aaptOptions {
        cruncherEnabled = false
        useNewCruncher = false
    }
}

dependencies {
    //AndroidX
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.core:core-ktx:1.0.2")

    // AndroidX Data Binding
    kapt("androidx.databinding:databinding-compiler:3.4.1")

    // AndroidX LiveData and ViewModel
    implementation("androidx.lifecycle:lifecycle-extensions:2.0.0")

    // Fresco
    implementation("com.facebook.fresco:fresco:2.0.0")

    // Google Material
    implementation("com.google.android.material:material:1.0.0")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.40")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.2.2")

    // Koin
    implementation("org.koin:koin-androidx-scope:1.0.2")
    implementation("org.koin:koin-androidx-viewmodel:1.0.2")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.4.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.4.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.8.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.squareup.okhttp3:logging-interceptor:3.11.0")

    // Material Search View
    implementation("com.miguelcatalan:materialsearchview:1.4.0")

    // Tests
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}