package ua.com.dkazhika.superheroes.presentation.heroeslist

import ua.com.dkazhika.superheroes.core.Abstract

sealed class HeroUi : Abstract.Object<Unit, HeroUi.AdapterMapper> {

    override fun map(mapper: AdapterMapper) = Unit

    object Progress : HeroUi()

    class Base(
        val id: Int,
        private val name: String,
        private val imageUrl: String
    ) : HeroUi() {
        override fun map(mapper: AdapterMapper) {
            mapper.map(HeroUiMappingBundle(id = id, name = name, imageUrl = imageUrl))
        }
    }

    class Fail(
        private val message: String
    ) : HeroUi() {
        override fun map(mapper: AdapterMapper) {
            mapper.map(HeroUiMappingBundle(message = message))
        }
    }

    interface AdapterMapper : Abstract.Mapper {
        fun map(bundle: HeroUiMappingBundle)
    }
}