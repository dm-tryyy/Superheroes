package ua.com.dkazhika.superheroes.presentation.herodetails

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import dagger.hilt.android.AndroidEntryPoint
import ua.com.dkazhika.superheroes.R
import ua.com.dkazhika.superheroes.SuperheroesApp
import ua.com.dkazhika.superheroes.databinding.FragmentHeroDetailsBinding
import ua.com.dkazhika.superheroes.domain.herodetails.GetHeroDetailsUseCase
import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetailsRepository
import ua.com.dkazhika.superheroes.presentation.viewModelCreator
import javax.inject.Inject
import kotlin.properties.Delegates.notNull

@AndroidEntryPoint
class HeroDetailsFragment : Fragment() {

    @Inject
    lateinit var factory: HeroDetailsViewModel.Factory
    private var heroId by notNull<Int>()
    private val viewModel: HeroDetailsViewModel by viewModelCreator {
        factory.create(heroId)
    }

    private lateinit var binding: FragmentHeroDetailsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("ddd", "onAttach ${this.hashCode()}")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ddd", "onCreate ${this.hashCode()}")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("ddd", "onCreateView ${this.hashCode()}")

        binding = FragmentHeroDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("ddd", "onViewCreated ${this.hashCode()}")

        heroId = arguments?.getInt(HERO_ID) ?: -1

        viewModel.state.observe(viewLifecycleOwner) {
            with(binding) {
                hideAll(heroDetailsContainer, progressBar, failContainer)
            }
            when (it) {
                is HeroDetailsUiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is HeroDetailsUiState.Success -> {
                    val heroDetailsUi = it.heroDetails
                    with(binding) {
                        heroDetailsContainer.visibility = View.VISIBLE
                        heroName.text = heroDetailsUi.name
                        description.text = heroDetailsUi.description
                        Glide.with(root.context)
                            .load(heroDetailsUi.imageUrl)
                            .transform(CenterCrop(), RoundedCorners(24))
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_background)
                            .into(image)
                        val comics = heroDetailsUi.comicsNames
                        comicsList.adapter =
                            ArrayAdapter(root.context, R.layout.comics_name, comics)
                    }
                }
                is HeroDetailsUiState.Fail -> {
                    with(binding) {
                        failContainer.visibility = View.VISIBLE
                        messageTextView.text = it.errorMessage
                        tryAgainButton.setOnClickListener {
                            viewModel.fetchHeroDetails(heroId)
                        }
                    }
                }
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

    companion object {
        const val HERO_ID = "heroId"
    }
}

fun hideAll(vararg view: View) {
    view.forEach {
        it.visibility = View.GONE
    }
}