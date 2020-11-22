package br.com.arquerosdev

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
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
import br.com.arquerosdev.views.FragSplash_1
import br.com.arquerosdev.views.FragmentsLogin
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken


class SplashActivity : BaseActivity(){

    private var TAG:String = "SplashActivity"

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_splash)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        if(verifyAvailableNetwork(this)){
            callProfissoes()
        }else{
            //startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            Toast.makeText(this,"Sem conexão com a Internet!",Toast.LENGTH_LONG).show()
            //finish()
        }

        if(icicle == null){
            val ft = supportFragmentManager.beginTransaction()
            val flagSplash_1 = FragSplash_1()
            ft.setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            ft.replace(R.id.frag_main, flagSplash_1, "flagSplash_1")
            ft.addToBackStack("flagSplash_1")
            ft.commit()
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
                Toast.makeText(this@SplashActivity,"Sem conexão com a Internet!",Toast.LENGTH_LONG).show()
                callEscolaridade()
            }

        })
    }

    private fun callEscolaridade(){
        val escolaridadeViewModel: EscolaridadeViewModel = ViewModelProvider(this)
            .get(EscolaridadeViewModel::class.java)
        APIsWebClient().listEscolaridade(object: CallbackResponse<List<ModelEscolaridade>> {
            override fun sucess(response: List<ModelEscolaridade>) {
                /*escolaridadeViewModel.insert(response)
                //TODO: Tornar funcoes repitidas em uma só
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()*/
            }

            override fun error(response: String) {
                /*Toast.makeText(this@SplashActivity,"Sem conexão com a Internet!",Toast.LENGTH_LONG).show()
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()*/
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