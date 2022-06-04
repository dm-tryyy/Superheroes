package ua.com.dkazhika.superheroes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ua.com.dkazhika.superheroes.R
import ua.com.dkazhika.superheroes.core.Hero

class SuperheroesAdapter : RecyclerView.Adapter<SuperheroesAdapter.SuperheroesViewHolder>() {

    private val heroes = ArrayList<Hero>()

    fun update(newList: List<Hero>) {
        heroes.clear()
        heroes.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hero_layout, parent, false)
        //todo progress and fail
        return SuperheroesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuperheroesViewHolder, position: Int) {
        holder.bind(heroes[position])
    }

    override fun getItemCount() = heroes.size

    inner class SuperheroesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(hero: Hero) {
            itemView.findViewById<TextView>(R.id.name).text = hero.name
            val imageUrl = hero.imageUrl
            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(itemView.findViewById(R.id.image))
        }
    }
}