package br.com.arquerosdev

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.model.ModelEscolaridade
import br.com.arquerosdev.model.ModelProfissao
import br.com.arquerosdev.retrofit.service.APIsWebClient
import br.com.arquerosdev.retrofit.service.CallbackResponse
import br.com.arquerosdev.viewmodel.EscolaridadeViewModel
import br.com.arquerosdev.viewmodel.ProfisaoViewModel


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
                TODO("Not yet implemented")
            }

        })
    }

    private fun callEscolaridade(){
        val escolaridadeViewModel: EscolaridadeViewModel = ViewModelProvider(this)
            .get(EscolaridadeViewModel::class.java)
        APIsWebClient().listEscolaridade(object: CallbackResponse<List<ModelEscolaridade>> {
            override fun sucess(response: List<ModelEscolaridade>) {
                escolaridadeViewModel.insert(response)
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }

            override fun error(response: String) {
                TODO("Not yet implemented")
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