package com.gulsah.kab.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gulsah.kab.entity.Food
import com.gulsah.kab.repo.KabRepository

class HomepageViewModel : ViewModel() {
    var repo = KabRepository()
    var foodList =  MutableLiveData<List<Food>>()

    init {
        foodLoad()
        foodList = repo.getFood()
    }

    private fun foodLoad(){
        repo.getAllFood()
    }

}