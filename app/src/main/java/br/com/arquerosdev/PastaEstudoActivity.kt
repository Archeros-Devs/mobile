package br.com.arquerosdev

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.arquerosdev.adapter.EstudoAdapter
import br.com.arquerosdev.model.ModelEstudo
import br.com.arquerosdev.model.ModelPasta
import br.com.arquerosdev.retrofit.service.APIsWebClient
import br.com.arquerosdev.retrofit.service.CallbackResponse
import br.com.arquerosdev.viewmodel.EstudoViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_pasta_estudo.*
import java.util.*

class PastaEstudoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasta_estudo)

        val pasta = intent.getParcelableExtra<ModelPasta>("pasta")
        val estudoViewModel: EstudoViewModel = ViewModelProvider(this)
            .get(EstudoViewModel::class.java)

        val ll = LinearLayoutManager(this)
        ll.stackFromEnd = true
        recycleEstudo?.layoutManager = ll
        recycleEstudo?.itemAnimator = DefaultItemAnimator()
        recycleEstudo?.setHasFixedSize(true)

        estudoViewModel.getEstudoChat(pasta!!.id_pasta).observe(this, Observer { listaEstudo ->
            recycleEstudo?.adapter = EstudoAdapter(Prefs.getInt("id_usuario"),listaEstudo) {}
        })

        bt_send.setOnClickListener { view ->
           val msg = ModelEstudo(
                System.currentTimeMillis(),
                0,
                Prefs.getInt("id_usuario"),
                pasta.id_pasta,
                1,
                et_msg.text.toString(),
                "",
                "",
                Prefs.getString("nome_usuario")
            )
            estudoViewModel.insertMsg(msg)
            Thread {
                val msgNova = estudoViewModel.getUltimoMSG(
                    Prefs.getInt("id_usuario"),
                    pasta.id_pasta,
                    et_msg.text.toString()
                )
                runOnUiThread {
                    val gsonEstudo = Gson()
                    val strMSG: String = gsonEstudo.toJson(msgNova)

                    APIsWebClient().criarEstudo(
                        strMSG,
                        pasta.id_pasta,
                        object : CallbackResponse<ModelEstudo> {
                            override fun sucess(response: ModelEstudo) {
                                ViewModelProvider(this@PastaEstudoActivity)
                                    .get(estudoViewModel::class.java).update(response)
                            }

                            override fun error(response: String) {
                                Toast.makeText(
                                    this@PastaEstudoActivity,
                                    response,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        })
                    et_msg.setText("")
                    et_msg.clearFocus()
                }
            }.start()
        }

        Timer().schedule(object : TimerTask() {
            override fun run() {
                APIsWebClient().listEstudo(
                    pasta.id_pasta.toString(),
                    object : CallbackResponse<JsonObject> {
                        override fun sucess(response: JsonObject) {
                            val listaEstudoResp = response.getAsJsonArray("estudos")

                            var listEstudos = ArrayList<ModelEstudo>()

                            listaEstudoResp.forEach{
                                val row = it.asJsonObject

                                val estudo = ModelEstudo(
                                    row.get("id_origem").asLong,
                                    row.get("id_mensagem").asInt,
                                    row.get("id_usuario").asInt,
                                    row.get("id_pasta").asInt,
                                    row.get("tipo").asInt,
                                    row.get("mensagem").asString,
                                    row.get("criado_em").asString,
                                    if (row.get("deletado_em").isJsonNull) "" else row.get("deletado_em").asString,
                                    row.get("usuario").asJsonObject.get("nome").asString
                                )

                                listEstudos.add(estudo)
                            }
                            estudoViewModel.insertList(listEstudos)
                        }

                        override fun error(response: String) {
                            Toast.makeText(
                                this@PastaEstudoActivity,
                                "Sem conexão com a Internet!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
            }
        }, 0, 5000)
    }
}