package ua.com.dkazhika.superheroes.data.net.servermodels
import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName


@Keep
data class CharactersDataResponse(
    @SerializedName("code")
    private val code: Int,
    @SerializedName("status")
    private val status: String,
    @SerializedName("data")
    private val data: CharactersDataContainer
)