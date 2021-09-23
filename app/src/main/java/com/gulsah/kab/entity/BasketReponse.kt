package com.gulsah.kab.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BasketReponse(
    @SerializedName("sepet_yemekler")
    @Expose
    var foodsInBasket: List<FoodBasket>,
    @SerializedName("success")
    @Expose
    var success: Int
) {
}