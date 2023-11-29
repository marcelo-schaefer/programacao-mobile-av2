package com.github.nunes03.av2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.github.nunes03.av2.R
import com.github.nunes03.av2.entities.UserEntity

class CreateAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        setClickEvents()
    }

    private fun setClickEvents() {
        val saveButton: Button = findViewById(R.id.buttonSaveCreateAccount);
        val backButton: Button = findViewById(R.id.buttonBackCreateAccount);

        saveButton.setOnClickListener { save() };
        backButton.setOnClickListener { backToLogin() };
    }

    private fun save() {
        if (isValid()) {
            val newUser = buildUser()
        }
    }

    private fun backToLogin() {
        finish();
    }

    private fun isValid(): Boolean {
        return isEmailValid() && isPasswordValid();
    }

    private fun isEmailValid(): Boolean {
        val email = getTextEmail().text.toString()

        if (true) {
            getTextValidate().text = "E-mail já cadastrado"

            return false;
        }

        getTextValidate().text = ""
        return true;
    }

    private fun isPasswordValid(): Boolean {
        val password: String = getTextPassword().text.toString()
        val confirmPassword: String = getTextConfirmPassword().text.toString()

        if (password != confirmPassword) {
            getTextValidate().text = "As senhas devem ser iguais"

            return false;
        }

        return true;
    }

    private fun buildUser(): UserEntity {
        val email = getTextEmail().text.toString();
        val password = getTextConfirmPassword().text.toString();

        return UserEntity();
    }

    private fun getTextEmail(): TextView = findViewById(R.id.textEmailCreateAccount)

    private fun getTextPassword(): TextView = findViewById(R.id.textPasswordCreateAccount)

    private fun getTextConfirmPassword(): TextView =
        findViewById(R.id.textConfirmPasswordCreateAccount)

    private fun getTextValidate(): TextView = findViewById(R.id.textValidateCreateAccount)
}