package com.codercampy.marvelapp.adapter

import com.codercampy.marvelapp.model.ItemModel

fun interface SpecificAdapterListener {

    fun onComicClicked(itemModel: ItemModel)
}