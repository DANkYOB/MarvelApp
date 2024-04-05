package com.codercampy.marvelapp.adapter

import com.codercampy.marvelapp.model.ItemModel

fun interface CharacterAdapterListener {

    fun onCharacterClicked(itemModel: ItemModel)

}