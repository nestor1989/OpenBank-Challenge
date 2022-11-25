package com.idea3d.open_bank_challengue.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.idea3d.open_bank_challengue.R
import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.databinding.FragmentFavoriteBinding
import com.idea3d.open_bank_challengue.databinding.FragmentMainBinding
import com.idea3d.open_bank_challengue.model.Hero
import com.idea3d.open_bank_challengue.model.HeroEntity
import com.idea3d.open_bank_challengue.model.Photo
import com.idea3d.open_bank_challengue.ui.adapter.FavoriteAdapter
import com.idea3d.open_bank_challengue.ui.adapter.MainAdapter
import com.idea3d.open_bank_challengue.ui.viewmodel.FavoriteViewModel
import com.idea3d.open_bank_challengue.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(), FavoriteAdapter.OnMovieClickListener {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FavoriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        setUpRecyclerView()
        setUpObservers()

        return binding.root
    }

    private fun setUpRecyclerView() {
        val appContext = requireContext().applicationContext
        val recyclerView = binding.rvHeros
        recyclerView.layoutManager= LinearLayoutManager(appContext)
        binding.rvHeros.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpObservers(){
        viewModel.getFavoriteHeroes().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading->{
                    binding.prBar.visibility=View.VISIBLE
                    binding.prError.visibility=View.GONE
                }
                is Resource.Success->{
                    binding.prBar.visibility=View.GONE
                    binding.prError.visibility=View.GONE
                    binding.rvHeros.adapter = FavoriteAdapter(requireContext(), result.data, this)
                }
                is Resource.Failure->{
                    binding.prBar.visibility=View.GONE
                    binding.prError.visibility=View.VISIBLE
                    Toast.makeText(requireContext(),getString(R.string.no_fav), Toast.LENGTH_LONG).show()
                }

            }
        })
    }

    override fun onHeroClick(heroEntity: HeroEntity) {
        val bundle = Bundle()
        val hero = Hero(heroEntity.id, heroEntity.name, Photo(heroEntity.path, heroEntity.extension), heroEntity.description)
        bundle.putParcelable("hero", hero)
        findNavController().navigate(R.id.detailsFragment, bundle)
    }



}