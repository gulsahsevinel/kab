package com.gulsah.kab.adapter

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.gulsah.kab.R
import com.gulsah.kab.databinding.BottomSheetDialogFoodDetailBinding
import com.gulsah.kab.databinding.RowFoodBinding
import com.gulsah.kab.entity.Food
import com.gulsah.kab.viewModel.HomepageViewModel
import com.squareup.picasso.Picasso

class FoodAdapter(
    var mContext: Context,
    var foodList: List<Food>,
    var viewModel: HomepageViewModel
) :
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
        var foodCount = 1

        Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/${food.foodMediaUrl}")
            .into(holder.foodBinding.imageViewFood)

        holder.foodBinding.imageButtonAddBasket.setOnClickListener {
            viewModel.addBasket(
                food.foodId,
                food.foodName,
                food.foodMediaUrl,
                food.foodPrice,
                foodCount
            )
            viewModel.basketLoad()
            Toast.makeText(mContext, "Added to basket", Toast.LENGTH_SHORT).show()
        }

        holder.foodBinding.foodCard.setOnClickListener {
            showBottomSheetDialog(position)
        }

    }

    override fun getItemCount(): Int = foodList.size

    private fun showBottomSheetDialog(position: Int) {
        val dialog = Dialog(mContext)
        val binding: BottomSheetDialogFoodDetailBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.bottom_sheet_dialog_food_detail, null,
            false
        )

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.show()

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.BottomSheetAnimation
        dialog.window!!.setGravity(Gravity.BOTTOM)

        val food = foodList[position]
        binding.foodObject = food

        var count = 1
        binding.foodCount.text = count.toString()
        Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/${food.foodMediaUrl}")
            .into(binding.imageViewDialogFood)

        binding.imageViewCloseDialog.setOnClickListener {
            dialog.dismiss()
        }

        binding.buttonAddBasket.setOnClickListener {
            count += 1
            Log.e("add basket", count.toString())
            binding.foodCount.text = count.toString()
        }
        binding.buttonRemoveBasket.setOnClickListener {
            count -= 1
            if (count < 1) count = 1
            Log.e("min basket", count.toString())
            binding.foodCount.text = count.toString()
        }

        binding.dialogAddBasket.setOnClickListener {
            viewModel.addBasket(
                food.foodId,
                food.foodName,
                food.foodMediaUrl,
                food.foodPrice,
                count
            )
            viewModel.basketLoad()
        }

        dialog.setOnDismissListener {
            //kapatılınca
            viewModel.basketLoad()
        }

    }
}