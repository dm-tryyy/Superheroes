package ua.com.dkazhika.superheroes.presentation

import ua.com.dkazhika.superheroes.R
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.domain.ErrorType

sealed class HeroesUi : Abstract.Object<Unit, HeroesCommunication> {

    class Success(
        private val heroes: List<HeroUi>
    ) : HeroesUi() {
        override fun map(mapper: HeroesCommunication) {
            mapper.map(heroes)
        }
    }

    class Fail(
        private val error: ErrorType,
        private val resourceProvider: ResourceProvider
    ) : HeroesUi() {
        override fun map(mapper: HeroesCommunication) {
            val messageId = when(error) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
            val message = resourceProvider.getString(messageId)
            mapper.map(listOf(HeroUi.Fail(message)))
        }

    }
}