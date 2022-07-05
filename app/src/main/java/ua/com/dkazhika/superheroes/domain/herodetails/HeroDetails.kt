package ua.com.dkazhika.superheroes.domain.herodetails

data class HeroDetails(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val comicsNames: List<String>
)