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
package dev.xavierc.herbarium.api.models

import com.google.gson.annotations.SerializedName


/**
 *
 * @param greenhouseName 
 */
data class PutGreenhouseRequest(
    @SerializedName(value = "greenhouse_name")
    val greenhouseName: String
) 

