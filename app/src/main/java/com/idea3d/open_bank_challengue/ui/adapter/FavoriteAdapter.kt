package com.idea3d.open_bank_challengue.ui.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idea3d.open_bank_challengue.R
import com.idea3d.open_bank_challengue.core.BaseViewHolder
import com.idea3d.open_bank_challengue.databinding.HeroesRowBinding
import com.idea3d.open_bank_challengue.model.HeroEntity

class FavoriteAdapter(private val context: Context, private val heroList:List<HeroEntity>,
                      private val itemClickListener:OnMovieClickListener):
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener{
        fun onHeroClick(hero:HeroEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            HeroesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MainViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder->holder.bind(heroList[position])
        }
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    inner class MainViewHolder(private val itemBinding:HeroesRowBinding):
        BaseViewHolder<HeroEntity>(itemBinding.root) {
        override fun bind(item: HeroEntity) {
            itemBinding.buttonFav.visibility = View.GONE
            val image = "${item.path}.${item.extension}"
            Glide.with(context)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.marvel_hero_red)
                .dontAnimate()
                .into(itemBinding.ivPortada)
            itemBinding.tvTitulo.text=item.name
            itemBinding.tvDesc.text=item.description
            itemView.setOnClickListener {itemClickListener.onHeroClick(item)}
        }
    }
}