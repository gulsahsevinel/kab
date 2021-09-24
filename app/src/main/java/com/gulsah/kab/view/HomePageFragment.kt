package com.gulsah.kab.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.gulsah.kab.R
import com.gulsah.kab.databinding.FragmentHomePageBinding
import com.synnapps.carouselview.ImageListener

class HomePageFragment : Fragment() {

    var imageArray: ArrayList<Int> = ArrayList()

    private lateinit var binding: FragmentHomePageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)

        //for carouselView
        imageArray.add(R.drawable.banner_1)
        imageArray.add(R.drawable.banner_2)
        imageArray.add(R.drawable.banner_3)

        binding.carouselView.pageCount = imageArray.size
        binding.carouselView.setImageListener(imageListener)

        return binding.root
    }

    var imageListener = ImageListener { position, imageView ->
        imageView!!.setImageResource(imageArray[position])
    }

}