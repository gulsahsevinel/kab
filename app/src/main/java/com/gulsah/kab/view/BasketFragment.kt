package com.gulsah.kab.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.gulsah.kab.R
import com.gulsah.kab.databinding.FragmentBasketBinding
import com.gulsah.kab.viewModel.BasketViewModel


class BasketFragment : Fragment() {

    private lateinit var binding: FragmentBasketBinding
    private lateinit var viewModel : BasketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : BasketViewModel by viewModels()
        viewModel = temp
    }

}