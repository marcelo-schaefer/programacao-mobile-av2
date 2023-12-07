package com.github.nunes03.av2.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.github.nunes03.av2.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        this.deleteDatabase("pet_shop")

        setClickButtons()
    }

    private fun setClickButtons() {
        val userButton = findViewById<Button>(R.id.userButtonHome)
        val kindButton = findViewById<Button>(R.id.kindButtonHome)
        val animalButton = findViewById<Button>(R.id.animalButtonHome)
        val consultationButton = findViewById<Button>(R.id.consultationButtonHome)
        val searchAnimalButton = findViewById<Button>(R.id.searchAnimalHomeButton)

        userButton.setOnClickListener { openActivity(UserActivity::class.java) }
        kindButton.setOnClickListener { openActivity(KindActivity::class.java) }
        animalButton.setOnClickListener { openActivity(AnimalActivity::class.java) }
        consultationButton.setOnClickListener { openActivity(ConsultationActivity::class.java) }
        searchAnimalButton.setOnClickListener { openActivity(AnimalFilterActivity::class.java) }
    }

    private fun <T> openActivity(activityClass: Class<T>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}