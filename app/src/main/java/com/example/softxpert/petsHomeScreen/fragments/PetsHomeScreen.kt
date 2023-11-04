package com.example.softxpert.petsHomeScreen.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.softxpert.databinding.PetsHomeScreenFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PetsHomeScreen : Fragment()  {
    private lateinit var binding: PetsHomeScreenFragmentBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = PetsHomeScreenFragmentBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }




}