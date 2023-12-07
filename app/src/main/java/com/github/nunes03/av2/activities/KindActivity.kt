package com.github.nunes03.av2.activities

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.nunes03.av2.R
import com.github.nunes03.av2.database.repositories.KindRepository
import com.github.nunes03.av2.database.entities.KindEntity

class KindActivity : AppCompatActivity() {

    private lateinit var kindRepository: KindRepository;

    private val kinds = ArrayList<String>()

    private lateinit var adapter: ArrayAdapter<String>

    private var idSelectedKind: Int = 0

    override fun onBackPressed() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kind)

        initVariables()
        defineClickFunction()
        prepareListView()
        updateList()
    }

    private fun initVariables() {
        this.kindRepository = KindRepository(applicationContext)
        this.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, kinds)
    }

    private fun defineClickFunction() {
        val saveButton = findViewById<Button>(R.id.saveKindButton)
        val editButton = findViewById<Button>(R.id.editKindButton)
        val nameKindEditText = findViewById<EditText>(R.id.nameKindText)
        val arrowBackImage = findViewById<ImageView>(R.id.right_icon_two)

        saveButton.setOnClickListener { save() }
        editButton.setOnClickListener { edit() }
        arrowBackImage.setOnClickListener{onBackPressed()}
        nameKindEditText.setOnClickListener { nameKindEditText.requestFocus() }
    }

    private fun prepareListView() {
        val listView: ListView = findViewById(R.id.kindListView)
        val nameKindEditText = findViewById<TextView>(R.id.nameKindText)

        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->
            val kindSplit = this.kinds[position].split("-")
            this.idSelectedKind = kindSplit[0].trim().toInt()
            nameKindEditText.text = kindSplit[1]
        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
            val kindSplit = this.kinds[position].split("-")
            this.idSelectedKind = kindSplit[0].trim().toInt()
            deleteById()
            true
        }
    }

    private fun save() {
        val nameKindText = findViewById<TextView>(R.id.nameKindText)
        val currentName = nameKindText.text.toString().trim()

        val kindEntity = KindEntity()
        kindEntity.name = currentName

        kindRepository.create(kindEntity)

        nameKindText.text = ""
        updateList()
    }

    private fun edit() {
        if (this.idSelectedKind != 0) {
            val nameKindText = findViewById<TextView>(R.id.nameKindText)
            val currentName = nameKindText.text.toString().trim()

            val kindEntity = KindEntity()
            kindEntity.id = idSelectedKind
            kindEntity.name = currentName

            kindRepository.updateById(kindEntity)

            idSelectedKind = 0
            nameKindText.text = ""
            updateList()
        }
    }

    private fun deleteById() {
        kindRepository.deleteById(this.idSelectedKind)
        updateList()
    }

    private fun updateList() {
        val kindEntities = kindRepository.findAll()
        kinds.clear()

        kindEntities.forEach { kindEntity ->
            val data = "${kindEntity.id} - ${kindEntity.name}"
            kinds.add(data)
        }

        adapter.notifyDataSetChanged()
    }
}