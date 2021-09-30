package com.gulsah.kab.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.gulsah.kab.R
import com.gulsah.kab.adapter.FoodAdapter
import com.gulsah.kab.databinding.FragmentHomePageBinding
import com.gulsah.kab.viewModel.HomepageViewModel
import com.synnapps.carouselview.ImageListener

class HomePageFragment : Fragment() {

    var imageArray: ArrayList<Int> = ArrayList()
    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewModel: HomepageViewModel
    private lateinit var adapter: FoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)

        binding.fragment = this

        binding.rv.layoutManager = GridLayoutManager(requireContext(), 2)

        //for carouselView
        imageArray.add(R.drawable.banner_1)
        imageArray.add(R.drawable.banner_2)
        imageArray.add(R.drawable.banner_3)

        binding.carouselView.pageCount = imageArray.size
        binding.carouselView.setImageListener(imageListener)

        viewModel.foodList.observe(viewLifecycleOwner) {
            adapter = FoodAdapter(requireContext(), it)
            binding.rv.adapter = adapter
        }

        //search
        binding.inputSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }

        })

        return binding.root
    }

    var imageListener = ImageListener { position, imageView ->
        imageView!!.setImageResource(imageArray[position])
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: HomepageViewModel by viewModels()
        viewModel = temp
    }

}