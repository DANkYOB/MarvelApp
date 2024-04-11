package com.codercampy.marvelapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codercampy.marvelapp.databinding.ItemCharacterBinding
import com.codercampy.marvelapp.model.ItemModel

class CharactersAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val itemModels = ArrayList<ItemModel>()
    private var listener: CharacterAdapterListener? = null

    fun setCharacters(items: List<ItemModel>) {
        val start = itemModels.size
        itemModels.addAll(items)
        notifyItemRangeInserted(start, itemModels.size)
    }

    fun setListener(listener: CharacterAdapterListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lf = LayoutInflater.from(parent.context)
        return ViewHolder(ItemCharacterBinding.inflate(lf, parent, false))
    }

    override fun getItemCount(): Int {
        return itemModels.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(itemModels[position])
        holder.binding.clRoot.setOnClickListener {
            val ch = itemModels[position]
            listener?.onCharacterClicked(ch)
        }
    }

}

class ViewHolder( val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(itemModel: ItemModel) {
        binding.tvTitle.text = itemModel.name
        if (itemModel.description.isEmpty()) {
            binding.tvDescription.visibility = View.GONE
        } else {
            binding.tvDescription.visibility = View.VISIBLE
            binding.tvDescription.text = itemModel.description
        }
        Glide.with(binding.ivImage).load(itemModel.thumbnail.getUrl()).into(binding.ivImage)
    }

}