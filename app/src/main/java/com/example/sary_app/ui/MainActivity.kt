package com.example.sary_app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sary_app.R
import com.example.sary_app.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.main_container, HomeFragment(), HomeFragment::class.java.simpleName)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}