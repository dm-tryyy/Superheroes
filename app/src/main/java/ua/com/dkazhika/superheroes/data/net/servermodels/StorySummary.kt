package ua.com.dkazhika.superheroes.data.net.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class StorySummary(
    @SerializedName("resourceURI")
    private val resourceURI: String,
    @SerializedName("name")
    private val name: String,
    @SerializedName("type")
    private val type: String
)