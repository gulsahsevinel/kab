package com.gulsah.kab.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gulsah.kab.R
import com.gulsah.kab.adapter.BasketAdapter
import com.gulsah.kab.databinding.FragmentBasketBinding
import com.gulsah.kab.viewModel.BasketViewModel


class BasketFragment : Fragment() {

    private lateinit var binding: FragmentBasketBinding
    private lateinit var viewModel: BasketViewModel
    private lateinit var adapter: BasketAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        binding.basketFragment = this

        viewModel.basketList.observe(viewLifecycleOwner) {
            adapter = BasketAdapter(requireContext(), it, viewModel)
            binding.rv.adapter = adapter
        }

        viewModel.totalPrice.observe(viewLifecycleOwner) {
            binding.totalPrice = it
        }

        binding.imageViewBack.setOnClickListener {
            findNavController().navigate(R.id.homePageFragment)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: BasketViewModel by viewModels()
        viewModel = temp
    }

}