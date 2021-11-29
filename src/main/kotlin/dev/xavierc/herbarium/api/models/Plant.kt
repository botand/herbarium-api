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
import org.joda.time.DateTime

/**
 * Representation of a plant in a greenhouse.
 * @param uuid Universal unique identifier
 * @param position On which tile of the greenhouse the plant is.
 * @param type 
 * @param plantedAt When the plant was planted.
 * @param moistureLastReading 
 * @param lightLastReading 
 * @param lastUuid Universal unique identifier
 * @param valveStatus 
 * @param lightStripStatus 
 */
data class Plant(
    /* Universal unique identifier */
    val uuid: java.util.UUID,
    /* On which tile of the greenhouse the plant is. */
    val position: kotlin.Int,
    val type: PlantType,
    /* When the plant was planted. */
    @SerializedName(value = "planted_at")
    val plantedAt: DateTime,
    @SerializedName(value = "moisture_last_reading")
    val moistureLastReading: Double?,
    @SerializedName(value = "light_last_reading")
    val lightLastReading: Double?,
    /* Universal unique identifier */
    @SerializedName(value = "last_uuid")
    val lastUuid: java.util.UUID? = null,
    @SerializedName(value = "valve_status")
    val valveStatus: Boolean? = null,
    @SerializedName(value = "light_strip_status")
    val lightStripStatus: Boolean? = null,
    /* Indicate if the plant was removed from the greenhouse */
    val removed: Boolean = false
) 

