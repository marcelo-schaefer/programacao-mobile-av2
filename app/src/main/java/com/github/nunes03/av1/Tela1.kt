package com.github.nunes03.av1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.github.nunes03.av1.entities.Cliente

class Tela1 : AppCompatActivity() {

    private val acaoAposRetornoObjeto =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            tratarRetornoObjeto(result.data)
        }

    private val acaoAposRetornoParametro =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            tratarRetornoParametro(result.data)
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

        acaoAposRetornoParametro.launch(tela02)
    }

    private fun objeto() {
        val tela02 = Intent(this, Tela2::class.java)
        tela02.apply {
            putExtra("retornarObjeto", true)
        }

        acaoAposRetornoObjeto.launch(tela02)
    }

    private fun limpar() {
        val textCliente = getTextClienteTela1()
        textCliente.text = ""
    }

    private fun tratarRetornoObjeto(tela2: Intent?) {
        Log.d("tela1", "Retorno como objeto")

        if (tela2Valida(tela2) == true) {
            val cliente: Cliente? = tela2?.getParcelableExtra("cliente")

            mostrarDados(cliente)
        }
    }

    private fun tratarRetornoParametro(tela2: Intent?) {
        Log.d("tela1", "Retorno como parâmetro")

        if (tela2Valida(tela2) == true) {
            val cliente = montarCliente(tela2);

            mostrarDados(cliente)
        }
    }

    private fun tela2Valida(retorno: Intent?): Boolean? = retorno?.getBooleanExtra("valido", false)

    private fun montarCliente(retorno: Intent?): Cliente {
        val cliente = Cliente()
        cliente.id = retorno?.getIntExtra("idCliente", 0)
        cliente.nome = retorno?.getStringExtra("nomeCliente")
        cliente.email = retorno?.getStringExtra("emailCliente")

        return cliente
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