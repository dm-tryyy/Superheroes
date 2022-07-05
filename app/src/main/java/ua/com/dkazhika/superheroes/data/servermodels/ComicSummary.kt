package ua.com.dkazhika.superheroes.data.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class ComicSummary(
    @SerializedName("name")
    val name: String
)