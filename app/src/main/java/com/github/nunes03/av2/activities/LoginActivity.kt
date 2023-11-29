package com.github.nunes03.av2.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.nunes03.av2.R
import com.github.nunes03.av2.database.repositories.KindRepository
import com.github.nunes03.av2.entities.KindEntity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.botaoEntrarLogin)
        val createAccountButton: Button = findViewById(R.id.botaoCriarContaLogin)

        loginButton.setOnClickListener { login() }
        createAccountButton.setOnClickListener { createAccount() }

        val kindRepository = KindRepository(applicationContext)
        val kinds = kindRepository.findAll()

        kinds.forEach {
            kind -> Log.d(LoginActivity::class.simpleName, "Id: ${kind?.name} | Name: ${kind?.name}")
        }
    }

    private fun login() {
        val email = getTextEmail().text.toString()
        val password = getTextLogin().text.toString()

        if (true) {
            getTextValidate().text = ""

            val homeActivity = Intent(this, Tela1Activity::class.java);
            startActivity(homeActivity);

        } else {
            getTextValidate().text = "E-mail ou senha incorretos"
        }
    }

    private fun createAccount() {
        val createAccountActivity = Intent(this, CreateAccountActivity::class.java);
        startActivity(createAccountActivity);
    }

    private fun getTextEmail(): TextView = findViewById(R.id.textEmailLogin)

    private fun getTextLogin(): TextView = findViewById(R.id.textSenhaLogin)

    private fun getTextValidate(): TextView = findViewById(R.id.textValidateLogin)
}