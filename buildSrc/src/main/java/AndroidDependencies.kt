object Versions {
    //General androidx
    val androidx_core_ktx = "1.7.0"
    val androidx_appcompat = "1.4.1"
    val google_android_material = "1.4.0"

    //Androidx compose
    val compose_version = "1.0.5"
    val compose_ui = compose_version
    val compose_material = compose_version
    val compose_ui_tooling_preview = compose_version
    val activity_compose = "1.4.0"
    val compose_navigation = "2.4.1"

    val coil_version = "2.0.0-rc01"


    //Lifecycle
    val lifecycle_runtime_ktx = "2.4.0"

    //Network
    val ktor_version = "2.0.0-beta-1"

    //DTO
    val kotlinx_serialization_json = "1.1.0"

    //Test
    val junit = "4.+"
    val test_ext_junit = "1.1.3"
    val test_espresso_core = "3.4.0"
    val compose_ui_test_junit4 = compose_version
    val compose_ui_tooling_test = compose_version
}

object AndroidxDependencies {
    val core_ktx = "androidx.core:core-ktx:${Versions.androidx_core_ktx}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    val material = "com.google.android.material:material:${Versions.google_android_material}"
    val lifecycle_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_runtime_ktx}"
}

object ComposeDependencies {
    val ui = "androidx.compose.ui:ui:${Versions.compose_ui}"
    val material = "androidx.compose.material:material:${Versions.compose_material}"
    val ui_tool_preview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.compose_ui_tooling_preview}"
    val activity = "androidx.activity:activity-compose:${Versions.activity_compose}"
    val navigation = "androidx.navigation:navigation-compose:${Versions.compose_navigation}"
    val navigation_lifecycle = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.compose_navigation}"
    val coil = "io.coil-kt:coil-compose:${Versions.coil_version}"
}

object Ktor {
    val client_android = "io.ktor:ktor-client-android:${Versions.ktor_version}"
    val client_serialization =
        "io.ktor:ktor-client-serialization:${Versions.ktor_version}"
    val client_logging = "io.ktor:ktor-client-logging-jvm:${Versions.ktor_version}"
    val core = "io.ktor:ktor-client-core:${Versions.ktor_version}"
    val serialization = "io.ktor:ktor-serialization:${Versions.ktor_version}"
    val content_negotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor_version}"
    val serialization_kotlinx_json = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor_version}"
}

object Kotlin {
    val serialization_json =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinx_serialization_json}"
}

object Test {
    val junit = "junit:junit:${Versions.junit}"
}

object AndroidTest {
    val androidx_junit = "androidx.test.ext:junit:${Versions.test_ext_junit}"
    val test_espresso_core = "androidx.test.espresso:espresso-core:${Versions.test_espresso_core}"
}

object Debug {
    val compose_ui_tooling = "androidx.compose.ui:ui-tooling:${Versions.compose_ui_tooling_test}"
}



