package com.github.nunes03.av2.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.nunes03.av2.R
import com.github.nunes03.av2.entities.UserEntity

class CreateAnimalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela2)
        setOnClickListeners()
    }

    override fun onBackPressed() {
        cancelar()
    }

    private fun setOnClickListeners() {
        val botaoCancelar: Button = findViewById(R.id.botaoCancelarTela2);
        val botaoSalvar: Button = findViewById(R.id.botaoSalvarTela2);

        botaoSalvar.setOnClickListener { salvar() }
        botaoCancelar.setOnClickListener { cancelar() }
    }

    private fun salvar() {
        if (valido()) {
                aplicarObjeto()


            setarMensagemValidacao("")
            intent.apply {
                putExtra("valido", true)
            }

            finish()
        } else {
            setarMensagemValidacao("Existem campos em branco ou vazio")
            intent.apply {
                putExtra("valido", false)
            }
        }
    }

    private fun valido(): Boolean {
        return validarNome() && validarEmail()
    }

    private fun validarNome(): Boolean {
        val nome = textNome().text.toString()

        return nome.trim().isNotEmpty()
    }

    private fun validarEmail(): Boolean {
        val email = textEmail().text.toString()

        return email.trim().isNotEmpty()
    }

    private fun setarMensagemValidacao(mensagem: String) {
        textValidarTela2().text = mensagem;
    }

    private fun cancelar() {
        intent.apply {
            putExtra("valido", false)
        }
        finish()
    }

    private fun montarCliente(): UserEntity {
        val cliente = UserEntity()
        cliente.name = textNome().text.toString()
        cliente.email = textEmail().text.toString()

        return cliente
    }

    private fun aplicarObjeto() {
        intent.apply {
            putExtra("cliente", montarCliente())
        }

        setResult(0, intent)
    }

    private fun textNome(): EditText = findViewById(R.id.textNomeCdastrarAnimal)

    private fun textEmail(): EditText = findViewById(R.id.textEspecieCdastrarAnimal)

    private fun textValidarTela2(): TextView = findViewById(R.id.textValidarTela02)
}