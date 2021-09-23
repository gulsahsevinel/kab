package com.gulsah.kab.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gulsah.kab.R
import com.gulsah.kab.entity.BasketReponse
import com.gulsah.kab.entity.CRUDResponse
import com.gulsah.kab.entity.FoodResponse
import com.gulsah.kab.retrofit.ApiUtils
import com.gulsah.kab.retrofit.FoodDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}