package ua.com.dkazhika.superheroes.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory<T : ViewModel>(
    private val viewModelCreator: () -> T
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelCreator() as T
    }
}

inline fun <reified T : ViewModel> Fragment.viewModelCreator(noinline  creator: () -> T) : Lazy<T> {
    return viewModels { ViewModelFactory(creator) }
}