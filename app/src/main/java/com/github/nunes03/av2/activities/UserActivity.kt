package com.github.nunes03.av2.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.nunes03.av2.R
import com.github.nunes03.av2.database.entities.UserEntity
import com.github.nunes03.av2.database.repositories.UserRepository

class UserActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository;

    private val users = ArrayList<String>()

    private lateinit var adapter: ArrayAdapter<String>

    private var idSelectedUser: Int = 0

    override fun onBackPressed() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        initVariables()
        defineClickFunction()
        prepareListView()
        updateList()
    }

    private fun initVariables() {
        this.userRepository = UserRepository(applicationContext)
        this.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
    }

    private fun defineClickFunction() {
        val saveButton = findViewById<Button>(R.id.saveUserButton)
        val editButton = findViewById<Button>(R.id.editUserButton)
        val nameUserEditText = findViewById<EditText>(R.id.nameUserText)
        val emailUserEditText = findViewById<EditText>(R.id.emailUserText)
        val arrowBackImage = findViewById<ImageView>(R.id.right_icon_two)

        saveButton.setOnClickListener { save() }
        editButton.setOnClickListener { edit() }
        arrowBackImage.setOnClickListener { onBackPressed() }
        nameUserEditText.setOnClickListener { nameUserEditText.requestFocus() }
        emailUserEditText.setOnClickListener { emailUserEditText.requestFocus() }
    }

    private fun prepareListView() {
        val listView: ListView = findViewById(R.id.userListView)
        val nameUserEditText = findViewById<TextView>(R.id.nameUserText)
        val emailUserEditText = findViewById<TextView>(R.id.emailUserText)

        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->
            val userSplit = this.users[position].split("-")
            this.idSelectedUser = userSplit[0].trim().toInt()
            nameUserEditText.text = userSplit[1]
            emailUserEditText.text = userSplit[2]
        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
            val userSplit = this.users[position].split("-")
            this.idSelectedUser = userSplit[0].trim().toInt()
            deleteById()
            true
        }
    }

    private fun save() {
        val nameUserText = findViewById<TextView>(R.id.nameUserText)
        val emailUserText = findViewById<TextView>(R.id.emailUserText)
        val currentName = nameUserText.text.toString().trim()
        val currentEmail = emailUserText.text.toString().trim()

        val userEntity = UserEntity()
        userEntity.name = currentName
        userEntity.email = currentEmail

        userRepository.create(userEntity)

        nameUserText.text = ""
        emailUserText.text = ""
        updateList()
    }

    private fun edit() {
        if (this.idSelectedUser != 0) {
            val nameUserText = findViewById<TextView>(R.id.nameUserText)
            val emailUserText = findViewById<TextView>(R.id.emailUserText)
            val currentName = nameUserText.text.toString().trim()
            val currentEmail = emailUserText.text.toString().trim()

            val userEntity = UserEntity()
            userEntity.id = idSelectedUser
            userEntity.name = currentName
            userEntity.email = currentEmail

            userRepository.updateById(userEntity)

            idSelectedUser = 0
            nameUserText.text = ""
            emailUserText.text = ""

            updateList()
        }
    }

    private fun deleteById() {
        userRepository.deleteById(this.idSelectedUser)
        updateList()
    }

    private fun updateList() {
        val userEntities = userRepository.findAll()
        users.clear()

        userEntities.forEach { userEntity ->
            val data = "${userEntity.id} - ${userEntity.name} - ${userEntity.email}"
            users.add(data)
        }

        adapter.notifyDataSetChanged()
    }
}