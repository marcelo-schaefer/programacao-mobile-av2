package com.github.nunes03.av2.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
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

    private var idAnimalSelected: Int = 0;

    private var idKindSelect: Int = 0

    private var idUserSelect: Int = 0

    override fun onBackPressed() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal)

        initVariables()
        updateListView()
        updateKindSpinner()
        updateUserSpinner()
        setButtonClicks()
        setClickSpinner()
    }

    private fun initVariables() {
        this.animalRepository = AnimalRepository(this)
        this.kindRepository = KindRepository(this)
        this.userRepository = UserRepository(this)
    }

    private fun setButtonClicks() {
        val saveButton = findViewById<Button>(R.id.saveAnimalButton)
        val editButton = findViewById<Button>(R.id.editAnimalButton)

        saveButton.setOnClickListener { createAnimal() }
        editButton.setOnClickListener { editAnimal() }
    }

    private fun setClickSpinner() {
        val kindSpinner = findViewById<Spinner>(R.id.kindAnimalSpinner)
        val userSpinner = findViewById<Spinner>(R.id.userAnimalSpinner)

        kindSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                val kindSelected = parentView.getItemAtPosition(position).toString()
                idKindSelect = kindSelected.split("-")[0].toInt()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        userSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                val userSelected = parentView.getItemAtPosition(position).toString()
                idUserSelect = userSelected.split("-")[0].toInt()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
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

    private fun updateKindSpinner() {
        val kinds = ArrayList<String>()

        kindRepository.findAll().forEach { kindEntity ->
            val kindData = "${kindEntity.id} - ${kindEntity.name}"

            kinds.add(kindData)
        }

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, kinds)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner = findViewById<Spinner>(R.id.kindAnimalSpinner)
        spinner.adapter = arrayAdapter
    }

    private fun updateUserSpinner() {
        val users = ArrayList<String>()

        userRepository.findAll().forEach { userEntity ->
            val userData = "${userEntity.id} - ${userEntity.name}"

            users.add(userData)
        }

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, users)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner = findViewById<Spinner>(R.id.userAnimalSpinner)
        spinner.adapter = arrayAdapter
    }

    private fun createAnimal() {

    }

    private fun editAnimal() {

    }
}