package ua.com.dkazhika.superheroes.presentation.herodetails

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import ua.com.dkazhika.superheroes.core.Abstract

interface HeroDetailsCommunication : Abstract.Mapper{
    fun map(heroDetails: HeroDetailsUi)

    fun observe(owner: LifecycleOwner, observer: Observer<HeroDetailsUi>)

    class Base : HeroDetailsCommunication {
        private val heroDetailsLiveData = MutableLiveData<HeroDetailsUi>()

        override fun map(heroDetails: HeroDetailsUi) {
            heroDetailsLiveData.value = heroDetails
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<HeroDetailsUi>) {
            heroDetailsLiveData.observe(owner, observer)
        }

    }
}
