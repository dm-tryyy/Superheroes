package ua.com.dkazhika.superheroes.presentation

import ua.com.dkazhika.superheroes.R
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.domain.ErrorType

sealed class HeroesUi : Abstract.Object<Unit, Abstract.Mapper.Empty>() {

    class Success(
        private val list: List<Hero>,
        private val communication: HeroesCommunication
    ) : HeroesUi() {
        override fun map(mapper: Abstract.Mapper.Empty) {
            communication.show(list)
        }
    }

    class Fail(
        private val error: ErrorType,
        private val communication: HeroesCommunication,
        private val resourceProvider: ResourceProvider
        ) : HeroesUi() {
        override fun map(mapper: Abstract.Mapper.Empty) {
            val messageId = when(error) { //todo move to other class
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
            communication.show(resourceProvider.getString(messageId))
        }

    }
}