package com.gulsah.kab.onBoarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.viewpager2.widget.ViewPager2
import com.gulsah.kab.R
import kotlinx.android.synthetic.main.fragment_second_screen.view.*

class SecondScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_second_screen, container, false)

        //hideStatusBar()
        activity?.window?.statusBarColor = resources.getColor(R.color.green)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.buttonNext.setOnClickListener {
            viewPager?.currentItem = 2
        }
        return view
    }

    fun hideStatusBar(){
        //hide status bar
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}