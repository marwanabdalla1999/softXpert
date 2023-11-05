package com.example.softxpert.bindingImages

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.softxpert.R

//Bind Bitmap Images to  Recipes Items
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, image: String?) {
    view.setImageResource(R.drawable.placeholder)
    if (image != null) {

        Glide.with(view.context).load(image)
            .override(500, 500)
            .placeholder(R.drawable.placeholder)
            .into(view)
    }


}


