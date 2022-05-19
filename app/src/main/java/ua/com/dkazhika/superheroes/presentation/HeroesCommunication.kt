package ua.com.dkazhika.superheroes.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import ua.com.dkazhika.superheroes.core.Hero

interface HeroesCommunication {
    fun show(list: List<Hero>)
    fun show(errorMessage: String)

    fun observeSuccess(owner: LifecycleOwner, observer: Observer<List<Hero>>)
    fun observeFail(owner: LifecycleOwner, observer: Observer<String>)

    class Base : HeroesCommunication {
        private val successLiveData = MutableLiveData<List<Hero>>()
        private val failLiveData = MutableLiveData<String>()

        override fun show(list: List<Hero>) {
            successLiveData.value = list
        }

        override fun show(errorMessage: String) {
            failLiveData.value = errorMessage
        }

        override fun observeSuccess(owner: LifecycleOwner, observer: Observer<List<Hero>>) {
            successLiveData.observe(owner, observer)
        }

        override fun observeFail(owner: LifecycleOwner, observer: Observer<String>) {
            failLiveData.observe(owner, observer)
        }

    }
}