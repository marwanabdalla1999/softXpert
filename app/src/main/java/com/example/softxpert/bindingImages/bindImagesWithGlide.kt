package com.example.softxpert.bindingImages

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.softxpert.R

//Bind Bitmap Images to  Recipes Items
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, image: String?) {
    if (image != null) {

        Glide.with(view.context).load(image).placeholder(R.drawable.pets_place_holder)
            .diskCacheStrategy(
                DiskCacheStrategy.ALL
            ).into(view)
    }


}


