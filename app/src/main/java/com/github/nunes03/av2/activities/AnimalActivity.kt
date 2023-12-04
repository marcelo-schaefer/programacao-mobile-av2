package com.github.nunes03.av2.activities

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.github.nunes03.av2.R
import com.github.nunes03.av2.adapters.AnimalListViewAdapter
import com.github.nunes03.av2.database.entities.AnimalEntity
import com.github.nunes03.av2.database.repositories.AnimalRepository
import com.github.nunes03.av2.database.repositories.KindRepository
import com.github.nunes03.av2.database.repositories.UserRepository
import com.github.nunes03.av2.database.repositories.interfaces.AnimalRepositoryInterface
import com.github.nunes03.av2.database.repositories.interfaces.KindRepositoryInterface
import com.github.nunes03.av2.database.repositories.interfaces.UserRepositoryInterface

class AnimalActivity : AppCompatActivity() {

    private val animals = ArrayList<AnimalEntity>()

    private lateinit var animalRepository: AnimalRepositoryInterface

    private lateinit var kindRepository: KindRepositoryInterface

    private lateinit var userRepository: UserRepositoryInterface

    override fun onBackPressed() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal)

        initVariables()
        updateListView()
    }

    private fun initVariables() {
        this.animalRepository = AnimalRepository(this)
        this.kindRepository = KindRepository(this)
        this.userRepository = UserRepository(this)
    }

    private fun updateListView() {
        animals.clear()
        val listView = findViewById<ListView>(R.id.animalListView)

        this.animalRepository.findAll().forEach { animalEntity ->
            animalEntity.kind = this.kindRepository.findById(animalEntity.kind?.id)
            animalEntity.user = this.userRepository.findById(animalEntity.user?.id)

            animals.add(animalEntity)
        }

        val listViewAdapter = AnimalListViewAdapter(this, animals)
        listView.adapter = listViewAdapter
    }
}