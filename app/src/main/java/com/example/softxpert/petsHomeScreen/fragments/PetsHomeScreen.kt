package com.example.softxpert.petsHomeScreen.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.apiStates.PetsApiStates
import com.example.domain.models.Pets
import com.example.softxpert.R
import com.example.softxpert.databinding.PetsHomeScreenFragmentBinding
import com.example.softxpert.petsHomeScreen.adapters.OnItemClickListener
import com.example.softxpert.petsHomeScreen.adapters.PetsAdapter
import com.example.softxpert.petsHomeScreen.viewModels.PetsViewModel
import com.example.softxpert.sharedViews.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PetsHomeScreen : Fragment(), View.OnClickListener ,OnItemClickListener{
    private lateinit var binding: PetsHomeScreenFragmentBinding
    private var currentType: String = ""
    private val petsViewModel: PetsViewModel by viewModels()
    private lateinit var adapter: PetsAdapter
    private var lastPage = 1
    private var currentPage = 1
    private var loading = false
    private lateinit var prevTypeView: TextView
    private  var petModels:List<Pets>?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = PetsHomeScreenFragmentBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
      //  loadPets()
        loadMore()
        observePetsListChanges()
        observeMorePets()

    }

    private fun observeMorePets() {
        lifecycleScope.launch {
            petsViewModel.morePets.collect {
                loading = false
                when (val result = it) {
                    is PetsApiStates.Success -> {
                        petModels=result.data?.pets
                        adapter.addData(petModels)
                        lastPage = result.data?.pagination?.total_pages ?: 1
                        currentPage = result.data?.pagination?.current_page ?: 1
                    }

                    is PetsApiStates.Failure -> {
                        showSnackBar(binding.root, result.error.message, loadPets)
                        adapter.setData(result.offlineData?.pets)


                    }

                    is PetsApiStates.Idle -> {

                    }

                    is PetsApiStates.Loading -> {

                    }
                }
            }
        }
    }

    //for instantiation
    private fun init() {
        adapter = PetsAdapter(this)
        binding.petsAdapter = adapter
        binding.all.setOnClickListener(this)
        binding.rabbit.setOnClickListener(this)
        binding.cats.setOnClickListener(this)
        binding.horse.setOnClickListener(this)
        binding.bird.setOnClickListener(this)

        prevTypeView=binding.all
        binding.all.performClick()
    }

    private fun loadMorePets() {

        petsViewModel.loadMorePets(currentPage, lastPage, currentType)

    }

    private fun observePetsListChanges() {
        lifecycleScope.launch {

            petsViewModel.pets.collect {
                when (val result = it) {
                    is PetsApiStates.Success -> {
                        binding.progressBar.visibility=View.GONE
                        binding.petsList.visibility=View.VISIBLE

                        adapter.setData(result.data?.pets)
                        lastPage = result.data?.pagination?.total_pages ?: 1
                        currentPage = result.data?.pagination?.current_page ?: 1
                    }

                    is PetsApiStates.Failure -> {
                        binding.progressBar.visibility=View.GONE
                        binding.petsList.visibility=View.VISIBLE

                        showSnackBar(binding.root, result.error.message, loadPets)
                        adapter.setData(result.offlineData?.pets)


                    }

                    is PetsApiStates.Idle -> {
                        binding.progressBar.visibility=View.GONE
                        binding.petsList.visibility=View.VISIBLE

                    }

                    is PetsApiStates.Loading -> {
                        binding.progressBar.visibility=View.VISIBLE
                        binding.petsList.visibility=View.GONE

                    }
                }
            }


        }
    }

    private val loadPets = {
        binding.petsList.scrollToPosition(0)
        petsViewModel.getPets(currentType)
    }
    private val loadMore = {
        binding.petsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1) && !loading) {
                    loading = true
                    loadMorePets()
                }


            }
        })
    }

    override fun onClick(p0: View?) {
        when(p0){

            binding.all ->{
                prevTypeView.setBackgroundColor(Color.WHITE)
                binding.all.setBackgroundColor(Color.CYAN)
                prevTypeView=binding.all
                currentType=""
                loadPets()
            }
            binding.bird ->{
                prevTypeView.setBackgroundColor(Color.WHITE)
                binding.bird.setBackgroundColor(Color.CYAN)
                prevTypeView=binding.bird
                currentType="Bird"
                loadPets()
            }
            binding.cats ->{
                prevTypeView.setBackgroundColor(Color.WHITE)
                binding.cats.setBackgroundColor(Color.CYAN)
                prevTypeView=binding.cats
                currentType="Cat"
                loadPets()
            }
            binding.horse ->{
                prevTypeView.setBackgroundColor(Color.WHITE)
                binding.horse.setBackgroundColor(Color.CYAN)
                prevTypeView=binding.horse
                currentType="Horse"
                loadPets()
            }
            binding.rabbit ->{
                prevTypeView.setBackgroundColor(Color.WHITE)
                binding.rabbit.setBackgroundColor(Color.CYAN)
                prevTypeView=binding.rabbit
                currentType="Rabbit"
                loadPets()
            }


        }
    }

    override fun onItemClicked(item: Int) {

        val bundle = Bundle().apply {
            putSerializable("pet",adapter.getItemsList()[item])
        }
        Navigation.findNavController(binding.petsList)
            .navigate(R.id.action_petsHomeScreen_to_petDetails, bundle)

    }

}