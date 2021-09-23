package com.gulsah.kab.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gulsah.kab.entity.*
import com.gulsah.kab.retrofit.ApiUtils
import com.gulsah.kab.retrofit.FoodDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KabRepository {
    private var fdao: FoodDaoInterface
    private var foodList: MutableLiveData<List<Food>>
    private var basketlist: MutableLiveData<List<FoodBasket>>

    init {
        fdao = ApiUtils.getFoodDaoInterface()
        foodList = MutableLiveData()
        basketlist = MutableLiveData()
    }

    fun getAllFood() {
        fdao.getAllFoods().enqueue(object : Callback<FoodResponse> {
            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                val list = response.body()!!.foods
                foodList.value = list
            }

            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {}
        })
    }

    fun getAllFoodsInBasket() {
        fdao.getAllFoodsInBasket("gulsahsevinel").enqueue(object : Callback<BasketReponse> {
            override fun onResponse(call: Call<BasketReponse>, response: Response<BasketReponse>) {
                val list = response.body()!!.foodsInBasket
                basketlist.value = list
            }

            override fun onFailure(call: Call<BasketReponse>, t: Throwable) {}
        })
    }

    fun addToBasket(
        foodId: Int,
        foodName: String,
        foodMediaUrl: String,
        foodPrice: Int,
        foodCount: Int
    ) {
        fdao.addToBasket(foodId, foodName, foodMediaUrl, foodPrice, foodCount, "gulsahsevinel")
            .enqueue(object : Callback<CRUDResponse> {
                override fun onResponse(
                    call: Call<CRUDResponse>,
                    response: Response<CRUDResponse>
                ) {
                }

                override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {}
            })
    }

    fun deleteFromBasket(id: Int) {
        fdao.deleteFromBasket(id).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {}
            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {}
        })
    }
}