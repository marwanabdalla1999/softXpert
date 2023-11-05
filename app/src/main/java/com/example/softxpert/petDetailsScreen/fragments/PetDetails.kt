package com.example.softxpert.petDetailsScreen.fragments

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.domain.models.Pets
import com.example.softxpert.databinding.FragmentPetDetailsBinding


class PetDetails : Fragment() {

    private lateinit var binding: FragmentPetDetailsBinding
    private  var pet: Pets? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPetDetailsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPetData()

        setPetData()

        openWebsite()
        backHandling()
    }

    private fun backHandling() {
        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun openWebsite() {
        binding.button.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(pet?.url)
            startActivity(i)
        }
    }

    private fun setPetData() {
        binding.petDetails=pet
    }

    private fun getPetData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pet = arguments?.getSerializable("pet", Pets::class.java)
        }
        else{
            @Suppress("DEPRECATION")
            pet = arguments?.getSerializable("pet") as Pets
        }

    }
}