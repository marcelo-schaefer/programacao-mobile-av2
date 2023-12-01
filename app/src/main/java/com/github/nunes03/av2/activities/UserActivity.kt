package com.github.nunes03.av2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.nunes03.av2.R

class UserActivity : AppCompatActivity() {

    override fun onBackPressed() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
    }
}