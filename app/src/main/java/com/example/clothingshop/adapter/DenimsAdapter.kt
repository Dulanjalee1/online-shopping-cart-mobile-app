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
import com.example.clothingshop.entity.Denims
import com.example.clothingshop.entity.CartItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class denimsAdapter(private val context: Context) : ListAdapter<Denims, DenimsViewHodler>(denimsDiffCallback()) {
    private val cartItemInfo = AppDB.getDatabase(context).cartDao();


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DenimsViewHodler {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.denimsitem,parent,false)
        return  DenimsViewHodler(view)
    }

    override fun onBindViewHolder(holder: DenimsViewHodler, position: Int) {
        val _denims = getItem(position)
        holder.denimsBind(_denims)

        holder.addToCart.setOnClickListener {
            val item = CartItem(0,_denims.id,_denims.denimName,1,_denims.price,_denims.ImageName)
            insertCartItem(item)
            Toast.makeText(context, _denims.denimName+" added to the cart", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertCartItem(cartItem: CartItem) {
        // Use a CoroutineScope to perform database operations in a background thread
        CoroutineScope(Dispatchers.IO).launch {
            cartItemInfo.insert(cartItem)
        }
    }
}

class denimsDiffCallback: DiffUtil.ItemCallback<Denims>() {
    override fun areItemsTheSame(oldItem: Denims, newItem: Denims): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Denims, newItem: Denims): Boolean {
        return oldItem == newItem
    }

}

class DenimsViewHodler(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //get fooditem layout object
    private  val denimsNameView : TextView = itemView.findViewById(R.id.denimName)
    private  val denimsPriceView : TextView = itemView.findViewById(R.id.denimPrice)
    private  val denimsImageView : ImageView = itemView.findViewById(R.id.denimImage)
    val  addToCart : Button = itemView.findViewById(R.id.addDenimCart)

    fun denimsBind (_denims:Denims){
        denimsNameView.text = _denims.denimName
        denimsPriceView.text = "Rs : ${_denims.price}"
        val denimsImageResourceId = getDenimsImageResorceId(_denims.ImageName)
        denimsImageView.setImageResource(denimsImageResourceId)

    }


    //get resource id from drawable folder according to image name
    private  fun getDenimsImageResorceId(imageName:String): Int{
        return itemView.context.resources.getIdentifier(imageName,"drawable", itemView.context.packageName)
    }

}
