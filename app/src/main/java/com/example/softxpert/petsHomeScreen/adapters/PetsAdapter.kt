package com.example.softxpert.petsHomeScreen.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Pets
import com.example.softxpert.R
import com.example.softxpert.databinding.PetItemBinding


class PetsAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<PetsAdapter.ViewHolder>() {
     private var petsList: List<Pets> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(petsList: List<Pets>?) {
        if (petsList != null) {
            this.petsList = petsList
           notifyDataSetChanged()

        }

    }


    fun addData(petsList: List<Pets>?) {
        if (petsList != null) {
            val newPetsList = mutableListOf<Pets>()
            newPetsList.addAll(this.petsList)
            newPetsList.addAll(petsList)
            val oldSize=this.petsList.size
            this.petsList = newPetsList

            notifyItemRangeChanged(oldSize, this.petsList.size)

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: PetItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.pet_item, parent, false
        )
        return ViewHolder(binding, onItemClickListener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.image = petsList[position].smallPhoto
        holder.binding.name = petsList[position].name
        holder.binding.type = petsList[position].type
        holder.binding.gender = petsList[position].gender


    }

    override fun getItemCount(): Int {
        return petsList.size
    }
     fun getItemsList(): List<Pets> {
        return petsList
    }

    class ViewHolder(
        val binding: PetItemBinding,

        private val onItemClickListener: OnItemClickListener,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener.onItemClicked(adapterPosition)
            }
        }
    }


}