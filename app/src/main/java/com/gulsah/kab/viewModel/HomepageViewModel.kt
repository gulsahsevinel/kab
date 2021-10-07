package com.gulsah.kab.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gulsah.kab.entity.Food
import com.gulsah.kab.entity.FoodBasket
import com.gulsah.kab.repo.KabRepository

class HomepageViewModel : ViewModel() {
    var repo = KabRepository()
    var foodList =  MutableLiveData<List<Food>>()
    var basketList =  MutableLiveData<List<FoodBasket>>()

    init {
        foodLoad()
        basketLoad()
        foodList = repo.getFood()
        basketList = repo.getBasketFood()
    }

    private fun foodLoad(){
        repo.getAllFood()
    }

    fun basketLoad(){
        repo.getAllFoodsInBasket()
        basketList = repo.getBasketFood()
    }

    fun addBasket(foodId: Int, foodName: String, foodMediaUrl: String, foodPrice: Int, foodCount: Int){
        repo.addToBasket(foodId, foodName, foodMediaUrl, foodPrice, foodCount)
        basketLoad()
    }

}