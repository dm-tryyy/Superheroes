package ua.com.dkazhika.superheroes.presentation.herodetails

import ua.com.dkazhika.superheroes.R
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.domain.ErrorType
import ua.com.dkazhika.superheroes.presentation.ResourceProvider

sealed class HeroDetailsUiContainer : Abstract.Object<Unit, HeroDetailsCommunication> {

    class Success(
        private val heroDetails: HeroDetailsUi
    ) : HeroDetailsUiContainer() {
        override fun map(mapper: HeroDetailsCommunication) {
            mapper.map(heroDetails)
        }
    }

    class Fail(
        private val error: ErrorType,
        private val resourceProvider: ResourceProvider
    ) : HeroDetailsUiContainer() {
        override fun map(mapper: HeroDetailsCommunication) {
            val messageId = when(error) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
            val message = resourceProvider.getString(messageId)
            mapper.map(HeroDetailsUi.Fail(message))
        }

    }
}