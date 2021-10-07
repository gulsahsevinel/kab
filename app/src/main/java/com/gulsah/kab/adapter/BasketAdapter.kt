package com.gulsah.kab.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gulsah.kab.databinding.RowBasketBinding
import com.gulsah.kab.entity.FoodBasket
import com.gulsah.kab.viewModel.BasketViewModel
import com.squareup.picasso.Picasso

class BasketAdapter(
    var mContext: Context,
    var foodList: List<FoodBasket>,
    var viewModel: BasketViewModel
) : RecyclerView.Adapter<BasketAdapter.CardHolder>() {

    inner class CardHolder(rowBasketBinding: RowBasketBinding) :
        RecyclerView.ViewHolder(rowBasketBinding.root) {
        var basketBinding: RowBasketBinding

        init {
            this.basketBinding = rowBasketBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = RowBasketBinding.inflate(layoutInflater, parent, false)
        return CardHolder(binding)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val food = foodList[position]
        holder.basketBinding.foodObject = food

        Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/${food.foodMediaUrl}")
            .into(holder.basketBinding.imageView)

        holder.basketBinding.imageButtonDeleteBasket.setOnClickListener {
            viewModel.delete(food.foodId)
            viewModel.basketLoad()
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

}