package ua.com.dkazhika.superheroes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ua.com.dkazhika.superheroes.R
import ua.com.dkazhika.superheroes.core.Hero

class SuperheroesAdapter(private val retry: () -> Unit) :
    RecyclerView.Adapter<SuperheroesAdapter.SuperheroesViewHolder>() {

    private val heroes = ArrayList<HeroUi>()

    fun update(newList: List<HeroUi>) {
        heroes.clear()
        heroes.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (heroes[position]) {
            is HeroUi.Base -> 0
            is HeroUi.Fail -> 1
            is HeroUi.Progress -> 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> SuperheroesViewHolder.Base(R.layout.hero_layout.makeView(parent))
        1 -> SuperheroesViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
        else -> SuperheroesViewHolder.FullscreenProgress(
            R.layout.progress_fullscreen.makeView(
                parent
            )
        )
    }

    override fun onBindViewHolder(holder: SuperheroesViewHolder, position: Int) {
        holder.bind(heroes[position])
    }

    override fun getItemCount() = heroes.size

    abstract class SuperheroesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        open fun bind(hero: HeroUi) = Unit

        class FullscreenProgress(view: View) : SuperheroesViewHolder(view)

        class Base(view: View) : SuperheroesViewHolder(view) {
            override fun bind(hero: HeroUi) {
                hero.map(object : HeroUi.AdapterMapper {
                    override fun map(bundle: HeroUiMappingBundle) {
                        itemView.findViewById<TextView>(R.id.name).text = bundle.name
                        Glide.with(itemView.context)
                            .load(bundle.imageUrl)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_background)
                            .centerCrop()
                            .into(itemView.findViewById(R.id.image))
                    }
                })
            }
        }

        class Fail(view: View, private val retry: () -> Unit) : SuperheroesViewHolder(view) {
            private val message = itemView.findViewById<TextView>(R.id.messageTextView)
            private val button = itemView.findViewById<Button>(R.id.tryAgainButton)
            override fun bind(hero: HeroUi) {
                hero.map(object : HeroUi.AdapterMapper {
                    override fun map(bundle: HeroUiMappingBundle) {
                        message.text = bundle.message
                    }
                })
                button.setOnClickListener {
                    retry()
                }
            }
        }
    }

    private fun Int.makeView(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(this, parent, false)
    }
}