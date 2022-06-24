package ua.com.dkazhika.superheroes.data.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.herodetails.cloud.ComicsListMapper

@Keep
data class ComicList(
    @SerializedName("items")
    private val items: List<ComicSummary>,
    @SerializedName("returned")
    private val returned: Int
) : Abstract.Object<List<String>, ComicsListMapper> {
    override fun map(mapper: ComicsListMapper): List<String> {
        return mapper.map(items)
    }
}