package com.example.vkcup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_cotainer)

        if(currentFragment==null){
            val fragment = CategoriesSelectorFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragment_cotainer,fragment).commit()
        }
    }
}