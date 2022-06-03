package com.idea3d.open_bank_challengue.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idea3d.open_bank_challengue.R
import com.idea3d.open_bank_challengue.core.BaseViewHolder
import com.idea3d.open_bank_challengue.databinding.HerosRowBinding
import com.idea3d.open_bank_challengue.model.Hero

class MainAdapter(private val context: Context, private val heroList:List<Hero>,
                  private val itemClickListener:OnMovieClickListener):
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener{
        fun onHeroClick(hero:Hero)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            HerosRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

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

    inner class MainViewHolder(private val itemBinding:HerosRowBinding):
        BaseViewHolder<Hero>(itemBinding.root) {
        override fun bind(item: Hero) {
            val image = "${item.image!!.path}.${item.image.extension}"
            Glide.with(context)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.marvel_heros)
                .dontAnimate()
                .into(itemBinding.ivPortada)
            itemBinding.tvTitulo.text=item.name
            itemBinding.tvDesc.text=item.description
            itemView.setOnClickListener {itemClickListener.onHeroClick(item)}
        }
    }
}