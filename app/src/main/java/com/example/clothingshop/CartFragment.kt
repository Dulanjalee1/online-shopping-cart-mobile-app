package com.example.clothingshop
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clothingshop.adapter.cartAdapter
import com.example.clothingshop.database.AppDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartActivity : AppCompatActivity() {
    private lateinit var cartItemAdapter: cartAdapter
    private val cartItemDao = AppDB.getDatabase(this).cartDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart)
        // Your initialization code here

        val recyclerView = findViewById<RecyclerView>(R.id.cart_Item_List)
        cartItemAdapter = cartAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = cartItemAdapter

        // Use lifecycleScope to launch a coroutine
        lifecycleScope.launch(Dispatchers.IO) {
            // Retrieve cart data from the database and set it to the adapter
            val cartItems = cartItemDao.getAllCartItems()
            cartItemAdapter.submitList(cartItems)
        }
    }
}