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
import com.idea3d.open_bank_challengue.data.DataSource
import com.idea3d.open_bank_challengue.databinding.FragmentMainBinding
import com.idea3d.open_bank_challengue.model.Hero
import com.idea3d.open_bank_challengue.repository.RepoImpl
import com.idea3d.open_bank_challengue.ui.adapter.MainAdapter
import com.idea3d.open_bank_challengue.ui.viewmodel.MainViewModel
import com.idea3d.open_bank_challengue.ui.viewmodel.VMFactory

class MainFragment : Fragment(), MainAdapter.OnMovieClickListener {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>(){ VMFactory(RepoImpl(DataSource())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        setUpRecyclerView()

        viewModel.fetchHerosList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading->{
                    binding.prBar.visibility=View.VISIBLE
                    binding.prError.visibility=View.GONE
                }
                is Resource.Success->{
                    binding.prBar.visibility=View.GONE
                    binding.prError.visibility=View.GONE
                    binding.rvHeros.adapter=MainAdapter(requireContext(), result.data, this)
                }
                is Resource.Failure->{
                    binding.prBar.visibility=View.GONE
                    binding.prError.visibility=View.VISIBLE
                    Toast.makeText(requireContext()," ${result.exception}",Toast.LENGTH_LONG).show()
                }

            }
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun setUpRecyclerView() {
        val appContext = requireContext().applicationContext
        val recyclerView = binding.rvHeros
        recyclerView.layoutManager= LinearLayoutManager(appContext)
        binding.rvHeros.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

    override fun onHeroClick(hero: Hero) {
        val bundle = Bundle()
        bundle.putParcelable("hero", hero)
        findNavController().navigate(R.id.detailsFragment, bundle)
    }
}
