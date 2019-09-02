package com.example.kotlinfirstapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinfirstapplication.Utils.SharedPreferenceManager

class Splash : AppCompatActivity() {

    var sharedPreferenceManager: SharedPreferenceManager ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        this.sharedPreferenceManager = SharedPreferenceManager(applicationContext)

        if (!sharedPreferenceManager!!.getLoginSession())
        {
            var intent = Intent(applicationContext,LoginScreen::class.java)
            startActivity(intent)
            finish()
        }
        else
        {
            var intent = Intent(applicationContext,Dashboard::class.java)
            startActivity(intent)
            finish()
        }




    }
}
