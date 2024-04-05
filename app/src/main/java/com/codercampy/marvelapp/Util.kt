package com.codercampy.marvelapp

import android.content.Context
import android.widget.Toast
import com.bumptech.glide.Glide
import com.codercampy.marvelapp.adapter.SpecificAdapter
import com.codercampy.marvelapp.model.ItemModel
import java.security.MessageDigest

const val PUBLIC_KEY = "b80140659d66914120561b29585385cd"
const val PRIVATE_KEY = "42e3d5d9dfee24ebc2dadaadca46d91cda3ccf66"

fun Context.showShortToast(msg: String?) {
    Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show()
}

@OptIn(ExperimentalStdlibApi::class)
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(this.toByteArray())
    return digest.toHexString()
}
