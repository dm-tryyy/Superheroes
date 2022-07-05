package ua.com.dkazhika.superheroes.data.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Image(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String
) {
    fun toImageUrl() = "$path.$extension"
}