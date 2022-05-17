package ua.com.dkazhika.superheroes.data.net.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SeriesSummary(
    @SerializedName("resourceURI")
    private val resourceURI: String,
    @SerializedName("name")
    private val name: String
)