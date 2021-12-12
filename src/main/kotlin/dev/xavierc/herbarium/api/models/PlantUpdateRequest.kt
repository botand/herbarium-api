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
 * Body send to the API to update a plant details
 * @param typeId
 * @param overrideMoistureGoal
 * @param overrideLightExposureMinDuration
 * @param movedPosition
 */
data class PlantUpdateRequest(
    @SerializedName(value="type_id")
    val typeId: Int?,
    @SerializedName(value="override_moisture_goal")
    val overrideMoistureGoal: Double?,
    @SerializedName(value="override_light_exposure_min_duration")
    val overrideLightExposureMinDuration: Double?,
    @SerializedName(value="moved_position")
    val movedPosition: Int?
) 

