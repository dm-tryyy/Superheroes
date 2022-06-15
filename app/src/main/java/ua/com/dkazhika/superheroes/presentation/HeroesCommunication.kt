package ua.com.dkazhika.superheroes.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero

interface HeroesCommunication : Abstract.Mapper{
    fun map(list: List<HeroUi>)

    fun observe(owner: LifecycleOwner, observer: Observer<List<HeroUi>>)

    class Base : HeroesCommunication {
        private val listLiveData = MutableLiveData<List<HeroUi>>()

        override fun map(list: List<HeroUi>) {
            listLiveData.value = list
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<HeroUi>>) {
            listLiveData.observe(owner, observer)
        }

    }
}