package ua.com.dkazhika.superheroes.presentation.herodetails

import ua.com.dkazhika.superheroes.core.Abstract

sealed class HeroDetailsUi : Abstract.Object<Unit, HeroDetailsUi.AdapterMapper> {

    override fun map(mapper: AdapterMapper) = Unit

    object Progress : HeroDetailsUi()

    class Base(
        private val id: Int,
        private val name: String,
        private val description : String,
        private val imageUrl: String,
        private val comicsNames: List<String>
    ) : HeroDetailsUi() {
        override fun map(mapper: AdapterMapper) {
            mapper.map(HeroDetailsUiMappingBundle(id, name, imageUrl, description, comicsNames))
        }
    }

    class Fail(
        private val message: String
    ) : HeroDetailsUi() {
        override fun map(mapper: AdapterMapper) {
            mapper.map(HeroDetailsUiMappingBundle(message = message))
        }
    }

    interface AdapterMapper : Abstract.Mapper {
        fun map(bundle: HeroDetailsUiMappingBundle)
    }
}
