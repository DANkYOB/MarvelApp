package com.codercampy.marvelapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codercampy.marvelapp.databinding.ItemCharacterBinding
import com.codercampy.marvelapp.model.Character

class CharactersAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val characters = ArrayList<Character>()

    fun setCharacters(items: List<Character>) {
        val start = characters.size
        characters.addAll(items)
        notifyItemRangeInserted(start, characters.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lf = LayoutInflater.from(parent.context)
        return ViewHolder(ItemCharacterBinding.inflate(lf, parent, false))
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(characters[position])
    }

}

class ViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(character: Character) {
        binding.tvTitle.text = character.name
        if (character.description.isEmpty()) {
            binding.tvDescription.visibility = View.GONE
        } else {
            binding.tvDescription.visibility = View.VISIBLE
            binding.tvDescription.text = character.description
        }
        Glide.with(binding.ivImage).load(character.thumbnail.getUrl()).into(binding.ivImage)
    }

}