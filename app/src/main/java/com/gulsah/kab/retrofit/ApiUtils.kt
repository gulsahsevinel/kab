package com.gulsah.kab.retrofit

class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/yemekler/"
        fun getFoodDaoInterface(): FoodDaoInterface {
            return RetrofitClient.getClient(BASE_URL).create(FoodDaoInterface::class.java)
        }
    }
}