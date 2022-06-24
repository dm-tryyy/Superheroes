package ua.com.dkazhika.superheroes.presentation.herodetails

class HeroDetailsUiMappingBundle(
    val id: Int = -1,
    val name: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val comicsNames: List<String> = emptyList(),
    val message: String = ""
)