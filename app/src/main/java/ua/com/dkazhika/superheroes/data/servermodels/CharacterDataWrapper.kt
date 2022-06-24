package ua.com.dkazhika.superheroes.data.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CharacterDataWrapper(
    @SerializedName("data")
    val data: CharactersDataContainer
)