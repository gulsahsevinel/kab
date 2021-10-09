package com.gulsah.kab

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:setPrice")
fun setPrice(textView : TextView, price : Int){
    textView.text = "₺ $price.00"
}

@BindingAdapter("android:set_basket")
fun setBasket(toolbar: androidx.appcompat.widget.Toolbar, size : Int){
    val basketItem = toolbar.menu.findItem(R.id.action_add_to_cart)
    Log.e("size adapter ",size.toString())
    when(size){
        0 -> basketItem.setIcon(R.drawable.icn_basket)
        else -> basketItem.setIcon(R.drawable.icn_full_basket)
    }
}

@BindingAdapter("android:setFoodCount")
fun setFoodCount(textView : TextView, count : Int){
    textView.text = "adet : $count"
}