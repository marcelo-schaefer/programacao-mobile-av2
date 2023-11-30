package com.github.nunes03.av2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.github.nunes03.av2.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setClickButtons()
    }

    private fun setClickButtons() {
        val userButton = findViewById<Button>(R.id.userButtonHome)
        val kindButton = findViewById<Button>(R.id.kindButtonHome)
        val animalButton = findViewById<Button>(R.id.animalButtonHome)
        val consultationButton = findViewById<Button>(R.id.consultationButtonHome)

        userButton.setOnClickListener{openActivity(UserActivity::class.java)}
        kindButton.setOnClickListener{openActivity(KindActivity::class.java)}
        animalButton.setOnClickListener{openActivity(AnimalActivity::class.java)}
        consultationButton.setOnClickListener{openActivity(ConsultationActivity::class.java)}
    }

    private fun <T> openActivity(activityClass: Class<T>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}