package ua.com.dkazhika.superheroes.presentation.heroeslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ua.com.dkazhika.superheroes.Navigator
import ua.com.dkazhika.superheroes.R
import ua.com.dkazhika.superheroes.core.SuperheroesApp

class HeroesListFragment : Fragment() {

    private lateinit var viewModel: HeroesListViewModel
    lateinit var adapter: SuperheroesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.heroes_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        viewModel = (requireActivity().application as SuperheroesApp).heroesListViewModel
        adapter = SuperheroesAdapter(
            { viewModel.fetchHeroes() },
            (requireActivity() as Navigator))
        recyclerView.adapter = adapter

        viewModel.observe(this) {
            adapter.update(it)
        }
        viewModel.fetchHeroes()
    }
}