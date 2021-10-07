package com.gulsah.kab.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
        //for carouselView
        imageArray = addImage()
        binding.carouselView.pageCount = imageArray.size
        binding.carouselView.setImageListener(imageListener)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.fragment = this

        binding.rv.layoutManager = GridLayoutManager(requireContext(),2)


        viewModel.foodList.observe(viewLifecycleOwner) {
            adapter = FoodAdapter(requireContext(), it, viewModel)
            binding.rv.adapter = adapter
        }

        viewModel.basketList.observe(viewLifecycleOwner){
            binding.basketListSize = it.size
            Log.e("size homepage", it.size.toString())
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: HomepageViewModel by viewModels()
        viewModel = temp
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        viewModel.basketLoad()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_to_cart -> {
                Navigation.findNavController(binding.toolbar).navigate(R.id.basketFragment)
                true
            }
            else -> true
        }
    }


    //carouselView
    var imageListener = ImageListener { position, imageView ->
        imageView!!.setImageResource(imageArray[position])
    }

    fun addImage(): ArrayList<Int> {
        return arrayListOf(R.drawable.banner_1, R.drawable.banner_2, R.drawable.banner_3)
    }

}