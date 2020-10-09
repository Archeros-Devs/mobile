package br.com.arquerosdev

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.model.ModelEscolaridade
import br.com.arquerosdev.model.ModelProfissao
import br.com.arquerosdev.retrofit.service.ProfissoesResponse
import br.com.arquerosdev.retrofit.service.APIsWebClient
import br.com.arquerosdev.retrofit.service.EscolaridadeResponse
import br.com.arquerosdev.viewmodel.EscolaridadeViewModel
import br.com.arquerosdev.viewmodel.ProfisaoViewModel


class SplashActivity : BaseActivity(){

    private var TAG:String = "SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val ctx = this
        val profissaoViewModel: ProfisaoViewModel = ViewModelProvider(this)
            .get(ProfisaoViewModel::class.java)
        APIsWebClient().listProfissoes(object: ProfissoesResponse {
              override fun sucess(profissoes: List<ModelProfissao>) {
                  profissaoViewModel.insert(profissoes)
            }

        })

        val escolaridadeViewModel: EscolaridadeViewModel = ViewModelProvider(this)
            .get(EscolaridadeViewModel::class.java)
        APIsWebClient().listEscolaridade(object: EscolaridadeResponse {
            override fun sucess(escolaridades: List<ModelEscolaridade>) {
                escolaridadeViewModel.insert(escolaridades)
            }

        })
        startActivity(Intent(ctx, LoginActivity::class.java))

    }
}