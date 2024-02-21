package com.example.clothingshop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.clothingshop.R
import com.example.clothingshop.database.AppDB
import com.example.clothingshop.entity.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class cartAdapter : ListAdapter<CartItem, CartViewHodler>(cartDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHodler {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cartitem,parent,false)
        return  CartViewHodler(view)
    }
    override fun onBindViewHolder(holder: CartViewHodler, position: Int) {
        val _cartItem = getItem(position)
        holder.cartItemBind(_cartItem)
    }
}

class cartDiffCallback: DiffUtil.ItemCallback<CartItem>() {
    override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem == newItem
    }
}

class CartViewHodler(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //get fooditem layout object
    private  val cartItemNameView : TextView = itemView.findViewById(R.id.cartItemName)
    private  val cartItemPriceView : TextView = itemView.findViewById(R.id.cartItemPrice)
    private  val cartItemImageView : ImageView = itemView.findViewById(R.id.cartItemImage)
    private val  removeFromCart : Button = itemView.findViewById(R.id.btnRemoveItem)
    private val  qryAdd : Button = itemView.findViewById(R.id.btnQtyIncriment)
    private val  qtyMin : Button = itemView.findViewById(R.id.btnQtyDecriment)
    private val  qty : TextView = itemView.findViewById(R.id.cartItemQty)

    fun cartItemBind (_cartItem: CartItem){
        cartItemNameView.text = _cartItem.itemName
        cartItemPriceView.text = "Rs : ${_cartItem.totalPrice}"
        qty.text = _cartItem.itemQty.toString()

        val imageName = _cartItem.itemImage
        val imageResourceId = itemView.context.resources.getIdentifier(
            imageName, "drawable", itemView.context.packageName
        )
        cartItemImageView.setImageResource(imageResourceId)

        removeFromCart.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val cartDao = AppDB.getDatabase(itemView.context).cartDao()
                cartDao.delete(_cartItem)
            }

            Toast.makeText(itemView.context, _cartItem.itemName+" removed from the cart", Toast.LENGTH_SHORT).show()
        }

        qryAdd.setOnClickListener {
            // Increment the quantity by 1 for the corresponding item
            _cartItem.itemQty++
            qty.text = _cartItem.itemQty.toString()
            cartItemPriceView.text = "Rs : "+(_cartItem.totalPrice * _cartItem.itemQty).toString()
            // Update the database with the new quantity
            // Make sure to check for upper limits if necessary
            GlobalScope.launch(Dispatchers.IO) {
                val cartDao = AppDB.getDatabase(itemView.context).cartDao()
                cartDao.updateCartItemQuantityAndTotalPrice(_cartItem.id, _cartItem.itemQty, _cartItem.totalPrice)
            }
        }

        qtyMin.setOnClickListener {
            // Decrement the quantity by 1 for the corresponding item
            if (_cartItem.itemQty > 1) {
                _cartItem.itemQty--
                qty.text = _cartItem.itemQty.toString()
                cartItemPriceView.text = "Rs : "+(_cartItem.totalPrice * _cartItem.itemQty).toString()
                // Update the database with the new quantity
                GlobalScope.launch(Dispatchers.IO) {
                    val cartDao = AppDB.getDatabase(itemView.context).cartDao()
                    cartDao.updateCartItemQuantityAndTotalPrice(_cartItem.id, _cartItem.itemQty, _cartItem.totalPrice)
                }
            }
        }
    }

    //get resource id from drawable folder according to image name
    private  fun getCartItemImageResorceId(imageName:String): Int{
        return itemView.context.resources.getIdentifier(imageName,"drawable", itemView.context.packageName)
    }

}