package br.com.arquerosdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.arquerosdev.adapter.PastaAdapter
import br.com.arquerosdev.model.ModelPasta
import br.com.arquerosdev.retrofit.service.APIsWebClient
import br.com.arquerosdev.retrofit.service.CallbackResponse
import br.com.arquerosdev.viewmodel.PastaViewModel
import com.google.android.material.snackbar.Snackbar
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

    fun onClickPasta(pasta: ModelPasta){
        //TODO: Criar Activity para visualizar a pasta (Pensar o Fluxo de tela e arquitetura)
        // val it = Intent(this, PastaActivity::class.java)
        // it.putExtra("pasta", pasta)
        // startActivity(it)
    }
}