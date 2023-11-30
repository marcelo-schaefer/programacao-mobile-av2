package com.github.nunes03.av2.activities

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.nunes03.av2.R
import com.github.nunes03.av2.database.repositories.KindRepository
import com.github.nunes03.av2.entities.KindEntity

class KindActivity : AppCompatActivity() {

    private lateinit var kindRepository: KindRepository;

    private val kinds = ArrayList<String>()

    private lateinit var adapter: ArrayAdapter<String>

    private var idSelectedKind: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kind)

        initVariables()
        defineButtonFunction()
        prepareListView()
        updateList()
    }

    private fun initVariables() {
        this.kindRepository = KindRepository(applicationContext)
        this.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, kinds)
    }

    private fun defineButtonFunction() {
        val saveButton = findViewById<Button>(R.id.saveKindButton)
        val editButton = findViewById<Button>(R.id.editKindButton)

        saveButton.setOnClickListener { save() }
        editButton.setOnClickListener { edit() }
    }

    private fun prepareListView() {
        val listView: ListView = findViewById(R.id.kindListView)
        val nameKindText = findViewById<TextView>(R.id.nameKindText)

        listView.setOnItemClickListener { parent, view, position, id ->
            val kindSplit = this.kinds[position].split("-")
            this.idSelectedKind = kindSplit[0].toInt()
            nameKindText.text = kindSplit[1]
        }

        listView.setOnItemLongClickListener { parent, view, position, id ->
            val kindSplit = this.kinds[position].split("-")
            this.idSelectedKind = kindSplit[0].toInt()
            deleteById()
            this.kinds.clear()
            true
        }
    }

    private fun save() {
        val nameKindText = findViewById<TextView>(R.id.nameKindText)
        val currentName = nameKindText.text.toString().trim()

        val kindEntity = KindEntity()
        kindEntity.name = currentName

        kindRepository.create(kindEntity)

        updateList()
    }

    private fun edit() {
        val nameKindText = findViewById<TextView>(R.id.nameKindText)
        val currentName = nameKindText.text.toString().trim()

        val kindEntity = KindEntity()
        kindEntity.id = idSelectedKind
        kindEntity.name = currentName

        kindRepository.updateById(kindEntity)
        updateList()
    }

    private fun deleteById() {
        kindRepository.deleteById(this.idSelectedKind)
        updateList()
    }

    private fun updateList() {
        log("Entrou no updateList")

        val kindEntities = kindRepository.findAll()

        kindEntities.forEach { kindEntity ->
            val data = "${kindEntity.id} - ${kindEntity.name}";
            log(data)
            kinds.add(data)
        }

        adapter.notifyDataSetChanged()
    }

    private fun log(message: String) {
        Log.d(KindActivity::class.simpleName, message)
    }
}