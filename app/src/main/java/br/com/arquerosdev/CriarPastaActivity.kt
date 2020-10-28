package br.com.arquerosdev

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.model.ModelPasta
import br.com.arquerosdev.retrofit.service.APIsWebClient
import br.com.arquerosdev.retrofit.service.CallbackResponse
import br.com.arquerosdev.viewmodel.PastaViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_nova_pasta.*
import java.sql.Date
import java.time.LocalDateTime
import java.util.*

class CriarPastaActivity : AppCompatActivity() {

    private val ctx: CriarPastaActivity get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_pasta)

        etSalvar.setOnClickListener { salvarNovaPasta() }
        etCancelar.setOnClickListener { finish() }

    }

    private fun salvarNovaPasta(){

        if(checarCampos()){
            val nomePasta = etNome_pasta.text.toString()
            val discusao = etDiscusao.text.toString()
            val categorias = spCategorias.selectedItemPosition
            val descricao = etDescricao.text.toString()
            val endereco = etEndereco.text.toString()

            val pasta = ModelPasta(
                0,
                0,
                Prefs.getInt("id_usuario"),
                nomePasta,
                descricao,
                "[$categorias]",
                discusao,
                endereco,
                null,
                null,
                null
            )

            val pastaViewModel = ViewModelProvider(this)
                .get(PastaViewModel::class.java)
            pastaViewModel.insert(pasta)

            val gson = Gson()
            val strPasta: String = gson.toJson(pasta)

            APIsWebClient().criarPasta(strPasta, object : CallbackResponse<ModelPasta> {
                override fun sucess(response: ModelPasta) {

                    ViewModelProvider(ctx)
                        .get(PastaViewModel::class.java).update(response)

                    Toast.makeText(ctx,"Pasta Criada!",Toast.LENGTH_LONG).show()
                    finish()
                }

                override fun error(response: String) {
                    Toast.makeText(ctx,response,Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun checarCampos(): Boolean {
        val nomePasta = etNome_pasta.text.toString()
        val discusao = etDiscusao.text.toString()
        val categorias = spCategorias.selectedItem.toString()
        val descricao = etDescricao.text.toString()
        val endereco = etEndereco.text.toString()

        if(nomePasta.isBlank() || nomePasta.length < 3){
            etNome_pasta.requestFocus()
            etNome_pasta.error = "Valor inválido!"
            return false
        }
        if(discusao.isBlank() || discusao.length < 3){
            etDiscusao.requestFocus()
            etDiscusao.error = "Valor inválido!"
            return false
        }
        if(categorias.isBlank()){
            spCategorias.requestFocus()
            Toast.makeText(this, "Selecione uma categoria!", Toast.LENGTH_LONG).show()
            return false
        }
        if(descricao.isBlank() || descricao.length < 3){
            etDescricao.requestFocus()
            etDescricao.error = "Valor inválido!"
            return false
        }
        if(endereco.isBlank() || endereco.length < 3){
            etEndereco.requestFocus()
            etEndereco.error = "Valor inválido!"
            return false
        }
        return true
    }
}
