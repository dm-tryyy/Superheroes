package ua.com.dkazhika.superheroes.data.net.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ua.com.dkazhika.superheroes.core.Abstract

@Keep
class CharacterDataWrapper(
    @SerializedName("code")
    private val code: Int,
    @SerializedName("status")
    private val status: String,
    @SerializedName("copyright")
    private val copyright: String,
    @SerializedName("attributionText")
    private val attributionText: String,
    @SerializedName("attributionHTML")
    private val attributionHTML: String,
    @SerializedName("data")
    val data: CharactersDataContainer,
    @SerializedName("etag")
    private val etag: String
)