package ua.com.dkazhika.superheroes.presentation.herodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import dagger.hilt.android.AndroidEntryPoint
import ua.com.dkazhika.superheroes.R
import ua.com.dkazhika.superheroes.databinding.FragmentHeroDetailsBinding
import ua.com.dkazhika.superheroes.presentation.viewModelCreator
import javax.inject.Inject
import kotlin.properties.Delegates.notNull

private const val HERO_ID = "heroId"

@AndroidEntryPoint
class HeroDetailsFragment : Fragment() {

    @Inject
    lateinit var factory: HeroDetailsViewModel.Factory
    private var heroId by notNull<Int>()
    private val viewModel: HeroDetailsViewModel by viewModelCreator {
        factory.create(heroId)
    }

    private lateinit var binding: FragmentHeroDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            heroId = it.getInt(HERO_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHeroDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

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

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            HeroDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(HERO_ID, id)
                }
            }
    }
}

fun hideAll(vararg view: View) {
    view.forEach {
        it.visibility = View.GONE
    }
}