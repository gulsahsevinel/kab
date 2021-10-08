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
    private var totalPrice: MutableLiveData<Int>
    private var basketList: MutableLiveData<List<FoodBasket>>

    init {
        fdao = ApiUtils.getFoodDaoInterface()
        foodList = MutableLiveData()
        basketList = MutableLiveData()
        totalPrice = MutableLiveData()
    }

    fun getFood(): MutableLiveData<List<Food>> {
        return foodList
    }

    fun getBasketFood(): MutableLiveData<List<FoodBasket>> {
        return basketList
    }

    fun getTotalPrice(): MutableLiveData<Int> {
        return totalPrice
    }

    fun getAllFood() {
        fdao.getAllFoods().enqueue(object : Callback<FoodResponse> {
            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                val list = response.body()?.foods
                foodList.value = list
            }

            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {}
        })
    }

    fun getAllFoodsInBasket() {
        fdao.getAllFoodsInBasket("gulsahsevinel").enqueue(object : Callback<BasketReponse> {
            override fun onResponse(call: Call<BasketReponse>, response: Response<BasketReponse>) {
                val list = response.body()!!.foodsInBasket
                var price = 0
                for (i in list) {
                    price += i.foodPrice * i.foodBasketCount
                }
                basketList.value = list
                totalPrice.value = price
            }

            override fun onFailure(call: Call<BasketReponse>, t: Throwable?) {
                if (t != null) {
                    Log.e("getallcartitems", t.toString())
                    basketList.value = emptyList()
                    totalPrice.value = 0
                }
            }
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