package com.example.clothingshop

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class Menumain : AppCompatActivity() {
    //lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityHomeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        setContentView(R.layout.activity_menumain)

        val dressesButtons = findViewById<Button>(R.id.fragment1Btn)
        val denimsButons = findViewById<Button>(R.id.fragment2Btn)

        dressesButtons.setOnClickListener{
            replaceFragment(DressesFragment())
        }
        denimsButons.setOnClickListener {
            replaceFragment(DenimsFragment())
        }
    }
    private fun replaceFragment(fragment :Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()
    }
}