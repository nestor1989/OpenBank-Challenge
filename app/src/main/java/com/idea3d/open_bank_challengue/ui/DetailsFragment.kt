package com.idea3d.open_bank_challengue.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.idea3d.open_bank_challengue.R
import com.idea3d.open_bank_challengue.databinding.FragmentDetailsBinding
import com.idea3d.open_bank_challengue.model.Hero

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var hero: Hero

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
        Glide.with(requireContext()).load(hero.image).centerCrop().into(_binding!!.imageView)
        _binding!!.tvTitle.text=hero.name
        _binding!!.tvDesc.text=hero.description
        _binding!!.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.mainFragment)
        }

    }
}