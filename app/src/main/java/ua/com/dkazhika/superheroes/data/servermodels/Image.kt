package ua.com.dkazhika.superheroes.data.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.heroeslist.cloud.ImageMapper

@Keep
data class Image(
    @SerializedName("path")
    private val path: String,
    @SerializedName("extension")
    private val extension: String
) : Abstract.Object<String, ImageMapper> {
    override fun map(mapper: ImageMapper): String {
        return mapper.map(path, extension)
    }
}