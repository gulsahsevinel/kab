package com.gulsah.kab

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:setPrice")
fun setPrice(textView : TextView, price : Int){
    textView.text = "₺ $price.00"
}