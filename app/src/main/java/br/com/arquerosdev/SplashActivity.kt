package br.com.arquerosdev

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.model.ModelEscolaridade
import br.com.arquerosdev.model.ModelPasta
import br.com.arquerosdev.model.ModelProfissao
import br.com.arquerosdev.retrofit.service.APIsWebClient
import br.com.arquerosdev.retrofit.service.CallbackResponse
import br.com.arquerosdev.viewmodel.EscolaridadeViewModel
import br.com.arquerosdev.viewmodel.PastaViewModel
import br.com.arquerosdev.viewmodel.ProfisaoViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken


class SplashActivity : BaseActivity(){

    private var TAG:String = "SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(verifyAvailableNetwork(this)){
            callProfissoes()
        }else{
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            Toast.makeText(this,"Sem conexão com a Internet!",Toast.LENGTH_LONG).show()
            finish()
        }

    }
    private fun callProfissoes(){
        val profissaoViewModel: ProfisaoViewModel = ViewModelProvider(this)
            .get(ProfisaoViewModel::class.java)
        APIsWebClient().listProfissoes(object: CallbackResponse<List<ModelProfissao>> {
            override fun sucess(response: List<ModelProfissao>) {
                profissaoViewModel.insert(response)
                callEscolaridade()
            }

            override fun error(response: String) {
                callEscolaridade()
            }

        })
    }

    private fun callEscolaridade(){
        val escolaridadeViewModel: EscolaridadeViewModel = ViewModelProvider(this)
            .get(EscolaridadeViewModel::class.java)
        APIsWebClient().listEscolaridade(object: CallbackResponse<List<ModelEscolaridade>> {
            override fun sucess(response: List<ModelEscolaridade>) {
                escolaridadeViewModel.insert(response)
                callPastas()
            }

            override fun error(response: String) {
                callPastas()
            }
        })
    }

    private fun callPastas(){
        val pastaViewModel: PastaViewModel = ViewModelProvider(this)
            .get(PastaViewModel::class.java)
        APIsWebClient().listPastas(object: CallbackResponse<JsonObject> {
            override fun sucess(response: JsonObject) {

                val listaPasta = response.getAsJsonArray("pastas")

                val gson = Gson()
                val typeResponse = object : TypeToken<List<ModelPasta>>() {}.type
                val listModelPasta: List<ModelPasta> = gson.fromJson(listaPasta, typeResponse)
                pastaViewModel.inserList(listModelPasta)

                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }

            override fun error(response: String) {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
        })
    }

    //TODO: fazer essa funcao publica para ser utlilizada em varios locais
    private fun verifyAvailableNetwork(activity: Activity):Boolean{
        val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}