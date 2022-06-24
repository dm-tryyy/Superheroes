package ua.com.dkazhika.superheroes.data.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.herodetails.cloud.ComicSummaryMapper

@Keep
class ComicSummary(
    @SerializedName("name")
    private val name: String
) : Abstract.Object<String, ComicSummaryMapper> {
    override fun map(mapper: ComicSummaryMapper): String {
        return mapper.map(name)
    }
}