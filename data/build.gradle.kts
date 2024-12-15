import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.detekt)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlin.kapt)


}

val secretsPropertiesFile: File = project.file("secrets.properties")
val secretsProperties = Properties().apply {
    load(FileInputStream(secretsPropertiesFile))
}

android {
    namespace = "com.pradip.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }

        buildFeatures {
            buildConfig = true
        }


        val baseUrl = secretsProperties["base.url"] as String
        val apiKey = secretsProperties["api.key"] as String
        buildConfigField("String", "BASE_URL", baseUrl)
        buildConfigField("String", "API_SECRET_KEY", apiKey)

    }

    buildTypes {
        debug {
            enableAndroidTestCoverage = true
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)

    // Data Store
    api(libs.androidx.datastore.preferences)

    // Networking
    api(libs.retrofit)
    api(libs.retrofit.converter.gson)
    api(libs.okhttp.logging.interceptor)

    // Room
    api(libs.androidx.room.ktx)
    api(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    // DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)


    // Test
    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
    testImplementation(libs.kotlin.corutiens.test)

    // AndroidTest
    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.turbine)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.kotlin.corutiens.test)
    androidTestImplementation(libs.androidx.espresso.core)
}
