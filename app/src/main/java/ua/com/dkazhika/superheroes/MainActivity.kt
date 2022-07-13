package ua.com.dkazhika.superheroes

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import ua.com.dkazhika.superheroes.databinding.ActivityMainBinding
import ua.com.dkazhika.superheroes.presentation.herodetails.HeroDetailsFragment.Companion.HERO_ID

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        navController = navHost.navController

        NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

    override fun showDetails(id: Int) {
        navController.navigate(
            R.id.action_heroesListFragment_to_heroDetailsFragment,
            Bundle().apply {
                putInt(HERO_ID, id)
            })
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

interface Navigator {

    fun showDetails(id: Int)

}