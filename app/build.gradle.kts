plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.hotel_customer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hotel_customer"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // remote
    implementation("com.squareup.retrofit2:converter-gson:2.5.0")
    implementation( "com.github.bumptech.glide:glide:5.0.0-rc01")
    // lombook
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor( "org.projectlombok:lombok:1.18.32")
    // image slider
    implementation("com.google.android.material:material:1.3.0")
    implementation("me.relex:circleindicator:2.1.6")
    // image picker in device
    implementation("com.github.dhaval2404:imagepicker:2.1")
    // chart
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
}