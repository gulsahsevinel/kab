package com.gulsah.kab.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FoodBasket(
    @SerializedName("yemek_id")
    @Expose
    var foodId: Int,
    @SerializedName("yemek_adi")
    @Expose
    var foodName: String,
    @SerializedName("yemek_resim_adi")
    @Expose
    var foodMediaUrl: String,
    @SerializedName("yemek_fiyat")
    @Expose
    var foodPrice: Int,
    @SerializedName("yemek_siparis_adet")
    @Expose
    var foodBasketCount: Int,
    @SerializedName("kullanici_adi")
    @Expose
    var username : String,
) : Serializable{
}