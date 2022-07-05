package ua.com.dkazhika.superheroes.presentation.heroeslist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ua.com.dkazhika.superheroes.Navigator
import ua.com.dkazhika.superheroes.SuperheroesApp
import ua.com.dkazhika.superheroes.databinding.HeroesListFragmentBinding
import ua.com.dkazhika.superheroes.domain.heroeslist.GetHeroesListUseCase
import ua.com.dkazhika.superheroes.domain.heroeslist.HeroesRepository

class HeroesListFragment : Fragment() {

    private lateinit var repository: HeroesRepository
    private val viewModel: HeroesListViewModel by viewModels {
        HeroesListViewModelFactory(
            GetHeroesListUseCase.Base(repository),
            HeroToHeroUiMapper.Base()
        )
    }
    private lateinit var adapter: SuperheroesAdapter
    private lateinit var binding: HeroesListFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        repository = (context.applicationContext as SuperheroesApp).heroesRepository
        Log.d("ddd", "onAttach ${this.hashCode()}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ddd", "onCreate ${this.hashCode()}")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("ddd", "onCreateView ${this.hashCode()}")

        binding = HeroesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ddd", "onViewCreated ${this.hashCode()}")

        adapter = SuperheroesAdapter(requireActivity() as Navigator)
        binding.recyclerViewContainer.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewContainer.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getHeroes().collectLatest { heroes ->
                adapter.submitData(heroes)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("ddd", "onStart ${this.hashCode()}")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ddd", "onResume ${this.hashCode()}")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ddd", "onPause ${this.hashCode()}")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ddd", "onStop ${this.hashCode()}")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("ddd", "onDestroyView ${this.hashCode()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ddd", "onDestroy ${this.hashCode()}")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("ddd", "onDetach ${this.hashCode()}")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("ddd", "onSaveInstanceState ${this.hashCode()}")

    }
}