package com.github.nunes03.av2.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.nunes03.av2.R
import com.github.nunes03.av2.adapters.AnimalListViewAdapter
import com.github.nunes03.av2.database.entities.AnimalEntity
import com.github.nunes03.av2.database.entities.KindEntity
import com.github.nunes03.av2.database.repositories.AnimalRepository
import com.github.nunes03.av2.database.repositories.KindRepository
import com.github.nunes03.av2.database.repositories.UserRepository
import com.github.nunes03.av2.database.repositories.interfaces.AnimalRepositoryInterface
import com.github.nunes03.av2.database.repositories.interfaces.KindRepositoryInterface
import com.github.nunes03.av2.database.repositories.interfaces.UserRepositoryInterface

class AnimalFilterActivity : AppCompatActivity() {

    private val animals = ArrayList<AnimalEntity>()

    private lateinit var animalRepository: AnimalRepositoryInterface

    private lateinit var kindRepository: KindRepositoryInterface

    private lateinit var userRepository: UserRepositoryInterface

    private var idKindSelect: Int = 0

    private lateinit var listViewAdapter: ArrayAdapter<AnimalEntity>

    override fun onBackPressed() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_filter)

        initVariables()
        prepareListView()
        updateListView()
        setButtonClicks()
        setClickSpinner()
        updateKindSpinner()
    }

    private fun initVariables() {
        this.animalRepository = AnimalRepository(this)
        this.kindRepository = KindRepository(this)
        this.userRepository = UserRepository(this)
        this.listViewAdapter = AnimalListViewAdapter(this, this.animals);
    }

    private fun prepareListView() {
        val animalListView: ListView = findViewById(R.id.animalFilterListView)
        animalListView.adapter = this.listViewAdapter
    }

    private fun setButtonClicks() {
        val searchAnimalButton = findViewById<Button>(R.id.searchAnimalFilterButton)
        val arrowBackImage = findViewById<ImageView>(R.id.right_icon_two)

        searchAnimalButton.setOnClickListener { search() }
        arrowBackImage.setOnClickListener { onBackPressed() }
    }

    private fun setClickSpinner() {
        val kindSpinner = findViewById<Spinner>(R.id.kindAnimalFilterSpinner)

        kindSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                val kindSelected = parentView.getItemAtPosition(position).toString()
                idKindSelect = kindSelected.split("-")[0].trim().toInt()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun updateKindSpinner() {
        val kinds = ArrayList<String>()

        kindRepository.findAll().forEach { kindEntity ->
            val kindData = "${kindEntity.id} - ${kindEntity.name}"

            kinds.add(kindData)
        }

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, kinds)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner = findViewById<Spinner>(R.id.kindAnimalFilterSpinner)
        spinner.adapter = arrayAdapter
    }

    private fun updateListView() {
        this.animals.clear()

        this.animalRepository.findAll().forEach { animalEntity ->
            animalEntity.kind = this.kindRepository.findById(animalEntity.kind?.id)
            animalEntity.user = this.userRepository.findById(animalEntity.user?.id)

            this.animals.add(animalEntity)
        }

        listViewAdapter.notifyDataSetChanged()
    }

    private fun search() {
        val nameAnimalText = findViewById<TextView>(R.id.nameAnimalFilterText)
        val currentNameAnimal = nameAnimalText.text.toString().trim()
        val kindSelected = KindEntity()
        kindSelected.id = idKindSelect

        val animalsFound = if (currentNameAnimal.isEmpty()) {
            animalRepository.findByKind(kindSelected)
        } else {
            animalRepository.findByNameContainsAndKind(currentNameAnimal, kindSelected)
        }

        this.animals.clear()
        animalsFound.forEach { animalEntity ->
            animalEntity.kind = this.kindRepository.findById(animalEntity.kind?.id)
            animalEntity.user = this.userRepository.findById(animalEntity.user?.id)

            this.animals.add(animalEntity)
        }

        listViewAdapter.notifyDataSetChanged()
    }
}