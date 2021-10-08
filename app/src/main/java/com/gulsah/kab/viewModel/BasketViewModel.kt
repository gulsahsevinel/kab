package com.gulsah.kab.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gulsah.kab.entity.FoodBasket
import com.gulsah.kab.repo.KabRepository

class BasketViewModel : ViewModel() {

    var repo = KabRepository()
    var basketList = MutableLiveData<List<FoodBasket>>()
    var totalPrice = MutableLiveData<Int>()

    init {
        basketLoad()
        basketList = repo.getBasketFood()
        totalPrice = repo.getTotalPrice()
    }

    fun basketLoad() {
        repo.getAllFoodsInBasket()
        basketList = repo.getBasketFood()
    }

    fun delete(id: Int) {
        repo.deleteFromBasket(id)
        basketLoad()
    }

}