package com.idea3d.open_bank_challengue.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idea3d.open_bank_challengue.core.BaseViewHolder
import com.idea3d.open_bank_challengue.databinding.LetterRowBinding


class AbcAdapter(private val context: Context, private val abc:List<String>,
                 private val itemClickListener:OnLetterClickListener):
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnLetterClickListener{
        fun onAbcClick(letter: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            LetterRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MainViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder->holder.bind(abc[position])
        }
    }

    override fun getItemCount(): Int {
        return abc.size
    }

    inner class MainViewHolder(private val itemBinding: LetterRowBinding):
        BaseViewHolder<String>(itemBinding.root) {
        override fun bind(item: String) {
            itemBinding.tvLetter.text=item
            itemBinding.tvLetter.setOnClickListener {itemClickListener.onAbcClick(item)}
        }
    }
}