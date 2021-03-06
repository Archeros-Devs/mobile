package br.com.arquerosdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.arquerosdev.adapter.PastaAdapter
import br.com.arquerosdev.model.ModelPasta
import br.com.arquerosdev.retrofit.service.APIsWebClient
import br.com.arquerosdev.retrofit.service.CallbackResponse
import br.com.arquerosdev.viewmodel.PastaViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_pasta_lista.*

class PastaListaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasta_lista)

        recyclePastas?.layoutManager = LinearLayoutManager(this)
        recyclePastas?.itemAnimator = DefaultItemAnimator()
        recyclePastas?.setHasFixedSize(true)

        val fab: View = findViewById(R.id.addPasta)
        fab.setOnClickListener { view ->
            startActivity(Intent(this, CriarPastaActivity::class.java))
        }

        val pastaViewModel: PastaViewModel = ViewModelProvider(this)
            .get(PastaViewModel::class.java)
        pastaViewModel.modelPasta.observe(this, Observer { listaPasta ->
            recyclePastas?.adapter = PastaAdapter(listaPasta) {onClickPasta(it)}
        })
    }

    override fun onResume() {
        super.onResume()
        callPastas()
    }

    private fun callPastas(){
        if(Prefs.getString("token").isNullOrEmpty()){
            Prefs.setBoolean("onSync",true)
            startActivity(Intent(this@PastaListaActivity, LoginActivity::class.java))
            finish()
        }else{
            val pastaViewModel: PastaViewModel = ViewModelProvider(this)
                .get(PastaViewModel::class.java)
            APIsWebClient().listPastas(object: CallbackResponse<JsonObject> {
                override fun sucess(response: JsonObject) {

                    val listaPasta = response.getAsJsonArray("pastas")

                    val gson = Gson()
                    val typeResponse = object : TypeToken<List<ModelPasta>>() {}.type
                    val listModelPasta: List<ModelPasta> = gson.fromJson(listaPasta, typeResponse)
                    pastaViewModel.inserList(listModelPasta)
                }

                override fun error(response: String) {
                    Toast.makeText(this@PastaListaActivity,"Falha ao baixar as pastas!", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun onClickPasta(pasta: ModelPasta){
        //TODO: Criar Activity para visualizar a pasta (Pensar o Fluxo de tela e arquitetura)
        val it = Intent(this@PastaListaActivity, PastaPerfilActivity::class.java)
        it.putExtra("pasta", pasta as Parcelable)
        startActivity(it)
    }
}