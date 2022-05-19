package ua.com.dkazhika.superheroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import ua.com.dkazhika.superheroes.core.SuperheroesApp
import ua.com.dkazhika.superheroes.presentation.SuperheroesAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = (application as SuperheroesApp).mainViewModel

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = SuperheroesAdapter()
        recyclerView.adapter = adapter

        viewModel.observe(this) {
            adapter.update(it)
        }
        viewModel.fetchHeroes()
        //todo observe fail
    }
}