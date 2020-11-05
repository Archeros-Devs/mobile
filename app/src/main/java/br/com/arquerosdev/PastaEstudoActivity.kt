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

        recycleEstudo?.layoutManager = LinearLayoutManager(this)
        recycleEstudo?.itemAnimator = DefaultItemAnimator()
        recycleEstudo?.setHasFixedSize(true)

        estudoViewModel.getEstudoChat(pasta!!.id_pasta).observe(this, Observer { listaEstudo ->
            recycleEstudo?.adapter = EstudoAdapter(listaEstudo) {}
        })

        bt_send.setOnClickListener { view ->
            val msg = ModelEstudo(
                0,
                0,
                Prefs.getInt("id_usuario"),
                pasta.id_pasta,
                0,
                et_msg.text.toString(),
                "",
                ""
            )
            estudoViewModel.insertMsg(msg)
            Thread {
                val msgNova = estudoViewModel.getUltimoMSG(Prefs.getInt("id_usuario"),pasta.id_pasta,et_msg.text.toString())
                runOnUiThread {
                    val gsonEstudo = Gson()
                    val strMSG: String = gsonEstudo.toJson(msgNova)

                    APIsWebClient().criarEstudo(strMSG, pasta.id_pasta, object : CallbackResponse<ModelEstudo> {
                        override fun sucess(response: ModelEstudo) {
                            ViewModelProvider(this@PastaEstudoActivity)
                                .get(estudoViewModel::class.java).update(response)
                        }

                        override fun error(response: String) {
                            Toast.makeText(this@PastaEstudoActivity,response, Toast.LENGTH_LONG).show()
                        }
                    })

                    et_msg.setText("")
                    et_msg.clearFocus()
                }
            }.start()
        }
    }
}