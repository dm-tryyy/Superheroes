package ua.com.dkazhika.superheroes.presentation.heroeslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ua.com.dkazhika.superheroes.Navigator
import ua.com.dkazhika.superheroes.R
import ua.com.dkazhika.superheroes.databinding.HeroLayoutBinding

class SuperheroesAdapter(private val navigator: Navigator) :
    PagingDataAdapter<HeroUi, SuperheroesAdapter.SuperheroesViewHolder>(HeroesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroesViewHolder {
        val binding = HeroLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuperheroesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuperheroesViewHolder, position: Int) {
        val hero = getItem(position) ?: return
        with(holder.binding) {
            name.text = hero.name
            root.setOnClickListener {
                navigator.showDetails(hero.id)
            }
            Glide.with(root.context)
                .load(hero.imageUrl)
                .transform(CenterCrop(), RoundedCorners(24))
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(image)
        }
    }

    class SuperheroesViewHolder(val binding: HeroLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}

class HeroesDiffCallback : DiffUtil.ItemCallback<HeroUi>() {
    override fun areItemsTheSame(oldItem: HeroUi, newItem: HeroUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HeroUi, newItem: HeroUi): Boolean {
        return oldItem == newItem
    }
}
