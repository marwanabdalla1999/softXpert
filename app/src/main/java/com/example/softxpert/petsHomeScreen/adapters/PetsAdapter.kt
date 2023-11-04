package com.example.softxpert.petsHomeScreen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Pets
import com.example.softxpert.R
import com.example.softxpert.databinding.PetItemBinding


class PetsAdapter : RecyclerView.Adapter<PetsAdapter.ViewHolder>() {
    private var petsList: List<Pets> = ArrayList()

    fun setData(petsList: List<Pets>?) {
        if (petsList != null) {
            this.petsList = petsList
            notifyItemRangeChanged(0, petsList.size)

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: PetItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.pet_item, parent, false
        )
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.image=petsList[position].smallPhoto
        holder.binding.name=petsList[position].name
        holder.binding.type=petsList[position].type
        holder.binding.gender=petsList[position].gender


    }

    override fun getItemCount(): Int {
        return petsList.size
    }

    class ViewHolder(val binding: PetItemBinding) : RecyclerView.ViewHolder(binding.root)


}