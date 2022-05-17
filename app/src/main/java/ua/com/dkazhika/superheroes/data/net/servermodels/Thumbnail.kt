package ua.com.dkazhika.superheroes.data.net.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.net.ThumbnailMapper

@Keep
data class Thumbnail(
    @SerializedName("path")
    private val path: String,
    @SerializedName("extension")
    private val extension: String
) : Abstract.Object<String, ThumbnailMapper>() {
    override fun map(mapper: ThumbnailMapper): String {
        return mapper.map(path, extension)
    }
}