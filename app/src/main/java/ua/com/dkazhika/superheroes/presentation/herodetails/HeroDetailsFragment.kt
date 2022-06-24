package ua.com.dkazhika.superheroes.presentation.herodetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ua.com.dkazhika.superheroes.R
import ua.com.dkazhika.superheroes.core.SuperheroesApp
import ua.com.dkazhika.superheroes.databinding.FragmentHeroDetailsBinding
import kotlin.properties.Delegates.notNull

private const val HERO_ID = "heroId"

class HeroDetailsFragment : Fragment() {

    private var heroId by notNull<Int>()
    private lateinit var viewModel: HeroDetailsViewModel
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
        // Inflate the layout for this fragment
        binding = FragmentHeroDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = (requireActivity().application as SuperheroesApp).heroDetailsViewModel
        if (!viewModel.isInitialized) {
            viewModel.initialize(heroId)
        }
        viewModel.observe(this) {
            hideAll()
            when (it) {
                is HeroDetailsUi.Progress -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is HeroDetailsUi.Base -> {
                    with(binding) {
                        heroDetailsContainer.visibility = View.VISIBLE
                        it.map(object : HeroDetailsUi.AdapterMapper {
                            override fun map(bundle: HeroDetailsUiMappingBundle) {
                                heroName.text = bundle.name
                                description.text = bundle.description
                                Glide.with(root.context)
                                    .load(bundle.imageUrl)
                                    .transform(CenterCrop(), RoundedCorners(24))
                                    .placeholder(R.drawable.ic_launcher_foreground)
                                    .error(R.drawable.ic_launcher_background)
                                    .into(image)
                                val comics = bundle.comicsNames
                                comicsList.adapter =
                                    ArrayAdapter(root.context, R.layout.comics_name, comics)
                            }
                        })
                    }
                }
                is HeroDetailsUi.Fail -> {
                    binding.failContainer.visibility = View.VISIBLE
                    it.map(object : HeroDetailsUi.AdapterMapper {
                        override fun map(bundle: HeroDetailsUiMappingBundle) {
                            binding.messageTextView.text = bundle.message
                        }
                    })
                    binding.tryAgainButton.setOnClickListener {
                        viewModel.fetchHeroes(heroId)
                    }
                }
            }
        }
    }

    private fun hideAll() {
        with(binding) {
            heroDetailsContainer.visibility = View.GONE
            progressBar.visibility = View.GONE
            failContainer.visibility = View.GONE
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