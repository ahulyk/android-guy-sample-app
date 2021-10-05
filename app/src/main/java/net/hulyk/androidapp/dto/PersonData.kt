package net.hulyk.androidapp.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonData(
    @SerialName("name")
    val name: String,
    @SerialName("img")
    val img: String
)