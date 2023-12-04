@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.google.map.secrets)
}

android {
    namespace = "com.veo.mobikemap"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
        kotlinCompilerExtensionVersion = "1.5.2"
    }
}

dependencies {
    implementation(libs.hilt.android)
    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.compose.material3)
    implementation(libs.androidx.material.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.retrofit.core)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.google.map)
    implementation(libs.google.map.compose)
//    implementation(libs.play.services.maps)
//    implementation(libs.maps.compose.utils)
//    implementation(libs.maps.compose.widgets)
    implementation(libs.maps.ktx.utils)
    implementation(libs.maps.ktx.std)
    implementation(libs.accompanist.permissions)

    implementation(libs.play.services.location)

    api(project(mapOf("path" to ":core:common")))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

secrets{

    defaultPropertiesFileName = "local.defaults.properties"
}