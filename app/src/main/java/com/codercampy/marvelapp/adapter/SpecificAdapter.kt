package com.codercampy.marvelapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codercampy.marvelapp.databinding.ItemViewSpecificBinding
import com.codercampy.marvelapp.model.ItemModel
import com.codercampy.marvelapp.model.SpecificCharacter

class SpecificAdapter :RecyclerView.Adapter<ViewHolder2> () {

    private val specificChar = ArrayList<ItemModel>()


    fun setSpecific(items: List<ItemModel>) {
        val start = specificChar.size
        specificChar.addAll(items)
        notifyItemRangeInserted(start, specificChar.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        val lf2 = LayoutInflater.from(parent.context)
        return ViewHolder2(ItemViewSpecificBinding.inflate(lf2, parent, false))
    }


    override fun getItemCount(): Int {
        return specificChar.size
    }



    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
        holder.bindData2(specificChar[position])

    }


}

class ViewHolder2(val binding: ItemViewSpecificBinding) : RecyclerView.ViewHolder(binding.root){

    fun bindData2(specificCharacter: ItemModel) {
        binding.tvTitleRv2.text = specificCharacter.name
        Glide.with(binding.ivImageRv2).load(specificCharacter.thumbnail.getUrl()).into(binding.ivImageRv2)
    }
}
