object Deps {

    //Android
    const val ANDROID_X_CORE = "androidx.core:core-ktx:${Versions.ANDROID_X_CORE}"
    const val ANDROID_LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.ANDROID_LIFECYCLE}"
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE}"
    const val COMPOSE_UI = "androidx.compose.ui:ui"
    const val COMPOSE_UI_GRAPHICS = "androidx.compose.ui:ui-graphics"
    const val COMPOSE_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val MATERIAL3 = "androidx.compose.material3:material3"

    //Testing
    const val JUNIT = "org.junit.jupiter:junit-jupiter:${Versions.JUNIT}"
    const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
    const val KTOR_MOCK = "io.ktor:ktor-client-mock:${Versions.KTOR}"
    const val KOTEST = "io.kotest:kotest-runner-junit5:${Versions.KOTEST}"
    const val KOTEST_ASSERTIONS = "io.kotest:kotest-assertions-core:${Versions.KOTEST}"
    const val ANDROID_X_TESTING = "androidx.arch.core:core-testing:${Versions.ANDROID_X_TESTING}"
    const val KOTLINX_CO_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KOTLINX_CO_TEST}"

    //KTOR
    const val KTOR_CORE = "io.ktor:ktor-client-core:${Versions.KTOR}"
    const val KTOR_CIO = "io.ktor:ktor-client-cio:${Versions.KTOR}"
    const val KOTLINX = "io.ktor:ktor-serialization-kotlinx-json:${Versions.KTOR}"
    const val KTOR_CONTENT_NEGOTIATION = "io.ktor:ktor-client-content-negotiation:${Versions.KTOR}"
    const val KTOR_CLIENT_ANDROID = "io.ktor:ktor-client-android:${Versions.KTOR}"
    const val KTOR_CLIENT_LOGGING = "io.ktor:ktor-client-logging:${Versions.KTOR}"
    const val KOTLINX_JSON = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KOTLINX}"

    //DI
    const val KOIN = "io.insert-koin:koin-android:${Versions.KOIN}"
    const val KOIN_JUNIT = "io.insert-koin:koin-test-junit4:${Versions.KOIN}"
    const val KOIN_KTOR = "io.insert-koin:koin-ktor:${Versions.KOIN}"
    const val KOIN_LOGGER = "io.insert-koin:koin-logger-slf4j:${Versions.KOIN}"
}