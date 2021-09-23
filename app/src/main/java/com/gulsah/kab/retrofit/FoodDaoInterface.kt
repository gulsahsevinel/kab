package com.gulsah.kab.retrofit

import com.gulsah.kab.entity.BasketReponse
import com.gulsah.kab.entity.CRUDResponse
import com.gulsah.kab.entity.FoodResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodDaoInterface {
    @GET("tumYemekleriGetir.php")
    fun getAllFoods(): Call<FoodResponse>

    @POST("sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun getAllFoodsInBasket(
        @Field("kullanici_adi") username: String
    ): Call<BasketReponse>

    @POST("sepeteYemekEkle.php")
    @FormUrlEncoded
    fun addToBasket(
        @Field("yemek_id") foodId: Int,
        @Field("yemek_adi") foodName: String,
        @Field("yemek_resim_adi") foodMediaUrl: String,
        @Field("yemek_fiyat") foodPrice: Int,
        @Field("yemek_siparis_adet") foodCount: Int,
        @Field("kullanici_adi") username: String
    ): Call<CRUDResponse>

    @POST("sepettenYemekSil.php")
    @FormUrlEncoded
    fun deleteFromBasket(
        @Field("yemek_id") foodId: Int
    ): Call<CRUDResponse>


}