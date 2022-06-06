package com.idea3d.open_bank_challengue.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.idea3d.open_bank_challengue.R
import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.data.DataSource
import com.idea3d.open_bank_challengue.databinding.FragmentDetailsBinding
import com.idea3d.open_bank_challengue.model.ComicDetails
import com.idea3d.open_bank_challengue.model.Hero
import com.idea3d.open_bank_challengue.model.HeroDetails
import com.idea3d.open_bank_challengue.model.ItemsComic
import com.idea3d.open_bank_challengue.repository.RepoImpl
import com.idea3d.open_bank_challengue.ui.adapter.ComicsAdapter
import com.idea3d.open_bank_challengue.ui.adapter.MainAdapter
import com.idea3d.open_bank_challengue.ui.viewmodel.DetailsViewModel
import com.idea3d.open_bank_challengue.ui.viewmodel.MainViewModel
import com.idea3d.open_bank_challengue.ui.viewmodel.VMFactory
import java.io.FileDescriptor
import java.io.IOException

class DetailsFragment : Fragment(), ComicsAdapter.OnComicClickListener {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var hero: Hero

    private val viewModel by viewModels<DetailsViewModel>(){ VMFactory(RepoImpl(DataSource())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let{
            hero=it!!.getParcelable("hero")!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtons()
        viewModel.setHero(hero.id)
        setUpObservers()
        setUpRecyclerView()

    }
    private fun setUpObservers(){
        viewModel.fetchHeroDetails.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading->{
                    binding.prBar.visibility=View.VISIBLE
                    binding.prError.visibility=View.GONE
                }
                is Resource.Success->{
                    binding.prBar.visibility=View.GONE
                    binding.prError.visibility=View.GONE
                    setHeroDetails(result.data)

                }
                is Resource.Failure->{
                    binding.prBar.visibility=View.GONE
                    binding.prError.visibility=View.VISIBLE
                    Toast.makeText(requireContext()," ${result.exception}", Toast.LENGTH_LONG).show()
                }

            }
        })

        viewModel.fetchComicDetails.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading->{
                    binding.prBar.visibility=View.VISIBLE
                    binding.prError.visibility=View.GONE
                }
                is Resource.Success->{
                    binding.prBar.visibility=View.GONE
                    binding.prError.visibility=View.GONE
                    binding.rvComics.adapter=ComicsAdapter(requireContext(), result.data, this)

                }
                is Resource.Failure->{
                    binding.prBar.visibility=View.GONE
                    binding.prError.visibility=View.VISIBLE
                    Toast.makeText(requireContext()," ${result.exception}", Toast.LENGTH_LONG).show()
                }

            }
        })
    }

    private fun setHeroDetails (heroDetails: HeroDetails){
        Glide.with(requireContext())
            .load("${heroDetails.image!!.path}.${heroDetails.image!!.extension}")
            .centerCrop()
            .placeholder(R.drawable.marvel_hero_red)
            .into(binding.imageView)
        binding.tvTitle.text=heroDetails.name
        binding.tvDesc.text=heroDetails.description

    }

    private fun setButtons() {
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.mainFragment)
        }
    }

    private fun setUpRecyclerView() {
        val appContext = requireContext().applicationContext
        val recyclerView = binding.rvComics
        recyclerView.layoutManager= LinearLayoutManager(appContext)
        binding.rvComics.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
    override fun onComicClick(comicDetails: ComicDetails) {
        TODO("Not yet implemented")
    }


}