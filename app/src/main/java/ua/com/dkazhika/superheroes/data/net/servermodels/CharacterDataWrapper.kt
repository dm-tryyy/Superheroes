package ua.com.dkazhika.superheroes.data.net.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ua.com.dkazhika.superheroes.core.Abstract

@Keep
data class CharacterDataWrapper(
    @SerializedName("data")
    val data: CharactersDataContainer
)