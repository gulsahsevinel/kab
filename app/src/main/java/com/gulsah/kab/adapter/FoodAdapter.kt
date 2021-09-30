package com.gulsah.kab.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gulsah.kab.databinding.RowFoodBinding
import com.gulsah.kab.entity.Food
import com.gulsah.kab.view.HomePageFragment
import com.squareup.picasso.Picasso

class FoodAdapter(var mContext: Context, var foodList: List<Food>) :
    RecyclerView.Adapter<FoodAdapter.CardHolder>() {

    inner class CardHolder(rowFoodBinding: RowFoodBinding) :
        RecyclerView.ViewHolder(rowFoodBinding.root) {
        var foodBinding: RowFoodBinding

        init {
            this.foodBinding = rowFoodBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = RowFoodBinding.inflate(layoutInflater, parent, false)
        return CardHolder(binding)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val food = foodList[position]
        holder.foodBinding.foodObject = food

        Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/${food.foodMediaUrl}")
            .into(holder.foodBinding.imageViewFood)

        holder.foodBinding.imageButtonAddBasket.setOnClickListener {
        }

    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}