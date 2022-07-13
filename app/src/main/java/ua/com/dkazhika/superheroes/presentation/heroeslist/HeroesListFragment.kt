package ua.com.dkazhika.superheroes.presentation.heroeslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ua.com.dkazhika.superheroes.Navigator
import ua.com.dkazhika.superheroes.databinding.HeroesListFragmentBinding

@AndroidEntryPoint
class HeroesListFragment : Fragment() {

    private val viewModel: HeroesListViewModel by viewModels()
    private lateinit var adapter: SuperheroesAdapter
    private lateinit var binding: HeroesListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HeroesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SuperheroesAdapter(requireActivity() as Navigator)
        binding.recyclerViewContainer.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewContainer.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getHeroes().collectLatest { heroes ->
                adapter.submitData(heroes)
            }
        }
    }
}