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
import com.github.nunes03.av2.adapters.ConsultationListViewAdapter
import com.github.nunes03.av2.database.entities.AnimalEntity
import com.github.nunes03.av2.database.entities.ConsultationEntity
import com.github.nunes03.av2.database.repositories.AnimalRepository
import com.github.nunes03.av2.database.repositories.ConsultationRepository
import com.github.nunes03.av2.database.repositories.interfaces.AnimalRepositoryInterface
import com.github.nunes03.av2.database.repositories.interfaces.ConsultationRepositoryInterface

class ConsultationActivity : AppCompatActivity() {

    private val consultations = ArrayList<ConsultationEntity>()

    private lateinit var consultationRepository: ConsultationRepositoryInterface

    private lateinit var animalRepository: AnimalRepositoryInterface

    private var idConsultationSelected: Int? = 0;

    private var idAnimalSelect: Int = 0

    private lateinit var listViewAdapter: ArrayAdapter<ConsultationEntity>;

    override fun onBackPressed() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultation)

        initVariables()
        prepareListView()
        updateListView()
        updateAnimalSpinner()
        setButtonClicks()
        setClickSpinner()
    }

    private fun initVariables() {
        this.consultationRepository = ConsultationRepository(this)
        this.animalRepository = AnimalRepository(this)
        this.listViewAdapter = ConsultationListViewAdapter(this, this.consultations);
    }

    private fun prepareListView() {
        val consultationListView = findViewById<ListView>(R.id.consultationListView)
        val descriptionConsultationText = findViewById<TextView>(R.id.descriptionConsultationText)
        val scheduleTimeConsultationText = findViewById<TextView>(R.id.scheduleTimeConsultationText)

        consultationListView.adapter = this.listViewAdapter
        consultationListView.setOnItemClickListener { _, _, position, _ ->
            this.idConsultationSelected = this.consultations[position].id
            descriptionConsultationText.text = this.consultations[position].description
            scheduleTimeConsultationText.text = this.consultations[position].scheduledTime
        }

        consultationListView.setOnItemLongClickListener { _, _, position, _ ->
            this.idConsultationSelected = this.consultations[position].id
            deleteById()
            true
        }
    }

    private fun setButtonClicks() {
        val saveButton = findViewById<Button>(R.id.saveConsultationButton)
        val editButton = findViewById<Button>(R.id.editConsultationButton)
        val arrowBackImage = findViewById<ImageView>(R.id.right_icon_two)

        saveButton.setOnClickListener { save() }
        editButton.setOnClickListener { edit() }
        arrowBackImage.setOnClickListener{onBackPressed()}
    }

    private fun setClickSpinner() {
        val animalSpinner = findViewById<Spinner>(R.id.animalConsultationSpinner)

        animalSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                val animalSelected = parentView.getItemAtPosition(position).toString()
                idAnimalSelect = animalSelected.split("-")[0].trim().toInt()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun updateListView() {
        consultations.clear()
        val listView = findViewById<ListView>(R.id.consultationListView)

        this.consultationRepository.findAll().forEach { consultationEntity ->
            consultationEntity.animal =
                this.animalRepository.findById(consultationEntity.animal?.id)

            consultations.add(consultationEntity)
        }

        listView.adapter = this.listViewAdapter
    }

    private fun updateAnimalSpinner() {
        val animals = ArrayList<String>()

        animalRepository.findAll().forEach { animalEntity ->
            val kindData = "${animalEntity.id} - ${animalEntity.name}"

            animals.add(kindData)
        }

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, animals)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner = findViewById<Spinner>(R.id.animalConsultationSpinner)
        spinner.adapter = arrayAdapter
    }

    private fun save() {
        val descriptionConsultationText = findViewById<TextView>(R.id.descriptionConsultationText)
        val scheduleTimeConsultationText = findViewById<TextView>(R.id.scheduleTimeConsultationText)
        val currentDescription = descriptionConsultationText.text.toString().trim()
        val currentScheduleTime = scheduleTimeConsultationText.text.toString().trim()

        val consultationEntity = ConsultationEntity()
        consultationEntity.description = currentDescription
        consultationEntity.scheduledTime = currentScheduleTime
        consultationEntity.animal = AnimalEntity()
        consultationEntity.animal?.id = idAnimalSelect

        consultationRepository.create(consultationEntity)

        descriptionConsultationText.text = ""
        scheduleTimeConsultationText.text = ""

        updateListView()
    }

    private fun edit() {
        if (this.idConsultationSelected != 0) {
            val descriptionConsultationText =
                findViewById<TextView>(R.id.descriptionConsultationText)
            val scheduleTimeConsultationText =
                findViewById<TextView>(R.id.scheduleTimeConsultationText)
            val currentDescription = descriptionConsultationText.text.toString().trim()
            val currentScheduleTime = scheduleTimeConsultationText.text.toString().trim()

            val consultationEntity = ConsultationEntity()
            consultationEntity.id = idConsultationSelected
            consultationEntity.description = currentDescription
            consultationEntity.scheduledTime = currentScheduleTime
            consultationEntity.animal = AnimalEntity()
            consultationEntity.animal?.id = idAnimalSelect

            consultationRepository.updateById(consultationEntity)

            descriptionConsultationText.text = ""
            scheduleTimeConsultationText.text = ""

            updateListView()
        }
    }

    private fun deleteById() {
        consultationRepository.deleteById(this.idConsultationSelected)
        updateListView()
    }
}