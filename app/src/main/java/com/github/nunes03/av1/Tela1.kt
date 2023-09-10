package com.github.nunes03.av1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.github.nunes03.av1.entities.Cliente

class Tela1 : AppCompatActivity() {

    val acaoAposRetorno =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            tratarRetorno(result.resultCode, result.data)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela1)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        val botaoParametro: Button = findViewById(R.id.botaoParametroTela1)
        val botaoObjeto: Button = findViewById(R.id.botaoObjetoTela1)
        val botaoLimpar: Button = findViewById(R.id.botaoLimparTela1)

        botaoParametro.setOnClickListener { parametro() }
        botaoObjeto.setOnClickListener { objeto() }
        botaoLimpar.setOnClickListener { limpar() }
    }

    private fun parametro() {
        val tela02 = Intent(this, Tela2::class.java)
        tela02.apply {
            putExtra("retornarObjeto", false)
        }
        acaoAposRetorno.launch(tela02)
    }

    private fun objeto() {
        val tela02 = Intent(this, Tela2::class.java)
        tela02.apply {
            putExtra("retornarObjeto", true)
        }
        acaoAposRetorno.launch(tela02)
    }

    private fun limpar() {
        val textCliente = getTextClienteTela1()
        textCliente.text = ""
    }

    private fun tratarRetorno(code: Int, retorno: Intent?) {
        val valido = retorno?.getBooleanExtra("valido", false)

        if (valido == true) {
            val cliente: Cliente? = if (code == 0) {
                retorno?.getParcelableExtra("cliente");
            } else {
                montarCliente(retorno)
            }

            mostrarDados(cliente)
        }
    }

    private fun montarCliente(retorno: Intent?): Cliente {
        val cliente = Cliente()
        cliente.id = retorno?.getIntExtra("idCliente", 0)
        cliente.nome = retorno?.getStringExtra("nomeCliente")
        cliente.email = retorno?.getStringExtra("emailCliente")

        return cliente;
    }

    private fun mostrarDados(cliente: Cliente?) {
        val textCliente = getTextClienteTela1()

        if (textCliente.text.isEmpty()) {
            textCliente.text = montarDado(cliente)
        } else {
            val novoDado = montarDado(cliente)
            val dadoAtual = textCliente.text.toString()

            textCliente.text = "$novoDado\n_________________________\n$dadoAtual"
        }
    }

    private fun montarDado(cliente: Cliente?): String {
        return "Id: ${cliente?.id}" +
                "\nNome: ${cliente?.nome}" +
                "\nE-mail: ${cliente?.email}"
    }

    private fun getTextClienteTela1(): TextView = findViewById(R.id.textClienteTela1)
}