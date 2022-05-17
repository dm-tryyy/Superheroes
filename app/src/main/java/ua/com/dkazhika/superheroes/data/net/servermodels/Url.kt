package ua.com.dkazhika.superheroes.data.net.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Url(
    @SerializedName("type")
    private val type: String,
    @SerializedName("url")
    private val url: String
)