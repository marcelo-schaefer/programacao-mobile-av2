package com.github.nunes03.av1.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.nunes03.av1.R

class Tela2Activity : AppCompatActivity() {

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
            val retornarObjeto = intent.getBooleanExtra("retornarObjeto", true);

            if (retornarObjeto) {
                aplicarObjeto()
            } else {
                aplicarParametro()
            }

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
        return validarId() && validarNome() && validarEmail()
    }

    private fun validarId(): Boolean {
        val id = textId().text.toString()

        return id.trim().isNotEmpty()
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

    private fun montarCliente(): Client {
        val cliente = Client()
        cliente.id = Integer.parseInt(textId().text.toString())
        cliente.nome = textNome().text.toString()
        cliente.email = textEmail().text.toString()

        return cliente
    }

    private fun aplicarObjeto() {
        intent.apply {
            putExtra("cliente", montarCliente())
        }

        setResult(0, intent)
    }

    private fun aplicarParametro() {
        val id = Integer.parseInt(textId().text.toString())
        val nome = textNome().text.toString()
        val email = textEmail().text.toString()

        intent.apply {
            putExtra("idCliente", id)
            putExtra("nomeCliente", nome)
            putExtra("emailCliente", email)
        }

        setResult(1, intent)
    }

    private fun textId(): EditText = findViewById(R.id.textIdTela2)

    private fun textNome(): EditText = findViewById(R.id.textNomeTela2)

    private fun textEmail(): EditText = findViewById(R.id.textEmailTela2)

    private fun textValidarTela2(): TextView = findViewById(R.id.textValidarTela02)
}