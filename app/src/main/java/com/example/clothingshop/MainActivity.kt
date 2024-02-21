package com.example.clothingshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.clothingshop.DAO.DenimsDao
import com.example.clothingshop.DAO.DressesDao
import com.example.clothingshop.database.AppDB
import com.example.clothingshop.entity.Denims
import com.example.clothingshop.entity.Dresses
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // Declare variables for Room Database and DAOs
    private lateinit var dressesDao: DressesDao
    private lateinit var denimsDao: DenimsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shopnow = findViewById<Button>(R.id.button2)
        shopnow.setOnClickListener {
            val intent = Intent(this, Menumain::class.java)
            startActivity(intent)
        }

        // Initialize your Room Database and DAOs
        val DB = AppDB.getDatabase(this.applicationContext)
        dressesDao = DB.dressesDao()
        denimsDao = DB.denimsDao()

        CoroutineScope(Dispatchers.IO).launch {
            addingDummyDataToDB()
        }

    }

    private suspend fun addingDummyDataToDB() {
        //adding dummy data to dresses
        val foodData = listOf<Dresses>(
            Dresses(0,"Butterfly print Top", 2500.0, "butterflyprittop"),
            Dresses(0,"Jumpsuit", 3000.0, "jumpsuit"),
            Dresses(0,"Maxi Dress", 4500.0, "maxidress"),
            Dresses(0,"Off Shoulder Crop Top", 3200.0, "offshouldercroptop"),
            Dresses(0,"Oversized Tshirt", 2400.0, "oversizedtshirt"),
            Dresses(0,"Romper", 3800.0, "romper"),
            Dresses(0,"Short Dress", 6450.0, "shortdress")
        )


        //adding dummy data to denims
        val beverageData = listOf<Denims>(
            Denims(0,"Bell Bottom Jeans",6990.0,"bellbottomjeans"),
            Denims(0,"Cargo Pant",4000.0,"cargopant"),
            Denims(0,"Denim Dungaree",5500.0,"denimdungaree"),
            Denims(0,"Denim Jogger",3500.0,"denimjogger"),
            Denims(0,"Denim shorts",2800.0,"denimshorts"),
            Denims(0,"High waist Jean",4800.0,"highwaistjean"),
            Denims(0,"Singlebutton Wideleg Denim",5700.0,"singlebuttonwidelegdenim")
        )

        // Insert the data into the database
        foodData.forEach { dressesDao.insert(it) }
        beverageData.forEach { denimsDao.insert(it) }
    }
}