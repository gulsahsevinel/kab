package com.gulsah.kab.onBoarding.screens

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.viewpager2.widget.ViewPager2
import com.gulsah.kab.R
import kotlinx.android.synthetic.main.fragment_first_screen.view.*

class FirstScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_screen, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        //hideStatusBar()
        activity?.window?.statusBarColor = resources.getColor(R.color.red)

        view.buttonNext.setOnClickListener {
            viewPager?.currentItem = 1
        }
        return view
    }

    fun hideStatusBar() {
        //hide status bar
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}
