package com.gulsah.kab.splashScreen

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gulsah.kab.R
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        //getActivity().getSupportActionBar().hide();

        Handler().postDelayed({
           /* if(onBoardingFinished()){
                findNavController().navigate(R.id.splashScreen_to_viewpager)
            }else{
                findNavController().navigate(R.id.splash_to_home)
            }*/
            findNavController().navigate(R.id.splashScreen_to_viewpager)
        },3000)

        return view
    }

    private fun onBoardingFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

}