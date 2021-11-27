/**
* Herbarium API
* No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
*
* The version of the OpenAPI document: 1.0.0
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package dev.xavierc.herbarium.api.apis

import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import dev.xavierc.herbarium.api.Paths
import io.ktor.locations.*
import io.ktor.routing.*
import dev.xavierc.herbarium.api.infrastructure.ApiPrincipal

@KtorExperimentalLocationsAPI
fun Route.PlantApi() {
    val gson = Gson()
    val empty = mutableMapOf<String, Any?>()

    authenticate("apiKey") {
    delete<Paths.deletePlant> {
        val principal = call.authentication.principal<ApiPrincipal>()!!
        
        val exampleContentType = "application/json"
            val exampleContentString = """{
              "uuid" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }"""
            
            when (exampleContentType) {
                "application/json" -> call.respond(gson.fromJson(exampleContentString, empty::class.java))
                "application/xml" -> call.respondText(exampleContentString, ContentType.Text.Xml)
                else -> call.respondText(exampleContentString)
            }
    }
    }

    authenticate("oauth") {
    post<Paths.postActuatorState> {
        val principal = call.authentication.principal<OAuthAccessTokenResponse>()!!
        
        call.respond(HttpStatusCode.NotImplemented)
    }
    }

    authenticate("oauth") {
    post<Paths.postUpdatePlant> {
        val principal = call.authentication.principal<OAuthAccessTokenResponse>()!!
        
        val exampleContentType = "application/json"
            val exampleContentString = """{
              "uuid" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }"""
            
            when (exampleContentType) {
                "application/json" -> call.respond(gson.fromJson(exampleContentString, empty::class.java))
                "application/xml" -> call.respondText(exampleContentString, ContentType.Text.Xml)
                else -> call.respondText(exampleContentString)
            }
    }
    }

    authenticate("apiKey") {
    put<Paths.putPlant> {
        val principal = call.authentication.principal<ApiPrincipal>()!!
        
        val exampleContentType = "application/json"
            val exampleContentString = """{
              "uuid" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }"""
            
            when (exampleContentType) {
                "application/json" -> call.respond(gson.fromJson(exampleContentString, empty::class.java))
                "application/xml" -> call.respondText(exampleContentString, ContentType.Text.Xml)
                else -> call.respondText(exampleContentString)
            }
    }
    }

}
