package ua.com.dkazhika.superheroes.data.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ComicList(
    @SerializedName("items")
    val items: List<ComicSummary>,
    @SerializedName("returned")
    val returned: Int
) {
    fun toComicsList(): List<String> = items.map { it.name }
}