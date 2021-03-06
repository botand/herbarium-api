package dev.xavierc.herbarium.api.infrastructure

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.auth.*
import io.ktor.request.*
import io.ktor.response.*

enum class ApiKeyLocation(val location: String) {
    QUERY("query"),
    HEADER("header")
}

data class ApiKeyCredential(val value: String) : Credential
data class ApiPrincipal(val apiKeyCredential: ApiKeyCredential?) : Principal

/**
* Represents an Api Key authentication provider
* @param name is the name of the provider, or `null` for a default provider
*/
class ApiKeyAuthenticationProvider(configuration: Configuration) : AuthenticationProvider(configuration) {

    private val authenticationFunction = configuration.authenticationFunction

    private val apiKeyName: String = configuration.apiKeyName

    private val apiKeyLocation: ApiKeyLocation = configuration.apiKeyLocation

    internal fun install() {
        pipeline.intercept(AuthenticationPipeline.RequestAuthentication) { context ->
            val credentials = call.request.apiKeyAuthenticationCredentials(apiKeyName, apiKeyLocation)
            val principal = credentials?.let { authenticationFunction(call, it) }

            val cause = when {
                credentials == null -> AuthenticationFailedCause.NoCredentials
                principal == null -> AuthenticationFailedCause.InvalidCredentials
                else -> null
            }

            if (cause != null) {
                context.challenge(apiKeyName, cause) {
                    call.respond(
                        UnauthorizedResponse(
                            HttpAuthHeader.Parameterized(
                                "API_KEY",
                                mapOf("key" to apiKeyName),
                                HeaderValueEncoding.QUOTED_ALWAYS
                            )
                        )
                    )
                    it.complete()
                }
            }

            if (principal != null) {
                context.principal(principal)
            }
        }
    }

    class Configuration internal constructor(name: String?) : AuthenticationProvider.Configuration(name) {

        internal var authenticationFunction: suspend ApplicationCall.(ApiKeyCredential) -> Principal? = {
            throw NotImplementedError(
                "Api Key auth validate function is not specified. Use apiKeyAuth { validate { ... } } to fix."
            )
        }

        var apiKeyName: String = ""

        var apiKeyLocation: ApiKeyLocation = ApiKeyLocation.QUERY

        /**
        * Sets a validation function that will check given [ApiKeyCredential] instance and return [Principal],
        * or null if credential does not correspond to an authenticated principal
        */
        fun validate(body: suspend ApplicationCall.(ApiKeyCredential) -> Principal?) {
            authenticationFunction = body
        }
    }
}

fun Authentication.Configuration.apiKeyAuth(
    name: String? = null,
    configure: ApiKeyAuthenticationProvider.Configuration.() -> Unit
) {
    val configuration = ApiKeyAuthenticationProvider.Configuration(name).apply(configure)
    val provider = ApiKeyAuthenticationProvider(configuration)
    provider.install()
    register(provider)
}

fun ApplicationRequest.apiKeyAuthenticationCredentials(
    apiKeyName: String,
    apiKeyLocation: ApiKeyLocation
): ApiKeyCredential? {
    val value: String? = when (apiKeyLocation) {
        ApiKeyLocation.QUERY -> this.queryParameters[apiKeyName]
        ApiKeyLocation.HEADER -> this.headers[apiKeyName]
    }
    return when (value) {
        null -> null
        else -> ApiKeyCredential(value)
    }
}
