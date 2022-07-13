package ua.com.dkazhika.superheroes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ua.com.dkazhika.superheroes.presentation.herodetails.HeroDetailsFragment
import ua.com.dkazhika.superheroes.presentation.heroeslist.HeroesListFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, HeroesListFragment())
                .commit()
        }
    }

    override fun showDetails(id: Int) {
        supportFragmentManager.beginTransaction()
            .addToBackStack("")
            .replace(R.id.fragmentContainer, HeroDetailsFragment.newInstance(id))
            .commit()
    }
}

interface Navigator {

    fun showDetails(id: Int)

}