package ua.com.dkazhika.superheroes.data.net.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Events(
    @SerializedName("available")
    private val available: Int,
    @SerializedName("collectionURI")
    private val collectionURI: String,
    @SerializedName("items")
    private val items: List<EventSummary>,
    @SerializedName("returned")
    private val returned: Int
)