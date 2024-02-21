package com.example.clothingshop.adapter

import android.content.Context
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
import com.example.clothingshop.entity.Dresses
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DressesAdapter(private val context: Context) : ListAdapter<Dresses, DressesViewHolder>(dressesDiffCallback()) {

    private val cartItemInfo = AppDB.getDatabase(context).cartDao();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DressesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dressesitem,parent,false)
        return  DressesViewHolder(view)
    }

    override fun onBindViewHolder(holder: DressesViewHolder, position: Int) {
        val _dresses = getItem(position)
        holder.dressesBind(_dresses)

        holder.addToCart.setOnClickListener {
            val item = CartItem(0,_dresses.id,_dresses.dressName,1,_dresses.price,_dresses.imageName)
            insertCartItem(item)
            Toast.makeText(context, _dresses.dressName+" added to the cart", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertCartItem(cartItem: CartItem) {
        // Use a CoroutineScope to perform database operations in a background thread
        CoroutineScope(Dispatchers.IO).launch {
            cartItemInfo.insert(cartItem)
        }
    }
}

class dressesDiffCallback: DiffUtil.ItemCallback<Dresses>() {
    override fun areItemsTheSame(oldItem: Dresses, newItem: Dresses): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Dresses, newItem: Dresses): Boolean {
        return oldItem == newItem
    }

}

class DressesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //get dressesitem layout object
    private  val dressesNameView : TextView = itemView.findViewById(R.id.dressName)
    private  val dressesPriceView : TextView = itemView.findViewById(R.id.dressPrice)
    private  val dressesImageView : ImageView = itemView.findViewById(R.id.dressImage)
    val  addToCart : Button = itemView.findViewById(R.id.addDressCart)

    fun dressesBind (_dresses:Dresses){
        dressesNameView.text = _dresses.dressName
        dressesPriceView.text = "Rs : ${_dresses.price}"
        val dressesImageResourceId = getDressesImageResorceId(_dresses.imageName)
        println("resouce id "+dressesImageResourceId)
        dressesImageView.setImageResource(dressesImageResourceId)
    }


    //get resource id from drawable folder according to image name
    private  fun getDressesImageResorceId(imageName:String): Int{
        return itemView.context.resources.getIdentifier(imageName,"drawable", itemView.context.packageName)
    }

}
