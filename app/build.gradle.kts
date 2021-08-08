import dependencies.BuildVersion
import dependencies.AndroidX
import dependencies.Google
import dependencies.Hilt
import dependencies.Kotlinx
import dependencies.Ktor
import dependencies.RXdependencies
import dependencies.Serialization
import dependencies.Kotlin
import dependencies.Banner
import dependencies.Glide


plugins {
    id("com.android.application")
    kotlin("android")
    id ("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.5.21"
    id("kotlin-android")
}

android {

    compileSdkVersion (BuildVersion.compileSdk)
    buildToolsVersion = BuildVersion.buildTools
    defaultConfig {
        applicationId  = BuildVersion.appId
        minSdkVersion (BuildVersion.minSdk)
        targetSdkVersion (BuildVersion.targetSdk)
        versionCode  = BuildVersion.versionCode
        versionName = BuildVersion.versionName
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }



    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility  = JavaVersion.VERSION_1_8
        targetCompatibility  = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = BuildVersion.jvmTarget
    }

    buildFeatures {
        viewBinding = true
    }

}



dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation (Kotlin.kotlinStdlib)

    implementation(AndroidX.constraintlayout)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.lifecycleRuntime)
    implementation(AndroidX.lifecycleVmKtx)
    implementation(AndroidX.livedataKtx)
    implementation(AndroidX.fragmentKtx)
    implementation(AndroidX.swiperefreshlayout)

    implementation(Google.material)
    implementation(Hilt.android)
    kapt(Hilt.compiler)
//    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
//
//    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")

    implementation(Banner.banner)
    implementation(Glide.glide)
    kapt(Glide.gildeCompiler)

    implementation(Kotlinx.coroutinesCore)
    implementation(RXdependencies.coroutinesRx)
    implementation(RXdependencies.rxkotlin)
    implementation(RXdependencies.rxAndroid)
    implementation(RXdependencies.rxJava)

    implementation(Serialization.serialization)

    implementation(Ktor.core)
    implementation(Ktor.clientSerialization)
    implementation(Ktor.android)
    implementation(Ktor.ktorJson)
    implementation(Ktor.logging)

//    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.1.2-alpha01")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.2-alpha01")




}


//plugins {
//    id 'com.android.application'
//    id 'kotlin-android'
//}
//
//android {
//    compileSdkVersion 30
//    buildToolsVersion "31.0.0"
//
//    defaultConfig {
//        applicationId "com.example.sary_app"
//        minSdkVersion 18
//        targetSdkVersion 30
//        versionCode 1
//        versionName "1.0"
//
//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
//    }
//
//    buildTypes {
//        release {
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//    }
//    compileOptions {
//        sourceCompatibility JavaVersion.VERSION_1_8
//        targetCompatibility JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = '1.8'
//    }
//}
//
//dependencies {
//
//    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
//    implementation 'androidx.core:core-ktx:1.6.0'
//    implementation 'androidx.appcompat:appcompat:1.3.1'
//    implementation 'com.google.android.material:material:1.4.0'
//    implementation 'androidx.constraintlayout:constraintlayout:2.1.0'
//    testImplementation 'junit:junit:4.+'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
//}