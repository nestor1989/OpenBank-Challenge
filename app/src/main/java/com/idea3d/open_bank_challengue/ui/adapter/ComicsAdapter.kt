package com.idea3d.open_bank_challengue.ui.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idea3d.open_bank_challengue.R
import com.idea3d.open_bank_challengue.core.BaseViewHolder
import com.idea3d.open_bank_challengue.databinding.ComicRowBinding
import com.idea3d.open_bank_challengue.model.ComicDetails
import com.idea3d.open_bank_challengue.model.ItemsComic

class ComicsAdapter (private val context: Context, private val items:List<ComicDetails>,
                     private val itemClickListener:OnComicClickListener):
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnComicClickListener{
        fun onComicClick(comicDetails: ComicDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            ComicRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MainViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder->holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MainViewHolder(private val itemBinding: ComicRowBinding):
        BaseViewHolder<ComicDetails>(itemBinding.root) {
        override fun bind(item: ComicDetails) {
            val image = "${item.image.path}.${item.image.extension}"
            Glide.with(context)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.marvel_hero_red)
                .dontAnimate()
                .into(itemBinding.ivPortada)
            itemBinding.tvTitulo.text=item.title
           // itemView.setOnClickListener {itemClickListener.onComicClick(item)}
        }
    }


}