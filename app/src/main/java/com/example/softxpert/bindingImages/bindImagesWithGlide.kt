package com.example.softxpert.bindingImages

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

//Bind Bitmap Images to  Recipes Items
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, image: String?) {
    if (image != null) {

            Glide.with(view.context).asBitmap().load(image).into(view)
    }



}


