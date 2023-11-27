package com.github.nunes03.av1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.github.nunes03.av1.R
import io.github.nunes03.pet_shop.database.UserRepository

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.botaoEntrarLogin);
        val createAccountButton: Button = findViewById(R.id.botaoCriarContaLogin);

        loginButton.setOnClickListener { login() };
        createAccountButton.setOnClickListener { createAccount() };
    }

    private fun login() {
        val email = getTextEmail().text.toString()
        val password = getTextLogin().text.toString()

        if (UserRepository.existByEmailAndPassword(email, password)) {
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