plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.roborazzi)
    jacoco
}

jacoco {
    toolVersion = "0.8.13-SNAPSHOT"
}

tasks.withType<Test>().configureEach {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}

tasks.register<JacocoReport>("createCoverageReport") {
    dependsOn("testDebugUnitTest")
    group = "Reporting"
    description = "Generate Jacoco coverage reports on the unit tests build."
    reports {
        html.required.set(true)
    }

    val coverageExclusions = listOf(
        "**/R.class",
        "**/R\$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*ClassesWithAsm/*",
    )

    val javaDirectories = fileTree(
        "${project.layout.buildDirectory.get()}/intermediates/classes/debug"
    ) { exclude(coverageExclusions) }

    val kotlinDirectories = fileTree(
        "${project.layout.buildDirectory.get()}/tmp/kotlin-classes/debug"
    ) { exclude(coverageExclusions) }

    val coverageSrcDirectories = listOf(
        "${project.projectDir}/src/main/java",
    )

    classDirectories.setFrom(files(javaDirectories, kotlinDirectories))
    additionalClassDirs.setFrom(files(coverageSrcDirectories))
    sourceDirectories.setFrom(files(coverageSrcDirectories))
    executionData.setFrom(file("${project.layout.buildDirectory.get()}/jacoco/testDebugUnitTest.exec"))
}

android {
    namespace = "net.kosev.roborazzidemo"
    compileSdk = 35

    defaultConfig {
        applicationId = "net.kosev.roborazzidemo"
        minSdk = 26
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
    testOptions.unitTests.isIncludeAndroidResources = true
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.viewmodel.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.robolectric)
    testImplementation(libs.roborazzi)
    testImplementation(libs.roborazzi.compose)
    testImplementation(libs.roborazzi.junit)
    testImplementation(libs.androidx.compose.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}