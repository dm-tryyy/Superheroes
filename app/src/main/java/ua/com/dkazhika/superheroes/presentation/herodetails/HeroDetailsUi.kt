package ua.com.dkazhika.superheroes.presentation.herodetails

data class HeroDetailsUi(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val comicsNames: List<String>
)
