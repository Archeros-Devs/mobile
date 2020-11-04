package br.com.arquerosdev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.arquerosdev.adapter.EstudoAdapter
import br.com.arquerosdev.adapter.PastaAdapter
import br.com.arquerosdev.model.ModelEstudo
import br.com.arquerosdev.model.ModelPasta
import br.com.arquerosdev.retrofit.service.APIsWebClient
import br.com.arquerosdev.retrofit.service.CallbackResponse
import br.com.arquerosdev.viewmodel.EstudoViewModel
import br.com.arquerosdev.viewmodel.PastaViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_pasta_estudo.*
import kotlinx.android.synthetic.main.activity_pasta_lista.*
import kotlinx.android.synthetic.main.activity_pasta_perfil.*

class PastaEstudoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasta_estudo)

        val pasta = intent.getParcelableExtra<ModelPasta>("pasta")
        val estudoViewModel: EstudoViewModel = ViewModelProvider(this)
            .get(EstudoViewModel::class.java)

        bt_send.setOnClickListener { view ->

            val msg = ModelEstudo(
                0,
                0,
                Prefs.getInt("id_usuario"),
                pasta!!.idpasta,
                0,
                et_msg.text.toString(),
                "",
                ""
            )

            val gson = Gson()
            val strMSG: String = gson.toJson(msg)

            APIsWebClient().criarEstudo(strMSG, object : CallbackResponse<ModelEstudo> {
                override fun sucess(response: ModelEstudo) {

                    ViewModelProvider(this@PastaEstudoActivity)
                        .get(estudoViewModel::class.java).update(response)

                }

                override fun error(response: String) {
                    Toast.makeText(this@PastaEstudoActivity,response, Toast.LENGTH_LONG).show()
                }
            })

            estudoViewModel.insertMsg(msg)
            et_msg.text.toString()
        }

        recyclePastas?.layoutManager = LinearLayoutManager(this)
        recyclePastas?.itemAnimator = DefaultItemAnimator()
        recyclePastas?.setHasFixedSize(true)

        estudoViewModel.getEstudoChat(pasta!!.idpasta).observe(this, Observer { listaEstudo ->
            recycleEstudo?.adapter = EstudoAdapter(listaEstudo) {}
        })
    }
}