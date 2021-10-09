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
import android.widget.Toast
import androidx.databinding.DataBindingUtil
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
            Toast.makeText(mContext, "${food.foodName} sepete eklendi!", Toast.LENGTH_SHORT).show()
        }

        holder.foodBinding.foodCard.setOnClickListener {
            showBottomSheetDialog(position)
        }

        holder.foodBinding.imageViewInfo.setOnClickListener {
            val popup = androidx.appcompat.widget.PopupMenu(mContext, it)
            popup.menuInflater.inflate(R.menu.food_adapter_menu, popup.menu)
            popup.show()
            popup.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_info -> {
                        showBottomSheetDialog(position)
                        true
                    }
                    else -> false
                }
            }
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

        var quantity = 1
        binding.quantity = quantity
        Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/${food.foodMediaUrl}")
            .into(binding.imageViewDialogFood)

        binding.imageViewCloseDialog.setOnClickListener {
            dialog.dismiss()
        }

        binding.buttonAddBasket.setOnClickListener {
            quantity += 1
            Log.e("add basket", quantity.toString())
            binding.quantity = quantity
        }
        binding.buttonRemoveBasket.setOnClickListener {
            quantity -= 1
            if (quantity < 1) quantity = 1
            Log.e("min basket", quantity.toString())
            binding.quantity = quantity
        }

        binding.dialogAddBasket.setOnClickListener {
            viewModel.addBasket(
                food.foodId,
                food.foodName,
                food.foodMediaUrl,
                food.foodPrice,
                quantity
            )
            Toast.makeText(mContext, "${food.foodName} sepete eklendi!", Toast.LENGTH_SHORT).show()
            viewModel.basketLoad()
        }

        dialog.setOnDismissListener {
            //kapatılınca
            viewModel.basketLoad()
        }

    }
}