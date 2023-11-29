package com.github.nunes03.av2.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.nunes03.av2.R
import com.github.nunes03.av2.database.repositories.KindRepository
import com.github.nunes03.av2.entities.KindEntity

class KindActivity : AppCompatActivity() {

    private lateinit var kindRepository: KindRepository;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kind)

        val saveButton = findViewById<Button>(R.id.saveKindButton)
        val editButton = findViewById<Button>(R.id.editKindButton)

        saveButton.setOnClickListener { save() }
        editButton.setOnClickListener { edit() }

        kindRepository = KindRepository(applicationContext)
    }

    private fun save() {
        val nameKindText = findViewById<TextView>(R.id.nameKindText)

        val kindEntity = KindEntity()
        kindEntity.name = nameKindText.text.toString()

        kindRepository.create(kindEntity)


    }

    private fun edit() {

    }

    private fun updateById(id: Int?) {
        val kindRepository = KindRepository(applicationContext);

        val kindEntity = KindEntity()
        kindEntity.id = id

        kindRepository.updateById(kindEntity)
    }

    private fun deleteById(id: Int?) {
        val kindRepository = KindRepository(applicationContext);

        kindRepository.deleteById(id ?: 0)
    }
}