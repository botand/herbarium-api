package dev.xavierc.herbarium.api

// Use this file to hold package-level internal functions that return receiver object passed to the `install` method.
import com.typesafe.config.ConfigFactory
import io.ktor.auth.*
import io.ktor.config.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.util.*
import java.time.Duration
import java.util.concurrent.TimeUnit

/**
 * Application block for [CORS] configuration.
 *
 * This file may be excluded in .openapi-generator-ignore,
 * and application specific configuration can be applied in this function.
 *
 * See http://ktor.io/features/cors.html
 */
internal fun applicationCORSConfiguration(): CORS.Configuration.() -> Unit {
    return {
        method(HttpMethod.Put)
        method(HttpMethod.Post)
        method(HttpMethod.Get)
        method(HttpMethod.Delete)
        allowCredentials = true
        // method(HttpMethod.Options)
        // header(HttpHeaders.XForwardedProto)
        // anyHost()
        // host("my-host")
        // host("my-host:80")
        // host("my-host", subDomains = listOf("www"))
        // host("my-host", schemes = listOf("http", "https"))
        // allowCredentials = true
        // maxAge = Duration.ofDays(1)
    }
}

/**
 * Application block for [Database] configuration
 *
 * Return the database section of the application.conf file
 */
internal fun applicationDatabaseConfiguration(): ApplicationConfig {
    val appConfig = HoconApplicationConfig(ConfigFactory.load())

    return appConfig.config("database")
}

/**
 * Return the api section of the application.conf file
 */
internal fun applicationConfiguration(): ApplicationConfig {
    val appConfig = HoconApplicationConfig(ConfigFactory.load())

    return appConfig.config("api")
}

/**
 * Application block for [HSTS] configuration.
 *
 * This file may be excluded in .openapi-generator-ignore,
 * and application specific configuration can be applied in this function.
 *
 * See http://ktor.io/features/hsts.html
 */
internal fun applicationHstsConfiguration(): HSTS.Configuration.() -> Unit {
    return {
        maxAgeInSeconds = TimeUnit.DAYS.toSeconds(365)
        includeSubDomains = true
        preload = false

        // You may also apply any custom directives supported by specific user-agent. For example:
        // customDirectives.put("redirectHttpToHttps", "false")
    }
}

/**
 * Application block for [Compression] configuration.
 *
 * This file may be excluded in .openapi-generator-ignore,
 * and application specific configuration can be applied in this function.
 *
 * See http://ktor.io/features/compression.html
 */
internal fun applicationCompressionConfiguration(): Compression.Configuration.() -> Unit {
    return {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024) // condition
        }
    }
}
